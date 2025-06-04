package com.marketplace.Offre_Demande.controller;

import com.marketplace.Offre_Demande.dto.ProductDtoRequest;
import com.marketplace.Offre_Demande.dto.ProductDtoResponse;
import com.marketplace.Offre_Demande.service.ProductService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @CrossOrigin
    @PostMapping("/save")
    public ResponseEntity<?> createProduct(@RequestBody ProductDtoRequest dto) {
        productService.createPoduct(dto);
        return ResponseEntity.ok().body("Enrgistrer");
    }

    @CrossOrigin
    @GetMapping("/get/all")
    public ResponseEntity<List<ProductDtoResponse>> getAllProducts() {

        return ResponseEntity.ok(productService.getAllProducts());
    }

    @CrossOrigin
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDtoRequest dto) {
        productService.updateProduct(id, dto);
        return ResponseEntity.ok().body("Mise ajout terminer");
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().body("Produit supprimer");
    }

    @CrossOrigin
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id)
    {
        return ResponseEntity.ok(productService.getProduct(id));
    }
}
