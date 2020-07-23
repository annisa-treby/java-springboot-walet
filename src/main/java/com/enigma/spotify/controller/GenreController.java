package com.enigma.spotify.controller;

import com.enigma.spotify.entity.Genre;
import com.enigma.spotify.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genre")
public class GenreController {

    @Autowired
    GenreService genreService;

    @PostMapping
    public Genre saveGenre(@RequestBody Genre genre){
        return genreService.saveGenre(genre);
    }

    @PostMapping("/update")
    public Genre updateGenre(@RequestBody Genre genre){
        return genreService.saveGenre(genre);
    }

    @GetMapping
    public Page<Genre> searchGenre(@RequestBody Genre searchForm, @RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return genreService.searchGenre(searchForm, pageable);
    }

    @DeleteMapping
    public void deleteGenre(@RequestBody Genre genre){
        genreService.deleteGenre(genre.getId());
    }

    @GetMapping("{/id}")
    public Genre getGenreById(@PathVariable String id){
        return genreService.getGenreById(id);
    }

    @GetMapping("/genres")
    public Page<Genre> getAllGenre(@RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return genreService.getAllGenre(pageable);
    }
}
