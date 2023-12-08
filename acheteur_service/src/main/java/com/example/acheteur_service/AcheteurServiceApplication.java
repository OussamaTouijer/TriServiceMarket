package com.example.acheteur_service;

import com.example.acheteur_service.entitise.Acheteur;
import com.example.acheteur_service.repositories.AcheteurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;
@EnableFeignClients
@SpringBootApplication
public class AcheteurServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcheteurServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AcheteurRepository acheteurRepository){
        return args -> {
            acheteurRepository.save(Acheteur.builder()
                    .nom("Ali Mohammed")
                    .ville("Rabat").idP(1)
                    .build());

            acheteurRepository.save(Acheteur.builder()
                    .nom("Khaoula Jadimoussa")
                    .ville("casa").idP(2)
                    .build());

            acheteurRepository.save(Acheteur.builder()
                    .nom("Salem salem")
                    .ville("tanger").idP(1)
                    .build());

            acheteurRepository.save(Acheteur.builder()
                    .nom("Aya aya")
                    .ville("agadir").idP(2)
                    .build());

            acheteurRepository.save(Acheteur.builder()
                    .nom("Jack Pattinson")
                    .ville("paris")
                    .build());

            acheteurRepository.save(Acheteur.builder()
                    .nom("Ahmad Khan")
                    .ville("dilhi")
                    .build());

            acheteurRepository.save(Acheteur.builder()
                    .nom("Michel Duchamps")
                    .ville("london")
                    .build());

            acheteurRepository.save(Acheteur.builder()
                    .nom("Julia Robert")
                    .ville("kopanhageb")
                    .build());

            acheteurRepository.save(Acheteur.builder()
                    .nom("Djamel ALI")
                    .ville("abu dabi")
                    .build());

            acheteurRepository.save(Acheteur.builder()
                    .nom("Luisa Clarck")
                    .ville("madrid")
                    .build());

            List<Acheteur> liste = acheteurRepository.findAll();

            for (Acheteur a:liste){
                System.out.println("*************************");
                System.out.println(a.getNom());
                System.out.println(a.getVille());
            }

        };
    }
}
