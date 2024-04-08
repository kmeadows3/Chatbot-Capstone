package com.techelevator.model;

public class Response {

    private String response;
    private int intent;
    private int entity;


    // Getters
    public String getResponse() {
        return response;
    }

    public int getIntent() {
        return intent;
    }

    public int getEntity() {
        return entity;
    }


    // Setters
    public void setResponse(String response) {
        this.response = response;
    }

    public void setIntent(int intent) {
        this.intent = intent;
    }

    public void setEntity(int entity) {
        this.entity = entity;
    }
}
