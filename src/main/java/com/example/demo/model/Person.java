package com.example.demo.model;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "person")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 50)
	private Integer age;

	@Column(length = 50)
	private String firstname;

	@Column(length = 50)
	private String lastname;

	@ManyToMany (fetch = FetchType.EAGER)
	List<Animal> animals;
		
	@Override
	public String toString() {
		return "Person [id= " + id + ", age= " + age + ", firstname= " + firstname + ", lastname= " + lastname
				+ ", animals= " + animals + "]";
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

	/** Getter pour l'attribut age
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/** Setter pour l'attribut age
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/** Getter pour l'attribut firstname
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/** Setter pour l'attribut firstname
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/** Getter pour l'attribut lastname
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/** Setter pour l'attribut lastname
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	
	
}
