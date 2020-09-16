package com.douane.entite;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("11")
public class OpSortie extends OperationES{
		
	@ManyToOne
	@JoinColumn(name="idDirect")
	private Direction direc;
	
	@ManyToOne
	@JoinColumn(name="idMotif")
	private MotifSortie motifsortie;
	
	private String pj;
	
	public OpSortie(Date date, Date time, String poste, Agent operateur, 
			Materiel mater, Direction d, MotifSortie mot) {
		super(date, time, poste, operateur);
		this.setMat(mater);
		this.setDirec(d);
		this.setMotifsortie(mot);
	}

	public OpSortie(Date date, Date time, String poste, Agent operateur, 
			Materiel mater, MotifSortie mot) {
		super(date, time, poste, operateur);
		this.setMat(mater);
		this.setMotifsortie(mot);
	}


	public Materiel getMat() {
		return mat;
	}

	public void setMat(Materiel mat) {
		this.mat = mat;
	}

	public String getNumSortie() {
		return numoperation;
	}

	public void generateNumSortie(Long currentNum) {
		int d = Calendar.getInstance().get(Calendar.DAY_OF_MONTH); String dd="x";
	    int m = Calendar.getInstance().get(Calendar.MONTH); m=m+1; String mm="x";
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
		this.numoperation="OS "+ currentNum+ "/"+codeDirection+ "/" +dd+ "/" +mm+ "/" +yy;
	}

	
	public Direction getDirec() {
		return direc;
	}

	public void setDirec(Direction direc) {
		this.direc = direc;
	}

	public MotifSortie getMotifsortie() {
		return motifsortie;
	}

	public void setMotifsortie(MotifSortie motif) {
		this.motifsortie = motif;
	}

	public OpSortie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPj() {
		return pj;
	}

	public void setPj(String pj) {
		this.pj = pj;
	}
	
	
	
	
}
