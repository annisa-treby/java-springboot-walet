package com.enigma.spotify.services;

import com.enigma.spotify.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AlbumService {
    public Album saveAlbumWithImage(MultipartFile file, String album) throws IOException;
    public Page<Album> searchAlbum(Album searchForm, Pageable pageable);
    public Album getAlbumById(String id);
    public void deleteAlbum(String id);
    public Page<Album> getAllAlbum(Pageable pageable);
}
