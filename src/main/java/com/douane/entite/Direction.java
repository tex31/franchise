package com.douane.entite;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Direction extends Referentiel {

	@Column(unique=true)
	private String codeDirection;
	
	public Direction() {
		// TODO Auto-generated constructor stub
		this.setLeref("Direction");
	}
	public Direction(String designation, String code)throws SQLException  {
		this.setDesignation(designation);
		this.setCodeDirection(code);
		this.setLeref("Direction");
	}

	public String getCodeDirection(){
		return codeDirection;
	}

	public void setCodeDirection(String codeDirection) throws SQLException  {
		this.codeDirection = codeDirection;
	}
	
	private String Budget;
	private String trois;
	private String quatre;

	public String getBudget() {
		return Budget;
	}
	public void setBudget(String budget) {
		Budget = budget;
	}
	public String getTrois() {
		return trois;
	}
	public void setTrois(String trois) {
		this.trois = trois;
	}
	public String getQuatre() {
		return quatre;
	}
	public void setQuatre(String quatre) {
		this.quatre = quatre;
	}
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable
	  (
	      name="directionhistor",
	      joinColumns={ @JoinColumn(name="dirid", referencedColumnName="id") },
	      inverseJoinColumns={ @JoinColumn(name="dirtitleid", referencedColumnName="idtitle", unique=true) }
	  )
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<DirectionTitleHist> listTitle = new ArrayList<DirectionTitleHist>();
	
	
	public List<DirectionTitleHist> getListTitle() {
		return listTitle;
	}
	public void setListTitle(List<DirectionTitleHist> listTitle) {
		this.listTitle = listTitle;
	}
	public void addTitle(DirectionTitleHist t) {
		listTitle.add(t);
		t.setDirection(this);
	}
	public void removeTitle(DirectionTitleHist t) {
		listTitle.remove(t);
		t.setDirection(null);
	}
	
	
	@Transient
	private String lastTitle;
	public String getLastTitle() {
		if(listTitle.size()>0) {
			
			return listTitle.get(listTitle.size()-1).getTitle();
		}
		return "None";
	}
	public void setLastTitle(String title) {
		this.lastTitle = title;
	}
	@Transient
	private DirectionTitleHist lastTitleObj;
	public DirectionTitleHist getLastTitleObj() {
		if(listTitle.size()>0) {
			
			return listTitle.get(listTitle.size()-1);
		}
		return null;
	}
	public void setLastTitleObj(DirectionTitleHist title) {
		this.lastTitleObj = title;
	}
	

}
