package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PersonDto;
import com.example.demo.exceptions.PersonNotFoundException;
import com.example.demo.exceptions.PersonToCreateHasAnIdException;
import com.example.demo.exceptions.PersonToUpdateHasNoIdException;
import com.example.demo.model.Animal;
import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.dto.PersonDtoMapper;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	PersonDtoMapper personDtoMapper;

	// Create
	public Person create(@Valid Person personToCreate) {
		if (personToCreate.getId() != null) {
	        throw new PersonToCreateHasAnIdException();
	    }
		return personRepository.save(personToCreate);
	}

	// Update
	public Person update(@Valid Person personToUpdate) {
		if (personToUpdate.getId() == null) {
	        throw new PersonToUpdateHasNoIdException();
	    }
		return personRepository.save(personToUpdate);
	}
	
	// Save pour test AOP
	public Person save(@Valid Person personToSave) {
	        throw new RuntimeException();
	}

	// Delete
	public void deleteById(Integer id) {
		if (!personRepository.existsById(id)) {
			throw new PersonNotFoundException();
		}
		personRepository.deleteById(id);
	}

	// FindAll
//	public List<Person> findAll() {
//		return personRepository.findAll();
//	}
	
	// FindAll avec PersonDto
	public List<PersonDto> findAll() {
		return personRepository.findAll()
				.stream()
		        .map((person)-> personDtoMapper.toDto(person))
		        .collect(Collectors.toList());
	}
	
	// FindALL avec une liste paginée
	public Page<Person> findAllPageable(Pageable pageable) {
	    return personRepository.findAll(pageable);
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
