package com.techelevator.controller;

import com.techelevator.dao.JdbcQueryDao;
import com.techelevator.model.UserInput;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
public class QueryController {

    private JdbcQueryDao queryDao;
    public QueryController(JdbcQueryDao queryDao) {
        this.queryDao = queryDao;
    }

    @RequestMapping(path= "/", method = RequestMethod.POST)
    public String getResponse(@RequestBody UserInput userInput) {
        List<String> potentialKeywords = tokenizeUtterance(userInput);
        List<String> responses = queryDao.getResponsesWithKeywords(potentialKeywords);

        return responses.get(0);

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

}
