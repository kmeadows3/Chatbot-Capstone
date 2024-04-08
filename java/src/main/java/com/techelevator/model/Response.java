package com.techelevator.model;

import java.util.List;

public class Response {

    private String response;
    private List<Integer> intents;
    private List<Integer> entities;


    // Getters
    public String getResponse() {
        return response;
    }

    public List<Integer> getIntents() {
        return intents;
    }

    public List<Integer> getEntities() {
        return entities;
    }


    // Setters
    public void setResponse(String response) {
        this.response = response;
    }

    public void setIntents(List<Integer> intents) {
        this.intents = intents;
    }

    public void setEntities(List<Integer> entities) {
        this.entities = entities;
    }
}
