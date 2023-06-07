package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Species;
import com.example.demo.repository.SpeciesRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class SpeciesService {
	@Autowired
	private SpeciesRepository speciesRepository;

	// Create
	public Species create(@Valid Species speciesToCreate) {
		return speciesRepository.save(speciesToCreate);
	}

	// Update
	public Species update(@Valid Species speciesToUpdate) {
		return speciesRepository.save(speciesToUpdate);
	}

	// Delete
	public void deleteById(Integer id) {
		speciesRepository.deleteById(id);
	}

	// FindAll
	public List<Species> findAll() {
		return speciesRepository.findAll();
	}

	// FindById
	public Species findById(Integer id) {
		return speciesRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	// Méthodes "passe-plat"
	public List<Species> findFirstByCommonName(String commonName) {
		return speciesRepository.findByCommonNameLikeJpql(commonName);
	}

	public List<Species> findByLatinNameContainsAllIgnoreCase(String query) {
		return speciesRepository.findByLatinNameContainsAllIgnoreCase(query);
	}

	public List<Species> findAllOrderedByCommonNameAscSql() {
		return speciesRepository.findAllOrderedByCommonNameAscSql();
	}

	public List<Species> findByCommonNameLikeJpql(String commonName) {
		return speciesRepository.findByCommonNameLikeJpql(commonName);
	}
}
