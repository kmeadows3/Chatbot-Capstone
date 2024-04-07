package com.techelevator.dao;

import java.util.List;

public interface QueryDao {

    List<String> getResponsesFromKeywords(List<String> keywords);

    List<String> getAllMultiWordKeywords();
}
