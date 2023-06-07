package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

// L’utilisateur ne passe pas d’ID lors de la mise à jour d’une entité

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PersonToUpdateHasNoIdException extends RuntimeException {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	public PersonToUpdateHasNoIdException() {
		super("La personne à mettre à jour doit avoir un ID.");
	}
}
