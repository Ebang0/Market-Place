package com.marketplace.Notation_Commentaire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketplace.Notation_Commentaire.entity.Comments;

public interface CommentRepository extends JpaRepository<Comments, Long> {
    List<Comments> findByIdOffre(Long idOffre);
}
