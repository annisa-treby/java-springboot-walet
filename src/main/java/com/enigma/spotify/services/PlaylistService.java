package com.enigma.spotify.services;

import com.enigma.spotify.entity.Playlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaylistService {
    public Playlist savePlaylist(Playlist playlist);
    public Playlist getPlaylist(String id);
    public Page<Playlist> getAllPlaylist(Pageable pageable);
    public void deletePlaylist(String id);
    public Page<Playlist> searchPlaylist(Playlist playlist, Pageable pageable);
}
