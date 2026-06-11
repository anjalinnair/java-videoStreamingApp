package com.videostreaming.moviecatalogservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieCatalogDto {
    private Long id;
    private String name;
    private String description;
    private String path;
}