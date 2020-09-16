package com.douane.metier.user;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import com.douane.entite.*;
import com.douane.model.EtatOperation;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.douane.repository.*;

import com.douane.requesthttp.RequestFilter;

import come.douane.dao.operation.IOperationDAO;

@Transactional
public class UserMetier implements IUserMetier {

	@Autowired
	private UserRepository userrepos;
	@Autowired
	private AgentRepository agentrepos;
	@Autowired
	private MaterielRepository matrepos;

	@Autowired
	private MaterielExRepository materielExRepository;

	@Autowired
	private MaterielNouvRepository materielNouvRepository;
	
	@Autowired
	private OpESRepository opesrepos;
	
	@Autowired
	private DesignationRepository desrepos;
	
	public DesignationRepository getDesrepos() {
		return desrepos;
	}

	public void setDesrepos(DesignationRepository desrepos) {
		this.desrepos = desrepos;
	}
	
	public OpESRepository getOpesrepos() {
		return opesrepos;
	}

	public void setOpesrepos(OpESRepository opesrepos) {
		this.opesrepos = opesrepos;
	}

	public OpRepository getOprepos() {
		return oprepos;
	}

	public void setOprepos(OpRepository oprepos) {
		this.oprepos = oprepos;
	}

	public OpEntreeRepository getOpentreerepos() {
		return opentreerepos;
	}

	public void setOpentreerepos(OpEntreeRepository opentreerepos) {
		this.opentreerepos = opentreerepos;
	}

	public OpSortieRepository getOpsortierepos() {
		return opsortierepos;
	}

	public void setOpsortierepos(OpSortieRepository opsortierepos) {
		this.opsortierepos = opsortierepos;
	}

	public OpAttrRepository getOpattrrepos() {
		return opattrrepos;
	}

	public void setOpattrrepos(OpAttrRepository opattrrepos) {
		this.opattrrepos = opattrrepos;
	}

	public OpDettRepository getOpdettrepos() {
		return opdettrepos;
	}

	public void setOpdettrepos(OpDettRepository opdettrepos) {
		this.opdettrepos = opdettrepos;
	}

	public CodeArticleRepository getCodeartrepos() {
		return codeartrepos;
	}

	public void setCodeartrepos(CodeArticleRepository codeartrepos) {
		this.codeartrepos = codeartrepos;
	}

	public ArticleRepository getArtreops() {
		return artreops;
	}

	public void setArtreops(ArticleRepository artreops) {
		this.artreops = artreops;
	}

	public OpEnArtRepository getOpentreeartrepos() {
		return opentreeartrepos;
	}

	public void setOpentreeartrepos(OpEnArtRepository opentreeartrepos) {
		this.opentreeartrepos = opentreeartrepos;
	}

	public OpSortArtRepository getOpsortieartrepos() {
		return opsortieartrepos;
	}

	public void setOpsortieartrepos(OpSortArtRepository opsortieartrepos) {
		this.opsortieartrepos = opsortieartrepos;
	}

	@Autowired
	private OpRepository oprepos;

	@Autowired
	private OpEntreeRepository opentreerepos;

	@Autowired
	private OpSortieRepository opsortierepos;

	@Autowired
	private OpAttrRepository opattrrepos;

	@Autowired
	private OpDettRepository opdettrepos;

	@Autowired
	private CodeArticleRepository codeartrepos;

	@Autowired
	private ArticleRepository artreops;

	@Autowired
	private ArticleExRepository artexreops;

	@Autowired
	private ArticleNouvRepository artnouvreops;

	@Autowired
	private OpEnArtRepository opentreeartrepos;

	@Autowired
	private OpSortArtRepository opsortieartrepos;

	@Autowired
	@ManagedProperty(value = "#{operationdao}")
	private IOperationDAO operationdao;

	@Override
	public Useri addUser(Useri u) {
		// TODO Auto-generated method stub
		userrepos.save(u);
		return u;
	}

	@Override
	public List<Useri> listUser() {
		// TODO Auto-generated method stub
		return (List<Useri>) userrepos.findAll();
	}

	@Override
	public void remUser(Useri u) {
		// TODO Auto-generated method stub
		userrepos.delete(u);
	}

	@Override
	public Agent addAgent(Agent a) {
		// TODO Auto-generated method stub
		System.out.println("udate2");
		agentrepos.save(a);
		return a;
	}

	@Override
	public Agent addAgentUser(Agent a, Useri u) {
		// TODO Auto-generated method stub
		a.setRoleAgent(u);
		// u.addAgentToList(a);
		agentrepos.save(a);

		return a;
	}

	@Override
	public Agent changeAgentPass(Agent a, String codedPassword) {
		// TODO Auto-generated method stub
		Agent av = agentrepos.findOne(a.getIdAgent());
		av.setPassword(codedPassword);
		agentrepos.save(av);
		return av;
	}

	@Override
	public void remAgent(Agent a) {
		// TODO Auto-generated method stub
		System.out.println("remove agent " + a.getIm());
		agentrepos.delete(a.getIdAgent());

	}
	@Override
	public void desactiveAgent(Agent a) {
		a.setActive(false);
		agentrepos.save(a);
	}

	@Override
	public List<Agent> findAgentByNom(String name) {
		// TODO Auto-generated method stub

		return agentrepos.findByNomAgentContainingIgnoreCase(name);
	}

	@Override
	public Agent findAgentByIm(Long im_agent) {
		// TODO Auto-generated method stub
		return (Agent) agentrepos.findByIm(im_agent);
	}

	// temporary
	@Override
	public List<Agent> findAllAgents() {
		// TODO Auto-generated method stub
		return (List<Agent>) agentrepos.findAll();
	}

