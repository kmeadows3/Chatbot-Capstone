package com.techelevator.model;

public class CompanyResponseDTO {
    private boolean success;
    private int status;
    private Company data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Company getData() {
        return data;
    }

    public void setData(Company data) {
        this.data = data;
    }
}
