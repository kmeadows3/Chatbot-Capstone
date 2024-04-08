package com.techelevator.controller;

import com.techelevator.model.UserInput;
import com.techelevator.service.QueryService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class QueryController {

    private QueryService queryService;

    public QueryController (QueryService queryService) {
        this.queryService = queryService;
    }

    @RequestMapping(path= "/", method = RequestMethod.POST)
    public String getResponse(@RequestBody UserInput userInput) {
        String response = queryService.getResponse(userInput);
        return response;

    }


}
