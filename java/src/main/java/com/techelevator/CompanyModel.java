package com.techelevator;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyModel {
    private String company_name;
    private String website;
    private String url;
    private String employees_on_li;
    private String description;

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
        return "CompanyModel{" +
                "name='" + company_name + '\'' +
                ", website='" + website + '\'' +
                ", url='" + url + '\'' +
                ", employees_on_li='" + employees_on_li + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
