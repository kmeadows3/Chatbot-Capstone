package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Response {

    private String response;
    private List<Integer> userIntents;
    private List<Integer> userEntities;
    private int mode = 0;
    @JsonIgnore
    private List<Integer> responseIntents = new ArrayList<>();
    @JsonIgnore
    private List<Integer> responseEntities = new ArrayList<>();

    public Response() {
    }

    public Response(String response) {
        this.response = response;
    }

    // Getters
    public String getResponse() {
        return response;
    }

    public int getMode() {
        return mode;
    }

    public List<Integer> getUserIntents() {
        return userIntents;
    }
    public List<Integer> getUserEntities() {
        return userEntities;
    }

    public List<Integer> getResponseIntents() {
        return responseIntents;
    }
    public List<Integer> getResponseEntities() {
        return responseEntities;
    }


    // Setters
    public void setResponse(String response) {
        this.response = response;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public void setUserIntents(List<Integer> userIntents) {
        this.userIntents = userIntents;
    }

    public void setUserEntities(List<Integer> userEntities) {
        this.userEntities = userEntities;
    }

    public void setResponseIntents(List<Integer> responseIntents) {
        this.responseIntents = responseIntents;
    }
    public void setResponseEntities(List<Integer> responseEntities) {
        this.responseEntities = responseEntities;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response1 = (Response) o;
        return Objects.equals(response, response1.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(response);
    }
}