	@Override
	public OpEntree reqEntrerMateriel(List<Materiel> l, Agent dc, String facturePath, String refFacture) {
		// TODO Auto-generated method stub*
		System.out.println("let entrer materiel");
		OpEntree entree = new OpEntree(new Date(), new Date(), dc.getIp(), dc);
		entree.setPathDoc(facturePath);
		entree.setRefFact(refFacture);
		// entree.setListMat(l);

		for (Materiel m : l) {
			m.setDc(dc);
			entree.addMateriel(m);
		}
		entree = opentreerepos.save(entree);
		/*
		 * MaterielEx ma= new MaterielEx(); ma = matrepos.save(ma);
		 */
		// m = materielExRepository.save((MaterielEx) m);
		return entree;
	}

	/*@Override
	public OpSortie reqSortirMateriel(Materiel m, MotifSortie motif, Direction d, Service s, Bureau b, Agent oper)
			throws Exception {
		// TODO Auto-generated method stub
		// try {
		if (m.getDetenteur() != null) {

			throw new Exception("Materiel deja detenu");
		}
		OpSortie sortie = new OpSortie(new Date(), new Date(), oper.getIp(), oper, m, d, s, b, motif);

		oprepos.save(sortie);
		return sortie;
		
	}*/

	@Override
	public OpSortie reqSortirMateriel(Materiel m, MotifSortie motif, Direction d, Agent oper) throws Exception {
		// TODO Auto-generated method stub
		// try {
		if (m.getDetenteur() != null) {
			throw new Exception("Matériel déjà détenu");
		}
		OpSortie sortie = new OpSortie(new Date(), new Date(), oper.getIp(), oper, m, d, motif);

		oprepos.save(sortie);
		return sortie;
		/*
		 * } catch(Exception e) { FacesMessage message = new
		 * FacesMessage(FacesMessage.SEVERITY_WARN,
		 * "Erreur requete pour operation sortie", e.getMessage());
		 * FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage(e.getMessage()));
		 * FacesContext.getCurrentInstance().addMessage(null, message); return null; }
		 */
	}

	@Override
	public OpSortie reqSortirMateriel(Materiel m, MotifSortie motif, Agent oper) throws Exception {
		// TODO Auto-generated method stub
		// try{
		if (m.getDetenteur() != null) {
			throw new Exception("Matériel déjà detenu");
		}
		OpSortie sortie = new OpSortie(new Date(), new Date(), oper.getIp(), oper, m, motif);

		oprepos.save(sortie);
		return sortie;
		/*
		 * } catch(Exception e) { FacesMessage message = new
		 * FacesMessage(FacesMessage.SEVERITY_WARN,
		 * "Erreur requete pour operation sortie", e.getMessage());
		 * FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage(e.getMessage()));
		 * FacesContext.getCurrentInstance().addMessage(null, message); return null; }
		 */
	}
	public Long countMaterielByTypeValide(TypeMateriel typemat, Direction dir) {
		return matrepos.countByTypematerieladdAndDirecAndValidation(typemat, dir,true);
	}

	public Materiel entrerMateriel(OpEntree op) {
		if (op == null) {
			// System.out.println("-------FUCK ETO ARY EH----------");
		}
		List<Materiel> mat = op.getListMat();
		for (Materiel m : mat) {
			m.setValidation(true);
			m.setNumeroType(countMaterielByTypeValide(m.getTypematerieladd(), op.getDirection()));
			System.out.println("materiel m" + m.getDc());
			matrepos.save(m);
		}
		op.valider();
		op.generateNumEntree(operationdao.countOpEntreeByYearByDirection(new Date(), op.getDirection())+1);
		oprepos.save(op);
		return null;
	}
	
	public Materiel MajMateriel(Materiel m) {
		m = matrepos.save(m);
		return m;
	}

	@Override
	public Materiel sortirMateriel(OpSortie sortie) throws Exception {
		// TODO Auto-generated method stub
		// try{
		Materiel m = sortie.getMat();
		if (m.getDetenteur() != null) {
			System.out.println("DETENU");
			throw new Exception("Matériel déjà détenu");
		}
		if(m.getDirec()==null) {
			System.out.println("NON VALIDE");
			throw new Exception("Matériel n'est plus valide");
		}
		m.setDirec(null); //Change: all materiel with no direction are sortie
		matrepos.save(m);

		sortie.valider();
		sortie.generateNumSortie(operationdao.countOpSortieByYearByDirection(new Date(), sortie.getDirection())+1);
		oprepos.save(sortie);
		return m;
	}

	/*
	 * Old function
	 * 
	 * @Override public Materiel attriuberMateriel(Long idMat, Long im) { // TODO
	 * Auto-generated method stub Materiel m = (Materiel) matrepos.findOne(idMat);
	 * Agent detenteur = (Agent) agentrepos.findOne(im); m.setDetenteur(detenteur);
	 * matrepos.save(m); return m; }
	 */

	@Override
	public Materiel attriuberMateriel(OpAttribution attr) throws Exception {
		return operationdao.attribuerMat(attr);
	}

	@Override
	public Materiel attribuerMaterielEx(MaterielEx matex, Agent detenteur) throws Exception {
		// TODO Auto-generated method stub
		return operationdao.attribuerMatEx(matex, detenteur);
	}

	/*
	 * @Override public Materiel dettacherMateriel(Materiel m) { // TODO
	 * Auto-generated method stub m.setDetenteur(null); matrepos.save(m); return
	 * null; }
	 */

	@Override
	public void delMat(Materiel m) {
		// TODO Auto-generated method stub
		matrepos.delete(m);
	}

