package com.enigma.spotify.services;

import com.enigma.spotify.entity.Song;
import com.enigma.spotify.repository.SongRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class SongServiceDVImplTest {

    @Autowired
    SongService songService;

    @MockBean
    SongRepository songRepository;

    @Test
    void saveSong_Should_Call_songRepository_Once_When_songSaved() {
        Song song=new Song();
        song.setTitle("Imagination");
        song.setDuration(200);
        song.setPrice(10000.0);

        songService.saveSong(song);

        Mockito.verify(songRepository, Mockito.times(1)).save(song);
    }

    @Test
    void saveGenre_once_Should_saved_Once_in_DB(){
        Song song=new Song();
        song.setTitle("Imagination");
        song.setDuration(200);
        song.setPrice(10000.0);

        songService.saveSong(song);
        List<Song> songs = new ArrayList<>();
        songs.add(song);

        Mockito.when(songRepository.findAll()).thenReturn(songs);
    }

    @Test
    void save_2_genre_Should_saved_2_in_DB(){
        Song song=new Song();
        song.setTitle("Imagination");
        song.setDuration(200);
        song.setPrice(10000.0);

        Song song1=new Song();
        song1.setTitle("Imagination");
        song1.setDuration(200);
        song1.setPrice(10000.0);

        songService.saveSong(song);
        songService.saveSong(song1);
        List<Song> songs = new ArrayList<>();
        songs.add(song);
        songs.add(song1);

        Mockito.when(songRepository.findAll()).thenReturn(songs);
    }

    @Test
    void deleteSong_should_decrease_totalOfSong_inDB() {
        Song song=new Song();
        song.setTitle("Imagination");
        song.setDuration(200);
        song.setPrice(10000.0);

        songRepository.save(song);
        songService.deleteSong(song.getId());

        assertEquals(0, songRepository.findAll().size());
    }

//    @Test
//    void getSongById() {
//        Song song= new Song();
//        song.setTitle("Imagination");
//        song.setDuration(230);
//        song.setDurationOfSong(""+song.getDurationOfSong());
//        song.setPrice(10000.0);
//
//        Song song1=new Song();
//        song1.setTitle("nervous");
//        song1.setPrice(11000.0);
//        song1.setDuration(210);
//
//        songRepository.save(song);
//        songRepository.save(song1);
//
//        assertEquals(song, songService.getSongById(song.getId()));
//    }

    @Test
    void getAllSong() {
    }
}