package com.techelevator.controller;

import com.techelevator.dao.JdbcQueryDao;
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
    public String getResponse(@RequestBody String utterance) {
        List<String> potentialKeywords = tokenizeUtterance(utterance);
        List<String> responses = queryDao.getResponsesWithKeywords(potentialKeywords);

        return responses.get(0);

    }

    /**
     *
     * @param utterance -- the User's utterance
     * @return List of all tokens from the utterance
     */
    private List<String> tokenizeUtterance(String utterance) {
        utterance = utterance.trim();
        String[] potentialKeywordsArray = utterance.split(" ");

        return Arrays.asList(potentialKeywordsArray);
    }

}
