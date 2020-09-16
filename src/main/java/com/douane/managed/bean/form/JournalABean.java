package com.douane.managed.bean.form;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.douane.entite.Direction;
import com.douane.managed.bean.SuiviEditionBean;
@SessionScoped
@ManagedBean(name="JournalABean")
public class JournalABean {
	private String direction;
	
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	private String trois;
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
	private String quatre;
	private Date date;
	private Date datF;
	private List<Object[]> liste;
	private String dateF;
	private String dateD;
	private Direction direc;
	public JournalABean()
	{
		this.date = new Date();
		this.datF = new Date();
	}
	public String execute(Direction dir,SuiviEditionBean s) {
		//this.liste = s.getListForJournalStock(this.date , this.datF);//this.date , this.datF
		//this.liste = s.ourListForJournalStock(date);
		this.liste = s.getListForJournalStockByDir(dir, date);
		// this myget
		this.liste = this.mygetFListESAForJournal(liste, date, datF);
		
		
		DateFormat  df = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE);
		this.dateD = df.format(this.date);
		this.dateF  = df.format(this.datF);
		this.trois = this.quatre ="tsy misy";
		if(dir==null) {
			this.direction = s.getDirection().getDesignation();
			this.trois = s.getDirection().getTrois();
			this.quatre = s.getDirection().getQuatre();
			this.direc = s.getDirection();
		}else {
			this.direction = dir.getDesignation();
			this.trois = dir.getTrois();
			this.quatre = dir.getQuatre();
			this.direc = dir;
		}
		return "dialogJournalAdmin";
	}
	
	public List<Object[]> mygetFListESAForJournal(List<Object[]> listebyyeaer,Date start, Date fin) {
		List<Object[]> listefiltered =  new ArrayList<Object[]>();
		for (Object[] o:listebyyeaer) {
			if(start.compareTo((Date)(o[1])) <=0 && fin.compareTo((Date)(o[1]))>=0)
			listefiltered.add(o);
		}
		return listefiltered;
		
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<Object[]> getListe() {
		return liste;
	}
	public void setListe(List<Object[]> liste) {
		this.liste = liste;
	}
	public String getDateD() {
		return dateD;
	}
	public void setDateD(String dateD) {
		this.dateD = dateD;
	}
	public String getDateF() {
		return dateF;
	}
	public void setDateF(String dateF) {
		this.dateF = dateF;
	}
	public Date getDatF() {
		return datF;
	}
	public void setDatF(Date datF) {
		this.datF = datF;
	}
	public Direction getDirec() {
		return direc;
	}
	public void setDirec(Direction direc) {
		this.direc = direc;
	}

}
