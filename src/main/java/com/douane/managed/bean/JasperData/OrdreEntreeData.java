package com.douane.managed.bean.JasperData;

import java.util.ArrayList;
import java.util.List;

import com.douane.entite.Designation;
import com.douane.entite.Nomenclature;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class OrdreEntreeData {
	private List<Object[]> data;
	public OrdreEntreeData(List<Object[]> d) {
		this.data = d;
	}
	@SuppressWarnings("unchecked")
	public List<OrdreEntree> getDataAsList() {
		List<OrdreEntree> newOne = new ArrayList<OrdreEntree>();
		OrdreEntree oe;
		for (Object[] des : this.data) {
			Nomenclature nom = (Nomenclature) des[0];
			Double f = (Double) des[1];
			List <Object[]> des2 = (List<Object[]>) des[2];
			for (Object[] desu : des2) {
				Designation desu0 = (Designation) desu[0];
				String designat = desu0.getTypematerieladd().getDesignation() + " - " + desu0.getMarque().getDesignation() + " - " + desu0.getRenseignement();
				String esp = desu0.getEspeceUnite();
				Long lo = (Long) desu[1];
				Double pu = desu0.getPu();
				Double som = (Double) des[1];
				String orig = desu0.getRefFacture();
				oe = new OrdreEntree(nom.getNomenclature(), designat, esp, lo, pu,orig, som);
				newOne.add(oe);
			}
		}
		return newOne;
	}
	public JRBeanCollectionDataSource getDataAsDataSource() {
		JRBeanCollectionDataSource res = new JRBeanCollectionDataSource(this.getDataAsList());
		return res;
	}
	public class OrdreEntree{
		private String nomenclature;
		private String designations;
		private String especeUnite;
		private Long nbr;
		private Double pu;
		private Double sommaire;
		private String origine;
		public OrdreEntree(String n, String d, String e, Long nb,Double p, String o, Double som) {
			this.nomenclature = n;
			this.designations = d;
			this.especeUnite = e;
			this.nbr = nb;
			this.pu = p;
			this.origine = o;
			this.setSommaire(som);
		}
		public String getNomenclature() {
			return nomenclature;
		}
		public void setNomenclature(String nomenclature) {
			this.nomenclature = nomenclature;
		}
		public String getDesignations() {
			return designations;
		}
		public void setDesignations(String designations) {
			this.designations = designations;
		}
		public String getEspeceUnite() {
			return especeUnite;
		}
		public void setEspeceUnite(String especeUnite) {
			this.especeUnite = especeUnite;
		}
		public Long getNbr() {
			return nbr;
		}
		public void setNbr(Long nbr) {
			this.nbr = nbr;
		}
		public Double getPu() {
			return pu;
		}
		public void setPu(Double pu) {
			this.pu = pu;
		}
		public String getOrigine() {
			return origine;
		}
		public void setOrigine(String origine) {
			this.origine = origine;
		}
		public Double getSommaire() {
			return sommaire;
		}
		public void setSommaire(Double sommaire) {
			this.sommaire = sommaire;
		}
	}
}
