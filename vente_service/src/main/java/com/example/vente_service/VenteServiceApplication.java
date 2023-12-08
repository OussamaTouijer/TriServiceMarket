package com.example.vente_service;

import com.example.vente_service.entities.Vente;
import com.example.vente_service.repositories.VenteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;



@EnableFeignClients
@SpringBootApplication
public class VenteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VenteServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start (VenteRepository venteRepository)
    { return args -> {
        Vente u1 = Vente.builder()
                .idA(1)
                .idP(2)
                .build();
        venteRepository.save(u1);

        Vente u2 = Vente.builder()
                .idA(1)
                .idP(3)
                .build();
        venteRepository.save(u2);

        Vente u3 = Vente.builder()
                .idA(1)
                .idP(4)
                .build();
        venteRepository.save(u3);

        Vente u4 = Vente.builder()
                .idA(5)
                .idP(3)
                .build();
        venteRepository.save(u4);

        Vente u5 = Vente.builder()
                .idA(3)
                .idP(1)
                .build();
        venteRepository.save(u5);
    };

    }
}
