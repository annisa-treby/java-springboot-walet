package com.enigma.spotify.specification;

import com.enigma.spotify.entity.Song;
import com.enigma.spotify.entity.Transaction;
import com.enigma.spotify.entity.Wallet;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;

public class TransactionSpecification {


    public static Specification<Transaction> findAll(Transaction transaction) {
        return new Specification<Transaction>() {

            @Override
            public Predicate toPredicate(Root<Transaction> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Join<Transaction, Song> transactionSongJoin = root.join("song");
                final Collection<Predicate> predicates = new ArrayList<>();
                if (transaction != null) {
                    if (!StringUtils.isEmpty(transaction.getSongs())){
                        final Predicate songPredicate = criteriaBuilder.like(transactionSongJoin.get("title"),"%"+transaction.getSongs().toLowerCase()+"%");
                        predicates.add(songPredicate);
                    }
                }
                return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
