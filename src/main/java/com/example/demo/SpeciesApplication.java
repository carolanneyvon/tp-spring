package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Animal;
import com.example.demo.model.Person;
import com.example.demo.model.Species;
import com.example.demo.repository.AnimalRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.SpeciesRepository;

@SpringBootApplication
public class SpeciesApplication implements CommandLineRunner {
	@Autowired
	private AnimalRepository animalRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private SpeciesRepository speciesRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpeciesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// Afficher la liste des entités avec findAll
		List<Animal> animals = this.animalRepository.findAll();
		System.out.println("Liste des animaux: " + animals);
		List<Person> persons = this.personRepository.findAll();
		System.out.println("Liste des personnes: " + persons);
		List<Species> species = this.speciesRepository.findAll();
		System.out.println("Liste des espéces: " + species);
		
		// Créer une entité avec la méthode save
		Species nouvelleEspece = new Species();
		nouvelleEspece.setCommonName("Tortue");
		nouvelleEspece.setLatinName("Testudines");
		this.speciesRepository.save(nouvelleEspece);
		System.out.println("Nouvelle espece: " + nouvelleEspece);
		
		// Rechercher une entité par son id avec findById
		Optional<Species> speciesOptional = this.speciesRepository.findById(1);
		if (speciesOptional.isPresent()) {
			speciesOptional.get();
		}
		System.out.println("Espece id= 1 : " + speciesOptional);
		
		// Supprimer une entité avec delete
		this.speciesRepository.delete(nouvelleEspece);
			// autre méthode
		//this.speciesRepository.deleteById(nouvelleEspece.getId());
	}
		
}
