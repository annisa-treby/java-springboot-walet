package com.enigma.spotify.controller;

import com.enigma.spotify.entity.Profile;
import com.enigma.spotify.entity.Wallet;
import com.enigma.spotify.services.ProfileService;
import com.enigma.spotify.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    @Autowired
    WalletService walletService;

    @PostMapping
    public Wallet saveWallet(@RequestBody Wallet wallet){
        return walletService.saveWallet(wallet);
    }

    @PostMapping("/update")
    public Wallet updateWallet(@RequestBody Wallet wallet){
        return walletService.saveWallet(wallet);
    }

    @GetMapping("/walets")
    public Page<Wallet> getAllWallet(@RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return walletService.getAllWallet(pageable);
    }
    @GetMapping
    public Page<Wallet> searchWallet(@RequestBody Wallet searchForm, @RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return walletService.searchWallet(searchForm, pageable);
    }

    @DeleteMapping
    public void deleteWallet(@RequestBody Wallet wallet){
        walletService.deleteWallet(wallet.getId());
    }

    @GetMapping("{/id}")
    public Wallet getWalletById(@PathVariable String id){
        return walletService.getWallet(id);
    }

    @PostMapping("/topup")
    public Wallet topUp(@RequestBody Wallet wallet, @RequestParam Double amount){
        return walletService.topUp(wallet,amount);
    }

    @PostMapping("/withdrawal")
    public Wallet withdrawal(@RequestBody Wallet wallet, @RequestParam Double amount){
        return walletService.withdrawal(wallet,amount);
    }



}
