package com.douane.entite;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
@Entity
public class Client implements Serializable{
	@Id
	@SequenceGenerator (name = "generatorClient", sequenceName = "CL_SEQ", allocationSize = 1) 
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "generatorClient")
	private Long idClient;
	private String nom;
	private String mail;
	@OneToMany(mappedBy="client", fetch=FetchType.LAZY)
	private Collection<Demande> demandes;
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Client(String nom, String mail, Collection<Demande> demandes) {
		super();
		this.nom = nom;
		this.mail = mail;
		this.demandes = demandes;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Collection<Demande> getDemandes() {
		return demandes;
	}
	public void setDemandes(Collection<Demande> demandes) {
		this.demandes = demandes;
	}
	public Long getIdClient() {
		return idClient;
	}
	
}
