package com.example.produit_service.web;

import com.example.produit_service.config.GlobalConfig;
import com.example.produit_service.config.ProduitConfig;
import com.example.produit_service.entities.Produit;
import com.example.produit_service.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProduitRestControler {

    @Autowired
    ProduitRepository produitRepository;

    @Autowired
    GlobalConfig globalConfig;

    @Autowired
    ProduitConfig produitConfig;

    @GetMapping("/globalConfigs")
    public GlobalConfig globalConfig(){
        return globalConfig;
    }

    @GetMapping("/produitConfigs")
    public ProduitConfig produitConfig(){
        return produitConfig;
    }

    @GetMapping("/produits")
    public List<Produit> getAll(){
        return  produitRepository.findAll();

    }

    @GetMapping("/produits/{id}")
    public Produit getById(@PathVariable("id") Integer id){


        return produitRepository.findById(id).get();

    }

    @PutMapping("/produits/{id}")
    public void save(@PathVariable ("id") Integer id, @RequestBody Produit a){
        a.setIdP(id);

        produitRepository.save(a);
    }

    @PostMapping("/produits")
    public void add(@RequestBody Produit a){
        produitRepository.save(a);
    }

    @DeleteMapping("/produits/{id}")
    public void supprimer(@PathVariable ("id") Integer id){
        produitRepository.deleteById(id);
    }

}
