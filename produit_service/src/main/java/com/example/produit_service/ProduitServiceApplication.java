package com.example.produit_service;

import com.example.produit_service.config.GlobalConfig;
import com.example.produit_service.config.ProduitConfig;
import com.example.produit_service.entities.Produit;
import com.example.produit_service.repositories.ProduitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties({GlobalConfig.class, ProduitConfig.class})
public class ProduitServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProduitServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner start(ProduitRepository produitRepository){
        return args -> {
            Produit p1 = Produit.builder()
                    .nom("iPhone 13 Pro Max")
                    .marque("Apple")
                    .desc("Le nouvel iPhone 13 Pro Max est doté d'un écran Super Retina XDR de 6,7 pouces avec ProMotion, d'une puce A15 Bionic, d'un système de caméra Pro avec trois objectifs de 12 Mpx, d'une batterie longue durée et de la 5G")
                    .prix((Double) 1159.00)
                    .quantite(9756)
                    .build();
            produitRepository.save(p1);

            Produit p2 = Produit.builder()
                    .nom("Samsung Galaxy S22 Ultra")
                    .marque("Samsung")
                    .desc("Le Samsung Galaxy S22 Ultra est doté d'un écran Dynamic AMOLED 2X de 6,8 pouces, d'un processeur Exynos 2200, d'un système de caméra quadruple avec un objectif principal de 108 Mpx, d'une batterie longue durée et de la 5G")
                    .prix((double) 12765.00)
                    .quantite(5640)
                    .build();
            produitRepository.save(p2);

            Produit p4 = Produit.builder()
                    .nom("Google Pixel 6 Pro")
                    .marque("Google")
                    .desc("Le Google Pixel 6 Pro est doté d'un écran OLED LTPO de 6,7 pouces avec un taux de rafraîchissement de 120 Hz, d'une puce Google Tensor, d'un système de caméra triple avec un objectif principal de 50 Mpx, d'une batterie longue durée et de la 5G")
                    .prix((double) 899.00)
                    .quantite(12534)
                    .build();
            produitRepository.save(p4);

            Produit p5 = Produit.builder()
                    .nom("OnePlus 10 Pro")
                    .marque("OnePlus")
                    .desc("Le OnePlus 10 Pro est doté d'un écran AMOLED LTPO de 6,7 pouces avec un taux de rafraîchissement de 120 Hz, d'un processeur Snapdragon 8 Gen 1, d'un système de caméra quadruple avec un objectif principal de 48 Mpx, d'une batterie longue durée et de la 5G")
                    .prix((double) 999.00)
                    .quantite(46376)
                    .build();
            produitRepository.save(p5);

        };
    }
}
