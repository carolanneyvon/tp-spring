package com.example.demo.controller;

//import java.util.List;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/person")
public class PersonController {

	@Autowired
	PersonService personService;

//	// Create
//	@PostMapping
//	public Person createPerson(@RequestBody @Valid Person personToCreate) {
//		return personService.create(personToCreate);
//	}
	
	// Create avec gestion des exceptions -> entité sans id
	@PostMapping
	public Person createPerson(@RequestBody @Valid Person personToCreate) {
		if(personToCreate.getId() != null) {
	        throw new IllegalArgumentException("Un ID ne doit pas être spécifié lors de la création d'une personne.");
	    }
		return personService.create(personToCreate);
	}

	// Update
//	@PutMapping("{id}")
//	public Person updatePerson(@PathVariable Integer id, @RequestBody @Valid Person personToUpdate) {
//		return personService.update(personToUpdate);
//	}
	
	// Update avec gestion des exceptions -> entité sans id
	@PutMapping("{id}")
	public Person updatePerson(@PathVariable Integer id, @RequestBody @Valid Person personToUpdate) {
		if(personToUpdate.getId() == null) {
	        throw new IllegalArgumentException("L'ID est nécessaire pour mettre à jour une personne.");
	    }
		return personService.update(personToUpdate);
	}
	
	// Exception Handler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ MethodArgumentNotValidException.class, IllegalArgumentException.class })
	public String handleException(Exception e) {
	    return e.getMessage();
	}

	// Delete
	@DeleteMapping("{id}")
	public void deletePerson(@PathVariable Integer id) {
		personService.deleteById(id);
	}

	// FindAll
//	@GetMapping
//	public List<Person> findAll() {
//		return personService.findAll();
//	}
	
	// FindALL avec une liste paginée
	// localhost:8080/rest/person/page?page=1&size=2 -> page numéro 2 et de taille 2
	@GetMapping("page") 
	public Page<Person> findAll(Pageable pageable) {
	    return personService.findAll(pageable);
	}

	// FindById
	@GetMapping("/{id}") // localhost:8080/rest/person/{id}
	public Person findById(@PathVariable Integer id) {
		return personService.findById(id);
	}
}
