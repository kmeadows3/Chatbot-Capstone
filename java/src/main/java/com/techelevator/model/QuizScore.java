package com.techelevator.model;

import java.util.List;

public class QuizScore {
    public static final int EASY_VALUE = 1;
    public static final int MEDIUM_VALUE = 2;
    public static final int HARD_VALUE = 3;
    List<Question> quiz;

    public QuizScore(){}

    public QuizScore(List<Question> quiz) {
        this.quiz = quiz;
    }

    //DERIVED
    public int getQuizScore(){
        int score = 0;
        for (Question question : quiz){
            if (question.isCorrectlyAnswered()){
                score += question.getDifficulty();
            }
        }
        return score;
    }

    public int getEasyCorrectCount(){
        int count = 0;
        for(Question question : quiz){
            if(question.getDifficulty() == EASY_VALUE && question.isCorrectlyAnswered()){
                count++;
            }
        }
        return count;
    }

    public int getMediumCorrectCount(){
        int count = 0;
        for(Question question : quiz){
            if(question.getDifficulty() == MEDIUM_VALUE && question.isCorrectlyAnswered()){
                count++;
            }
        }
        return count;
    }

    public int getHardCorrectCount(){
        int count = 0;
        for(Question question : quiz){
            if(question.getDifficulty() == HARD_VALUE && question.isCorrectlyAnswered()){
                count++;
            }
        }
        return count;
    }

    public int getEasyQuestionCount(){
        int count = 0;
        for(Question question : quiz){
            if(question.getDifficulty() == EASY_VALUE){
                count++;
            }
        }
        return count;
    }
    public int getMediumQuestionCount(){
        int count = 0;
        for(Question question : quiz){
            if(question.getDifficulty() == MEDIUM_VALUE){
                count++;
            }
        }
        return count;
    }
    public int getHardQuestionCount(){
        int count = 0;
        for(Question question : quiz){
            if(question.getDifficulty() == HARD_VALUE){
                count++;
            }
        }
        return count;
    }

    public int getTotalPossibleScore(){
        return getHardQuestionCount() * HARD_VALUE + getMediumQuestionCount() * MEDIUM_VALUE +
                getEasyQuestionCount() * EASY_VALUE;
    }


    //Getters and setters

    public List<Question> getQuiz() {
        return quiz;
    }

    public void setQuiz(List<Question> quiz) {
        this.quiz = quiz;
    }
}
