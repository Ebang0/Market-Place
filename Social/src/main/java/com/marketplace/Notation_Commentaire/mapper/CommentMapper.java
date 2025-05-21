package com.marketplace.Notation_Commentaire.mapper;

import org.springframework.stereotype.Service;

import com.marketplace.Notation_Commentaire.dto.CommentDtoRequest;
import com.marketplace.Notation_Commentaire.dto.CommentDtoResponse;
import com.marketplace.Notation_Commentaire.entity.Comments;


@Service
public class CommentMapper {

    // Convertit le formulaire (DTO) en entité pour la base de données
    public Comments DtoToEntity(CommentDtoRequest commentDtoRequest) {
        Comments comment = new Comments();
        // comment.setIdUser(commentDtoRequest.idUser());
        // comment.setIdOffer(commentDtoRequest.idOffer());
        comment.setEvaluation(commentDtoRequest.evaluation());
        comment.setContent(commentDtoRequest.content());
        // comment.setAime(0);         // Initialisé à 0
        // comment.setNAimePas(0);       // Initialisé à 0
        return comment;
    }

    // Prépare les données pour l'affichage
    public CommentDtoResponse EntityToDto(Comments comment) {
        return new CommentDtoResponse(
            comment.getIdOffre(), 
            // comment.getUserName(),
            comment.getCreationDate(),
            comment.getEvaluation(),
            comment.getContent(),
            comment.getAime(),
            comment.getNAimePas()
        );
    }
}



// public class CommentMapper {

// }

// Transforme un type d'objet en un autre

// @Mapper(componentModel = "spring") // Dit à Spring de gérer ce mapper
// public interface CommentMapper {
//     // Instance unique du mapper
//     CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    
//     // Transforme CommentRequest → Comment
//     @Mapping(target = "statusComment", expression = "java(CommentStatus.PENDING)")
//     // ↑ Met automatiquement le statut à "PENDING"
//     @Mapping(target = "aime", constant = "0") // Initialise les "j'aime" à 0
//     @Mapping(target = "nAimePas", constant = "0") // Initialise les "je n'aime pas" à 0
//     Comment toEntity(CommentDtoRequest commentDtoRequest);
    
//     // Transforme Comment → CommentResponse
//     CommentDtoResponse commentDtoResponse(Comment comment);
// }


