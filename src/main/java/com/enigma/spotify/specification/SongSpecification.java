package com.enigma.spotify.specification;

import com.enigma.spotify.entity.Album;
import com.enigma.spotify.entity.Artist;
import com.enigma.spotify.entity.Genre;
import com.enigma.spotify.entity.Song;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;

public class SongSpecification {
    public static Specification<Song> findAll(Song song){
        return new Specification<Song>() {
            @Override
            public Predicate toPredicate(Root<Song> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Join<Song, Artist> songArtistJoin = root.join("artist");
                Join<Song, Genre> songGenreJoin = root.join("genre");
                Join<Song, Album> songAlbumJoin = root.join("album");
                final Collection<Predicate> predicates = new ArrayList<>();
                if(song!=null){
                    if(!StringUtils.isEmpty(song.getTitle())){
                        final Predicate titlePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("title")),"%"+song.getTitle().toLowerCase()+"%");
                        predicates.add(titlePredicate);
                    }
                    if(!StringUtils.isEmpty(song.getReleaseYear())){
                        final Predicate releaseYearPredicate = criteriaBuilder.equal(root.get("releaseYear"),song.getReleaseYear());
                        predicates.add(releaseYearPredicate);
                    }
                    if(!StringUtils.isEmpty(song.getArtis())){
                        final Predicate artistPredicate = criteriaBuilder.like(criteriaBuilder.lower(songArtistJoin.get("name")),"%"+song.getArtis().toLowerCase()+"%");
                        predicates.add(artistPredicate);
                    }
                    if(!StringUtils.isEmpty(song.getGenres())){
                        final Predicate genrePredicate = criteriaBuilder.like(criteriaBuilder.lower(songGenreJoin.get("type")), "%"+song.getGenres().toLowerCase()+"%");
                        predicates.add(genrePredicate);
                    }
                    if(!StringUtils.isEmpty(song.getAlbums())){
                        final Predicate albumPredicate = criteriaBuilder.like(criteriaBuilder.lower(songAlbumJoin.get("title")),"%"+song.getAlbums().toLowerCase()+"%");
                        predicates.add((albumPredicate));
                    }
                }
                return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
