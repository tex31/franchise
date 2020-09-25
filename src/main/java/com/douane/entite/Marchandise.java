package com.douane.entite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Marchandise implements Serializable{
	
	@Id
	@SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="marchandise_seq", name="marchandise_seq")
	@GeneratedValue(generator="marchandise_seq", strategy=GenerationType.SEQUENCE)
	private Long idMarchandise;
	private String nom;
	private String description;
	
	public Marchandise() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Marchandise(String nom, String description) {
		super();
		this.nom = nom;
		this.description = description;
	}

	public Long getId() {
		return idMarchandise;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
