package com.videostreaming.moviecatalogservice.service;

import com.videostreaming.moviecatalogservice.dto.MovieCatalogDto;
import java.util.List;

public interface MovieCatalogService {
    List<MovieCatalogDto> saveAll(List<MovieCatalogDto> movieCatalogDtoList);
    List<MovieCatalogDto> getAll();
    String findPathById(Long id);
    String findPathByName(String name);
}