package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Species;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {
	//Requête qui retourne la première Species dont le nom commun est égal au paramètre fourni
	List<Species> findFirstByCommonName(String commonName);
	
	//Requête qui retourne une liste de Species dont le nom latin contient le paramètre fourni en ignorant la casse
	List<Species> findByLatinNameContainsAllIgnoreCase(String query);
}
