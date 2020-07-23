package com.enigma.spotify.services;

import com.enigma.spotify.entity.Album;
import com.enigma.spotify.repository.AlbumRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AlbumServiceDBImplTest {

    @MockBean
    AlbumRepository albumRepository;

    @Autowired
    AlbumService albumService;



    @Test
    void saveAlbum() {
    }

    @Test
    void saveAlbumWithImage() {
    }

    @Test
    void searchAlbum() {
    }

    @Test
    void getAlbumById() {
        Album album = new Album();
        album.setTitle("illuminate");
        album.setDescription("Amazing album");
        album.setDiscount(0.05);

        albumRepository.save(album);

        assertEquals(1, albumService.getAllAlbum(PageRequest.of(0,2)).getTotalElements());
    }

    @Test
    void deleteAlbum() {
        Album album = new Album();
        album.setTitle("illuminate");
        album.setDescription("Amazing album");
        album.setDiscount(0.05);

        albumRepository.save(album);
        albumService.deleteAlbum(album.getId());

        assertEquals(0,albumRepository.findAll().size());
    }
}