package com.enigma.spotify.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUtility {
    public String store(MultipartFile file, String destination)throws IOException;
    public Resource read(String filename);
    public String upload(String id,MultipartFile file) throws IOException;
}
