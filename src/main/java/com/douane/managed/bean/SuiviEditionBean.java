package com.douane.managed.bean;

import com.douane.entite.*;
import com.douane.metier.referentiel.IRefMetier;
import com.douane.metier.user.IUserMetier;
import com.douane.model.EtatOperation;
import come.douane.dao.operation.IOperationDAO;

import org.apache.poi.util.SystemOutLogger;
import org.hamcrest.core.IsInstanceOf;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;

import com.douane.requesthttp.RequestFilter;

/**
 * Created by hasina on 11/3/17.
 */

@ManagedBean(name = "suivieditionBean")
@ViewScoped
public class SuiviEditionBean implements Serializable {

	@ManagedProperty(value = "#{usermetier}")
	IUserMetier usermetierimpl;

	public SuiviEditionBean() {
		Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
		this.direction = cur.getDirection();
	}

	private Agent agentOperateur;
	private Direction direction;
	private Date startDate;
	private Date endDate;

	private Operation curentOperation;

	public IOperationDAO getOperationdao() {
		return operationdao;
	}

	public void setOperationdao(IOperationDAO operationdao) {
		this.operationdao = operationdao;
	}

	@Autowired
	@ManagedProperty(value = "#{operationdao}")
	private IOperationDAO operationdao;

	/*
	 * private List<OpEntree> listOperationEntree; private List<OpSortie>
	 * listOperationSortie; private List<Operation> listOperatoinByOperateur;
	 * private List<OpEntree> listOperationEntreeByOperator; private List<OpSortie>
	 * listOperationSortieByOperator; private List<Operation>
	 * listOperatoinByDirection; private List<OpEntree>
	 * listOperationEntreeByDirection; private List<OpSortie>
	 * listOperationSortieByDirection; private List<Operation> listOperationBetween;
	 * private List<OpEntree> listOperationEntreeByMateriel; private List<OpSortie>
	 * listOperationSortieByMateriel; private List<OpEntree>
	 * listOperationEntreeByMaterielByDate; private List<OpSortie>
	 * listOperationSortieByMaterielByDate; private List<OpAttribution>
	 * listOperationAttribution; private List<OpDettachement>
	 * listOperationDetachement; private List<OpAttribution>
	 * listOperationAttributionByOperator; private List<OpDettachement>
	 * listOperationDetachementByOperator; private List<OpAttribution>
	 * listOperationAttributionByDirection; private List<OpDettachement>
	 * listOperationDeetachementByDirection; private List<OpAttribution>
	 * listOperationAttributionByMateriel; private List<OpDettachement>
	 * listOperationDetachementByMateriel;
	 */

	private Materiel materiel;

	private int annee;

	// ----------ALL LIST BY METHOD------------------
	private List<Operation> listAllOperation;
	private List<OpEntree> listOperationEntree;
	private List<OpSortie> listOperationSortie;
	private List<Operation> listOperationByOperateur;
	private List<OpEntree> listOperationEntreeByOperator;
	private List<OpSortie> listOperationSortieByOperator;
	private List<Operation> listOperatoinByDirection;
	private List<OpEntree> listOperationEntreeByDirection;
	private List<OpSortie> listOperationSortieByDirection;
	private List<Operation> listOperationBetween;

	private List<Operation> listOperationByDirectionByYearByDateAsc;

	private List<OpEntree> listOperationEntreeByMateriel;
	private List<OpSortie> listOperationSortieByMateriel;
	private List<OpEntree> listOperationEntreeByMaterielByDate;
	private List<OpSortie> listOperationSortieByMaterielByDate;
	private List<OpAttribution> listOperationAttribution;
	private List<OpDettachement> listOperationDetachement;
	private List<OpAttribution> listOperationAttributionByOperator;
	private List<OpDettachement> listOperationDetachementByOperator;
	private List<OpDettachement> listOperationDetachementByDirection;
	private List<OpAttribution> listOperationAttributionByDirection;
	private List<OpDettachement> listOperationDeetachementByDirection;
	private List<OpAttribution> listOperationAttributionByMateriel;
	private List<OpDettachement> listOperationDetachementByMateriel;

	// ----------ALL LIST BY METHOD------------------

	private List<Materiel> listMaterielByDet;

	private Double total;

	public void setAnnee(int t) {
		this.annee = t;
	}

	public int getAnnee() {
		return this.annee;
	}

	public String setAnnee1(int t) {
		this.annee = t;
		return "annee";
	}

	public void setTotal(Double t) {
		this.total = t;
	}

	public Double getTotal() {
		return this.total;
	}

	public void setListMaterielByDet(List<Materiel> listMateriel) {
		this.listMaterielByDet = listMateriel;
	}

	public List<Materiel> getListMaterielByDet() {
		// List<Materiel> listmatcorrespondant;
		if (listMaterielByDet == null) {
			return usermetierimpl.getListMat();
		} else {
			// return usermetierimpl.getListMatByDet(getDetenteur());
			return listMaterielByDet;
		}
	}

	public List<Operation> getListAllOperation() {
		return usermetierimpl.getListOp();
	}

	public void setListAllOperation(List<Operation> l) {
		this.listAllOperation = l;
	}

	public List<OpEntree> getListOperationEntree() {
		// setListOperationEntree(usermetierimpl.getListOpEntree());
		// return listOperationEntree;
		return usermetierimpl.getListOpEntree();

	}

	public void setListOperationEntree(List<OpEntree> l) {

		this.listOperationEntree = l;
	}

	public List<OpSortie> getListOperationSortie() {
		return usermetierimpl.getListOpSortie();
	}

	public void setListOperationSortie(List<OpSortie> l) {
		this.listOperationSortie = l;
	}

	// get Agent and set Agent operator
	public Agent getAgentOperateur() {
		return agentOperateur;
	}

	public void setAgentOperateur(Agent agentOperateur) {
		this.agentOperateur = agentOperateur;
	}

	// -------------GET List of operations by OPERATOR
	// --------------------------------
	// get direction
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public List<Operation> getListOperationByOperateur() {
		return usermetierimpl.getListOpByOperator(this.agentOperateur);
	}

	public void setListOperationByOperateur(List<Operation> l) {
		this.listOperationByOperateur = l;
	}

	public List<OpEntree> getListOperationEntreeByOperator() {
		return usermetierimpl.getListOpEntreeByOperator(this.agentOperateur);
	}

	public void setListOperationEntreeByOperator(List<OpEntree> l) {
		this.listOperationEntreeByOperator = l;
	}

	public List<OpSortie> getListOperationSortieByOperator() {
		return usermetierimpl.getListOpSortieByOperator(this.agentOperateur);
	}

	public void setListOperationSortieByOperator(List<OpSortie> l) {
		this.listOperationSortieByOperator = l;
	}

	// -------------GET List of operations by DIRECTION
	// --------------------------------
	public List<Operation> getListOperatoinByDirection() {
		if (listOperatoinByDirection == null) {
			Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
			listOperatoinByDirection = usermetierimpl.getListOpByDirection(agent.getDirection());
		}
		return listOperatoinByDirection;
	}

	public void setListOperatoinByDirection(List<Operation> l) {
		this.listOperatoinByDirection = l;
	}

	public List<OpEntree> getListOperationEntreeByDirection() {
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		Date sdate = new GregorianCalendar(year - 2, Calendar.JANUARY, 1).getTime();
		Date edate = new GregorianCalendar(year + 1, Calendar.DECEMBER, 30).getTime();
		return usermetierimpl.getListOpEntreeByDirection(agent.getDirection(), sdate, edate);
	}

	public void setListOperationEntreeByDirection(List<OpEntree> l) {
		this.listOperationEntreeByDirection = l;
	}

	public List<OpSortie> getListOperationSortieByDirection() {
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		Date sdate = new GregorianCalendar(year - 2, Calendar.JANUARY, 1).getTime();
		Date edate = new GregorianCalendar(year + 1, Calendar.DECEMBER, 30).getTime();
		return usermetierimpl.getListOpSortieByDirection(agent.getDirection(), sdate, edate);
	}

	public void setListOperationSortieByDirection(List<OpSortie> l) {
		this.listOperationSortieByDirection = l;
	}

	// ------------GET List of Operations By DATE-------------------
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Operation> getListOperationBetween(Date startDate, Date endDate) {
		return getListOperationBetween(startDate, endDate);
	}

	public void setListOperationBetween(List<Operation> l) {
		this.listOperationBetween = l;
	}

	public List<Operation> getListOperationByDirectionByYearByDateAsc() {
		// return getListOperationBetween(startDate, endDate);

		Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
		Date sdate = new GregorianCalendar(getAnnee(), Calendar.JANUARY, 1).getTime();
		Date edate = new GregorianCalendar(getAnnee(), Calendar.DECEMBER, 30).getTime();
		return usermetierimpl.getListOperationByDirectionByYearByDateAsc(cur.getDirection(), sdate, edate);
	}

	public void setListOperationByDirectionByYearByDateAsc(List<Operation> l) {
		this.listOperationByDirectionByYearByDateAsc = l;
	}

	// ------------GET List of Operations By Materiel-------------------
	public Materiel getMateriel() {
		return materiel;
	}

	public void setMateriel(Long materielid) {
		Materiel a = usermetierimpl.getMatById(materielid);
		System.out.print(
				"======================================SET MAT================================================================="
						+ materielid);

		this.materiel = a;
	}

	public List<OpEntree> getListOperationEntreeByMateriel() {
		return usermetierimpl.getListOpEntreeByMat(getMateriel());
	}

	public void setListOperationEntreeByMateriel(List<OpEntree> l) {
		this.listOperationEntreeByMateriel = l;
	}

	public List<OpSortie> getListOperationSortieByMateriel() {
		return usermetierimpl.getListOpSortieByMat(getMateriel());
	}

	public void setListOperationSortieByMateriel(List<OpSortie> l) {
		this.listOperationSortieByMateriel = l;
	}

	// ------------GET List of Materiel By Date-------------------
	public List<OpEntree> getListOperationEntreeByMaterielByDate(Date startDate, Date endDate) {
		return usermetierimpl.getListOpEntreeByMatBDate(getMateriel(), startDate, endDate);
	}

	public void setListOperationEntreeByMaterielByDate(List<OpEntree> l) {
		this.listOperationEntreeByMaterielByDate = l;
	}

	public List<OpSortie> getListOperationSortieByMaterielByDate(Date startDate, Date endDate) {
		return usermetierimpl.getListOpSortieByMatBDate(getMateriel(), startDate, endDate);
	}

	public void setListOperationSortieByMaterielByDate(List<OpSortie> l) {
		this.listOperationSortieByMaterielByDate = l;
	}

	// ------------------liste des operations atributions et dettachements
	// ---------------------
	public List<OpAttribution> getListOperationAttribution() {
		return usermetierimpl.getListOpAttribution();
	}

	public void setListOperationAttribution(List<OpAttribution> l) {
		this.listOperationAttribution = l;
	}

	public List<OpDettachement> getListOperationDetachement() {
		return usermetierimpl.getListOpDettachement();
	}

	public void setListOperationDetachement(List<OpDettachement> l) {
		this.listOperationDetachement = l;
	}

	public List<OpAttribution> getListOperationAttributionByOperator() {
		return usermetierimpl.getListOpAttrByOperator(getAgentOperateur());
	}

	public void setListOperationAttributionByOperator(List<OpAttribution> l) {
		this.listOperationAttributionByOperator = l;
	}

	public List<OpDettachement> getListOperationDetachementByOperator() {
		return usermetierimpl.getListOpDettByOperatort(getAgentOperateur());
	}

	public void setListOperationDetachementByOperator(List<OpDettachement> l) {
		this.listOperationDetachementByOperator = l;
	}

	public List<OpAttribution> getListOperationAttributionByDirection() {
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		Date sdate = new GregorianCalendar(year - 2, Calendar.JANUARY, 1).getTime();
		Date edate = new GregorianCalendar(year + 1, Calendar.DECEMBER, 30).getTime();
		return usermetierimpl.getListOpAttrByDirection(agent.getDirection(), sdate, edate);
	}

	public void setListOperationAttributionByDirection(List<OpAttribution> l) {
		this.listOperationAttributionByDirection = l;
	}

	public List<OpDettachement> getListOperationDeetachementByDirection() {
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		Date sdate = new GregorianCalendar(year - 2, Calendar.JANUARY, 1).getTime();
		Date edate = new GregorianCalendar(year + 1, Calendar.DECEMBER, 30).getTime();
		return usermetierimpl.getListOpDettByDirection(agent.getDirection(), sdate, edate);
	}

	public void setListOperationDeetachementByDirection(List<OpDettachement> l) {
		this.listOperationDeetachementByDirection = l;
	}

	public List<OpAttribution> getListOperationAttributionByMateriel() {
		return usermetierimpl.getListOpAttrByMat(getMateriel());
	}

	public List<OpAttribution> getListOperationAttributionByMateriel1(Materiel m) {
		return usermetierimpl.getListOpAttrByMat(m);
	}

	public void setListOperationAttributionByMateriel(List<OpAttribution> l) {
		this.listOperationAttributionByMateriel = l;
	}

	public List<OpDettachement> getListOperationDetachementByMateriel() {
		return usermetierimpl.getListOpDettByMat(getMateriel());
	}

	public void setListOperationDetachementByMateriel(List<OpDettachement> l) {
		this.listOperationDetachementByMateriel = l;
	}

	// ----------------NEW SETTER AND GETTER---------------------

	public IUserMetier getUsermetierimpl() {
		return usermetierimpl;
	}

	public void setUsermetierimpl(IUserMetier usermetierimpl) {
		this.usermetierimpl = usermetierimpl;
	}

	public void setCurentOperation(Operation operation) {
		this.curentOperation = operation;
	}

	public void setCurentOperation2(Operation operation) {
		this.curentOperation = operation;

		if (((OpAttribution) operation).getDetenteur() != null) {
			this.setListMaterielByDet(usermetierimpl.getListMatByDet(((OpAttribution) operation).getDetenteur()));

			ListIterator<Materiel> it = this.getListMaterielByDet().listIterator();
			if (it != null) {
				this.setTotal(Double.parseDouble("0"));
				while (it.hasNext()) {
					setTotal(this.total + (Double) (it.next().getPu()));
				}
			}
		} else {

		}

	}

	public Operation getCurentOperation() {
		return this.curentOperation;
	}

	// -----------------GETTER AND SETTER FOR OPERATION --------------
	private List<OpEntree> listOpEn;
	private List<OpSortie> listOpSo;
	private List<OpAttribution> listOpAttr;
	private List<OpDettachement> listOpDet;

	public List<OpEntree> getListOpEn() {
		return usermetierimpl.getListOpEntreeByMatAndByState(getMateriel(), EtatOperation.ACCEPTED);
	}

	public void setListOpEn(List<OpEntree> listOpEn) {
		this.listOpEn = listOpEn;
	}

	public List<OpSortie> getListOpSo() {
		return usermetierimpl.getListOpSortieByMatAndByState(getMateriel(), EtatOperation.ACCEPTED);
	}

	public void setListOpSo(List<OpSortie> listOpSo) {
		this.listOpSo = listOpSo;
	}

	public List<OpAttribution> getListOpAttr() {
		return usermetierimpl.getListOpAttrByMatAndByState(getMateriel(), EtatOperation.ACCEPTED);
	}

	public void setListOpAttr(List<OpAttribution> listOpAttr) {
		this.listOpAttr = listOpAttr;
	}

	public List<OpDettachement> getListOpDet() {
		return usermetierimpl.getListOpDettByMatAndByState(getMateriel(), EtatOperation.ACCEPTED);
	}

	public void setListOpDet(List<OpDettachement> listOpDet) {
		this.listOpDet = listOpDet;
	}

	// -----------------TO DO 30 12--------------
	private List<Operation> listOpEntreeAndSortieByDirectionByYearByDateAsc;

