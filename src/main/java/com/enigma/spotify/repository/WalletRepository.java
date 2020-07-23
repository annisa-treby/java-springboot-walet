package com.enigma.spotify.repository;

import com.enigma.spotify.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WalletRepository extends JpaRepository<Wallet,String>, JpaSpecificationExecutor<Wallet> {
}
