package com.enigma.spotify.specification;

import com.enigma.spotify.entity.Album;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;

public class AlbumSpecification {
    public static Specification<Album> findAll(Album album){

        return new Specification<Album>() {
            @Override
            public Predicate toPredicate(Root<Album> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                final Collection<Predicate> predicates = new ArrayList<>();
                if(album != null){
                    if(!StringUtils.isEmpty(album.getTitle())){
                        final Predicate titlePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%"+album.getTitle().toLowerCase()+"%");
                        predicates.add(titlePredicate);
                    }
                    if(!StringUtils.isEmpty(album.getDescription())){
                        final  Predicate descriptionPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%"+album.getDescription().toLowerCase()+"%");
                        predicates.add(descriptionPredicate);
                    }
                    if(!StringUtils.isEmpty(album.getId())){
                        final Predicate idPredicate = criteriaBuilder.equal(root.get("id"),album.getId());
                        predicates.add(idPredicate);
                    }
                }
                return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
