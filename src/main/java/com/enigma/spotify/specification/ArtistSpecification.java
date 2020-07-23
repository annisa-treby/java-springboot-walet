package com.enigma.spotify.specification;

import com.enigma.spotify.entity.Artist;
import com.enigma.spotify.entity.Song;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;

public class ArtistSpecification {
    public static Specification<Artist> findAll(Artist artist) {

        return new Specification<Artist>() {
            @Override
            public Predicate toPredicate(Root<Artist> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Join<Artist, Song> artistSongJoin= root.join("songList");
                final Collection<Predicate> predicates = new ArrayList<>();
                if(artist != null){
                    if(!StringUtils.isEmpty(artist.getName())){
                        final Predicate artistNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),"%"+artist.getName().toLowerCase()+"%");
                        predicates.add(artistNamePredicate);
                    }
                    if(!StringUtils.isEmpty(artist.getDebutYear())){
                        final Predicate debutYearPredicate = criteriaBuilder.equal(root.get("debutYear"),artist.getDebutYear());
                        predicates.add(debutYearPredicate);
                    }
                    if(!StringUtils.isEmpty(artist.getBiography())){
                        final Predicate biographyPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("biography")),"%"+artist.getBiography().toLowerCase()+"%");
                        predicates.add(biographyPredicate);
                    }
                    if(!StringUtils.isEmpty(artist.getSong())){
                        final Predicate songPredicate = criteriaBuilder.like(criteriaBuilder.lower(artistSongJoin.get("title")), "%"+artist.getSong().toLowerCase()+"%");
                        predicates.add(songPredicate);
                    }
                }
                return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
