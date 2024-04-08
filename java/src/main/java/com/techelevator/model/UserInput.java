package com.techelevator.model;


public class UserInput {
    private String utterance;
    private int previousResponseId;


    // Getters
    public String getUtterance() {
        return utterance;
    }

    public int getPreviousResponseId() {
        return previousResponseId;
    }


    // Setters
    public void setUtterance(String utterance) {
        this.utterance = utterance;
    }

    public void setPreviousReponseId(int previousReponse) {
        this.previousResponseId = previousReponse;
    }
}
