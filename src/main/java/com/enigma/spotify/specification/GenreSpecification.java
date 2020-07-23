package com.enigma.spotify.specification;

import com.enigma.spotify.entity.Genre;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;

public class GenreSpecification {
    public static Specification<Genre> findAll(Genre genre){
        return new Specification<Genre>() {
            @Override
            public Predicate toPredicate(Root<Genre> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
               final Collection<Predicate> predicates = new ArrayList<>();
               if(genre != null){
                   if(!StringUtils.isEmpty(genre.getType())){
                       final Predicate typePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("type")),"%"+genre.getType().toLowerCase()+"%");
                       predicates.add(typePredicate);
                   }
                   if ((!StringUtils.isEmpty(genre.getId()))){
                       final Predicate idPredicate = criteriaBuilder.equal(root.get("id"), genre.getId());
                       predicates.add(idPredicate);
                   }

               }
                return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
