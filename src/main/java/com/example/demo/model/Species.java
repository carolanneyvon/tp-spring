package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "species")
public class Species {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 50)
	//essaie de trouver une colonne qui a le nom commonName
	private String commonName;

	@Column(length = 200)
	private String latinName;

	@Override
	public String toString() {
		return "Species [id= " + id + ", nomCommun= " + commonName + ", nomLatin= " + latinName + "]";
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

	/** Getter pour l'attribut commonName
	 * @return the commonName
	 */
	public String getCommonName() {
		return commonName;
	}

	/** Setter pour l'attribut commonName
	 * @param commonName the commonName to set
	 */
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	/** Getter pour l'attribut latinName
	 * @return the latinName
	 */
	public String getLatinName() {
		return latinName;
	}

	/** Setter pour l'attribut latinName
	 * @param latinName the latinName to set
	 */
	public void setLatinName(String latinName) {
		this.latinName = latinName;
	}
	
	
}
