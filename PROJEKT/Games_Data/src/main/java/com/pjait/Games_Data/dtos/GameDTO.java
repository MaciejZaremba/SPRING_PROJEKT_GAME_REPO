package com.pjait.Games_Data.dtos;

import java.util.List;

public record GameDTO(
        Long id,
        Integer apiId,
        String name,
        Integer releaseYear,
        Double rating,
        List<GenreDTO> genres,
        List<PlatformDTO> platforms,
        List<ThemeDTO> themes,
        CompanyDTO company
) {}
