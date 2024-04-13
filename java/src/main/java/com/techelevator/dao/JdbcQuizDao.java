package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Answer;
import com.techelevator.model.Question;
import com.techelevator.model.Quiz;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcQuizDao implements QuizDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcQuizDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Question> getRandomQuiz() {
        String sql = "SELECT question_id, question, q.difficulty_id, difficulty FROM question q " +
                "JOIN difficulty d ON d.difficulty_id = q.difficulty_id " +
                "ORDER BY RANDOM() LIMIT 3";
        List<Question> questions = new ArrayList<>();
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()){
                Question question = new Question();
                question.setId(results.getInt("question_id"));
                question.setQuestion(results.getString("question"));
                question.setDifficulty(results.getInt("difficulty_id"));
                question.setDifficultyString(results.getString("difficulty"));
                question.setAnswers(getAnswers(question.getId()));
                questions.add(question);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }

        return questions;
    }

    private List<Answer> getAnswers(int questionId){
        String sql = "SELECT answer_id, answer, is_correct FROM answer WHERE question_id = ? ORDER BY RANDOM()";
        List<Answer> answers = new ArrayList<>();

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, questionId);
        while (results.next()){
            Answer answer = new Answer();
            answer.setAnswer(results.getString("answer"));
            answer.setId(results.getInt("answer_id"));
            answer.setCorrect(results.getBoolean("is_correct"));
            answers.add(answer);
        }

        return answers;
    }

}
