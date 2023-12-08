package com.example.vente_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestVenteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(VenteServiceApplication::main).with(TestVenteServiceApplication.class).run(args);
    }

}
