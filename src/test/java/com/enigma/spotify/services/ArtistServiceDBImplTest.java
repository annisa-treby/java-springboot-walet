package com.enigma.spotify.services;

import com.enigma.spotify.entity.Artist;
import com.enigma.spotify.enums.GenderEnum;
import com.enigma.spotify.repository.ArtistRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ArtistServiceDBImplTest {
    

    @MockBean
    ArtistRepository artistRepository;

    @Autowired
    ArtistService artistService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    FileUtility fileUtility;

    @Test
    void saveArtist_should_call_artistRepository_once_when_artistSaved() throws IOException {
//        Artist artist = new Artist();
//        MultipartFile multipartFile = null;
//        artist.setName("Shan mendes");
//        artist.setBiography("He is the best musician");
//        artist.setDebutYear(2013);
//        artist.setGender(GenderEnum.MALE);
//        String requestBody = artist.toString();
//        Artist artist1 = artistRepository.save(objectMapper.readValue(requestBody, Artist.class));
//
//        artist.setPhoto(fileUtility.upload(artist.getId(), multipartFile));
//
//        artistService.saveArtistWithImage(multipartFile, requestBody);
//        Mockito.verify(artistRepository, Mockito.times(1)).save(artist1);
    }

    @Test
    void searchArtist() {

    }

    @Test
    void getArtistById() {
    }

    @Test
    void deleteArtist() {
        Artist artist = new Artist();
        artist.setName("Shan mendes");
        artist.setBiography("He is the best musician");
        artist.setDebutYear(2013);
        artist.setGender(GenderEnum.MALE);

        artistRepository.save(artist);
        artistService.deleteArtist(artist.getId());

        assertEquals(0, artistRepository.findAll().size());
    }

    @Test
    void getAllArtist() {
    }
}