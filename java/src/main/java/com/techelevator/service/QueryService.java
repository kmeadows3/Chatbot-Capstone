package com.techelevator.service;

import com.techelevator.dao.QueryDao;
import com.techelevator.model.UserInput;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class QueryService {
    private QueryDao queryDao;

    public QueryService (QueryDao queryDao){
        this.queryDao = queryDao;
    }

    public String getResponse(UserInput userInput){
        List<String> tokens = tokenizeUtterance(userInput);
        List<String> responses = queryDao.getResponsesFromKeywords(tokens);
        return selectResponse(responses);
    }




    /**
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
                utterance.replace(keyword, "");
            }
        }

        String[] potentialKeywordsArray = utterance.split(" ");
        for (String word : potentialKeywordsArray){
            tokens.add(word);
        }

        return tokens;
    }


    /**
     *
     * @param responses -- all responses that match the intents and entities in the utterance
     * @return the single response that best fits the utterance
     */
    private String selectResponse(List<String> responses){
        Map<String, Integer> responseCount = new HashMap<>();

        for (int i = 0; i < responses.size(); i++){
            int count = Collections.frequency(responses, responses.get(i));
            responseCount.put(responses.get(i), count);
        }

        List<Map.Entry<String, Integer>> countList = new ArrayList<>(responseCount.entrySet());
        countList.sort(Map.Entry.comparingByValue());


        //TODO this needs more logic, currently only returns the first of the responses with the most keyword matches
        return countList.get(0).getKey();
    }
}
