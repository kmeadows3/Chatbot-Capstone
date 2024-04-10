package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Response;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcQueryDao implements QueryDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcQueryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Response> getResponsesFromIntentsAndEntities(List<Integer> intentIds, List<Integer> entityIds) {
        String sql = "SELECT r.response_id, response from response r " +
                "JOIN response_intent ri ON ri.response_id = r.response_id " +
                "JOIN response_entity re on re.response_id = r.response_id " +
                "WHERE intent_id = ? AND entity_id = ?";
        List<Response> responses = new ArrayList<>();

        try {
            for (Integer intentId : intentIds) {
                for (Integer entityId : entityIds) {
                    SqlRowSet result = jdbcTemplate.queryForRowSet(sql, intentId, entityId);
                    while (result.next()) {
                        Response response = new Response();
                        response.setResponse(result.getString("response"));
                        int responseId = result.getInt("response_id");
                        response.setResponseIntents(getAllResponseIntentsByResponseId(responseId));
                        response.setResponseEntities(getAllResponseEntitiesByResponseId(responseId));
                        responses.add(response);
                    }
                }
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }

        return responses;
    }

    private List<Integer> getAllResponseIntentsByResponseId(int responseId){
        String sql = "SELECT intent_id FROM response_intent WHERE response_id = ?";
        List<Integer> intentList = new ArrayList<>();
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, responseId);
            while(results.next()){
                intentList.add(results.getInt("intent_id"));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }

        return intentList;
    }

    private List<Integer> getAllResponseEntitiesByResponseId(int responseId){
        String sql = "SELECT entity_id FROM response_entity WHERE response_id = ?";
        List<Integer> intentList = new ArrayList<>();
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, responseId);
            while(results.next()){
                intentList.add(results.getInt("entity_id"));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }

        return intentList;
    }


    /**
     *
     * @param potentialKeywords -- List of tokens from the utterance
     * @return Array of 2 lists -- the index 0 is the List of intent ids, index 1 is the List of entity ids
     */
    @Override
    public List<Integer>[] getIntentsAndEntitiesFromKeywords(List<String> potentialKeywords) {
        List<Integer> entityIds = new ArrayList<>();
        List<Integer> intentIds = new ArrayList<>();
        String sql = "SELECT keyword, entity_id, intent_id FROM keyword WHERE keyword ILIKE ?";

        try {
            for (String potentialKeyword : potentialKeywords) {
                SqlRowSet results = jdbcTemplate.queryForRowSet(sql, potentialKeyword);
                while (results.next()) {
                    Integer entityId = results.getInt("entity_id");
                    Integer intentId = results.getInt("intent_id");
                    if (entityId != 0 && !entityIds.contains(entityId)) {
                        entityIds.add(entityId);
                    } else if (intentId != 0 && !intentIds.contains(intentId)) {
                        intentIds.add(intentId);
                    }
                }
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }

        List<Integer>[] returnArray = new List[]{intentIds, entityIds};
        return returnArray;
    }

    @Override
    public List<String> getAllMultiWordKeywords(){
        List<String> keywords = new ArrayList<>();
        String sql = "SELECT keyword FROM keyword WHERE keyword LIKE '% %' " +
                "ORDER BY LENGTH(keyword) - LENGTH(REPLACE(keyword, ' ', '')) DESC";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()){
                String keyword = results.getString("keyword");
                keywords.add(keyword);
            }
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data Integrity Violation", e);
            }
        return keywords;
    };
}

