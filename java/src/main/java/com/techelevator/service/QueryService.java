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
    public static final int DEFAULT_INTENT_ID = 1;
    public static final int DEFAULT_ENTITY_ID = 1;
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

        List<String> potentialResponses = getPotentialResponseList(intentsAndEntities[0], intentsAndEntities[1]);

        outputResponse.setIntents(intentsAndEntities[0]);
        outputResponse.setEntities(intentsAndEntities[1]);
        outputResponse.setResponse(selectResponse(potentialResponses));
        return outputResponse;
    }


    /**
     * This method takes the utterance provided by the client and breaks it into tokens
     *
     * @param userInput -- the Input from the client
     * @return List of all tokens from the utterance
     */
    private List<String> tokenizeUtterance(UserInput userInput) {
        List<String> tokens = new ArrayList<>();
        String utterance = userInput.getUtterance();
        utterance = utterance.trim();

        List<String> mutlipleWordKeywords  = queryDao.getAllMultiWordKeywords();
        for (String keyword : mutlipleWordKeywords){
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
        intentsAndEntities = setIntentsAndEntitiesWhenNoneReturned(intentsAndEntities[0], intentsAndEntities[1], userInput);

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
        if (intentIds.size() == 0) {
            if (userInput.getIntents() == null ||userInput.getIntents().size() != 0) {
                intentIds.addAll(userInput.getIntents());
            } else {
                intentIds.add(DEFAULT_INTENT_ID);  //adds default intent
            }
        }

        if (entityIds.size() == 0) {
            if (userInput.getEntities() == null ||userInput.getEntities().size() != 0){
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
        defaultEntityList.add(QueryService.DEFAULT_ENTITY_ID);

        if (entityIds.get(0) != 1){
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
}
