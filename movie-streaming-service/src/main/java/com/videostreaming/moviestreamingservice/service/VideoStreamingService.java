package com.videostreaming.moviestreamingservice.service;

import com.videostreaming.moviestreamingservice.exception.VideoNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class VideoStreamingService {

    private final String videoDirectory;

    public VideoStreamingService(
            @Value("${video.directory:../StreamVideos/}") String videoDirectory
    ) {
        this.videoDirectory = videoDirectory;
    }

    public Resource getVideoResource(String videoPath) {
        File file = new File(videoDirectory, videoPath);
        if (!file.exists() || !file.isFile()) {
            throw new VideoNotFoundException("Video file not found: " + videoPath);
        }
        return new FileSystemResource(file);
    }
}