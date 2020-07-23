package com.enigma.spotify.specification;

import com.enigma.spotify.entity.Artist;
import com.enigma.spotify.entity.Profile;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;

public class ProfileSpecification {

    public static Specification<Profile> findAll(Profile profile) {

        return new Specification<Profile>() {
            @Override
            public Predicate toPredicate(Root<Profile> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                final Collection<Predicate> predicates = new ArrayList<>();
                if(profile!= null){
                    if(!StringUtils.isEmpty(profile.getFirstName())){
                        final Predicate firstNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")),"%"+profile.getFirstName().toLowerCase()+"%");
                        predicates.add(firstNamePredicate);
                    }
                    if(!StringUtils.isEmpty(profile.getMiddleName())){
                        final Predicate middleNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("middleName")),"%"+profile.getMiddleName().toLowerCase()+"%");
                        predicates.add(middleNamePredicate);
                    }
                    if(!StringUtils.isEmpty(profile.getLastName())){
                        final Predicate middleNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("middleName")),"%"+profile.getMiddleName().toLowerCase()+"%");
                        predicates.add(middleNamePredicate);
                    }
                }
                return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
