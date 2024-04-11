package com.techelevator;

import com.techelevator.model.Response;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


public class CompanyApiConsumer {
    private RestTemplate restTemplate = new RestTemplate();
    private String apiKey = "c01969675dmsh90c9cf1d5b0c87fp1a4bd0jsn60b0494c5f2c";
    private String apiHost = "linkedin-data-scraper.p.rapidapi.com";
    private String url = "https://linkedin-data-scraper.p.rapidapi.com/web-domain";

    public void getCompanyData(String webDomain){
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setLink(webDomain);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", apiHost);
        HttpEntity<CompanyDTO> entity = new HttpEntity<>(companyDTO, headers);

        ResponseEntity<CompanyModel> response = restTemplate.exchange(url, HttpMethod.POST, entity, CompanyModel.class);

        CompanyModel company = response.getBody();

        System.out.println(company);


    }
}
