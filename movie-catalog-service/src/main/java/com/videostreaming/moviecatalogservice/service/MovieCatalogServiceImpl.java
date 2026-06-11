package com.videostreaming.moviecatalogservice.service;

import com.videostreaming.moviecatalogservice.dto.MovieCatalogDto;
import com.videostreaming.moviecatalogservice.exception.MovieNotFoundException;
import com.videostreaming.moviecatalogservice.model.MovieCatalog;
import com.videostreaming.moviecatalogservice.repository.MovieCatalogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieCatalogServiceImpl implements MovieCatalogService {

    private final MovieCatalogRepository movieCatalogRepository;

    // Constructor injection
    public MovieCatalogServiceImpl(MovieCatalogRepository movieCatalogRepository) {
        this.movieCatalogRepository = movieCatalogRepository;
    }

    @Override
    public List<MovieCatalogDto> saveAll(List<MovieCatalogDto> movieCatalogDtoList) {
        List<MovieCatalog> entities = movieCatalogDtoList.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        List<MovieCatalog> savedEntities = movieCatalogRepository.saveAll(entities);
        return savedEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieCatalogDto> getAll() {
        return movieCatalogRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public String findPathById(Long id) {
        return movieCatalogRepository.findById(id)
                .map(MovieCatalog::getPath)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with ID: " + id));
    }

    @Override
    public String findPathByName(String name) {
        return movieCatalogRepository.findByName(name)
                .map(MovieCatalog::getPath)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with name: " + name));
    }

    private MovieCatalog convertToEntity(MovieCatalogDto dto) {
        MovieCatalog entity = new MovieCatalog();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPath(dto.getPath());
        return entity;
    }

    private MovieCatalogDto convertToDto(MovieCatalog entity) {
        return new MovieCatalogDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPath()
        );
    }
}
