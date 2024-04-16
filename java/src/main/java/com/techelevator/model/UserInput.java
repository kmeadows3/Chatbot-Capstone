package com.techelevator.model;


import java.util.List;

public class UserInput {
    private String utterance;
    private List<Integer> intents;
    private List<Integer> entities;
    private int mode;
    private List<Question> quiz;

    // Getters
    public String getUtterance() {
        return utterance;
    }

    public List<Integer> getIntents() {
        return intents;
    }

    public List<Integer> getEntities() {
        return entities;
    }

    public int getMode() {
        return mode;
    }

    public List<Question> getQuiz() {
        return quiz;
    }

    // Setters
    public void setUtterance(String utterance) {
        this.utterance = utterance;
    }

    public void setIntents(List<Integer> intents) {
        this.intents = intents;
    }

    public void setEntities(List<Integer> entities) {
        this.entities = entities;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
    public void setQuiz(List<Question> quiz) {
        this.quiz = quiz;
    }
}
