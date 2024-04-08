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
    public void getResponsesWithKeywords_returns_correct_response_list_when_multiple_responses_match(){
        List<String> potentialKeywords = new ArrayList<>();
        potentialKeywords.add("intent2keyword");
        potentialKeywords.add("entity3keyword");
        List<String> returnedResponses = jdbcQueryDao.getResponsesFromKeywords(potentialKeywords);
        Assert.assertEquals(2, returnedResponses.size());
        Assert.assertTrue(returnedResponses.contains("Test Response 3"));
        Assert.assertTrue(returnedResponses.contains("Test Response 4"));
    }

    @Test
    public void getResponsesWithKeywords_works_with_overlapping_multi_word_keywords(){
        List<String> potentialKeywords = new ArrayList<>();
        potentialKeywords.add("intent2keyword");
        potentialKeywords.add("The Longest Multiple Word Keyword");
        List<String> returnedResponses = jdbcQueryDao.getResponsesFromKeywords(potentialKeywords);
        Assert.assertTrue(returnedResponses.contains("Test Response 3"));
        Assert.assertFalse(returnedResponses.contains("Test Response 6"));

    }

    @Test
    public void getAllMultiWordKeywords_returns_expected_list(){
        List<String> returnedKeywords = jdbcQueryDao.getAllMultiWordKeywords();
        Assert.assertEquals(3, returnedKeywords.size());
        Assert.assertEquals("The Longest Mutiple Word Keyword", returnedKeywords.get(0));
        Assert.assertEquals("Multiple Word Keyword", returnedKeywords.get(1));
        Assert.assertEquals("Two-word keyword", returnedKeywords.get(2));

    }
}
