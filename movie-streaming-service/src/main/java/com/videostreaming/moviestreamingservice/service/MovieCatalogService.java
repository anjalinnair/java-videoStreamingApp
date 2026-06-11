package com.videostreaming.moviestreamingservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieCatalogService {

    private final String catalogServiceUrl;
    private final RestTemplate restTemplate;

    // Constructor Injection
    public MovieCatalogService(
            @Value("${catalog.service.url:http://movie-catalog-service}") String catalogServiceUrl,
            RestTemplate restTemplate
    ) {
        this.catalogServiceUrl = catalogServiceUrl;
        this.restTemplate = restTemplate;
    }

    public String getMoviePathById(Long id) {
        var response = restTemplate.getForEntity(catalogServiceUrl + "/movie-catalog/find-path-by-id/{id}", String.class, id);
        return response.getBody();
    }

    public String getMoviePathByName(String name) {
        var responseName = restTemplate.getForEntity(catalogServiceUrl + "/movie-catalog/find-path-by-name/{name}", String.class, name);
        return responseName.getBody();
    }
}