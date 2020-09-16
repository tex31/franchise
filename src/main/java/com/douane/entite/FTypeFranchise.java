package com.douane.entite;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class FTypeFranchise implements Serializable{
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@SequenceGenerator (name = "generator_type", sequenceName = "TYPE_SEQ", allocationSize = 1) 
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "generator_type")
	private Long idType;
	
	@Column(unique = true)
	private String code;
	
	private String designation;
	
	
	public FTypeFranchise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FTypeFranchise(String code, String designation) {
		super();
		this.code = code;
		this.designation = designation;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Long getIdType() {
		return idType;
	}
	
	
}
