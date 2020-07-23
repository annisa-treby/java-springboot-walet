package com.enigma.spotify.services;

import com.enigma.spotify.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenreService {
    public Genre saveGenre(Genre genre);
    public Page<Genre> searchGenre(Genre searchForm, Pageable pageable);
    public Genre getGenreById(String id);
    public void deleteGenre(String id);
    public Page<Genre> getAllGenre(Pageable pageable);
}
