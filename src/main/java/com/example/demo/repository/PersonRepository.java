package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
	//Requête qui retourne les personnes ayant pour nom le premier paramètre fourni ou ayant pour prénom le second paramètre fourni
	List<Person> findByFirstnameOrLastname(String firstname, String lastname);
	
	//Requête qui retourne toutes les personnes d’un âge supérieur ou égal au paramètre fourni
	List<Person> findByAgeGreaterThanEqual(int age);
}
