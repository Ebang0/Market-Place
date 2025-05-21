package com.marketplace.Offre_Demande.mappers;

import org.springframework.stereotype.Service;

import com.marketplace.Offre_Demande.dto.OfferDtoRequest;
import com.marketplace.Offre_Demande.dto.OfferDtoResponse;
import com.marketplace.Offre_Demande.entity.Offer;
import com.marketplace.Offre_Demande.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OfferMappers {

  private final ProductRepository productRepository;
  
  public Offer DtoToEntitiy(OfferDtoRequest offertDtoRequest)
  {
    Offer offer = new Offer();
    offer.setQuantity(offertDtoRequest.quantity());
    offer.setDescription(offertDtoRequest.description());
    offer.setProduct(productRepository.findById(offertDtoRequest.idPoduct()).get());
    offer.setImage(offertDtoRequest.image());
    return offer;
  }
  
  public OfferDtoResponse EntityToDto(Offer offer){

    return new OfferDtoResponse(productRepository.findById(offer.getProduct().getProductId()).get().getProductName(),offer.getOfferId(), offer.getQuantity(), offer.getPrice(), offer.getDate(),offer.getDescription(),offer.getImage());
  }
  
}
