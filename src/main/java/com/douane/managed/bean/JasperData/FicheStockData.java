package com.douane.managed.bean.JasperData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class FicheStockData {
	List <Object[]> list;
	public FicheStockData(List <Object[]> l){
		
		this.list = l;
	}
	public JRBeanCollectionDataSource getDataSource() {
		List<FicheStock> newOne = new ArrayList<FicheStock>();
		for (Object[] i : this.list ) {
			FicheStock la = new FicheStock(i);
			newOne.add(la);
		}
		
		return new JRBeanCollectionDataSource(newOne);
	}
	
	public class FicheStock{
		private Date row0;
		private String row1;
		private Long row2;
		private Long row3;
		private String row4;
		private Long row5;
		private Long row6;
		
		public FicheStock(Object[] i) {
			this.row0= (Date)i[0];
			this.row1= (String)i[1];
			this.row2= (Long)i[2];
			this.row3= (Long)i[3];
			this.row4= (String)i[4];
			this.row5= (Long) i[5];
			this.row6= (Long)i[6];
		}

		public Date getRow0() {
			return row0;
		}

		public void setRow0(Date row0) {
			this.row0 = row0;
		}

		public String getRow1() {
			return row1;
		}

		public void setRow1(String row1) {
			this.row1 = row1;
		}

		public Long getRow2() {
			return row2;
		}

		public void setRow2(Long row2) {
			this.row2 = row2;
		}

		public Long getRow3() {
			return row3;
		}

		public void setRow3(Long row3) {
			this.row3 = row3;
		}

		public String getRow4() {
			return row4;
		}

		public void setRow4(String row4) {
			this.row4 = row4;
		}

		public Long getRow5() {
			return row5;
		}

		public void setRow5(Long row5) {
			this.row5 = row5;
		}

		public Long getRow6() {
			return row6;
		}

		public void setRow6(Long row6) {
			this.row6 = row6;
		}		
	}

	public List<Object[]> getList() {
		return list;
	}
	public void setList(List<Object[]> list) {
		this.list = list;
	}
}

