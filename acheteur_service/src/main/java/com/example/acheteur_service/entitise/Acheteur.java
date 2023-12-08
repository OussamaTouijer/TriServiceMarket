package com.example.acheteur_service.entitise;

import com.example.acheteur_service.module.Produit;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Acheteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idA;
    private String nom;
    private String ville;
    private Integer idP;
    @Transient
    private Produit produit;
}