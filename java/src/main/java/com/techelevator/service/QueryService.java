package com.techelevator.service;

import com.techelevator.dao.QueryDao;
import com.techelevator.model.Response;
import com.techelevator.model.UserInput;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Component
public class QueryService {

    // Constants
    int[] rankedIds = new int[]{
            3, // Star Method
            4, // Cover Letter
            5, // Recruiter
            9, // Attire
            7, // Tech Interview
            8, // HR Interview
            6, // General Interview
            2, // Chatbot
            1 }; // Default


    // Instance Variables
    public static final int DEFAULT_INTENT_ID = 1;
    public static final int DEFAULT_ENTITY_ID = 1;
    public static final int INTENTS_INDEX = 0;
    public static final int ENTITIES_INDEX = 1;
    private QueryDao queryDao;

    public QueryService (QueryDao queryDao){
        this.queryDao = queryDao;
    }


    /**
     *
     * @param userInput -- the user input provided by the client
     * @return the chatbot response
     */
    public Response getResponseFromUserInput(UserInput userInput){
        Response outputResponse = new Response();

        List<String> tokens = tokenizeUtterance(userInput);
        List<Integer>[] intentsAndEntities = getIntentsAndEntities(tokens, userInput);
        List<String> potentialResponses = getPotentialResponseList(intentsAndEntities[INTENTS_INDEX],
                intentsAndEntities[ENTITIES_INDEX]);

        outputResponse.setIntents(intentsAndEntities[INTENTS_INDEX]);
        outputResponse.setEntities(intentsAndEntities[ENTITIES_INDEX]);
        outputResponse.setResponse(selectResponse(potentialResponses));
        return outputResponse;
    }

    /**
     * This method takes the utterance provided by the client, cleans it, and breaks it into tokens
     *
     * @param userInput -- the Input from the client
     * @return List of all tokens from the utterance
     */
    private List<String> tokenizeUtterance(UserInput userInput) {
        List<String> tokens = new ArrayList<>();

        String utterance = userInput.getUtterance();
        utterance = utterance.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().trim();
        userInput.setUtterance(utterance);

        List<String> mutlipleWordKeywords  = queryDao.getAllMultiWordKeywords();
        for (String keyword : mutlipleWordKeywords){
            keyword = keyword.toLowerCase();
            if(utterance.contains(keyword)){
                tokens.add(keyword);
                utterance = utterance.replace(keyword, "");
            }
        }

        String[] potentialKeywordsArray = utterance.split(" ");
        Collections.addAll(tokens, potentialKeywordsArray);

        return tokens;
    }

    /**
     * This method gets the intents and entities of an utterance.
     * @param tokens -- the potential keywords from the utterance
     * @param userInput -- the userInput provided by the client
     * @return
     */
    private List<Integer>[] getIntentsAndEntities(List<String> tokens, UserInput userInput){
        List<Integer>[] intentsAndEntities = queryDao.getIntentsAndEntitiesFromKeywords(tokens);
        intentsAndEntities = setIntentsAndEntitiesWhenNoneReturned(intentsAndEntities[INTENTS_INDEX],
                intentsAndEntities[ENTITIES_INDEX], userInput);

        return intentsAndEntities;
    }

    /**
     * This method checks for empty intent and entity lists and fills in default values.
     * @param intentIds -- a list of intent Ids that are obtained from the utterance
     * @param entityIds -- a list of entity Ids that are obtained from the utterance
     * @param userInput -- the userInput provided by the client
     * @return a list of responses that have the highest number of keyword matches
     */
    private List<Integer>[] setIntentsAndEntitiesWhenNoneReturned(List<Integer>intentIds, List<Integer>entityIds, UserInput userInput){

        if (intentIds.size() == 0 && entityIds.size() == 0){
            intentIds.add(DEFAULT_INTENT_ID);
            entityIds.add(DEFAULT_ENTITY_ID);
        } else if (intentIds.size() == 0) {
            if (userInput.getIntents() != null && userInput.getIntents().size() != 0) {
                intentIds.addAll(userInput.getIntents());
            } else {
                intentIds.add(DEFAULT_INTENT_ID);  //adds default intent
            }
        } else if (entityIds.size() == 0) {
            if (userInput.getEntities() != null && userInput.getEntities().size() != 0){
                entityIds.addAll(userInput.getEntities());
            } else {
                entityIds.add(DEFAULT_ENTITY_ID); //adds default entity
            }
        }

        return new List[]{intentIds, entityIds};
    }

