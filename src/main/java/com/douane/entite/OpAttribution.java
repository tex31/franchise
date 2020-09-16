package com.douane.entite;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="opattribution")
public class OpAttribution extends Operation{
	
	@ManyToOne
	@JoinColumn(name="idMat")
	private Materiel mat;
	
	@ManyToOne
	@JoinColumn(name="idDetenteur")
	private Agent detenteur;
	private String detenteurEffectif;
	
	public OpAttribution(Date date, Date time, String poste, Agent operateur, Materiel m, Agent det) {
		super(date, time, poste, operateur);
		this.mat = m;
		this.detenteur = det;
		this.setDetenteurEffectif("generated default detenteur effectif ");
		
	}
	public OpAttribution() {
		super();
	}
	
	
	
	public Materiel getMat() {
		return mat;
	}
	public void setMat(Materiel mat) {
		this.mat = mat;
		this.setDetenteurEffectif("generated detenteur effectif Materiel modified");
	}
	
	public Agent getDetenteur() {
		return this.detenteur;
	}
	
	public void setDetenteur(Agent det) {
		this.detenteur = det;
		this.setDetenteurEffectif("generated detenteur effectif detenteur modified");
	}
	public String getDetenteurEffectif() {
		return detenteurEffectif;
	}
	public void setDetenteurEffectif(String detenteurEffectif) {
		this.detenteurEffectif = detenteurEffectif;
	}
	
	public String getNumerodet() {
		return numerodet;
	}
	public void setNumerodet(String numerodet) {
		this.numerodet = numerodet;
	}

	private String numerodet;
	
	public void generateNumDet(Long currentNum) {
		//Date today = new Date();
		
	    int d = Calendar.getInstance().get(Calendar.DAY_OF_MONTH); String dd="x";
	    int m = Calendar.getInstance().get(Calendar.MONTH); m=m+1;String mm="x";
	    int y = Calendar.getInstance().get(Calendar.YEAR); String yy="x";
	    if(d < 10){
	      dd="0"+d;
	    }else {
	    	dd=""+d;
	    }
	    if(m < 10){
	       mm="0"+m;
	    }else {
	    	mm=""+m;
	    }
		yy=""+y%200;
		String codeDirection ="tsy misy";
		if(this.getDirection()!=null) {
			codeDirection = this.getDirection().getCodeDirection();
		}
		this.numerodet="OA "+ currentNum+ "/"+codeDirection+ "/" +dd+ "/" +mm+ "/" +yy;
	}
	
	
}
