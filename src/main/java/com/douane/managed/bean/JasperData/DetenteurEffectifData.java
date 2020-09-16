package com.douane.managed.bean.JasperData;

import java.util.ArrayList;
import java.util.List;

import com.douane.entite.Materiel;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class DetenteurEffectifData {
	private List<Materiel> list;
	public DetenteurEffectifData(List<Materiel> l){	
		this.list = l;
	}
	public JRBeanCollectionDataSource getDataSource() {
		List<DetenteurEffectif> newOne = new ArrayList<DetenteurEffectif>();
		for (Materiel i : this.list ) {
			DetenteurEffectif la = new DetenteurEffectif(i);
			newOne.add(la);
		}
		
		return new JRBeanCollectionDataSource(newOne);
	}
	
	public class DetenteurEffectif{
		private String row0;
		private String row1;
		private Double row2;
		private Double row3;
		private Double row4;
		
		public DetenteurEffectif(Materiel i) {
			this.row0= i.getDesign().getNomenMat().getNomenclature() + " - "
                                + i.getDesign().getMarque().getDesignation() + " - "
                                + i.getDesign().getRenseignement() + " - "
                                + i.getReference();
			
			this.row1= i.getDesign().getEspeceUnite();//matdet.design.especeUnite
			this.row2= i.getDesign().getPu();//matdet.design.pu
			this.row3= new Double(1); // 1
			this.row4= i.getDesign().getPu();//matdet.design.pu*1
		}

		public String getRow0() {
			return row0;
		}

		public void setRow0(String row0) {
			this.row0 = row0;
		}

		public String getRow1() {
			return row1;
		}

		public void setRow1(String row1) {
			this.row1 = row1;
		}

		public Double getRow2() {
			return row2;
		}

		public void setRow2(Double row2) {
			this.row2 = row2;
		}

		public Double getRow3() {
			return row3;
		}

		public void setRow3(Double row3) {
			this.row3 = row3;
		}

		public Double getRow4() {
			return row4;
		}

		public void setRow4(Double row4) {
			this.row4 = row4;
		}
	}

	public List<Materiel> getList() {
		return list;
	}
	public void setList(List<Materiel> list) {
		this.list = list;
	}
}
