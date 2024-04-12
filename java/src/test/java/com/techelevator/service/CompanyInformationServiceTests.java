package com.techelevator.service;

import com.techelevator.exception.CompanyInformationExpection;
import com.techelevator.model.Company;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class CompanyInformationServiceTests {
    private CompanyInformationService companyInformationService;
    private final String MICROSOFT_TO_STRING = "<p>Microsoft's headquarters is 1 Microsoft Way, Redmond, Washington 98052, US.</p>" +
            "<p>They describe themselves as 'Every company has a mission. What's ours? To empower every person and" +
            " every organization to achieve more. We believe technology can and should be a force for good and " +
            "that meaningful innovation contributes to a brighter world in the future and today. Our culture " +
            "doesn’t just encourage curiosity; it embraces it. Each day we make progress together by showing up " +
            "as our authentic selves. We show up with a learn-it-all mentality. We show up cheering on others, " +
            "knowing their success doesn't diminish our own. We show up every day open to learning our own biases, " +
            "changing our behavior, and inviting in differences. Because impact matters. Microsoft operates in 190 " +
            "countries and is made up of more than 220,000 passionate employees worldwide.'</p><p>They have " +
            "10,001+ employees.</p><p>You can find out more at their website at https://news.microsoft.com/ " +
            "or their LinkedIn at https://www.linkedin.com/company/microsoft.</p>";
    @Before
    public void setCompanyInformationService(){
        companyInformationService = new CompanyInformationService();
    }

    @Test
    public void getCompanyDataDemoMode_returns_microsoft_information(){
        Company company = companyInformationService.getCompanyDataDemoMode("microsoft.com");
        Assert.assertEquals(MICROSOFT_TO_STRING, company.toString());
    }

    @Test (expected = CompanyInformationExpection.class)
    public void getCompanyData_throws_company_information_exception_when_company_can_not_be_found(){
        companyInformationService.getCompanyData("asdfasfasdf");
        Assert.fail();
    }

    @Test
    public void getCompanyData_returns_correct_data_if_domain_exists(){
        Company testCompany = companyInformationService.getCompanyData("microsoft.com");
        Assert.assertEquals(MICROSOFT_TO_STRING, testCompany.toString());
    }
}
