package com.example.vente_service.acheteurs;

import com.example.vente_service.module.Acheteur;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="ACHETEUR-SERVICE")
public interface AcheteurOpenFeing {
    @GetMapping("/acheteurs")
    public List<Acheteur> getAll();

    @GetMapping("/acheteurs/{id}")
    public Acheteur getById(@PathVariable Integer id);
}
