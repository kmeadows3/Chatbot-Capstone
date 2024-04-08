package com.techelevator.model;


import java.util.List;

public class UserInput {
    private String utterance;
    private int previousResponseId;
    private List<Integer> intents;
    private List<Integer> entities;


    // Getters
    public String getUtterance() {
        return utterance;
    }

    public int getPreviousResponseId() {
        return previousResponseId;
    }

    public List<Integer> getIntents() {
        return intents;
    }

    public List<Integer> getEntities() {
        return entities;
    }



    // Setters
    public void setUtterance(String utterance) {
        this.utterance = utterance;
    }

    public void setPreviousReponseId(int previousReponse) {
        this.previousResponseId = previousReponse;
    }

    public void setIntents(List<Integer> intents) {
        this.intents = intents;
    }

    public void setEntities(List<Integer> entities) {
        this.entities = entities;
    }


}