	/*
	 * Affichage
	 */
	@Override
	public void seeMat(Materiel m) {
		// TODO Auto-generated method stub
		ModeAcquisition ma = null;
		Financement fi = null;
		Fournisseur f = null;
		Double mont = 0d;
		String refFact = null;

		if (m instanceof MaterielNouv) {
			ma = ((MaterielNouv) m).getModAcq();
			fi = ((MaterielNouv) m).getFinancement();
			f = ((MaterielNouv) m).getFournisseur();
			mont = ((MaterielNouv) m).getMontant_facture();
			refFact = ((MaterielNouv) m).getRefFacture();
		}
		String detenteur = "aucun";
		if (m.getDetenteur() != null) {
			detenteur = m.getDetenteur().getNomAgent();
		}
		System.out.println("MATERIEL:");
		System.out.println("--------");
		System.out.println("Type| Nomenclature| marque | pu| ref| numSerie | caract | detenteur | autre |"
				+ "|Etat | Mode Acqui | Financement | Montant | ref Fact | Fournisseur| :");
		System.out.println(m.getCaract() + "|" + m.getNomenMat() + "|" + m.getMarque() + "|" + m.getPu() + "|"
				+ m.getReference() + "|" + m.getNumSerie() + "|" + "XX" + "|" + detenteur + "|" + m.getAutre() + "|"
				+ m.getEtat() + "|" + ma + "|" + fi + "|" + mont + "|" + refFact + "|" + f);

	}

	@Override
	public void seeAgent(Agent a) {
		// TODO Auto-generated method stub
		System.out.println("");

	}

	@Override
	public OpAttribution reqAttribution(Materiel m, Agent oper, Agent detenteur) throws Exception {
		// TODO Auto-generated method stub
		if(detenteur==null) {
			throw new Exception("Détenteur non valide");
		}
		if (!m.isValidation()) {
			throw new Exception("Matériel non validé");
		}
		OpAttribution attroper = new OpAttribution(new Date(), new Date(), oper.getIp(), oper, m, detenteur);
		oprepos.save(attroper);
		return attroper;
	}

	@Override
	public OpEntree reqMatAModifier(OpEntree entree, String motif) throws Exception {
		// TODO Auto-generated method stub
		for (Materiel m : entree.getListMat()) {
			if (m.isValidation()) {
				throw new Exception("Matériel déjà validé");
			}
		}

		entree.amodifier(motif);
		oprepos.save(entree);
		return entree;
	}

	@Override
	public OpSortie reqSortirAModifier(OpSortie sort, String motif) {
		// TODO Auto-generated method stub
		sort.amodifier(motif);
		oprepos.save(sort);
		return sort;
	}

	public OpEntree reqMatRefuser(OpEntree entree, String motif) throws Exception {
		// TODO Auto-generated method stub
		// try {
		for (Materiel m : entree.getListMat()) {
			if (m.isValidation()) {
				throw new Exception("Matériel déjà validé");
			}
		}

		entree.arefuser(motif);
		oprepos.save(entree);
		return entree;
		/*
		 * } catch(Exception e) { FacesMessage message = new
		 * FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur pour attribution de sortie",
		 * e.getMessage()); FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage(e.getMessage()));
		 * FacesContext.getCurrentInstance().addMessage(null, message); return null; }
		 */
	}

	@Override
	public OpSortie reqSortirRefuser(OpSortie sortie, String string) {
		// TODO Auto-generated method stub
		sortie.arefuser(string);
		oprepos.save(sortie);
		return sortie;
	}

	@Override
	public OpAttribution reqAttrAModifier(OpAttribution attr, String motif) throws Exception {
		// TODO Auto-generated method stub
		// try{
		if (attr.getMat().getDetenteur() != null) {
			throw new Exception("Matériel déjà détenu");
		}
		attr.amodifier(motif);
		oprepos.save(attr);
		return attr;
		/*
		 * } catch(Exception e) { FacesMessage message = new
		 * FacesMessage(FacesMessage.SEVERITY_WARN,
		 * "Erreur de modficiation d'attribution", e.getMessage());
		 * FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage(e.getMessage()));
		 * FacesContext.getCurrentInstance().addMessage(null, message); return null; }
		 */
	}

	@Override
	public OpAttribution reqAttrRefuser(OpAttribution attr, String motif) throws Exception {
		// TODO Auto-generated method stub
		// try{
		/*if (attr.getMat().getDetenteur() != null) {
			throw new Exception("Materiel deja detenu");
		}*/
		if(motif==null) {
			motif="";
		}
		attr.arefuser(motif);
		oprepos.save(attr);
		return attr;
		/*
		 * } catch(Exception e) { FacesMessage message = new
		 * FacesMessage(FacesMessage.SEVERITY_WARN,
		 * "Erreur de requete pour refus d'attribution", e.getMessage());
		 * FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage(e.getMessage()));
		 * FacesContext.getCurrentInstance().addMessage(null, message); return null; }
		 */
	}

	@Override
	public OpDettachement reqDettachement(Materiel mat1, Agent oper, Agent dete, MotifDecharge m) throws Exception {
		// TODO Auto-generated method stub
		// try{
		if(dete==null) {
			throw new Exception("Détenteur non valide");
		}
		if (mat1.getDetenteur() == null) {
			throw new Exception("aucun");
		}
		OpDettachement opdet = new OpDettachement(new Date(), new Date(), oper.getIp(), oper, mat1, dete);
		opdet.setMotifDettachement(m);;
		oprepos.save(opdet);
		return opdet;
		/*
		 * } catch(Exception e) { FacesMessage message = new
		 * FacesMessage(FacesMessage.SEVERITY_WARN,
		 * "Erreur pour requete de detachement", e.getMessage());
		 * FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage(e.getMessage()));
		 * FacesContext.getCurrentInstance().addMessage(null, message); return null; }
		 */
	}

