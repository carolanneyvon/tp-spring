package com.example.demo.repository;

public interface PersonRepositoryCustom {
	public void deletePersonWithoutAnimal();
	
	public void addPerson(Integer nbPersonnes);
	
	//(en plus du TP 06)
	public void addPersonWithAnimal (Integer nbPersonnes);
}
