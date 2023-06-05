package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Species;
import com.example.demo.repository.SpeciesRepository;


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
	public String getDetailSpecies(@PathVariable("id")Integer id, Model model) {
		Optional<Species> speciesOpt = speciesRepository.findById(id);
		
		if (speciesOpt.isEmpty()) {
			//page d'erreur car pas de species avec l'id renseigné
			return "error";
		}
		
		model.addAttribute("speciesDetail", speciesOpt.get());

		return "species/detail_species";
	}
	
	@GetMapping("species/create")
	public String getCreateSpecies(Model model) {

		model.addAttribute("speciesCreate", new Species());

		return "species/create_species";
	}
	
//	@PostMapping("/species")
//	public String createOrUpdate(Species speciesList) {
//		this.speciesRepository.save(speciesList);
//		return "redirect:/species";
//	}
	
//	@GetMapping("/species/delete/{id}")
//	public String delete(@PathVariable("id") Integer speciesId) {
//	Optional<Species> speciesToDelete = this.speciesRepository.findById(speciesId);
//	speciesToDelete.ifPresent(species -> this.speciesRepository.delete(species));
//	return "redirect:/species";
//	}
}
