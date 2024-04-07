package com.techelevator.controller;

import com.techelevator.dao.JdbcQueryDao;
import com.techelevator.model.UserInput;
import com.techelevator.service.QueryService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
public class QueryController {

    private QueryService queryService;

    private JdbcQueryDao queryDao;
    public QueryController (QueryService queryService) {
        this.queryService = queryService;
    }

    @RequestMapping(path= "/", method = RequestMethod.POST)
    public String getResponse(@RequestBody UserInput userInput) {
        String response = queryService.getResponse(userInput);
        return response;

    }


}
