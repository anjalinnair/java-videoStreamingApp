package com.videostreaming.moviecatalogservice.controller;

import com.videostreaming.moviecatalogservice.dto.MovieCatalogDto;
import com.videostreaming.moviecatalogservice.service.MovieCatalogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieCatalogController {

    private final MovieCatalogService movieCatalogService;

    // Constructor Injection
    public MovieCatalogController(MovieCatalogService movieCatalogService) {
        this.movieCatalogService = movieCatalogService;
    }

    @PostMapping("/movie-catalog/save")
    public List<MovieCatalogDto> saveAll(@RequestBody List<MovieCatalogDto> movieCatalogList) {
        return movieCatalogService.saveAll(movieCatalogList);
    }

    @GetMapping("/movie-catalog/lists")
    public List<MovieCatalogDto> getAll() {
        return movieCatalogService.getAll();
    }

    @GetMapping("/movie-catalog/find-path-by-id/{id}")
    public String findPathById(@PathVariable Long id) {
        return movieCatalogService.findPathById(id);
    }

    @GetMapping("/movie-catalog/find-path-by-name/{name}")
    public String findPathByName(@PathVariable String name) {
        return movieCatalogService.findPathByName(name);
    }
}
