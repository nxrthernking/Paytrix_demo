package com.example.paytrix_demo.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrencyConversionClient {

    private RestTemplate template = new RestTemplate();
    private static final String API_URL = "https://devapi.currencycloud.com/v2/rates/find?currency_pair=%s";

    public String exchange(String source, String destiny) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Auth-Token", "57f8a2daf47aff5ad9c9357fc5782147");

        MultiValueMap<String, String> headersMap = new LinkedMultiValueMap<>();
        headersMap.add("X-Auth-Token", "57f8a2daf47aff5ad9c9357fc5782147");
        headersMap.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        org.springframework.http.HttpEntity<String> requestEntity = new org.springframework.http.HttpEntity<>(headersMap);

        ResponseEntity<String> response = template.exchange(String.format(API_URL, source.concat(destiny)),
                HttpMethod.GET, requestEntity, String.class);

        return response.getBody();
    }

}
