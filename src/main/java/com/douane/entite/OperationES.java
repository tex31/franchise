package com.douane.entite;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
/*@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="typeOES", discriminatorType=DiscriminatorType.INTEGER)*/
public abstract class OperationES extends Operation {
	
	@ManyToOne
	@JoinColumn(name="idMat")
	protected Materiel mat;
	protected String numoperation;
	public OperationES(Date date, Date time, String poste, Agent operateur, Materiel mat, String numoperation) {
		super(date, time, poste, operateur);
		this.mat = mat;
		this.numoperation = numoperation;
	}
	public OperationES() {
		super();
		this.numoperation = "";
		// TODO Auto-generated constructor stub
	}
	public OperationES(Date date, Date time, String poste, Agent operateur) {
		super(date, time, poste, operateur);
		// TODO Auto-generated constructor stub
		this.numoperation = "";
	}
	
	public Materiel getMat() {
		return mat;
	}
	public void setMat(Materiel mat) {
		this.mat = mat;
	}
	public String getNumoperation() {
		return numoperation;
	}
	public void setNumoperation(String numoperation) {
		this.numoperation = numoperation;
	}
	
}
