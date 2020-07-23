package com.enigma.spotify.controller;

import com.enigma.spotify.repository.ArtistRepository;
import com.enigma.spotify.services.ArtistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ArtistControllerTest {

    @MockBean
    ArtistService artistServices;

    @MockBean
    ArtistRepository artistRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    void saveArtist() {

    }

    @Test
    void updateArtist() {
    }

    @Test
    void searchArtist() {
    }

    @Test
    void deleteArtist() {

    }

    @Test
    void getArtistById() {
    }

    @Test
    void getAllArtist() {
    }

    @Test
    void getArtistPhoto() {
    }
}