package com.techelevator.service;

import com.techelevator.ResponseSelector;
import com.techelevator.dao.QueryDao;
import com.techelevator.exception.CompanyInformationExpection;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Company;
import com.techelevator.model.Response;
import com.techelevator.model.UserInput;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class QueryService {

    // Constants
    private final int[] RANKED_ENTITY_IDS = new int[]{
            11, // Negative
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
    public final int JOB_SEARCH_MODE = 1;
    public final int COMPANY_DATA_MODE = 2;
    public final int COMPANY_INFORMATION_INTENT_ID = 7;
    public final int JOB_SEARCH_INTENT_ID = 8;
    public final int DEFAULT_INTENT_ID = 1;
    public final int DEFAULT_ENTITY_ID = 1;
    public final int INTENTS_INDEX = 0;
    public final int ENTITIES_INDEX = 1;

    // Instance Variables
    private QueryDao queryDao;
    private CompanyInformationService companyInformationService;
    private ResponseSelector responseSelector;

    //Constructor
    public QueryService (QueryDao queryDao, CompanyInformationService companyInformationService, ResponseSelector responseSelector){
        this.queryDao = queryDao;
        this.companyInformationService = companyInformationService;
        this.responseSelector = responseSelector;
    }


    /**
     *
     * @param userInput -- the user input provided by the client
     * @return the chatbot response
     */
    public Response getResponseFromUserInput(UserInput userInput){
        Response outputResponse = null;

        if (userInput.getMode() == COMPANY_DATA_MODE){
            outputResponse = companyDataResponse(userInput.getUtterance());
        } else {
            outputResponse = generalChatbotResponse(userInput);
        }

        return outputResponse;
    }


    /**
     * This method creates a response when the chatbot is in the default chatbot mode
     * @param userInput -- the user input provided by the client
     * @return the chatbot response
     */
    private Response generalChatbotResponse(UserInput userInput) {

        List<String> tokens = tokenizeUtterance(userInput);
        List<Integer>[] intentsAndEntities = getIntentsAndEntities(tokens, userInput);
        List<Integer> intents = intentsAndEntities[INTENTS_INDEX];
        List<Integer> entities = intentsAndEntities[ENTITIES_INDEX];

        String responseString = null;
        Response outputResponse = new Response();
        if (intents.contains(COMPANY_INFORMATION_INTENT_ID)) {
            responseString = "Please enter the domain name of the company you're interested in learning more about.";
            outputResponse.setMode(COMPANY_DATA_MODE);
        } else if (intents.contains(JOB_SEARCH_INTENT_ID)){
            responseString = "I'd be happy to help you find a job. First I'll needs some information about what you're looking for.";
            outputResponse.setMode(JOB_SEARCH_MODE);
        }else {
            List<Response> potentialResponses = getPotentialResponseList(intents, entities);
            responseString = responseSelector.selectResponse(potentialResponses,intents, entities);
        }

        outputResponse.setUserIntents(intents);
        outputResponse.setUserEntities(entities);
        outputResponse.setResponse(responseString);

        return outputResponse;
    }

    /**
     * This method creates a response when the chatbot is in company information mode
     * @param utterance the user's utterance
     * @return the response string that will be returned in the response
     */
    private Response companyDataResponse(String utterance) {
        String responseString;
        List<Integer> intents = new ArrayList<>();
        List<Integer> entities = new ArrayList<>();
        intents.add(DEFAULT_INTENT_ID);
        entities.add(DEFAULT_ENTITY_ID);
        Response outputResponse = new Response();

        try {
            Company company = companyInformationService.getCompanyDataDemoMode(utterance);
            responseString = company.toString();
        } catch (CompanyInformationExpection e){
            responseString = e.getMessage();
        }

        outputResponse.setUserIntents(intents);
        outputResponse.setUserEntities(entities);
        outputResponse.setResponse(responseString);

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
                List<Integer> rankedEntities = sortEntitiesByPriority(userInput.getEntities());
                entityIds.addAll(rankedEntities);
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


}
