package com.techelevator.model;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class Email {

    /*******************************************************************************************************************
     * Sender Email Credentials
     */
    private final String SENDER_EMAIL = "your_email@gmail.com";
    private final String SENDER_PASSWORD = "your_password"; // Change this to your actual password


    /*******************************************************************************************************************
     * SMTP server configuration
     */
    private String host = "smtp.gmail.com";
    private int gmailSmtpPort = 587;


    /*******************************************************************************************************************
     * CInstance Variable
     */
    private Session emailSession;


    /*******************************************************************************************************************
     * Constructor
     */
    public Email () {
        // Create properties object
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", gmailSmtpPort);

        // Create Session object
        emailSession = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
            }
        });

    }


    public void sendEmail(String recipientEmail) {

        // Create MimeMessage object
        Message message = new MimeMessage(emailSession);

        try {
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

        } catch (MessagingException e) {
            System.err.println("Error Sending Email: " + e);
        }
    }

}
