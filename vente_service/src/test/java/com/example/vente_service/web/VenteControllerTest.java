package com.example.vente_service.web;

import com.example.vente_service.acheteurs.AcheteurOpenFeing;
import com.example.vente_service.entities.Vente;
import com.example.vente_service.module.Acheteur;
import com.example.vente_service.module.Produit;
import com.example.vente_service.produits.ProduitOpenFeing;
import com.example.vente_service.repositories.VenteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


@SpringBootTest
@AutoConfigureMockMvc
// Annotation pour activer la découverte des clients Feign dans les packages spécifiés
@EnableFeignClients(basePackages = {"com.example.vente_service.produits", "com.example.vente_service.acheteurs"})
public class VenteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VenteRepository venteRepository;

    @MockBean
    private ProduitOpenFeing produitOpenFeing;

    @MockBean
    private AcheteurOpenFeing acheteurOpenFeing;

    @Test
    public void testGetAllVentes() throws Exception {
        // Création de données simulées pour les ventes
        Vente vente1 = Vente.builder().idV(1).idA(1).idP(2).build();
        Vente vente2 = Vente.builder().idV(2).idA(2).idP(3).build();
        List<Vente> ventes = Arrays.asList(vente1, vente2);

        // Configuration du comportement simulé du VenteRepository
        when(venteRepository.findAll()).thenReturn(ventes);

        // Configuration du comportement simulé du ProduitOpenFeing.getAll()
        Produit produit1 = Produit.builder().idP(2).nom("Produit 1").marque("Marque 1").build();
        Produit produit2 = Produit.builder().idP(3).nom("Produit 2").marque("Marque 2").build();
        List<Produit> produits = Arrays.asList(produit1, produit2);
        when(produitOpenFeing.getAll()).thenReturn(produits);

        // Configuration du comportement simulé du AcheteurOpenFeing.getAll()
        Acheteur acheteur1 = Acheteur.builder().idA(1).nom("Acheteur 1").ville("Ville 1").build();
        Acheteur acheteur2 = Acheteur.builder().idA(2).nom("Acheteur 2").ville("Ville 2").build();
        List<Acheteur> acheteurs = Arrays.asList(acheteur1, acheteur2);
        when(acheteurOpenFeing.getAll()).thenReturn(acheteurs);

        // Exécution d'une requête HTTP simulée pour obtenir toutes les ventes
        mockMvc.perform(MockMvcRequestBuilders.get("/ventes"))
                // Vérification des résultats attendus
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].idV").value(vente1.getIdV()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].produit.idP").value(produit1.getIdP()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].acheteur.idA").value(acheteur1.getIdA()))  // Vérification de l'Acheteur ajoutée
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].idV").value(vente2.getIdV()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].produit.idP").value(produit2.getIdP()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].acheteur.idA").value(acheteur2.getIdA()));  // Vérification de l'Acheteur ajoutée
        // COMMENTAIRE : Cette méthode de test vérifie le comportement de la récupération de toutes les ventes.
        // Elle utilise des données simulées pour représenter les ventes, les produits et les acheteurs,
        // configure les réponses simulées pour les appels aux services (venteRepository, acheteurOpenFeing, produitOpenFeing),
        // puis effectue une requête HTTP simulée et vérifie que la réponse est OK et contient les données attendues.
    }


    @Test
    public void testGetVenteById() throws Exception {
        // Données de simulation
        int venteId = 1;
        Vente vente = Vente.builder().idV(venteId).idA(1).idP(2).build();
        Acheteur acheteur = Acheteur.builder().idA(1).nom("Acheteur 1").ville("Ville 1").build();
        Produit produit = Produit.builder().idP(2).nom("Produit 1").marque("Marque 1").build();

        // Simulation de la réponse pour venteRepository.findById()
        when(venteRepository.findById(venteId)).thenReturn(Optional.of(vente));

        // Simulation de la réponse pour acheteurOpenFeing.getById()
        when(acheteurOpenFeing.getById(vente.getIdA())).thenReturn(acheteur);

        // Simulation de la réponse pour produitOpenFeing.getById()
        when(produitOpenFeing.getById(vente.getIdP())).thenReturn(produit);

        // Exécution d'une requête HTTP simulée pour obtenir une vente par ID
        mockMvc.perform(MockMvcRequestBuilders.get("/ventes/{id}", venteId))
                // Vérification des résultats attendus
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idV").value(vente.getIdV()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.acheteur.idA").value(acheteur.getIdA()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.produit.idP").value(produit.getIdP()));
        // COMMENTAIRE : Cette méthode de test vérifie le comportement de la récupération d'une vente par ID.
        // Elle utilise des données simulées pour représenter la vente, l'acheteur et le produit,
        // configure les réponses simulées pour les appels aux services (venteRepository, acheteurOpenFeing, produitOpenFeing),
        // puis effectue une requête HTTP simulée et vérifie les résultats pour s'assurer qu'ils correspondent aux attentes.
    }


    @Test
    public void testUpdateVente() throws Exception {
        // Données de simulation
        int venteId = 1;
        Vente existingVente = Vente.builder().idV(venteId).idA(1).idP(2).build();
        Vente updatedVente = Vente.builder().idV(venteId).idA(1).idP(3).build();

        // Simulation de la réponse pour venteRepository.findById()
        Mockito.when(venteRepository.findById(venteId)).thenReturn(Optional.of(existingVente));

        // Simulation de la réponse pour produitOpenFeing.getById()
        Produit produit = Produit.builder().idP(3).nom("Produit Mis à Jour").marque("Marque Mis à Jour").build();
        Mockito.when(produitOpenFeing.getById(updatedVente.getIdP())).thenReturn(produit);

        // Simulation de la réponse pour acheteurOpenFeing.getById()
        Acheteur acheteur = Acheteur.builder().idA(1).nom("Acheteur 1").ville("Ville 1").build();
        Mockito.when(acheteurOpenFeing.getById(updatedVente.getIdA())).thenReturn(acheteur);

        // Simulation de la méthode save de venteRepository
        Mockito.when(venteRepository.save(Mockito.any(Vente.class))).thenReturn(updatedVente);

        // Exécution d'une requête HTTP simulée pour mettre à jour une vente
        mockMvc.perform(MockMvcRequestBuilders.put("/ventes/{id}", venteId)
                        .content(asJsonString(updatedVente))
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Assertions supplémentaires pour vérifier les données mises à jour
        mockMvc.perform(MockMvcRequestBuilders.get("/ventes/{id}", venteId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idV").value(updatedVente.getIdV()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.acheteur.idA").value(acheteur.getIdA()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.produit.idP").value(produit.getIdP()));
        // COMMENTAIRE : Cette méthode de test vérifie le comportement de la mise à jour d'une vente.
        // Elle utilise des données simulées pour représenter la vente existante et la vente mise à jour,
        // configure les réponses simulées pour les appels aux services (venteRepository, acheteurOpenFeing, produitOpenFeing),
        // puis effectue une requête HTTP simulée de mise à jour et vérifie que la réponse est OK.
        // Ensuite, elle effectue des assertions supplémentaires pour s'assurer que les données ont été effectivement mises à jour.
    }


    @Test
    public void testSaveVente() throws Exception {
        // Données simulées
        Vente newVente = Vente.builder().idA(1).idP(2).build();

        // Simulation de la méthode save de venteRepository
        Mockito.when(venteRepository.save(Mockito.any(Vente.class))).thenReturn(newVente);

        // Exécution d'une requête HTTP simulée pour sauvegarder une vente
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/ventes")
                        .content(asJsonString(newVente))
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Vérification que la méthode save de venteRepository a été appelée une fois avec la nouvelle vente
        verify(venteRepository, times(1)).save(newVente);

        // Vérification qu'aucune autre interaction n'a eu lieu avec venteRepository
        verifyNoMoreInteractions(venteRepository);
        // COMMENTAIRE : Cette méthode de test vérifie le comportement de la sauvegarde d'une nouvelle vente.
        // Elle utilise des données simulées pour représenter la nouvelle vente,
        // configure la réponse simulée pour la méthode save de venteRepository,
        // puis effectue une requête HTTP simulée pour sauvegarder la vente et vérifie que la réponse est OK.
        // Ensuite, elle vérifie que la méthode save de venteRepository a été appelée une fois avec la nouvelle vente,
        // et qu'aucune autre interaction n'a eu lieu avec venteRepository.
    }


    @Test
    public void testDeleteVente() throws Exception {
        // Données simulées
        int venteId = 7;

        // Création d'une vente simulée
        Vente vente = Vente.builder().idV(venteId).idA(1).idP(2).build();

        // Simulation de la méthode findById de venteRepository
        Mockito.when(venteRepository.findById(vente.getIdV())).thenReturn(Optional.of(vente));

        // Exécution d'une requête HTTP simulée pour supprimer une vente
        mockMvc.perform(MockMvcRequestBuilders.delete("/ventes/{id}", venteId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Vérification que la méthode deleteById est appelée une fois avec le bon ID
        Mockito.verify(venteRepository, Mockito.times(1)).deleteById(venteId);

        // Vérification qu'aucune autre interaction n'a eu lieu avec venteRepository
        Mockito.verifyNoMoreInteractions(venteRepository);

        // COMMENTAIRE : Cette méthode de test vérifie le comportement de la suppression d'une vente.
        // Elle utilise des données simulées pour représenter une vente existante,
        // configure la réponse simulée pour la méthode findById de venteRepository,
        // puis effectue une requête HTTP simulée pour supprimer la vente et vérifie que la réponse est OK.
        // Ensuite, elle vérifie que la méthode deleteById de venteRepository a été appelée une fois avec le bon ID,
        // et qu'aucune autre interaction n'a eu lieu avec venteRepository.
    }


    // Méthode utilitaire pour convertir des objets en chaîne JSON
    private static String asJsonString(final Object obj) {
        try {
            // Utilisation de l'objet ObjectMapper de Jackson pour convertir l'objet en JSON
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            // En cas d'erreur pendant la conversion, une exception est lancée
            throw new RuntimeException(e);
        }
    }

}
