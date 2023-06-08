package com.example.demo.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.dto.ErrorDto;
import com.example.demo.exceptions.PersonNotFoundException;
import com.example.demo.exceptions.PersonToCreateHasAnIdException;
import com.example.demo.exceptions.PersonToUpdateHasNoIdException;

@RestControllerAdvice

// Méthode qui va intercepter les 3 exceptions créées
public class ExceptionHandlerControllerAdvice {
	@ExceptionHandler({ 
		jakarta.persistence.EntityNotFoundException.class,
		PersonNotFoundException.class,
		PersonToCreateHasAnIdException.class,
		PersonToUpdateHasNoIdException.class
		})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDto handleExceptionNotFound(Exception exception, WebRequest request) {
		exception.printStackTrace();
		return new ErrorDto(
				HttpStatus.BAD_REQUEST.value(), 
				LocalDateTime.now(), 
				exception.getMessage(),
				request.getDescription(false));
	}
	
	
	// Méthode qui va intercepter l’erreur qui est lancée par Spring lors d’une validation qui échoue
	@ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleValidationExceptions (MethodArgumentNotValidException ex, WebRequest request) {
		ex.printStackTrace();
        StringBuilder message = new StringBuilder();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            message.append(fieldName + ": " + errorMessage + "\n");
        });

        return new ErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                message.toString(),
                request.getDescription(false)
        );
    }
}
