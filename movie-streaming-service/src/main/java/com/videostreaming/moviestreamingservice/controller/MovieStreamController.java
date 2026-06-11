package com.videostreaming.moviestreamingservice.controller;

import com.videostreaming.moviestreamingservice.service.MovieCatalogService;
import com.videostreaming.moviestreamingservice.service.VideoStreamingService;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class MovieStreamController {

    private static final Logger log = Logger.getLogger(MovieStreamController.class.getName());

    private final MovieCatalogService movieCatalogService;
    private final VideoStreamingService videoStreamingService;

    // Constructor Injection
    public MovieStreamController(MovieCatalogService movieCatalogService, VideoStreamingService videoStreamingService) {
        this.movieCatalogService = movieCatalogService;
        this.videoStreamingService = videoStreamingService;
    }

    @GetMapping("/stream/find-by-path/{videoPath}")
    public ResponseEntity<Resource> streamVideo(@PathVariable String videoPath) {
        Resource videoResource = videoStreamingService.getVideoResource(videoPath);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("video/mp4"))
                .body(videoResource);
    }

    @GetMapping("/stream/find-path-by-id/{id}")
    public ResponseEntity<Resource> streamVideoById(@PathVariable Long id) {
        String moviePath = movieCatalogService.getMoviePathById(id);
        log.log(Level.INFO, "Resolved movie path by id= {0}", moviePath);
        return streamVideo(moviePath);
    }

    @GetMapping("/stream/find-path-by-name/{name}")
    public ResponseEntity<Resource> streamVideoByName(@PathVariable String name) {
        String moviePathByName = movieCatalogService.getMoviePathByName(name);
        return streamVideo(moviePathByName);
    }
}