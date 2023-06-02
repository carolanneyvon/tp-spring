package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.enums.Sex;
import com.example.demo.model.Animal;
import com.example.demo.model.Species;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
	//TP 04
	//Requête qui renvoie tous les animaux de la Species fournie en paramètre
	List<Animal> findBySpecies(Species species);

	//Requête qui renvoie tous les animaux dont la couleur fait partie de la liste de couleurs fournie
	List<Animal> findByColorIn(List<String> colors);
	
	//TP 05 @query
	//Renvoie le nombre d’Animaux dont le Sex est égal à la valeur donnée en paramètres
	@Query(value = "SELECT COUNT(a) FROM Animal a where a.sex = :sex ")
	Integer countAnimalsBySex(@Param("sex")Sex sex);
	
	//OPTION difficile ++ -> Renvoie un booléen si l’animal fourni « appartient » à au moins une personne
	@Query("SELECT CASE WHEN (COUNT(a)>0) THEN true ELSE false END FROM Person p JOIN p.animals a WHERE a = :animal")
	boolean animalPerson(@Param("animal") Animal animal);
}
