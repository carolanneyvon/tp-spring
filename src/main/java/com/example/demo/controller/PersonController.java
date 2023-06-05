package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.Person;
import com.example.demo.repository.AnimalRepository;
import com.example.demo.repository.PersonRepository;


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
	public String getDetailPerson(@PathVariable("id")Integer id, Model model) {
		Optional<Person> personOpt = personRepository.findById(id);
		
		if (personOpt.isEmpty()) {
			//page d'erreur car pas de person avec l'id renseigné
			return "error";
		}
		
		model.addAttribute("personDetail", personOpt.get());
		model.addAttribute(
				"animalList",
				animalRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))
				);

		return "person/detail_person";
	}
	
	@GetMapping("person/create")
	public String getCreatePerson(Model model) {

		model.addAttribute("personCreate", new Person());
		model.addAttribute(
				"animalList",
				animalRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))
				);

		return "person/create_person";
	}
}
