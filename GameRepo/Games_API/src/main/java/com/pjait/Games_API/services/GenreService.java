package com.pjait.Games_API.services;

import com.pjait.Games_Data.entities.Genre;
import com.pjait.Games_Data.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService extends BaseService<Genre> {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        super(genreRepository);
        this.genreRepository = genreRepository;
    }

    public List<Genre> findAllGenres() {
        return findAllEntities();
    }

    public Genre findGenreById(Long id) {
        return findById(id);
    }

    public void deleteGenre(Long id) {
        deleteById(id);
    }

    public void addGenre(Genre genre) {
        add(genre);
    }

    public void updateGenre(Genre genre, Long id) {
        update(genre, id);
    }

    public Genre findGenreByName(String name) {
        return genreRepository.findByName(name);
    }

    public Genre findGenreByApiId(Long apiId)
    {
        return genreRepository.findByApiId(apiId);
    }
}
