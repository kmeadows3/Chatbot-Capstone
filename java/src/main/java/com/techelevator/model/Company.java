package com.techelevator.model;

public class Company {
    private String company_name;
    private String website;
    private String url;
    private String employees_on_li;
    private String description;
    private CompanyHeadquarters headquarters;

    public CompanyHeadquarters getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(CompanyHeadquarters headquarters) {
        this.headquarters = headquarters;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getEmployees_on_li() {
        return employees_on_li;
    }

    public void setEmployees_on_li(String employees_on_li) {
        this.employees_on_li = employees_on_li;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "<p>"+ company_name + "'s headquarters is " + headquarters + ".</p>" +
                "<p>They describe themselves as '" + description +"'</p>"+
                "<p>They have " + employees_on_li +".</p>" +
                "<p>You can find out more at their website at " + website + " or " +
                "their LinkedIn at " + url +".</p>";
    }
}
