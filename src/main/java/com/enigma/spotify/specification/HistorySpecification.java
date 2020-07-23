package com.enigma.spotify.specification;

import com.enigma.spotify.entity.History;
import com.enigma.spotify.entity.Wallet;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;

public class HistorySpecification {
    public static Specification<History> findAll(History history) {
        return new Specification<History>() {

            @Override
            public Predicate toPredicate(Root<History> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                final Collection<Predicate> predicates = new ArrayList<>();
                if (history != null) {
                    if (!StringUtils.isEmpty(history.getTrxDate())) {
                        final Predicate historyDatePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("trxDate")), "%" + history.getTrxDate() + "%");
                        predicates.add(historyDatePredicate);
                    }
                }
                return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

}
