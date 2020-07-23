package com.enigma.spotify.controller;

import com.enigma.spotify.entity.Playlist;
import com.enigma.spotify.entity.Profile;
import com.enigma.spotify.services.PlaylistService;
import com.enigma.spotify.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    PlaylistService playlistService;

    @PostMapping
    public Playlist savePlaylist(@RequestBody Playlist playlist){
        return playlistService.savePlaylist(playlist);
    }

    @PostMapping("/update")
    public Playlist updatePlaylist(@RequestBody Playlist playlist){
        return playlistService.savePlaylist(playlist);
    }

    @GetMapping("/playlists")
    public Page<Playlist> getAllPlaylist(@RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return playlistService.getAllPlaylist(pageable);
    }
    @GetMapping
    public Page<Playlist> searchPlaylist(@RequestBody Playlist searchForm, @RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return playlistService.searchPlaylist(searchForm, pageable);
    }

    @DeleteMapping
    public void deletePlaylist(@RequestBody Playlist playlist){
        playlistService.deletePlaylist(playlist.getId());
    }

    @GetMapping("{/id}")
    public Playlist getPlaylistById(@PathVariable String id){
        return playlistService.getPlaylist(id);
    }
//playlist
}