package com.enigma.spotify.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mst_playlist")
@Getter
@Setter
@EqualsAndHashCode
public class Playlist {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    private String name;

    private boolean publik;

    @ManyToMany
    @JsonIgnoreProperties("playlists")
    @JoinTable(name = "playlist_has_songs",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id"))
    private List<Song> songs;

    @ManyToOne
    @JsonIgnoreProperties("playlists")
    @JoinColumn(name = "account_id")
    private Account account;

    public Playlist() {
    }

    public Playlist(String name, boolean publik, List<Song> songs, Account account) {
        this.name = name;
        this.publik = publik;
        this.songs = songs;
        this.account = account;
    }


}
