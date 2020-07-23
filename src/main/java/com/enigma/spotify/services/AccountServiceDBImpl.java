package com.enigma.spotify.services;

import com.enigma.spotify.entity.Account;
import com.enigma.spotify.entity.Profile;
import com.enigma.spotify.repository.AccountRepository;
import com.enigma.spotify.repository.ProfileRepository;
import com.enigma.spotify.specification.ProfileSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceDBImpl implements AccountService{

    @Autowired
    AccountRepository accountRepository;


    @Override
    public Account saveAccount(Account account) {

        System.out.println(account.isActive());
        return accountRepository.save(account);
    }

    @Override
    public Account getAccount(String id) {
        Account account = accountRepository.findById(id).get();
        return account;
    }

    @Override
    public Page<Account> getAllAccount(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }

}
