package com.marketplace.Offre_Demande.service;

import com.marketplace.Offre_Demande.dto.OfferDtoRequest;
import com.marketplace.Offre_Demande.dto.OfferDtoResponse;
import com.marketplace.Offre_Demande.entity.Offer;
import com.marketplace.Offre_Demande.entity.Product;
import com.marketplace.Offre_Demande.exception.ResourceNotFoundException;
import com.marketplace.Offre_Demande.mappers.OfferMappers;
import com.marketplace.Offre_Demande.repository.OfferRepository;
import com.marketplace.Offre_Demande.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
//RELI BIEN TOUS PREND TON TEMPS
@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;
    private final ProductRepository productRepository;
    private final OfferMappers offerMappers;

    public void createOffer(Long idProducteur ,OfferDtoRequest request) {
        if(request == null)
            throw new RuntimeException("Pas d'information");
        
        Product product = productRepository.findById(request.idPoduct())
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));

        Offer offer = new Offer();
        offer.setProduct(product);
        offer.setIdProducteur(idProducteur); 
        offer.setQuantity(request.quantity());
        offer.setDescription(request.description());
        offer.setPrice((Double)(product.getPrice() * request.quantity()));
        offer.setDate(LocalDateTime.now());
        offer.setImage(request.image());
        offer.setValidate(false);
        offerRepository.save(offer);
    }

    //detail d'une offre
    public OfferDtoResponse getOffer(Long id) {

        if( id == null)
            throw new RuntimeException("Pas d'information");
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer not found"));
        return offerMappers.EntityToDto(offer);
    }
    //pour les category
    public List<OfferDtoResponse> getOffersByProducer(Long producerId){
        if(producerId == null)
            throw new RuntimeException("Pas d'information");
        List<Offer> offers = offerRepository.findByProduct(productRepository.findById(producerId).get());
        List<Offer> pOffers = offers.stream().filter(o -> o.isValidate()).collect(Collectors.toList());
        
        return pOffers.stream().map(offerMappers::EntityToDto).collect(Collectors.toList());
    }
    //mise ajour de la quantite des produits d'une offre
    public void updateOfferQuantity(Long offerId, Long newQuantity)
    {
        Offer offer = offerRepository.findById(offerId)
        .orElseThrow(() -> new RuntimeException("Offre introuvable"));

        // Mise à jour de la quantité
        offer.setQuantity(offer.getQuantity() - newQuantity);

        // Recalcul automatique du prix total
        if (offer.getProduct() != null) {
            offer.setPrice((Double) (offer.getProduct().getPrice() * newQuantity));
        } else {
            throw new RuntimeException("Produit associé à l'offre introuvable");
        }

        if(offer.getQuantity() == 0)
            offer.setValidate(false);

        offerRepository.save(offer);
    }

    //supprimer l'offre
    public void deleteOffer(Long id) {
        if(id == null)
            throw new RuntimeException("pas d'information");
        
        Offer offer = offerRepository.findById(id).get();

        if(offer == null)
            throw new RuntimeException("Offre n'existe pas");
        
        offerRepository.deleteById(id);
    }

    //validation de l'offre pas l'admin
    public void validateOffer(Long id) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found"));
        offer.setValidate(true);
        offerRepository.save(offer);
    }

    //refuse de l'offre pas l'admin
    public void cancelOffer(Long id) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found"));
        offerRepository.delete(offer);
    }


}
