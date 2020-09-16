package com.douane.entite;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class DirectionTitleHist implements Serializable{
	
	@Id
	@SequenceGenerator (name = "generator_hist", sequenceName = "HIST_SEQ", allocationSize = 1) 
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "generator_hist")
	private Long idtitle;
	
	@ManyToOne
	@JoinColumn(name="idDirection")
	private Direction direction;
	
	
	private String title;
	
	@Temporal(TemporalType.DATE)
	@Column(name="datehist")
	private Date date;
	
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getIdtitle() {
		return idtitle;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public DirectionTitleHist() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DirectionTitleHist(Long idtitle, Direction direction, String title, Date date) {
		super();
		this.idtitle = idtitle;
		this.direction = direction;
		this.title = title;
		this.date = date;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((idtitle == null) ? 0 : idtitle.hashCode());
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
		DirectionTitleHist other = (DirectionTitleHist) obj;
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
			return false;
		if (idtitle == null) {
			if (other.idtitle != null)
				return false;
		} else if (!idtitle.equals(other.idtitle))
			return false;
		return true;
	}
	
	
	
	
	
}
