package com.marketplace.Notation_Commentaire.entity;
import jakarta.persistence.*;

// import java.sql.Date;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idComment;

    @Column(nullable = false)
    private Long idUser;

    @Column(nullable = false)
    private Long idOffre;
    
    @CreationTimestamp // met directement la date actuelle
    private LocalDateTime creationDate;
    
    @Column(nullable = false)
    private int evaluation; //L'évaluation de 1 à 5 étoiles

    @Column(nullable = false, columnDefinition = "TEXT", length = 500) //permet de stocker un texte long
    private String content;

    private int aime = 0;
    private int nAimePas = 0;  
    

}
