package com.douane.managed.bean.form;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.douane.entite.Direction;
import com.douane.managed.bean.SuiviEditionBean;
@SessionScoped
@ManagedBean(name="livreAnnuelBean")
public class livreAnnuelBean {
	public livreAnnuelBean() {
		this.d = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(this.d);
		this.anne  = calendar.get(Calendar.YEAR);
		this.actualyear  = calendar.get(Calendar.YEAR);
	}
	private int actualyear;
	//private int annee;
	private String service;
	private Date d;
	private String trois;
	private String quatre;
	private String dateD;
	private String dateF;
	private Integer anne;
	private List<Object[]> liste;
	private Direction dir;
	public String execute(SuiviEditionBean s) {
		DateFormat df =  new SimpleDateFormat("yyyy", Locale.FRANCE);
		this.setListe(s.getListESForGrandLivre());
		this.anne = Integer.valueOf(df.format(this.d));
		this.trois  = s.getDirection().getTrois();
		this.quatre =  "";//s.getDirection().getQuatre();
		df = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE);
		this.dateF = df.format(this.d);
		this.service = s.getDirection().getDesignation();
		return "dialogLivre";
	}
	public String executer(SuiviEditionBean s, Integer i, Direction direc) {
		
		DateFormat  df = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE);
		if(i!=0)
			this.anne = i;
		this.quatre =  "";//s.getDirection().getQuatre();
		Date sdate = new GregorianCalendar(this.anne, Calendar.JANUARY, 1).getTime();
		this.d = new GregorianCalendar(this.anne, Calendar.DECEMBER, 31).getTime();
		this.service = s.getDirection().getDesignation();
		this.dateD = df.format(sdate);
		this.dateF = df.format(this.d);
		
		if(direc == null) {
			this.trois  = s.getDirection().getTrois();
			this.liste=s.getListESForGrandLivre(sdate,this.d,s.getDirection());
			this.service = s.getDirection().getDesignation();
			this.dir = s.getDirection();
			
			
		}else {
			this.trois  = direc.getTrois();
			this.liste=s.getListESForGrandLivre(sdate,this.d,direc);
			this.service = direc.getDesignation();
			this.dir = direc;
		}
		return "dialogLivre"; 
	}
	public List<Object[]> getListe() {
		return liste;
	}
	public void setListe(List<Object[]> liste) {
		this.liste = liste;
	}
	public Integer getAnne() {
		return anne;
	}
	public void setAnne(Integer anne) {
		this.anne = anne;
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
	public String getDateF() {
		return dateF;
	}
	public void setDateF(String dateF) {
		this.dateF = dateF;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public Date getD() {
		return d;
	}
	public void setD(Date d) {
		this.d = d;
	}
	public Direction getDir() {
		return dir;
	}
	public void setDir(Direction dir) {
		this.dir = dir;
	}
	public int getActualyear() {
		return actualyear;
	}
	public void setActualyear(int actualyear) {
		this.actualyear = actualyear;
	}
	public String getDateD() {
		return dateD;
	}
	public void setDateD(String dateD) {
		this.dateD = dateD;
	}

}
