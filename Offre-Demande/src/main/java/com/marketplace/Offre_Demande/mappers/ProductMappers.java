package com.marketplace.Offre_Demande.mappers;

import org.springframework.stereotype.Component;

import com.marketplace.Offre_Demande.dto.ProductDtoRequest;
import com.marketplace.Offre_Demande.dto.ProductDtoResponse;
import com.marketplace.Offre_Demande.dto.ProductDtoResponseList;
import com.marketplace.Offre_Demande.entity.Product;

@Component
public class ProductMappers {
    public Product DtoToEntity(ProductDtoRequest productDtoRequest){
        Product product = new Product();
        product.setProductName(productDtoRequest.productName());
        product.setPrice(productDtoRequest.Price());

        return product;
    }
    
    public ProductDtoResponse EntityToDto(Product product){
        return new ProductDtoResponse(product.getProductId(), product.getProductName());
    }

    public ProductDtoResponseList EntityToDtoList(Product product)
    {
        return new ProductDtoResponseList(product.getProductId(),product.getProductName(), product.getPrice());
    }
}
