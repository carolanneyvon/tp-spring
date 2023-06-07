package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

// L’utilisateur passe un ID lors de la création d’une entité

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PersonToCreateHasAnIdException extends RuntimeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	public PersonToCreateHasAnIdException() {
		super("La personne à créer ne devrait pas avoir d'ID.");
	}
}
