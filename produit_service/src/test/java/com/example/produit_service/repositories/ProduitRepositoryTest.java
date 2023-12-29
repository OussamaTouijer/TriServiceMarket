package com.example.produit_service.repositories;

import com.example.produit_service.entities.Produit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProduitRepositoryTest {

    @Autowired
    private ProduitRepository produitRepository;

    @Test
    public void testSaveProduit() {
        Produit produit = Produit.builder()
                .nom("i phone proo max")
                .marque("Apple")
                .desc("Le nouvel iPhone 13 Pro Max est doté d'un écran Super Retina XDR de 6")
                .prix(198760.0)
                .quantite(50)
                .build();

        Produit savedProduit = produitRepository.save(produit);

        assertThat(savedProduit.getIdP()).isNotNull();
        assertThat(savedProduit.getNom()).isEqualTo(produit.getNom());
        assertThat(savedProduit.getMarque()).isEqualTo(produit.getMarque());
        assertThat(savedProduit.getDesc()).isEqualTo(produit.getDesc());
        assertThat(savedProduit.getPrix()).isEqualTo(produit.getPrix());
        assertThat(savedProduit.getQuantite()).isEqualTo(produit.getQuantite());
    }

    @Test
    public void testFindAll() {
        Produit produit1 = Produit.builder()
                .nom("i phone proo max")
                .marque("Apple")
                .desc("Le nouvel iPhone 13 Pro Max est doté d'un écran Super Retina XDR de 6")
                .prix(198760.0)
                .quantite(50)
                .build();

        Produit produit2 = Produit.builder()
                .nom("Samsung Galaxy S22 Ultra")
                .marque("Samsung")
                .desc("Le Samsung Galaxy S22 Ultra est doté d'un écran Dynamic AMOLED 2X de 6")
                .prix(4560.0)
                .quantite(300)
                .build();

        produitRepository.save(produit1);
        produitRepository.save(produit2);

        List<Produit> produits = produitRepository.findAll();

        assertThat(produits).isNotEmpty();
        assertThat(produits.size()).isEqualTo(6);
        assertThat(produits).contains(produit1, produit2);
    }

    @Test
    public void testFindById() {
        Produit produit = Produit.builder()
                .nom("i phone proo max")
                .marque("Apple")
                .desc("Le nouvel iPhone 13 Pro Max est doté d'un écran Super Retina XDR de 6")
                .prix(198760.0)
                .quantite(50)
                .build();
        produitRepository.save(produit);

        Optional<Produit> foundProduit = produitRepository.findById(produit.getIdP());

        assertThat(foundProduit).isPresent();
        assertThat(foundProduit.get().getIdP()).isEqualTo(produit.getIdP());
    }

    @Test
    public void testUpdateProduit() {
        Produit produit = Produit.builder()
                .nom("i phone proo max")
                .marque("Apple")
                .desc("Le nouvel iPhone 13 Pro Max est doté d'un écran Super Retina XDR de 6")
                .prix(198760.0)
                .quantite(50)
                .build();
        Produit savedProduit = produitRepository.save(produit);

        savedProduit.setNom("oussama");
        savedProduit.setMarque("Football");
        savedProduit.setDesc("dima wydad");
        savedProduit.setPrix(150.0);
        savedProduit.setQuantite(150);
        Produit updatedProduit = produitRepository.save(savedProduit);

        assertThat(updatedProduit.getNom()).isEqualTo("oussama");
        assertThat(updatedProduit.getMarque()).isEqualTo("Football");
        assertThat(updatedProduit.getDesc()).isEqualTo("dima wydad");
        assertThat(updatedProduit.getPrix()).isEqualTo(150.0);
        assertThat(updatedProduit.getQuantite()).isEqualTo(150);
    }

    @Test
    public void testDeleteProduit() {
        Produit produit = Produit.builder()
                .nom("i phone proo max")
                .marque("Apple")
                .desc("Le nouvel iPhone 13 Pro Max est doté d'un écran Super Retina XDR de 6")
                .prix(198760.0)
                .quantite(50)
                .build();
        produitRepository.save(produit);

        produitRepository.deleteById(produit.getIdP());

        Optional<Produit> deletedProduit = produitRepository.findById(produit.getIdP());
        assertThat(deletedProduit).isEmpty();
    }
}
