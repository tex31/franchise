package com.douane.managed.bean.JasperData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.douane.entite.Designation;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JournalMatiereData {
	List <Object[]> list;
	public JournalMatiereData(List <Object[]> l){
		this.list = l;
	}
	public JRBeanCollectionDataSource getDataSource() {
		List<JournalMatiere> newOne = new ArrayList<JournalMatiere>();
		for (Object[] i : this.list ) {
			JournalMatiere jm = new JournalMatiere(i[0], i[1], i[2], i[3],
					i[4], i[5], i[6], i[7], i[8], i[9], i[10], i[11]);
			newOne.add(jm);
		}
		
		return new JRBeanCollectionDataSource(newOne);
	}
	
	public class JournalMatiere{
		private Long id;
		private String numerotation;
		private Date date;
		private String origine;
		private String nomenclature;
		private String nbrDes;
		private String designations;
		private String especeU;
		private Double pu;
		private Long e_qt;
		private Double e_v;
		private Long s_qt;
		private Double s_v;
		public JournalMatiere(Object i, Object i2, Object i3,Object i4, Object i5, Object i6, Object i7,Object i8, Object i9, Object i10, Object i11, Object i12){
			this.id = (Long) i;
			this.numerotation = (String) i2;
			this.date = (Date) i3;
			this.setOrigine((String) i4);
			this.designations = (String) i5;//((Designation) i12).getNomenMat().getNomenclature();
			this.especeU = (String) i6;
			this.pu = (Double) i7;
			this.nomenclature = " ";
			this.e_qt = (Long) i8;
			this.e_v = (Double) i9;
			this.s_qt = (Long) i10;
			this.s_v = (Double) i11;
			this.nbrDes =  ((Designation) i12).getNomenMat().getNomenclature();
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getNumerotation() {
			return numerotation;
		}
		public void setNumerotation(String numerotation) {
			this.numerotation = numerotation;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public String getNomenclature() {
			return nomenclature;
		}
		public void setNomenclature(String nom) {
			this.nomenclature = nom;
		}
		public String getDesignations() {
			return designations;
		}
		public void setDesignations(String designations) {
			this.designations = designations;
		}
		public String getEspeceU() {
			return especeU;
		}
		public void setEspeceU(String especeU) {
			this.especeU = especeU;
		}
		public Double getPu() {
			return pu;
		}
		public void setPu(Double pu) {
			this.pu = pu;
		}
		public String getNbrDes() {
			return nbrDes;
		}
		public void setNbrDes(String nbrDes) {
			this.nbrDes = nbrDes;
		}
		public Long getE_qt() {
			return e_qt;
		}
		public void setE_qt(Long e_qt) {
			this.e_qt = e_qt;
		}
		public Double getE_v() {
			return e_v;
		}
		public void setE_v(Double e_v) {
			this.e_v = e_v;
		}
		public Long getS_qt() {
			return s_qt;
		}
		public void setS_qt(Long s_qt) {
			this.s_qt = s_qt;
		}
		public Double getS_v() {
			return s_v;
		}
		public void setS_v(Double s_v) {
			this.s_v = s_v;
		}
		public String getOrigine() {
			return origine;
		}
		public void setOrigine(String origine) {
			this.origine = origine;
		}
	}
}
