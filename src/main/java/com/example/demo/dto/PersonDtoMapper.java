package com.example.demo.dto;

import org.springframework.stereotype.Component;

import com.example.demo.model.Person;

@Component
public class PersonDtoMapper {

	public PersonDto toDto(Person person) {
		PersonDto dto = new PersonDto();
		dto.setId(person.getId());
		dto.setNomAge(person.getFirstname() + " " + person.getLastname() + " (" + person.getAge() + ")");

		if (person.getAnimals() != null) {
			dto.setAnimaux(person.getAnimals().stream()
					.map(animal -> animal.getName() + " (" + animal.getSpecies() + ")").toArray(String[]::new));
		}

		return dto;
	}
}
