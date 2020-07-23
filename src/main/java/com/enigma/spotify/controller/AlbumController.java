package com.enigma.spotify.controller;

import com.enigma.spotify.entity.Album;
import com.enigma.spotify.constan.PhotoConstant;
import com.enigma.spotify.services.AlbumService;
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
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @Autowired
    FileUtility fileUtility;

    @PostMapping
    public Album saveAlbum(@RequestPart MultipartFile file, @RequestPart String requestBody) throws IOException {
        return albumService.saveAlbumWithImage(file, requestBody);
    }

    @GetMapping
    public Page<Album> searchAlbum(@RequestBody Album album, @RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return albumService.searchAlbum(album, pageable);
    }

    @PutMapping("/update")
    public Album updateAlbum(@RequestPart MultipartFile file, @RequestPart String album) throws IOException {
        return albumService.saveAlbumWithImage(file, album);
    }

    @DeleteMapping
    public void deleteAlbum(@RequestBody Album album){
        albumService.deleteAlbum(album.getId());
    }

    @GetMapping("{/id}")
    public Album getAlbumById(@PathVariable String id){
        return albumService.getAlbumById(id);
    }

    @GetMapping("/albums")
    public Page<Album> getAllAlbum(@RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return albumService.getAllAlbum(pageable);
    }
    @GetMapping("/photo/{id}")
    public ResponseEntity<Resource> getAlbumPhoto(@PathVariable String id, HttpServletRequest request){
        Album album= albumService.getAlbumById(id);
        if (album==null)throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format(PhotoConstant.FILENOTFOUNDMESSAGE));
        Resource resource=fileUtility.read(album.getImage());
        String contentType;
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