	@Override
	public Agent detacherMateriel(OpDettachement det) throws Exception {
		// TODO Auto-generated method stub
		/*
		 * Agent ancienDet = det.getMat().getDetenteur(); Materiel m = det.getMat();
		 * ancienDet.getMatdetenu().remove(m); agentrepos.save(ancienDet);
		 * det.valider(); oprepos.save(det);
		 * 
		 * return ancienDet;
		 */
		// try {
		return operationdao.detacherMat(det);
		/*
		 * } catch(Exception e) { FacesMessage message = new
		 * FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur de detachement materiel",
		 * e.getMessage()); FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage(e.getMessage()));
		 * FacesContext.getCurrentInstance().addMessage(null, message); return null; }
		 */
	}

	@Override
	public OpDettachement reqDetRefuser(OpDettachement det, String string) {
		// TODO Auto-generated method stub
		det.arefuser(string);
		oprepos.save(det);
		return det;
	}

	public IOperationDAO getOperationdao() {
		return operationdao;
	}

	public void setOperationdao(IOperationDAO operationdao) {
		this.operationdao = operationdao;
	}

	/**
	 *
	 * GETTERS
	 */
	@Override
	public Materiel getMatById(Long idmat) {
		// TODO Auto-generated method stub
		return matrepos.findByIdMateriel(idmat);
	}

	@Override
	public List<Materiel> getListMatByDet(Agent detenteur) {
		// TODO Auto-generated method stub
		return matrepos.findByDetenteur(detenteur);
		// return null;
	}

	@Override
	public List<ArticleEx> getListArticleEx(Direction d) {
		// TODO Auto-generated method stub
		return (List<ArticleEx>) artexreops.findTop200ByDirecArtOrderByIdArticleDesc(d);
		// return null;
	}

	@Override
	public List<Operation> getListOp() {
		// TODO Auto-generated method stub
		return oprepos.findAll();
	}

	@Override
	public List<OpEntree> getListOpEntree() {
		// TODO Auto-generated method stub

		return opentreerepos.findAll();
	}

	@Override
	public List<OpSortie> getListOpSortie() {
		// TODO Auto-generated method stub
		return opsortierepos.findAll();
	}

	@Override
	public List<Operation> getListOpByOperator(Agent operator) {
		// TODO Auto-generated method stub
		// return oprepos.findByOperateur(operator);
		int maxresult = 2000;
		return operationdao.getListOperationByOperator(operator, maxresult);
	}

	@Override
	public List<OpEntree> getListOpEntreeByOperator(Agent operator) {
		// TODO Auto-generated method stub
		return opentreerepos.findByOperateur(operator);
	}

	@Override
	public List<OpSortie> getListOpSortieByOperator(Agent operator) {
		// TODO Auto-generated method stub
		return opsortierepos.findByOperateur(operator);
	}

	@Override
	public List<Operation> getListOpByDirection(Direction direction) {
		// TODO Auto-generated method stub
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		Date sdate = new GregorianCalendar(year-2, Calendar.JANUARY, 1).getTime();
        Date edate = new GregorianCalendar(year+1, Calendar.DECEMBER, 30).getTime();
		return oprepos.findByDirectionAndDateBetweenOrderByIdDesc(direction, sdate, edate);
	}

	@Override
	public List<OpEntree> getListOpEntreeByDirection(Direction direction, Date sdate, Date edate) {
		// TODO Auto-generated method stub
		return opentreerepos.findByDirectionAndDateBetweenOrderByIdDesc(direction,sdate,edate);
	}

	@Override
	public List<OpSortie> getListOpSortieByDirection(Direction direction, Date sdate, Date edate) {
		// TODO Auto-generated method stub
		return opsortierepos.findByDirectionAndDateBetweenOrderByIdDesc(direction,sdate,edate);
	}

	@Override
	public List<Materiel> getListMatByNom(Nomenclature nomenclature) {
		// TODO Auto-generated method stub
		return matrepos.findByNomenMat(nomenclature);
	}

	@Override
	public List<Materiel> getListMatByDirection(Direction direction) {
		// TODO Auto-generated method stub
		return matrepos.findByDirec(direction);
	}

	@Override
	public List<Operation> getListOpBetween(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		int maxresult = 100;
		return operationdao.getListOpByDate(startDate, endDate, maxresult);
	}

	@Override
	public List<OpEntree> getListOpEntreeByMat(Materiel m) {
		// TODO Auto-generated method stub
		return opentreerepos.findByMat(m);
	}

	@Override
	public List<OpSortie> getListOpSortieByMat(Materiel m) {
		// TODO Auto-generated method stub
		return opsortierepos.findByMat(m);
	}

	@Override
	public List<OpEntree> getListOpEntreeByMatBDate(Materiel m, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		int maxresult = 100;
		return operationdao.getListOpEntreeByByMaterielBDate(m, startDate, endDate, maxresult);
	}

	@Override
	public List<OpSortie> getListOpSortieByMatBDate(Materiel m, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		int maxresult = 100;
		return operationdao.getListOpSortieByByMaterielBDate(m, startDate, endDate, maxresult);
	}

	@Override
	public List<Operation> getListOpEntreeAndSortieByDirectionByYearByDateAsc(Direction d, Date startDate,
			Date endDate) {
		return operationdao.getListOpEntreeAndSortieByDirectionByYearByDateAsc(d, startDate, endDate);
	}

	@Override
	public List<Materiel> getListMat() {
		// TODO Auto-generated method stub
		return (List<Materiel>) matrepos.findAll();
	}

