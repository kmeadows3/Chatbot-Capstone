package com.techelevator.service;

import com.techelevator.dao.BaseDaoTests;
import com.techelevator.dao.JdbcQueryDao;
import com.techelevator.model.Response;
import com.techelevator.model.UserInput;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class QueryServiceTests extends BaseDaoTests {
    private QueryService queryService;
    private JdbcQueryDao jdbcQueryDao;
    private JdbcTemplate jdbcTemplate;
    private UserInput input;

    @Before
    public void setup(){
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcQueryDao = new JdbcQueryDao(jdbcTemplate);
        queryService = new QueryService(jdbcQueryDao);
        UserInput input = new UserInput();
        List<Integer> intentList = new ArrayList<>();
        List<Integer> entityList = new ArrayList<>();
        intentList.add(1);
        entityList.add(1);
        input.setIntents(intentList);
        input.setEntities(entityList);
    }

    @Test
    public void getResponseFromUserInput_returns_expected_response_when_only_one_response_matches(){
        UserInput input = new UserInput();
        List<Integer> intentList = new ArrayList<>();
        List<Integer> entityList = new ArrayList<>();
        intentList.add(1);
        entityList.add(1);
        input.setIntents(intentList);
        input.setEntities(entityList);



        input.setUtterance("intent3keyword entity2keyword");
        Response response = queryService.getResponseFromUserInput(input);
        Assert.assertEquals("Test Response 5", response.getResponse());
    }

    @Test
    public void getResponseFromUserInput_returns_expected_response_when_one_response_has_more_matches(){
        UserInput input = new UserInput();
        List<Integer> intentList = new ArrayList<>();
        List<Integer> entityList = new ArrayList<>();
        intentList.add(1);
        entityList.add(1);
        input.setIntents(intentList);
        input.setEntities(entityList);

        input.setUtterance("intent2keyword entity2keyword entity3keyword");
        Response response = queryService.getResponseFromUserInput(input);

        Assert.assertEquals("Test Response 4", response.getResponse());
    }

    @Test
    public void getResponseFromUserInput_returns_catch_all_response_when_no_keywords_exist(){
        UserInput input = new UserInput();
        List<Integer> intentList = new ArrayList<>();
        List<Integer> entityList = new ArrayList<>();
        intentList.add(3);
        entityList.add(4);
        input.setIntents(intentList);
        input.setEntities(entityList);

        input.setUtterance("These are not keywords");
        Response response = queryService.getResponseFromUserInput(input);

        Assert.assertEquals("Test Response 1", response.getResponse());
    }

    @Test
    public void getResponseFromUserInput_pulls_intent_from_history_if_entity_exists(){
        UserInput input = new UserInput();
        List<Integer> intentList = new ArrayList<>();
        List<Integer> entityList = new ArrayList<>();
        intentList.add(3);
        entityList.add(1);
        input.setIntents(intentList);
        input.setEntities(entityList);

        input.setUtterance("These are not keywords entity2keyword");
        Response response = queryService.getResponseFromUserInput(input);

        Assert.assertEquals("Test Response 5", response.getResponse());
    }

    @Test
    public void getResponseFromUserInput_pulls_entity_from_history_if_intent_exists(){
        UserInput input = new UserInput();
        List<Integer> intentList = new ArrayList<>();
        List<Integer> entityList = new ArrayList<>();
        intentList.add(1);
        entityList.add(2);
        input.setIntents(intentList);
        input.setEntities(entityList);

        input.setUtterance("These are not keywords intent3keyword");
        Response response = queryService.getResponseFromUserInput(input);
        Assert.assertEquals("Test Response 5", response.getResponse());
    }

    @Test
    public void getResponseFromUserInput_returns_intent_catch_all_response_when_only_intent_keyword(){
        UserInput input = new UserInput();
        List<Integer> intentList = new ArrayList<>();
        List<Integer> entityList = new ArrayList<>();
        intentList.add(1);
        entityList.add(1);
        input.setIntents(intentList);
        input.setEntities(entityList);

        input.setUtterance("gibberish intent7keyword");
        Response response = queryService.getResponseFromUserInput(input);

        Assert.assertEquals("Intent 7 Catch-All", response.getResponse());
    }

    @Test
    public void getResponseFromUserInput_returns_entity_catch_all_if_no_response_matches_intent_and_entity(){
        UserInput input = new UserInput();
        List<Integer> intentList = new ArrayList<>();
        List<Integer> entityList = new ArrayList<>();
        intentList.add(1);
        entityList.add(1);
        input.setIntents(intentList);
        input.setEntities(entityList);

        input.setUtterance("intent7keyword entity2keyword");
        Response response = queryService.getResponseFromUserInput(input);
        Assert.assertEquals("Test Response 2", response.getResponse());
    }

    @Test
    public void getResponseFromUserInput_returns_intent_catch_all_if_no_response_for_entity_catch_all(){
        UserInput input = new UserInput();
        List<Integer> intentList = new ArrayList<>();
        List<Integer> entityList = new ArrayList<>();
        intentList.add(1);
        entityList.add(1);
        input.setIntents(intentList);
        input.setEntities(entityList);

        input.setUtterance("intent7keyword entity3keyword");
        Response response = queryService.getResponseFromUserInput(input);
        Assert.assertEquals("Intent 7 Catch-All", response.getResponse());
    }

    @Test
    public void getResponseFromUserInput_returns_overall_catch_all_if_no_intent_or_entity_catch_all(){
        UserInput input = new UserInput();
        List<Integer> intentList = new ArrayList<>();
        List<Integer> entityList = new ArrayList<>();
        intentList.add(3);
        entityList.add(4);
        input.setIntents(intentList);
        input.setEntities(entityList);

        input.setUtterance("intent3keyword entity4keyword");
        Response response = queryService.getResponseFromUserInput(input);
        Assert.assertEquals("Test Response 1", response.getResponse());
    }

    @Test
    public void getResponseFromUserInput_returns_random_interview_hr_question_with_practice_intent_and_hr_entity(){
        UserInput input = new UserInput();
        input.setUtterance("intent4keyword entity7keyword");

        List<String> expectedResponseList = new ArrayList<>();
        expectedResponseList.add("Test Response 10");
        expectedResponseList.add("Test Response 11");
        expectedResponseList.add("Test Response 12");
        expectedResponseList.add("Test Response 13");
        expectedResponseList.add("Test Response 14");

        Response response = queryService.getResponseFromUserInput(input);
        Assert.assertTrue(expectedResponseList.contains(response.getResponse()));
    }

    @Test
    public void getResponseFromUserInput_returns_random_interview_technical_question_with_practice_intent_and_technial_entity(){
        UserInput input = new UserInput();
        input.setUtterance("intent4keyword entity8keyword");

        List<String> expectedResponseList = new ArrayList<>();
        expectedResponseList.add("Test Response 15");
        expectedResponseList.add("Test Response 16");
        expectedResponseList.add("Test Response 17");
        expectedResponseList.add("Test Response 18");
        expectedResponseList.add("Test Response 19");

        Response response = queryService.getResponseFromUserInput(input);
        Assert.assertTrue(expectedResponseList.contains(response.getResponse()));
    }

    @Test
    public void getResponseFromUserInput_returns_higher_ranked_choice_if_number_of_matches_is_equal_one_intent_and_entity(){
        UserInput input = new UserInput();
        List<Integer> intentList = new ArrayList<>();
        List<Integer> entityList = new ArrayList<>();
        intentList.add(1);
        entityList.add(1);
        input.setIntents(intentList);
        input.setEntities(entityList);

        input.setUtterance("intent2keyword entity3keyword entity4keyword");
        Response response = queryService.getResponseFromUserInput(input);
        Assert.assertEquals("Test Response 6", response.getResponse());
    }

    @Test
    public void getResponseFromUserInput_returns_higher_ranked_choice_if_number_of_matches_is_equal_one_intent_and_two_entities(){
        UserInput input = new UserInput();
        List<Integer> intentList = new ArrayList<>();
        List<Integer> entityList = new ArrayList<>();
        intentList.add(1);
        entityList.add(1);
        input.setIntents(intentList);
        input.setEntities(entityList);

        input.setUtterance("intent5keyword intent6keyword entity2keyword entity3keyword");
        Response response = queryService.getResponseFromUserInput(input);

        //TODO method to make this happen, the default response is 9
        Assert.assertEquals("Test Response 8", response.getResponse());
    }

}
