package com.techelevator.model;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

import javax.mail.internet.MimeMessage;

public class Email {

    /*******************************************************************************************************************
     * Sender Email Credentials
     */
    private final String SENDER_EMAIL = "loriidaec102@gmail.com";
    private final String SENDER_APP_PASSWORD = "dyrywbxtqgbgigto";


    /*******************************************************************************************************************
     * SMTP server configuration
     */
    private final String host = "smtp.gmail.com";
    private final int gmailSmtpPort = 587;


    /*******************************************************************************************************************
     * Instance Variable
     */
    private String recipientEmail;
    private String subject;
    private String body;



    /*******************************************************************************************************************
     * Constructor
     */
    public Email () {

    }


    public String sendEmail() {
        String responseMessage = "There was an error sending your email.";

        // Create properties object
        Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", gmailSmtpPort);

        // Create Session object
        Session emailSession = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_APP_PASSWORD);
            }
        });

        try {
            // Create MimeMessage object
            MimeMessage message = new MimeMessage(emailSession);

            // Set From: header field
            message.setFrom(new InternetAddress(SENDER_EMAIL));

            // Set To: header field
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.recipientEmail));

            // Set Subject: header field
            message.setSubject(this.subject);

            // Set Content: text/html
            message.setContent(this.body, "text/html");

            // Send the message
            Transport.send(message);

            responseMessage = "I successfully sent your email!";

        } catch (MessagingException e) {
            System.err.println("Error Sending Email: " + e);
        }

        return responseMessage;
    }


    /*******************************************************************************************************************
     * Getters
     */
    public String getRecipientEmail() {
        return recipientEmail;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }


    /*******************************************************************************************************************
     * Getters
     */
    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
