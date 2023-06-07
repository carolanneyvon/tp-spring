package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.enums.Sex;
import com.example.demo.model.Animal;
import com.example.demo.model.Species;
import com.example.demo.repository.AnimalRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class AnimalService {
	@Autowired
	private AnimalRepository animalRepository;

	// Create
	public Animal create(@Valid Animal animalToCreate) {
		return animalRepository.save(animalToCreate);
	}

	// Update
	public Animal update(@Valid Animal animalToUpdate) {
		return animalRepository.save(animalToUpdate);
	}

	// Delete
	public void deleteById(Integer id) {
		animalRepository.deleteById(id);
	}

	// FindAll
	public List<Animal> findAll() {
		return animalRepository.findAll();
	}

	// FindById
	public Animal findById(Integer id) {
		return animalRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	// Méthodes "passe-plat"
	public List<Animal> findBySpecie(@Valid Species specie) {
		return animalRepository.findBySpecies(specie);
	}

	public List<Animal> findByColorIn(List<String> colors) {
		return animalRepository.findByColorIn(colors);
	}

	public Integer countAnimalsBySex(Sex sex) {
		return animalRepository.countAnimalsBySex(sex);
	}
}
