package com.techelevator.service;

import com.techelevator.dao.QueryDao;
import com.techelevator.model.Response;
import com.techelevator.model.UserInput;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Component
public class QueryService {

    // Constants
    private final int[] RANKED_ENTITY_IDS = new int[]{
            3, // Star Method
            4, // Cover Letter
            10, // Follow up
            5, // Recruiter
            9, // Attire
            7, // Tech Interview
            8, // HR Interview
            6, // General Interview
            2, // Chatbot
            1 }; // Default
    public final int DEFAULT_INTENT_ID = 1;
    public final int DEFAULT_ENTITY_ID = 1;
    public final int PRACTICE_INTENT_ID = 4;
    public final int HR_INTERVIEW_ENTITY_ID = 7;
    public final int TECHNICAL_INTERVIEW_ENTITY_ID = 8;
    public final int INTENTS_INDEX = 0;
    public final int ENTITIES_INDEX = 1;

    // Instance Variables
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
        List<Integer> intents = intentsAndEntities[INTENTS_INDEX];
        List<Integer> entities = intentsAndEntities[ENTITIES_INDEX];
        List<Response> potentialResponses = getPotentialResponseList(intents,
                entities);

        outputResponse.setUserIntents(intents);
        outputResponse.setUserEntities(entities);
        outputResponse.setResponse(selectResponse(potentialResponses,intents, entities));
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
    private List<Response> getPotentialResponseList(List<Integer> intentIds, List<Integer> entityIds){
        entityIds = sortEntitiesByPriority(entityIds);
        List<Response> responses = queryDao.getResponsesFromIntentsAndEntities(intentIds, entityIds);
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
    private List<Response> handleZeroResponseMatches(List<Integer> intentIds, List<Integer> entityIds) {
        List<Response> responses = new ArrayList<>();

        List<Integer> defaultIntentList = new ArrayList<>();
        defaultIntentList.add(DEFAULT_INTENT_ID);

        List<Integer> defaultEntityList = new ArrayList<>();
        defaultEntityList.add(DEFAULT_ENTITY_ID);

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
    private String selectResponse(List<Response> responses, List<Integer> intents, List<Integer> entities){

        //get list with one random response if the user wants a practice interview
        if (intents.contains(PRACTICE_INTENT_ID) &&
                (entities.contains(HR_INTERVIEW_ENTITY_ID) || entities.contains(TECHNICAL_INTERVIEW_ENTITY_ID))) {
            responses = handlePracticeInterviews(responses);
        }

        //if there are multiple responses, filter out everything but the responses with the most keyword matches on the list
        if (responses.size() > 1) {
            responses = getResponsesWithMostKeywordMatches(responses);
            if (responses.size() > 1){
                //TODO select the best fitting of the remaining responses
            }
        }


        return responses.get(0).getResponse();
    }

    /**
     *This method selects a random response from the responses that are practice interview questions
     *
     * @param responses -- all responses that match the intents and entities in the utterance
     * @return a random practice interview response
     */
    private List<Response> handlePracticeInterviews(List<Response> responses) {

        responses = responses.stream()
                .filter(response -> response.getResponseIntents().contains(PRACTICE_INTENT_ID))
                .collect(Collectors.toList());
        responses = getResponseListWithOnlyOneRandomResponse(responses);

        return responses;
    }

    /**
     *This method selects a random response from a list of responses
     *
     * @param responses -- all responses that match the intents and entities in the utterance
     * @return a list of responses that contains only the random response
     */
    private static List<Response> getResponseListWithOnlyOneRandomResponse(List<Response> responses) {
        Random rand = new Random();
        List<Response> randomResponseList = new ArrayList<>();
        Response randomResponse = responses.get(rand.nextInt(responses.size()));
        randomResponseList.add(randomResponse);
        return randomResponseList;
    }

    /**
     * @param responses -- all responses that match the intents and entities in the utterance
     * @return a list of responses that have the highest number of keyword matches
     */
    private List<Response> getResponsesWithMostKeywordMatches(List<Response> responses) {
        Map<Response, Integer> responseCounts = new LinkedHashMap<>();

        responses.stream().forEach(response -> {
            if (!responseCounts.containsKey(response)){
                int count = Collections.frequency(responses, response);
                responseCounts.put(response, count);
            }
        });

        List<Entry<Response, Integer>> countList = new ArrayList<>(responseCounts.entrySet());

        countList.sort(Entry.comparingByValue());
        Collections.reverse(countList);
        int maxCount = countList.get(0).getValue();
        List<Response> topResults = countList.stream()
                .filter(response -> response.getValue() == maxCount)
                .map(Entry::getKey)
                .collect(Collectors.toList());

        return topResults;
    }

    /**
     * @param entityIds -- The list of unranked entity ids
     * @return the list of entity ids ranked by priority
     */
    private List<Integer> sortEntitiesByPriority(List<Integer> entityIds) {
        List<Integer> rankedList = new ArrayList<>();
        for (int currentRankId : RANKED_ENTITY_IDS) {
            for (int currentEntityId : entityIds) {
                if (currentRankId == currentEntityId) {
                    rankedList.add(0, currentEntityId);
                }
            }
        }
        return rankedList;
    }

}