    /**
     * This method takes the keywords from the utterance and returns a list of responses that match them
     *
     * @param intentIds -- a list of intent Ids that are obtained from the utterance
     * @param entityIds -- a list of entity Ids that are obtained from the utterance
     * @return List of responses that fit the utterance
     */
    private List<String> getPotentialResponseList(List<Integer> intentIds, List<Integer> entityIds){
        entityIds = sortEntitiesByPriority(entityIds);
        List<String> responses = queryDao.getResponsesFromIntentsAndEntities(intentIds, entityIds);
        if (responses.size() == 0) {
            responses = handleZeroResponseMatches(intentIds, entityIds);
        }

        return responses;
    }

    /**
     * This method finds the appropriate catch-all response if the search for a specific response that matched the intents
     * and entities from the utterance has failed to return any responses.
     *
     * @param intentIds -- a list of intent Ids that are obtained from the utterance
     * @param entityIds -- a list of entity Ids that are obtained from the utterance
     * @return List of responses that match the catch-all conditions
     */
    private List<String> handleZeroResponseMatches(List<Integer> intentIds, List<Integer> entityIds) {
        List<String> responses = new ArrayList<>();

        List<Integer> defaultIntentList = new ArrayList<>();
        defaultIntentList.add(DEFAULT_INTENT_ID);

        List<Integer> defaultEntityList = new ArrayList<>();
        defaultEntityList.add(DEFAULT_ENTITY_ID);

        entityIds = sortEntitiesByPriority(entityIds);
        if (entityIds.get(0) != DEFAULT_ENTITY_ID){
            responses = queryDao.getResponsesFromIntentsAndEntities(defaultIntentList, entityIds);
        }

        if (responses.size() == 0){
            responses = queryDao.getResponsesFromIntentsAndEntities(intentIds, defaultEntityList);

            if(responses.size() == 0){
                responses = queryDao.getResponsesFromIntentsAndEntities(defaultIntentList, defaultEntityList);
            }
        }

        return responses;
    }

    /**
     *This method selects the appropriate response from a list of potential responses
     *
     * @param responses -- all responses that match the intents and entities in the utterance
     * @return the single response that best fits the utterance
     */
    private String selectResponse(List<String> responses){
        List<String> topResponses = getResponsesWithMostKeywordMatches(responses);

        //TODO this needs more logic, currently only returns the first of the responses with the most keyword matches
        return topResponses.get(0);
    }

    /**
     * @param responses -- all responses that match the intents and entities in the utterance
     * @return a list of responses that have the highest number of keyword matches
     */
    private List<String> getResponsesWithMostKeywordMatches(List<String> responses) {
        Map<String, Integer> responseCounts = new HashMap<>();

        responses.stream().forEach(response -> {
            if (!responseCounts.containsKey(response)){
                int count = Collections.frequency(responses, response);
                responseCounts.put(response, count);
            }
        });

        List<Entry<String, Integer>> countList = new ArrayList<>(responseCounts.entrySet());

        countList.sort(Entry.comparingByValue());
        Collections.reverse(countList);
        int maxCount = countList.get(0).getValue();
        List<String> topResults = countList.stream()
                .filter(response -> response.getValue() == maxCount)
                .map(Entry::getKey)
                .collect(Collectors.toList());

        return topResults;
    }

    /**
     * TODO -- rename to deprioritize chatbot
     * @param entityIds -- The list of unranked entity ids
     * @return the list of entity ids ranked by priority
     */
    private List<Integer> sortEntitiesByPriority(List<Integer> entityIds) {
        List<Integer> entityIdListWithoutChatbot = new ArrayList<>();

        for (int entityId : entityIds) {
            if (entityId != 2) {
                entityIdListWithoutChatbot.add(entityId);
            }
        }

        if (entityIdListWithoutChatbot.size() > 0) {
            return entityIdListWithoutChatbot;
        } else {
            return entityIds;
        }

        /*List<Integer> rankedList = new ArrayList<>();
        for (int currentRankId : rankedIds) {
            for (int currentEntityId : entityIds) {
                if (currentRankId == currentEntityId) {
                    rankedList.add(0, currentEntityId);
                }
            }
        }
        return rankedList; */
    }

}
