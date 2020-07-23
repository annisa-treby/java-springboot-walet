package com.enigma.spotify.services;

import com.enigma.spotify.entity.History;
import com.enigma.spotify.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HistoryService {
    public History saveHistory(History history);
    public History getHistory(String id);
    public Page<History> getAllHistory(Pageable pageable);
    public void deleteHistory(String id);
    public Page<History> searchHistory(History history, Pageable pageable);
}