	public List<Operation> getListOpEntreeAndSortieByDirectionByYearByDateAsc() {
		Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
		Date sdate = new GregorianCalendar(2010, Calendar.JANUARY, 1).getTime();
		Date edate = new GregorianCalendar(2018, Calendar.DECEMBER, 30).getTime();
		// List<Operation> l =
		// usermetierimpl.getListOpEntreeAndSortieByDirectionByYearByDateAsc(cur.getDirection(),
		// sdate, edate);
		System.out.print(
				"=======================================================================================================");
		System.out.print(
				"=======================================================================================================");
		System.out.print(
				"=======================================================================================================");
		List<Operation> l = operationdao.getListOpEntreeAndSortieByDirectionByYearByDateAsc(cur.getDirection(), sdate,
				edate);
		for (Operation o : l) {
			System.out.print("listOperation=======" + o.getState());
		}
		return usermetierimpl.getListOpEntreeAndSortieByDirectionByYearByDateAsc(cur.getDirection(), sdate, edate);
		// return
		// usermetierimpl.getListOpEntreeAndSortieByDirectionByYearByDateAsc(cur.getDirection(),
		// getStartDate(), getEndDate());
	}

	public void setListOpEntreeAndSortieByDirectionByYearByDateAsc(
			List<Operation> listOpEntreeAndSortieByDirectionByYearByDateAsc) {
		this.listOpEntreeAndSortieByDirectionByYearByDateAsc = listOpEntreeAndSortieByDirectionByYearByDateAsc;
	}

	// -------TEST FINAL-------

	public List<OpEntree> getListOpEntreeByDirectionByYearByDateAsc(Direction d, Date startDate, Date endDate) {
		return usermetierimpl.getListOpEntreeByDirectionByYearByDateAsc(d, startDate, endDate);
	}

	public List<OpSortie> getListOpSortieByDirectionByYearByDateAsc(Direction d, Date startDate, Date endDate) {
		return usermetierimpl.getListOpSortieByDirectionByYearByDateAsc(d, startDate, endDate);
	}

	// -----NEW FORM OF GETTER
	private List<OperationES> listOpESForJournal;

	public List<OperationES> getListOpESForJournal() {
		Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		Date sdate = new GregorianCalendar(year - 2, Calendar.JANUARY, 1).getTime();
		Date edate = new GregorianCalendar(year + 1, Calendar.DECEMBER, 30).getTime();
		return usermetierimpl.getListOpESForJournal(cur.getDirection(), sdate, edate);
	}

	public void setListOpESForJournal(List<OperationES> listOpESForJournal) {
		this.listOpESForJournal = listOpESForJournal;
	}

	private List<OpSortie> listOpSortieValideByDirection;

	public List<OpSortie> getListOpSortieValideByDirection() {
		Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
		return usermetierimpl.getListOpSortieValideByDirection(cur.getDirection());
	}

	public void setListOpSortieValideByDirection(List<OpSortie> listOpSortieValideByDirection) {
		this.listOpSortieValideByDirection = listOpSortieValideByDirection;
	}

	// ------------EDITION

	public void setListobjectForInvetaire(List<Object[]> listobjectForInvetaire) {
		this.listobjectForInvetaire = listobjectForInvetaire;
	}

	public List<OpEntree> getListOpentreeForOrdre() {
		Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
		return usermetierimpl.listOpentreeByStateByDirection(EtatOperation.ACCEPTED, cur.getDirection());
	}

	public void setListOpentreeForOrdre(List<OpEntree> listOpentreeForOrdre) {
		this.listOpentreeForOrdre = listOpentreeForOrdre;
	}

	private List<Object[]> listobjectForInvetaire;

	private List<OpEntree> listOpentreeForOrdre;

	/*
	 * LISTE OF METHODS FOR CA EDITIONS
	 * 
	 */

	private List<Operation> listOpESArtByDirection;

	public List<Operation> getListOpESArtByDirection(Direction d, Date sdate, Date edate) {
		if (d == null) {
			Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
			d = cur.getDirection();
		}

		return usermetierimpl.getListOpESArtValideByDirection(d, sdate, edate);
	}

	public void setListOpESArtByDirection(List<Operation> listOpESArtByDirection) {
		this.listOpESArtByDirection = listOpESArtByDirection;
	}

	private List<Object[]> listESForJournal;

	public List<Object[]> getListESForJournal(Date fdate) {

		if (listESForJournal == null) {
			Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
			// this.direction = cur.getDirection();
			Date date = new Date();
			if (fdate != null) {
				date = fdate;
				System.out.println(" fdate not null");
			} else {
				System.out.println(" fdate  null");
			}
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			int year = calendar.get(Calendar.YEAR);
			System.out.println(" my year " + year);
			Date sdate = new GregorianCalendar(year, Calendar.JANUARY, 1).getTime();
			Date edate = new GregorianCalendar(year, Calendar.DECEMBER, 30).getTime();
			List<OperationES> listop = usermetierimpl.getListOpESForJournal(cur.getDirection(), sdate, edate);
			Collections.sort(listop, new Comparator<OperationES>() {
				public int compare(OperationES o1, OperationES o2) {
					Long id1 = o1.getId();
					Long id2 = o2.getId();
					// System.out.println("date :" + d1);
					return id1.compareTo(id2);
				}
			});
			List<Object[]> listobjectForJournal = new ArrayList<Object[]>();
			Long i = 1L;
			for (OperationES op : listop) {
				Object[] row = new Object[13];

				if (op instanceof OpEntree) {
					List<Object[]> bydesignation1 = (this.getDesingationByOpEntree(op));

					for (Object[] nom : bydesignation1) {
						List<Object[]> liste = (List<Object[]>) nom[2];
						for (Object[] des : liste) {

							Designation d = (Designation) des[0];
							// id
							row[12] = op.getId();
							row[0] = i;
							// numero d'ordre
							row[1] = op.getNumoperation();
							// date
							row[2] = op.getDate();
							// origine
							row[3] = d.getOrigine();
							// designation
							String mar = "";
							if (d.getMarque() != null) {
								mar = d.getMarque().getDesignation();
							}
							String rens = "";
							if (d.getRenseignement() != null) {
								rens = d.getRenseignement();
							}
							row[4] = d.getTypematerieladd().getDesignation() + " - " + mar + " - " + rens
							// + " - " + mat.getNumSerie();
							;
							// espece unite
							row[5] = d.getEspeceUnite();
							// pu
							row[6] = d.getPu();
							// nombre par desingation entree
							row[7] = des[1];
							// total entree
							row[8] = d.getPu() * (Long) row[7];
							// nombre par desingation sortie
							row[9] = new Long(0);
							// total sortie
							row[10] = new Double(0);
							row[11] = d;
							listobjectForJournal.add(row);
							row = new Object[13];
							i = i + 1;
						}
					} /*
						 * for(Materiel mat :((OpEntree) op).getListMat()) { //id row[0] = op.getId();
						 * //numero d'ordre row[1] = op.getNumoperation(); //date row[2] = op.getDate();
						 * //origine row[3] = mat.getDesign().getOrigine(); // designation row[4] =
						 * mat.getDesign().getTypematerieladd().getDesignation() + " - " +
						 * mat.getDesign().getMarque() + " - " + mat.getDesign().getRenseignement() +
						 * " - " + mat.getNumSerie(); //espece unite row[5] =
						 * mat.getDesign().getEspeceUnite(); //pu row[6] = mat.getDesign().getPu();
						 * //nombre par desingation entree row[7] = 1; //total entree row[8] =
						 * mat.getDesign().getPu()*(Integer)row[7]; //nombre par desingation sortie
						 * row[9] = 0; //total sortie row[10] = 0; row[11] = mat;
						 * listobjectForJournal.add(row); row = new Object[12]; }
						 */

				} else if (op instanceof OpSortie) {
					// id
					row[12] = op.getId();
					row[0] = i;
					// numero d'ordre
					row[1] = op.getNumoperation();
					// date
					row[2] = op.getDate();
					// origine
					if (((OpSortie) op).getMotifsortie() != null) {
						row[3] = ((OpSortie) op).getMotifsortie().getDesignation();
						if (((OpSortie) op).getMotifsortie().getDesignation().equalsIgnoreCase("Affectation")) {
							row[3] = row[3] + " vers " + ((OpSortie) op).getDirec().getCodeDirection();
						}
					}
					// designation
					Materiel mat = op.getMat();
					String marquemat = "Inconnue";
					if (mat.getDesign().getMarque() != null) {
						marquemat = mat.getDesign().getMarque().getDesignation();
					}
					String rens = "";
					if (mat.getDesign().getRenseignement() != null) {
						rens = mat.getDesign().getRenseignement();
					}
					row[4] = mat.getDesign().getTypematerieladd().getDesignation() + " - " + marquemat + " - " + rens
							+ " - " + mat.getNumSerie();
					// espece unite
					row[5] = mat.getDesign().getEspeceUnite();
					// pu
					row[6] = mat.getDesign().getPu();
					// nombre par desingation entree
					row[7] = new Long(0);
					// total entree
					row[8] = new Double(0);
					// nombre par desingation sortie
					row[9] = 1L;
					// total sortie
					row[10] = mat.getDesign().getPu() * (Long) row[9];
					row[11] = mat.getDesign();
					listobjectForJournal.add(row);
					row = new Object[13];
					i = i + 1;
				}
			}

			listESForJournal = listobjectForJournal;

		}

		return listESForJournal;
	}

