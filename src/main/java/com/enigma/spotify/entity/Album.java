package com.enigma.spotify.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mst_album")
@Getter
@Setter
@EqualsAndHashCode
public class Album {
//g
    @Id
    @GeneratedValue(generator = "album_uuid")
    @GenericGenerator(name = "album_uuid", strategy = "uuid")
    private String id;
    private String title;
    private String description;
    private Double discount;
    private String image;

    @OneToMany(mappedBy = "album")
    @JsonIgnoreProperties("album")
    private List<Song> songList;

    public Album() {
    }
}
