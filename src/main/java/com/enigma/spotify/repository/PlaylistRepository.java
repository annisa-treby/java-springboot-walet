package com.enigma.spotify.repository;

import com.enigma.spotify.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PlaylistRepository extends JpaRepository<Playlist, String>, JpaSpecificationExecutor<Playlist> {
}
