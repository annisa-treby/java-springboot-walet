package com.enigma.spotify.services;

import com.enigma.spotify.entity.Playlist;
import com.enigma.spotify.entity.Profile;
import com.enigma.spotify.repository.PlaylistRepository;
import com.enigma.spotify.specification.PlaylistSpecification;
import com.enigma.spotify.specification.ProfileSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlaylistServiceDBImpl implements PlaylistService{

    @Autowired
    PlaylistRepository playlistRepository;

    @Override
    public Playlist savePlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    @Override
    public Playlist getPlaylist(String id) {
        Playlist playlist = playlistRepository.findById(id).get();
        return playlist;
    }

    @Override
    public Page<Playlist> getAllPlaylist(Pageable pageable) {
        return playlistRepository.findAll(pageable);
    }

    @Override
    public void deletePlaylist(String id) {
        Playlist playlist = getPlaylist(id);
        playlistRepository.delete(playlist);
    }

    @Override
    public Page<Playlist> searchPlaylist(Playlist playlist, Pageable pageable) {
        Page<Playlist> playlists = playlistRepository.findAll(PlaylistSpecification.findAll(playlist), pageable);
        return playlists;
    }
}
