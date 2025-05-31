package com.example.Image.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import com.example.Image.entity.Image;
import com.example.Image.repository.ImageRepository;
import com.example.Image.service.ImageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
    private final ImageRepository imageRepository;
    
    @PostMapping
    public ResponseEntity<Image> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String storedName = imageService.storeFile(file);
        Image fichier = new Image();
        fichier.setName(storedName);
        return ResponseEntity.ok(imageRepository.save(fichier));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) throws Exception {
        Image fichier = imageRepository.findById(id)
            .orElseThrow(() -> new Exception("Image non trouvé"));
        
        Path filePath = imageService.loadFile(fichier.getName());
        Resource resource = new UrlResource(filePath.toUri());
        
        return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=\"" + fichier.getName() + "\"")
            .body(resource);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long id) throws Exception {
        Image fichier = imageRepository.findById(id)
            .orElseThrow(() -> new Exception("Image non trouvé"));
        
        imageService.deleteFile(fichier.getName());
        imageRepository.delete(fichier);
        
        return ResponseEntity.noContent().build();
    }

   @PutMapping("/{id}")
    public ResponseEntity<Image> updateFile(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile newFile) throws IOException {
        
        // Vérification du fichier reçu
        if (newFile.isEmpty()) {
            throw new IllegalArgumentException("Image vide");
        }

        Image existingFile = imageRepository.findById(id)
            .orElseThrow(() -> new FileNotFoundException("Image non trouvé"));

        // Chemin complet du fichier existant
        Path filePath = Paths.get(imageService.getStorageDirectory(), existingFile.getName());
        
        // Vérification sécurité
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException("Image physique introuvable");
        }

        // Écraser le fichier existant
        try (InputStream inputStream = newFile.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }
        
        return ResponseEntity.ok(imageRepository.save(existingFile));
    }
}
