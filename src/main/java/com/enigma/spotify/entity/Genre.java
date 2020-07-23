package com.enigma.spotify.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "mst_genre")
@Getter
@Setter
public class Genre {

    @Id
    @GeneratedValue(generator = "genre_uuid")
    @GenericGenerator(name = "genre_uuid", strategy = "uuid")
    private String id;
    private String type;

    @OneToMany(mappedBy = "genre")
    private List<Song> songList;

    public Genre() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(id, genre.id) &&
                Objects.equals(type, genre.type) &&
                Objects.equals(songList, genre.songList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, songList);
    }
}