	@Override
	public List<MaterielEx> getListMatEx(Direction d) {
		// return (List<MaterielEx>) materielExRepository.findAll();
		
		System.out.println("gegt list materiel by direction");
		return (List<MaterielEx>) materielExRepository.findByDirecOrderByIdMaterielDesc(d);
	}

	@Override
	public List<MaterielNouv> getListMatNouv() {
		return (List<MaterielNouv>) materielNouvRepository.findAll();
	}

	@Override
	public List<Materiel> getMatByValidation(boolean validation) {
		return matrepos.findByValidation(validation);
	}

	@Override
	public List<Materiel> getMatByDetenteurAndValidation(Agent detenteur, boolean validation) {
		return matrepos.findByDetenteurAndValidation(detenteur, validation);
	}

	@Override
	public List<Materiel> getMatByDetenteurAndDirection(Agent detenteur, Direction direction) {
		return matrepos.findByDetenteurAndDirec(detenteur, direction);
	}
	@Override
	public List<Materiel> getMatByValidationAndDetenteurAndDirection(boolean val,Agent detenteur, Direction direction) {
		return matrepos.findByValidationAndDetenteurAndDirec(val,detenteur, direction);
	}

	@Override
	public List<Agent> listAgentByDirection(Direction direction) {
		// TODO Auto-generated method stub
		return agentrepos.findByDirectionAndActive(direction,true);
	}

	@Override
	public List<OpAttribution> getListOpAttribution() {
		// TODO Auto-generated method stub
		return opattrrepos.findAll();
	}

	@Override
	public List<OpDettachement> getListOpDettachement() {
		// TODO Auto-generated method stub
		return opdettrepos.findAll();
	}

	@Override
	public List<OpAttribution> getListOpAttrByOperator(Agent operator) {
		// TODO Auto-generated method stub
		return opattrrepos.findByOperateur(operator);
	}

	@Override
	public List<OpDettachement> getListOpDettByOperatort(Agent operator) {
		// TODO Auto-generated method stub
		return opdettrepos.findByOperateur(operator);
	}

	@Override
	public List<OpAttribution> getListOpAttrByDirection(Direction direction,Date sdate, Date edate) {
		// TODO Auto-generated method stub
		return opattrrepos.findByDirectionAndDateBetweenOrderByIdDesc(direction,sdate,edate);
	}
	
	@Override
	public List<OpAttribution> getListOpAttrByValideByDirection(Direction direction, Date sdate, Date edate,
			EtatOperation e) {
		// TODO Auto-generated method stub
		return opattrrepos.findByDirectionAndStateAndDateBetweenOrderByIdDesc(direction,e,sdate,edate);
	}
	

	@Override
	public List<OpDettachement> getListOpDettByDirection(Direction direction,Date sdate, Date edate) {
		// TODO Auto-generated method stub
		return opdettrepos.findByDirectionAndDateBetweenOrderByIdDesc(direction,sdate,edate);
	}

	@Override
	public List<OpAttribution> getListOpAttrByMat(Materiel m) {
		// TODO Auto-generated method stub
		return opattrrepos.findByMat(m);
	}

	@Override
	public List<OpDettachement> getListOpDettByMat(Materiel m) {
		// TODO Auto-generated method stub
		return opdettrepos.findByMat(m);
	}

	@Override
	public List<OpEntree> getListOpEntreeByMatAndByState(Materiel m, EtatOperation e) {
		return opentreerepos.findByMatAndState(m, e);
	}

	@Override
	public List<OpSortie> getListOpSortieByMatAndByState(Materiel m, EtatOperation e) {
		return opsortierepos.findByMatAndState(m, e);
	}

	@Override
	public List<OpAttribution> getListOpAttrByMatAndByState(Materiel m, EtatOperation e) {
		return opattrrepos.findByMatAndState(m, e);
	}

	@Override
	public List<OpDettachement> getListOpDettByMatAndByState(Materiel m, EtatOperation e) {
		return opdettrepos.findByMatAndState(m, e);
	}

	@Override
	public CodeArticle addCodeArticle(CodeArticle codeArticle) {
		// TODO Auto-generated method stub
		return codeartrepos.save(codeArticle);
	}

	@Override
	public void removeCodeArticle(CodeArticle codearticle) {
		// TODO Auto-generated method stub
		codeartrepos.delete(codearticle);
	}

	@Override
	public List<CodeArticle> listCodeArticle() {
		// TODO Auto-generated method stub
		return codeartrepos.findAll();
	}

	@Override
	public List<CodeArticle> listCodeArticleByTypeObj(TypeObjet typeObj) {
		// TODO Auto-generated method stub
		return codeartrepos.findByTypeObjet(typeObj);
	}

	@Override
	public OpEntreeArticle reqEntrerArticle(Article article, Agent dc) {
		// TODO Auto-generated method stub
		article.setDc(dc);
		System.out.println("First Save article");
		article = artreops.save(article);
		
		OpEntreeArticle entreeart = new OpEntreeArticle(new Date(), new Date(), dc.getIp(), dc, article);
		System.out.println("second Save article");
		opentreeartrepos.save(entreeart);
		
		return entreeart;

	}

