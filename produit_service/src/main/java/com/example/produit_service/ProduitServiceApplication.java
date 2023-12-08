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
            for(int i=0;i<10;i++){
                Produit p=Produit.builder().marque("abibas").desc("andjzbdjzbdjz").prix((Float) ((float) (Math.random()*1000))).quantite(100).build();
                produitRepository.save(p);
            }

            List<Produit> l=produitRepository.findAll();


            for(Produit p :l){
                System.out.println("===============================");
                System.out.println(p.getIdP());
                System.out.println(p.getMarque());
                System.out.println(p.getPrix());


            }
        };
    }
}
