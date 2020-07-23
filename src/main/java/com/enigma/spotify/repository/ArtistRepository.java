package com.enigma.spotify.repository;

import com.enigma.spotify.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaSpecificationExecutor<Artist>, JpaRepository<Artist, String> {
}
