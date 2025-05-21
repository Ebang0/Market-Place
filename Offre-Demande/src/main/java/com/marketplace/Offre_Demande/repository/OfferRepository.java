package com.marketplace.Offre_Demande.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketplace.Offre_Demande.entity.Offer;
import com.marketplace.Offre_Demande.entity.Product;

public interface OfferRepository extends JpaRepository<Offer,Long> {
    List<Offer> findByProduct(Product product);
}
