package com.douane.entite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import javax.persistence.FetchType;
/*
 * Entity utilisé pour les utilisateurs et les agents détenteurs
 * 
 */
@Entity
public class Agent implements Serializable {


	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@SequenceGenerator (name = "generator_agent", sequenceName = "AG_SEQ", allocationSize = 1) 
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "generator_agent")
	private Long idAgent;

	@Column(unique = true)
	private Long im;
	
	private String nomAgent;
	private String prenomAgent;
	@Column(name="PASSWORD", nullable = false)
	private String password;
	
	//role agent pour definir la fonction de l'agent donc les menus et fonctions dispo
	@ManyToOne
	@JoinColumn(name="idrole")
	private Useri roleAgent;
	
	@ManyToOne
	@JoinColumn(name="idposteny")
	private Poste posteny;

	//Liste des matériels détenus par l'agent
	//EAGER pour faciliter les requêtes dans db
	@OneToMany(mappedBy="detenteur", fetch = FetchType.EAGER)
	private List<Materiel> matdetenu = new ArrayList<Materiel>();
	
	//Localisation
	@ManyToOne
	@JoinColumn(name="idDirection")
	private Direction direction;
	
	// Pour activation du compte
	private boolean active;
	
	@Transient
	private String ip;
	
	public Agent(Long im, String nom_agent, String prenom_agent, Useri role) {
		super();
		this.im = im;
		this.nomAgent = nom_agent;
		this.prenomAgent = prenom_agent;
		this.roleAgent = role;
	}
	
	public Agent(Long im, String nom_agent, String prenom_agent) {
		super();
		this.im = im;
		this.nomAgent = nom_agent;
		this.prenomAgent = prenom_agent;
		this.setLeref("Agent");
	}
	
	public Agent(){
		this.setLeref("Agent");
		this.setActive(true);
	}

	

	public Long getIm() {
		return im;
	}

	public void setIm(Long im) {
		this.im = im;
	}

	public String getNomAgent() {
		return nomAgent;
	}

	public void setNomAgent(String nom_agent) {
		this.nomAgent = nom_agent;
	}

	public String getPrenomAgent() {
		return prenomAgent;
	}

	public void setPrenomAgent(String prenom_agent) {
		this.prenomAgent = prenom_agent;
	}

	public Useri getRoleAgent() {
		return roleAgent;
	}

	public void setRoleAgent(Useri role) {
		this.roleAgent = role;
	}
	
	@Transient
	private String leref;

	public String getLeref() {
		return leref;
	}

	public void setLeref(String leref) {
		this.leref = leref;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Materiel> getMatdetenu() {
		return matdetenu;
	}

	public void setMatdetenu(List<Materiel> matdetenu) {
		this.matdetenu = matdetenu;
	}
	
	public Poste getPosteny() {
		return posteny;
	}

	public void setPosteny(Poste poste) {
		this.posteny = poste;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	@Override
	public boolean equals(Object o) {
		/*if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Agent agent = (Agent) o;

		if (im != null ? !im.equals(agent.im) : agent.im != null) return false;
		return roleAgent != null ? roleAgent.equals(agent.roleAgent) : agent.roleAgent == null;
		*/
		if (this.getIm().equals(((Agent)o).getIm()))
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		int result = im != null ? im.hashCode() : 0;
		result = 31 * result + (nomAgent != null ? nomAgent.hashCode() : 0);
		result = 31 * result + (prenomAgent != null ? prenomAgent.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (roleAgent != null ? roleAgent.hashCode() : 0);
		return result;
	}


	public Long getIdAgent() {
		return idAgent;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