	public List<Object[]> ourListESForJournal(Direction dir, Date fdate) {
		Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
		// this.direction = cur.getDirection();
		Date date = new Date();
		if (fdate != null) {
			date = fdate;
			System.out.println(" fdate not null");
		} else {
			System.out.println(" fdate  null");
		}
		if (dir == null) {
			dir = cur.getDirection();
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		System.out.println(" my year " + year);
		Date sdate = new GregorianCalendar(year, Calendar.JANUARY, 1).getTime();
		Date edate = new GregorianCalendar(year, Calendar.DECEMBER, 30).getTime();
		List<OperationES> listop = usermetierimpl.getListOpESForJournal(dir, sdate, edate);
		Collections.sort(listop, new Comparator<OperationES>() {
			public int compare(OperationES o1, OperationES o2) {
				Long id1 = o1.getId();
				Long id2 = o2.getId();
				// System.out.println("date :" + d1);
				return id1.compareTo(id2);
			}
		});
		List<Object[]> listobjectForJournal = new ArrayList<Object[]>();
		Long i = 1L;
		for (OperationES op : listop) {
			Object[] row = new Object[13];

			if (op instanceof OpEntree) {
				List<Object[]> bydesignation1 = (this.getDesingationByOpEntree(op));

				for (Object[] nom : bydesignation1) {
					List<Object[]> liste = (List<Object[]>) nom[2];
					for (Object[] des : liste) {

						Designation d = (Designation) des[0];
						// id
						row[12] = op.getId();
						row[0] = i;
						// numero d'ordre
						row[1] = op.getNumoperation();
						// date
						row[2] = op.getDate();
						// origine
						row[3] = d.getOrigine();
						// designation
						String mar = "";
						if (d.getMarque() != null) {
							mar = d.getMarque().getDesignation();
						}
						String rens = "";
						if (d.getRenseignement() != null) {
							rens = d.getRenseignement();
						}
						row[4] = d.getTypematerieladd().getDesignation() + " - " + mar + " - " + rens
						// + " - " + mat.getNumSerie();
						;
						// espece unite
						row[5] = d.getEspeceUnite();
						// pu
						row[6] = d.getPu();
						// nombre par desingation entree
						row[7] = des[1];
						// total entree
						row[8] = d.getPu() * (Long) row[7];
						// nombre par desingation sortie
						row[9] = new Long(0);
						// total sortie
						row[10] = new Double(0);
						row[11] = d;
						listobjectForJournal.add(row);
						row = new Object[13];
						i = i + 1;
					}
				} /*
					 * for(Materiel mat :((OpEntree) op).getListMat()) { //id row[0] = op.getId();
					 * //numero d'ordre row[1] = op.getNumoperation(); //date row[2] = op.getDate();
					 * //origine row[3] = mat.getDesign().getOrigine(); // designation row[4] =
					 * mat.getDesign().getTypematerieladd().getDesignation() + " - " +
					 * mat.getDesign().getMarque() + " - " + mat.getDesign().getRenseignement() +
					 * " - " + mat.getNumSerie(); //espece unite row[5] =
					 * mat.getDesign().getEspeceUnite(); //pu row[6] = mat.getDesign().getPu();
					 * //nombre par desingation entree row[7] = 1; //total entree row[8] =
					 * mat.getDesign().getPu()*(Integer)row[7]; //nombre par desingation sortie
					 * row[9] = 0; //total sortie row[10] = 0; row[11] = mat;
					 * listobjectForJournal.add(row); row = new Object[12]; }
					 */

			} else if (op instanceof OpSortie) {
				// id
				row[12] = op.getId();
				row[0] = i;
				// numero d'ordre
				row[1] = op.getNumoperation();
				// date
				row[2] = op.getDate();
				// origine
				if (((OpSortie) op).getMotifsortie() != null) {
					row[3] = ((OpSortie) op).getMotifsortie().getDesignation();
					if (((OpSortie) op).getMotifsortie().getDesignation().equalsIgnoreCase("Affectation")) {
						row[3] = row[3] + " vers " + ((OpSortie) op).getDirec().getCodeDirection();
					}
				}
				// designation
				Materiel mat = op.getMat();
				String marqueMat = "Inconnue";
				if (mat.getDesign().getMarque() != null) {
					marqueMat = mat.getDesign().getMarque().getDesignation();
				}
				String rens = "";
				if (mat.getDesign().getRenseignement() != null) {
					rens = mat.getDesign().getRenseignement();
				}
				row[4] = mat.getDesign().getTypematerieladd().getDesignation() + " - " + marqueMat + " - " + rens
						+ " - " + mat.getNumSerie();
				// espece unite
				row[5] = mat.getDesign().getEspeceUnite();
				// pu
				row[6] = mat.getDesign().getPu();
				// nombre par desingation entree
				row[7] = new Long(0);
				// total entree
				row[8] = new Double(0);
				// nombre par desingation sortie
				row[9] = 1L;
				// total sortie
				row[10] = mat.getDesign().getPu() * (Long) row[9];
				row[11] = mat.getDesign();
				listobjectForJournal.add(row);
				row = new Object[13];
				i = i + 1;
			}
		}
		return listobjectForJournal;
	}

	public List<Object[]> mygetFListESForJournal(Date start, Date fin) {
		Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
		System.out.println("my es " + start);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		int year = calendar.get(Calendar.YEAR);
		Date sdat = new GregorianCalendar(year, Calendar.JANUARY, 1).getTime();
		List<Object[]> listebyyeaer = getListESForJournal(sdat);
		List<Object[]> listefiltered = new ArrayList<Object[]>();
		for (Object[] o : listebyyeaer) {
			if (start.compareTo((Date) (o[2])) >= 0 && fin.compareTo((Date) (o[2])) <= 0)
				listefiltered.add(o);
		}
		return listefiltered;

	}

	public List<Object[]> getFListESForJournal(Date start, Date fin) {
		Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		// int year = calendar.get(Calendar.YEAR);
		Date sdate = start;
		Date edate = fin;
		List<OperationES> listop = usermetierimpl.getListOpESForJournal(cur.getDirection(), sdate, edate);
		List<Object[]> listobjectForJournal = new ArrayList<Object[]>();
		for (OperationES op : listop) {
			Object[] row = new Object[12];
			if (op instanceof OpEntree) {
				List<Object[]> bydesignation1 = (this.getDesingationByOpEntree(op));

				for (Object[] nom : bydesignation1) {
					List<Object[]> liste = (List<Object[]>) nom[2];
					for (Object[] des : liste) {
						Designation d = (Designation) des[0];
						// id
						row[0] = op.getId();
						// numero d'ordre
						row[1] = op.getNumoperation();
						// date
						row[2] = op.getDate();
						// origine
						row[3] = d.getOrigine();
						// designation
						String mar = "";
						if (d.getMarque() != null) {
							mar = d.getMarque().getDesignation();
						}
						String rens = "";
						if (d.getRenseignement() != null) {
							rens = d.getRenseignement();
						}
						row[4] = d.getTypematerieladd().getDesignation() + " - " + mar + " - " + rens
						// mat.getNumSerie();
						;
						// espece unite
						row[5] = d.getEspeceUnite();
						// pu
						row[6] = d.getPu();
						// nombre par desingation entree
						row[7] = des[1];
						// total entree
						row[8] = d.getPu() * (Long) row[7];
						// nombre par desingation sortie
						row[9] = new Long(0);
						// total sortie
						row[10] = new Double(0);
						row[11] = d;
						listobjectForJournal.add(row);
						row = new Object[12];
					}
				} /*
					 * for(Materiel mat :((OpEntree) op).getListMat()) { //id row[0] = op.getId();
					 * //numero d'ordre row[1] = op.getNumoperation(); //date row[2] = op.getDate();
					 * //origine row[3] = mat.getDesign().getOrigine(); // designation row[4] =
					 * mat.getDesign().getTypematerieladd().getDesignation() + " - " +
					 * mat.getDesign().getMarque() + " - " + mat.getDesign().getRenseignement() +
					 * " - " + mat.getNumSerie(); //espece unite row[5] =
					 * mat.getDesign().getEspeceUnite(); //pu row[6] = mat.getDesign().getPu();
					 * //nombre par desingation entree row[7] = 1; //total entree row[8] =
					 * mat.getDesign().getPu()*(Integer)row[7]; //nombre par desingation sortie
					 * row[9] = 0; //total sortie row[10] = 0; row[11] = mat;
					 * listobjectForJournal.add(row); row = new Object[12]; }
					 */

			} else if (op instanceof OpSortie) {
				// id
				row[0] = op.getId();
				// numero d'ordre
				row[1] = op.getNumoperation();
				// date
				row[2] = op.getDate();
				// origine
				row[3] = ((OpSortie) op).getMotifsortie().getDesignation();
				if (((OpSortie) op).getMotifsortie().getDesignation().equalsIgnoreCase("Affectation")) {
					row[3] = row[3] + " vers " + ((OpSortie) op).getDirec().getCodeDirection();
				}
				// designation
				Materiel mat = op.getMat();
				String marqueMat = "Inconnue";
				if (mat.getDesign().getMarque() != null) {
					marqueMat = mat.getDesign().getMarque().getDesignation();
				}
				String rens = "";
				if (mat.getDesign().getRenseignement() != null) {
					rens = mat.getDesign().getRenseignement();
				}
				row[4] = mat.getDesign().getTypematerieladd().getDesignation() + " - " + marqueMat + " - " + rens
						+ " - " + mat.getNumSerie();
				// espece unite
				row[5] = mat.getDesign().getEspeceUnite();
				// pu
				row[6] = mat.getDesign().getPu();
				// nombre par desingation entree
				row[7] = new Long(0);
				// total entree
				row[8] = new Double(0);
				// nombre par desingation sortie
				row[9] = 1L;
				// total sortie
				row[10] = mat.getDesign().getPu() * (Long) row[9];
				row[11] = mat.getDesign();
				listobjectForJournal.add(row);
				row = new Object[12];
			}
		}
		return listobjectForJournal;
	}

	public void setListESForJournal(List<Object[]> listESForJournal) {
		this.listESForJournal = listESForJournal;
	}

	private List<Object[]> listESForGrandLivre;
	private List<Object[]> listESForGrandLivreOld;

	// OLD

	public List<Object[]> getListESForGrandLivreOld() {
		if (listESForGrandLivreOld == null) {

			Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
			Date date = new Date();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			int year = calendar.get(Calendar.YEAR);
			Date sdate = new GregorianCalendar(year , Calendar.JANUARY, 1).getTime();
			Date edate = new GregorianCalendar(year , Calendar.DECEMBER, 30).getTime();
			List<OperationES> listop = usermetierimpl.getListOpESForJournal(cur.getDirection(), sdate, edate);
			List<Object[]> listobjectForLivre = new ArrayList<Object[]>();
			for (OperationES op : listop) {
				Object[] row = new Object[16];
				if (op instanceof OpEntree) {
					List<Object[]> bydesignation1 = (this.getDesingationByOpEntree(op));
					// operation
					// row[1] = op;
					// numeros operation
					row[1] = op.getNumoperation();
					// date
					row[15] = op.getDate();
					// Désignation sommaire des opérations
					row[2] = "";

					// par nomenclature
					row[4] = new Double(0);
					row[6] = new Double(0);
					row[7] = new Double(0);
					row[8] = new Double(0);
					row[9] = new Double(0);
					row[10] = new Double(0);
					row[11] = new Double(0);
					row[12] = new Double(0);
					row[13] = new Double(0);
					row[14] = new Double(0);

					for (Object[] nom : bydesignation1) {
						List<Object[]> liste = (List<Object[]>) nom[2];
						Nomenclature nomenclcurrent = (Nomenclature) nom[0];
						if (nomenclcurrent.getNomenclature().equals("1")) {
							row[4] = nom[1];
						} else if (nomenclcurrent.getNomenclature().equals("2")) {
							row[7] = nom[1];
						} else if (nomenclcurrent.getNomenclature().equals("3")) {
							row[9] = nom[1];
						} else if (nomenclcurrent.getNomenclature().equals("5")) {
							row[11] = nom[1];
						} else if (nomenclcurrent.getNomenclature().equals("10")) {
							row[13] = nom[1];
						}
						// nomenclature
						row[0] = nom[0];

						// total charge
						// row[4] = nom[1];
						// total decharge
						// row[6] = 0;
						for (Object[] des : liste) {
							row[2] = row[2] + " " + ((Designation) des[0]).getOrigine();
						}
						// row[2] = row[2] + " xx: " + ((Nomenclature)row[0]).getNomenclature();

						/*
						 * for(Object[] des: liste) { Designation d = (Designation)des[0]; //designation
						 * row[0] = d; //operation row[1] = op;
						 * 
						 * //numeros operation row[11] = op.getNumoperation(); //date row[12] =
						 * op.getDate();
						 * 
						 * 
						 * //origine row[2] = d.getOrigine(); //nombre par desingation entree annee X
						 * row[3] = des[1]; //total entree annee X row[4] = d.getPu()*(Long)row[3];
						 * //nombre par desingation sortie row[5] = 0; //total sortie row[6] = 0;
						 * 
						 * //existant X-1 row[7] = 0L; //valeur X-1 row[8] = (Long)row[7] * d.getPu();
						 * 
						 * //restant X row[9] = (Long)row[3] + (Long)row[7]; //valeur restant X
						 * //row[10] = (Double)row[8] + d.getPu()*(Long)row[9]; row[10] =
						 * d.getPu()*(Long)row[9];
						 * 
						 * 
						 * }
						 */
					}
					listobjectForLivre.add(row);
					row = new Object[16];

				} else if (op instanceof OpSortie) {
					Materiel mat = op.getMat();
					row[0] = mat.getDesign();
					// row[1] = op;

					// numeros operation
					row[1] = op.getNumoperation();
					// date
					row[15] = op.getDate();

					// origine
					if (((OpSortie) op).getMotifsortie() != null) {
						row[2] = ((OpSortie) op).getMotifsortie().getDesignation();
					}

					// par nomenclature
					row[4] = new Double(0);
					row[6] = new Double(0);
					row[7] = new Double(0);
					row[8] = new Double(0);
					row[9] = new Double(0);
					row[10] = new Double(0);
					row[11] = new Double(0);
					row[12] = new Double(0);
					row[13] = new Double(0);
					row[14] = new Double(0);

					// total sortie
					Nomenclature nomenclcurrent = mat.getDesign().getNomenMat();
					if (nomenclcurrent.getNomenclature().equals("1")) {
						row[6] = mat.getDesign().getPu();
					} else if (nomenclcurrent.getNomenclature().equals("2")) {
						row[8] = mat.getDesign().getPu();
					} else if (nomenclcurrent.getNomenclature().equals("3")) {
						row[10] = mat.getDesign().getPu();
					} else if (nomenclcurrent.getNomenclature().equals("5")) {
						row[12] = mat.getDesign().getPu();
					} else if (nomenclcurrent.getNomenclature().equals("10")) {
						row[14] = mat.getDesign().getPu();
					}

					/*
					 * //row[6] = mat.getDesign().getPu()*(Long)row[5];
					 * 
					 * //existant X-1 row[7] = 1L; //valeur X-1 row[8] = mat.getDesign().getPu() *
					 * (Long) row[7];
					 * 
					 * //restant X row[9] = (Long)row[7] - (Long)row[5]; //valeur restant X-X
					 * row[10] = (Long) row[9] * mat.getDesign().getPu();
					 * 
					 */

					listobjectForLivre.add(row);
					row = new Object[16];
				}
			}

			/*
			 * //group by designation List<Object[]> resultstableGrouped = new
			 * ArrayList<Object[]>();
			 * 
			 * Map<Long, List<Object[]>> map = new HashMap<Long, List<Object[]>>();
			 * 
			 * for (Object[] o : listobjectForLivre) { Long key =
			 * ((Designation)o[0]).getIdDesignation(); if(map.containsKey(key)){
			 * List<Object[]> list = map.get(key); list.add(o);
			 * 
			 * }else{ List<Object[]> list = new ArrayList<Object[]>(); list.add(o);
			 * map.put(key, list); }
			 * 
			 * } for (Map.Entry<Long, List<Object[]>> entry : map.entrySet()) {
			 * System.out.println(" gp by livre"); System.out.println(entry.getKey() + ":" +
			 * entry.getValue().size()); Object[] row = new Object[12]; Designation des =
			 * (Designation)(entry.getValue().get(0))[0]; List<Object[]> infos =
			 * entry.getValue(); //Operations List<OperationES> opes = new
			 * ArrayList<OperationES>(); for(Object[] o: infos) {
			 * opes.add((OperationES)(o[1])); }
			 * 
			 * //Designation row[0] = des; //Numéros des operations row[1] = "";
			 * for(OperationES op:opes) { row[1] = row[1]+op.getNumoperation()+" "; }
			 * //origine row[2] = ""; for(OperationES op:opes) { if(op instanceof OpEntree)
			 * { row[2] = row[2] + des.getOrigine() + " "; } else if(op instanceof OpSortie)
			 * { row[2] = row[2] + ((OpSortie) op).getMotifsortie().getDesignation() + " ";
			 * } }
			 * 
			 * ///nombre par desingation entree annee X row[3] = 0L; for(Object[] o: infos)
			 * { if((OperationES)(o[1]) instanceof OpEntree) { row[3] = (Long)o[3]; } }
			 * //total entree annee X row[4] = des.getPu()*(Long)row[3];
			 * 
			 * //nombre par desingation sortie row[5] = 0L; for(Object[] o: infos) {
			 * if((OperationES)(o[1]) instanceof OpSortie) { row[5] = (Long)row[5]+1L; } }
			 * 
			 * //total sortie row[6] = des.getPu()*(Long)row[5];
			 * 
			 * //existant X-1 MBOLA TSY MMETY row[7] = 0L; for(Object[] o: infos) {
			 * if((OperationES)(o[1]) instanceof OpSortie) { row[7] = 1L; } } //valeur X-1
			 * row[8] = des.getPu() * (Long) row[7];
			 * 
			 * //restant X row[9] = (Long)row[7] + (Long)row[3] - (Long)row[5]; //valeur
			 * restant X-X row[10] = (Long) row[9] * des.getPu();
			 * 
			 * //date row[11] = ""; for(OperationES op:opes) { row[11] = row[11] +
			 * op.getDate().toString() + " "; }
			 * 
			 * 
			 * resultstableGrouped.add(row); }
			 */
			// set result GP By
			listESForGrandLivreOld = listobjectForLivre;
		}
		return listESForGrandLivreOld;
	}

	public List<Object[]> getListInventaire(int year) {

		Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		// int year = calendar.get(Calendar.YEAR);
		Date sdate = new GregorianCalendar(year, Calendar.JANUARY, 1).getTime();
		Date edate = new GregorianCalendar(year, Calendar.DECEMBER, 31).getTime();
		List<OperationES> listop = usermetierimpl.getListOpESForJournal(cur.getDirection(), sdate, edate);
		List<Object[]> listobjectForLivre = new ArrayList<Object[]>();
		for (OperationES op : listop) {
			Object[] row = new Object[16];
			if (op instanceof OpEntree) {
				List<Object[]> bydesignation1 = (this.getDesingationByOpEntree(op));
				// operation
				// row[1] = op;
				// numeros operation
				row[1] = op.getNumoperation();
				// date
				row[15] = op.getDate();
				// Désignation sommaire des opérations
				row[2] = "";

				// par nomenclature
				row[4] = new Double(0);
				row[6] = new Double(0);
				row[7] = new Double(0);
				row[8] = new Double(0);
				row[9] = new Double(0);
				row[10] = new Double(0);
				row[11] = new Double(0);
				row[12] = new Double(0);
				row[13] = new Double(0);
				row[14] = new Double(0);

				for (Object[] nom : bydesignation1) {
					List<Object[]> liste = (List<Object[]>) nom[2];
					Nomenclature nomenclcurrent = (Nomenclature) nom[0];
					if (nomenclcurrent.getNomenclature().equals("1")) {
						row[4] = nom[1];
					} else if (nomenclcurrent.getNomenclature().equals("2")) {
						row[7] = nom[1];
					} else if (nomenclcurrent.getNomenclature().equals("3")) {
						row[9] = nom[1];
					} else if (nomenclcurrent.getNomenclature().equals("5")) {
						row[11] = nom[1];
					} else if (nomenclcurrent.getNomenclature().equals("10")) {
						row[13] = nom[1];
					}
					// nomenclature
					row[0] = nom[0];
					for (Object[] des : liste) {
						row[2] = row[2] + " " + ((Designation) des[0]).getOrigine();
					}

				}
				listobjectForLivre.add(row);
				row = new Object[16];

			} else if (op instanceof OpSortie) {
				Materiel mat = op.getMat();
				row[0] = mat.getDesign();
				// row[1] = op;

				// numeros operation
				row[1] = op.getNumoperation();
				// date
				row[15] = op.getDate();

				// origine
				row[2] = ((OpSortie) op).getMotifsortie().getDesignation();

				// par nomenclature
				row[4] = new Double(0);
				row[6] = new Double(0);
				row[7] = new Double(0);
				row[8] = new Double(0);
				row[9] = new Double(0);
				row[10] = new Double(0);
				row[11] = new Double(0);
				row[12] = new Double(0);
				row[13] = new Double(0);
				row[14] = new Double(0);

				// total sortie
				Nomenclature nomenclcurrent = mat.getDesign().getNomenMat();
				if (nomenclcurrent.getNomenclature().equals("1")) {
					row[6] = mat.getDesign().getPu();
				} else if (nomenclcurrent.getNomenclature().equals("2")) {
					row[8] = mat.getDesign().getPu();
				} else if (nomenclcurrent.getNomenclature().equals("3")) {
					row[10] = mat.getDesign().getPu();
				} else if (nomenclcurrent.getNomenclature().equals("5")) {
					row[12] = mat.getDesign().getPu();
				} else if (nomenclcurrent.getNomenclature().equals("10")) {
					row[14] = mat.getDesign().getPu();
				}

				listobjectForLivre.add(row);
				row = new Object[16];
			}
		}

		return listobjectForLivre;
	}

	public List<Object[]> getListESForGrandLivre() {
		if (listESForGrandLivre == null) {
			DateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.FRANCE);
			Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
			Date date = new Date();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			int year = calendar.get(Calendar.YEAR);
			Date sdate = new GregorianCalendar(year, Calendar.JANUARY, 1).getTime();
			Date edate = new GregorianCalendar(year, Calendar.DECEMBER, 31).getTime();
			System.out.println("RRRRRRRRRRR Begin:");
			List<Object[]> r = usermetierimpl.getListObjectForinvetaire(cur.getDirection(), sdate, edate);
			System.out.println("RRRRRRRRRRR Ending:");
			/*
			 * for(Object[] o:r) { System.out.println(String.valueOf(o[0]));
			 * System.out.println(String.valueOf(o[1])); }
			 */
			List<Object[]> resultstable = new ArrayList<Object[]>();

			for (Object[] m : r) {
				Object[] row = new Object[15];
				Materiel mat = (Materiel) m[1];
				OpSortie o = (OpSortie) m[0];
				// Nomenclature
				row[0] = mat.getDesign().getNomenMat().getNomenclature();
				// Numéros du folio du grand livre
				row[1] = mat.getIdMateriel();
				// Désignation du matériel
				String marque = "Inconnue";
				if (mat.getDesign().getMarque() != null) {
					marque = mat.getDesign().getMarque().getDesignation();
				}
				String rens = "";
				if (mat.getDesign().getRenseignement() != null) {
					rens = mat.getDesign().getRenseignement();
				}
				row[2] = mat.getDesign().getTypematerieladd().getDesignation() + " - " + marque + " - " + rens + " - "
				// + mat.getNumSerie()
				;
				// Espèce des unités
				row[3] = mat.getDesign().getEspeceUnite();
				// Prix de l’unité
				row[4] = mat.getDesign().getPu();
				// Existantes au 1er Janvier X
				row[5] = 0;
				// Sortie du matériel
				row[14] = null;
				// Entrées pendant l’année X
				if (mat.getMyoperationEntree() != null && mat.getMyoperationEntree().getDate().compareTo(edate) >= 0) {
					System.out.println("mat tsy misy" + mat.getReference());
					continue;
				}
				if (mat.getMyoperationEntree() == null || mat.getMyoperationEntree().getDate().compareTo(sdate) < 0) {
					row[6] = "Matériel Existant";
					row[5] = 1;
				} else {
					row[6] = mat.getMyoperationEntree().getNumoperation();
				}

				// Sortie pendant l’année X
				if (o == null) {
					row[7] = "Aucune sortie";
				} else {
					row[7] = o.getNumoperation();
					row[14] = o;
				}
				// Reste au 31 déc. X
				row[8] = "reste";
				// Décompte
				row[9] = "decompte";
				row[10] = mat.getDesign().getTypematerieladd();
				row[11] = mat.getDesign();
				row[12] = mat;
				if (o != null) {
					row[13] = df.format(o.getDate());// set année sortie for affichage
				}

				resultstable.add(row);
			}

			// group by designation
			List<Object[]> resultstableGrouped = new ArrayList<Object[]>();

			Map<Designation, List<Object[]>> map = new HashMap<Designation, List<Object[]>>();

			for (Object[] o : resultstable) {
				Designation key = (Designation) o[11];
				if (map.containsKey(key)) {
					List<Object[]> list = map.get(key);
					list.add(o);

				} else {
					List<Object[]> list = new ArrayList<Object[]>();
					list.add(o);
					map.put(key, list);
				}

			}
			for (Map.Entry<Designation, List<Object[]>> entry : map.entrySet()) {
				// System.out.println(entry.getKey().getIdDesignation() + ":" +
				// entry.getValue().size());
				Object[] row = new Object[18];
				Designation des = entry.getKey();
				List<Object[]> infos = entry.getValue();
				// materiels
				List<Materiel> materiels = new ArrayList<Materiel>();
				for (Object[] o : infos) {
					materiels.add((Materiel) ((infos.get(0))[12]));
				}

				// Nomenclature
				row[0] = des.getNomenMat().getNomenclature();
				// Espèces des unités
				row[1] = des.getEspeceUnite();
				// Désignation des objets (1)
				row[2] = (infos.get(0))[2];
				;
				String series = "";
				for (Object[] o : infos) {
					series = series + " / " + ((Materiel) (o[12])).getNumSerie();
				}
				row[2] = row[2] + series;
				// Prix de l’unité
				row[3] = des.getPu();
				;
				// Prix de l’unité
				row[4] = des.getPu();
				// Existantes au 1er Janvier X
				row[5] = 0;
				// Entrées pendant l’année X
				row[6] = "";
				// date des entrées et des sorties
				row[14] = ""; // entrée
				row[15] = ""; // sorties
				// origine des entrées et motifs des sorties
				row[16] = "";
				row[17] = "";

				int entreeAx = 0;// entree pendant année X
				if (materiels.get(0).getMyoperationEntree() == null
						|| materiels.get(0).getMyoperationEntree().getDate().compareTo(sdate) < 0) {
					String es = "  ";
					if (materiels.get(0).getMyoperationEntree() != null
							&& materiels.get(0).getMyoperationEntree().getDate().compareTo(sdate) < 0) {
						es = "du " + df.format(materiels.get(0).getMyoperationEntree().getDate());
					}
					row[6] = "Matériel Existant  " + es;
					row[5] = materiels.size();
					entreeAx = 0;
					// set origine des entrées pour le premier matériel
					row[16] = materiels.get(0).getDesign().getOrigine();

				} else {
					row[6] = materiels.get(0).getMyoperationEntree().getNumoperation();
					entreeAx = materiels.size();
					row[14] = materiels.get(0).getMyoperationEntree().getDate();
					row[16] = row[16] + materiels.get(0).getDesign().getRefFacture() + " - "
							+ materiels.get(0).getDesign().getOrigine();
				}

				// Sortie pendant l’année X
				String sortie = "";
				String sortieAnnee = "xxxx";
				String sortieMotif = "";
				int sortieAx = 0;
				for (Object[] o : infos) {
					if (!o[7].equals("Aucune sortie")) {
						sortie = sortie + " - " + (String) o[7];
						sortieAx = sortieAx + 1;
						sortieAnnee = sortieAnnee + " , " + (String) o[13];
						OpSortie sortieoperation = (OpSortie) o[14];
						if (sortieoperation.getMotifsortie() != null) {
							sortieMotif = sortieoperation.getMotifsortie().getDesignation();
						}
						if (sortieoperation.getDirec() != null) {
							sortieMotif = sortieMotif + " vers " + sortieoperation.getDirec().getCodeDirection();
						}
						row[17] = row[17] + sortieMotif; // motifs des sorties
					}

				}
				row[15] = sortieAnnee; // set annees sorties
				row[7] = sortie;

				/*
				 * if(o ==null) { row[7] = "Aucune sortie"; } else { row[7] =
				 * o.getNumoperation(); }
				 */
				// Reste au 31 déc. X
				row[8] = (Integer) row[5] + entreeAx - sortieAx; // existant + entree en X - sortie en X
				// Décompte
				row[9] = (Integer) row[8] * des.getPu();
				row[10] = des.getTypematerieladd();
				row[11] = des;

				// nombre entree et sortie pour affichage
				row[12] = entreeAx;
				row[13] = sortieAx;

				resultstableGrouped.add(row);
			}

			listESForGrandLivre = resultstableGrouped;
		}
		return listESForGrandLivre;
	}

