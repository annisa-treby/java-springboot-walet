package com.enigma.spotify.services;

import com.enigma.spotify.constan.PhotoConstant;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUtilityImpl implements FileUtility{
    private final Path storageLocation = Paths.get("uploads").toAbsolutePath().normalize();

    @Override
    public String store(MultipartFile file, String destination) throws IOException {
        Path target = storageLocation.resolve(destination);
        Files.copy(file.getInputStream(),target, StandardCopyOption.REPLACE_EXISTING);
        return destination;
    }

    @Override
    public Resource read(String filename) {
        String exceptionMessage=String.format(PhotoConstant.FILENOTFOUNDMESSAGE);
        try{
            Path file =storageLocation.resolve("artist/photo/"+filename+".jpg").normalize();
       //    Resource resource=new UrlResource(file.toUri());
            Resource resource=new UrlResource("artist/photo/"+filename+".jpg");
            System.out.println(file.toUri().toString());
            if (!resource.exists()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,exceptionMessage);
            return resource;
        }catch (MalformedURLException exp){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,exceptionMessage);
        }
    }

    @Override
    public String upload(String id, MultipartFile file) throws IOException {
        File upload=new File(String.format(PhotoConstant.PHOTOSOURCE,id));
        file.transferTo(upload);
        return "/"+id+".jpg";
    }
}
