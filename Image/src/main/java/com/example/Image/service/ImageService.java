package com.example.Image.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {
    
    @Value("${app.file.storage-dir:./file-storage}")
    private String storageDir;
    
    public String storeFile(MultipartFile file) throws IOException {
        // Génération du nom unique avec extension
        String extension = file.getOriginalFilename() != null ? 
            file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")) : "";
        String storedFileName = UUID.randomUUID().toString() + extension;
        
        // Création du répertoire si inexistant
        Path storagePath = Paths.get(storageDir).toAbsolutePath().normalize();
        Files.createDirectories(storagePath);
        
        // Sauvegarde du fichier
        Path targetLocation = storagePath.resolve(storedFileName);
        Files.copy(file.getInputStream(), targetLocation);
        
        return storedFileName;
    }
    
    public Path loadFile(String filename) {
        return Paths.get(storageDir).resolve(filename).normalize();
    }
    
    public void deleteFile(String filename) throws IOException {
        Path filePath = Paths.get(storageDir).resolve(filename).normalize();
        Files.deleteIfExists(filePath);
    }

    public String updateFile(String oldFilename, MultipartFile newFile) throws IOException {
        // Supprimer l'ancien fichier
        deleteFile(oldFilename);
        
        // Stocker le nouveau fichier
        return storeFile(newFile);
    }

    public void replaceFile(String filename, MultipartFile newFile) throws IOException {
        Path filePath = Paths.get(storageDir, filename).normalize();
        
        // Validation sécurité
        if (!filePath.startsWith(Paths.get(storageDir).normalize())) {
            throw new SecurityException("Chemin de fichier non autorisé");
        }
        
        Files.copy(newFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    }

    public String getStorageDirectory() {
        return this.storageDir;
    }
}
