package com.techelevator.dao;

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
    public void getResponsesWithKeywords_returns_correct_response_list_when_only_one_response_matches(){
        List<Integer> intents = new ArrayList<>();
        List<Integer> entities = new ArrayList<>();
        intents.add(2);
        entities.add(4);
        List<String> returnedResponses = jdbcQueryDao.getResponsesFromIntentsAndEntities(intents,entities);
        Assert.assertEquals(1, returnedResponses.size());
        Assert.assertTrue(returnedResponses.contains("Test Response 6"));
    }

    @Test
    public void getResponsesWithKeywords_returns_correct_response_list_when_multiple_responses_match(){
        List<Integer> intents = new ArrayList<>();
        List<Integer> entities = new ArrayList<>();
        intents.add(2);
        entities.add(3);
        List<String> returnedResponses = jdbcQueryDao.getResponsesFromIntentsAndEntities(intents,entities);
        Assert.assertEquals(2, returnedResponses.size());
        Assert.assertTrue(returnedResponses.contains("Test Response 3"));
        Assert.assertTrue(returnedResponses.contains("Test Response 4"));
    }


    @Test
    public void getEntitiesAndIntents_returns_correct_array() {
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
    public void getAllMultiWordKeywords_returns_expected_list(){
        List<String> returnedKeywords = jdbcQueryDao.getAllMultiWordKeywords();
        Assert.assertEquals(3, returnedKeywords.size());
        Assert.assertEquals("The Longest Multiple Word Keyword", returnedKeywords.get(0));
        Assert.assertEquals("Multiple Word Keyword", returnedKeywords.get(1));
        Assert.assertEquals("Two-word keyword", returnedKeywords.get(2));

    }




}
