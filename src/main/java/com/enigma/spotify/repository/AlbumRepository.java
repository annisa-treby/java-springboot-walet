package com.enigma.spotify.repository;

import com.enigma.spotify.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AlbumRepository extends JpaRepository<Album, String>, JpaSpecificationExecutor<Album> {
}
