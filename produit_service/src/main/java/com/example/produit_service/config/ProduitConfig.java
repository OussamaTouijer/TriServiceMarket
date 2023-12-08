package com.example.produit_service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "produit.params")
@Setter
@Getter
public class ProduitConfig {
    int x;
    int y;
}