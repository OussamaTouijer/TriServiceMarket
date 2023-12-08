package com.example.acheteur_service.web;

import com.example.acheteur_service.entitise.Acheteur;
import com.example.acheteur_service.module.Produit;
import com.example.acheteur_service.produit.ProduitFindController;

import com.example.acheteur_service.repositories.AcheteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@RestController
public class AcheteurRestControler {

    @Autowired
    ProduitFindController produitFindController;

    @Autowired
   AcheteurRepository acheteurRepository;

    @GetMapping("/acheteurs")
    public List<Acheteur> getAll(){
        List<Produit> lp = produitFindController.getAll();
        List<Acheteur> l= acheteurRepository.findAll();

        for(Acheteur a:l){
            for(Produit p:lp) {
                if(Objects.equals(a.getIdP(), p.getIdP())) {

                    a.setProduit(p);break;
                }
            }
        }
        return l;
    }

    @GetMapping("/acheteurs/{id}")
    public Acheteur getById(@PathVariable("id") Integer id){

        Acheteur e= acheteurRepository.findById(id).get();
        Produit p = produitFindController.getById(e.getIdP());

        e.setProduit(p);
        return  e;
    }

    @PutMapping("/acheteurs/{id}")
    public void save(@PathVariable ("id") Integer id, @RequestBody Acheteur a){
        Acheteur ar = acheteurRepository.findById(id).get();

        if(a.getNom()!=null) {ar.setNom(a.getNom());}
        if(a.getVille()!=null){ar.setVille(a.getVille());}

        acheteurRepository.save(ar);
    }

    @PostMapping("/acheteurs")
    public void add(@RequestBody Acheteur a){
        acheteurRepository.save(a);
    }

    @DeleteMapping("/acheteurs/{id}")
    public void supprimer(@PathVariable ("id") Integer id){
        acheteurRepository.deleteById(id);
    }

}
