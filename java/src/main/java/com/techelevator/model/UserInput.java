package com.techelevator.model;


public class UserInput {
    private String utterance;
    private int previousResponseId;
    private int intent;
    private int entity;


    // Getters
    public String getUtterance() {
        return utterance;
    }

    public int getPreviousResponseId() {
        return previousResponseId;
    }

    public int getIntent() {
        return intent;
    }

    public int getEntity() {
        return entity;
    }



    // Setters
    public void setUtterance(String utterance) {
        this.utterance = utterance;
    }

    public void setPreviousReponseId(int previousReponse) {
        this.previousResponseId = previousReponse;
    }

    public void setIntent(int intent) {
        this.intent = intent;
    }

    public void setEntity(int entity) {
        this.entity = entity;
    }


}
