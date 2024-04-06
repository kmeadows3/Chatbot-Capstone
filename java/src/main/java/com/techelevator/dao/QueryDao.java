package com.techelevator.dao;

import java.util.List;

public interface QueryDao {

    List<String> getResponsesWithKeywords(List<String> keywords);
}
