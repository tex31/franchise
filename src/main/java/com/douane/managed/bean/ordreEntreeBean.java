package com.douane.managed.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.douane.entite.Devise;
@SessionScoped
@ManagedBean(name="ordreEntree")
public class ordreEntreeBean {
	private String filamatra;
	private String num3;
	private String num4;
	private String chap;
	private String budget;
	private String article;
	private String paragraphe;
	private String approOuService;
	private String noumJour;
	private String comptable;
	private String comptable1;
	private String matieres;
	private String ordre;
	private String somme;
	private String lieu;
	private String concordance;
	private String date;
	private String date2;
	private String lieu1;
	private String date1;
	/*private Map<String,String> filamatras = new HashMap<String, String>();
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
	}*/
	public String toString() {
		StringJoiner joiner = new StringJoiner(" ");
		joiner.add("ordreEntree = ").add(num3).add(num4).add(chap).add(article).add(paragraphe).add(noumJour).add(comptable).add(matieres).add(ordre).add(chap)
		.add(somme).add(lieu).add(concordance).add(date).add(date).add(lieu1).add(date1);
		String joinedString = joiner.toString();
		return joinedString;
	}
	public String getNum3() {
		return num3;
	}
	public void setNum3(String num3) {
		this.num3 = num3;
	}
	public String getNum4() {
		return num4;
	}
	public void setNum4(String num4) {
		this.num4 = num4;
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
	public String getParagraphe() {
		return paragraphe;
	}
	public void setParagraphe(String paragraphe) {
		this.paragraphe = paragraphe;
	}
	public String getNoumJour() {
		return noumJour;
	}
	public void setNoumJour(String noumJour) {
		this.noumJour = noumJour;
	}
	public String getComptable() {
		return comptable; 
	}
	public void setComptable(String comptable) {
		this.comptable = comptable;
	}
	public String getMatieres() {
		return matieres;
	}
	public void setMatieres(String matieres) {
		this.matieres = matieres;
	}
	public String getOrdre() {
		return ordre;
	}
	public void setOrdre(String ordre) {
		this.ordre = ordre;
	}
	public String getSomme() {
		return somme;
	}
	public void setSomme(String somme) {
		this.somme = somme;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public String getConcordance() {
		return concordance;
	}
	public void setConcordance(String concordance) {
		this.concordance = concordance;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDate2() {
		return date2;
	}
	public void setDate2(String date2) {
		this.date2 = date2;
	}
	public String getLieu1() {
		return lieu1;
	}
	public void setLieu1(String lieu1) {
		this.lieu1 = lieu1;
	}
	public String getDate1() {
		return date1;
	}
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	public String getComptable1() {
		return comptable1;
	}
	public void setComptable1(String comptable1) {
		this.comptable1 = comptable1;
	}
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public String getApproOuService() {
		return approOuService;
	}
	public void setApproOuService(String approOuService) {
		this.approOuService = approOuService;
	}
	public String getFilamatra() {
		return filamatra;
	}
	public void setFilamatra(String filamatra) {
		this.filamatra = filamatra;
	}
	
	private String dirc;
	public String getDirc() {
		return dirc;
	}
	public void setDirc(String dirc) {
		this.dirc = dirc;
	}
		
}
