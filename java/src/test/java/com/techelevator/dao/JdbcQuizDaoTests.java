package com.techelevator.dao;

import com.techelevator.model.Question;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcQuizDaoTests extends BaseDaoTests{

    private JdbcQuizDao sut;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setup(){
        jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcQuizDao(jdbcTemplate);
    }


    @Test
    public void getRandomQuiz_returns_list_of_questions(){
        List<Question> testList = sut.getRandomQuiz();
        Assert.assertEquals(3, testList.size());
        Assert.assertEquals(4, testList.get(0).getAnswers().size());

    }


}
