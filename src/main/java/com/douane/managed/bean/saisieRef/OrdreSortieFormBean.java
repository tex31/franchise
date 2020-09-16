package com.douane.managed.bean.saisieRef;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.douane.entite.Devise;
import com.douane.managed.bean.DocumentsBean;
import com.douane.managed.bean.SISEformBean;
import java.util.Map;
import java.util.StringJoiner;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.douane.entite.Devise;
@SessionScoped
@ManagedBean(name="ordreSortie")

public class OrdreSortieFormBean {
		private String numFolio;
		private String num5;
		private String num6;
		private String budget;
		private String chapitre;
		private String article;
		private String paragraphe;
		public String getParagraphe() {
			return paragraphe;
		}
		public void setParagraphe(String paragraphe) {
			this.paragraphe = paragraphe;
		}
		//mila mampiditra paragraphe
		private String filamatra;
		public String getFilamatra() {
			return filamatra;
		}
		public void setFilamatra(String filamatra) {
			this.filamatra = filamatra;
		}
		private String num2;
		private String approOuService;
		private String ordre;
		private String somme;		
		private String lieu;
		private String comptable;
		private String conformite;
		private String dateConformite;
		private String num4;
		private String lieu1;
		private String sousigne;
		private String num7;
		private String lieu2;
		private String date2;
		private Map<String,String> filamatras = new HashMap<String, String>();
		@PostConstruct
	    public void init() {
			filamatras = new HashMap<String, String>();
			SISEformBean s = new SISEformBean();
			for(Devise d : s.getListDevise()) {
				filamatras.put(d.getDesignation(), d.getDesignation());
			}
			/*FacesContext facesContext = FacesContext.getCurrentInstance();
			DocumentsBean doc = (DocumentsBean) facesContext.getApplication().evaluateExpressionGet(facesContext, '#{docbean}', DocumentsBean.class);
			this.budget = doc.getCurentOpeationSortieToPdf().getDirection().getBudget();
			System.out.println("code SOA :" this.budget);*/
	    }
		
		public Map<String, String> getFilamatras() {
			return filamatras;
		}
		public void setFilamatras(Map<String, String> filamatras) {
			this.filamatras = filamatras;
		}
		public String toString() {
			StringJoiner joiner = new StringJoiner(" ");
			joiner.add("ordreSortie = ").add(num5).add(num6).add(num4).add(budget).add(chapitre).add(num2).add(approOuService)
			.add(ordre).add(somme).add(lieu).add(comptable)
			.add(conformite).add(dateConformite).add(num4).add(lieu1).add(sousigne).add(num7).add(lieu2).add(date2);
			String joinedString = joiner.toString();
			return joinedString;
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
		public String getNum2() {
			return num2;
		}
		public void setNum2(String num2) {
			this.num2 = num2;
		}
		public String getApproOuService() {
			return approOuService;
		}
		public void setApproOuService(String approOuService) {
			this.approOuService = approOuService;
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
		public String getComptable() {
			return comptable;
		}
		public void setComptable(String comptable) {
			this.comptable = comptable;
		}
		public String getConformite() {
			return conformite;
		}
		public void setConformite(String conformite) {
			this.conformite = conformite;
		}
		public String getDateConformite() {
			return dateConformite;
		}
		public void setDateConformite(String dateConformite) {
			this.dateConformite = dateConformite;
		}
		public String getNum4() {
			return num4;
		}
		public void setNum4(String num4) {
			this.num4 = num4;
		}
		public String getLieu1() {
			return lieu1;
		}
		public void setLieu1(String lieu1) {
			this.lieu1 = lieu1;
		}
		public String getSousigne() {
			return sousigne;
		}
		public void setSousigne(String sousigne) {
			this.sousigne = sousigne;
		}
		public String getNum7() {
			return num7;
		}
		public void setNum7(String num7) {
			this.num7 = num7;
		}
		public String getLieu2() {
			return lieu2;
		}
		public void setLieu2(String lieu2) {
			this.lieu2 = lieu2;
		}
		public String getDate2() {
			return date2;
		}
		public void setDate2(String date2) {
			this.date2 = date2;
		}
		public String getArticle() {
			return article;
		}
		public void setArticle(String article) {
			this.article = article;
		}
		private String dirc;
		public String getDirc() {
			return dirc;
		}
		public void setDirc(String dirc) {
			this.dirc = dirc;
		}
		public String getNumFolio() {
			return numFolio;
		}
		public void setNumFolio(String numFolio) {
			this.numFolio = numFolio;
		}
		
}
