package com.enigma.spotify.repository;

import com.enigma.spotify.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProfileRepository extends JpaRepository<Profile,String>, JpaSpecificationExecutor<Profile> {

}
