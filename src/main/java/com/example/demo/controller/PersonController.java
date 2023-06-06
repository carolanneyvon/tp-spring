package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Animal;
import com.example.demo.model.Person;
import com.example.demo.repository.AnimalRepository;
import com.example.demo.repository.PersonRepository;

import jakarta.validation.Valid;

@Controller
public class PersonController {
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AnimalRepository animalRepository;

	@GetMapping("person")
	public String getListPerson(Model model) {
		// 1ere etape : récup les person via le repo
		List<Person> personList = personRepository.findAll();

		// 2eme etape : mettre la liste dans ce Model
		model.addAttribute("personList", personList);

		// 3eme etape : retourne la vue
		return "person/list_person";
	}

	@GetMapping("person/{id}")
	public String getDetailPerson(@PathVariable("id") Integer id, Model model) {
		Optional<Person> personOpt = personRepository.findById(id);

		if (personOpt.isEmpty()) {
			// page d'erreur car pas de person avec l'id renseigné
			return "error";
		}

		model.addAttribute("person", personOpt.get());
		model.addAttribute("animalList", animalRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));

		return "person/detail_person";
	}

	@GetMapping("person/create")
	public String getCreatePerson(Model model) {

		model.addAttribute("person", new Person());
		model.addAttribute("animalList", animalRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));

		return "person/create_person";
	}

	// pour generer directement des personnes en aleatoire
	@GetMapping("person/generate")
	public String generateRandomPerson(@RequestParam("nb") Integer nbToCreate) {
		personRepository.addPerson(nbToCreate);
		return "redirect:/person";
	}

// Avant mise en place de la validation	
//	@PostMapping("/person")
//	public String createOrUpdate(Person person) {
//		personRepository.save(person);
//		return "redirect:/person";
//	}

	@PostMapping("/person")
	public String createOrUpdate(@Valid Person person, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("animalList", animalRepository.findAll());
			if (person.getId() != null) {
				return "person/detail_person";
			}
			return "person/create_person";
		}
		personRepository.save(person);
		return "redirect:/person";
	}

	@GetMapping("/person/delete/{id}")
	public String deletePerson(@PathVariable("id") Integer personId) {
		Optional<Person> personToDeleteOpt = personRepository.findById(personId);

		if (personToDeleteOpt.isPresent()) {
			Person personToDelete = personToDeleteOpt.get();

			// Pour chaque animal liée à cet personne, supprimez la personne de la liste
			for (Animal animal : personToDelete.getAnimals()) {
				animal.getPerson().remove(personToDelete);
			}

			// Ensuite, supprimez la personne
			personRepository.delete(personToDelete);
		}

		return "redirect:/person";
	}
}
