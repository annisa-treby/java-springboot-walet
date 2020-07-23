package com.enigma.spotify.specification;

import com.enigma.spotify.entity.History;
import com.enigma.spotify.entity.Playlist;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;

public class PlaylistSpecification {
    public static Specification<Playlist> findAll(Playlist playlist) {
        return new Specification<Playlist>() {

            @Override
            public Predicate toPredicate(Root<Playlist> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                final Collection<Predicate> predicates = new ArrayList<>();
                if (playlist != null) {
                    if (!StringUtils.isEmpty(playlist.getName())) {
                        final Predicate playlistNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + playlist.getName() + "%");
                        predicates.add(playlistNamePredicate);
                    }
                }
                return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}

