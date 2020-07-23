package com.enigma.spotify.services;

import com.enigma.spotify.entity.Song;
import com.enigma.spotify.repository.SongRepository;
import com.enigma.spotify.specification.SongSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SongServiceDVImpl implements SongService{

    @Autowired
    SongRepository songRepository;

    @Override
    public Song saveSong(Song song) {
    return songRepository.save(song);
    }

    @Override
    public Page<Song> searchSong(Song searchForm, Pageable pageable) {
        Page<Song> songs = songRepository.findAll(SongSpecification.findAll(searchForm),pageable);
        return songs;
    }

    @Override
    public void deleteSong(String id) {
        songRepository.deleteById(id);
    }

    @Override
    public Song getSongById(String id) {
        Song song = songRepository.findById(id).get();
        return song;
    }

    @Override
    public Page<Song> getAllSong(Pageable pageable) {
        Page<Song> songs = songRepository.findAll(pageable);
        return songs;
    }
}
