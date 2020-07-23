package com.enigma.spotify.services;

import com.enigma.spotify.entity.Account;
import com.enigma.spotify.entity.History;
import com.enigma.spotify.entity.Profile;
import com.enigma.spotify.repository.HistoryRepository;
import com.enigma.spotify.specification.HistorySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceDBImpl implements HistoryService{

    @Autowired
    HistoryRepository historyRepository;
    @Override
    public History saveHistory(History history) {
        return historyRepository.save(history);
    }

    @Override
    public History getHistory(String id) {
        History history = historyRepository.findById(id).get();
        return history;
    }

    @Override
    public Page<History> getAllHistory(Pageable pageable) {
        return historyRepository.findAll(pageable);
    }

    @Override
    public void deleteHistory(String id) {
        History history = getHistory(id);
        historyRepository.delete(history);
    }

    @Override
    public Page<History> searchHistory(History history, Pageable pageable) {
        Page<History> histories = historyRepository.findAll(HistorySpecification.findAll(history), pageable);
        return histories;
    }
}
