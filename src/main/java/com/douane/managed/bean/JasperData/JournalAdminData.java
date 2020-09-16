package com.douane.managed.bean.JasperData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.douane.entite.Designation;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JournalAdminData {
	List <Object[]> list;
	public JournalAdminData(List <Object[]> l){
		
		this.list = l;
	}
	public JRBeanCollectionDataSource getDataSource() {
		List<JournalAdmin> newOne = new ArrayList<JournalAdmin>();
		for (Object[] i : this.list ) {
			JournalAdmin la = new JournalAdmin(i);
			newOne.add(la);
		}
		
		return new JRBeanCollectionDataSource(newOne);
	}
	
	public class JournalAdmin{
		private String row0;
		private Date row1;
		private String row2;
		private String row3;
		private String row4;
		private Long row5;
		private Double row6;
		private Double row7;
		
		public JournalAdmin(Object[] i) {
			this.row0= (String)i[0];
			this.row1= (Date)i[1];
			this.row2= (String)i[2];
			this.row3= (String)i[3];
			this.row4= (String)i[4];
			this.row5= (Long) i[5];
			this.row6= (Double)i[6];
			this.row7= (Double)i[7];
		}

		public String getRow0() {
			return row0;
		}

		public void setRow0(String row0) {
			this.row0 = row0;
		}

		public Date getRow1() {
			return row1;
		}

		public void setRow1(Date row1) {
			this.row1 = row1;
		}

		public String getRow2() {
			return row2;
		}

		public void setRow2(String row2) {
			this.row2 = row2;
		}

		public String getRow3() {
			return row3;
		}

		public void setRow3(String row3) {
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
		
	}

	public List<Object[]> getList() {
		return list;
	}
	public void setList(List<Object[]> list) {
		this.list = list;
	}
}
