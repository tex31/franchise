package com.douane.managed.bean.form;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;

import com.douane.entite.Agent;
import com.douane.entite.Direction;
import com.douane.managed.bean.SuiviEditionBean;
import com.douane.requesthttp.RequestFilter;
@SessionScoped
@ManagedBean(name="JournalBean")
public class JournalBean {
	private String trois;
	private String direction;
	private String quatre;
	private String dateD;
	private String dateF;
	private Date dat;
	private Date datF;
	private String actualYear;
	private List<Object[]> li;
	private Direction dir;
	private String  section;
	public JournalBean() {
		DateFormat  df = new SimpleDateFormat("yyyy", Locale.FRANCE);
		this.dat = new Date();
		this.datF = new Date();
		this.actualYear = df.format(this.dat);
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
	public String execute(Direction d, SuiviEditionBean s) {
		DateFormat  df = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE);
		this.dateD = df.format(this.dat);
		this.dateF  = df.format(this.datF);
		this.trois = this.quatre ="";
		if(d == null) {
			this.li = s.ourListESForJournal(s.getDirection(),this.dat);
			this.li = this.mygetFListESForJournal(li, dat, datF);
			this.direction = s.getDirection().getDesignation();
			this.trois = s.getDirection().getTrois();
			this.dir = s.getDirection();
			this.section = this.dir.getBudget();
		}else {
			this.li = s.ourListESForJournal(d,this.dat);
			this.li = this.mygetFListESForJournal(li, dat, datF);
			this.direction = d.getDesignation();
			this.trois = d.getTrois();
			this.dir = d;
			this.section = this.dir.getBudget();
		}
		return "dialogJournal";
	}
	
	public List<Object[]> mygetFListESForJournal(List<Object[]> listebyyeaer,Date start, Date fin) {
		List<Object[]> listefiltered =  new ArrayList<Object[]>();
		for (Object[] o:listebyyeaer) {
			if(start.compareTo((Date)(o[2])) <=0 && fin.compareTo((Date)(o[2]))>=0)
			listefiltered.add(o);
		}
		return listefiltered;
		
	}
	
	public Date getDat() {
		return dat;
	}
	public void setDat(Date d) {
		dat = d;
	}
	public Date getDatF() {
		return datF;
	}
	public void setDatF(Date datF) {
		this.datF = datF;
	}
	public List<Object[]> getLi() {
		return li;
	}
	public void setLi(List<Object[]> li) {
		this.li = li;
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
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public Direction getDir() {
		return dir;
	}
	public void setDir(Direction dir) {
		this.dir = dir;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getActualYear() {
		return actualYear;
	}
	public void setActualYear(String actualYear) {
		this.actualYear = actualYear;
	}
	
}
