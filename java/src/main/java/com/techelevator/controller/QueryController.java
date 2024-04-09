package com.techelevator.controller;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Response;
import com.techelevator.model.UserInput;
import com.techelevator.service.QueryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
public class QueryController {

    private QueryService queryService;

    public QueryController (QueryService queryService) {
        this.queryService = queryService;
    }

    @RequestMapping(path= "/", method = RequestMethod.POST)
    public Response getResponse(@RequestBody UserInput userInput) {
        Response response = null;

        try {
            response = queryService.getResponseFromUserInput(userInput);
        } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return response;

    }


}
