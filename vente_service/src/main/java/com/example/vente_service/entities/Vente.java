package com.example.vente_service.entities;

import com.example.vente_service.module.Acheteur;
import com.example.vente_service.module.Produit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idV;
    private Integer idA;
    private Integer idP;

    @Transient
    private Acheteur acheteur;
    @Transient
    private Produit produit;

}
