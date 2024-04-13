package com.techelevator.model;

import java.util.List;
public class Question {
    private int id;
    private String question;
    private List<Answer> answers;
    private int difficulty;
    private String difficultyString;
    private boolean isCorrectlyAnswered = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficultyString() {
        return difficultyString;
    }

    public void setDifficultyString(String difficultyString) {
        this.difficultyString = difficultyString;
    }

    public boolean isCorrectlyAnswered() {
        return isCorrectlyAnswered;
    }

    public void setCorrectlyAnswered(boolean correctlyAnswered) {
        this.isCorrectlyAnswered = correctlyAnswered;
    }
}
