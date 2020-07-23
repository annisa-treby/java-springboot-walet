package com.enigma.spotify.services;

import com.enigma.spotify.entity.*;
import com.enigma.spotify.enums.HistoryTypeEnum;
import com.enigma.spotify.exceptions.BalanceException;
import com.enigma.spotify.repository.TransactionRepository;
import com.enigma.spotify.specification.TransactionSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceDBImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    WalletService walletServiceDB;

    @Autowired
    AccountService accountServiceDb;

    @Autowired
    SongService songServiceDB;

    @Autowired
    HistoryService historyServiceDB;

    @Autowired
    AlbumService albumServiceDB;


    @Override
    public Transaction saveSongTransaction(Transaction transaction) {
        Wallet wallet = walletServiceDB.getWallet(transaction.getWallet().getId());
        Song song = songServiceDB.getSongById(transaction.getSong().getId());
        Account account = wallet.getOwner();
        History history = new History();
        if (account.isActive()) {
            if (wallet.getBalance()>= song.getPrice()) {
                transaction.setSong(song);
                transaction.setWallet(wallet);
                transaction = transactionRepository.save(transaction);
                Double newBalance = wallet.getBalance()-song.getPrice();
                wallet.setBalance(newBalance);
                walletServiceDB.saveWallet(wallet);
                history.setTrxDate(transaction.getTrxDate());
                history.setType(HistoryTypeEnum.PAYMENT);
                history.setAmount(song.getPrice());
                history.setWallet(wallet);
                historyServiceDB.saveHistory(history);

            }
        }

        return transaction;
    }

    @Override
    public List<Transaction> saveAlbumTransaction(Transaction transaction) {
        Wallet wallet = walletServiceDB.getWallet(transaction.getWallet().getId());
        Album album = albumServiceDB.getAlbumById(transaction.getAlbum().getId());
        Account account = wallet.getOwner();
        List<Song> songs = album.getSongList();
        List<Transaction> transactions = new ArrayList<>();

        Double sum = 0.0;
        if (account.isActive()) {
            if(album.getSongList() != null) {
            for (Song song : songs) {
                sum += song.getPrice() * (1 - album.getDiscount());
            }
                if (wallet.getBalance() >= sum) {
                    for (Song song : songs) {
                        Transaction transaction1 = new Transaction();
                        transaction1.setSong(song);
                        transaction1.setTrxDate(transaction.getTrxDate());
                        transaction1.setAmount(song.getPrice()*(1-album.getDiscount()));
                        transaction1.setAlbumDiscount(album.getDiscount());
                        transactionRepository.save(transaction1);
                        History history = new History();
                        history.setTrxDate(transaction1.getTrxDate());
                        history.setAmount(transaction1.getAmount());
                        history.setType(HistoryTypeEnum.PAYMENT);
                        history.setWallet(wallet);
                        historyServiceDB.saveHistory(history);
                        transactions.add(transaction1);
                        Double newBalance = wallet.getBalance() - (song.getPrice()*(1-album.getDiscount()));
                        wallet.setBalance(newBalance);
                        transaction.setWallet(wallet);
                        walletServiceDB.saveWallet(wallet);

                    }
                }
            } else {
                throw new BalanceException();
            }
        }
        return transactions;
    }


    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return saveSongTransaction(transaction);
    }

    @Override
    public Page<Transaction> getAllTransaction(Pageable pageable) {
        Page<Transaction> transactions = transactionRepository.findAll(pageable);
        return transactions;
    }

    @Override
    public void deleteTransaction(String id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public Page<Transaction> searchTransaction(Transaction searchForm, Pageable pageable) {
        Page<Transaction> transactions = transactionRepository.findAll(TransactionSpecification.findAll(searchForm), pageable);
        return transactions;
    }

}
