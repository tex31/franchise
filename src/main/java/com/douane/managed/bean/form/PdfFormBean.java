package com.douane.managed.bean.form;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.douane.entite.Devise;
import com.douane.managed.bean.SISEformBean;

@ManagedBean(name="pdfForm")
public class PdfFormBean {
	private String budget;
	private String chapitre;
	private String article;
	private String paragraphe;
	private String num1;
	private String num2;
	private String num3;
	private String num4;
	private String num5;
	private String num6;
	private String num7;
	private String num8;
	private String num9;
	private String somme;
	private String somme1;
	private String somme2;
	private String date;
	private String lieu;
	private String filamatra;
	private int annee;
	
	public String getFilamatra() {
		return filamatra;
	}
	public void setFilamatra(String filamatra) {
		this.filamatra = filamatra;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public String toString() {
		StringJoiner joiner = new StringJoiner(" ");
		joiner.add("pdfForm = ").add(budget).add(chapitre).add(article).add(num1).add(num2).add(num3).add(num4).add(num5)
		.add(num6).add(num7).add(num8).add(num9).add(somme).add(somme2).add(date);
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
	public String getParagraphe() {
		return paragraphe;
	}
	public void setParagraphe(String paragraphe) {
		this.paragraphe = paragraphe;
	}
	public String getNum1() {
		return num1;
	}
	public void setNum1(String num1) {
		this.num1 = num1;
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
	public String getNum7() {
		return num7;
	}
	public void setNum7(String num7) {
		this.num7 = num7;
	}
	public String getNum8() {
		return num8;
	}
	public void setNum8(String num8) {
		this.num8 = num8;
	}
	public String getNum9() {
		return num9;
	}
	public void setNum9(String num9) {
		this.num9 = num9;
	}
	public String getSomme() {
		return somme;
	}
	public void setSomme(String somme) {
		this.somme = somme;
	}
	public String getSomme1() {
		return somme1;
	}
	public void setSomme1(String somme1) {
		this.somme1 = somme1;
	}
	public String getSomme2() {
		return somme2;
	}
	public void setSomme2(String somme2) {
		this.somme2 = somme2;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
}