	public List<Object[]> getListESForGrandLivre(Date start, Date fin, Direction dir) {

		if (listESForGrandLivre == null) {
			DateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.FRANCE);
			if (dir == null) {
				Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
				dir = cur.getDirection();
			}

			Date sdate = start;
			Date edate = fin;
			System.out.println("RRRRRRRRRRR Begin:");
			List<Object[]> r = usermetierimpl.getListObjectForinvetaire(dir, sdate, edate);
			System.out.println("RRRRRRRRRRR Ending:");
			/*
			 * for(Object[] o:r) { System.out.println(String.valueOf(o[0]));
			 * System.out.println(String.valueOf(o[1])); }
			 */
			List<Object[]> resultstable = new ArrayList<Object[]>();

			for (Object[] m : r) {
				Object[] row = new Object[15];
				Materiel mat = (Materiel) m[1];
				OpSortie o = (OpSortie) m[0];
				// Nomenclature
				row[0] = mat.getDesign().getNomenMat().getNomenclature();
				// Numéros du folio du grand livre
				row[1] = mat.getIdMateriel();
				// Désignation du matériel
				String rens = "";
				if (mat.getDesign().getRenseignement() != null) {
					rens = mat.getDesign().getRenseignement();
				}
				String mar = "";
				if (mat.getDesign().getMarque() != null) {
					mar = mat.getDesign().getMarque().getDesignation();
				}
				row[2] = mat.getDesign().getTypematerieladd().getDesignation() + " - " + mar + " - " + rens

				// + mat.getNumSerie()
				;
				// Espèce des unités
				row[3] = mat.getDesign().getEspeceUnite();
				// Prix de l’unité
				row[4] = mat.getDesign().getPu();
				// Existantes au 1er Janvier X
				row[5] = 0;
				// Sortie du matériel
				row[14] = null;
				// Entrées pendant l’année X
				if (mat.getMyoperationEntree() != null && mat.getMyoperationEntree().getDate().compareTo(edate) >= 0) {
					System.out.println("mat tsy misy" + mat.getReference());
					continue;
				}
				if (mat.getMyoperationEntree() == null || mat.getMyoperationEntree().getDate().compareTo(sdate) < 0) {
					row[6] = "Matériel Existant";
					row[5] = 1;
				} else {
					row[6] = mat.getMyoperationEntree().getNumoperation();
				}

				// Sortie pendant l’année X
				if (o == null) {
					row[7] = "Aucune sortie";
				} else {
					row[7] = o.getNumoperation();
					row[14] = o;
				}
				// Reste au 31 déc. X
				row[8] = "reste";
				// Décompte
				row[9] = "decompte";
				row[10] = mat.getDesign().getTypematerieladd();
				row[11] = mat.getDesign();
				row[12] = mat;
				if (o != null) {
					row[13] = df.format(o.getDate());// set année sortie for affichage
				}

				resultstable.add(row);
			}

			// group by designation
			List<Object[]> resultstableGrouped = new ArrayList<Object[]>();

			Map<Designation, List<Object[]>> map = new HashMap<Designation, List<Object[]>>();

			for (Object[] o : resultstable) {
				Designation key = (Designation) o[11];
				if (map.containsKey(key)) {
					List<Object[]> list = map.get(key);
					list.add(o);

				} else {
					List<Object[]> list = new ArrayList<Object[]>();
					list.add(o);
					map.put(key, list);
				}

			}
			for (Map.Entry<Designation, List<Object[]>> entry : map.entrySet()) {
				// System.out.println(entry.getKey().getIdDesignation() + ":" +
				// entry.getValue().size());
				Object[] row = new Object[18];
				Designation des = entry.getKey();
				List<Object[]> infos = entry.getValue();
				// materiels
				List<Materiel> materiels = new ArrayList<Materiel>();
				for (Object[] o : infos) {
					materiels.add((Materiel) ((infos.get(0))[12]));
				}

				// Nomenclature
				row[0] = des.getNomenMat().getNomenclature();
				// Espèces des unités
				row[1] = des.getEspeceUnite();
				// Désignation des objets (1)
				row[2] = (infos.get(0))[2];
				;
				String series = "";
				for (Object[] o : infos) {
					series = series + " / " + ((Materiel) (o[12])).getNumSerie();
				}
				row[2] = row[2] + series;
				// Prix de l’unité
				row[3] = des.getPu();
				;
				// Prix de l’unité
				row[4] = des.getPu();
				// Existantes au 1er Janvier X
				row[5] = 0;
				// Entrées pendant l’année X
				row[6] = "";
				// date des entrées et des sorties
				row[14] = ""; // entrée
				row[15] = ""; // sorties
				// origine des entrées et motifs des sorties
				row[16] = "";
				row[17] = "";

				int entreeAx = 0;// entree pendant année X
				if (materiels.get(0).getMyoperationEntree() == null
						|| materiels.get(0).getMyoperationEntree().getDate().compareTo(sdate) < 0) {
					String es = "  ";
					if (materiels.get(0).getMyoperationEntree() != null
							&& materiels.get(0).getMyoperationEntree().getDate().compareTo(sdate) < 0) {
						es = "du " + df.format(materiels.get(0).getMyoperationEntree().getDate());
					}
					row[6] = "Matériel Existant  " + es;
					row[5] = materiels.size();
					entreeAx = 0;
					// set origine des entrées pour le premier matériel
					row[16] = materiels.get(0).getDesign().getOrigine();

				} else {
					row[6] = materiels.get(0).getMyoperationEntree().getNumoperation();
					entreeAx = materiels.size();
					row[14] = materiels.get(0).getMyoperationEntree().getDate();
					row[16] = row[16] + materiels.get(0).getDesign().getRefFacture() + " - "
							+ materiels.get(0).getDesign().getOrigine();
				}

				// Sortie pendant l’année X
				String sortie = "";
				String sortieAnnee = "xxxx";
				String sortieMotif = "";
				int sortieAx = 0;
				for (Object[] o : infos) {
					if (!o[7].equals("Aucune sortie")) {
						sortie = sortie + " - " + (String) o[7];
						sortieAx = sortieAx + 1;
						sortieAnnee = sortieAnnee + " , " + (String) o[13];
						OpSortie sortieoperation = (OpSortie) o[14];
						if (sortieoperation.getMotifsortie() != null) {
							sortieMotif = sortieoperation.getMotifsortie().getDesignation();
						}
						if (sortieoperation.getDirec() != null) {
							sortieMotif = sortieMotif + " vers " + sortieoperation.getDirec().getCodeDirection();
						}
						row[17] = row[17] + sortieMotif; // motifs des sorties
					}

				}
				row[15] = sortieAnnee; // set annees sorties
				row[7] = sortie;

				/*
				 * if(o ==null) { row[7] = "Aucune sortie"; } else { row[7] =
				 * o.getNumoperation(); }
				 */
				// Reste au 31 déc. X
				row[8] = (Integer) row[5] + entreeAx - sortieAx; // existant + entree en X - sortie en X
				// Décompte
				row[9] = (Integer) row[8] * des.getPu();
				row[10] = des.getTypematerieladd();
				row[11] = des;

				// nombre entree et sortie pour affichage
				row[12] = entreeAx;
				row[13] = sortieAx;

				resultstableGrouped.add(row);
			}

			listESForGrandLivre = resultstableGrouped;
		}
		return listESForGrandLivre;
	}

	public void setListESForGrandLivre(List<Object[]> listESForGrandLivre) {
		this.listESForGrandLivre = listESForGrandLivre;
	}

	public void setListESForGrandLivreOld(List<Object[]> listESForGrandLivre) {
		this.listESForGrandLivreOld = listESForGrandLivre;
	}

	public List<Object[]> getDesingationByOpEntree(Operation op) {
		System.out.println("FIRST CALL");
		List<Object[]> results = usermetierimpl.listDesignationByOperationEntree((OpEntree) op);
		Map<Nomenclature, Double> bynom = new ConcurrentHashMap<Nomenclature, Double>();
		Iterator<Map.Entry<Nomenclature, Double>>  it;
		try {
		// disable concurrent 
		//synchronized (bynom) {
		Iterator<Object[]> resIt = results.iterator();
		while(resIt.hasNext()){

		//for(int i = 0; i<results.size(); i++) {
		//for (Object[] o : results) {
			//Object[] o = results.get(i);
			Object[] o = resIt.next();
			Designation a = (Designation) (o[0]);
			Long nbr = (Long) (o[1]);
			if (bynom.isEmpty()) {
				bynom.put(a.getNomenMat(), nbr * a.getPu());
				System.out.println(a.getNomenMat()+ " initialize "+bynom.get(a.getNomenMat()));
			} else {
				//it = bynom.entrySet().iterator();
				//Map.Entry<Nomenclature, Double>  entry;
				if(bynom.containsKey(a.getNomenMat())) {
					Double curentvalue = bynom.get(a.getNomenMat())+ (nbr * a.getPu());
					bynom.replace(a.getNomenMat(), curentvalue);
					System.out.println(a.getNomenMat()+ " has current value "+bynom.get(a.getNomenMat()));
				}
				else {
					bynom.put(a.getNomenMat(), nbr * a.getPu());
					System.out.println(a.getNomenMat()+ " as "+bynom.get(a.getNomenMat()));
				}
				/*while (it.hasNext())
				{
					 entry = it.next();
					 System.out.println(a.getNomenMat()+ " has  value "+bynom.get(a.getNomenMat()));
					if (entry.getKey() == a.getNomenMat() && entry.getValue()!=null) {
						Double curentvalue = entry.getValue()+ (nbr * a.getPu());
						bynom.replace(a.getNomenMat(), curentvalue);
						System.out.println(a.getNomenMat()+ " has current value "+bynom.get(a.getNomenMat()));
						//entry.setValue(curentvalue);
					} else {
						bynom.put(a.getNomenMat(), nbr * a.getPu());
						System.out.println(a.getNomenMat()+ " as "+bynom.get(a.getNomenMat()));
					}
				}*/
			}
			
			
		}
		//}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		List<Object[]>resultatfinal = new CopyOnWriteArrayList<Object[]>();
		
		
		for (Map.Entry<Nomenclature, Double> entry : bynom.entrySet()) {
		//while (it.hasNext())
		//{
			//entry = it.next();
			Object[] item = new Object[3];
			System.out.println(entry.getKey().getNomenclature()+ " has current valuesss "+entry.getValue());
			item[0] = entry.getKey();
			item[1] = entry.getValue();
			List<Object[]> ourlist = new ArrayList<Object[]>();
			for(Object[]o:results) {
				Designation a = (Designation) o[0];
				if(a.getNomenMat() == entry.getKey()) {
					ourlist.add(o);
				}
				//System.out.println("Its ok!!");
			}
			item[2] = ourlist;
			resultatfinal.add(item);
		}
		// */
		return resultatfinal;
		
	}

	public List<Object[]> getListobjectForInvetaire() {
		// if (listobjectForInvetaire == null) {
		Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		Date sdate = new GregorianCalendar(year, Calendar.JANUARY, 1).getTime();
		Date edate = new GregorianCalendar(year, Calendar.DECEMBER, 31).getTime();
		System.out.println("RRRRRRRRRRR Begin:");
		List<Object[]> r = usermetierimpl.getListObjectForinvetaire(cur.getDirection(), sdate, edate);
		System.out.println("RRRRRRRRRRR Ending:");
		/*
		 * for(Object[] o:r) { System.out.println(String.valueOf(o[0]));
		 * System.out.println(String.valueOf(o[1])); }
		 */
		List<Object[]> resultstable = new ArrayList<Object[]>();

		for (Object[] m : r) {
			Object[] row = new Object[13];
			Materiel mat = (Materiel) m[1];
			OpSortie o = (OpSortie) m[0];

			if (mat.getMyoperationEntree() != null && mat.getMyoperationEntree().getDate().compareTo(edate) >= 0) {
				System.out.println("mat tsy misy" + mat.getReference());
				continue;
			}
			// Nomenclature
			row[0] = mat.getDesign().getNomenMat().getNomenclature();
			// Numéros du folio du grand livre
			row[1] = mat.getIdMateriel();
			// Désignation du matériel
			String marque = "Inconnue";
			if (mat.getDesign().getMarque() != null) {
				marque = mat.getDesign().getMarque().getDesignation();
			}
			String rens = "";
			if (mat.getDesign().getRenseignement() != null) {
				rens = mat.getDesign().getRenseignement();
			}
			row[2] = mat.getDesign().getTypematerieladd().getDesignation() + " - " + marque + " - " + rens
			// + mat.getNumSerie()
			;
			// Espèce des unités
			row[3] = mat.getDesign().getEspeceUnite();
			// Prix de l’unité
			row[4] = mat.getDesign().getPu();
			// Existantes au 1er Janvier X
			row[5] = 0;
			// Entrées pendant l’année X
			if (mat.getMyoperationEntree() == null || mat.getMyoperationEntree().getDate().compareTo(sdate) <= 0) {
				row[6] = "Matériel Existant";
				row[5] = 1;
			} else {
				row[6] = mat.getMyoperationEntree().getNumoperation();
			}

			// Sortie pendant l’année X
			if (o == null) {
				row[7] = "Aucune sortie";
			} else {
				if (o.getDate() != null && o.getDate().compareTo(edate) >= 0) {
					row[7] = "Aucune sortie";
				} else {
					row[7] = o.getNumoperation();
				}
			}
			// Reste au 31 déc. X
			row[8] = "reste";
			// Décompte
			row[9] = "decompte";
			row[10] = mat.getDesign().getTypematerieladd();
			row[11] = mat.getDesign();
			row[12] = mat;

			resultstable.add(row);
		}

		// group by designation
		List<Object[]> resultstableGrouped = new ArrayList<Object[]>();

		Map<Designation, List<Object[]>> map = new HashMap<Designation, List<Object[]>>();

		for (Object[] o : resultstable) {
			Designation key = (Designation) o[11];
			if (map.containsKey(key)) {
				List<Object[]> list = map.get(key);
				list.add(o);

			} else {
				List<Object[]> list = new ArrayList<Object[]>();
				list.add(o);
				map.put(key, list);
			}

		}
		for (Map.Entry<Designation, List<Object[]>> entry : map.entrySet()) {
			// System.out.println(entry.getKey().getIdDesignation() + ":" +
			// entry.getValue().size());
			Object[] row = new Object[12];
			Designation des = entry.getKey();
			List<Object[]> infos = entry.getValue();
			// materiels
			List<Materiel> materiels = new ArrayList<Materiel>();
			for (Object[] o : infos) {
				materiels.add((Materiel) ((infos.get(0))[12]));
			}

			// Nomenclature
			row[0] = des.getNomenMat().getNomenclature();
			// Numéros du folio du grand livre
			row[1] = des.getIdDesignation();
			// Désignation du matériel
			row[2] = (infos.get(0))[2];
			;
			String series = "";
			for (Object[] o : infos) {
				series = series + " / " + ((Materiel) (o[12])).getNumSerie();
			}
			row[2] = row[2] + series;
			// Espèce des unités
			row[3] = des.getEspeceUnite();
			// Prix de l’unité
			row[4] = des.getPu();
			// Existantes au 1er Janvier X
			row[5] = 0;
			// Entrées pendant l’année X
			int entreeAx = 0;// entree pendant année X
			if (materiels.get(0).getMyoperationEntree() == null
					|| materiels.get(0).getMyoperationEntree().getDate().compareTo(sdate) < 0) {
				String es = "old";
				if (materiels.get(0).getMyoperationEntree() != null
						&& materiels.get(0).getMyoperationEntree().getDate().compareTo(sdate) < 0) {
					es = materiels.get(0).getMyoperationEntree().getDate().toString();
				}

				row[6] = "Matériel Existant " + es;
				row[5] = materiels.size();
				entreeAx = 0;

			} else {
				row[6] = materiels.get(0).getMyoperationEntree().getNumoperation();
				entreeAx = materiels.size();
			}

			// Sortie pendant l’année X
			String sortie = "0";
			int sortieAx = 0;
			for (Object[] o : infos) {
				if (!o[7].equals("Aucune sortie")) {
					sortie = sortie + (String) o[7] + " and ";
					sortieAx = sortieAx + 1;
				}

			}
			row[7] = sortie;
			/*
			 * if(o ==null) { row[7] = "Aucune sortie"; } else { row[7] =
			 * o.getNumoperation(); }
			 */
			// Reste au 31 déc. X
			row[8] = (Integer) row[5] + entreeAx - sortieAx; // existant + entree en X - sortie en X
			// Décompte
			row[9] = (Integer) row[8] * des.getPu();
			row[10] = des.getTypematerieladd();
			row[11] = des;

			// set nombre entree et sortie pendant X
			row[6] = entreeAx;
			row[7] = sortieAx;

			resultstableGrouped.add(row);
		}

		listobjectForInvetaire = resultstableGrouped;
		// }

		return listobjectForInvetaire;
	}

	public List<Object[]> getListobjectForInvetaire(Date s, Date f) {
		// if (listobjectForInvetaire == null) {
		Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
		Date sdate = s;
		Date edate = f;
		System.out.println("RRRRRRRRRRR Begin:");
		List<Object[]> r = usermetierimpl.getListObjectForinvetaire(cur.getDirection(), sdate, edate);
		System.out.println("RRRRRRRRRRR Ending:");
		/*
		 * for(Object[] o:r) { System.out.println(String.valueOf(o[0]));
		 * System.out.println(String.valueOf(o[1])); }
		 */
		List<Object[]> resultstable = new ArrayList<Object[]>();

		for (Object[] m : r) {
			Object[] row = new Object[13];
			Materiel mat = (Materiel) m[1];
			OpSortie o = (OpSortie) m[0];

			if (mat.getMyoperationEntree() != null && mat.getMyoperationEntree().getDate().compareTo(edate) >= 0) {
				System.out.println("mat tsy misy" + mat.getReference());
				continue;
			}
			// Nomenclature
			row[0] = mat.getDesign().getNomenMat().getNomenclature();
			// Numéros du folio du grand livre
			row[1] = mat.getIdMateriel();
			// Désignation du matériel
			String marqueMat = "Inconnue";
			if (mat.getDesign().getMarque() != null) {
				marqueMat = mat.getDesign().getMarque().getDesignation();
			}
			String rens = "";
			if (mat.getDesign().getRenseignement() != null) {
				rens = mat.getDesign().getRenseignement();
			}
			row[2] = mat.getDesign().getTypematerieladd().getDesignation() + " - " + marqueMat + " - " + rens
			// + mat.getNumSerie()
			;
			// Espèce des unités
			row[3] = mat.getDesign().getEspeceUnite();
			// Prix de l’unité
			row[4] = mat.getDesign().getPu();
			// Existantes au 1er Janvier X
			row[5] = 0;
			// Entrées pendant l’année X
			if (mat.getMyoperationEntree() == null || mat.getMyoperationEntree().getDate().compareTo(sdate) <= 0) {
				row[6] = "Matériel Existant";
				row[5] = 1;
			} else {
				row[6] = mat.getMyoperationEntree().getNumoperation();
			}

			// Sortie pendant l’année X
			if (o == null) {
				row[7] = "Aucune sortie";
			} else {
				if (o.getDate() != null && o.getDate().compareTo(edate) >= 0) {
					row[7] = "Aucune sortie";
				} else {
					row[7] = o.getNumoperation();
				}
			}
			// Reste au 31 déc. X
			row[8] = "reste";
			// Décompte
			row[9] = "decompte";
			row[10] = mat.getDesign().getTypematerieladd();
			row[11] = mat.getDesign();
			row[12] = mat;

			resultstable.add(row);
		}

		// group by designation
		List<Object[]> resultstableGrouped = new ArrayList<Object[]>();

		Map<Designation, List<Object[]>> map = new HashMap<Designation, List<Object[]>>();

		for (Object[] o : resultstable) {
			Designation key = (Designation) o[11];
			if (map.containsKey(key)) {
				List<Object[]> list = map.get(key);
				list.add(o);

			} else {
				List<Object[]> list = new ArrayList<Object[]>();
				list.add(o);
				map.put(key, list);
			}

		}
		for (Map.Entry<Designation, List<Object[]>> entry : map.entrySet()) {
			// System.out.println(entry.getKey().getIdDesignation() + ":" +
			// entry.getValue().size());
			Object[] row = new Object[12];
			Designation des = entry.getKey();
			List<Object[]> infos = entry.getValue();
			// materiels
			List<Materiel> materiels = new ArrayList<Materiel>();
			for (Object[] o : infos) {
				materiels.add((Materiel) ((infos.get(0))[12]));
			}

			// Nomenclature
			row[0] = des.getNomenMat().getNomenclature();
			// Numéros du folio du grand livre
			row[1] = des.getIdDesignation();
			// Désignation du matériel
			row[2] = (infos.get(0))[2];
			;
			String series = "";
			for (Object[] o : infos) {
				series = series + " / " + ((Materiel) (o[12])).getNumSerie();
			}
			row[2] = row[2] + series;
			// Espèce des unités
			row[3] = des.getEspeceUnite();
			// Prix de l’unité
			row[4] = des.getPu();
			// Existantes au 1er Janvier X
			row[5] = 0;
			// Entrées pendant l’année X
			int entreeAx = 0;// entree pendant année X
			if (materiels.get(0).getMyoperationEntree() == null
					|| materiels.get(0).getMyoperationEntree().getDate().compareTo(sdate) < 0) {
				String es = "old";
				if (materiels.get(0).getMyoperationEntree() != null
						&& materiels.get(0).getMyoperationEntree().getDate().compareTo(sdate) < 0) {
					es = materiels.get(0).getMyoperationEntree().getDate().toString();
				}

				row[6] = "Matériel Existant " + es;
				row[5] = materiels.size();
				entreeAx = 0;

			} else {
				row[6] = materiels.get(0).getMyoperationEntree().getNumoperation();
				entreeAx = materiels.size();
			}

			// Sortie pendant l’année X
			String sortie = "0";
			int sortieAx = 0;
			for (Object[] o : infos) {
				if (!o[7].equals("Aucune sortie")) {
					sortie = sortie + (String) o[7] + " and ";
					sortieAx = sortieAx + 1;
				}

			}
			row[7] = sortie;
			/*
			 * if(o ==null) { row[7] = "Aucune sortie"; } else { row[7] =
			 * o.getNumoperation(); }
			 */
			// Reste au 31 déc. X
			row[8] = (Integer) row[5] + entreeAx - sortieAx; // existant + entree en X - sortie en X
			// Décompte
			row[9] = (Integer) row[8] * des.getPu();
			row[10] = des.getTypematerieladd();
			row[11] = des;

			// set nombre entree et sortie pendant X
			row[6] = entreeAx;
			row[7] = sortieAx;

			resultstableGrouped.add(row);
		}
		// }

		return resultstableGrouped;
	}

	// List object format for fiche de stock
	public List<Object[]> getListForFicheStock() {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		Date sdate = new GregorianCalendar(year - 2, Calendar.JANUARY, 1).getTime();
		Date edate = new GregorianCalendar(year + 1, Calendar.DECEMBER, 30).getTime();
		List<Operation> lesoperations = getListOpESArtByDirection(null, sdate, edate);
		Collections.sort(lesoperations, new Comparator<Operation>() {
			public int compare(Operation o1, Operation o2) {
				Long id1 = o1.getId();
				Long id2 = o2.getId();
				// System.out.println("date :" + d1);
				return id1.compareTo(id2);
			}
		});

		// structure de données
		List<Object[]> resulttable = new ArrayList<Object[]>();
		Object[] row = new Object[8];
		for (Operation o : lesoperations) {
			// Date operation
			row[0] = null;
			// reference entrée
			row[1] = "";
			// quantite entrée
			row[2] = 0;
			// quantite entrée cumulée
			row[3] = 0;
			// reference sortie
			row[4] = "";
			// quantite sortie
			row[5] = 0;
			// quantite finale
			row[6] = new Long(0);

			// Remplissage à partir du liste des operations

			// initiale
			row[0] = o.getDate();

			// entree
			if (o instanceof OpEntreeArticle) {
				row[1] = o.getId(); // need to add this attribut for operation reference
				row[2] = (Long) (((OpEntreeArticle) o).getArticle().getNombre());
				row[3] = (Long) row[2] + 0; // need to set previous nombre
			}
			// sortie
			else if (o instanceof OpSortieArticle) {
				row[4] = o.getId();
				row[5] = (Long) (((OpSortieArticle) o).getNombreToS());

			}
			// finale quantity
			// row[6] = (Long)row[3] - (Long)row[5];
			resulttable.add(row);
			row = new Object[8];
		}

		Collections.sort(resulttable, new Comparator<Object[]>() {
			public int compare(Object[] s1, Object[] s2) {
				Date d1 = (Date) s1[0];
				Date d2 = (Date) s2[0];
				System.out.println("date :" + d1);
				return d1.compareTo(d2);
			}
		});

		return resulttable;
	}

	// list objet format pour journal
	public List<Object[]> getListForJournalStockByDir(Direction d, Date fdate) {
		// if date not set
		Date date = new Date();
		if (fdate != null) {
			date = fdate;
			System.out.println(" fdate not null");
		} else {
			System.out.println(" fdate  null");
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		Date sdate = new GregorianCalendar(year, Calendar.JANUARY, 1).getTime();
		Date edate = new GregorianCalendar(year, Calendar.DECEMBER, 31).getTime();
		// end date set
		List<Operation> lesoperations = getListOpESArtByDirection(d, sdate, edate);
		Collections.sort(lesoperations, new Comparator<Operation>() {
			public int compare(Operation o1, Operation o2) {
				Long id1 = o1.getId();
				Long id2 = o2.getId();
				// System.out.println("date :" + d1);
				return id1.compareTo(id2);
			}
		});
		/*
		 * for(Operation o:lesoperations) { if(o instanceof OpEntreeArticle) {
		 * if(((OpEntreeArticle)o).getArticle() instanceof ArticleEx) {
		 * lesoperations.remove(o); } } }
		 */
		// structure de données
		List<Object[]> resulttable = new ArrayList<Object[]>();
		Long i = 1L;
		Object[] row = new Object[9];
		for (Operation o : lesoperations) {
			// id
			row[8] = "" + o.getId().toString();
			// numero d'ordre
			row[0] = i;
			// date operation
			row[1] = o.getDate();
			// reference
			row[2] = " a ajouter " + o.getId();
			// origine
			row[3] = "";
			// designation des articles
			row[4] = "";
			// quantite
			row[5] = new Long(0);
			// prix unitaire
			row[6] = new Double(0);
			// Montant total
			row[7] = new Double(0);

			// processing
			if (o instanceof OpEntreeArticle) {
				if (((OpEntreeArticle) o).getArticle() instanceof ArticleEx) {
					continue;
				}
				row[0] = row[0] + "/E";
				row[3] = "a ajouter origine";
				Article a = ((OpEntreeArticle) o).getArticle();
				String marqueArt = "";
				if (a.getMarqueArticle() != null) {
					marqueArt = a.getMarqueArticle().getDesignation();
				}
				row[4] = a.getCodeArticle().getDesignation() + marqueArt;
				row[5] = a.getNombre();
				row[6] = a.getPrix();
				row[7] = (Long) row[5] * (Double) row[6];
				// set reference entree
				if (a.getReference() != null) {
					row[2] = a.getReference();
				}
				if (a.getOrigine() != null) {
					row[3] = a.getOrigine();
				}

			} else if (o instanceof OpSortieArticle) {
				row[0] = row[0] + "/S";
				row[3] = (((OpSortieArticle) o).getBeneficiaire()).getNomAgent();
				Article a = ((OpSortieArticle) o).getArticle();
				row[4] = a.getCodeArticle().getDesignation();
				row[5] = ((OpSortieArticle) o).getNombreToS();
				row[6] = a.getPrix();
				row[7] = (Long) row[5] * (Double) row[6];
				if (((OpSortieArticle) o).getDecision() != null) {
					row[2] = ((OpSortieArticle) o).getDecision();
				}

			}

			resulttable.add(row);
			row = new Object[9];
			i = i + 1;
		}
		return resulttable;
	}

	// Used in jouenalabean
	public List<Object[]> ourListForJournalStock(Date fdate) {
		// if date not set
		Date date = new Date();
		if (fdate != null) {
			date = fdate;
			System.out.println(" fdate not null");
		} else {
			System.out.println(" fdate  null");
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		Date sdate = new GregorianCalendar(year, Calendar.JANUARY, 1).getTime();
		Date edate = new GregorianCalendar(year, Calendar.DECEMBER, 31).getTime();
		// end date set
		List<Operation> lesoperations = getListOpESArtByDirection(null, sdate, edate);
		Collections.sort(lesoperations, new Comparator<Operation>() {
			public int compare(Operation o1, Operation o2) {
				Long id1 = o1.getId();
				Long id2 = o2.getId();
				// System.out.println("date :" + d1);
				return id1.compareTo(id2);
			}
		});
		// structure de données
		List<Object[]> resulttable = new ArrayList<Object[]>();
		Long i = 1L;
		Object[] row = new Object[9];
		for (Operation o : lesoperations) {
			// id
			row[8] = "" + o.getId().toString();
			// numero d'ordre
			row[0] = i;
			// date operation
			row[1] = o.getDate();
			// reference
			row[2] = " a ajouter " + o.getId();
			// origine
			row[3] = "";
			// designation des articles
			row[4] = "";
			// quantite
			row[5] = new Long(0);
			// prix unitaire
			row[6] = new Double(0);
			// Montant total
			row[7] = new Double(0);

			// processing
			if (o instanceof OpEntreeArticle) {
				if (((OpEntreeArticle) o).getArticle() instanceof ArticleEx) {
					continue;
				}
				row[0] = row[0] + "/E";
				row[3] = "a ajouter origine";
				Article a = ((OpEntreeArticle) o).getArticle();
				String marqueArt = "";
				if (a.getMarqueArticle() != null) {
					marqueArt = a.getMarqueArticle().getDesignation();
				}
				row[4] = a.getCodeArticle().getDesignation() + marqueArt;
				row[5] = a.getNombre();
				row[6] = a.getPrix();
				row[7] = (Long) row[5] * (Double) row[6];
				// set reference entree
				if (a.getReference() != null) {
					row[2] = a.getReference();
				}
				if (a.getOrigine() != null) {
					row[3] = a.getOrigine();
				}

			} else if (o instanceof OpSortieArticle) {
				row[0] = row[0] + "/S";
				row[3] = (((OpSortieArticle) o).getBeneficiaire()).getNomAgent();
				Article a = ((OpSortieArticle) o).getArticle();
				row[4] = a.getCodeArticle().getDesignation();
				row[5] = a.getNombre();
				row[6] = a.getPrix();
				row[7] = (Long) row[5] * (Double) row[6];
				if (((OpSortieArticle) o).getDecision() != null) {
					row[2] = ((OpSortieArticle) o).getDecision();
				}

			}

			resulttable.add(row);
			row = new Object[9];
			i = i + 1;
		}
		return resulttable;
	}

	public List<Object[]> getListForJournalStock(Date s, Date f) {
		List<Operation> lesoperations = getListOpESArtByDirection(null, s, f);
		// structure de données
		List<Object[]> resulttable = new ArrayList<Object[]>();
		Object[] row = new Object[8];
		for (Operation o : lesoperations) {
			// if((s.compareTo(o.getDate()) <= 0) && (f.compareTo(o.getDate()) >= 0)) {
			// numero d'ordre
			row[0] = "" + o.getId().toString();
			// date operation
			row[1] = o.getDate();
			// reference
			row[2] = " a ajouter " + o.getId();
			// origine
			row[3] = "";
			// designation des articles
			row[4] = "";
			// quantite
			row[5] = new Long(0);
			// prix unitaire
			row[6] = new Double(0);
			// Montant total
			row[7] = new Double(0);

			// processing
			if (o instanceof OpEntreeArticle) {

				row[0] = row[0] + "/E";
				row[3] = "a ajouter origine";
				Article a = ((OpEntreeArticle) o).getArticle();
				row[4] = a.getCodeArticle().getDesignation();
				row[5] = a.getNombre();
				row[6] = a.getPrix();
				row[7] = (Long) row[5] * (Double) row[6];
				// set reference entree
				if (a.getReference() != null) {
					row[2] = a.getReference();
				}
				if (a.getOrigine() != null) {
					row[3] = a.getOrigine();
				}

			} else if (o instanceof OpSortieArticle) {
				row[0] = row[0] + "/S";
				row[3] = (((OpSortieArticle) o).getBeneficiaire()).getNomAgent();
				Article a = ((OpSortieArticle) o).getArticle();
				String marqueArt = "";
				if (a.getMarqueArticle() != null) {
					marqueArt = a.getMarqueArticle().getDesignation();
				}
				row[4] = a.getCodeArticle().getDesignation() + marqueArt;
				row[5] = a.getNombre();
				row[6] = a.getPrix();
				row[7] = (Long) row[5] * (Double) row[6];
				if (((OpSortieArticle) o).getDecision() != null) {
					row[2] = ((OpSortieArticle) o).getDecision();
				}

			}

			resulttable.add(row);
			row = new Object[8];
			// }
		}
		return resulttable;
	}

	public List<Operation> getListOpESArtByDirectionByCod(Direction d, CodeArticle codeart) {
		if (d == null) {
			Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
			d = cur.getDirection();
		}

		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		Date sdate = new GregorianCalendar(year, Calendar.JANUARY, 1).getTime();
		Date edate = new GregorianCalendar(year, Calendar.DECEMBER, 30).getTime();
		return usermetierimpl.getListOpESArtValideByDirectionByCod(codeart, d, sdate, edate);
	}

	// list objet format pour journal By codification
	public List<Object[]> getListForJournalStockByCod(Direction d, CodeArticle code) {
		List<Operation> lesoperations = getListOpESArtByDirectionByCod(d, code);
		// structure de données
		List<Object[]> resulttable = new ArrayList<Object[]>();
		Object[] row = new Object[9];
		for (Operation o : lesoperations) {
			// Date operation
			row[0] = null;
			// reference entrée
			row[1] = "";// "";
			// quantite entrée
			row[2] = new Long(0);
			// quantite entrée cumulée
			row[3] = new Long(0);
			// reference sortie
			row[4] = new Long(0);// "";
			// quantite sortie
			row[5] = "";
			// quantite de depart à reporter
			row[6] = new Long(0);

			// Remplissage à partir du liste des operations

			// initiale
			row[0] = o.getDate();

			// entree
			if (o instanceof OpEntreeArticle) {
				// row[1] = o.getId(); // need to add this attribut for operation reference
				row[1] = ((OpEntreeArticle) o).getArticle().getReference();
				row[2] = (Long) (((OpEntreeArticle) o).getArticle().getNombre());
				row[3] = (Long) row[2] + 0; // need to set previous nombre
			}
			// sortie
			else if (o instanceof OpSortieArticle) {
				// row[4] = o.getId();
				row[4] = ((OpSortieArticle) o).getDecision();
				row[5] = (Long) (((OpSortieArticle) o).getNombreToS());

			}
			// report
			row[6] = areportByCod(d,code,null);
			// row[6] = 5;
			resulttable.add(row);
			row = new Object[9];
		}

		Collections.sort(resulttable, new Comparator<Object[]>() {
			public int compare(Object[] s1, Object[] s2) {
				Date d1 = (Date) s1[0];
				Date d2 = (Date) s2[0];
				System.out.println("date :" + d1);
				return d1.compareTo(d2);
			}
		});

		return resulttable;
	}

	public List<Operation> getListOpESArtByDirectionByCod(Direction d, CodeArticle codeart, Date s, Date f) {
		if (d == null) {
			Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
			d = cur.getDirection();
		}

		Date sdate = s;
		Date edate = f;
		return usermetierimpl.getListOpESArtValideByDirectionByCod(codeart, d, sdate, edate);
	}

	public List<Object[]> getListForJournalStockByCod(Direction d, CodeArticle code, Date s, Date f) {
		List<Operation> lesoperations = getListOpESArtByDirectionByCod(d, code, s, f);
		// structure de données
		List<Object[]> resulttable = new ArrayList<Object[]>();
		Object[] row = new Object[10];
		for (Operation o : lesoperations) {
			// Date operation
			row[0] = null;
			// reference entrée
			row[1] = "";// "";
			// quantite entrée
			row[2] = new Long(0);
			// quantite entrée cumulée
			row[3] = new Long(0);
			// reference sortie
			row[4] = "";// "";
			// quantite sortie
			row[5] = new Long(0);
			// quantite de depart à reporter
			row[6] = new Long(0);
			// espece des unites
			row[8] = "";

			// Remplissage à partir du liste des operations

			// initiale
			row[0] = o.getDate();

			// entree
			if (o instanceof OpEntreeArticle) {
				// row[1] = o.getId(); // need to add this attribut for operation reference
				row[1] = ((OpEntreeArticle) o).getArticle().getReference();
				row[2] = (Long) (((OpEntreeArticle) o).getArticle().getNombre());
				row[3] = (Long) row[2] + 0; // need to set previous nombre
				row[8] = ((OpEntreeArticle) o).getArticle().getEspeceunit();
			}
			// sortie
			else if (o instanceof OpSortieArticle) {
				row[4] = ((OpSortieArticle) o).getDecision();
				// row[4] = o.getId();
				row[5] = (Long) (((OpSortieArticle) o).getNombreToS());
				row[8] = ((OpSortieArticle) o).getArticle().getEspeceunit();

			}
			// report
			row[6] = areportByCod(d,code,f);
			// row[6] = 5;
			resulttable.add(row);
			row = new Object[9];
		}

		Collections.sort(resulttable, new Comparator<Object[]>() {
			public int compare(Object[] s1, Object[] s2) {
				Date d1 = (Date) s1[0];
				Date d2 = (Date) s2[0];
				System.out.println("date :" + d1);
				return d1.compareTo(d2);
			}
		});

		return resulttable;
	}

	public Long areportByCod(Direction dir, CodeArticle code, Date stop) {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		Date stopdate = new GregorianCalendar(year, Calendar.MAY, 1).getTime();
		if(stop !=null) {
			stopdate = stop;
		}
		if(dir ==null) {
			Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
			dir = cur.getDirection();
		}
		
		Long nombreareporter = usermetierimpl.getAreporter(code, dir, stopdate);
		return nombreareporter;
	}

	private List<Materiel> listHistoriqueMatDirection;

	public List<Materiel> getListHistoriqueMatDirection() {
		if (listHistoriqueMatDirection == null) {
			Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
			listHistoriqueMatDirection = usermetierimpl.getListMatByDirection(agent.getDirection());
		}
		return listHistoriqueMatDirection;
	}

	public void setListHistoriqueMatDirection(List<Materiel> listHistoriqueMatDirection) {
		this.listHistoriqueMatDirection = listHistoriqueMatDirection;
	}

	private List<Materiel> listAllMateriel;

	public List<Materiel> getListAllMateriel() {
		if (listAllMateriel == null) {
			listAllMateriel = usermetierimpl.getListMat();
		}
		return listAllMateriel;
	}

	public void setListAllMateriel(List<Materiel> listAllMateriel) {
		this.listAllMateriel = listAllMateriel;
	}

	public List<OpEntree> getListOpentreeForOrdreByDir(Direction d) {
		return usermetierimpl.listOpentreeByStateByDirection(EtatOperation.ACCEPTED, d);
	}

	public List<OpSortie> getListSortieValideByDir(Direction d) {
		return usermetierimpl.getListOpSortieValideByDirection(d);
	}

	public List<Object[]> getListESForJournalByDir(Direction dir, Date fdate) {

		if (dir == null) {
			Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
			dir = cur.getDirection();
		}
		// this.direction = cur.getDirection();
		Date date = new Date();
		if (fdate != null) {
			date = fdate;
		} else {
			System.out.println(" fdate getList  null");
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		System.out.println(" my year getList " + year);
		Date sdate = new GregorianCalendar(year, Calendar.JANUARY, 1).getTime();
		Date edate = new GregorianCalendar(year, Calendar.DECEMBER, 30).getTime();
		System.out.println("ETO");
		List<OperationES> listop = usermetierimpl.getListOpESForJournal(dir, sdate, edate);
		System.out.println("SA ETO");
		Collections.sort(listop, new Comparator<OperationES>() {
			public int compare(OperationES o1, OperationES o2) {
				Long id1 = o1.getId();
				Long id2 = o2.getId();
				// System.out.println("date :" + d1);
				return id1.compareTo(id2);
			}
		});
		List<Object[]> listobjectForJournal = new ArrayList<Object[]>();
		Long i = 1L;
		for (OperationES op : listop) {
			Object[] row = new Object[13];

			if (op instanceof OpEntree) {
				List<Object[]> bydesignation1 = (this.getDesingationByOpEntree(op));

				for (Object[] nom : bydesignation1) {
					List<Object[]> liste = (List<Object[]>) nom[2];
					for (Object[] des : liste) {

						Designation d = (Designation) des[0];
						// id
						row[12] = op.getId();
						row[0] = i;
						// numero d'ordre
						row[1] = op.getNumoperation();
						// date
						row[2] = op.getDate();
						// origine
						row[3] = d.getOrigine();
						// designation
						String mar = "";
						if (d.getMarque() != null) {
							mar = d.getMarque().getDesignation();
						}
						String rens = "";
						if (d.getRenseignement() != null) {
							rens = d.getRenseignement();
						}
						row[4] = d.getTypematerieladd().getDesignation() + " - " + mar + " - " + rens
						// mat.getNumSerie();
						;
						// espece unite
						row[5] = d.getEspeceUnite();
						// pu
						row[6] = d.getPu();
						// nombre par desingation entree
						row[7] = des[1];
						// total entree
						row[8] = d.getPu() * (Long) row[7];
						// nombre par desingation sortie
						row[9] = new Long(0);
						// total sortie
						row[10] = new Double(0);
						row[11] = d;
						listobjectForJournal.add(row);
						row = new Object[13];
						i = i + 1;
					}
				} /*
					 * for(Materiel mat :((OpEntree) op).getListMat()) { //id row[0] = op.getId();
					 * //numero d'ordre row[1] = op.getNumoperation(); //date row[2] = op.getDate();
					 * //origine row[3] = mat.getDesign().getOrigine(); // designation row[4] =
					 * mat.getDesign().getTypematerieladd().getDesignation() + " - " +
					 * mat.getDesign().getMarque() + " - " + mat.getDesign().getRenseignement() +
					 * " - " + mat.getNumSerie(); //espece unite row[5] =
					 * mat.getDesign().getEspeceUnite(); //pu row[6] = mat.getDesign().getPu();
					 * //nombre par desingation entree row[7] = 1; //total entree row[8] =
					 * mat.getDesign().getPu()*(Integer)row[7]; //nombre par desingation sortie
					 * row[9] = 0; //total sortie row[10] = 0; row[11] = mat;
					 * listobjectForJournal.add(row); row = new Object[12]; }
					 */

			} else if (op instanceof OpSortie) {
				// id
				row[12] = op.getId();
				row[0] = i;
				// numero d'ordre
				row[1] = op.getNumoperation();
				// date
				row[2] = op.getDate();
				// origine
				if (((OpSortie) op).getMotifsortie() != null) {
					row[3] = ((OpSortie) op).getMotifsortie().getDesignation();
					if (((OpSortie) op).getMotifsortie().getDesignation().equalsIgnoreCase("Affectation")) {
						row[3] = row[3] + " vers " + ((OpSortie) op).getDirec().getCodeDirection();
					}
				}
				// designation
				Materiel mat = op.getMat();
				String marqueMat = "";
				if (mat.getDesign().getMarque() != null) {
					marqueMat = mat.getDesign().getMarque().getDesignation();
				}

				String rens = "";
				if (mat.getDesign().getRenseignement() != null) {
					rens = mat.getDesign().getRenseignement();
				}
				row[4] = mat.getDesign().getTypematerieladd().getDesignation() + " - " + marqueMat + " - " + rens
						+ " - " + mat.getNumSerie();
				// espece unite
				row[5] = mat.getDesign().getEspeceUnite();
				// pu
				row[6] = mat.getDesign().getPu();
				// nombre par desingation entree
				row[7] = new Long(0);
				// total entree
				row[8] = new Double(0);
				// nombre par desingation sortie
				row[9] = 1L;
				// total sortie
				row[10] = mat.getDesign().getPu() * (Long) row[9];
				row[11] = mat.getDesign();
				listobjectForJournal.add(row);
				row = new Object[13];
				i = i + 1;
			}
		}

		return listobjectForJournal;
	}

	public List<Object[]> getListobjectForInvetaireByDir(Direction direc, Date datdeb, Date datend) {
		// if (listobjectForInvetaire == null) {
		if (direc == null) {
			Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
			direc = cur.getDirection();
		}

		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		Date sdate = new GregorianCalendar(year, Calendar.JANUARY, 1).getTime();
		Date edate = new GregorianCalendar(year, Calendar.DECEMBER, 31).getTime();

		if (datdeb != null & datend != null) {
			sdate = datdeb;
			edate = datend;
		}

		System.out.println("RRRRRRRRRRR Begin:");
		List<Object[]> r = usermetierimpl.getListObjectForinvetaire(direc, sdate, edate);
		System.out.println("RRRRRRRRRRR Ending:");

		/*
		 * for(Object[] o:r) { System.out.println(String.valueOf(o[0]));
		 * System.out.println(String.valueOf(o[1])); }
		 */
		List<Object[]> resultstable = new ArrayList<Object[]>();

		for (Object[] m : r) {
			Object[] row = new Object[13];
			Materiel mat = (Materiel) m[1];
			OpSortie o = (OpSortie) m[0];

			if (mat.getMyoperationEntree() != null && mat.getMyoperationEntree().getDate().compareTo(edate) >= 0) {

				continue;
			}
			// Nomenclature
			row[0] = mat.getDesign().getNomenMat().getNomenclature();
			// Numéros du folio du grand livre
			row[1] = mat.getIdMateriel();
			// Désignation du matériel
			String marqueMat = "Inconnue";
			if (mat.getDesign().getMarque() != null) {
				marqueMat = mat.getDesign().getMarque().getDesignation();
			}
			String rens = "";
			if (mat.getDesign().getRenseignement() != null) {
				rens = mat.getDesign().getRenseignement();
			}
			row[2] = mat.getDesign().getTypematerieladd().getDesignation() + " - " + marqueMat + " - " + rens
			// + mat.getNumSerie()
			;
			// Espèce des unités
			row[3] = mat.getDesign().getEspeceUnite();
			// Prix de l’unité
			row[4] = mat.getDesign().getPu();
			// Existantes au 1er Janvier X
			row[5] = 0;
			// Entrées pendant l’année X
			if (mat.getMyoperationEntree() == null || mat.getMyoperationEntree().getDate().compareTo(sdate) <= 0) {
				row[6] = "Matériel Existant";
				row[5] = 1;
			} else {
				row[6] = mat.getMyoperationEntree().getNumoperation();
			}

			// Sortie pendant l’année X
			if (o == null) {
				row[7] = "Aucune sortie";
			} else {
				if (o.getDate() != null && o.getDate().compareTo(edate) >= 0) {
					row[7] = "Aucune sortie";
				} else {
					row[7] = o.getNumoperation();
				}
			}
			// Reste au 31 déc. X
			row[8] = "reste";
			// Décompte
			row[9] = "decompte";
			row[10] = mat.getDesign().getTypematerieladd();
			row[11] = mat.getDesign();
			row[12] = mat;

			resultstable.add(row);
		}

		// group by designation
		List<Object[]> resultstableGrouped = new ArrayList<Object[]>();

		Map<Designation, List<Object[]>> map = new HashMap<Designation, List<Object[]>>();

		for (Object[] o : resultstable) {
			Designation key = (Designation) o[11];
			if (map.containsKey(key)) {
				List<Object[]> list = map.get(key);
				list.add(o);

			} else {
				List<Object[]> list = new ArrayList<Object[]>();
				list.add(o);
				map.put(key, list);
			}

		}
		for (Map.Entry<Designation, List<Object[]>> entry : map.entrySet()) {
			// System.out.println(entry.getKey().getIdDesignation() + ":" +
			// entry.getValue().size());
			Object[] row = new Object[12];
			Designation des = entry.getKey();
			List<Object[]> infos = entry.getValue();
			// materiels
			List<Materiel> materiels = new ArrayList<Materiel>();
			for (Object[] o : infos) {
				materiels.add((Materiel) ((infos.get(0))[12]));
			}

			// Nomenclature
			row[0] = des.getNomenMat().getNomenclature();
			// Numéros du folio du grand livre
			row[1] = des.getIdDesignation();
			// Désignation du matériel
			row[2] = (infos.get(0))[2];
			;
			String series = "";
			for (Object[] o : infos) {
				series = series + " / " + ((Materiel) (o[12])).getNumSerie();
			}
			row[2] = row[2] + series;
			// Espèce des unités
			row[3] = des.getEspeceUnite();
			// Prix de l’unité
			row[4] = des.getPu();
			// Existantes au 1er Janvier X
			row[5] = 0;
			// Entrées pendant l’année X
			int entreeAx = 0;// entree pendant année X
			if (materiels.get(0).getMyoperationEntree() == null
					|| materiels.get(0).getMyoperationEntree().getDate().compareTo(sdate) < 0) {
				String es = "old";
				if (materiels.get(0).getMyoperationEntree() != null
						&& materiels.get(0).getMyoperationEntree().getDate().compareTo(sdate) < 0) {
					es = materiels.get(0).getMyoperationEntree().getDate().toString();
				}

				row[6] = "Matériel Existant " + es;
				row[5] = materiels.size();
				entreeAx = 0;

			} else {
				row[6] = materiels.get(0).getMyoperationEntree().getNumoperation();
				entreeAx = materiels.size();
			}

			// Sortie pendant l’année X
			String sortie = "0";
			int sortieAx = 0;
			for (Object[] o : infos) {
				if (!o[7].equals("Aucune sortie")) {
					sortie = sortie + (String) o[7] + " and ";
					sortieAx = sortieAx + 1;
				}

			}
			row[7] = sortie;
			/*
			 * if(o ==null) { row[7] = "Aucune sortie"; } else { row[7] =
			 * o.getNumoperation(); }
			 */
			// Reste au 31 déc. X
			row[8] = (Integer) row[5] + entreeAx - sortieAx; // existant + entree en X - sortie en X
			// Décompte
			row[9] = (Integer) row[8] * des.getPu();
			row[10] = des.getTypematerieladd();
			row[11] = des;

			// set nombre entree et sortie pendant X
			row[6] = entreeAx;
			row[7] = sortieAx;

			resultstableGrouped.add(row);
		}

		listobjectForInvetaire = resultstableGrouped;
		// }

		return listobjectForInvetaire;
	}

	private List<MaterielEx> listMaterielexistant;

	public List<MaterielEx> getListMaterielexistant() {
		if (listMaterielexistant == null) {
			Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
			listMaterielexistant = usermetierimpl.getListMatEx(agent.getDirection());
		}
		return listMaterielexistant;
	}

	public void setListMaterielexistant(List<MaterielEx> listMaterielexistant) {
		this.listMaterielexistant = listMaterielexistant;
	}

	private List<MaterielNouv> listMaterielNouveauNonValide;

	public List<MaterielNouv> getListMaterielNouveauNonValide() {
		if (listMaterielNouveauNonValide == null) {
			Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
			listMaterielNouveauNonValide = usermetierimpl.getListMaterielNouvNonValide(agent.getDirection());
		}
		return listMaterielNouveauNonValide;
	}

	public void setListMaterielNouveauNonValide(List<MaterielNouv> listMaterielNouveauNonValide) {
		this.listMaterielNouveauNonValide = listMaterielNouveauNonValide;
	}

	private List<MaterielNouv> listMaterielNouveauValide;

	public List<MaterielNouv> getListMaterielNouveauValide() {
		if (listMaterielNouveauValide == null) {
			Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
			listMaterielNouveauValide = usermetierimpl.getListMaterielNouvValide(agent.getDirection());
		}
		return listMaterielNouveauValide;
	}

	public void setListMaterielNouveauValide(List<MaterielNouv> listMaterielNouveauValide) {
		this.listMaterielNouveauValide = listMaterielNouveauValide;
	}

	public OpEntree getCurrentOpEntree() {
		return currentOpEntree;
	}

	public void setCurrentOpEntree(OpEntree currentOpEntree) {
		if (currentOpEntree == null) {
			System.out.println("FA AHOANA");
		} else {
			System.out.println("cur :" + currentOpEntree.getId());
		}
		this.currentOpEntree = currentOpEntree;
	}

	public OpSortie getCurrentOpSortie() {
		return currentOpSortie;
	}

	public void setCurrentOpSortie(OpSortie currentOpSortie) {
		this.currentOpSortie = currentOpSortie;
	}

	public OpAttribution getCurrentOpAttribution() {
		return currentOpAttribution;
	}

	public void setCurrentOpAttribution(OpAttribution currentOpAttribution) {
		this.currentOpAttribution = currentOpAttribution;
	}

	public OpDettachement getCurrentOpDettachement() {
		return currentOpDettachement;
	}

	public void setCurrentOpDettachement(OpDettachement currentOpDettachement) {
		this.currentOpDettachement = currentOpDettachement;
	}

	private OpEntree currentOpEntree;

	private OpSortie currentOpSortie;

	private OpAttribution currentOpAttribution;

	private OpDettachement currentOpDettachement;

	private List<Operation> listOperatoinByDirectionFiltered;

	public List<Operation> getListOperatoinByDirectionFiltered() {
		return listOperatoinByDirectionFiltered;
	}

	public void setListOperatoinByDirectionFiltered(List<Operation> listOperatoinByDirectionFiltered) {
		this.listOperatoinByDirectionFiltered = listOperatoinByDirectionFiltered;
	}

	public List<OpAttribution> getListOperationAttributionValidateByDirection(Direction d) {
		if (d == null) {
			Agent curentAg = (Agent) RequestFilter.getSession().getAttribute("agent");
			d = curentAg.getDirection();
		}
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		Date sdate = new GregorianCalendar(year - 2, Calendar.JANUARY, 1).getTime();
		Date edate = new GregorianCalendar(year + 1, Calendar.DECEMBER, 30).getTime();
		return usermetierimpl.getListOpAttrByValideByDirection(d, sdate, edate, EtatOperation.ACCEPTED);
	}

	public List<MaterielNouv> getListMaterielNouveauValideFor(Direction d) {
		if (d == null) {
			Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
			d = agent.getDirection();
		}

		return usermetierimpl.getListMaterielNouvValide(d);
	}

	public List<MaterielNouv> getListMaterielNouveauNonValideFor(Direction d) {
		if (d == null) {
			Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
			d = agent.getDirection();
		}
		return usermetierimpl.getListMaterielNouvNonValide(d);
	}

	public List<MaterielEx> getListMaterielExistantFor(Direction d) {
		if (d == null) {
			Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
			d = agent.getDirection();
		}
		return usermetierimpl.getListMatEx(d);
	}

	public List<Object[]> getListESExForEtatAppr(Direction dir, Date debut, Date fin) {

		DateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.FRANCE);

		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		Date sdate = new GregorianCalendar(year, Calendar.JANUARY, 1).getTime();
		Date edate = new GregorianCalendar(year, Calendar.DECEMBER, 30).getTime();

		if (fin != null && debut != null) {
			sdate = debut;
			edate = fin;
		}

		if (dir == null) {
			Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
			dir = cur.getDirection();
		}

		// GET MATERIEL INVENTAIRE AND TRY TO MODEL IT APPRECIATIF
		System.out.println("RRRRRRRRRRR Begin:");
		List<Object[]> r = usermetierimpl.getListObjectForinvetaire(dir, sdate, edate);
		System.out.println("RRRRRRRRRRR Ending:");

		//

		// group by designation
		List<Object[]> resultstableGrouped = new ArrayList<Object[]>();

		Map<Designation, List<Object[]>> map = new HashMap<Designation, List<Object[]>>();

		for (Object[] o : r) {
			Materiel mat = (Materiel) o[1];
			Designation key = mat.getDesign();
			if (map.containsKey(key)) {
				List<Object[]> list = map.get(key);
				list.add(o);

			} else {
				List<Object[]> list = new ArrayList<Object[]>();
				list.add(o);
				map.put(key, list);
			}

		}
		for (Map.Entry<Designation, List<Object[]>> entry : map.entrySet()) {
			// System.out.println(entry.getKey().getIdDesignation() + ":" +
			// entry.getValue().size());

			Designation des = entry.getKey();
			List<Object[]> infos = entry.getValue();
			// materiels
			List<Materiel> materiels = new ArrayList<Materiel>();
			List<OpSortie> os = new ArrayList<OpSortie>();
			for (Object[] o : infos) {
				materiels.add((Materiel) (o[1]));
				if(o[0]!=null) {
					os.add((OpSortie) o[0]);
				}
			}

			// Nomenclature
			String nomenclature = des.getTypematerieladd().getNomenclaureParent().getNomenclature();

			// Désignation du matériel
			String marque = "Inconnue";
			if (des.getMarque() != null) {
				marque = des.getMarque().getDesignation();
			}
			String rens = "";
			if (des.getRenseignement() != null) {
				rens = des.getRenseignement();
			}
			String series = " ";
			for (Materiel m : materiels) {
				series = series + " / " + m.getNumSerie();
			}
			// Designation Finale
			String designation = des.getTypematerieladd().getDesignation() + " - " + marque + " - " + rens + series;
			// Prix de l’unité
			Double pu = des.getPu();
			
			/////////////////////
			/*
			 * Debut initilaisation calcul entree
			 */
			// Existantes au 1er Janvier X
			int existp = 0;
			// Motif entree
			String motifentre = "";
			// Entrées pendant l’année X
			if (materiels.get(0).getMyoperationEntree() != null && materiels.get(0).getMyoperationEntree().getDate().compareTo(edate) >= 0) {
				System.out.println("mat tsy misy" + materiels.get(0).getReference());
				continue;
			}
			if (materiels.get(0).getMyoperationEntree() == null || materiels.get(0).getMyoperationEntree().getDate().compareTo(sdate) < 0) {
				motifentre = "Matériel Existant ";
				existp = materiels.size();
				//existp = 1;
				// add row 1
				Object[] row = new Object[21];
				// Nomenclature
				row[0] = nomenclature;
				// justificatives
				row[1] = motifentre;
				// date
				if (materiels.get(0).getMyoperationEntree() == null) {
					if(materiels.get(0).getAnneeAcquisition()!=null) {
						row[15] = materiels.get(0).getAnneeAcquisition();
					}else {
						row[15] ="";
					}
					
				} else {
					row[15] = df.format(materiels.get(0).getMyoperationEntree().getDate());
				}
				// Désignation sommaire des opérations
				row[2] = designation;
				// Désignation sommaire des opérations
				row[2] = designation;

				// par nomenclature
				row[4] = new Double(0);
				row[6] = new Double(0);

				row[7] = new Double(0);
				row[8] = new Double(0);

				row[9] = new Double(0);
				row[10] = new Double(0);

				row[11] = new Double(0);
				row[12] = new Double(0);

				row[13] = new Double(0);
				row[14] = new Double(0);

				row[16] = new Double(0);
				row[17] = new Double(0);
				row[18] = new Double(0);
				row[19] = new Double(0);
				row[20] = new Double(0);
				// add existant for that
				if (nomenclature.equals("1")) {
					row[16] = existp * pu;
				} else if (nomenclature.equals("2")) {
					row[17] = existp * pu;
				} else if (nomenclature.equals("3")) {
					row[18] = existp * pu;
				} else if (nomenclature.equals("5")) {
					row[19] = existp * pu;
				} else if (nomenclature.equals("10")) {
					row[20] = existp * pu;
				}
				resultstableGrouped.add(row);
			} else {
				motifentre = materiels.get(0).getMyoperationEntree().getNumoperation();
				// add row 2
				Object[] row = new Object[21];
				// Nomenclature
				row[0] = nomenclature;
				// justificatives
				row[1] = motifentre;
				// date
				row[15] = df.format(materiels.get(0).getMyoperationEntree().getDate());
				// Désignation sommaire des opérations
				row[2] = designation;

				// par nomenclature
				row[4] = new Double(0);
				row[6] = new Double(0);

				row[7] = new Double(0);
				row[8] = new Double(0);

				row[9] = new Double(0);
				row[10] = new Double(0);

				row[11] = new Double(0);
				row[12] = new Double(0);

				row[13] = new Double(0);
				row[14] = new Double(0);

				row[16] = new Double(0);
				row[17] = new Double(0);
				row[18] = new Double(0);
				row[19] = new Double(0);
				row[20] = new Double(0);
				// add existant for that
				if (nomenclature.equals("1")) {
					row[4] = materiels.size() * pu;
				} else if (nomenclature.equals("2")) {
					row[7] = materiels.size() * pu;
				} else if (nomenclature.equals("3")) {
					row[9] = materiels.size() * pu;
				} else if (nomenclature.equals("5")) {
					row[11] = materiels.size() * pu;
				} else if (nomenclature.equals("10")) {
					row[13] = materiels.size() * pu;
				}
				resultstableGrouped.add(row);
			}
			
			/*
			 * Debut initilaisation sortie
			 */
			int nbrsortie = 0;
			// Sortie pendant l’année X
			if (os.size() == 0) {
				nbrsortie = 0;
			} else {
				String motifsortie = " ";
				for(OpSortie o: os) {
					motifsortie = motifsortie+ o.getNumoperation()+" ";
				}		
				// add row 2
				Object[] row = new Object[21];
				// Nomenclature
				row[0] = nomenclature;
				// justificatives
				row[1] = motifsortie;
				// date
				row[15] = " ";
				for(OpSortie o: os) {
					row[15] = row[15] + df.format(o.getDate()) + " ";
				}
				// Désignation sommaire des opérations
				row[2] = designation;

				// par nomenclature
				row[4] = new Double(0);
				row[6] = new Double(0);

				row[7] = new Double(0);
				row[8] = new Double(0);

				row[9] = new Double(0);
				row[10] = new Double(0);

				row[11] = new Double(0);
				row[12] = new Double(0);

				row[13] = new Double(0);
				row[14] = new Double(0);

				row[16] = new Double(0);
				row[17] = new Double(0);
				row[18] = new Double(0);
				row[19] = new Double(0);
				row[20] = new Double(0);
				// add existant for that
				if (nomenclature.equals("1")) {
					row[6] = os.size() * pu;
				} else if (nomenclature.equals("2")) {
					row[8] = os.size() * pu;
				} else if (nomenclature.equals("3")) {
					row[10] = os.size() * pu;
				} else if (nomenclature.equals("5")) {
					row[12] = os.size() * pu;
				} else if (nomenclature.equals("10")) {
					row[14] = os.size() * pu;
				}
				resultstableGrouped.add(row);

			}	
				
				
		}

		List<Object[]> resultstable = new ArrayList<Object[]>();
		for (Object[] m : r) {

			Materiel mat = (Materiel) m[1];
			OpSortie o = (OpSortie) m[0];

			// Nomenclature
			String nomenclature = mat.getDesign().getNomenMat().getNomenclature();
			//nomenclature = mat.getDesign().getNomenMat().getNomenclature();

			// Désignation du matériel
			String marque = "Inconnue";
			//marque = "Inconnue";
			if (mat.getDesign().getMarque() != null) {
				marque = mat.getDesign().getMarque().getDesignation();
			}
			String rens = "";
			//rens = "";
			if (mat.getDesign().getRenseignement() != null) {
				rens = mat.getDesign().getRenseignement();
			}
			// Designation Finale
			String designation = "";
			designation = mat.getDesign().getTypematerieladd().getDesignation() + " - " + marque + " - " + rens
					+ " - " + mat.getNumSerie();
			// Prix de l’unité
			Double pu = 0d; 
			pu = mat.getDesign().getPu();

			/*
			 * Debut initilaisation calcul entree
			 */
			// Existantes au 1er Janvier X
			int existp = 0;
			//existp = 0;
			// Motif entree
			String motifentre = "";
			//motifentre = "";
			// Entrées pendant l’année X
			if (mat.getMyoperationEntree() == null || mat.getMyoperationEntree().getDate().compareTo(sdate) < 0) {
				motifentre = "Matériel Existant";
				existp = 1;
				// add row 1
				Object[] row = new Object[21];
				// Nomenclature
				row[0] = nomenclature;
				// justificatives
				row[1] = motifentre;
				// date
				if (mat.getMyoperationEntree() == null) {
					row[15] = mat.getDesign().getAnneeAcquisition();
				} else {
					row[15] = df.format(mat.getMyoperationEntree().getDate());
				}
				// Désignation sommaire des opérations
				row[2] = designation;

				// par nomenclature
				row[4] = new Double(0);
				row[6] = new Double(0);

				row[7] = new Double(0);
				row[8] = new Double(0);

				row[9] = new Double(0);
				row[10] = new Double(0);

				row[11] = new Double(0);
				row[12] = new Double(0);

				row[13] = new Double(0);
				row[14] = new Double(0);

				row[16] = new Double(0);
				row[17] = new Double(0);
				row[18] = new Double(0);
				row[19] = new Double(0);
				row[20] = new Double(0);
				// add existant for that
				if (nomenclature.equals("1")) {
					row[16] = existp * pu;
				} else if (nomenclature.equals("2")) {
					row[17] = existp * pu;
				} else if (nomenclature.equals("3")) {
					row[18] = existp * pu;
				} else if (nomenclature.equals("5")) {
					row[19] = existp * pu;
				} else if (nomenclature.equals("10")) {
					row[20] = existp * pu;
				}
				resultstable.add(row);
			} else {
				motifentre = mat.getMyoperationEntree().getNumoperation();
				// add row 2
				Object[] row = new Object[21];
				// Nomenclature
				row[0] = nomenclature;
				// justificatives
				row[1] = motifentre;
				// date
				row[15] = df.format(mat.getMyoperationEntree().getDate());
				// Désignation sommaire des opérations
				row[2] = designation;

				// par nomenclature
				row[4] = new Double(0);
				row[6] = new Double(0);

				row[7] = new Double(0);
				row[8] = new Double(0);

				row[9] = new Double(0);
				row[10] = new Double(0);

				row[11] = new Double(0);
				row[12] = new Double(0);

				row[13] = new Double(0);
				row[14] = new Double(0);

				row[16] = new Double(0);
				row[17] = new Double(0);
				row[18] = new Double(0);
				row[19] = new Double(0);
				row[20] = new Double(0);
				// add existant for that
				if (nomenclature.equals("1")) {
					row[4] = 1 * pu;
				} else if (nomenclature.equals("2")) {
					row[7] = 1 * pu;
				} else if (nomenclature.equals("3")) {
					row[9] = 1 * pu;
				} else if (nomenclature.equals("5")) {
					row[11] = 1 * pu;
				} else if (nomenclature.equals("10")) {
					row[13] = 1 * pu;
				}
				resultstable.add(row);
			}

			/*
			 * Debut initilaisation sortie
			 */
			int nbrsortie = 0;
			//nbrsortie = 0;
			// Sortie pendant l’année X
			if (o == null) {
				nbrsortie = 0;
			} else {
				String motifsortie = o.getNumoperation();
				// add row 2
				Object[] row = new Object[21];
				// Nomenclature
				row[0] = nomenclature;
				// justificatives
				row[1] = motifsortie;
				// date
				row[15] = df.format(o.getDate());
				// Désignation sommaire des opérations
				row[2] = designation;

				// par nomenclature
				row[4] = new Double(0);
				row[6] = new Double(0);

				row[7] = new Double(0);
				row[8] = new Double(0);

				row[9] = new Double(0);
				row[10] = new Double(0);

				row[11] = new Double(0);
				row[12] = new Double(0);

				row[13] = new Double(0);
				row[14] = new Double(0);

				row[16] = new Double(0);
				row[17] = new Double(0);
				row[18] = new Double(0);
				row[19] = new Double(0);
				row[20] = new Double(0);
				// add existant for that
				if (nomenclature.equals("1")) {
					row[6] = 1 * pu;
				} else if (nomenclature.equals("2")) {
					row[8] = 1 * pu;
				} else if (nomenclature.equals("3")) {
					row[10] = 1 * pu;
				} else if (nomenclature.equals("5")) {
					row[12] = 1 * pu;
				} else if (nomenclature.equals("10")) {
					row[14] = 1 * pu;
				}
				resultstable.add(row);
			}

		}

		return resultstableGrouped;
	}
	
	private List<Object[]> listobjectForInvetaireBytypemat;
	
	
	
	/*public List<Object[]> getListobjectForInvetaireByDir(Direction direc, Date datdeb, Date datend) {
		
		return listobjectForInvetaire;
	}*/

	public List<Object[]> getListobjectForInvetaireBytypemat() {
		// if (listobjectForInvetaire == null) {
		/* Direction direc = null; 
		Date datdeb = null; 
		Date datend = null;
				if (direc == null) {
					Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
					direc = cur.getDirection();
				}

				Date date = new Date();
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				int year = calendar.get(Calendar.YEAR);
				Date sdate = new GregorianCalendar(year, Calendar.JANUARY, 1).getTime();
				Date edate = new GregorianCalendar(year, Calendar.DECEMBER, 31).getTime();

				if (datdeb != null & datend != null) {
					sdate = datdeb;
					edate = datend;
				}

				System.out.println("RRRRRRRRRRR Begin:");
				List<Object[]> r = usermetierimpl.getListObjectForinvetaire(direc, sdate, edate);
				System.out.println("RRRRRRRRRRR Ending:");

				
				List<Object[]> resultstable = new ArrayList<Object[]>();

				for (Object[] m : r) {
					Object[] row = new Object[13];
					Materiel mat = (Materiel) m[1];
					OpSortie o = (OpSortie) m[0];

					if (mat.getMyoperationEntree() != null && mat.getMyoperationEntree().getDate().compareTo(edate) >= 0) {

						continue;
					}
					// Nomenclature
					row[0] = mat.getDesign().getNomenMat().getNomenclature();
					// Numéros du folio du grand livre
					row[1] = mat.getIdMateriel();
					// Désignation du matériel
					String marqueMat = "Inconnue";
					if (mat.getDesign().getMarque() != null) {
						marqueMat = mat.getDesign().getMarque().getDesignation();
					}
					String rens = "";
					if (mat.getDesign().getRenseignement() != null) {
						rens = mat.getDesign().getRenseignement();
					}
					row[2] = mat.getDesign().getTypematerieladd().getDesignation() + " - " + marqueMat + " - " + rens
					// + mat.getNumSerie()
					;
					// Espèce des unités
					row[3] = mat.getDesign().getEspeceUnite();
					// Prix de l’unité
					row[4] = mat.getDesign().getPu();
					// Existantes au 1er Janvier X
					row[5] = 0;
					// Entrées pendant l’année X
					if (mat.getMyoperationEntree() == null || mat.getMyoperationEntree().getDate().compareTo(sdate) <= 0) {
						row[6] = "Matériel Existant";
						row[5] = 1;
					} else {
						row[6] = mat.getMyoperationEntree().getNumoperation();
					}

					// Sortie pendant l’année X
					if (o == null) {
						row[7] = "Aucune sortie";
					} else {
						if (o.getDate() != null && o.getDate().compareTo(edate) >= 0) {
							row[7] = "Aucune sortie";
						} else {
							row[7] = o.getNumoperation();
						}
					}
					// Reste au 31 déc. X
					row[8] = "reste";
					// Décompte
					row[9] = "decompte";
					row[10] = mat.getDesign().getTypematerieladd();
					row[11] = mat.getDesign();
					row[12] = mat;

					resultstable.add(row);
				}

				// group by designation
				List<Object[]> resultstableGrouped = new ArrayList<Object[]>();

				Map<TypeMateriel, List<Object[]>> map = new HashMap<TypeMateriel, List<Object[]>>();

				for (Object[] o : resultstable) {
					Designation des = (Designation) o[11];
					TypeMateriel key = des.getTypematerieladd();
					if (map.containsKey(key)) {
						List<Object[]> list = map.get(key);
						list.add(o);

					} else {
						List<Object[]> list = new ArrayList<Object[]>();
						list.add(o);
						map.put(key, list);
					}

				}
				for (Map.Entry<TypeMateriel, List<Object[]>> entry : map.entrySet()) {
					// System.out.println(entry.getKey().getIdDesignation() + ":" +
					// entry.getValue().size());
					Object[] row = new Object[12];
					//Designation des = entry.getKey();
					TypeMateriel tm = entry.getKey();
					List<Object[]> infos = entry.getValue();
					// materiels
					List<Materiel> materiels = new ArrayList<Materiel>();
					for (Object[] o : infos) {
						materiels.add((Materiel) ((infos.get(0))[12]));
					}

					// Nomenclature
					row[0] = tm.getNomenclaureParent().getNomenclature();
					// Numéros du folio du grand livre
					row[1] = 0L;
					// Désignation du matériel
					row[2] = tm.getDesignation();
					;
					String series = "";
					for (Object[] o : infos) {
						series = series + " / " + ((Materiel) (o[12])).getNumSerie();
					}
					row[2] = row[2] + series;
					// Espèce des unités
					row[3] = des.getEspeceUnite();
					// Prix de l’unité
					row[4] = des.getPu();
					// Existantes au 1er Janvier X
					row[5] = 0;
					// Entrées pendant l’année X
					int entreeAx = 0;// entree pendant année X
					if (materiels.get(0).getMyoperationEntree() == null
							|| materiels.get(0).getMyoperationEntree().getDate().compareTo(sdate) < 0) {
						String es = "old";
						if (materiels.get(0).getMyoperationEntree() != null
								&& materiels.get(0).getMyoperationEntree().getDate().compareTo(sdate) < 0) {
							es = materiels.get(0).getMyoperationEntree().getDate().toString();
						}

						row[6] = "Matériel Existant " + es;
						row[5] = materiels.size();
						entreeAx = 0;

					} else {
						row[6] = materiels.get(0).getMyoperationEntree().getNumoperation();
						entreeAx = materiels.size();
					}

					// Sortie pendant l’année X
					String sortie = "0";
					int sortieAx = 0;
					for (Object[] o : infos) {
						if (!o[7].equals("Aucune sortie")) {
							sortie = sortie + (String) o[7] + " and ";
							sortieAx = sortieAx + 1;
						}

					}
					row[7] = sortie;
					
					// Reste au 31 déc. X
					row[8] = (Integer) row[5] + entreeAx - sortieAx; // existant + entree en X - sortie en X
					// Décompte
					row[9] = (Integer) row[8] * des.getPu();
					row[10] = des.getTypematerieladd();
					row[11] = des;

					// set nombre entree et sortie pendant X
					row[6] = entreeAx;
					row[7] = sortieAx;

					resultstableGrouped.add(row);
				}

				listobjectForInvetaireBytypemat = resultstableGrouped;
				// }
				
			*/

		return listobjectForInvetaireBytypemat;
	}

	public void setListobjectForInvetaireBytypemat(List<Object[]> listobjectForInvetaireBytypemat) {
		this.listobjectForInvetaireBytypemat = listobjectForInvetaireBytypemat;
	}
	
	public Double getTotalInventaire(List<Object[]> listobjectForInvetaireset) {
		Double total =0d;
			for(Object[] m: listobjectForInvetaireset) {
				total+=(Double)m[9];
			}
		return total;
	}
	
	public Double getTotalJournal(List<Object[]> listobjectForJournal) {
		Double total =0d;
		Double entree = 0d;
		Double sortie = 0d;
		for(Object[] c: listobjectForJournal) {
			entree+=(Double)c[8];
			sortie+=(Double)c[10];
		}
		total =entree -sortie;
		return total;
	}

}
