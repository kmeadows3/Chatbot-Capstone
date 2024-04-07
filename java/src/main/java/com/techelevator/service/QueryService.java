package com.techelevator.service;

import com.techelevator.dao.QueryDao;
import com.techelevator.model.UserInput;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class QueryService {
    private QueryDao queryDao;

    public QueryService (QueryDao queryDao){
        this.queryDao = queryDao;
    }

    public String getResponse(UserInput userInput){
        List<String> tokens = tokenizeUtterance(userInput);
        List<String> responses = queryDao.getResponsesWithKeywords(tokens);
        return selectResponse(responses);
    }




    /**
     *
     * @param userInput -- the Input from the client
     * @return List of all tokens from the utterance
     */
    private List<String> tokenizeUtterance(UserInput userInput) {
        String utterance = userInput.getUtterance();
        utterance = utterance.trim();
        String[] potentialKeywordsArray = utterance.split(" ");

        return Arrays.asList(potentialKeywordsArray);
    }


    //TODO this needs logic, currently only returns the first one
    private String selectResponse(List<String> responses){
        return responses.get(0);
    }
}
