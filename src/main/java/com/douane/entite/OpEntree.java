package com.douane.entite;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;

@Entity
@Table(name="operationentree")
@DiscriminatorValue("13")
public class OpEntree extends OperationES{
	
	
	public OpEntree(Date date, Date time, String poste, Agent operateur, Materiel mater) {
		super(date, time, poste, operateur);
		this.setMat(mater);
	}
    public OpEntree(Date date, Date time, String poste, Agent operateur) {
        super(date, time, poste, operateur);
    }
	public OpEntree() {
		
	}
	public void generateNumEntree(Long currentNum) {
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
		
		this.numoperation="OE "+ currentNum+ "/"+codeDirection+ "/" +dd+ "/" +mm+ "/" +yy;
	}
	public String getNumentree() {
		return this.numoperation;
	}



	//----CORRECTION---------
	 
	//@OneToMany(mappedBy="myoperationEntree", cascade=CascadeType.ALL, fetch=FetchType.EAGER)	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable
	  (
	      name="opentreeMateriel",
	      joinColumns={ @JoinColumn(name="entreeid", referencedColumnName="id") },
	      inverseJoinColumns={ @JoinColumn(name="materielid", referencedColumnName="idMateriel", unique=true) }
	  )
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Materiel> listMat = new ArrayList<Materiel>();
	//oaky

	private String pathDoc;
	private String refFact;

	public String getPathDoc() {
		return pathDoc;
	}

	public void setPathDoc(String pathDoc) {
		this.pathDoc = pathDoc;
	}

	public String getRefFact() {
		return refFact;
	}

	public void setRefFact(String refFact) {
		this.refFact = refFact;
	}



	public List<Materiel> getListMat() {
		return listMat;
	}

	public void setListMat(List<Materiel> listMat) {
		this.listMat = listMat;
	}
	public void addMateriel(Materiel m) {
		listMat.add(m);
		m.setMyoperationEntree(this);
	}
	public void removeMateriel(Materiel m) {
		listMat.remove(m);
		m.setMyoperationEntree(null);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listMat == null) ? 0 : listMat.hashCode());
		result = prime * result + ((numoperation == null) ? 0 : numoperation.hashCode());
		result = prime * result + ((refFact == null) ? 0 : refFact.hashCode());
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
		OpEntree other = (OpEntree) obj;
		if (listMat == null) {
			if (other.listMat != null)
				return false;
		} else if (!listMat.equals(other.listMat))
			return false;
		if (numoperation == null) {
			if (other.numoperation != null)
				return false;
		} else if (!numoperation.equals(other.numoperation))
			return false;
		if (refFact == null) {
			if (other.refFact != null)
				return false;
		} else if (!refFact.equals(other.refFact))
			return false;
		return true;
	}
	
	
}
