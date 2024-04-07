package com.techelevator.model;


public class UserInput {
    private String utterance;
    private String topic;


    // Getters
    public String getUtterance() {
        return utterance;
    }

    public String getTopic() {
        return topic;
    }


    // Setters
    public void setUtterance(String utterance) {
        this.utterance = utterance;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
