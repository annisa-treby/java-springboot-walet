package com.enigma.spotify.controller;

import com.enigma.spotify.entity.Genre;
import com.enigma.spotify.entity.Profile;
import com.enigma.spotify.services.GenreService;
import com.enigma.spotify.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @PostMapping
    public Profile saveProfile(@RequestBody Profile profile){
        return profileService.saveProfile(profile);
    }

    @PostMapping("/update")
    public Profile updateProfile(@RequestBody Profile profile){
        return profileService.saveProfile(profile);
    }

    @GetMapping("/profiles")
    public Page<Profile> getAllProfile(@RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return profileService.getAllProfile(pageable);
    }
    @GetMapping
    public Page<Profile> searchProfile(@RequestBody Profile searchForm, @RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return profileService.searchProfile(searchForm, pageable);
    }

    @DeleteMapping
    public void deleteProfile(@RequestBody Profile profile){
        profileService.deleteProfile(profile.getId());
    }

    @GetMapping("{/id}")
    public Profile getProfileById(@PathVariable String id){
        return profileService.getProfile(id);
    }
}
