package com.enigma.spotify.controller;

import com.enigma.spotify.entity.Song;
import com.enigma.spotify.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    SongService songService;

    @PostMapping
    public Song saveSong(@RequestBody Song song){
        return songService.saveSong(song);
    }

    @GetMapping
    public Page<Song> searchSong(@RequestBody Song song, @RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return songService.searchSong(song, pageable);
    }

    @PutMapping("/update")
    public Song updateSong(@RequestBody Song song){
        return songService.saveSong(song);
    }

    @DeleteMapping
    public void deleteSong(@RequestBody Song song){
        songService.deleteSong(song.getId());
    }

    @GetMapping("{/id}")
    public Song getSongById(@PathVariable String id){
        return songService.getSongById(id);
    }

    @GetMapping("/songs")
    public Page<Song> getAllSong(@RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return songService.getAllSong(pageable);
    }


}
