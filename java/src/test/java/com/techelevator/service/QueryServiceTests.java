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
        intentList.add(2);
        entityList.add(1);
        input.setIntents(intentList);
        input.setEntities(entityList);

        input.setUtterance("These are not keywords entity3keyword");
        Response response = queryService.getResponseFromUserInput(input);

        Assert.assertEquals("Test Response 3", response.getResponse());
    }

    @Test
    public void getResponseFromUserInput_pulls_entity_from_history_if_intent_exists(){
        UserInput input = new UserInput();
        List<Integer> intentList = new ArrayList<>();
        List<Integer> entityList = new ArrayList<>();
        intentList.add(1);
        entityList.add(3);
        input.setIntents(intentList);
        input.setEntities(entityList);

        input.setUtterance("These are not keywords intent2keyword");
        Response response = queryService.getResponseFromUserInput(input);

        Assert.assertEquals("Test Response 3", response.getResponse());
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

        input.setUtterance("gibberish intent4keyword");
        Response response = queryService.getResponseFromUserInput(input);

        Assert.assertEquals("Intent 4 Catch-All", response.getResponse());
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

        input.setUtterance("intent4keyword entity2keyword");
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

        input.setUtterance("intent4keyword entity3keyword");
        Response response = queryService.getResponseFromUserInput(input);
        Assert.assertEquals("Intent 4 Catch-All", response.getResponse());
    }

    @Test
    public void getResponseFromUserInput_returns_overall_catch_all_if_no_intent_or_entity_catch_all(){
        UserInput input = new UserInput();
        List<Integer> intentList = new ArrayList<>();
        List<Integer> entityList = new ArrayList<>();
        intentList.add(1);
        entityList.add(1);
        input.setIntents(intentList);
        input.setEntities(entityList);

        input.setUtterance("intent3keyword entity4keyword");
        Response response = queryService.getResponseFromUserInput(input);
        Assert.assertEquals("Test Response 1", response.getResponse());
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
        //TODO this response might not be the highest ranked, method wasn't created when I wrote the test, other result is Test Response 3
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
        //TODO this response might not be the highest ranked, method wasn't created when I wrote the test, other result is Test Response 3
        Assert.assertEquals("Test Response 8", response.getResponse());
    }

}
