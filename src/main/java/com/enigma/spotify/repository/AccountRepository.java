package com.enigma.spotify.repository;

import com.enigma.spotify.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccountRepository extends JpaRepository<Account,String>, JpaSpecificationExecutor<Account> {

}
