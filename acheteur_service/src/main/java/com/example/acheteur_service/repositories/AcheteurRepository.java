package com.example.acheteur_service.repositories;

import com.example.acheteur_service.entitise.Acheteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcheteurRepository extends JpaRepository<Acheteur,Integer> {
}
