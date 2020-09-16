package com.douane.managed.bean.saisieRef;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.douane.entite.Devise;
import com.douane.managed.bean.SISEformBean;
@ManagedBean(name="journalFormBean")
public class JournalFormBean {
	private String filamatra;
	private String budget;
	private String chapitre;
	private String article;
	private String num2;
	private String num3;
	private String num4;
	private String num5;
	private String num6;
	private String nbFeuillets;
	private String lieu;
	private String date;
	private String debutDate;
	private String finDate;
	private String arrete;
	private String date1;

	public String getFilamatra() {
		return filamatra;
	}
	public void setFilamatra(String filamatra) {
		this.filamatra = filamatra;
	}
	public String toString() {
		StringJoiner joiner = new StringJoiner(" ");
		joiner.add("JournalForm = ").add(budget).add(chapitre).add(article).add(num2).add(num3).add(num4).add(num5)
		.add(num6).add(nbFeuillets).add(lieu).add(date)
		.add(debutDate).add(finDate).add(arrete).add(date1);
		String joinedString = joiner.toString();
		return joinedString;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getChapitre() {
		return chapitre;
	}

	public void setChapitre(String chapitre) {
		this.chapitre = chapitre;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getNum2() {
		return num2;
	}

	public void setNum2(String num2) {
		this.num2 = num2;
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

	public String getNum5() {
		return num5;
	}

	public void setNum5(String num5) {
		this.num5 = num5;
	}

	public String getNum6() {
		return num6;
	}

	public void setNum6(String num6) {
		this.num6 = num6;
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

	public String getArrete() {
		return arrete;
	}

	public void setArrete(String arrete) {
		this.arrete = arrete;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getDebutDate() {
		return debutDate;
	}

	public void setDebutDate(String debutDate) {
		this.debutDate = debutDate;
	}

	public String getFinDate() {
		return finDate;
	}

	public void setFinDate(String finDate) {
		this.finDate = finDate;
	}
	
}
