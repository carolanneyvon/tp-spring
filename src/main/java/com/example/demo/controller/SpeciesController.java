package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Species;
import com.example.demo.repository.SpeciesRepository;

import jakarta.validation.Valid;

@Controller
public class SpeciesController {
	@Autowired
	private SpeciesRepository speciesRepository;

	@GetMapping("species")
	public String getListSpecies(Model model) {
		// 1ere etape : récup les species via le repo
		List<Species> speciesList = speciesRepository.findAll();

		// 2eme etape : mettre la liste dans ce Model
		model.addAttribute("speciesList", speciesList);

		// 3eme etape : retourne la vue
		return "species/list_species";
	}

	@GetMapping("species/{id}")
	public String getDetailSpecies(@PathVariable("id") Integer id, Model model) {
		Optional<Species> speciesOpt = speciesRepository.findById(id);

		if (speciesOpt.isEmpty()) {
			// page d'erreur car pas de species avec l'id renseigné
			return "error";
		}

		model.addAttribute("species", speciesOpt.get());

		return "species/detail_species";
	}

	@GetMapping("species/create")
	public String getCreateSpecies(Model model) {

		model.addAttribute("species", new Species());

		return "species/create_species";
	}

//  Avant mise ne place de la validation
//	@PostMapping("/species")
//	public String createOrUpdate(Species speciesList) {
//		speciesRepository.save(speciesList);
//		return "redirect:/species";
//	}
	
	@PostMapping("/species")
	public String createOrUpdate(@Valid Species species, BindingResult result, Model model) {
		if (result.hasErrors()) {
			if (species.getId() != null) {
				return "species/detail_species";
			}
			return "species/create_species";
		}
		speciesRepository.save(species);
		return "redirect:/species";
	}

	@GetMapping("/species/delete/{id}")
	public String deleteSpecies(@PathVariable("id") Integer speciesId) {
//	Optional<Species> speciesToDelete = this.speciesRepository.findById(speciesId);
//	speciesToDelete.ifPresent(species -> this.speciesRepository.delete(species));
		speciesRepository.deleteById(speciesId);
		return "redirect:/species";
	}
}
