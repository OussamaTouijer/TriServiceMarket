package com.example.acheteur_service.produit;

import com.example.acheteur_service.module.Produit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;


import java.util.List;

@FeignClient(name="PRODUIT-SERVICE")
public interface ProduitFindController {

    @GetMapping("produits")
    public List<Produit> getAll();

    @GetMapping("produits/{idP}")
    public Produit getById(@PathVariable Integer idP);
}