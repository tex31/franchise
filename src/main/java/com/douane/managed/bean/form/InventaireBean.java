package com.douane.managed.bean.form;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;

import com.douane.entite.Direction;
import com.douane.managed.bean.GACBean;
import com.douane.managed.bean.SuiviEditionBean;
@SessionScoped
@ManagedBean(name="InventaireBean")
public class InventaireBean {
	private int year;
	private int actualyear;
	private String trois;
	private String quatre;
	private String dateD;
	private String dateF;
	private Date dat;
	private Date datF;
	private List<Object[]> li;
	private Direction dir;
	private String section;
	public InventaireBean() {
		this.dat = new Date();
		this.datF = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(this.dat);
		this.year  = calendar.get(Calendar.YEAR);
		this.actualyear = calendar.get(Calendar.YEAR);
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
	public String execute(Direction direc, SuiviEditionBean s) {
		//this.li = s.getListobjectForInvetaire(this.dat , this.datF);
		//this.li = s.getListobjectForInvetaireByDir(direc,this.dat,this.datF);
		
		DateFormat  df = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE);
		this.dateD = df.format(this.dat);
		this.dateF  = df.format(this.datF);
		this.trois = this.quatre ="";
		
		if(direc ==null) {
			this.trois = s.getDirection().getTrois();
			this.quatre = s.getDirection().getQuatre();
			this.li = s.getListobjectForInvetaireByDir(s.getDirection(),this.dat,this.datF);
			this.dir = s.getDirection();
			this.section = this.dir.getBudget();
			
		}else {
			this.trois = direc.getTrois();
			this.quatre = direc.getQuatre();
			this.li = s.getListobjectForInvetaireByDir(direc,this.dat,this.datF);
			this.dir = direc;
			this.section = this.dir.getBudget();
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(this.datF);
		this.year = calendar.get(Calendar.YEAR);
		return "anneeInv";
		 
	}
	//gacBean.setAnneeInv(d,suivieditionBean.listobjectForInvetaire)
	public String executer(int d,SuiviEditionBean s) {
		Date date = new Date();
		if(d!=0) {
			this.year = d;
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		Date sdate = new GregorianCalendar(this.year, Calendar.JANUARY, 1).getTime();
		Date edate = new GregorianCalendar(this.year, Calendar.DECEMBER, 31).getTime();
		this.dat = sdate;
		this.datF = edate;
		this.li = s.getListobjectForInvetaire(this.dat , this.datF);
		DateFormat  df = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE);
		this.dateD = df.format(this.dat);
		this.dateF  = df.format(this.datF);
		this.trois = this.quatre ="tsy misy";
		if(s.getDirection() !=null) {
			this.trois = s.getDirection().getTrois();
			this.quatre = s.getDirection().getQuatre();
		}else System.out.println("tsy tonga ny journal.Direction");
		return "anneeInv";
		 
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
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
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
	public int getActualyear() {
		return actualyear;
	}
	public void setActualyear(int actualyear) {
		this.actualyear = actualyear;
	}
}
