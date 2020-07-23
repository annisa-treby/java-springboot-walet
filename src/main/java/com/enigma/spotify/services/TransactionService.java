package com.enigma.spotify.services;

import com.enigma.spotify.entity.Transaction;
import com.enigma.spotify.entity.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {
    public Transaction saveSongTransaction(Transaction transaction);
    public List<Transaction> saveAlbumTransaction(Transaction transaction);
    public Transaction updateTransaction(Transaction transaction);
    public Page<Transaction> getAllTransaction(Pageable pageable);
    public void deleteTransaction(String id);
    public Page<Transaction> searchTransaction(Transaction searchForm, Pageable pageable);
}
