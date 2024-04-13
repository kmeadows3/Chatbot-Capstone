package com.techelevator.dao;

import com.techelevator.model.Question;

import java.util.List;

public interface QuizDao {

    List<Question> getRandomQuiz();

}
