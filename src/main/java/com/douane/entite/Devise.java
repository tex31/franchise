package com.douane.entite;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Devise extends Referentiel {

	public Devise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Devise(String designation) {
		super(designation);
		this.setLeref("Devise");
		// TODO Auto-generated constructor stub
	}
	
	@Temporal(TemporalType.DATE)
	private Date dateExpire;

	public Date getDateExpire() {
		return dateExpire;
	}

	public void setDateExpire(Date dateExpire) {
		this.dateExpire = dateExpire;
	}
	
	
}
