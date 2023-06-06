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

import com.example.demo.model.Animal;
import com.example.demo.model.Person;
import com.example.demo.repository.AnimalRepository;
import com.example.demo.repository.SpeciesRepository;

import jakarta.validation.Valid;

@Controller
public class AnimalController {
	@Autowired
	private AnimalRepository animalRepository;
	@Autowired
	private SpeciesRepository speciesRepository;

	@GetMapping("animal")
	public String getListAnimal(Model model) {
		// 1ere etape : récup les animal via le repo
		List<Animal> animalList = animalRepository.findAll();

		// 2eme etape : mettre la liste dans ce Model
		model.addAttribute("animalList", animalList);

		// 3eme etape : retourne la vue
		return "animal/list_animal";
	}

	@GetMapping("animal/{id}")
	public String getDetailAnimal(@PathVariable("id") Integer id, Model model) {
		Optional<Animal> animalOpt = animalRepository.findById(id);

		if (animalOpt.isEmpty()) {
			// page d'erreur car pas de animal avec l'id renseigné
			return "error";
		}

		model.addAttribute("animal", animalOpt.get());
		model.addAttribute("speciesList", speciesRepository.findAll(Sort.by(Sort.Direction.ASC, "commonName")));

		return "animal/detail_animal";
	}

	@GetMapping("animal/create")
	public String getCreateAnimal(Model model) {

		model.addAttribute("animal", new Animal());
		model.addAttribute("speciesList", speciesRepository.findAll(Sort.by(Sort.Direction.ASC, "commonName")));

		return "animal/create_animal";
	}

//  Avant mise ne place de la validation
//	@PostMapping("/animal")
//	public String createOrUpdate(Animal animal) {
//		animalRepository.save(animal);
//		return "redirect:/animal";
//	}

	@PostMapping("/animal")
	public String createOrUpdate(@Valid Animal animal, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("speciesList", speciesRepository.findAll());
			if (animal.getId() != null) {
				return "animal/detail_animal";
			}
			return "animal/create_animal";
		}
		animalRepository.save(animal);
		return "redirect:/animal";
	}

	@GetMapping("/animal/delete/{id}")
	public String deleteAnimal(@PathVariable("id") Integer animalId) {
		Optional<Animal> animalToDeleteOpt = animalRepository.findById(animalId);

		if (animalToDeleteOpt.isPresent()) {
			Animal animalToDelete = animalToDeleteOpt.get();

			// Pour chaque personne liée à cet animal, supprimez l'animal de la liste
			for (Person person : animalToDelete.getPerson()) {
				person.getAnimals().remove(animalToDelete);
			}

			// Ensuite, supprimez l'animal
			animalRepository.delete(animalToDelete);
		}

		return "redirect:/animal";
	}

}
