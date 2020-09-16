package com.douane.managed.bean.form;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
public class mockData {
	public JRBeanCollectionDataSource getGrandLivreMock() throws ParseException {
		List<ficheStock> listItems = new ArrayList<ficheStock>();
		ficheStock item1 = new ficheStock("04/01/2017");
		item1.setEntreRef("Facture N° A01");
		item1.setEntreQuant("20");
		ficheStock item2 = new ficheStock("13/12/2017");
		item2.setSortiRef("OS N° 25/17 ");
		item2.setSortiQuant("4");
		ficheStock item3 = new ficheStock("14/12/2017");
		item3.setEntreRef("N° A 016/17");
		item3.setEntreQuant("5");
		ficheStock item4 = new ficheStock("06/06/2018");
		item4.setSortiRef("OS N° 30/17");
		item4.setSortiQuant("4");
		ficheStock item5 = new ficheStock("13/12/2017");
		item5.setSortiRef("OS N° 24/17 ");
		item5.setSortiQuant("4");
		
		listItems.add(item1);
		listItems.add(item3);
		listItems.add(item2);
		listItems.add(item4);
		listItems.add(item5);
		
		JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);
		return itemsJRBean;
	}
	public class ficheStock{
		public Date date;
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public String entreRef;
		public String entreQuant;
		public String entreCum;
		public String sortiRef;
		public String sortiQuant;
		public String sortiCum;
		public String quant;
		@SuppressWarnings("deprecation")
		public ficheStock(String d) throws ParseException {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			this.date = format.parse(d);
			this.entreRef="";
			this.entreQuant="";
			this.entreCum="";
			this.sortiRef="";
			this.sortiQuant="";
			this.sortiCum="";
			this.quant="";
		}
		public String getEntreRef() {
			return entreRef;
		}
		public void setEntreRef(String entreRef) {
			this.entreRef = entreRef;
		}
		public String getEntreQuant() {
			return entreQuant;
		}
		public void setEntreQuant(String entreQuant) {
			this.entreQuant = entreQuant;
		}
		public String getEntreCum() {
			return entreCum;
		}
		public void setEntreCum(String entreCum) {
			this.entreCum = entreCum;
		}
		public String getSortiRef() {
			return sortiRef;
		}
		public void setSortiRef(String sortiRef) {
			this.sortiRef = sortiRef;
		}
		public String getSortiQuant() {
			return sortiQuant;
		}
		public void setSortiQuant(String sortiQuant) {
			this.sortiQuant = sortiQuant;
		}
		public String getSortiCum() {
			return sortiCum;
		}
		public void setSortiCum(String sortiCum) {
			this.sortiCum = sortiCum;
		}
		public String getQuant() {
			return quant;
		}
		public void setQuant(String quant) {
			this.quant = quant;
		}
	}
}
