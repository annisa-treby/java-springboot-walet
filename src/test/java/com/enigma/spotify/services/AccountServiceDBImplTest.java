package com.enigma.spotify.services;

import com.enigma.spotify.entity.Account;
import com.enigma.spotify.entity.Profile;
import com.enigma.spotify.entity.Wallet;
import com.enigma.spotify.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceDBImplTest {

    @Autowired
    AccountService accountService;

@Autowired
AccountRepository accountRepository;

@BeforeEach
void cleanUp(){
    accountRepository.deleteAll();
}


    @Test
    void saveAccount_should_call_repository_Once_when_accountSaved() {
        Account account = new Account();
        account.setActive(Boolean.TRUE);

        accountService.saveAccount(account);

    }

    @Test
    void getAccount() {
    }

    @Test
    void getAllAccount() {
    }

    @Test
    void deleteAccount() {
    }
}