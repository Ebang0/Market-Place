package com.marketplace.Notation_Commentaire.dto;

import java.time.LocalDateTime;


public record CommentDtoResponse( Long idOffre,LocalDateTime creationDate, 
                                    int evaluation, 
                                    String content, 
                                    int aime, 
                                    int nAimePas) {


}
