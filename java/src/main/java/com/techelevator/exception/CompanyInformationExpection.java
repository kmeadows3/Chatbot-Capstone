package com.techelevator.exception;

public class CompanyInformationExpection extends RuntimeException{
    public CompanyInformationExpection() {
        super();
    }
    public CompanyInformationExpection(String message) {
        super(message);
    }
    public CompanyInformationExpection(String message, Exception cause) {
        super(message, cause);
    }
}
