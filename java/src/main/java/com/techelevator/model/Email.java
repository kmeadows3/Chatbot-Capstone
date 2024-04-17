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
    private String host = "smtp.gmail.com";
    private int gmailSmtpPort = 587;


    /*******************************************************************************************************************
     * CInstance Variable
     */



    /*******************************************************************************************************************
     * Constructor
     */
    public Email () {

    }


    public String sendEmail(String recipientEmail) {
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
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

            // Set Subject: header field
            message.setSubject("Test Email from Java");

            // Set Content: text/plain
            message.setText("This is the message from the test email sent from Java program.");

            // Send the message
            Transport.send(message);

            responseMessage = "I successfully sent your email!";

        } catch (MessagingException e) {
            System.err.println("Error Sending Email: " + e);
        }

        return responseMessage;
    }

}
