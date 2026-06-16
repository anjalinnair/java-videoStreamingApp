package com.videostreaming.moviecatalogservice.controller;

import com.videostreaming.moviecatalogservice.dto.MovieCatalogDto;
import com.videostreaming.moviecatalogservice.service.MovieCatalogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<MovieCatalogDto>> saveAll(@RequestBody List<MovieCatalogDto> movieCatalogList) {
        List<MovieCatalogDto> savedMovies = movieCatalogService.saveAll(movieCatalogList);
        return new ResponseEntity<>(savedMovies, HttpStatus.CREATED);
    }

    @GetMapping("/movie-catalog/lists")
    public ResponseEntity<List<MovieCatalogDto>> getAll() {
        List<MovieCatalogDto> movies = movieCatalogService.getAll();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/movie-catalog/find-path-by-id/{id}")
    public ResponseEntity<String> findPathById(@PathVariable Long id) {
        String path = movieCatalogService.findPathById(id);
        return ResponseEntity.ok(path);
    }

    @GetMapping("/movie-catalog/find-path-by-name/{name}")
    public ResponseEntity<String> findPathByName(@PathVariable String name) {
        String path = movieCatalogService.findPathByName(name);
        return ResponseEntity.ok(path);
    }
}
