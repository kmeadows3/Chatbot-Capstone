package com.techelevator;

public class ThrowawayApp {

    public static void main(String[] args) {
        CompanyApiConsumer companyApiConsumer = new CompanyApiConsumer();

        companyApiConsumer.getCompanyData("huzzle.app");

        System.out.println("stops the debugger");
    }
}
