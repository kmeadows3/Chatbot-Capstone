package com.techelevator.service;

import com.techelevator.ResponseSelector;
import com.techelevator.dao.BaseDaoTests;
import com.techelevator.dao.JdbcQueryDao;
import com.techelevator.dao.JdbcQuizDao;
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
        queryService = new QueryService(jdbcQueryDao, new JdbcQuizDao(jdbcTemplate), new CompanyInformationService(), new ResponseSelector());
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

        input.setUtterance("intent5keyword entity2keyword entity3keyword entity4keyword");
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

        input.setUtterance("gibberish intent2keyword");
        Response response = queryService.getResponseFromUserInput(input);

        Assert.assertEquals("Intent 2 Catch-All", response.getResponse());
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

        input.setUtterance("intent8keyword entity2keyword");
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

        input.setUtterance("intent2keyword entity3keyword");
        Response response = queryService.getResponseFromUserInput(input);
        Assert.assertEquals("Intent 2 Catch-All", response.getResponse());
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

        input.setUtterance("intent3keyword entity3keyword entity4keyword");
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

        input.setUtterance("intent3keyword intent6keyword entity2keyword entity3keyword");
        Response response = queryService.getResponseFromUserInput(input);

        //TODO method to make this happen, the default response is 9
        Assert.assertEquals("Test Response 8", response.getResponse());
    }

    @Test
    public void getResponseFromUserInput_returns_higher_ranked_choice_if_number_of_matches_is_equal(){
        UserInput input = new UserInput();
        List<Integer> intentList = new ArrayList<>();
        List<Integer> entityList = new ArrayList<>();
        intentList.add(1);
        entityList.add(1);
        input.setIntents(intentList);
        input.setEntities(entityList);

        input.setUtterance("intent3keyword entity3keyword");
        Response response = queryService.getResponseFromUserInput(input);

        Assert.assertEquals("Test Response 6", response.getResponse());
    }

    @Test
    public void getResponseFromUserInput_returns_company_information_when_in_company_mode(){
        UserInput input = new UserInput();
        input.setMode(2);
        input.setUtterance("microsoft.com");
        Response response = queryService.getResponseFromUserInput(input);
        Assert.assertEquals("<p>Microsoft's headquarters is 1 Microsoft Way, Redmond, Washington 98052, US.</p>" +
                "<p>They describe themselves as 'Every company has a mission. What's ours? To empower every person and" +
                " every organization to achieve more. We believe technology can and should be a force for good and " +
                "that meaningful innovation contributes to a brighter world in the future and today. Our culture " +
                "doesn’t just encourage curiosity; it embraces it. Each day we make progress together by showing up " +
                "as our authentic selves. We show up with a learn-it-all mentality. We show up cheering on others, " +
                "knowing their success doesn't diminish our own. We show up every day open to learning our own biases, " +
                "changing our behavior, and inviting in differences. Because impact matters. Microsoft operates in 190 " +
                "countries and is made up of more than 220,000 passionate employees worldwide.'</p><p>They have " +
                "10,001+ employees.</p><p>You can find out more at their website at https://news.microsoft.com/ " +
                "or their LinkedIn at https://www.linkedin.com/company/microsoft.</p>", response.getResponse());
    }
}
