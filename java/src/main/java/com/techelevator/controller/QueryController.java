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
        String cleanedUtterance = userInput.getUtterance().replaceAll("[^a-zA-Z ]", "");
        userInput.setUtterance(cleanedUtterance);
        Response response = queryService.getResponseFromUserInput(userInput);
        return response;

    }


}
