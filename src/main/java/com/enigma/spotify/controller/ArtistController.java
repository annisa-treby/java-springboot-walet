package com.enigma.spotify.controller;


import com.enigma.spotify.entity.Artist;
import com.enigma.spotify.constan.PhotoConstant;
import com.enigma.spotify.services.ArtistService;
import com.enigma.spotify.services.FileUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    ArtistService artistService;

    @Autowired
    FileUtility fileUtility;

    @PostMapping
    public Artist saveArtist(@RequestPart MultipartFile file, @RequestPart String requestBody) throws IOException {
        return artistService.saveArtistWithImage(file, requestBody);
    }

    @PutMapping("/update")
    public Artist updateArtist(@RequestPart MultipartFile file, @RequestPart String requestBody) throws IOException {
        return artistService.saveArtistWithImage(file, requestBody);
    }

    @GetMapping
    public Page<Artist> searchArtist(@RequestBody Artist artist, @RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return artistService.searchArtist(artist, pageable);
    }

    @DeleteMapping
    public void deleteArtist(@RequestBody Artist artist){
        artistService.deleteArtist(artist.getId());
    }

    @GetMapping("{/id}")
    public Artist getArtistById(@PathVariable String id){
        return artistService.getArtistById(id);
    }

    @GetMapping("/artists")
    public Page<Artist> getAllArtist(@RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return artistService.getAllArtist(pageable);
    }

    @GetMapping("/photo/{id}")
    public ResponseEntity<Resource> getArtistPhoto(@PathVariable String id, HttpServletRequest request){
        Artist artist= artistService.getArtistById(id);
        if (artist==null)throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format(PhotoConstant.FILENOTFOUNDMESSAGE));
        Resource resource=fileUtility.read(artist.getPhoto());
        String contentType=null;
        try{
            contentType=request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch (IOException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format(PhotoConstant.FILENOTFOUNDMESSAGE));
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+resource.getFilename()+"\"")
                .body(resource);
    }
}
