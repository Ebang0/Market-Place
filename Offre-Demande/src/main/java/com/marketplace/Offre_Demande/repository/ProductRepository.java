package com.marketplace.Offre_Demande.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketplace.Offre_Demande.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
   
}
