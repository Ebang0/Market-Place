package com.marketplace.Offre_Demande.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketplace.Offre_Demande.entity.Product;
import com.marketplace.Offre_Demande.entity.Request;
import com.marketplace.Offre_Demande.entity.Request.Status;

public interface RequestRepository extends JpaRepository<Request,Long>{
    List<Request> findByProduct(Product product);
    List<Request> findByValidate(Boolean validate);
    List<Request> findByStatus(Status status);
    List<Request> findByIdProducteur(Long idProducteur);
}
