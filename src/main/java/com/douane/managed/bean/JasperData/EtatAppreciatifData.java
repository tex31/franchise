package com.douane.managed.bean.JasperData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.douane.entite.Designation;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class EtatAppreciatifData {
	List <Object[]> list;
	public EtatAppreciatifData(List <Object[]> l){
		
		this.list = l;
	}
	public JRBeanCollectionDataSource getDataSource() {
		List<EtatAppreciatif> newOne = new ArrayList<EtatAppreciatif>();
		for (Object[] i : this.list ) {
			EtatAppreciatif la = new EtatAppreciatif(i);
			newOne.add(la);
		}
		
		return new JRBeanCollectionDataSource(newOne);
	}
	
	public class EtatAppreciatif{
		private Designation row0;
		private String row1;
		private String row2;
		private Double row3;
		private Double row4;
		private int row5;
		private Double row6;
		private Double row7;
		private Double row8;
		private Double row9;
		private Double row10;
		private Double row11;
		private Double row12;
		private Double row13;
		private Double row14;///TO-DO
		private String row15;
		private Double row16;
		private Double row17;
		private Double row18;
		private Double row19;
		private Double row20;
		
		public EtatAppreciatif(Object[] i) {
			//this.row0= (Designation)i[0];
			this.row1= (String)i[1];
			this.row2= (String)i[2];
			//this.row3= (Double)i[3];
			this.row4= (Double)i[4];
			//this.row5= (Integer) i[5];
			this.row6= (Double)i[6];
			this.row7= (Double)i[7];
			this.row8= (Double)i[8];
			this.row9= (Double)i[9];
			this.row10= (Double)i[10];
			this.row11= (Double)i[11];
			this.row12= (Double)i[12];
			this.row13= (Double)i[13];
			this.row14= (Double) i[14];
			this.row15 = (String) i[15];
			this.row16 = (Double) i[16];
			this.row17 = (Double) i[17];
			this.row18 = (Double) i[18];
			this.row19 = (Double) i[19];
			this.row20 = (Double) i[20];
			
		}

		public Designation getRow0() {
			return row0;
		}

		public void setRow0(Designation row0) {
			this.row0 = row0;
		}

		public String getRow1() {
			return row1;
		}

		public void setRow1(String row1) {
			this.row1 = row1;
		}

		public String getRow2() {
			return row2;
		}

		public void setRow2(String row2) {
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

		public int getRow5() {
			return row5;
		}

		public void setRow5(int row5) {
			this.row5 = row5;
		}

		public Double getRow6() {
			return row6;
		}

		public void setRow6(Double row6) {
			this.row6 = row6;
		}

		public Double getRow7() {
			return row7;
		}

		public void setRow7(Double row7) {
			this.row7 = row7;
		}

		public Double getRow8() {
			return row8;
		}

		public void setRow8(Double row8) {
			this.row8 = row8;
		}

		public Double getRow9() {
			return row9;
		}

		public void setRow9(Double row9) {
			this.row9 = row9;
		}

		public Double getRow10() {
			return row10;
		}

		public void setRow10(Double row10) {
			this.row10 = row10;
		}

		public Double getRow11() {
			return row11;
		}

		public void setRow11(Double row11) {
			this.row11 = row11;
		}

		public Double getRow12() {
			return row12;
		}

		public void setRow12(Double row12) {
			this.row12 = row12;
		}

		public Double getRow13() {
			return row13;
		}

		public void setRow13(Double row13) {
			this.row13 = row13;
		}

		public Double getRow14() {
			return row14;
		}

		public void setRow14(Double row14) {
			this.row14 = row14;
		}

		public String getRow15() {
			return row15;
		}

		public void setRow15(String row15) {
			this.row15 = row15;
		}

		public Double getRow16() {
			return row16;
		}

		public void setRow16(Double row16) {
			this.row16 = row16;
		}

		public Double getRow17() {
			return row17;
		}

		public void setRow17(Double row17) {
			this.row17 = row17;
		}

		public Double getRow18() {
			return row18;
		}

		public void setRow18(Double row18) {
			this.row18 = row18;
		}

		public Double getRow19() {
			return row19;
		}

		public void setRow19(Double row19) {
			this.row19 = row19;
		}

		public Double getRow20() {
			return row20;
		}

		public void setRow20(Double row20) {
			this.row20 = row20;
		}
		
	}

	public List<Object[]> getList() {
		return list;
	}
	public void setList(List<Object[]> list) {
		this.list = list;
	}
}
