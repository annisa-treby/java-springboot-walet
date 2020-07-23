package com.enigma.spotify.services;

import com.enigma.spotify.entity.Profile;
import com.enigma.spotify.entity.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WalletService {
    public Wallet saveWallet(Wallet wallet);
    public Wallet getWallet(String id);
    public Page<Wallet> getAllWallet(Pageable pageable);
    public void deleteWallet(String id);
    public Page<Wallet> searchWallet(Wallet wallet, Pageable pageable);
    public Wallet topUp(Wallet wallet, Double topUpBalance);
    public Wallet withdrawal(Wallet wallet, Double withdrawalBalance);

}
