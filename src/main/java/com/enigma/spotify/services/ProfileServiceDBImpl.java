package com.enigma.spotify.services;

import com.enigma.spotify.entity.Genre;
import com.enigma.spotify.entity.Profile;
import com.enigma.spotify.repository.ProfileRepository;
import com.enigma.spotify.specification.GenreSpecification;
import com.enigma.spotify.specification.ProfileSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProfileServiceDBImpl implements ProfileService {

    @Autowired
    ProfileRepository profileRepository;
    @Override
    public Profile saveProfile(Profile profile) {
    return profileRepository.save(profile);
    }

    @Override
    public Profile getProfile(String id) {
        Profile profile = profileRepository.findById(id).get();
        return profile;
    }

    @Override
    public Page<Profile> getAllProfile(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }

    @Override
    public void deleteProfile(String id) {
    Profile profile = getProfile(id);
    profileRepository.delete(profile);
    }

    @Override
    public Page<Profile> searchProfile(Profile profile, Pageable pageable) {
        Page<Profile> profiles = profileRepository.findAll(ProfileSpecification.findAll(profile), pageable);
        return profiles;
    }
}
