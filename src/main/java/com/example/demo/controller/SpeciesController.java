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

import com.example.demo.model.Species;
import com.example.demo.service.SpeciesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/species")
public class SpeciesController {
	@Autowired
	SpeciesService speciesService;

	// Create
	@PostMapping
	public Species createSpecies(@RequestBody @Valid Species speciesToCreate) {
		return speciesService.create(speciesToCreate);
	}

	// Update
	@PutMapping("{id}")
	public Species updateSpecies(@PathVariable Integer id, @RequestBody @Valid Species speciesToUpdate) {
		return speciesService.update(speciesToUpdate);
	}

	// Delete
	@DeleteMapping("{id}")
	public void deleteSpecies(@PathVariable Integer id) {
		speciesService.deleteById(id);
	}

	// FindAll
	@GetMapping
	public List<Species> findAll() {
		return speciesService.findAll();
	}

	// FindById
	@GetMapping("/{id}") // localhost:8080/rest/species/{id}
	public Species findById(@PathVariable Integer id) {
		return speciesService.findById(id);
	}
}
