package com.techelevator;

import com.techelevator.model.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

public class ResponseSelectorTests {
    private ResponseSelector sut;
    private List<Response> potentialResponses;
    private List<Integer> intents;
    private List<Integer> entities;
    private final String EXPECTED_STRING = "Expected Response String";
    private final String INVALID_STRING = "Invalid";

    @Before
    public void setup(){
        sut = new ResponseSelector();
        potentialResponses = new ArrayList<>();
        intents = new ArrayList<>();
        entities = new ArrayList<>();
    }

    @Test
    public void selectResponse_returns_response_when_only_one_response_on_list(){
        intents.add(1);
        entities.add(1);
        Response testResponse = new Response(EXPECTED_STRING);
        potentialResponses.add(testResponse);
        String returnedResponseString = sut.selectResponse(potentialResponses, intents, entities);
        Assert.assertEquals(EXPECTED_STRING, returnedResponseString);
    }

    @Test
    public void selectResponse_returns_response_that_appears_most_when_dealing_with_multiple_responses(){
        intents.add(1);
        entities.add(1);
        Response testResponse = new Response(EXPECTED_STRING);
        Response responseToAvoid = new Response(INVALID_STRING);
        potentialResponses.add(testResponse);
        potentialResponses.add(responseToAvoid);
        potentialResponses.add(testResponse);
        potentialResponses.add(testResponse);
        potentialResponses.add(responseToAvoid); //last response on list is the default picked

        String returnedString = sut.selectResponse(potentialResponses, intents, entities);
        Assert.assertEquals(EXPECTED_STRING, returnedString);
        Assert.assertNotEquals(INVALID_STRING, returnedString);
    }

    @Test
    public void selectResponse_returns_response_that_matches_user_entities_most_exactly_when_only_one_result_each(){
        intents.add(1);
        entities.add(1);

        Response testResponse = new Response(EXPECTED_STRING);
        List<Integer> testResponseEntities = new ArrayList<>();
        testResponseEntities.add(1);
        testResponse.setResponseEntities(testResponseEntities);

        Response responseToAvoid = new Response(INVALID_STRING);
        List<Integer> avoidEntities = new ArrayList<>();
        avoidEntities.add(1);
        avoidEntities.add(2);
        responseToAvoid.setResponseEntities(avoidEntities);

        potentialResponses.add(testResponse);
        potentialResponses.add(responseToAvoid);

        String returnedString = sut.selectResponse(potentialResponses, intents, entities);
        Assert.assertEquals(EXPECTED_STRING, returnedString);
        Assert.assertNotEquals(INVALID_STRING, returnedString);
    }

    @Test
    public void selectResponse_returns_response_that_matches_user_entities_most_exactly_multiple_tied_responses(){
        intents.add(1);
        entities.add(1);

        Response testResponse = new Response(EXPECTED_STRING);
        List<Integer> testResponseEntities = new ArrayList<>();
        testResponseEntities.add(1);
        testResponse.setResponseEntities(testResponseEntities);

        Response responseToAvoid = new Response(INVALID_STRING);
        List<Integer> avoidEntities = new ArrayList<>();
        avoidEntities.add(1);
        avoidEntities.add(2);
        responseToAvoid.setResponseEntities(avoidEntities);

        potentialResponses.add(testResponse);
        potentialResponses.add(responseToAvoid);
        potentialResponses.add(testResponse);
        potentialResponses.add(responseToAvoid);

        String returnedString = sut.selectResponse(potentialResponses, intents, entities);
        Assert.assertEquals(EXPECTED_STRING, returnedString);
        Assert.assertNotEquals(INVALID_STRING, returnedString);
    }
}
