package com.enigma.spotify.services;

import com.enigma.spotify.entity.Artist;
import com.enigma.spotify.repository.ArtistRepository;
import com.enigma.spotify.specification.ArtistSpecification;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class ArtistServiceDBImpl implements ArtistService{

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    FileUtility fileUtility;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public Artist saveArtistWithImage(MultipartFile multipartFile, String requestBody) throws IOException {
        Artist artist = artistRepository.save(objectMapper.readValue(requestBody, Artist.class));
        artist.setPhoto(fileUtility.upload(artist.getId(), multipartFile));
        artistRepository.save(artist);
        return artist;
    }

    @Override
    public Page<Artist> searchArtist(Artist searchForm, Pageable pageable) {
        Page<Artist> artists = artistRepository.findAll(ArtistSpecification.findAll(searchForm), pageable);
        return artists;
    }

    @Override
    public Artist getArtistById(String id) {
        Artist artist = new Artist();
        if(artistRepository.findById(id).isPresent()){
            artist= artistRepository.findById(id).get();
        }
        return  artist;
    }

    @Override
    public void deleteArtist(String id) {
        Artist artist = getArtistById(id);
        artistRepository.delete(artist);
    }

    @Override
    public Page<Artist> getAllArtist(Pageable pageable) {
        Page<Artist> artists = artistRepository.findAll(pageable);
        return artists;
    }
}
