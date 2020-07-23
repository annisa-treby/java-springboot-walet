package com.enigma.spotify.services;

import com.enigma.spotify.entity.Album;
import com.enigma.spotify.repository.AlbumRepository;
import com.enigma.spotify.specification.AlbumSpecification;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class AlbumServiceDBImpl implements AlbumService {

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    FileUtility fileUtility;

    public Album saveAlbum(Album album){
        return albumRepository.save(album);
    }

    @Override
    public Album saveAlbumWithImage(MultipartFile file, String album) throws IOException {
        Album album1 = saveAlbum(objectMapper.readValue(album, Album.class));
        album1.setImage(fileUtility.upload(album1.getId(), file));
        return album1;
    }

    @Override
    public Page<Album> searchAlbum(Album searchForm, Pageable pageable) {
        Page<Album> albums = albumRepository.findAll(AlbumSpecification.findAll(searchForm), pageable);
        return albums;
    }

    @Override
    public Album getAlbumById(String id) {
        Album album = albumRepository.findById(id).get();
        return album;
    }

    @Override
    public void deleteAlbum(String id) {
        albumRepository.deleteById(id);
    }

    @Override
    public Page<Album> getAllAlbum(Pageable pageable) {
        Page<Album> albums = albumRepository.findAll(pageable);
        return albums;
    }
}
