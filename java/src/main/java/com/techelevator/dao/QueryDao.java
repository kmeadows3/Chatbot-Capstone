package com.techelevator.dao;

import com.techelevator.model.Response;

import java.util.List;

public interface QueryDao {

    List<Response> getResponsesFromIntentsAndEntities(List<Integer> intentIds, List<Integer> entityIds);

    List<String> getAllMultiWordKeywords();

    List<Integer>[] getIntentsAndEntitiesFromKeywords(List<String> potentialKeywords);
}
