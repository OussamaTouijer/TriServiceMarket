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
    public List<Produit> getAllProduits(){
        return  produitRepository.findAll();

    }

    @GetMapping("/produits/{id}")
    public Produit getProduitById(@PathVariable("id") Integer id){


        return produitRepository.findById(id).get();

    }

    @PutMapping("/produits/{id}")
    public void update_Produit(@PathVariable ("id") Integer id, @RequestBody Produit p){
        Produit pr = produitRepository.findById(id).get();
        if(p.getNom()!=null) {pr.setNom(p.getNom());}
        if(p.getMarque()!=null){pr.setMarque(p.getMarque());}
        if(p.getDesc()!=null){pr.setDesc(p.getDesc());}
        if(p.getPrix()!=null){pr.setPrix(p.getPrix());}
        if(p.getQuantite()!=null){pr.setQuantite(p.getQuantite());}
        produitRepository.save(p);
    }


    @PostMapping("/produits")
    public void save_Produit(@RequestBody Produit p){
        produitRepository.save(p);
    }

    @DeleteMapping("/produits/{id}")
    public void delete_Produit(@PathVariable ("id") Integer id){
        produitRepository.deleteById(id);
    }

}