package com.techelevator.controller;

import com.techelevator.model.Email;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmailController {

    public EmailController () {

    }

    @RequestMapping(path= "/email", method = RequestMethod.POST)
    public String getResponse(@RequestBody String recipientEmail) {
        recipientEmail = recipientEmail.replace("%40", "@");
        recipientEmail = recipientEmail.replace("=", "");
        Email email = new Email();
        String response = email.sendEmail(recipientEmail);
        return response;
    }


}