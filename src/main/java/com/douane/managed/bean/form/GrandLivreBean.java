package com.douane.managed.bean.form;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.douane.entite.Agent;
import com.douane.entite.Devise;
import com.douane.managed.bean.SISEformBean;
import com.douane.requesthttp.RequestFilter;

@ManagedBean(name="GrandLivre")

public class GrandLivreBean {
	public GrandLivreBean() {
		Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
		this.budget = cur.getDirection().getBudget();
	}
	private String budget;
	private String chap;
	private String article;
	private String paragraphe;
	private String libelle;
	private String subd;
	private String materiel;
	private String indication;
	private String nbFeuillets;
	private String lieu;
	private String date;
	private String dateD;
	private String ans;
	private String filamatra;
	private Map<String,String> filamatras = new HashMap<String, String>();
	@PostConstruct
    public void init() {
		filamatras = new HashMap<String, String>();
		SISEformBean s = new SISEformBean();
		for(Devise d : s.getListDevise()) {
			filamatras.put(d.getDesignation(), d.getDesignation());
		}
    }
	
	public Map<String, String> getFilamatras() {
		return filamatras;
	}
	public void setFilamatras(Map<String, String> filamatras) {
		this.filamatras = filamatras;
	}
	public String getFilamatra() {
		return filamatra;
	}
	public void setFilamatra(String filamatra) {
		this.filamatra = filamatra;
	}
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getSubd() {
		return subd;
	}
	public void setSubd(String subd) {
		this.subd = subd;
	}
	public String getMateriel() {
		return materiel;
	}
	public void setMateriel(String materiel) {
		this.materiel = materiel;
	}
	public String getIndication() {
		return indication;
	}
	public void setIndication(String indication) {
		this.indication = indication;
	}
	public String getNbFeuillets() {
		return nbFeuillets;
	}
	public void setNbFeuillets(String nbFeuillets) {
		this.nbFeuillets = nbFeuillets;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAns() {
		return ans;
	}
	public void setAns(String ans) {
		this.ans = ans;
	}
	public String getChap() {
		return chap;
	}
	public void setChap(String chap) {
		this.chap = chap;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}

	public String getDateD() {
		return dateD;
	}

	public void setDateD(String dateD) {
		this.dateD = dateD;
	}

	public String getParagraphe() {
		return paragraphe;
	}

	public void setParagraphe(String paragraphe) {
		this.paragraphe = paragraphe;
	}
}
