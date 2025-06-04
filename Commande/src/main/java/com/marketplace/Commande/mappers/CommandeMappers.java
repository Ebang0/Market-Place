package com.marketplace.Commande.mappers;

import org.springframework.stereotype.Service;

import com.marketplace.Commande.dto.CommandeDtoRequest;
import com.marketplace.Commande.dto.CommandeDtoResponse;
import com.marketplace.Commande.entity.Commande;

@Service

public class CommandeMappers {
    public Commande DtoToEntity(CommandeDtoRequest commandeDtoRequest){
        Commande commande = new Commande();
        commande.setQuantite(commandeDtoRequest.quantite());

        return commande;
    }

    public CommandeDtoResponse EntityToDto(Commande commande){
        return new CommandeDtoResponse(commande.getId(), commande.getCommandeDate(), commande.getStatut(),commande.getQuantite(), commande.getPrixtotal());
    }

}
