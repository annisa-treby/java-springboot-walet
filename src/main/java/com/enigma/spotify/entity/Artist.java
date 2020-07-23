package com.enigma.spotify.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import com.enigma.spotify.enums.GenderEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mst_artist")
@Getter @Setter
@EqualsAndHashCode
public class Artist {

    @Id
    @GeneratedValue(generator = "artist_uuid")
    @GenericGenerator(name = "artist_uuid", strategy = "uuid")
    private String id;
    private String name;
    private Integer debutYear;
    private String biography;
    private String photo;
///
    @Transient
    @JsonBackReference
    private String song;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @OneToMany(mappedBy = "artist")
    @JsonIgnore
    private List<Song> songList;

    public Artist() {
    }
}
