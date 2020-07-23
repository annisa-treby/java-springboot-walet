package com.enigma.spotify.services;

import com.enigma.spotify.entity.History;
import com.enigma.spotify.entity.Wallet;
import com.enigma.spotify.enums.HistoryTypeEnum;
import com.enigma.spotify.exceptions.BalanceException;
import com.enigma.spotify.repository.WalletRepository;
import com.enigma.spotify.specification.WalletSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class WalletServiceDBImpl implements WalletService {

    @Autowired
    WalletRepository walletRepository;
    @Autowired
    HistoryService historyService;

    @Override
    public Wallet saveWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet getWallet(String id) {
        Wallet wallet = walletRepository.findById(id).get();
        return wallet;
    }

    @Override
    public Page<Wallet> getAllWallet(Pageable pageable) {
        return walletRepository.findAll(pageable);
    }

    @Override
    public void deleteWallet(String id) {
        Wallet wallet = getWallet(id);
        walletRepository.delete(wallet);
    }

    @Override
    public Page<Wallet> searchWallet(Wallet wallet, Pageable pageable) {
        Page<Wallet> wallets = walletRepository.findAll(WalletSpecification.findAll(wallet), pageable);
        return wallets;
    }

    @Override
    public Wallet topUp(Wallet wallet, Double topUpBalance) {
        History historyWallet = new History();
        wallet = getWallet(wallet.getId());
        if (wallet.getOwner().isActive() == true) {
            wallet.setBalance(wallet.getBalance() + topUpBalance);
            historyWallet.setAmount(topUpBalance);
            historyWallet.setType(HistoryTypeEnum.TOPUP);
            historyWallet.setTrxDate(new Timestamp(new Date().getTime()));
            historyWallet.setWallet(wallet);
            historyService.saveHistory(historyWallet);
            return walletRepository.save(wallet);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"your account is not active");
        }
    }

    @Override
    public Wallet withdrawal(Wallet wallet, Double withdrawalBalance) {
        History historyWallet = new History();
        wallet = getWallet(wallet.getId());
        if (wallet.getOwner().isActive() == true) {
            if (withdrawalBalance < wallet.getBalance()&& wallet.getBalance()>20000.0){
                wallet.setBalance(wallet.getBalance() - withdrawalBalance);
                historyWallet.setAmount(withdrawalBalance);
                historyWallet.setType(HistoryTypeEnum.WITHDRAWAL);
                historyWallet.setTrxDate(new Timestamp(new Date().getTime()));
                historyWallet.setWallet(wallet);
                historyService.saveHistory(historyWallet);
                return walletRepository.save(wallet);
            }else {
               throw new BalanceException();
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"your account is not active");
        }
    }
}
