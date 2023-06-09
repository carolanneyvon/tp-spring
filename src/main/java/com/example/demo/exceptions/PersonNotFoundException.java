package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

// L’utilisateur accède à une ressource non trouvée
// Mis pour personne pour le TP mais Exceptions qui s'applique à toutes les entités 
// Renommer EntityNotFoundException et rajouter l'exception sur les autres services

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PersonNotFoundException extends RuntimeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	public PersonNotFoundException() {
		super("Personne avec cet ID non trouvée.");
	}
}
