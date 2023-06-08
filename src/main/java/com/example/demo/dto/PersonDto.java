package com.example.demo.dto;

//import com.example.demo.model.Person;

public class PersonDto {
	private Integer id;
	private String nomAge;
	private String[] animaux;


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

	/** Getter pour l'attribut nomAge
	 * @return the nomAge
	 */
	public String getNomAge() {
		return nomAge;
	}

	/** Setter pour l'attribut nomAge
	 * @param nomAge the nomAge to set
	 */
	public void setNomAge(String nomAge) {
		this.nomAge = nomAge;
	}

	/** Getter pour l'attribut animaux
	 * @return the animaux
	 */
	public String[] getAnimaux() {
		return animaux;
	}

	/** Setter pour l'attribut animaux
	 * @param animaux the animaux to set
	 */
	public void setAnimaux(String[] animaux) {
		this.animaux = animaux;
	}
	
	

}
