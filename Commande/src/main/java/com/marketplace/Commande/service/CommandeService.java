package com.marketplace.Commande.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.marketplace.Commande.customService.OffreService;
import com.marketplace.Commande.customService.UtilisateurService;
import com.marketplace.Commande.dto.CommandeDtoRequest;
import com.marketplace.Commande.dto.CommandeDtoResponse;
import com.marketplace.Commande.dto.OffreDto;
import com.marketplace.Commande.dto.ProduitDto;
import com.marketplace.Commande.dto.ProfilDto;
import com.marketplace.Commande.entity.Commande;
import com.marketplace.Commande.entity.Commande.Status;
import com.marketplace.Commande.mappers.CommandeMappers;
import com.marketplace.Commande.repository.CommandeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommandeService {

    private final OffreService offreService;
    private final CommandeRepository commandeRepository;
    private final CommandeMappers commandeMappers;
    private final UtilisateurService utilisateurService;

    public void Save(Long idOffre,Long idUser,CommandeDtoRequest commandeDtoRequest)
    {
        if(idOffre == null || commandeDtoRequest == null)
        {
            throw new RuntimeException("Pas d'information");
        }

        OffreDto offreDto = offreService.getOffre(idOffre);
        ProduitDto produitDto = offreService.getProduct(offreDto.offerId());

        if(produitDto == null)
        {
            throw new RuntimeException("Pas produit existant");
        }

        if(offreDto == null)
        {
            throw new RuntimeException("L'offre n'existe pas");
        }

        offreService.updateOfferQuantity(idOffre, commandeDtoRequest.quantite());

        Commande commande = new Commande();
        commande.setIdOffer(idOffre);
        commande.setQuantite(commandeDtoRequest.quantite());
        commande.setPrixtotal(produitDto.price() * commandeDtoRequest.quantite());
        commande.setStatut(Status.LOADING);
        commande.setCommandeDate(LocalDateTime.now());
        commande.setIdUser(idUser);

        commandeRepository.save(commande);
    }

    public List<CommandeDtoResponse> getAll()
    {
        List<Commande> commandes = commandeRepository.findAll();

        List<CommandeDtoResponse> commandeDtoResponses = commandes.stream().map(commandeMappers::EntityToDto).collect(Collectors.toList());
        return commandeDtoResponses;
    }

    public List<CommandeDtoResponse> getAllForUser(Long id)
    {
        if (id == null) {
            throw new RuntimeException("Pas de donnée");
        }

        ProfilDto profilDto = utilisateurService.getProfil(id);

        if(profilDto == null)
            throw new RuntimeException("Utilisatuer non existant");

        List<Commande> commandes = commandeRepository.findAll();

        if(commandes == null)
        {
            throw new RuntimeException("Pas de commande");
        }

        return commandes.stream().map(commandeMappers::EntityToDto).collect(Collectors.toList());
    }

    public void Annuler(Long id)
    {
        Commande commande = commandeRepository.findById(id).orElseThrow(() -> new RuntimeException("Pas d'offre"));
        offreService.updateOfferQuantity(commande.getIdOffer(), -commande.getQuantite());
        commandeRepository.delete(commande);
    }
}
