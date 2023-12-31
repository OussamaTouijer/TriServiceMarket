package com.example.vente_service.module;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString @Data
public class Produit {
    private Integer idP;
    private String nom;
    private String marque;
    private String desc;
    private Double prix;
    private Integer quantite;
}