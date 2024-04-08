package com.techelevator.service;

import com.techelevator.dao.QueryDao;
import com.techelevator.model.UserInput;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Component
public class QueryService {
    private QueryDao queryDao;

    public QueryService (QueryDao queryDao){
        this.queryDao = queryDao;
    }


    /**
     *
     * @param userInput -- the user input provided by the client
     * @return the chatbot response
     */
    public String getResponse(UserInput userInput){
        List<String> tokens = tokenizeUtterance(userInput);
        List<String> responses = queryDao.getResponsesFromKeywords(tokens);
        return selectResponse(responses);
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
