package com.enigma.spotify.services;

import com.enigma.spotify.entity.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ArtistService {
    public Artist saveArtistWithImage(MultipartFile file, String artist) throws IOException;
    public Page<Artist> searchArtist(Artist searchForm, Pageable pageable);
    public Artist getArtistById(String id);
    public void deleteArtist(String id);
    public Page<Artist> getAllArtist(Pageable pageable);
}
