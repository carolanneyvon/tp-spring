package com.example.demo.model;

import java.util.List;

import com.example.demo.enums.Sex;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "animal")
public class Animal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 50)
	private String color;

	@Column(length = 50)
	private String name;

	@Enumerated(EnumType.STRING)
	private Sex sex;

	@ManyToOne
	private Species species;
	
	@ManyToMany(mappedBy = "animals")
	private List<Person> person;
	
	@Override
	public String toString() {
		return "Animal [id= " + id + ", couleur= " + color + ", nom= " + name + ", sex= " + sex + ", espece= " + species
				+ "]";
	}

	/** Getter pour l'attribut id
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/** Setter pour l'attribut id
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/** Getter pour l'attribut color
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/** Setter pour l'attribut color
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/** Getter pour l'attribut name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/** Setter pour l'attribut name
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** Getter pour l'attribut sex
	 * @return the sex
	 */
	public Sex getSex() {
		return sex;
	}

	/** Setter pour l'attribut sex
	 * @param sex the sex to set
	 */
	public void setSex(Sex sex) {
		this.sex = sex;
	}

	/** Getter pour l'attribut specie
	 * @return the specie
	 */
	public Species getSpecies() {
		return species;
	}

	/** Setter pour l'attribut specie
	 * @param specie the specie to set
	 */
	public void setSpecies(Species species) {
		this.species = species;
	}

	/** Getter pour l'attribut persons
	 * @return the persons
	 */
	public List<Person> getPerson() {
		return person;
	}

	/** Setter pour l'attribut persons
	 * @param persons the persons to set
	 */
	public void setPerson(List<Person> person) {
		this.person = person;
	}
	
	
	
}
