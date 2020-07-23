package com.enigma.spotify.services;

import com.enigma.spotify.entity.Genre;
import com.enigma.spotify.entity.Song;
import com.enigma.spotify.repository.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class GenreServiceDBImplTest {

    @Autowired
    GenreService genreService;

    @MockBean
    GenreRepository genreRepository;

    @BeforeEach
    void cleanUp(){
        genreRepository.deleteAll();
    }

    @Test
    void saveGenre_Should_Call_genreRepository_Once_When_genreSaved() {
        Genre genre = new Genre();
        genre.setId("1");
        genre.setType("pop");

        genreService.saveGenre(genre);
        Mockito.verify(genreRepository, Mockito.times(1)).save(genre);
    }
    @Test
    void saveGenre_once_Should_saved_Once_in_DB() {
        Genre genre = new Genre();
        genre.setId("1");
        genre.setType("pop");

        genreService.saveGenre(genre);
        List<Genre> genres =new ArrayList<>();
        genres.add(genre);

        Mockito.when(genreRepository.findAll()).thenReturn(genres);
    }
    @Test
    void save_2_genre_Should_saved_2_in_DB() {
        Genre genre = new Genre();
        genre.setId("1");
        genre.setType("pop");

        Genre genre1 = new Genre();
        genre.setId("2");
        genre.setType("rock");

        genreService.saveGenre(genre);
        genreService.saveGenre(genre1);

        List<Genre> genres =new ArrayList<>();
        genres.add(genre);
        genres.add(genre1);

        Mockito.when(genreRepository.findAll()).thenReturn(genres);
    }

    @Test
    void getGenreById_should_return_correct_genre() {
        Genre genre = new Genre();
        genre.setType("pop");

        Genre genre1 = new Genre();
        genre1.setType("jazz");

        genreRepository.save(genre);
        genreRepository.save(genre1);

        assertEquals(genre, genreService.getGenreById(genre.getId()));
    }

    @Test
    void deleteGenre() {
        Genre genre = new Genre();
        genre.setType("pop");

        genreRepository.save(genre);
        genreService.deleteGenre(genre.getId());

        assertEquals(0,genreRepository.findAll().size());
    }

    @Test
    void getAllGenre() {
        Genre genre = new Genre();
        genre.setType("pop");

        genreRepository.save(genre);

        assertEquals(1, genreService.getAllGenre(PageRequest.of(0,1)).getTotalElements());
}
}