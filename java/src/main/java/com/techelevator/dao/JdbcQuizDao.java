package com.techelevator.dao;

import com.techelevator.model.QuizResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcQuizDao implements QuizDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcQuizDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public QuizResponse getRandomQuizQuestion(List<Integer> askedQuestionIds) {
        String sql = buildSqlStringForRandomSearch(askedQuestionIds);
        //TODO WORKING HERE
        return null;
    }

    private String buildSqlStringForRandomSearch(List<Integer> askedQuestionIds) {
        String sqlStart = "SELECT question_id, question, q.difficulty_id, difficulty FROM question q " +
                "JOIN difficulty d ON d.difficulty_id = q.difficulty_id ";
        String sqlEnd = "ORDER BY RANDOM() LIMIT 1";
        String removeFormerQuestions = "WHERE question_id NOT IN (";

        StringBuilder sqlBuilder = new StringBuilder(sqlStart);
        if (askedQuestionIds.size() == 0){
            sqlBuilder.append(sqlEnd);
        } else {
            sqlBuilder.append(removeFormerQuestions);
            for (int i = 0; i < askedQuestionIds.size(); i++){
                if(i == 0){
                    sqlBuilder.append("?");
                } else {
                    sqlBuilder.append(", ?");
                }
            }
            sqlBuilder.append(") ");
        }

        return sqlBuilder.toString();
    }
}
