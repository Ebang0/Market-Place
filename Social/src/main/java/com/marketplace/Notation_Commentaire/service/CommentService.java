package com.marketplace.Notation_Commentaire.service;

import java.util.List;

import com.marketplace.Notation_Commentaire.dto.CommentDtoRequest;
import com.marketplace.Notation_Commentaire.dto.CommentDtoResponse;

public interface CommentService {
   
    public boolean Save(Long idUser, Long idOffre, CommentDtoRequest commentDtoRequest);
    public List<CommentDtoResponse> getAll(Long idOffre);
    // public float averageComment(Long idComment, )

}

// Ce fichier contient la logique métier
// Un service est la couche intermédiaire entre le controleur(API) et le repository


