package com.enigma.spotify.services;

import com.enigma.spotify.entity.Transaction;
import com.enigma.spotify.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionServiceDBImplTest {

    @MockBean
    TransactionRepository transactionRepository;

    @Autowired
    TransactionService transactionService;

    @Test
    void saveSongTransaction_should_call_transactionRepository_once_when_songSaved() {
        Transaction transaction = new Transaction();
        transaction.setAmount(20000.0);

        transactionService.saveSongTransaction(transaction);
        Mockito.verify(transactionRepository, Mockito.times(1)).save(transaction);
    }

    @Test
    void saveAlbumTransaction() {
    }

    @Test
    void updateTransaction() {
    }

    @Test
    void getAllTransaction() {
    }

    @Test
    void deleteTransaction() {
    }
}