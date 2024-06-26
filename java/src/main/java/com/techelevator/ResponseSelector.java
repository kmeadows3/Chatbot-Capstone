package com.techelevator;

import com.techelevator.model.Response;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class ResponseSelector {
    public final int PRACTICE_INTENT_ID = 4;
    public final int HR_INTERVIEW_ENTITY_ID = 7;
    public final int TECHNICAL_INTERVIEW_ENTITY_ID = 8;
    public final int JOHN_ENTITY = 13;
    public final int ERIC_ENTITY = 16;



    /**
     *This method selects the appropriate response from a list of potential responses
     *
     * @param responses -- all responses that match the intents and entities in the utterance
     * @param inputIntents -- intents derived from the utterance
     * @param inputEntities -- entities derived from the utterance
     * @return the single response that best fits the utterance
     */
    public String selectResponse(List<Response> responses, List<Integer> inputIntents, List<Integer> inputEntities){

        //get list with one random response if the user wants a practice interview
        if (inputEntities.contains(JOHN_ENTITY)||inputEntities.contains(ERIC_ENTITY)||(inputIntents.contains(PRACTICE_INTENT_ID) &&
                (inputEntities.contains(HR_INTERVIEW_ENTITY_ID) || inputEntities.contains(TECHNICAL_INTERVIEW_ENTITY_ID)))) {
            responses = handlePracticeInterviews(responses);
        }


        if (responses.size() > 1) {
            //filter out everything but the responses that appear on the list the most
            responses = filterForResponsesWithMostKeywordMatches(responses);
            if (responses.size() > 1){
                //filter out the responses with the most entities that don't match the utterance
                responses = filterForResponsesWithMostExactMatch(responses, inputEntities, inputIntents);
            }
            //TODO select the best fitting of the remaining responses


        }


        return responses.get(0).getResponse();
    }

    /**
     *This method takes a list of responses and filters to find the one whose keywords that most exactly matches the utterance
     *
     * @param responses -- list of responses to filter
     * @para entities -- list of entities derived from the user's utterance
     * @return list of responses that match the utterance entities the best
     */
    private List<Response> filterForResponsesWithMostExactMatch(List<Response> responses, List<Integer> entities, List<Integer> intents) {
        Map<Response, Integer> responseRanks = new LinkedHashMap<>();
        for (Response response : responses){
            int rank = 0;
            for(int entity : response.getResponseEntities()){
                if(entities.contains(entity)){
                    rank--;
                } else {
                    rank++;
                }
            }
            for(int intent : response.getResponseIntents()){
                if(intents.contains(intent)){
                    rank--;
                } else {
                    rank++;
                    if (intent == 11) {
                        rank +=5;
                    }
                }
            }
            responseRanks.put(response, rank);
        }

        List<Map.Entry<Response, Integer>> rankList = new ArrayList<>(responseRanks.entrySet());
        rankList.sort(Map.Entry.comparingByValue());
        List<Response> bestResponses = removeAllButBestRankedMatchesFromEntryList(rankList);

        return bestResponses;
    }

    /**
     * This method takes a list of map entries and returns a list of the keys with the same value as the top ranked key
     * @param rankedResponseList A list of Map<Response,Integer> entries, sorted by integer so that the index 0 on the list
     *                           is the top ranked entry
     * @return a list of the responses whose values matched the top ranked value
     */
    private List<Response> removeAllButBestRankedMatchesFromEntryList(List<Map.Entry<Response, Integer>> rankedResponseList) {
        int bestRank = rankedResponseList.get(0).getValue();
        List<Response> bestResponses = rankedResponseList.stream()
                .filter(response -> response.getValue() == bestRank)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return bestResponses;
    }

    /**
     *This method selects a random response from the responses that are practice interview questions
     *
     * @param responses -- all responses that match the intents and entities in the utterance
     * @return a random practice interview response
     */
    private List<Response> handlePracticeInterviews(List<Response> responses) {

        responses = responses.stream()
                .filter(response -> (response.getResponseIntents().contains(PRACTICE_INTENT_ID)
                                    ||response.getResponseEntities().contains(JOHN_ENTITY)
                                    ||response.getResponseEntities().contains(ERIC_ENTITY)))
                .collect(Collectors.toList());
        responses = getResponseListWithOnlyOneRandomResponse(responses);

        return responses;
    }

    /**
     *This method selects a random response from a list of responses
     *
     * @param responses -- all responses that match the intents and entities in the utterance
     * @return a list of responses that contains only the random response
     */
    private static List<Response> getResponseListWithOnlyOneRandomResponse(List<Response> responses) {
        Random rand = new Random();
        List<Response> randomResponseList = new ArrayList<>();
        Response randomResponse = responses.get(rand.nextInt(responses.size()));
        randomResponseList.add(randomResponse);
        return randomResponseList;
    }

    /**
     * @param responses -- all responses that match the intents and entities in the utterance
     * @return a list of responses that have the highest number of keyword matches
     */
    private List<Response> filterForResponsesWithMostKeywordMatches(List<Response> responses) {
        Map<Response, Integer> responseCounts = new LinkedHashMap<>();

        responses.stream().forEach(response -> {
            if (!responseCounts.containsKey(response)){
                int count = Collections.frequency(responses, response);
                responseCounts.put(response, count);
            }
        });

        List<Map.Entry<Response, Integer>> countList = new ArrayList<>(responseCounts.entrySet());

        countList.sort(Map.Entry.comparingByValue());
        Collections.reverse(countList);
        List<Response> topResults = removeAllButBestRankedMatchesFromEntryList(countList);

        return topResults;
    }


}
