package com.techelevator.dao;

import java.util.List;

public interface QueryDao {

    List<String> getResponsesFromIntentsAndEntities(List<Integer> intentIds, List<Integer> entityIds);

    List<String> getAllMultiWordKeywords();

    List<Integer>[] getIntentsAndEntitiesFromKeywords(List<String> potentialKeywords);
}
