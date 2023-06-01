package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Animal;
import com.example.demo.model.Species;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
	//Requête qui renvoie tous les animaux de la Species fournie en paramètre
	List<Animal> findBySpecies(Species species);

	//Requête qui renvoie tous les animaux dont la couleur fait partie de la liste de couleurs fournie
	List<Animal> findByColorIn(List<String> colors);
}
