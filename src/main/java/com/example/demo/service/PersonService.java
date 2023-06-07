package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Animal;
import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	// Create
	public Person create(@Valid Person personToCreate) {
		return personRepository.save(personToCreate);
	}

	// Update
	public Person update(@Valid Person personToUpdate) {
		return personRepository.save(personToUpdate);
	}

	// Delete
	public void deleteById(Integer id) {
		personRepository.deleteById(id);
	}

	// FindAll
	public List<Person> findAll() {
		return personRepository.findAll();
	}

	// FindById
	public Person findById(Integer id) {
		return personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	// Méthodes "passe-plat"

	public List<Person> findByFirstnameOrLastname(String firstname, String lastname) {
		return personRepository.findByFirstnameOrLastname(firstname, lastname);
	}

	public List<Person> findByAgeGreaterThanEqual(int age) {
		return personRepository.findByAgeGreaterThanEqual(age);
	}

	public List<Person> findBetweenMinAgeAndMaxAge(int minAge, int maxAge) {
		return personRepository.findBetweenMinAgeAndMaxAge(minAge, maxAge);
	}

	public List<Person> findAllPersonsByAnimal(Animal animal) {
		return personRepository.findAllPersonsByAnimal(animal);
	}

}
