package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Animal;
import com.example.demo.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>, PersonRepositoryCustom {
	//TP 04
	//Requête qui retourne les personnes ayant pour nom le premier paramètre fourni ou ayant pour prénom le second paramètre fourni
	List<Person> findByFirstnameOrLastname(String firstname, String lastname);
	
	//Requête qui retourne toutes les personnes d’un âge supérieur ou égal au paramètre fourni
	List<Person> findByAgeGreaterThanEqual(int age);
	
	//TP 05 @query
	//méthode qui va chercher les Personnes dont l’âge est entre « age min » et « age max »
	@Query(value = "from Person where age between :minAge and :maxAge")
	List<Person> findBetweenMinAgeAndMaxAge(@Param("minAge") int minAge, @Param("maxAge")  int maxAge);
	
	//OPTION difficile + -> méthode qui va chercher toutes les Personnes qui possèdent l’animal donné en paramètre
	@Query("SELECT DISTINCT p FROM Person p JOIN p.animals a WHERE a = :animal")
	List<Person> findAllPersonsByAnimal(@Param("animal") Animal animal);
}
