package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.demo.enums.Sex;
import com.example.demo.model.Animal;
import com.example.demo.model.Person;
import com.example.demo.model.Species;
import com.github.javafaker.Faker;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

public class PersonRepositoryImpl implements PersonRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void deletePersonWithoutAnimal() {

		//Recupere les personnes sans animaux
		TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.animals IS EMPTY", Person.class);
		List<Person> persons = query.getResultList();
		System.out.println("Nombre de personnes sans animaux : " + persons.size());

		//Supprime les personnes sans animaux
		persons.forEach(p -> {
			System.out.println(p.getFirstname() + " " + p.getLastname() + " est supprimé de la base");
			em.remove(p);
		});

	}

	@Override
	@Transactional
	public void addPerson(Integer nbPersonnes) {

		//Génére des noms aléatoire
		Faker faker = new Faker();

		for (int i = 0; i < nbPersonnes; i++) {
			Person person = new Person();
			person.setFirstname(faker.name().firstName());
			person.setLastname(faker.name().lastName());
			em.persist(person);
		}

		System.out.println("Nombre de personnes ajouté : " + nbPersonnes);

	}
	
	
	//(en plus du TP 06)
	@Override
	@Transactional
	public void addPersonWithAnimal(Integer nbPersonnes) {

		//Génére des noms aléatoire
		Faker faker = new Faker();

		for (int i = 0; i < nbPersonnes; i++) {
			Person person = new Person();
			person.setFirstname(faker.name().firstName());
			person.setLastname(faker.name().lastName());
			
	        // Crée un nouvel animal
	        Animal animal = new Animal();
	        animal.setName(faker.animal().name());
	        
	        // Assigner un sexe à l'animal
	        Sex[] sexes = Sex.values();
	        Sex randomSex = sexes[new Random().nextInt(sexes.length)];
	        animal.setSex(randomSex);
	        //em.persist(animal);

	        // Ajoute l'animal à la liste des animaux de la personne
	        List<Animal> animals = new ArrayList<>();
	        animals.add(animal);
	        person.setAnimals(animals);
	        
	        // Crée une nouvelle espèce
	        Species species = new Species();
	        species.setCommonName("TestCommun"); 
	        species.setLatinName("TestLatin"); 

	        // Sauvegardez l'espèce
	        em.persist(species);

	        // Assigner l'espèce à l'animal
	        animal.setSpecies(species);

	        // Sauvegardez l'animal
	        em.persist(animal);

	        em.persist(person);
	    }
		System.out.println("Nombre de personnes avec un animal ajouté : " + nbPersonnes);

	}
}
