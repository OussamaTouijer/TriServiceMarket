package com.example.vente_service.web;

import com.example.vente_service.acheteurs.AcheteurOpenFeing;
import com.example.vente_service.entities.Vente;
import com.example.vente_service.module.Acheteur;
import com.example.vente_service.module.Produit;
import com.example.vente_service.produits.ProduitOpenFeing;
import com.example.vente_service.repositories.VenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VenteRestControler {
    @Autowired
    VenteRepository venteRepository;

    @Autowired
    ProduitOpenFeing produitOpenFeing;

    @Autowired
    AcheteurOpenFeing acheteurOpenFeing;

    @GetMapping("/ventes")
    public List<Vente> getAll(){
        List<Vente> lv=venteRepository.findAll();
        List<Acheteur> la=acheteurOpenFeing.getAll();
        List<Produit> lp=produitOpenFeing.getAll();

        for(Vente vente :lv){
            for(Acheteur acheteur : la){
                if(vente.getIdA()==acheteur.getIdA()){ vente.setAcheteur(acheteur); break;}
            }
            for(Produit produit : lp){
                if(vente.getIdP()==produit.getIdP()){ vente.setProduit(produit); break;}
            }
        }

        return  lv;
    }

    @GetMapping("/ventes/{id}")
    public Vente getById(@PathVariable("id") Integer id){
        Vente vente=venteRepository.findById(id).get();
        Acheteur acheteur=acheteurOpenFeing.getById(vente.getIdA());
        Produit produit=produitOpenFeing.getById(vente.getIdP());

        vente.setAcheteur(acheteur);
        vente.setProduit(produit);

        return vente;

    }

    @PutMapping("/ventes/{id}")
    public void update_Vente(@PathVariable ("id") Integer id, @RequestBody Vente v){
        Vente vt = venteRepository.findById(id).get();
        if(v.getIdV()!=null) {vt.setIdV(v.getIdV());}
        if(v.getIdA()!=null){vt.setIdA(v.getIdA());}
        if(v.getIdP()!=null){vt.setIdP(v.getIdP());}
        venteRepository.save(vt);
    }


    @PostMapping("/ventes")
    public void save_vente(@RequestBody Vente v){
        venteRepository.save(v);
    }

    @DeleteMapping("/ventes/{id}")
    public void delete_vente(@PathVariable ("id") Integer id){
        venteRepository.deleteById(id);
    }

}
