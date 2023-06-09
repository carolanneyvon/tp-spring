package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

// L’utilisateur passe un ID lors de la création d’une entité
// Mis pour personne pour le TP mais Exceptions qui s'applique à toutes les entités 
// Renommer EntityNotFoundException et rajouter l'exception sur les autres services

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PersonToCreateHasAnIdException extends RuntimeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	public PersonToCreateHasAnIdException() {
		super("La personne à créer ne devrait pas avoir d'ID.");
	}
}
