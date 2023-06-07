package com.example.demo.dto;

import java.time.LocalDateTime;

public class ErrorDto {
	private final int statusCode;
	private final LocalDateTime localDateTime;
	private final String message;
	private final String description;


	/** Constructeur
	 * @param statusCode
	 * @param localDateTime
	 * @param message
	 * @param description
	 */
	public ErrorDto(int statusCode, LocalDateTime localDateTime, String message, String description) {
		super();
		this.statusCode = statusCode;
		this.localDateTime = localDateTime;
		this.message = message;
		this.description = description;
	}


	/** Getter pour l'attribut statusCode
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}


	/** Getter pour l'attribut localDateTime
	 * @return the localDateTime
	 */
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}


	/** Getter pour l'attribut message
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}


	/** Getter pour l'attribut description
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	

}
