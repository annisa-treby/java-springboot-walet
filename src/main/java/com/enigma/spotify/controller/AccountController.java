package com.enigma.spotify.controller;

import com.enigma.spotify.entity.Account;
import com.enigma.spotify.entity.Profile;
import com.enigma.spotify.services.AccountService;
import com.enigma.spotify.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping
    public Account saveAccount(@RequestBody Account account){
        return accountService.saveAccount(account);
    }

    @PostMapping("/update")
    public Account updateAccount(@RequestBody Account account){
        return accountService.saveAccount(account);
    }

    @GetMapping("/accounts")
    public Page<Account> getAllAccount(@RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return accountService.getAllAccount(pageable);
    }

    @DeleteMapping
    public void deleteAccount(@RequestBody Account account){
        accountService.deleteAccount(account.getId());
    }

    @GetMapping("{/id}")
    public Account getAccountById(@PathVariable String id){
        return accountService.getAccount(id);
    }
}

