package com.example.acheteur_service.module;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString @Data
public class Produit {
    private Integer idP;
    private String marque;
    private String desc;
    private Float prix;
    private Integer quantite;
}
