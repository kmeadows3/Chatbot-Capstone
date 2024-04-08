package com.techelevator.service;

import com.techelevator.dao.BaseDaoTests;
import com.techelevator.dao.JdbcQueryDao;
import com.techelevator.model.UserInput;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class QueryServiceTests extends BaseDaoTests {
    private QueryService queryService;
    private JdbcQueryDao jdbcQueryDao;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setup(){
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcQueryDao = new JdbcQueryDao(jdbcTemplate);
        queryService = new QueryService(jdbcQueryDao);
    }

    @Test
    public void getResponse_returns_expected_response_when_only_one_response_matches(){
        UserInput input = new UserInput();
        input.setTopic("topic");
        input.setUtterance("intent3keyword entity2keyword");
        String response = queryService.getResponse(input);
        Assert.assertEquals("Test Response 5", response);
    }

    @Test
    public void getResponse_returns_expected_response_when_multiple_responses_match(){
        UserInput input = new UserInput();
        input.setTopic("topic");
        input.setUtterance("intent2keyword entity2keyword entity3keyword");
        String response = queryService.getResponse(input);

        Assert.assertEquals("Test Response 4", response);
    }


}
