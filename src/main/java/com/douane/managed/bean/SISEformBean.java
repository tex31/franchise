package com.douane.managed.bean;

import com.douane.entite.*;
import com.douane.exception.GetFieldName;
import com.douane.exception.NullPointerAttributeException;
import com.douane.metier.referentiel.IRefMetier;
import com.douane.metier.user.IUserMetier;
import com.douane.metier.utilisateur.IUtilisateurMetier;
import com.douane.model.EtatOperation;
import com.douane.requesthttp.RequestFilter;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import java.sql.SQLException;

import java.util.*;

/**
 * Created by hasina on 10/25/17.
 */
@ManagedBean(name = "siseBean")
@RequestScoped
public class SISEformBean {
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{usermetier}")
	IUserMetier usermetierimpl;

	@ManagedProperty(value = "#{refmetier}")
	IRefMetier refmetierimpl;

	@ManagedProperty(value = "#{utilisateurmetier}")
	IUtilisateurMetier utilisateurmetierimpl;

	private String designation = null;
	private String nomenclature = null;
	private Nomenclature nomen;
	

	public Nomenclature getNomen() {
		return nomen;
	}

	public void setNomen(Nomenclature nomen) {
		this.nomen = nomen;
	}

	public Marque getMarque() {
		return marque;
	}

	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	private TypeMateriel tymat;
	private EtatMateriel etmat;
	private Marque marque;

	private String name;
	private String username;
	private String password;
	private Long im;
	private String role;
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private ModeAcquisition modeAcquisition;
	private MotifDecharge motifDecharge;
	private Financement financement;

	private Fournisseur fournisseur;

	private String codeBureau;
	private String codeService;
	private String codeDirection;

	/*
	 * private String adresse; private String localite;
	 * 
	 * private String motifSortie; private String poste; private String service;
	 * direction; bureau;
	 */

	// List des objets
	
	private List<Nomenclature> listNomenclature;
	private List<Direction> listDirection;
	private List<EtatMateriel> listEtatMateriel;
	private List<Financement> listFinancement;
	private List<Fournisseur> listFournisseur;
	private List<Marque> listMarque;
	private List<ModeAcquisition> listModeAcquisition;
	private List<MotifDecharge> listMotifDecharge;
	private List<TypeMateriel> listTypeMateriel;

	
	private List<MotifSortie> listMotifSortie;
	private List<Poste> listPoste;
	
	private List<Useri> listUseri;
	private List<TypeObjet> listTypeObjet;

	public List<Direction> getListDirection() {
		if (listDirection == null) {
			ArrayList<Referentiel> r = (ArrayList<Referentiel>) refmetierimpl.listRef(new Direction());
			List<Direction> ds = new ArrayList<Direction>();
			for (Object d : r) {
				if (d instanceof Direction) {
					ds.add((Direction) d);
				} else {
					System.out.println("NOT INSTANCE");
				}
			}
			Collections.sort(ds, new Comparator<Direction>() {
			    @Override
			    public int compare(Direction d1, Direction d2) {
			        return d1.getCodeDirection().compareTo(d2.getCodeDirection());
			    }
			});
			
			listDirection = ds;
		}
		return listDirection;
	}

	public void setListDirection(List<Direction> listDirection) {
		this.listDirection = listDirection;
	}

	public List<EtatMateriel> getListEtatMateriel() {
		if (listEtatMateriel == null) {
			ArrayList<Referentiel> r = (ArrayList<Referentiel>) refmetierimpl.listRef(new EtatMateriel());
			List<EtatMateriel> ds = new ArrayList<EtatMateriel>();
			for (Object d : r) {
				if (d instanceof EtatMateriel) {
					ds.add((EtatMateriel) d);
				}
			}
			listEtatMateriel= ds;
		}
		return listEtatMateriel;
	}

	public void setListEtatMateriel(List<EtatMateriel> listEtatMateriel) {
		this.listEtatMateriel = listEtatMateriel;
	}

	public List<Financement> getListFinancement() {
		if (listFinancement == null) {
			ArrayList<Referentiel> r = (ArrayList<Referentiel>) refmetierimpl.listRef(new Financement());
			List<Financement> ds = new ArrayList<Financement>();
			for (Object d : r) {
				if (d instanceof Financement) {
					ds.add((Financement) d);
				}
			}
			listFinancement = ds;
		}
		return listFinancement;
	}

	public void setListFinancement(List<Financement> listFinancement) {
		this.listFinancement = listFinancement;
	}

	public List<Fournisseur> getListFournisseur() {
		if (listFournisseur == null) {
			ArrayList<Referentiel> r = (ArrayList<Referentiel>) refmetierimpl.listRef(new Fournisseur());
			List<Fournisseur> ds = new ArrayList<Fournisseur>();
			for (Object d : r) {
				if (d instanceof Fournisseur) {
					ds.add((Fournisseur) d);
				}
			}
			listFournisseur = ds;
		}
		return listFournisseur;
	}

	public void setListFournisseur(List<Fournisseur> listFournisseur) {
		this.listFournisseur = listFournisseur;
	}

	public List<Marque> getListMarque() {
		if (listMarque == null) {
			ArrayList<Referentiel> r = (ArrayList<Referentiel>) refmetierimpl.listRef(new Marque());
			List<Marque> ds = new ArrayList<Marque>();
			for (Object d : r) {
				if (d instanceof Marque) {
					ds.add((Marque) d);
				}
			}
			listMarque= ds;
		}
		return listMarque;
	}

