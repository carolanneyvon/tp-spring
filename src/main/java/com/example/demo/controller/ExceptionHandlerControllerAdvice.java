package com.example.demo.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.dto.ErrorDto;
import com.example.demo.exceptions.PersonNotFoundException;
import com.example.demo.exceptions.PersonToCreateHasAnIdException;
import com.example.demo.exceptions.PersonToUpdateHasNoIdException;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {
	@ExceptionHandler({ 
		jakarta.persistence.EntityNotFoundException.class,
		PersonNotFoundException.class,
		PersonToCreateHasAnIdException.class,
		PersonToUpdateHasNoIdException.class
		})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorDto handleExceptionNotFound(Exception exception, WebRequest request) {
		exception.printStackTrace();
		return new ErrorDto(
				HttpStatus.BAD_REQUEST.value(), 
				LocalDateTime.now(), 
				exception.getMessage(),
				request.getDescription(false));
	}
}
