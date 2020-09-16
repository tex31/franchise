package com.douane.entite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class FDossier implements Serializable {
	
	@Id
	@SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="dossier_seq", name="dossier_seq")
	@GeneratedValue(generator="dossier_seq", strategy=GenerationType.SEQUENCE)
	private Long idDossier;
	
	private String nom;
	private String path;
	private String description;
	private boolean presence;
	
	
	
	public FDossier() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FDossier(String nom, String path, String description, boolean presence) {
		super();
		this.nom = nom;
		this.path = path;
		this.description = description;
		this.presence = presence;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isPresence() {
		return presence;
	}
	public void setPresence(boolean presence) {
		this.presence = presence;
	}
	public Long getIdDossier() {
		return idDossier;
	}
	
	

}
