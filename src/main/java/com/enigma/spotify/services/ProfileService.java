package com.enigma.spotify.services;

import com.enigma.spotify.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfileService {
    public Profile saveProfile(Profile profile);
    public Profile getProfile(String id);
    public Page<Profile> getAllProfile(Pageable pageable);
    public void deleteProfile(String id);
    public Page<Profile> searchProfile(Profile profile, Pageable pageable);
}
