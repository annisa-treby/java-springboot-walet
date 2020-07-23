package com.enigma.spotify.services;

import com.enigma.spotify.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SongService {
    public Song saveSong(Song song);
    public Page<Song> searchSong(Song searchForm, Pageable pageable);
    public void deleteSong(String id);
    public Song getSongById(String id);
    public Page<Song> getAllSong(Pageable pageable);
}
