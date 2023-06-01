package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
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
		
		// TP 3
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
		
		// TP 4
		//Première espece dont le nom commun est égal au le parametre
		System.out.println("Première espece: " + speciesRepository.findFirstByCommonName("Tortue").get(0).getCommonName());
		
		//Liste d'espece nom latin contient parametre
		System.out.println("Nom latin: " + speciesRepository.findByLatinNameContainsAllIgnoreCase("CatuS").get(0).getCommonName());
		
		//Personne ayant pour nom le premier ou second parametre
		System.out.println("Premier parametre: " + personRepository.findByFirstnameOrLastname("Sophie", null).get(0).getFirstname());
		System.out.println("Second parametre: " + personRepository.findByFirstnameOrLastname(null, "Nero").get(0).getLastname());
		
		//Personnes ayant une age supérieur au parametre
		List<Person> resAgePlusGrand = personRepository.findByAgeGreaterThanEqual(26);
		System.out.println("Il y a " + resAgePlusGrand.size() + " personnes de plus de 26 ans");
			//liste complète
		for (Person person : resAgePlusGrand) {
			System.out.println(person.getFirstname() + " " + person.getLastname());
		}
		
		//Tous les animaux de l'espece en parametre
		List<Species> speciesList = new ArrayList<>();
		for (Species specie : species) {
			speciesList.add(specie);
		}
		List<Animal> resAnimal = animalRepository.findBySpecies(speciesList.get(0));// 0-> id 1 = chat
		resAnimal.forEach(a -> System.out.println("Nom de l'animal pour l'espece: " + a.getName()));
		
		//Animaux dont la couleur fait partie de la liste
		List<String> colors = new ArrayList<>(Arrays.asList("Brun", "Roux", "Rose"));
		List<Animal> resAnimalColor = animalRepository.findByColorIn(colors);
		resAnimalColor.forEach(c -> System.out.println("Nom de l'animal pour la couleur: " + c.getName()));
	}
		
}
