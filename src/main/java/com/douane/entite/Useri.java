package com.douane.entite;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.UniqueConstraint;

@Entity
public class Useri implements Serializable{
    @Column(unique = true)
    private String role;
	/*public Useri(String designation, String role) {
		super();
		this.designation = designation;
		this.role = role;
	}*/
	public Useri(){
		
	}
	@Id
	@SequenceGenerator (name = "useri_gen", sequenceName = "USERI_SEQ", allocationSize = 1) 
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "useri_gen")
	private int idUser;

	private String designation;
	


	/*TEMPORARY NOT NEEDED 
	 * @OneToMany(mappedBy="roleAgent", fetch=FetchType.LAZY)
	private List<Agent> agents;
	
	public List<Agent> getAgents() {
		return agents;
	}
	public void setAgents(List<Agent> agents) {
		this.agents = agents;
	}
	public void addAgentToList(Agent a){
		agents.add(a);
	}*/
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getIdUser(){
		return this.idUser;
	}
	public Useri(String designation, String role) {
		super();
		this.designation = designation;
		this.role = role;
	}
	public Useri(String designation) {
		super();
		this.designation = designation;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((designation == null) ? 0 : designation.hashCode());
		result = prime * result + idUser;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Useri other = (Useri) obj;
		if (designation == null) {
			if (other.designation != null)
				return false;
		} else if (!designation.equals(other.designation))
			return false;
		if (idUser != other.idUser)
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}
	
	


		
}
