package com.marketplace.Notation_Commentaire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.marketplace.Notation_Commentaire.dto.CommentDtoRequest;
import com.marketplace.Notation_Commentaire.dto.CommentDtoResponse;
import com.marketplace.Notation_Commentaire.entity.Comments;
import com.marketplace.Notation_Commentaire.mapper.CommentMapper;
import com.marketplace.Notation_Commentaire.repository.CommentRepository;
import com.marketplace.Notation_Commentaire.requetes_externes_aux_services.OffreDto;
import com.marketplace.Notation_Commentaire.requetes_externes_aux_services.OffreService;
import com.marketplace.Notation_Commentaire.requetes_externes_aux_services.UserDatas;
import com.marketplace.Notation_Commentaire.requetes_externes_aux_services.UserDatasClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImplement implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserDatasClient userDatasClient;
    private final OffreService offreService;

    @Override
    public boolean Save( Long idUser, Long idOffre, CommentDtoRequest commentDtoRequest) {
        if (commentDtoRequest == null) {
            return false;
        } 
        
        UserDatas userDatas = userDatasClient.getUserDatasById(idUser);
        if(userDatas == null)
            return false;

        OffreDto offreDto = offreService.getOffre(idOffre);
        if (offreDto == null) {
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

