package com.techelevator.dao;

import com.techelevator.model.QuizResponse;

import java.util.List;

public interface QuizDao {

    QuizResponse getRandomQuizQuestion(List<Integer> askedQuestions);

}
