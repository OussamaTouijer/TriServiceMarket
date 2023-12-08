package com.example.acheteur_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestAcheteurServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(AcheteurServiceApplication::main).with(TestAcheteurServiceApplication.class).run(args);
    }

}
