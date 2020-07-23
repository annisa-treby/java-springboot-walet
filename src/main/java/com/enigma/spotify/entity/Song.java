package com.enigma.spotify.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mst_song")
@Setter
@EqualsAndHashCode
public class Song {
    @Id
    @GeneratedValue(generator = "song_uuid")
    @GenericGenerator(name = "song_uuid", strategy = "uuid")
    private String id;

    @Transient
    @JsonBackReference(value = "duration_reference")
    private String durationOfSong;

    private String title;

    private Integer releaseYear;
    private Double price;

    public String getArtis() {
        return artis;
    }

    public void setArtis(String artis) {
        this.artis = artis;
    }

    @Transient
    @JsonBackReference(value = "artist_reference")
    private String artis;

    @Transient
    @JsonBackReference(value = "genre_reference")
    private String genres;

    @Transient
    @JsonBackReference(value = "album_reference")
    private String albums;

    @JsonBackReference
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "album_id")
    @JsonIgnoreProperties("songList")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    @JsonIgnoreProperties("songList")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    @JsonIgnoreProperties("songList")
    private Genre genre;

    @OneToMany(mappedBy = "song")
    @JsonIgnore
    private List<Transaction> transactionList;

    @ManyToMany(mappedBy = "songs")
    @JsonIgnore
    private List<Playlist> playlists;

    public Song() {
    }

    public String getAlbums() {
        return albums;
    }

    public String getDurationOfSong() {
        Integer seconds = this.duration % 60;
        Integer minutes = (this.duration-seconds)/60;
        return minutes+"."+seconds;
    }

    public Integer getDuration() {

        return duration;
    }


    public String getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    public Integer getReleaseYear() {
        return releaseYear;
    }


    public Double getPrice() {
        return price;
    }


    public Album getAlbum() {
        return album;
    }


    public Artist getArtist() {
        return artist;
    }


    public Genre getGenre() {
        return genre;
    }


    public List<Transaction> getTransactionList() {
        return transactionList;
    }


    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public String getGenres() {
        return genres;
    }
}
