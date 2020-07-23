package com.enigma.spotify.services;

import com.enigma.spotify.entity.Genre;
import com.enigma.spotify.repository.GenreRepository;
import com.enigma.spotify.specification.GenreSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class GenreServiceDBImpl implements GenreService {

    @Autowired
    GenreRepository genreRepository;

    @Override
    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Page<Genre> searchGenre(Genre searchForm, Pageable pageable) {
        Page<Genre> genres = genreRepository.findAll(GenreSpecification.findAll(searchForm), pageable);
        return genres;
    }

    @Override
    public Genre getGenreById(String id) {
        Genre genre = genreRepository.findById(id).get();
        return genre;
    }

    @Override
    public void deleteGenre(String id) {
        Genre genre = getGenreById(id);
        genreRepository.delete(genre);
    }

    @Override
    public Page<Genre> getAllGenre(Pageable pageable) {
        Page<Genre> genres = genreRepository.findAll(pageable);
        return genres;
    }
}
