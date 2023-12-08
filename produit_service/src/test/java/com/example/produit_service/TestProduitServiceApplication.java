package com.example.produit_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestProduitServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(ProduitServiceApplication::main).with(TestProduitServiceApplication.class).run(args);
    }

}