	@Override
	public OpSortieArticle reqSortirArticle(Article article, Agent op, Agent destinataire, Long nbr, String decision) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// try {
		if (!article.isValidation()) {
			throw new Exception("Requête non validée");
		}
		OpSortieArticle opsortieart = new OpSortieArticle(new Date(), new Date(), op.getIp(), op, article,
				destinataire);
		opsortieart.setNombreToS(nbr);
		opsortieart.setDecision(decision);
		opsortieartrepos.save(opsortieart);
		return opsortieart;
		/*
		 * } catch(Exception e) { FacesMessage message = new
		 * FacesMessage(FacesMessage.SEVERITY_WARN,
		 * "Erreur de requete de sortie materiel", e.getMessage());
		 * FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage(e.getMessage()));
		 * FacesContext.getCurrentInstance().addMessage(null, message); return null; }
		 */
	}

	@Override
	public OpEntreeArticle reqArtAModifier(OpEntreeArticle entreeArt, String motif) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// try{
		if (entreeArt.getArticle().isValidation()) {
			throw new Exception("Requête deja validée");
		}
		entreeArt.amodifier(motif);
		opentreeartrepos.save(entreeArt);
		return entreeArt;
		/*
		 * } catch(Exception e) { FacesMessage message = new
		 * FacesMessage(FacesMessage.SEVERITY_WARN,
		 * "Erreur de requete d'article à modifier", e.getMessage());
		 * FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage(e.getMessage()));
		 * FacesContext.getCurrentInstance().addMessage(null, message); return null; }
		 */
	}

	@Override
	public OpSortieArticle reqSortirArtAModifier(OpSortieArticle sortArt, String motif) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// try {
		if (sortArt.getArticle().isValidation()) {
			throw new Exception("déjà valider");
		}
		sortArt.amodifier(motif);
		opsortieartrepos.save(sortArt);
		return sortArt;
		/*
		 * } catch(Exception e) { FacesMessage message = new
		 * FacesMessage(FacesMessage.SEVERITY_WARN,
		 * "Erreur de requete de sortie d'article à modifier", e.getMessage());
		 * FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage(e.getMessage()));
		 * FacesContext.getCurrentInstance().addMessage(null, message); return null; }
		 */
	}

	@Override
	public OpEntreeArticle reqArtRefuser(OpEntreeArticle entreeArt, String motif) throws Exception {
		// TODO Auto-generated method stub
		// try{
		if (entreeArt.getArticle().isValidation()) {
			throw new Exception("déjà valider");
		}
		entreeArt.arefuser(motif);
		opentreeartrepos.save(entreeArt);
		return entreeArt;
		/*
		 * } catch(Exception e) { FacesMessage message = new
		 * FacesMessage(FacesMessage.SEVERITY_WARN,
		 * "Erreur de requete d'article à refuser", e.getMessage());
		 * FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage(e.getMessage()));
		 * FacesContext.getCurrentInstance().addMessage(null, message); return null; }
		 */
	}

	@Override
	public OpSortieArticle reqSortirRefuser(OpSortieArticle sortArt, String motif) throws Exception {
		// TODO Auto-generated method stub
		// try{
		if (!sortArt.getArticle().isValidation()) {
			throw new Exception("Requête déjà validée");
		}
		sortArt.arefuser(motif);
		opsortieartrepos.save(sortArt);
		return sortArt;
		/*
		 * } catch(Exception e) { FacesMessage message = new
		 * FacesMessage(FacesMessage.SEVERITY_WARN,
		 * "Erreur de requete, sortie de matériel refusée", e.getMessage());
		 * FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage(e.getMessage()));
		 * FacesContext.getCurrentInstance().addMessage(null, message); return null; }
		 */
	}

	@Override
	public Article entrerArticle(OpEntreeArticle opentreeart) {
		// TODO Auto-generated method stub
		Article a = opentreeart.getArticle();
		a.setValidation(true);
		artreops.save(a);
		opentreeart.valider();
		opentreeartrepos.save(opentreeart);
		return a;
	}

	@Override
	public Article sortirArticle(OpSortieArticle sortieart) throws Exception {
		// TODO Auto-generated method stub
		// try{
		Article a = sortieart.getArticle();

		Agent beneficiaire = sortieart.getBeneficiaire();
		a.setBeneficiaire(beneficiaire);
		a.setValidation(true);// For test
		artreops.save(a);
		sortieart.valider();
		// oprepos.save(attr);
		opsortieartrepos.save(sortieart);
		return a;
		/*
		 * } catch(Exception e) { FacesMessage message = new
		 * FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur de sortie d'article",
		 * e.getMessage()); FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage(e.getMessage()));
		 * FacesContext.getCurrentInstance().addMessage(null, message); return null; }
		 */
	}

	@Override
	public List<ArticleNouv> getListAllArticleNouv() {
		return artnouvreops.findAll();
	}

	@Override
	public List<Article> getListAllArticle() {
		return artreops.findAll();
	}

	@Override
	public ArticleNouv addArticleNouv(CodeArticle cde, Agent ben, Agent depo, Fournisseur fourn, Double prix,
			Long nombre, Marque marqueArt, String caraArt) {
		ArticleNouv a = new ArticleNouv(fourn, prix);
		a.setCodeArticle(cde);
		a.setBeneficiaire(ben);
		a.setDc(depo);
		a.setNombre(nombre);
		a.setMarqueArticle(marqueArt);
		a.setCaracteristiqueArticle(caraArt);

		artnouvreops.save(a);
		return a;
	}

	@Override
	public ArticleEx addArticleEx(CodeArticle cde, Agent ben, Agent depo, Double prix, Long nombre, Marque marqueArt,
			String caraArt) {
		ArticleEx a = new ArticleEx();
		a.setCodeArticle(cde);
		a.setBeneficiaire(ben);
		a.setDc(depo);
		a.setNombre(nombre);
		artexreops.save(a);
		return a;
	}

	@Override
	public List<MaterielNouv> getListMaterielNouvValide(Direction d) {
		// TODO Auto-generated method stub
		// return materielNouvRepository.findByValidation(true);
		
		return materielNouvRepository.findByValidationAndDirecOrderByIdMaterielDesc(true, d);
		// return materielNouvRepository.findByValidationAndAModifier(true, true);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
	public OpEntree getOperationEntreeById(Long idopentree) {
		// TODO Auto-generated method stub

		return opentreerepos.findOne(idopentree);
		// okay
	}

	@Override
	public List<OpEntree> getListOpEntreeByDirectionByYearByDateAsc(Direction d, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return operationdao.getListOpEntreeByDirectionByYearByDateAsc(d, startDate, endDate);
	}

	@Override
	public List<OpSortie> getListOpSortieByDirectionByYearByDateAsc(Direction d, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return operationdao.getListOpSortieByDirectionByYearByDateAsc(d, startDate, endDate);
	}

	@Override
	public List<Operation> getListOperationByDirectionByYearByDateAsc(Direction d, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return operationdao.getListOperationByDirectionByYearByDateAsc(d, startDate, endDate);
	}
	
	
	@Override
	public List<Operation> getListAllOperationByYearByDateAsc(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return operationdao.getListAllOperationByYearByDateAsc(startDate, endDate);
	}
	
	
	@Override
	public List<Operation> getListAllOperationByDirectionByYearByDateAsc(Direction d, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return operationdao.getListAllOperationByDirectionByYearByDateAsc(d, startDate, endDate);
	}

	@Override
	public List<Article> getListArticleValideByDirection(Direction d) {
		// TODO Auto-generated method stub
		return artreops.findByValidationAndDirecArt(true, d);
	}

	@Override
	public List<Article> getListArticleNonDetenuValideByDirection(Direction d) {
		// TODO Auto-generated method stub
		return artreops.findByValidationAndBeneficiaireAndDirecArt(true, null, d);
	}

	@Override
	public List<Article> getListArticleByDetenteurByValida(boolean valide, Agent detenteur) {
		// TODO Auto-generated method stub
		return artreops.findByValidationAndBeneficiaire(valide, detenteur);
	}

	@Override
	public List<Article> getListArticleByValidationByDirection(boolean valide, Direction d) {
		// TODO Auto-generated method stub
		List<Article> lesart = artreops.findByValidationAndDirecArt(valide, d);
		Collections.sort(lesart, new Comparator<Article>() {  
		    @Override  
		    public int compare(Article a1, Article a2) {  
		        
		        return new CompareToBuilder().append(a1.getCodeArticle().getTypeObjet().getDesignation(), a2.getCodeArticle().getTypeObjet().getDesignation()).
		        		append(a1.getCodeArticle().getDesignation(), a2.getCodeArticle().getDesignation()).append(a1.getIdArticle(), a2.getIdArticle()).toComparison();
		    	  
		    }  
		});
		return lesart;
	}

	@Override
	public List<OpEntreeArticle> getListOpEntreeArtByValideByDirection(EtatOperation etat, Direction direction,
			Date startDate, Date endDate) {
		// TODO Auto-generated method stub

		return operationdao.getListOpEntreeArtByValideByDirection(etat, direction, startDate, endDate);
	}

	@Override
	public List<OpSortieArticle> getListOpSortieArtByValideByDirection(EtatOperation etat, Direction direction,
			Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return operationdao.getListOpSortieArtByValideByDirection(etat, direction, startDate, endDate);
	}

	@Override
	public List<ArticleNouv> getListArtNouvValideByDirection(Direction d) {
		// TODO Auto-generated method stub
		return artnouvreops.findByValidationAndDirecArt(true, d);
	}

	@Override
	public List<ArticleNouv> getListArtNouvByValidationByDirection(boolean val, Direction d) {
		// TODO Auto-generated method stub
		return artnouvreops.findTop200ByValidationAndDirecArtOrderByIdArticleDesc(val, d);
	}

	@Override
	public List<ArticleEx> getListArtExByDirction(Direction d) {
		// TODO Auto-generated method stub
		return artexreops.findByDirecArt(d);
	}

	@Override
	public Article getArticleById(Long id) {
		// TODO Auto-generated method stub
		return artreops.findOne(id);
	}

	@Override
	public List<OpEntreeArticle> getListOpEntreeArtByDirection(Direction direction, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return operationdao.getListOpEntreeArtByDirection(direction, startDate, endDate);
	}

	@Override
	public List<OpSortieArticle> getListOpSortieArtByDirection(Direction direction, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return operationdao.getListOpSortieArtByDirection(direction, startDate, endDate);
	}

	@Override
	public List<OperationES> getListOpESForJournal(Direction direction, Date sdate, Date edate) {
		// TODO Auto-generated method stub
		return opesrepos.findByDirectionAndStateAndDateBetween(direction, EtatOperation.ACCEPTED,sdate, edate);
		//return null;
	}

	@Override
	public List<OpSortie> getListOpSortieValideByDirection(Direction direction) {
		// TODO Auto-generated method stub
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		Date sdate = new GregorianCalendar(year-2, Calendar.JANUARY, 1).getTime();
        Date edate = new GregorianCalendar(year+1, Calendar.DECEMBER, 30).getTime();
		return opsortierepos.findByDirectionAndStateAndDateBetweenOrderByIdDesc(direction, EtatOperation.ACCEPTED,sdate,edate);
	}

	@Override
	public List<Object[]> getListObjectForinvetaire(Direction d, Date startDate, Date endDate){
		//return operationdao.getListForInventaire(d, new Date(), new Date());
		return operationdao.getListForInventaireWithMatex(d, startDate, endDate);
	}

	@Override
	public void entrerMaterielExistant(Designation des, List <MaterielEx> matexs, Agent dc) {
		// TODO Auto-generated method stub
		//des.setNombreparEntree(matexs.size()); Not now
		Designation d =desrepos.save(des);
		for(Materiel matex:matexs) {
			matex.setDesign(d);
			matex.setDc(dc);
			matex.setValidation(true);
			matex.setNumeroType(countMaterielByTypeValide(matex.getTypematerieladd(), matex.getDirec()));
			matrepos.save(matex);
			
		}
		OpSaisie saisirefmatex = new OpSaisie(new Date(), new Date(), dc.getIp(), dc, "Matex",matexs.get(0).getIdMateriel());
		saisirefmatex.valider();
		oprepos.save(saisirefmatex);
		
	}
	
	@Override
	public OpEntree reqEntrerMaterielNouv(Designation des,List<Materiel> l, Agent dc, String facturePath, String refFacture) {
		// TODO Auto-generated method stub*
		System.out.println("let entrer materiel");
		OpEntree entree = new OpEntree(new Date(), new Date(), dc.getIp(), dc);
		entree.setPathDoc(facturePath);
		entree.setRefFact(refFacture);
		// entree.setListMat(l);
		//set nombre pendant entree
		//des.setNombreparEntree(l.size()); Not now
		Designation d =desrepos.save(des);
		for (Materiel m : l) {
			m.setDc(dc);
			m.setDesign(d);
			entree.addMateriel(m);
		}
		//
		entree = opentreerepos.save(entree);
		/*
		 * MaterielEx ma= new MaterielEx(); ma = matrepos.save(ma);
		 */
		// m = materielExRepository.save((MaterielEx) m);
		return entree;
	}

	@Override
	public OpEntree reqEntrerMaterielNouv(Map<Designation, List<MaterielNouv>> mappingdeslistmat, Agent dc,
			String facturePath, String refFacture) {
		// TODO Auto-generated method stub
		
		System.out.println("let entrer materiel and their designation");
		OpEntree entree = new OpEntree(new Date(), new Date(), dc.getIp(), dc);
		entree.setPathDoc(facturePath);
		entree.setRefFact(refFacture);
		/*
		 * Parcourir hashmap
		 * save desingation
		 * set designation to materiel
		 * save materiel (possible tsy ilaina)
		 * set materiel to entree
		 */
		Iterator<Map.Entry<Designation, List<MaterielNouv>>> iterator = mappingdeslistmat.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Designation, List<MaterielNouv>> entry = iterator.next();
            System.out.printf("Key : %s and Value: %s %n", entry.getKey(), 

                                                           entry.getValue());
            
            Designation des = entry.getKey();
            List<MaterielNouv> listmatn = entry.getValue();
            //des.setNombreparEntree(listmatn.size()); Not now
            Designation d =desrepos.save(des);
            for(MaterielNouv m:listmatn) {
            	m.setDc(dc);
    			m.setDesign(d);
    			entree.addMateriel(m);
            }
            iterator.remove(); // right way to remove entries from Map, 

                               // avoids ConcurrentModificationException
        }
		entree = opentreerepos.save(entree);
		//sss
		return null;
	}

	@Override
	public List<Object[]> listDesignationByOperationEntree(OpEntree operationentree) {
		// TODO Auto-generated method stub
		if(operationentree ==null) {
			System.out.println("operation entree null");
			return null;
		}
		List<Object[]> a =  matrepos.getDesignationByOpEntree(operationentree);
		for(Object[] o:a) {
			Long id = (Long)o[0];
			o[0]= desrepos.findOne(id);
		}
		
		return a;
	}

	@Override
	public List<OpEntree> listOpentreeByStateByDirection(EtatOperation etat, Direction d) {
		// TODO Auto-generated method stub
		//this.direction = cur.getDirection();
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		Date sdate = new GregorianCalendar(year - 3, Calendar.JANUARY, 1).getTime();
		Date edate = new GregorianCalendar(year + 1, Calendar.DECEMBER, 30).getTime();
		
		return opentreerepos.findByStateAndDirectionAndDateBetweenOrderByIdDesc(etat, d,sdate,edate);
	}

	@Override
	public Materiel updateMateriel(Materiel m) {
		// TODO Auto-generated method stub
		desrepos.save(m.getDesign());
		return matrepos.save(m);
	}

	@Override
	public void updateArticle(Article art) {
		// TODO Auto-generated method stub
		artreops.save(art);
	}

	@Override
	public Long calculArticleRestant(Article article) {
		// TODO Auto-generated method stub
		if(article == null) {
			return 0L;
		}
		Long nombreSortie = (Long)opsortieartrepos.getNumberOfSortieByArticle(article
				, EtatOperation.ACCEPTED
				);
		System.out.println("Nombre :"+nombreSortie);
		if(nombreSortie == null) {
			nombreSortie = 0L;
		}
		return article.getNombre() -nombreSortie;
	}

	@Override
	public List<Operation> getListOpESArtValideByDirection(Direction direction, Date startDate,
			Date endDate) {
		// TODO Auto-generated method stub
		return operationdao.getListOpESArtByValideByDirection(EtatOperation.ACCEPTED, direction, startDate, endDate);
	}

	@Override
	public List<Operation> getListOpESArtValideByDirectionByCod(CodeArticle codeart, Direction direction,
			Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return operationdao.getListOpESArtByValideByDirectionByCod(EtatOperation.ACCEPTED, 
				direction, startDate, endDate,codeart);
		
	}

	@Override
	public Long getAreporter(CodeArticle code, Direction direction, Date stopdat) {
		// TODO Auto-generated method stub
		
		return operationdao.areporter(code,direction,stopdat);
	}

	@Override
	public List<MaterielNouv> getListMaterielNouvNonValide(Direction d) {
		// TODO Auto-generated method stub
		if(d==null) {
			Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
			d= agent.getDirection();
		}
		
		return materielNouvRepository.findByValidationAndDirecOrderByIdMaterielDesc(false, d);
	}

	


}
