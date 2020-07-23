package com.enigma.spotify.specification;

import com.enigma.spotify.entity.Account;
import com.enigma.spotify.entity.Wallet;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;

public class WalletSpecification {

    public static Specification<Wallet> findAll(Wallet wallet) {
        return new Specification<Wallet>() {

            @Override
            public Predicate toPredicate(Root<Wallet> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                final Collection<Predicate> predicates = new ArrayList<>();
                if (wallet != null) {
                    if (!StringUtils.isEmpty(wallet.getBalance())) {
                        final Predicate balancePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("balance")), "%" + wallet.getBalance()+ "%");
                        predicates.add(balancePredicate);
                    }
                    if (!StringUtils.isEmpty(wallet.getOwner().getId())) {
                        final Predicate balancePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("account_id")), "%" + wallet.getOwner().getId()+ "%");
                        predicates.add(balancePredicate);
                    }
                }
                return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
