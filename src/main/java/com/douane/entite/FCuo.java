package com.douane.entite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class FCuo implements Serializable{
	@Id
	@SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="rcuo_seq", name="cuo_seq")
	@GeneratedValue(generator="cuo_seq", strategy=GenerationType.SEQUENCE)
	private Long idCuo;
	
	private String sigle;
	
	private String nom;
	
	

	public FCuo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FCuo(String sigle, String nom) {
		super();
		this.sigle = sigle;
		this.nom = nom;
	}

	public String getSigle() {
		return sigle;
	}

	public void setSigle(String sigle) {
		this.sigle = sigle;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Long getIdCuo() {
		return idCuo;
	}
	
	
	
}
