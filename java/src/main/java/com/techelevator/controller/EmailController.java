package com.techelevator.controller;

import com.techelevator.model.Email;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmailController {

    public EmailController () {

    }

    @RequestMapping(path= "/email", method = RequestMethod.POST)
    public String getResponse(@RequestBody Email email) {
        String response = email.sendEmail();
        return response;
    }


}