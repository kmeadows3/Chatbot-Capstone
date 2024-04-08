package com.techelevator.controller;

import com.techelevator.model.Response;
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
    public Response getResponse(@RequestBody UserInput userInput) {
        Response response = queryService.getResponse(userInput);
        return response;

    }


}
