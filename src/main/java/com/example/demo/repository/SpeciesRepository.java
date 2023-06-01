package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Species;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {
	//TP 04
	//Requête qui retourne la première Species dont le nom commun est égal au paramètre fourni
	List<Species> findFirstByCommonName(String commonName);
	
	//Requête qui retourne une liste de Species dont le nom latin contient le paramètre fourni en ignorant la casse
	List<Species> findByLatinNameContainsAllIgnoreCase(String query);
	
	//TP 05 @query
	//méthode qui va aller chercher toutes les Species, ordonnées par nom commun ascendant
	@Query(value = "SELECT * FROM species ORDER BY common_name ASC", nativeQuery = true)
	List<Species> findAllOrderedByCommonNameAscSql();
	
	//méthode qui retourne les Species avec un nom commun LIKE le paramètre fourni
	@Query(value = "from Species where commonName like %:commonName%")
	List<Species> findByCommonNameLikeJpql(@Param("commonName") String commonName);
}
