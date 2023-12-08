package com.example.vente_service.produits;

import com.example.vente_service.module.Acheteur;
import com.example.vente_service.module.Produit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="PRODUIT-SERVICE")
public interface ProduitOpenFeing {
    @GetMapping("/produits")
    public List<Produit> getAll();

    @GetMapping("/produits/{id}")
    public Produit getById(@PathVariable Integer id);
}
