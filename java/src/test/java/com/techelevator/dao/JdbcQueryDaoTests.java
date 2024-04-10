package com.techelevator.dao;

import com.techelevator.model.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class JdbcQueryDaoTests extends BaseDaoTests {
    private JdbcQueryDao jdbcQueryDao;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setJdbcQueryDao() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcQueryDao = new JdbcQueryDao(jdbcTemplate);
    }

    @Test
    public void getResponsesFromIntentsAndEntities_returns_correct_response_list_when_only_one_response_matches(){
        List<Integer> intents = new ArrayList<>();
        List<Integer> entities = new ArrayList<>();
        intents.add(3);
        entities.add(2);
        List<Response> returnedResponses = jdbcQueryDao.getResponsesFromIntentsAndEntities(intents,entities);
        Response expectedResponse = new Response("Test Response 5");
        Assert.assertEquals(1, returnedResponses.size());
        Assert.assertTrue(returnedResponses.contains(expectedResponse));
    }

    @Test
    public void getResponsesFromIntentsAndEntities_returns_correct_response_list_when_multiple_responses_match(){
        List<Integer> intents = new ArrayList<>();
        List<Integer> entities = new ArrayList<>();
        intents.add(2);
        entities.add(3);
        List<Response> returnedResponses = jdbcQueryDao.getResponsesFromIntentsAndEntities(intents,entities);
        Response expectedResponse1 = new Response("Test Response 6");
        Response expectedResponse2 = new Response("Test Response 4");
        Assert.assertEquals(2, returnedResponses.size());
        Assert.assertTrue(returnedResponses.contains(expectedResponse1));
        Assert.assertTrue(returnedResponses.contains(expectedResponse2));
    }



    @Test
    public void getIntentsAndEntitiesFromKeywords_returns_correct_array_only_one_intent_keyword() {
        List<String> potentialKeywords = new ArrayList<>();
        potentialKeywords.add("intent1keyword");
        List<Integer>[] resultsArray = jdbcQueryDao.getIntentsAndEntitiesFromKeywords(potentialKeywords);
        List<Integer> intents = resultsArray[0];
        List<Integer> entities = resultsArray[1];

        Assert.assertEquals(1, intents.size());
        Assert.assertEquals(0, entities.size());
        Assert.assertEquals(1, (int) intents.get(0));
    }

    @Test
    public void getIntentsAndEntitiesFromKeywords_returns_correct_array_when_intent_keyword_repeated() {
        List<String> potentialKeywords = new ArrayList<>();
        potentialKeywords.add("intent1keyword");
        potentialKeywords.add("intent1keyword");
        List<Integer>[] resultsArray = jdbcQueryDao.getIntentsAndEntitiesFromKeywords(potentialKeywords);
        List<Integer> intents = resultsArray[0];
        List<Integer> entities = resultsArray[1];

        Assert.assertEquals(1, intents.size());
        Assert.assertEquals(0, entities.size());
        Assert.assertEquals(1, (int) intents.get(0));
    }

    @Test
    public void getIntentsAndEntitiesFromKeywords_returns_correct_array_when_entity_keyword_repeated() {
        List<String> potentialKeywords = new ArrayList<>();
        potentialKeywords.add("entity1keyword");
        potentialKeywords.add("entity1keyword");
        List<Integer>[] resultsArray = jdbcQueryDao.getIntentsAndEntitiesFromKeywords(potentialKeywords);
        List<Integer> intents = resultsArray[0];
        List<Integer> entities = resultsArray[1];

        Assert.assertEquals(0, intents.size());
        Assert.assertEquals(1, entities.size());
        Assert.assertEquals(1, (int) entities.get(0));
    }
    @Test
    public void getIntentsAndEntitiesFromKeywords_returns_correct_array_multiple_intent_keywords() {
        List<String> potentialKeywords = new ArrayList<>();
        potentialKeywords.add("intent1keyword");
        potentialKeywords.add("intent2keyword");
        List<Integer>[] resultsArray = jdbcQueryDao.getIntentsAndEntitiesFromKeywords(potentialKeywords);
        List<Integer> intents = resultsArray[0];
        List<Integer> entities = resultsArray[1];

        Assert.assertEquals(2, intents.size());
        Assert.assertEquals(0, entities.size());
        Assert.assertTrue(intents.contains(1));
        Assert.assertTrue(intents.contains(2));
    }
    @Test
    public void getIntentsAndEntitiesFromKeywords_returns_correct_array_only_one_entity_keyword() {
        List<String> potentialKeywords = new ArrayList<>();
        potentialKeywords.add("entity1keyword");
        List<Integer>[] resultsArray = jdbcQueryDao.getIntentsAndEntitiesFromKeywords(potentialKeywords);
        List<Integer> intents = resultsArray[0];
        List<Integer> entities = resultsArray[1];

        Assert.assertEquals(0, intents.size());
        Assert.assertEquals(1, entities.size());
        Assert.assertEquals(1, (int) entities.get(0));
    }
    @Test
    public void getIntentsAndEntitiesFromKeywords_returns_correct_array_multiple_entity_keywords() {
        List<String> potentialKeywords = new ArrayList<>();
        potentialKeywords.add("entity1keyword");
        potentialKeywords.add("entity2keyword");
        List<Integer>[] resultsArray = jdbcQueryDao.getIntentsAndEntitiesFromKeywords(potentialKeywords);
        List<Integer> intents = resultsArray[0];
        List<Integer> entities = resultsArray[1];

        Assert.assertEquals(0, intents.size());
        Assert.assertEquals(2, entities.size());
        Assert.assertTrue(entities.contains(1));
        Assert.assertTrue(entities.contains(2));
    }

    @Test
    public void getIntentsAndEntitiesFromKeywords_returns_correct_array_when_both_entity_and_intent_keywords_exist() {
        List<String> potentialKeywords = new ArrayList<>();
        potentialKeywords.add("intent1keyword");
        potentialKeywords.add("entity1keyword");
        List<Integer>[] resultsArray = jdbcQueryDao.getIntentsAndEntitiesFromKeywords(potentialKeywords);
        List<Integer> intents = resultsArray[0];
        List<Integer> entities = resultsArray[1];

        Assert.assertEquals(1, intents.size());
        Assert.assertEquals(1, entities.size());
        Assert.assertEquals(1, (int) intents.get(0));
        Assert.assertEquals(1, (int) entities.get(0));
    }

    @Test
    public void getIntentsAndEntitiesFromKeywords_returns_empty_arrays_when_no_keywords_found() {
        List<String> potentialKeywords = new ArrayList<>();
        potentialKeywords.add("not a keyword");
        potentialKeywords.add("still not a keyword");
        List<Integer>[] resultsArray = jdbcQueryDao.getIntentsAndEntitiesFromKeywords(potentialKeywords);
        List<Integer> intents = resultsArray[0];
        List<Integer> entities = resultsArray[1];

        Assert.assertEquals(0, intents.size());
        Assert.assertEquals(0, entities.size());
    }

    @Test
    public void getAllMultiWordKeywords_returns_expected_list(){
        List<String> returnedKeywords = jdbcQueryDao.getAllMultiWordKeywords();
        Assert.assertEquals(3, returnedKeywords.size());
        Assert.assertEquals("The Longest Multiple Word Keyword", returnedKeywords.get(0));
        Assert.assertEquals("Multiple Word Keyword", returnedKeywords.get(1));
        Assert.assertEquals("Two-word keyword", returnedKeywords.get(2));
    }




}

