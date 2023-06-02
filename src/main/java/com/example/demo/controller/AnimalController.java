package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.Animal;
import com.example.demo.repository.AnimalRepository;



@Controller
public class AnimalController {
	@Autowired
	private AnimalRepository animalRepository;
	
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
	public String getDetailAnimal(@PathVariable("id")Integer id, Model model) {
		Optional<Animal> animalOpt = animalRepository.findById(id);
		
		if (animalOpt.isEmpty()) {
			//page d'erreur car pas de animal avec l'id renseigné
			return "error";
		}
		
		model.addAttribute("animalDetail", animalOpt.get());

		return "animal/detail_animal";
	}
	
	@GetMapping("animal/create")
	public String getCreateAnimal(Model model) {

		model.addAttribute("animalCreate", new Animal());

		return "animal/create_animal";
	}
}
