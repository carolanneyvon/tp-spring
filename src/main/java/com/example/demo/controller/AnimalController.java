package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Animal;
import com.example.demo.service.AnimalService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/animal")
public class AnimalController {
	@Autowired
	AnimalService animalService;

	// Create
	@PostMapping
	public Animal createAnimal(@RequestBody @Valid Animal animalToCreate) {
		return animalService.create(animalToCreate);
	}

	// Update
	@PutMapping("{id}")
	public Animal updateAnimal(@PathVariable Integer id, @RequestBody @Valid Animal animalToUpdate) {
		return animalService.update(animalToUpdate);
	}

	// Delete
	@DeleteMapping("{id}")
	public void deleteAnimal(@PathVariable Integer id) {
		animalService.deleteById(id);
	}

	// FindAll
	@GetMapping
	public List<Animal> findAll() {
		return animalService.findAll();
	}

	// FindById
	@GetMapping("/{id}") // localhost:8080/rest/animal/{id}
	public Animal findById(@PathVariable Integer id) {
		return animalService.findById(id);
	}
}
