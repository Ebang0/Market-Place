package com.marketplace.Offre_Demande.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.marketplace.Offre_Demande.dto.ProductDtoRequest;
import com.marketplace.Offre_Demande.dto.ProductDtoResponse;
import com.marketplace.Offre_Demande.entity.Product;
import com.marketplace.Offre_Demande.mappers.ProductMappers;
import com.marketplace.Offre_Demande.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMappers productMappers;

    
    public void createPoduct(ProductDtoRequest request) {
        
        if(request == null)
            throw new RuntimeException("Pas d'informtion");

        Product product = new Product();
        product.setProductName(request.productName());
        product.setPrice(request.Price()); 
        
        
        productRepository.save(product);
    }

    public void updateProduct(Long productId, ProductDtoRequest productDtoRequest){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Offer not found"));

                product.setProductName(productDtoRequest.productName());
                product.setPrice(productDtoRequest.Price());
    }

    public void deleteProduct(Long id) {
        if(id == null)
            throw new RuntimeException("Pas d'informtion");

        Product product = productRepository.findById(id).get();

        if(product == null )
            throw new RuntimeException("Pas de produit");

        productRepository.delete(product);
    }

    public List<ProductDtoResponse> getAllProducts(){

        return productRepository.findAll().stream()
        .map(productMappers::EntityToDto)
        .collect(Collectors.toList());
    }
}
