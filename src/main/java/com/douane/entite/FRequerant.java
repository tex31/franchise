package com.douane.entite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class FRequerant implements Serializable{
	@Id
	@SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="requerant_seq", name="requerant_seq")
	@GeneratedValue(generator="requerant_seq", strategy=GenerationType.SEQUENCE)
	private Long idRequerant;
	
	private String renseignement;
	
	private String nom;
	
	

	public FRequerant() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public FRequerant(String renseignement, String nom) {
		super();
		this.renseignement = renseignement;
		this.nom = nom;
	}



	public String getRenseignement() {
		return renseignement;
	}

	public void setRenseignement(String renseignement) {
		this.renseignement = renseignement;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Long getIdRequerant() {
		return idRequerant;
	}
	
	
}
