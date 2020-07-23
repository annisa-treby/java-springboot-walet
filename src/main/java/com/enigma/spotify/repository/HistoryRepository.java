package com.enigma.spotify.repository;

import com.enigma.spotify.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HistoryRepository extends JpaRepository<History, String>, JpaSpecificationExecutor<History> {
}
