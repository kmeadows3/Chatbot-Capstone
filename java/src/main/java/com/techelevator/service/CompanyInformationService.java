package com.techelevator.service;

import com.techelevator.model.Company;
import com.techelevator.model.CompanyHeadquarters;
import com.techelevator.model.CompanyRequestDTO;
import com.techelevator.model.CompanyResponseDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CompanyInformationService {
    private RestTemplate restTemplate = new RestTemplate();
    private String apiKey = "c01969675dmsh90c9cf1d5b0c87fp1a4bd0jsn60b0494c5f2c";
    private String apiHost = "linkedin-data-scraper.p.rapidapi.com";
    private String url = "https://linkedin-data-scraper.p.rapidapi.com/web-domain";

    public Company getCompanyData(String webDomain){
        CompanyRequestDTO companyRequestDTO = new CompanyRequestDTO();
        companyRequestDTO.setLink(webDomain);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", apiHost);
        HttpEntity<CompanyRequestDTO> entity = new HttpEntity<>(companyRequestDTO, headers);

        ResponseEntity<CompanyResponseDTO> response = restTemplate.exchange(url, HttpMethod.POST, entity, CompanyResponseDTO.class);
        CompanyResponseDTO companyResponseDTO = response.getBody();
        Company company = companyResponseDTO.getData();

        return company;
    }

    public Company getCompanyDataDemoMode(String webDomain){
        Company company = new Company();
        company.setCompany_name("Microsoft");
        CompanyHeadquarters headquarters = new CompanyHeadquarters();
        headquarters.setFullAddress("1 Microsoft Way, Redmond, Washington 98052, US");
        company.setHeadquarters(headquarters);
        company.setDescription("Every company has a mission. What's ours? To empower every person and " +
                "every organization to achieve more. We believe technology can and should be a force for good and that " +
                "meaningful innovation contributes to a brighter world in the future and today. Our culture doesn’t " +
                "just encourage curiosity; it embraces it. Each day we make progress together by showing up as our " +
                "authentic selves. We show up with a learn-it-all mentality. We show up cheering on others, knowing " +
                "their success doesn't diminish our own. We show up every day open to learning our own biases, " +
                "changing our behavior, and inviting in differences. Because impact matters. Microsoft operates in " +
                "190 countries and is made up of more than 220,000 passionate employees worldwide.");
        company.setEmployees_on_li("10,001+ employees");
        company.setWebsite("https://news.microsoft.com/");
        company.setUrl("https://www.linkedin.com/company/microsoft");

        return company;
    }
}
