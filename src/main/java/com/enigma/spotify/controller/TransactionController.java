package com.enigma.spotify.controller;

import com.enigma.spotify.entity.Transaction;
import com.enigma.spotify.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/song")
    public Transaction songTransaction(@RequestBody Transaction transaction){
        return transactionService.saveSongTransaction(transaction);
    }
    @PostMapping("/album")
    public List<Transaction> albumTransaction(@RequestBody Transaction transaction){
        return transactionService.saveAlbumTransaction(transaction);
    }

    @PostMapping("/update")
    public Transaction updateTransaction(@RequestBody Transaction transaction){
        return transactionService.updateTransaction(transaction);
    }

    @GetMapping("/transactions")
    public Page<Transaction> getAllTransaction(@RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return transactionService.getAllTransaction(pageable);
    }

    @GetMapping
    public Page<Transaction> searchTransaction(@RequestBody Transaction searchForm,@RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page,size);
    return transactionService.searchTransaction(searchForm, pageable);
    }

    @DeleteMapping
    public void deleteTransaction(@RequestBody Transaction transaction){
        transactionService.deleteTransaction(transaction.getId());
    }


}
