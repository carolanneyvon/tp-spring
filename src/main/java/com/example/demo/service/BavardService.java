package com.example.demo.service;

import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

@Service
public class BavardService {

    private String nom = "Doe";

    public void parler() {
        System.out.println("Mon nom est " + nom + " et je suis un " + this.getClass().getSimpleName());
    }
    
    @PostConstruct
    private void postConstruct() {
        System.out.println("La méthode postConstruct a été appelée");
    }
    
    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
