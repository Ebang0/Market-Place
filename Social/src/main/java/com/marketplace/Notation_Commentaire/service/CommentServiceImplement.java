package com.marketplace.Notation_Commentaire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.marketplace.Notation_Commentaire.dto.CommentDtoRequest;
import com.marketplace.Notation_Commentaire.dto.CommentDtoResponse;
import com.marketplace.Notation_Commentaire.entity.Comments;
import com.marketplace.Notation_Commentaire.mapper.CommentMapper;
import com.marketplace.Notation_Commentaire.repository.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImplement implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    // Crée un nouveau commentaire
    // public CommentDtoResponse create(CommentDtoRequest commentDtoRequest) {
    //     Comments newComment = new Comments();
    //     newComment.setContent(commentDtoRequest.content());
    //     newComment.setEvaluation(commentDtoRequest.evaluation());
    //     // ... (autres attributs)
    //     return CommentMapper.toDto(commentRepository.save(newComment));
    // }

    // Récupère tous les commentaires d'une offre
    // public List<CommentDtoResponse> getByOffer(Long idOffre) {
    //     return commentRepository.findByIdOffre(idOffre).stream()
    //            .map(CommentMapper::toDto)
    //            .toList();
    // }



    @Override
    public boolean Save( Long idUser, Long idOffre, CommentDtoRequest commentDtoRequest) {
        if (commentDtoRequest == null) {
            return false;
        }    
        Comments comment = commentMapper.DtoToEntity(commentDtoRequest);
        comment.setIdOffre(idOffre);
        comment.setIdUser(idUser);
        commentRepository.save(comment);
        return true;    
    }

    @Override
    public List<CommentDtoResponse> getAll(Long idOffre) {
        List<Comments> comments = commentRepository.findByIdOffre(idOffre);
       return comments.stream().map(commentMapper::EntityToDto).collect(Collectors.toList());
    }
}








// public class CommentServiceImplement implements CommentService{

//     // Ce fichier contient la logique métier

// public class CommentService {
    
//     // Branchement des outils nécessaires
//     private final CommentRepository commentRepository; // Pour la base de données
//     private final CommentMapper commentMapper;         // Pour transformer les objets
//     private final UserServiceClient userServiceClient; // Pour vérifier les utilisateurs
//     private final OfferServiceClient offerServiceClient; // Pour vérifier les offres

//     public CommentDtoResponse createComment(CommentDtoRequest_V1 commentRequest) {
//         // Vérifie si l'utilisateur existe
//         if (!userServiceClient.userExists(commentRequest.getIdUser())) {
//             throw new UserNotFoundException("Utilisateur non trouvé");
//         }
        
//         // Vérifie si l'offre existe
//         if (!offerServiceClient.offerExists(commentRequest.getIdOffer())) {
//             throw new OfferNotFoundException("Offre non trouvée");
//         }
        
//         // Transforme la demande en entité
//         Comment comment = commentMapper.toEntity(commentRequest);
        
//         // Sauvegarde en base de données
//         Comment savedComment = commentRepository.save(comment);
        
//         // Transforme en réponse
//         return commentMapper.toResponse(savedComment);
//     }
    
    
// }



// }