	public void setListMarque(List<Marque> listMarque) {
		this.listMarque = listMarque;
	}

	public List<ModeAcquisition> getListModeAcquisition() {
		if (listModeAcquisition == null) {
			ArrayList<Referentiel> r = (ArrayList<Referentiel>) refmetierimpl.listRef(new ModeAcquisition());
			List<ModeAcquisition> ds = new ArrayList<ModeAcquisition>();
			for (Object d : r) {
				if (d instanceof ModeAcquisition) {
					ds.add((ModeAcquisition) d);
				}
			}
			listModeAcquisition = ds;
		}
		return listModeAcquisition;
	}

	public void setListModeAcquisition(List<ModeAcquisition> listModeAcquisition) {
		this.listModeAcquisition = listModeAcquisition;
	}

	public List<MotifDecharge> getListMotifDecharge() {
		if (listMotifDecharge == null) {
			ArrayList<Referentiel> r = (ArrayList<Referentiel>) refmetierimpl.listRef(new MotifDecharge());
			List<MotifDecharge> ds = new ArrayList<MotifDecharge>();
			for (Object d : r) {
				if (d instanceof MotifDecharge) {
					ds.add((MotifDecharge) d);
				}
			}
			listMotifDecharge = ds;
		}
		return listMotifDecharge;
	}

	public void setListMotifDecharge(List<MotifDecharge> listMotifDecharge) {
		this.listMotifDecharge = listMotifDecharge;
	}

	public List<TypeMateriel> getListTypeMateriel() {
		if (listTypeMateriel == null) {
			ArrayList<Referentiel> r = (ArrayList<Referentiel>) refmetierimpl.listRef(new TypeMateriel());
			List<TypeMateriel> ds = new ArrayList<TypeMateriel>();
			for (Object d : r) {
				if (d instanceof TypeMateriel) {
					ds.add((TypeMateriel) d);
				}
			}
			//sort ds by nomenclature and then by alpha designation
			Collections.sort(ds, new Comparator<TypeMateriel>() {  
			    @Override  
			    public int compare(TypeMateriel tm1, TypeMateriel tm2) {  
			        
			        return new CompareToBuilder().append(tm1.getNomenclaureParent().getNomenclature(), tm2.getNomenclaureParent().getNomenclature()).
			        		append(tm1.getDesignation(), tm2.getDesignation()).toComparison();
			    	  
			    }  
			});
			listTypeMateriel = ds;
		}
		return listTypeMateriel;
	}

	public void setListTypeMateriel(List<TypeMateriel> listTypeMateriel) {
		this.listTypeMateriel = listTypeMateriel;
	}

	
	public List<MotifSortie> getListMotifSortie() {
		if(listMotifSortie ==null) {
		ArrayList<Referentiel> r = (ArrayList<Referentiel>) refmetierimpl.listRef(new MotifSortie());
		List<MotifSortie> ds = new ArrayList<MotifSortie>();
		for (Object d : r) {
			if (d instanceof MotifSortie) {
				ds.add((MotifSortie) d);
			}
		}
		listMotifSortie= ds;
		}
		return listMotifSortie;
	}

	public void setListMotifSortie(List<MotifSortie> listMotifSortie) {
		this.listMotifSortie = listMotifSortie;
	}

	public List<Poste> getListPoste() {
		if (listPoste == null) {
			ArrayList<Referentiel> r = (ArrayList<Referentiel>) refmetierimpl.listRef(new Poste());
			List<Poste> ds = new ArrayList<Poste>();
			for (Object d : r) {
				if (d instanceof Poste) {
					ds.add((Poste) d);
				}
			}
			listPoste = ds;
		}
		return listPoste;
	}

	public void setListPoste(List<Poste> listPoste) {
		this.listPoste = listPoste;
	}

	public List<Useri> getListUseri() {

		return utilisateurmetierimpl.findAllUtilisateur();
	}

	public void setListUseri(List<Useri> listUseri) {
		this.listUseri = listUseri;
	}

