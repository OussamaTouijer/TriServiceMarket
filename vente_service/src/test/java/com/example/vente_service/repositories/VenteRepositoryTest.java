package com.example.vente_service.repositories;

import com.example.vente_service.entities.Vente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class VenteRepositoryTest {

    @Autowired
    private VenteRepository venteRepository;

    @Test
    public void testSaveVente() {
        Vente vente = Vente.builder().idA(1).idP(2).build();
        Vente savedVente = venteRepository.save(vente);

        assertThat(savedVente.getIdV()).isNotNull();
        assertThat(savedVente.getIdA()).isEqualTo(vente.getIdA());
        assertThat(savedVente.getIdP()).isEqualTo(vente.getIdP());
    }

    @Test
    public void testFindAll() {
        Vente vente1 = Vente.builder().idA(1).idP(2).build();
        Vente vente2 = Vente.builder().idA(1).idP(3).build();

        venteRepository.save(vente1);
        venteRepository.save(vente2);

        List<Vente> ventes = venteRepository.findAll();

        assertThat(ventes).isNotEmpty();
        assertThat(ventes.size()).isEqualTo(7);
        assertThat(ventes).contains(vente1, vente2);
    }

    @Test
    public void testFindById() {
        Vente vente = Vente.builder().idA(1).idP(2).build();
        venteRepository.save(vente);

        Optional<Vente> foundVente = venteRepository.findById(vente.getIdV());

        assertThat(foundVente).isPresent();
        assertThat(foundVente.get().getIdV()).isEqualTo(vente.getIdV());
    }

    @Test
    public void testUpdateVente() {
        Vente vente = Vente.builder().idA(1).idP(2).build();
        Vente savedVente = venteRepository.save(vente);

        savedVente.setIdP(3);
        Vente updatedVente = venteRepository.save(savedVente);

        assertThat(updatedVente.getIdP()).isEqualTo(3);
    }

    @Test
    public void testDeleteVente() {
        Vente vente = Vente.builder().idA(1).idP(2).build();
        venteRepository.save(vente);

        venteRepository.deleteById(vente.getIdV());

        Optional<Vente> deletedVente = venteRepository.findById(vente.getIdV());
        assertThat(deletedVente).isEmpty();
    }

}
