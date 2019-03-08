package com.apnot.hquery.service;

import com.apnot.hquery.model.Details;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class DetailsServiceClient {

    private final RestTemplate restTemplate;

    public DetailsServiceClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public Details getDetails(String params) {
        String host = "https://megapersonals.com";
        String path = "/user";

        URI uri = UriComponentsBuilder
                .fromUriString(host)
                .path(path)
                .query(params)
                .build()
                .encode()
                .toUri();

        System.out.println("uri: " + uri.toString());
        return restTemplate.getForObject(uri, Details.class);
    }

    public ResponseEntity<Details> exchangeWithPost(String params) {
        HttpHeaders headers = new HttpHeaders();
        String host = "https://megapersonals.com";
        String path = "/user";

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        URI uri = UriComponentsBuilder
                .fromUriString(host)
                .path(path)
                .query(params)
                .build()
                .encode()
                .toUri();

        System.out.println("uri: " + uri.toString());
        return restTemplate.exchange(uri, HttpMethod.POST, httpEntity, Details.class);
    }

}