	public String addNomenclature() {
		try {
			nomen = new Nomenclature(getDesignation(), getNomenclature());
			Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
			// check if there is nomenclature duplicate
			System.out.println("NOMENCLATURE ADD" + nomen.getDesignation());
			System.out.println("AGENT ADD" + agent.getNomAgent());

			refmetierimpl.addRef(nomen, agent);
			return SUCCESS;
		} catch (DataIntegrityViolationException ex) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Nomenclature unique ",
					getNomenclature() + " existe déjà");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "";
			// throw new DataIntegrityViolationException(getNomenclature()+ " already
			// exists");
		}
	}

	public String addTypeMateriel() {
		try {
			tymat = new TypeMateriel(getDesignation());
			tymat.setNomenclaureParent(this.getNomenclatureP());
			tymat.setCodeTypeMate(this.getCodeTypeMateriel());
			Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
			// check if there is nomenclature duplicate

			refmetierimpl.addRef(tymat, agent);
			return SUCCESS;
		} catch (DataIntegrityViolationException ex) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Code type of materiel  unique ",
					getCodeTypeMateriel() + " existe déjàs");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "";
			// throw new DataIntegrityViolationException(getCodeTypeMateriel()+ " already
			// exists");
		}
	}

	public String addEtatMateriel() throws NullPointerAttributeException, NoSuchMethodException {
		if (getDesignation() == null || getDesignation().equals("")) {

			throw new NullPointerAttributeException("", new GetFieldName(),
					this.getClass().getMethod("getDesignation"));

		}
		etmat = new EtatMateriel(getDesignation());
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		// check if there is nomenclature duplicate

		refmetierimpl.addRef(etmat, agent);
		return SUCCESS;
	}

	public String addMarque() {
		marque = new Marque(getDesignation().toUpperCase());
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		refmetierimpl.addRef(marque, agent);
		return SUCCESS;
	}

	public String addMarqueCA() {
		marque = new Marque(getDesignation().toUpperCase());
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		refmetierimpl.addRef(marque, agent);
		return SUCCESS;
	}

	public String addUser() {
		try {
			Agent user = new Agent();
			Useri useri = new Useri();
			// user.setName(getName());

			user.setNomAgent(getName());

			// user.setUsername(getUsername());

			user.setPrenomAgent(getName());
			user.setIm(getIm());

			String hashedPassword = passwordEncoder.encode(getPassword());
			user.setPassword(hashedPassword);
			user.setPassword(hashedPassword);
			useri.setDesignation(designation);
			useri.setRole(role);
			user.setRoleAgent(useri);
			// getUsermetierimpl().addAgent(user);
			// refmetierimpl.addRef(new Useri(designation,role), new
			// Agent(getIm(),getName(),hashedPassword,new Useri(designation,role)));
			// refmetierimpl.addRef(useri,user);
			usermetierimpl.addUser(useri);
			usermetierimpl.addAgent(user);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Données Sauvegardées"));
			return SUCCESS;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage("myForm:password1",
				new FacesMessage("Mot de passe ne correspond pas"));
		return ERROR;
	}

	/* Mbola tsy vita bureau, porte, adresse */

	public String addModeAcquisition() {
		modeAcquisition = new ModeAcquisition(getDesignation());
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		refmetierimpl.addRef(modeAcquisition, agent);
		return SUCCESS;
	}

	public String addModeAcquisitionCA() {
		modeAcquisition = new ModeAcquisition(getDesignation());
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		refmetierimpl.addRef(modeAcquisition, agent);
		return SUCCESS;
	}

	public String addModeDecharge() {
		motifDecharge = new MotifDecharge(getDesignation());
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		refmetierimpl.addRef(motifDecharge, agent);
		return SUCCESS;
	}

	public String addFinancement() {
		financement = new Financement(getDesignation());
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		refmetierimpl.addRef(financement, agent);
		return SUCCESS;
	}

	public String addFinancementCA() {
		financement = new Financement(getDesignation());
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		refmetierimpl.addRef(financement, agent);
		return SUCCESS;
	}

	public String addFournisseur() {
		fournisseur = new Fournisseur(getDesignation());
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		refmetierimpl.addRef(fournisseur, agent);
		return SUCCESS;
	}

	public String addFournisseurCA() {
		fournisseur = new Fournisseur(getDesignation());
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		refmetierimpl.addRef(fournisseur, agent);
		return SUCCESS;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getNomenclature() {
		return nomenclature;
	}

	public void setNomenclature(String nomenclature) {
		this.nomenclature = nomenclature;
	}

	public IRefMetier getRefmetierimpl() {
		return refmetierimpl;
	}

	public void setRefmetierimpl(IRefMetier refmetierimpl) {
		this.refmetierimpl = refmetierimpl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getIm() {
		return im;
	}

	public void setIm(Long im) {
		this.im = im;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public IUserMetier getUsermetierimpl() {
		return usermetierimpl;
	}

	public void setUsermetierimpl(IUserMetier usermetierimpl) {
		this.usermetierimpl = usermetierimpl;
	}

	

	public String addPoste() {

		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		refmetierimpl.addRef(new Poste(this.getDesignation()), agent);
		return SUCCESS;
	}

	public String addMotifSortie() {

		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		refmetierimpl.addRef(new MotifSortie(this.getDesignation()), agent);
		return SUCCESS;
	}

	
	public String addTypeObjet() {
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		refmetierimpl.addRef(new TypeObjet(getDesignation(), getCaracteristique()), agent);
		return SUCCESS;
	}

	public String getCodeBureau() {
		return codeBureau;
	}

	public void setCodeBureau(String codeBureau) {
		this.codeBureau = codeBureau;
	}

	public List<Nomenclature> getListNomenclature() {
		if (this.listNomenclature == null) {
			ArrayList<Referentiel> r = (ArrayList<Referentiel>) refmetierimpl.listRef(new Nomenclature());
			List<Nomenclature> ds = new ArrayList<Nomenclature>();
			for (Object d : r) {
				if (d instanceof Nomenclature) {
					ds.add((Nomenclature) d);
				}
			}
			//reorder ds by alph
			Collections.sort(ds, new Comparator<Nomenclature>() {
			    @Override
			    public int compare(Nomenclature n1, Nomenclature n2) {
			        return n1.getNomenclature().compareToIgnoreCase(n2.getNomenclature());
			    }
			});
			
			listNomenclature = ds;
		}
		return listNomenclature;
	}

	public List<TypeObjet> getListTypeObjet() {
		if(this.listTypeObjet==null) {
		ArrayList<Referentiel> r = (ArrayList<Referentiel>) refmetierimpl.listRef(new TypeObjet());
		List<TypeObjet> ds = new ArrayList<TypeObjet>();
		for (Object d : r) {
			if (d instanceof TypeObjet) {
				ds.add((TypeObjet) d);
			}
		}
		
		//reorder ds by designation
		Collections.sort(ds, new Comparator<TypeObjet>() {
		    @Override
		    public int compare(TypeObjet t1, TypeObjet t2) {
		        return t1.getDesignation().compareTo(t2.getDesignation());
		    }
		});
		
		listTypeObjet= ds;
		}
		return this.listTypeObjet;
	}

	public void setListTypeObjet(List<TypeObjet> listTypeObjet) {
		this.listTypeObjet = listTypeObjet;
	}

	

	public String addDirection() {
		try {
			Direction direction = new Direction(this.getDesignation(), this.getCodeDirection());
			direction.setBudget(this.getBudget());
			direction.setTrois(this.getTrois());
			direction.setQuatre(this.getQuatre());
			
			//add title to list
			//save direction title
			DirectionTitleHist dirtitle = new DirectionTitleHist();
			dirtitle.setTitle(this.getDesignation());
			dirtitle.setDate(new Date());
			
			//add dirtitle
			direction.addTitle(dirtitle);
			//refmetierimpl.saveDirHisto(dirtitle);
			//set direction title
			
			
			Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
			refmetierimpl.addRef(direction, agent);
			return SUCCESS;
		} catch (DataIntegrityViolationException ex) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Code direction  unique",
					getCodeDirection() + " existe déjà");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "";
		}catch(SQLException exu){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erreur interne",
					" Une erreur s'est produite pendant la requête");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "";
		}
	}

	public void setListNomenclature(List<Nomenclature> listNomenclature) {
		this.listNomenclature = listNomenclature;
	}

	public String getCodeService() {
		return codeService;
	}

	public void setCodeService(String codeService) {
		this.codeService = codeService;
	}

	public String getCodeDirection() {
		return codeDirection;
	}

	public void setCodeDirection(String codeDirection) {
		this.codeDirection = codeDirection;
	}

	public IUtilisateurMetier getUtilisateurmetierimpl() {
		return utilisateurmetierimpl;
	}

	public void setUtilisateurmetierimpl(IUtilisateurMetier utilisateurmetierimpl) {
		this.utilisateurmetierimpl = utilisateurmetierimpl;
	}

	public String addRole() {
		try {
			// Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
			Useri useri = new Useri();
			useri.setDesignation(designation);
			useri.setRole(role);
			usermetierimpl.addUser(useri);
			return SUCCESS;
		} catch (DataIntegrityViolationException ex) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fonction unique",
					role + " existe déjà");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "";
			// throw new DataIntegrityViolationException(role+ " already exists");
		}
	}

	public String addRoleCA() {
		// Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
		Useri useri = new Useri();
		useri.setDesignation(designation);
		useri.setRole(role);
		usermetierimpl.addUser(useri);
		return SUCCESS;
	}

	// -------------------------GRAND II--------------------------
	private String caracteristique;
	private List<TypeObjet> listTypeObject;
	private TypeObjet typeObjet;
	private List<CodeArticle> listCodeArticle;
	private List<CodeArticle> listCodeArticleByTypeObject;
	private Double prix;
	private Agent agentDest;

	private CodeArticle codeArticle;

	private List<OpEntreeArticle> listOpEntreeArticle;
	private List<OpSortieArticle> listOpSortieArticle;

	private List<Agent> listAgentDestinataire;

	private OpEntreeArticle opEntreeArticle;
	private OpSortieArticle opSortieArticle;
	private String motif;

	private List<ArticleEx> listArticleEx;
	private List<ArticleNouv> listArticleNouv;

	private List<Article> listArticle;

	private List<ArticleNouv> listArticleNouvValide;

	public void setCaracteristiqueObjet() {
		TypeObjet t = new TypeObjet();
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		t.setCaracteristique(getCaracteristique());
		t.setDesignation(getDesignation());
		refmetierimpl.addRef(t, agent);
	}

	public String getCaracteristique() {
		return caracteristique;
	}

	public void setCaracteristique(String caracteristique) {
		this.caracteristique = caracteristique;
	}

	public List<TypeObjet> getListTypeObject() {
		ArrayList<Referentiel> r = (ArrayList<Referentiel>) refmetierimpl.listRef(new TypeObjet());
		List<TypeObjet> ds = new ArrayList<TypeObjet>();
		for (Object d : r) {
			if (d instanceof MotifSortie) {
				ds.add((TypeObjet) d);
			}
		}
		return ds;
	}

	public void setListTypeObject(List<TypeObjet> listTypeObject) {
		this.listTypeObject = listTypeObject;
	}

	public TypeObjet getTypeObjet() {
		return typeObjet;
	}

	public void setTypeObjet(TypeObjet typeObjet) {
		this.typeObjet = typeObjet;
	}

	public String addCodeArticle() {
		CodeArticle c = new CodeArticle();
		c.setDesignation(getDesignation());
		c.setTypeObjet(getTypeObjet());
		usermetierimpl.addCodeArticle(c);
		return SUCCESS;
	}

	public List<ArticleEx> getListArticleEx() {
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		return usermetierimpl.getListArticleEx(agent.getDirection());
	}

	public void setListArticleEx(List<ArticleEx> list) {
		this.listArticleEx = list;
	}

	public List<ArticleNouv> getListArticleNouv() {
		return usermetierimpl.getListAllArticleNouv();
	}

	public void setListArticleNouv(List<ArticleNouv> list) {
		this.listArticleNouv = list;
	}

	public List<Article> getListArticle() {
		return usermetierimpl.getListAllArticle();
	}

	public void setListArticle(List<Article> list) {
		this.listArticle = list;
	}

	public List<ArticleNouv> getListArticleNouvValide() {
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");

		return usermetierimpl.getListArtNouvByValidationByDirection(true, agent.getDirection());
	}

	public void setListArticleNouvValide(List<ArticleNouv> list) {
		this.listArticleNouvValide = list;
	}

	public List<CodeArticle> getListCodeArticle() {
		if(this.listCodeArticle==null) {
			
			List<CodeArticle> lc= usermetierimpl.listCodeArticle();
			Collections.sort(lc, new Comparator<CodeArticle>() {
				public int compare(CodeArticle c1, CodeArticle c2) {
					
			        return c1.getDesignation().compareToIgnoreCase(c2.getDesignation());
				}
			});
			this.listCodeArticle = lc;
			
		}
		return this.listCodeArticle;
	}

	public void setListCodeArticle(List<CodeArticle> listCodeArticle) {
		this.listCodeArticle = listCodeArticle;
	}

	public CodeArticle getCodeArticle() {
		return codeArticle;
	}

	public void setCodeArticle(CodeArticle Code) {
		this.codeArticle = Code;
	}

	public List<CodeArticle> getListCodeArticleByTypeObject() {
		return usermetierimpl.listCodeArticleByTypeObj(getTypeObjet());
	}

	public void setListCodeArticleByTypeObject(List<CodeArticle> listCodeArticleByTypeObject) {
		this.listCodeArticleByTypeObject = listCodeArticleByTypeObject;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public OpEntreeArticle addRequeteOpEntree() {
		ArticleNouv a = new ArticleNouv();
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		a.setFournisseur(getFournisseur());
		a.setPrix(getPrix());
		return usermetierimpl.reqEntrerArticle(a, agent);
	}

	// -----------FIN SISE------------------
	/*public OpSortieArticle addRequeteSortie() throws Exception {
		try {
			ArticleNouv a = new ArticleNouv();
			Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
			a.setFournisseur(getFournisseur());
			a.setPrix(getPrix());

			return usermetierimpl.reqSortirArticle(a, agent, getAgentDest());
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur de requete de sortie materiel",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
	}*/

	public List<OpEntreeArticle> getListOpEntreeArticle() {
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		Date sdate = new GregorianCalendar(year - 3, Calendar.JANUARY, 1).getTime();
		Date edate = new GregorianCalendar(year + 1, Calendar.DECEMBER, 30).getTime();
		
		return usermetierimpl.getListOpEntreeArtByDirection(agent.getDirection(), sdate, edate);
	}

	public void setListOpEntreeArticle(List<OpEntreeArticle> listOpEntreeArticle) {
		this.listOpEntreeArticle = listOpEntreeArticle;
	}

	public List<OpSortieArticle> getListOpSortieArticle() {
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		Date sdate = new GregorianCalendar(year - 3, Calendar.JANUARY, 1).getTime();
		Date edate = new GregorianCalendar(year + 1, Calendar.DECEMBER, 30).getTime();
		return usermetierimpl.getListOpSortieArtByDirection(agent.getDirection(), sdate, edate);

	}

	public void setListOpSortieArticle(List<OpSortieArticle> listOpSortieArticle) {
		this.listOpSortieArticle = listOpSortieArticle;
	}

	public OpEntreeArticle getOpEntreeArticle() {
		return opEntreeArticle;
	}

	public void setOpEntreeArticle(OpEntreeArticle opEntreeArticle) {
		this.opEntreeArticle = opEntreeArticle;
	}

	public OpSortieArticle getOpSortieArticle() {
		return opSortieArticle;
	}

	public void setOpSortieArticle(OpSortieArticle opSortieArticle) {
		this.opSortieArticle = opSortieArticle;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public List<Agent> getListAgentDestinataire() {
		return listAgentDestinataire;
	}

	public void setListAgentDestinataire(List<Agent> listAgentDestinataire) {
		this.listAgentDestinataire = listAgentDestinataire;
	}

	public Agent getAgentDest() {
		return agentDest;
	}

	public void setAgentDest(Agent agentDest) {
		this.agentDest = agentDest;
	}

	// ------------DEBUT GAC--------------
	public void validateArticleSaisieExistant() {
		OpEntreeArticle o = addRequeteOpEntree();
		usermetierimpl.entrerArticle(o);
	}

	public void reqArtAModifier() throws Exception {
		try {
			usermetierimpl.reqArtAModifier(getOpEntreeArticle(), getMotif());
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erreur de requete d'article à modifier", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void reqSortirArtAModifier() throws Exception {
		try {
			usermetierimpl.reqSortirArtAModifier(getOpSortieArticle(), getMotif());
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erreur de requete de sortie d'article à modifier", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void reqArtRefuser() throws Exception {
		try {
			usermetierimpl.reqArtRefuser(getOpEntreeArticle(), getMotif());
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur de requete d'article à refuser",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void reqSortirRefuser() throws Exception {
		try {
			usermetierimpl.reqSortirRefuser(getOpSortieArticle(), getMotif());
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erreur de requete, sortie de matériel refusée", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void entrerArticle() throws Exception {
		usermetierimpl.entrerArticle(getOpEntreeArticle());
	}

	public void sortirArticle() throws Exception {
		try {
			usermetierimpl.sortirArticle(getOpSortieArticle());
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur de sortie d'article",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	Nomenclature nomenclatureP;

	public Nomenclature getNomenclatureP() {
		return nomenclatureP;
	}

	public void setNomenclatureP(Nomenclature nomenclatureP) {
		this.nomenclatureP = nomenclatureP;
	}

	public void onTypeMaterielChange() {

		this.setNomenclatureP(nomenclatureP);
	}

	private String codeTypeMateriel;

	public String getCodeTypeMateriel() {
		return codeTypeMateriel;
	}

	public void setCodeTypeMateriel(String codeTypeMateriel) {
		this.codeTypeMateriel = codeTypeMateriel;
	}

	// ------DEBUG FARANY
	private String direction;

	private List<Article> listArticleNonDetenuValideByDirection;

	public List<Article> getListArticleNonDetenuValideByDirection() {
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		return usermetierimpl.getListArticleNonDetenuValideByDirection(agent.getDirection());
	}

	public void setListArticleNonDetenuValideByDirection(List<Article> d) {
		this.listArticleNonDetenuValideByDirection = d;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	private List<Article> listArticleValide;

	public List<Article> getListArticleValide() {
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		return usermetierimpl.getListArticleValideByDirection(agent.getDirection());
	}

	public void setListArticleValide(List<Article> listArticleValide) {
		this.listArticleValide = listArticleValide;
	}

	private List<ArticleNouv> listArtNouvValide;

	public List<ArticleNouv> getListArtNouvValide() {
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		return usermetierimpl.getListArtNouvValideByDirection(agent.getDirection());
	}

	public void setListArtNouvValide(List<ArticleNouv> listArtNouvValide) {
		this.listArtNouvValide = listArtNouvValide;
	}

	public String updateNomenclature() {
		try {
			Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
			// check if there is nomenclature duplicate
			System.out.println("NOMENCLATURE UPDATE" + nomen.getDesignation());
			System.out.println("AGENT UPDATE" + agent.getNomAgent());

			refmetierimpl.addRef(nomen, agent);
			return SUCCESS;
		} catch (DataIntegrityViolationException ex) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Nomenclature unique ",
					getNomenclature() + " existe déjà");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return ERROR;
			// throw new DataIntegrityViolationException(getNomenclature()+ " already
			// exists");
		}
	}

	public void onRowEdit(RowEditEvent event) {
		Referentiel r = (Referentiel) event.getObject();
		try {
			Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
			refmetierimpl.addRef(r, agent);
			FacesMessage msg = new FacesMessage("Referentiel modifié",
					((Referentiel) event.getObject()).getId().toString() + " modifié ");
			FacesContext.getCurrentInstance().addMessage("myerrorReferentiel", msg);
			// listNomenclature=null;
		} catch (Exception ex) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Referentiel non modifié ",
					r.getDesignation() + " ne peut pas être modifié car ne respecte pas les contraintes ");
			FacesContext.getCurrentInstance().addMessage("myerrorReferentiel", message);
		}

	}

	public void deleteRow(Referentiel r) {
		try {

			refmetierimpl.removeRef(r);
			FacesMessage msg = new FacesMessage("Referentiel supprimé ", r.getDesignation() + " supprimé");
			FacesContext.getCurrentInstance().addMessage("myerrorReferentiel", msg);
		} catch (Exception ex) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Referentiel non supprimé ",
					r.getDesignation() + " ne peut pas être supprimé car en cours d'utilisation ");
			FacesContext.getCurrentInstance().addMessage("myerrorReferentiel", message);
		}

	}
	
	public void deleteUser(Agent a) {
		try {
			usermetierimpl.remAgent(a);
			FacesMessage msg = new FacesMessage("Agent supprimé ", a.getIm() + " supprimé");
			FacesContext.getCurrentInstance().addMessage("myerrorReferentiel", msg);
			
		}catch(ConstraintViolationException e) {
			usermetierimpl.desactiveAgent(a);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Agent désactivé ",
					//e.getMessage());
					a.getIm() + " ne peut pas être supprimé car encore reférencé ");
			FacesContext.getCurrentInstance().addMessage("myerrorReferentiel", message);
		}		
		catch(Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Agent non supprimé ",
					e.getMessage());
					//a.getIm() + " ne peut pas être supprimé car encore reférencé ");
			FacesContext.getCurrentInstance().addMessage("myerrorReferentiel", message);
		
		}
	}
	private Marque nouvmark = new Marque();
	public Marque getNouvmark() {
		return nouvmark;
	}

	public void setNouvmark(Marque nouvmark) {
		this.nouvmark = nouvmark;
	}
	
	public void addNewMarque(AjaxBehaviorEvent event) {
		System.out.println("ajouter new marque");
		if (nouvmark.equals(((UIInput) event.getComponent()).getValue())) {
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.update("addNewMarkDialog");
            ajax.execute("PF('widget_addNewJMarkDialog').show()");
        }
		
		
	}
	public void saveNewMark() {
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		nouvmark.setDesignation(nouvmark.getDesignation().toUpperCase());
		refmetierimpl.addRef(nouvmark, agent);
		nouvmark = new Marque();

        RequestContext ajax = RequestContext.getCurrentInstance();
        ajax.update("fileUpoadForm");
        ajax.execute("PF('widget_addNewJMarkDialog').hide()");
    }
	
	
	private CodeArticle nouvarticle = new CodeArticle();
	public CodeArticle getNouvarticle() {
		return nouvarticle;
	}

	public void setNouvarticle(CodeArticle nouvarticle) {
		this.nouvarticle = nouvarticle;
	}
	
	public void addNewArticle(AjaxBehaviorEvent event) {
		System.out.println("ajouter new article");
		if (nouvarticle.equals(((UIInput) event.getComponent()).getValue())) {
			System.out.println("mitovy");
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.update("addNewArticleDialog");
            ajax.execute("PF('widget_addNewJArticleDialog').show()");
       }
		
		
	}
	public void saveNewArticle() {
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		refmetierimpl.addRef(nouvarticle, agent);
		nouvarticle = new CodeArticle();

        RequestContext ajax = RequestContext.getCurrentInstance();
        ajax.update("fileUpoadForm");
        ajax.execute("PF('widget_addNewJArticleDialog').hide()");
    }
	
	

	private List<SelectItem> groupeListTypeMateriel;
	
	public List<SelectItem> getGroupeListTypeMateriel() {
		
		if(groupeListTypeMateriel==null) {
			System.out.println("get it");
			List<SelectItem> lstgp = new ArrayList<SelectItem>();
			Map<Nomenclature,List<TypeMateriel>> mapnl = new HashMap<Nomenclature, List<TypeMateriel>>();
			for(TypeMateriel tm : getListTypeMateriel()) {
				Nomenclature key = tm.getNomenclaureParent();
				if(mapnl.containsKey(key)) {
					List<TypeMateriel> list = mapnl.get(key); list.add(tm);
				}
				else {
					List<TypeMateriel> list = new ArrayList<TypeMateriel>(); list.add(tm);
					mapnl.put(key, list);
				}
			}
			Iterator<Map.Entry<Nomenclature, List<TypeMateriel>>> iterator = mapnl.entrySet().iterator();
			while(iterator.hasNext()){
				Map.Entry<Nomenclature, List<TypeMateriel>> entry = iterator.next();
				SelectItemGroup g = new SelectItemGroup(entry.getKey().getDesignation());
				SelectItem[] lesitems = new SelectItem[entry.getValue().size()];
				int i =0;
				for(TypeMateriel tm: entry.getValue()) {
					lesitems[i] = new SelectItem(tm, tm.getDesignation());
					i=i+1;
					
				}
				g.setSelectItems(lesitems);
				lstgp.add(g);
			}
			System.out.println("got it");
			System.out.println(lstgp.size());
			groupeListTypeMateriel = lstgp;
		}
		return groupeListTypeMateriel;
	}

	public void setGroupeListTypeMateriel(List<SelectItem> groupeListTypeMateriel) {
		this.groupeListTypeMateriel = groupeListTypeMateriel;
	}


	private List<SelectItem> groupeListArticle;
	
	public List<SelectItem> getGroupeListArticle() {
		if(groupeListArticle==null) {
			System.out.println("get it");
			List<SelectItem> lstgp = new ArrayList<SelectItem>();
			Map<TypeObjet,List<CodeArticle>> mapnl = new HashMap<TypeObjet, List<CodeArticle>>();
			for(CodeArticle tm : getListCodeArticle()) {
				TypeObjet key = tm.getTypeObjet();
				if(mapnl.containsKey(key)) {
					List<CodeArticle> list = mapnl.get(key); list.add(tm);
				}
				else {
					List<CodeArticle> list = new ArrayList<CodeArticle>(); list.add(tm);
					mapnl.put(key, list);
				}
				System.out.println("mapnl : " + mapnl.get(key));
			}
			Iterator<Map.Entry<TypeObjet, List<CodeArticle>>> iterator = mapnl.entrySet().iterator();
			while(iterator.hasNext()){
				Map.Entry<TypeObjet, List<CodeArticle>> entry = iterator.next();
				SelectItemGroup g = new SelectItemGroup(entry.getKey().getDesignation());
				SelectItem[] lesitems = new SelectItem[entry.getValue().size()];
				int i =0;
				for(CodeArticle tm: entry.getValue()) {
					lesitems[i] = new SelectItem(tm, tm.getDesignation());
					i=i+1;
					
				}
				g.setSelectItems(lesitems);
				lstgp.add(g);
			}
			System.out.println("got it");
			System.out.println(lstgp.size());
			groupeListArticle = lstgp;
		}
		return groupeListArticle;
	}

	public void setGroupeListArticle(List<SelectItem> groupeListArticle) {
		this.groupeListArticle = groupeListArticle;
	}
	
	private String budget;
	private String trois;
	private String quatre;


	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getTrois() {
		return trois;
	}

	public void setTrois(String trois) {
		this.trois = trois;
	}

	public String getQuatre() {
		return quatre;
	}

	public void setQuatre(String quatre) {
		this.quatre = quatre;
	}
	
	public Devise getDevise() {
		return devise;
	}
	private Map<String,String> mapDevise;
	public Map<String,String> getMapDevise(){
		if(mapDevise == null) {
			Map<String, String> filamatras = new HashMap<String, String>();
			for(Devise d : this.getListDevise()) {
				filamatras.put(d.getDesignation(), d.getDesignation());
				System.out.println(d.getDesignation());
			}
			this.mapDevise = filamatras;
		}
		return this.mapDevise;
	}
	public void setMapDevise(Map<String,String> md) {
		this.mapDevise = md;
	}
	public void setDevise(Devise devise) {
		this.devise = devise;
	}

	private Devise devise = new Devise();
	
	public String addDevise() {
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		refmetierimpl.addRef(this.getDevise(), agent);
		return SUCCESS;
	}
	
	private List<Devise> listDevise;
	
	public List<Devise> getListDevise() {
		if (listDevise == null) {
			ArrayList<Referentiel> r = (ArrayList<Referentiel>) refmetierimpl.listRef(new Devise());
			List<Devise> ds = new ArrayList<Devise>();
			for (Object d : r) {
				if (d instanceof Devise) {
					ds.add((Devise) d);
				}
			}
			listDevise = ds;
		}
		return listDevise;
	}

	public void setListDevise(List<Devise> listDevise) {
		this.listDevise = listDevise;
	}
	
	

	private Direction directionToModify = new Direction();
	public Direction getDirectionToModify() {
		return directionToModify;
	}

	public void setDirectionToModify(Direction directionToModify) {
		this.directionToModify = directionToModify;
	}
	public void setCurrentDirectiontomodify(Direction d) {
		Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMapObj.put("editDir", d);
		this.setDirectionToModify(d);
	}
	
	public void updateDirection(Direction d) {
		if(d==null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur direction inconnue", "Direction inconnue");
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage("editdirectionerror", message);
			System.out.println("direction null");
			return ;
		}
		try {
		//Agent operat = (Agent) RequestFilter.getSession().getAttribute("agent");
		System.out.println("id : "+d.getId());
		refmetierimpl.updateDirection(d);
		
		System.out.println("add direction");
		}catch(Exception e) {
			FacesMessage messagea = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur Modification Direction", "Ne respecte pas les contraintes");
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage("editdirectionerror", messagea);
		}
		finally {
			//Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			//sessionMapObj.remove("editAgent");
		}
	}
	public void exit() {
		this.directionToModify=new Direction();
		//sessionMapObj.put("editDir", d);
		Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMapObj.remove("editDir");
		FacesMessage messagea = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modification Direction", "La modification a été annulée");
		FacesContext.getCurrentInstance().addMessage("editdirectionerror", messagea);
		
	}
	
	

	private DirectionTitleHist dirhist = new DirectionTitleHist();
	public DirectionTitleHist getDirhist() {
		return dirhist;
	}

	public void setDirhist(DirectionTitleHist dirhist) {
		this.dirhist = dirhist;
	}
	
	public void addIntitule(Direction d, DirectionTitleHist dh) {
		d.addTitle(dh);
	}
	
	public List<SelectItem> mapDirection(Direction d, Date dates){
		//Map<String, String> filamatras = new HashMap<String, String>();
		if(d==null) {
			Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
			d = agent.getDirection(); 
		}
		final Date date = new Date();
		
		//System.out.println(date);
		Direction dir =(Direction) refmetierimpl.findById(d.getId());
		Collections.sort(dir.getListTitle(), new Comparator<DirectionTitleHist>() {
			public int compare(DirectionTitleHist h1, DirectionTitleHist h2) {
				Date d1 = h1.getDate();
				Date d2 = h2.getDate();
				/*System.out.println("date :" + h1);
				System.out.println("date :" + h2);*/
				if(d1 ==null || d2 ==null) {
					return 0;
				}
				long diff1 = Math.abs(d1.getTime() - date.getTime());
		        long diff2 = Math.abs(d2.getTime() - date.getTime());
		        //System.out.println(h1.getTitle()+" : "+ diff1 +" - "+ h2.getTitle()+" : "+diff2 +" = " + (diff1-diff2));
		        return Long.compare(diff1, diff2);
			}
		});
		List<SelectItem> result = new ArrayList<SelectItem>();  
		for(DirectionTitleHist dh : dir.getListTitle()) {
			//System.out.println(dh.getTitle());
			result.add(new SelectItem(dh.getTitle(), dh.getTitle()));
			//filamatras.put(dh.getTitle(), dh.getTitle());
		}
		
		return result;
	}
	
		private boolean invismdp=false;
		public boolean isInvismdp() {
			return invismdp;
		}

		public void setInvismdp(boolean invismdp) {
			this.invismdp = invismdp;
		}
		public void showmdp() {
			this.invismdp = true;
		}
		public void hidemdp() {
			this.invismdp = false;
		}
		public List<ArticleEx> getListArticleExbyDir(Direction dir) {
			if(dir ==null) {
				Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
				dir = agent.getDirection();
			}
			
			return usermetierimpl.getListArticleEx(dir);
		}
		public List<ArticleNouv> getListArticleNouvValidebyDir(Direction dir) {
			if(dir ==null) {
				Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
				dir = agent.getDirection();
			}
			return usermetierimpl.getListArtNouvByValidationByDirection(true, dir);
		}
}
