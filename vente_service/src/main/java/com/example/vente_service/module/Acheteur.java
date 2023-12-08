package com.example.vente_service.module;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString @Data
public class Acheteur {
    private Integer idA;
    private String nom;
    private String ville;
}
