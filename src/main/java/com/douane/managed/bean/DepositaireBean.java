package com.douane.managed.bean;

import com.douane.entite.*;
import com.douane.metier.nomenclature.INomenclatureMetier;
import com.douane.metier.referentiel.IRefMetier;
import com.douane.metier.user.IUserMetier;
import com.douane.model.DocumentModel;
//import com.douane.model.UploadedFileByte;
import com.douane.requesthttp.RequestFilter;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.JDBCException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.component.html.HtmlSelectManyCheckbox;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.Part;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by hasina on 10/29/17.
 */
@ManagedBean(name = "depositaireBean")
@SessionScoped
@PropertySource("classpath:config.properties")
public class DepositaireBean {

	@Autowired
	public Environment env;

	@Value("${fileupload.size}")
	private String fileupload;

	private String fileUploadSize;

	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{usermetier}")
	IUserMetier usermetierimpl;

	@ManagedProperty(value = "#{refmetier}")
	IRefMetier refmetierimpl;

	private String anneeAcquisition;

	/* attribute for image */
	private int imgPosition = 0;

	/* attribute for file upload */
	private static final long serialVersionUID = 1L;
	private String name;
	private UploadedFile document;
	ArrayList<DocumentModel> documentList = new ArrayList<DocumentModel>();
	ArrayList<DocumentModel> imageList = new ArrayList<DocumentModel>();
	ArrayList<DocumentModel> documentFacList = new ArrayList<DocumentModel>();
	private Part file;
	private byte[] byteDoc;
	HashMap<UploadedFile, byte[]> HashMapFileByteContent = new HashMap<UploadedFile, byte[]>();
	HashMap<String, HashMap<UploadedFile, byte[]>> hashOfHashMapFIle = new HashMap<String, HashMap<UploadedFile, byte[]>>();
	private ArrayList<UploadedFile> uploadedFiles = new ArrayList<UploadedFile>();
	private UploadedFile uploadedFile;
	private String fileZipPath;

	/* attribute for designation */
	private Double unitPrice;
	private String reference;
	private String numSerie;
	private String carac;
	private String renseignement;
	private Nomenclature typemateriel;
	private String nomencl;// Mbola tsy ay
	private Marque marq;
	private ModeAcquisition acquisition;
	private Financement financement;
	private Fournisseur fournisseur;
	private Double montantFac;// Tokony mitovy @ unitPrice ihany
	private String refFacture;
	private String nombPerType;
	private String autre;
	private String documentPath;
	private String caracteristique;// miasa ve?

	/* attribute for materiels */
	private EtatMateriel etat;
	private String etatMatPresent;

	private Direction direction;// direction des materiels

	/* acces sur les listes */
	private List<MaterielEx> listMaterielexistant;// Existant Valide
	private List<Materiel> listMaterielByDet;// Materiel valide par detenteur pour dettachement
	private List<Materiel> listAllMaterielValideSansDetenteur;// Materiel valide sans detenteur pour attribution
	private List<MaterielNouv> listMaterielnouveau;// Pour affichage dans prise en charge Materiel Nouveau valide
	private List<Materiel> listAllMateriel;// miasa ve???
	private List<MotifSortie> listMotifSortie;// liste des motifs de sortie pour decharge
	private List<MotifDecharge> listMotifDettachement;
	private List<Materiel> listAllMaterilValide;// Miasa ve?

	/* Current pour les objets selectionner */
	private OpEntree curentOrdreEntree;
	private OpSortie curentOrdreSortie;
	private Article curentArticle;

	/* Pour les articles */
	private Article article; // miasa aiza?

	/* Les variables afficher automatique */
	private String nom;
	private String prenom;

	// Sortie Materiel

	private Direction destinationDirec; // direction de sortie lors de l'affectation

	private MotifSortie motifSortie;
	private MotifDecharge motifDettachement;
	private Agent detenteur;
	private Materiel materiel;

	private List<Materiel> currentListMateriel = new ArrayList<Materiel>();

	// Current Agent
	private Agent currentAgent;
	private Materiel curentMateriel;
	private String listParamShouldNotBeNull = "";
	private Long idMat;

	// Methode on Change
	public void onDetenteurChange() {

		if (getDetenteur() == null) {
			this.setNom(null);
			this.setPrenom(null);
		}

		/*
		 * this.setNom(getDetenteur().getNomAgent());
		 * this.setPrenom(getDetenteur().getPrenomAgent()); return null;
		 */
	}

	public void onDetenteurDetChange() {
		this.setNom(getDetenteur().getNomAgent());
		this.setPrenom(getDetenteur().getPrenomAgent());
	}

	public void onMaterielChange() {
		this.setMarq(this.getMateriel().getMarque());
		this.setReference(this.getMateriel().getReference());
		this.setNumSerie(this.getMateriel().getNumSerie());
	}

	public void onMotifSortieChange() {

	}

	public void onTypeMaterielChange() {

		// this.setNomencl(getTypemateriel().getNomenclature());
		if (getTypematerielToAdd() != null) {
			this.setNomencl(getTypematerielToAdd().getNomenclaureParent().getNomenclature());
		} else {
			this.setNomencl("");
		}

	}

	// Usefull function
	public void clear() throws IOException {

		this.setAgentDest(null);
		this.setPrix(null);
		this.setNombre(null);
		this.setRenseignement(null);
		this.setCodeArticle(null);

		this.setDetenteur(null);
		this.setMaterielSeclected(null);
		this.setNom(null);
		this.setReferenceAutom(null);
		this.setNumSerie(null);
		this.setNomenclatureAutom(null);
		this.setFinancement(null);
		this.setAcquisition(null);
		this.setUnitPrice(null);
		this.setAnneeAcquisition(null);
		this.setTypematerielToAdd(null);
		this.setNomencl(null);
		this.setMarq(null);
		this.setReference(null);
		this.setNumSerie(null);
		this.setRenseignement(null);
		this.setAutre(null);
		this.setEtat(null);
		this.setOrigine(null);
		this.setEspeceUnite(null);
		this.setMontantFac(null);
		this.setRefFacture(null);
		this.setFournisseur(null);

		this.setDetenteurDett(null);
		this.setMaterielSeclectedDett(null);

		// documentList = initialize();
		// imageList = initializeImageFile();
		documentFacList = initializeFacFile();
	}

	public void clearPriseEnCharge() throws IOException {
		this.setDetenteur(null);
		this.setMaterielSeclected(null);
		this.setNom(null);
		this.setReferenceAutom(null);
		this.setNumSerie(null);
		this.setNomenclatureAutom(null);
		this.setFinancement(null);
		this.setAcquisition(null);
		this.setUnitPrice(null);
		this.setAnneeAcquisition(null);
		this.setTypematerielToAdd(null);
		this.setNomencl(null);
		this.setMarq(null);
		this.setReference(null);
		this.setNumSerie(null);
		this.setRenseignement(null);
		this.setAutre(null);
		this.setEtat(null);
		this.setOrigine(null);
		this.setEspeceUnite(null);
		this.setMontantFac(null);
		this.setRefFacture(null);
		this.setFournisseur(null);
		// documentList = initialize();
		// imageList = initializeImageFile();
	}

	public void setAllNull() {
		this.typemateriel = null;
		this.anneeAcquisition = null;
		this.autre = null;
		this.fournisseur = null;
		this.etat = null;
		this.marq = null;
		this.numSerie = null;
		this.unitPrice = null;
		this.reference = null;
		this.renseignement = null;
		this.typematerielToAdd = null;
		this.especeUnite = null;
		this.origine = null;
		this.refFacture = null;
		this.direction = null;
		this.financement = null;
		this.acquisition = null;
		this.montantFac = null;

		this.materielSeclected = null;
		this.nomenclatureAutom = null;
		this.marqueAutom = null;
		this.referenceAutom = null;
		this.detenteur = null;

		this.nom = null;

		this.codificationAutom = null;
		this.motifSortie = null;

		this.materiel = null;

		this.setDetenteurDett(null);
		this.setMaterielSeclectedDett(null);

	}

	public String getAnneeAcquisition() {
		return anneeAcquisition;
	}

	public void setAnneeAcquisition(String annee) {
		this.anneeAcquisition = annee;
	}

	public IRefMetier getRefmetierimpl() {
		return refmetierimpl;
	}

	public void setRefmetierimpl(IRefMetier refmetierimpl) {
		this.refmetierimpl = refmetierimpl;
	}

	public int getImgPosition() {
		return imgPosition;
	}

	public void setImgPosition(int i) {
		imgPosition = i;
	}

	public void setCurentArticle(Article a) {
		this.curentArticle = a;
	}

	public void mySetCurentArticle(Article a) {
		this.curentArticle = a;
	}

	public Article getCurentArticle() {
		return this.curentArticle;
	}

	public void setArticle(Article a) {
		this.article = a;
	}

	public Article getArticle() {
		return this.article;
	}

	public List<Materiel> getListMaterielByDet() {
		// List<Materiel> listmatcorrespondant;
		if (usermetierimpl.getListMatByDet(getDetenteur()) == null) {
			return usermetierimpl.getListMat();
		} else {
			return usermetierimpl.getListMatByDet(getDetenteur());
		}
	}

	public void setCurentOrdreEntree(OpEntree o) {
		this.curentOrdreEntree = o;
	}

	public String exit() {
		// this.setCurentMateriel(null);
		this.curentMaterielEx = null;
		this.curentMaterielNouv = null;
		this.curentMateriel = null;
		this.curentArticle = null;
		this.articleTomodify = null;
		setCurentNull();
		setAllNull();
		return "success";

	}

	public void exity() {
		// this.setCurentMateriel(null);
		this.curentMateriel = null;
		this.curentArticle = null;
		setCurentNull();
		setAllNull();
	}

	public OpEntree getCurentOrdreEntree() {
		return this.curentOrdreEntree;
	}

	public void setCurentOrdreSortie(OpSortie o) {
		this.curentOrdreSortie = o;
	}

	public OpSortie getCurentOrdreSortie() {
		return this.curentOrdreSortie;
	}

	public void setListMaterielByDet(List<Materiel> listMateriel) {
		this.listMaterielByDet = listMateriel;
	}

	public List<MaterielNouv> getListMaterielnouveau() {
		return usermetierimpl.getListMatNouv();
	}

	public void setListMaterielnouveau(List<MaterielNouv> listMaterielnouveau) {
		this.listMaterielnouveau = listMaterielnouveau;
	}

	public List<Materiel> getListAllMateriel() {
		if (listAllMateriel == null) {
			listAllMateriel = usermetierimpl.getListMat();
		}
		return listAllMateriel;
	}

	public void setListAllMateriel(List<Materiel> listAllMateriel) {
		this.listAllMateriel = listAllMateriel;
	}

	public Long getIdMat() {
		return idMat;
	}

	public void setIdMat(Long id) {
		this.idMat = id;
	}

	public String getFileZipPath() {

		if (getIdMat() != null) {
			try {
				fileZipPath = usermetierimpl.getMatById(getIdMat()).getDesign().getDocumentPath();//
				// RequestFilter.getSession().setAttribute("fileZipPath",fileZipPath);

				return usermetierimpl.getMatById(getIdMat()).getDesign().getDocumentPath();
			} catch (Exception e) {
				return "";
			}
		} else {
			return "";
		}
	}

	public void setFileZipPath(String f) {
		this.fileZipPath = f;
	}

	public Agent getCurrentAgent() {
		return (Agent) RequestFilter.getSession().getAttribute("agent");
	}

	public void setCurrentAgent(Agent currentAgent) {
		this.currentAgent = currentAgent;
	}

	// get list detenteur
	public List<Agent> getDetenteurs() {
		return usermetierimpl.findAllAgents();
	}

	public Double getUnitPrice() {
		return this.unitPrice;
		// return (Double) RequestFilter.getSession().getAttribute("unitPrice");
	}

	public void setUnitPrice(Double unitPrice) {
		// RequestFilter.getSession().setAttribute("unitPrice", unitPrice);
		this.unitPrice = unitPrice;
	}

	public String getReference() {
		return this.reference;
		// return (String) RequestFilter.getSession().getAttribute("reference");
	}

	public void setReference(String reference) {

		// RequestFilter.getSession().setAttribute("reference", reference);
		this.reference = reference;
	}

	public String getNumSerie() {
		return numSerie;
	}

	public void setNumSerie(String numSerie) {

		// RequestFilter.getSession().setAttribute("numSerie", numSerie);
		this.numSerie = numSerie;
	}

	public String getCarac() {
		return carac;
	}

	public void setCarac(String carac) {
		this.carac = carac;
	}

	public IUserMetier getUsermetierimpl() {
		return usermetierimpl;
	}

	public void setUsermetierimpl(IUserMetier usermetierimpl) {
		this.usermetierimpl = usermetierimpl;
	}

	public String getRenseignement() {
		return this.renseignement;
		// return (String) RequestFilter.getSession().getAttribute("renseignement");
	}

	public void setRenseignement(String renseignement) {
		// RequestFilter.getSession().setAttribute("renseignement", renseignement);

		this.renseignement = renseignement;
	}

	public EtatMateriel getEtat() {
		return this.etat;
		// return (EtatMateriel) RequestFilter.getSession().getAttribute("etat");
	}

	public void setEtat(EtatMateriel etat) {

		// RequestFilter.getSession().setAttribute("etat", etat);
		this.etat = etat;
	}

	public Nomenclature getTypemateriel() {
		return this.typemateriel;
		// return (Nomenclature)
		// RequestFilter.getSession().getAttribute("typemateriel");
	}

	public void setTypemateriel(Nomenclature typemateriel) {
		// RequestFilter.getSession().setAttribute("typemateriel", typemateriel);
		this.typemateriel = typemateriel;
	}

	public String getNomencl() {
		return nomencl;
	}

	public void setNomencl(String nomencl) {
		this.nomencl = nomencl;
	}

	public Marque getMarq() {
		return this.marq;
		// return (Marque) RequestFilter.getSession().getAttribute("marq");

	}

	public void setMarq(Marque marq) {

		// RequestFilter.getSession().setAttribute("marq", marq);
		this.marq = marq;
	}

	public ModeAcquisition getAcquisition() {
		return this.acquisition;
		// return (ModeAcquisition)
		// RequestFilter.getSession().getAttribute("acquisition");
	}

	public void setAcquisition(ModeAcquisition acquisition) {
		// RequestFilter.getSession().setAttribute("acquisition", acquisition);
		this.acquisition = acquisition;
	}

	public Financement getFinancement() {
		return this.financement;
		// return (Financement) RequestFilter.getSession().getAttribute("financement");
	}

	public void setFinancement(Financement financement) {
		// RequestFilter.getSession().setAttribute("financement", financement);
		this.financement = financement;
	}

	public Fournisseur getFournisseur() {
		return this.fournisseur;
		// return (Fournisseur) RequestFilter.getSession().getAttribute("fournisseur");
	}

	public void setFournisseur(Fournisseur fournisseur) {
		// RequestFilter.getSession().setAttribute("fournisseur", fournisseur);
		this.fournisseur = fournisseur;
	}

	public Double getMontantFac() {
		return this.montantFac;
		// return (Double) RequestFilter.getSession().getAttribute("montantFac");
	}

	public void setMontantFac(Double montantFac) {
		// RequestFilter.getSession().setAttribute("montantFac", montantFac);
		this.montantFac = montantFac;
	}

	public String getRefFacture() {
		return refFacture;
	}

	public void setRefFacture(String refFacture) {
		this.refFacture = refFacture;
	}

	public String getNombPerType() {
		return nombPerType;
	}

	public void setNombPerType(String nombPerType) {
		this.nombPerType = nombPerType;
	}

	public String getEtatMatPresent() {
		return etatMatPresent;
	}

	public void setEtatMatPresent(String etatMatPresent) {
		this.etatMatPresent = etatMatPresent;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public ArrayList<UploadedFile> getUploadedFiles() {
		ArrayList<UploadedFile> uploadedfiles = (ArrayList<UploadedFile>) RequestFilter.getSession()
				.getAttribute("uploadedFiles");
		if (uploadedfiles != null)
			return uploadedfiles;
		return uploadedFiles;
	}

	public void setUploadedFiles(ArrayList<UploadedFile> uploadedFiles) {
		this.uploadedFiles = uploadedFiles;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	/*---------------------method for uploading file--------------------------------*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UploadedFile getDocument() {
		return document;
	}

	public void setDocument(UploadedFile document) {
		this.document = document;
	}

	public ArrayList<DocumentModel> getDocumentList() {
		ArrayList<DocumentModel> documentlist = this.documentList;
		if (documentlist != null) {
			this.setDocumentList(documentlist);
			return documentlist;
		}
		return documentList;
	}

	public void setDocumentList(ArrayList<DocumentModel> documentList) {
		this.documentList = documentList;
	}

	public DepositaireBean() {
		try {
			documentList = initialize();
			imageList = initializeImageFile();
			documentFacList = initializeFacFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<DocumentModel> initialize() throws IOException {

		ArrayList<DocumentModel> list = new ArrayList<DocumentModel>();
		DocumentModel obj = new DocumentModel();
		obj.setSrNo(1);
		obj.setDocumentName("test filename1");
		obj.setRemovable(false);
		list.add(obj);
		obj = new DocumentModel();
		obj.setSrNo(2);
		obj.setDocumentName("test filename2");
		obj.setRemovable(false);
		list.add(obj);
		return list;
	}

	public ArrayList<DocumentModel> initializeImageFile() throws IOException {

		ArrayList<DocumentModel> list = new ArrayList<DocumentModel>();
		DocumentModel obj = new DocumentModel();
		obj.setSrNo(1);
		obj.setDocumentName("test imagefilename1");
		obj.setRemovable(false);
		list.add(obj);

		return list;
	}

	public ArrayList<DocumentModel> initializeFacFile() throws IOException {

		ArrayList<DocumentModel> list = new ArrayList<DocumentModel>();
		DocumentModel obj = new DocumentModel();
		obj.setSrNo(1);
		obj.setDocumentName("test imagefac1");
		obj.setRemovable(false);
		list.add(obj);

		return list;
	}

	public String addNewDoc() throws IOException {

		ArrayList<DocumentModel> list = getDocumentList();
		System.out.println("list count= " + list.size() + list.get(list.size() - 1).getSrNo());
		DocumentModel obj = new DocumentModel();
		obj.setSrNo(list.get(list.size() - 1).getSrNo() + 1);
		obj.setDocumentName("Type Document Name here");
		obj.setInstitute("Type Institute here");
		list.add(obj);

		setDocumentList(list);

		return null;

	}

	public String removeRow(DocumentModel row) {
		documentList.remove(row);
		// updates serial no.
		int i = 0;
		for (DocumentModel fl : documentList) {

			i++;
			fl.setSrNo(i);
		}
		return null;
	}

	public void removeDoc(DocumentModel row) {

		System.out.println("filename to be removed " + row.getDocumentUploadedPath());
		// You can write logic to remove uploaded file from the device. not written
		// here.
		System.out.println(row.getDocumentName());
		row.setDocumentUploadedPath("");
		row.setUploaded(false);

		//return null;
	}

	public String removeDocFac(DocumentModel row) {

		System.out.println("filename to be removed " + row.getDocumentUploadedPath());
		System.out.println(row.getDocumentName());
		row.setDocumentUploadedPath("");
		row.setUploaded(false);

		return null;
	}

	public String upload_image(FileUploadEvent e) throws IOException {
		RequestFilter.getSession().setAttribute("imageMat", e.getFile().getContents());
		return null;
	}

	public String uploadIm_Advanced(FileUploadEvent e) throws IOException {
		System.out.println("begin upload image");
		this.setByteDoc(e.getFile().getContents());

		DocumentModel docObj = (DocumentModel) e.getComponent().getAttributes().get("docObj");

		if (e.getFile().getContents() != null)
			docObj.setByteArrayImage(e.getFile().getContents());

		// document = e.getFile();

		docObj.setUploaded(true);
		docObj.setByteArrayImage(e.getFile().getContents());
		docObj.setDocumentUploadedPath("" + e.getFile().getFileName());

		ArrayList<DocumentModel> imagelist = this.imageList;
		if (imagelist != null) {
			this.setImageList(imageList);
		} else {
			imagelist = this.imageList;
		}

		imageList.set(imageList.indexOf(docObj), docObj);
		// RequestFilter.getSession().setAttribute("imageList", imagelist);
		System.out.println("File Uploaded");
		// this.setByteDoc(null);

		return null;
	}

	public String uploadDoc_Advanced(FileUploadEvent e) {
		try {
			DocumentModel docObj = (DocumentModel) e.getComponent().getAttributes().get("docObj");

			if (e.getFile().getContents() != null)
				docObj.setByteArrayImage(e.getFile().getContents());

			document = e.getFile();

			docObj.setUploaded(true);
			String dataPath = "";
			dataPath = System.getProperty("catalina.base");
			dataPath = dataPath + File.separator + "data" + File.separator;
			File dataDir = new File(dataPath);
			if (!dataDir.exists()) {
				dataDir.mkdirs();
			}
			docObj.setDocumentUploadedPath(dataDir.getAbsolutePath() + File.separator + e.getFile().getFileName());
			ArrayList<DocumentModel> documentlist = documentList;
			if (documentlist != null)
				this.setDocumentList(documentlist);

			documentList.set(documentList.indexOf(docObj), docObj);
			// RequestFilter.getSession().setAttribute("documentList", documentList);
			System.out.println("File Uploaded " + docObj.getDocumentUploadedPath());
		} catch (Exception exc) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur de Téléchargement",
					"le fichier n'a pas pu être téléchargé");
			FacesContext.getCurrentInstance().addMessage("errorupload", message);
		}

		return null;
	}

	public String uploadFac_Advanced(FileUploadEvent e) throws IOException {
		DocumentModel docObj = (DocumentModel) e.getComponent().getAttributes().get("docObj");

		if (e.getFile().getContents() != null)
			docObj.setByteArrayImage(e.getFile().getContents());

		docFacture = e.getFile();

		docObj.setUploaded(true);
		docObj.setDocumentUploadedPath("" + e.getFile().getFileName());

		ArrayList<DocumentModel> documentFaclist = documentFacList;
		if (documentFaclist != null)
			this.setDocumentList(documentFaclist);

		documentFacList.set(documentFacList.indexOf(docObj), docObj);
		RequestFilter.getSession().setAttribute("documentFacList", documentFacList);
		System.out.println("Facture Uploaded");
		saveFacFile();
		return null;
	}

	public void saveFacFile() throws IOException {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
		String datetime = ft.format(dNow);

		ArrayList<DocumentModel> documentlist = documentFacList;
		String fileName;
		try {
			if (documentlist != null) {
				for (DocumentModel d : documentlist) {
					InputStream is = docFacture.getInputstream();
					fileName = FilenameUtils.getName(d.getDocumentUploadedPath());
					File fileFac = new File(datetime + "_" + fileName);
					OutputStream out = new FileOutputStream(fileFac);
					facturePath = fileFac.getAbsolutePath();
					int read = 0;
					byte[] bytes = new byte[1024];

					while ((read = is.read(bytes)) != -1) {
						out.write(bytes, 0, read);
					}

					is.close();
					out.flush();
					out.close();

					/*
					 * System.out.println("file name" + fileName); BufferedOutputStream stream;
					 * stream = new BufferedOutputStream(new FileOutputStream(new File(datetime
					 * +"_"+ fileName))); //stream.write(bytes); stream.close();
					 */
				}
			}
		} catch (IOException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error file not found",
					"Facture's file not found ");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Facture's file not found "));
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public String updatePage() throws IOException {

		ArrayList<DocumentModel> list = getDocumentList();

		int i = 0;

		StringBuffer resultTemp = new StringBuffer();

		for (DocumentModel fl : list) {

			i++;

			resultTemp.append(
					i + ". Document Name : " + fl.getDocumentName() + ", File Path: " + fl.getDocumentUploadedPath());
		}

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(resultTemp.toString()));

		// setResult(resultTemp.toString());

		return "success";

	}

	public String uploadFilesDocument() throws IOException {
		String fileName = "";
		/*
		 * ArrayList<UploadedFileByte> uploadedfiles = (ArrayList<UploadedFileByte>)
		 * RequestFilter.getSession() .getAttribute("uploadedFiles");
		 * 
		 * HashMap<String, HashMap<UploadedFile, byte[]>> hashOfhashmapfilebytecontent =
		 * (HashMap<String, HashMap<UploadedFile, byte[]>>) RequestFilter
		 * .getSession().getAttribute("hashmapFileBytecontent");
		 */
		ArrayList<String> filesTozip = new ArrayList<String>();
		ArrayList<DocumentModel> documentlist = this.documentList;
		String dataPath = "";
		dataPath = System.getProperty("catalina.base");
		dataPath = dataPath + File.separator + "data" + File.separator;
		File dataDir = new File(dataPath);
		if (!dataDir.exists()) {
			dataDir.mkdirs();
		}
		// docObj.setDocumentUploadedPath(dataDir.getAbsolutePath()+File.separator +
		// e.getFile().getFileName());
		if (documentlist != null) { // set test for document changed
			for (DocumentModel d : documentlist) {
				File file = new File(dataDir.getAbsolutePath() + File.separator + "resourcesSigma");
				String absolutePath = file.getAbsolutePath();
				String filePath = absolutePath;
				String filePath2 = new File("test").getAbsolutePath();
				byte[] bytes = null;

				if (null != d) {
					bytes = d.getByteArrayImage();
					if (bytes != null) {
						fileName = FilenameUtils.getName(d.getDocumentUploadedPath());
						System.out.println("file name" + fileName);
						BufferedOutputStream stream;
						stream = new BufferedOutputStream(new FileOutputStream(new File(filePath + fileName)));
						filesTozip.add(filePath + fileName);
						stream.write(bytes);
						stream.close();
					}
				}
			}
		} else {
			RequestFilter.getSession().setAttribute("documentpath", "");
			filesTozip.add("");
		}

		zipFiles(filesTozip);
		return SUCCESS;
	}

	public void zipFiles(List<String> files) {

		FileOutputStream fos = null;
		ZipOutputStream zipOut = null;
		FileInputStream fis = null;

		String dataPath = "";
		dataPath = System.getProperty("catalina.base");
		dataPath = dataPath + File.separator + "data" + File.separator;
		File dataDir = new File(dataPath);
		if (!dataDir.exists()) {
			dataDir.mkdirs();
		}
		// docObj.setDocumentUploadedPath(dataDir.getAbsolutePath()+File.separator +
		// e.getFile().getFileName());
		try {
			if (files.size() == 0) {
				RequestFilter.getSession().setAttribute("documentpath", null);
			}

			else {
				Date dNow = new Date();
				SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
				String datetime = ft.format(dNow);

				File file = new File(dataDir.getAbsolutePath() + File.separator + datetime);
				String absolutePath = file.getAbsolutePath();

				RequestFilter.getSession().setAttribute("documentpath", absolutePath + ".zip");
				// eto miset an le documentpath
				String a = (String) RequestFilter.getSession().getAttribute("documentpath");

				fos = new FileOutputStream(dataDir.getAbsolutePath() + File.separator + datetime + ".zip");
				setDocumentPath(dataDir.getAbsolutePath() + File.separator + datetime + ".zip");

				zipOut = new ZipOutputStream(new BufferedOutputStream(fos));
				for (String filePath : files) {
					File input = new File(filePath);
					System.out.println("Zipping the file with path: " + input.getName());

					fis = new FileInputStream(input);
					ZipEntry ze = new ZipEntry(input.getName());

					zipOut.putNextEntry(ze);
					byte[] tmp = new byte[4 * 1024];
					int size = 0;
					while ((size = fis.read(tmp)) != -1) {
						zipOut.write(tmp, 0, size);
					}
					// zipOut.flush();
					fis.close();
					input.delete();
				}
				zipOut.close();
				System.out.println("Done... Zipped the files...");
				RequestFilter.getSession().removeAttribute("documentList");
				// RequestFilter.getSession().removeAttribute("documentpath");
			}
			// response.setHeader("Refresh", "0; URL=http://your-current-page");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur du Téléchargement",
					"Fichier introuvalbe");
			FacesContext.getCurrentInstance().addMessage("errorupload", message);
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur du Téléchargement",
					"Fichier introuvalbe");
			FacesContext.getCurrentInstance().addMessage("errorupload", message);
			e.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (Exception ex) {

			}
		}
	}

	public byte[] getByteDoc() {
		return this.byteDoc;
	}

	public void setByteDoc(byte[] byteDoc) {
		this.byteDoc = byteDoc;
	}

	public String getAutre() {
		return this.autre;
		// return (String) RequestFilter.getSession().getAttribute("autre");
	}

	public void setAutre(String autre) {
		// RequestFilter.getSession().setAttribute("autre", autre);
		this.autre = autre;
	}

	public String getCaracteristique() {
		return caracteristique;
	}

	public void setCaracteristique(String caracteristique) {
		this.caracteristique = caracteristique;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

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

	public Materiel getCurentMateriel() {
		return curentMateriel;
	}

	public void setCurentMateriel(Materiel curentMateriel) {
		RequestFilter.getSession().setAttribute("fileZipPath", curentMateriel.getDesign().getDocumentPath());
		this.curentMateriel = curentMateriel;
		this.setIdMat(curentMateriel.getIdMateriel());
	}

	public void mySetCurentMateriel(Materiel curentMateriel) {
		System.out.println("SET CURENT MATERIEL  ID = " + curentMateriel.getIdMateriel());
		setCurentMateriel(curentMateriel);
		this.setIdMat(curentMateriel.getIdMateriel());
		// this.curentMateriel = curentMateriel;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Direction getDestinationDirec() {
		return destinationDirec;
	}

	public void setDestinationDirec(Direction destinationDirec) {
		this.destinationDirec = destinationDirec;
	}

	public void setListMotifSortie(List<MotifSortie> listMotifSortie) {
		this.listMotifSortie = listMotifSortie;
	}

	public Agent getDetenteur() {
		return this.detenteur;
	}

	public void setDetenteur(Agent detenteur) {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		System.out.println(FacesContext.getCurrentInstance().toString());
		String userId = ec.getRequestParameterMap().get(1);
		Map<String, String> map = ec.getRequestParameterMap();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (entry.getKey().equals("j_idt58:det_input"))
				System.out.println(entry.getKey() + "/" + entry.getValue());
		}
		String userId1 = ec.getRequestParameterMap().getClass().getName();
		this.detenteur = detenteur;
	}

	public Materiel getMateriel() {
		return materiel;
	}

	public void setMateriel(Materiel materiel) {
		this.materiel = materiel;
	}

	// ADD LIST MATERIEL WITH BOUTTON NEXT WITHOUT DESIGNATION
	public String addIntoListMateriel() throws IOException {
		System.out.println("Add it into list materiel");
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");

		uploadFilesDocument();
		ArrayList<DocumentModel> imagelist = this.imageList;
		MaterielNouv m = new MaterielNouv();
		if (imagelist != null) {
			m.setImage(imagelist.get(0).getByteArrayImage());
		} else {
			m.setImage(null);
		}
		m.setAnneeAcquisition(this.getAnneeAcquisition());
		m.setDocumentPath((String) RequestFilter.getSession().getAttribute("documentpath"));
		RequestFilter.getSession().removeAttribute("documentpath");
		m.setAutre(getAutre());

		m.setFournisseur(getFournisseur());
		m.setEtat(getEtat());
		m.setMarque(getMarq());
		m.setNumSerie(getNumSerie());
		m.setPu(getUnitPrice());
		m.setReference(getReference());
		m.setRenseignement(getRenseignement());
		m.setTypematerieladd(this.getTypematerielToAdd());
		m.setNomenMat(this.getTypematerielToAdd().getNomenclaureParent());

		m.setDirec(agent.getDirection());
		m.setEspeceUnite(getEspeceUnite());
		m.setOrigine(getOrigine());
		m.setRefFacture(getRefFacture());
		m.setDirec(agent.getDirection());

		// proprietes propre aux materiels nouveaux
		m.setFinancement(getFinancement());
		m.setFournisseur(getFournisseur());
		m.setModAcq(getAcquisition());
		m.setMontant_facture(getMontantFac());
		m.setValidation(false);

		if (listMaterielForOpEntree == null)
			listMaterielForOpEntree = new ArrayList<Materiel>();

		listMaterielForOpEntree.add(m);
		System.out.println("added to list");
		clearPriseEnCharge();
		this.documentList = initialize();
		this.imageList = initializeImageFile();
		setAllNull();
		return null;
	}

	// AJOUT DES MATERIELS EXISTANTS WITHOUT DESIGNATION
	public String addMateriel() throws IOException {
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");

		System.out.println("ADD MATERIEL");
		try {
			uploadFilesDocument();
			// saveFacFile();

			// ArrayList<DocumentModel> imagelist = (ArrayList<DocumentModel>)
			// RequestFilter.getSession()
			// .getAttribute("imageList");
			// agent.setIp()

			ArrayList<DocumentModel> imagelist = imageList;
			MaterielEx m = new MaterielEx();
			// System.out.println("---------------SIZE IMAGE BYTE
			// ARRAY="+imagelist.get(0).getByteArrayImage().length);
			if (imagelist != null) {
				m.setImage(imagelist.get(0).getByteArrayImage());
			} else {
				m.setImage(null);
			}

			m.setAnneeAcquisition((String) "" + Calendar.getInstance().get(Calendar.YEAR) + "");

			m.setDocumentPath((String) RequestFilter.getSession().getAttribute("documentpath"));
			System.out.println("DOCUMENTPATH========================="
					+ (String) RequestFilter.getSession().getAttribute("documentpath"));
			RequestFilter.getSession().removeAttribute("documentpath");
			m.setAutre(getAutre());
			// m.setBureau(getBureau());
			// m.setDirec(getDirection());
			// m.setDirec(agent.getDirection());
			m.setDirec(agent.getDirection());
			m.setEtat(getEtat());
			m.setMarque(getMarq());
			// m.setNomenMat(getTypemateriel());
			m.setNumSerie(getNumSerie());
			m.setPu(getUnitPrice());
			m.setReference(getReference());
			m.setRenseignement(getRenseignement());
			m.setTypematerieladd(this.getTypematerielToAdd());
			m.setNomenMat(this.getTypematerielToAdd().getNomenclaureParent());
			m.setEspeceUnite(getEspeceUnite());
			m.setOrigine(getOrigine());

			// m.setServ(getServiceforMat());
			// m.setDirec(agent.getDirection());

			// m.setCaract(caract);
			// m.setCategorie(categorie);

			// m.setDocumentPath(documentPath);
			m.setValidation(false);
			if (listMaterielForOpEntree == null)
				listMaterielForOpEntree = new ArrayList<Materiel>();

			listMaterielForOpEntree.add(m);

			// set Operation requete entrer materiel existant
			OpEntree opentree = usermetierimpl.reqEntrerMateriel(listMaterielForOpEntree, agent, getFacturePath(),
					getRefFacture());
			// set Operation valider automatique car ne necessite pas de validation GAC
			usermetierimpl.entrerMateriel(opentree);

			// miboucle list op entree
			for (Materiel ma : listMaterielForOpEntree) {
				if (getDetenteurMatEx() != null) {
					// usermetierimpl.attribuerMaterielEx((MaterielEx) ma,getDetenteurMatEx());
					System.out.println("Begin Attribution");
					OpAttribution oa = usermetierimpl.reqAttribution((MaterielEx) ma, agent, getDetenteurMatEx());
					usermetierimpl.attriuberMateriel(oa);
					System.out.println("End Attribution");
					// m.setDetenteur(getDetenteurMatEx());
				}
			}
			clear();

			// -----------DESTROY ALL SESSION------------------
			RequestFilter.getSession().setAttribute("documentpath", null);
			// RequestFilter.getSession().setAttribute("documentList", null);
			// RequestFilter.getSession().setAttribute("imageList", null);

			listMaterielForOpEntree = null;
			this.documentList = initialize();
			this.imageList = initializeImageFile();
			this.documentFacList = initializeFacFile();
			RequestFilter.getSession().setAttribute("imageList", imageList);
			RequestFilter.getSession().setAttribute("documentList", documentList);

			setAllNull();

			return SUCCESS;
		} catch (JDBCException jdbce) {
			jdbce.getSQLException().getNextException().printStackTrace();
			listMaterielForOpEntree = null;
			return ERROR;
		} catch (IOException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error file not found",
					"Facture's file not found ");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Facture's file not found "));
			listMaterielForOpEntree = null;
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		} catch (NullPointerException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error validating materiel",
					"Error operation");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error operation valeur null"));
			listMaterielForOpEntree = null;
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error validating materiel",
					"Error operation");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error operation exception :" + e.getMessage()));
			listMaterielForOpEntree = null;
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		/*
		 * catch(Exception e){ e.printStackTrace(); e.getNextException(); return ERROR;
		 * }
		 */
	}

	// ADD LISTES DES MATERIELS NOUVEAUX WITHOUT DESIGNATION
	public String addPriseEncharge() {
		try {
			uploadFilesDocument();
			Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
			// agent.setIp()
			ArrayList<DocumentModel> imagelist = this.imageList;

			MaterielNouv m = new MaterielNouv();

			if (imagelist != null) {
				m.setImage(imagelist.get(0).getByteArrayImage());
			} else {
				m.setImage(null);
			}

			m.setDocumentPath((String) RequestFilter.getSession().getAttribute("documentpath"));
			RequestFilter.getSession().removeAttribute("documentpath");

			m.setAutre(getAutre());

			m.setAnneeAcquisition((String) "" + Calendar.getInstance().get(Calendar.YEAR) + "");

			// m.setDirec(getDirection());
			m.setDirec(agent.getDirection());

			m.setEtat(getEtat());

			m.setMarque(getMarq());

			// m.setNomenMat(getTypemateriel());
			m.setTypematerieladd(this.getTypematerielToAdd());
			m.setNomenMat(this.getTypematerielToAdd().getNomenclaureParent());

			m.setEspeceUnite(getEspeceUnite());
			m.setOrigine(getOrigine());

			m.setNumSerie(getNumSerie());

			m.setPu(getUnitPrice());

			m.setReference(getReference());

			m.setRenseignement(getRenseignement());

			m.setRefFacture(getRefFacture());

			m.setDirec(agent.getDirection());

			// m.setCaract(caract);
			// m.setCategorie(categorie);

			// m.setImage((byte[]) RequestFilter.getSession().getAttribute("imageMat"));

			// m.setDocumentPath((String)
			// RequestFilter.getSession().getAttribute("documentpath"));

			// proprietes propre aux materiels nouveaux

			m.setFinancement(getFinancement());

			m.setFournisseur(getFournisseur());

			m.setModAcq(getAcquisition());

			m.setMontant_facture(getMontantFac());

			listMaterielForOpEntree.add(m);
			// m.setRefFacture(refFacture);

			// set Operation requete entrer materiel nouveau
			System.out.println("ready to send request");
			for (Materiel a : listMaterielForOpEntree) {
				a.setAnneeAcquisition((String) "" + Calendar.getInstance().get(Calendar.YEAR) + "");
				System.out.println("materiel: " + a.getNumSerie());
			}

			List<String> facPathList = new ArrayList<String>();

			OpEntree opEntree = usermetierimpl.reqEntrerMateriel(listMaterielForOpEntree, agent,
					(String) RequestFilter.getSession().getAttribute("documentpath"), getRefFacture());

			RequestFilter.getSession().setAttribute("documentpath", null);

			listMaterielForOpEntree = null;
			clear();

			setAllNull();

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error validating materiel",
					e.getMessage());
			// FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error
			// operation "));
			listMaterielForOpEntree = null;
			FacesContext.getCurrentInstance().addMessage(null, message);
			return ERROR;
		}

	}

	// ???????
	public String addPriseEnchargeOp() {
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		OpEntree opEntree = usermetierimpl.reqEntrerMateriel(listMaterielForOpEntree, agent, getFacturePath(),
				getRefFacture());
		return SUCCESS;
	}

	// SEND REQUETE ATTRIBUTION
	public String addAttribution() {
		System.out.println("****************************ADD ATTR**ERRORR********************************");
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		try {
			// getCurrent Materiel ve?????
			System.out.println("****************************ADD2 ATTR**ERRORR*****NULL*************************** "
					+ getMaterielSeclected().getDesign().getNomenMat().getDesignation());
			OpAttribution opAt = usermetierimpl.reqAttribution(getMaterielSeclected(), agent, getDetenteur());
			System.out.println("****************************ADD3 ATTR**ERRORR********************************");
			clear();
			setAllNull();
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception

			/*
			 * e.printStackTrace(); System.out.println(e.getMessage());
			 */
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de l'attribution",
					"Certaines champs ne respectent pas les contraintes");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return ERROR;
		}

	}

	// ADD REQUETE DETTACHEMENT
	public String addDetachement1(Agent dettest) {
		Agent a = dettest;
		return SUCCESS;
	}

	public String addDetachement() {
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		// agent.setIp()
		OpDettachement opDet = null;
		System.out.println("****************************ADD DETACHEMENT********************************");
		try {
			// getCurrent Materiel ve?????
			opDet = usermetierimpl.reqDettachement(this.getMaterielSeclectedDett(), agent, getDetenteurDett(),
					this.getMotifDettachement());
			setAllNull();
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors du déttachement",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			return ERROR;
		}

	}

	// ADD REQUETE DECHARGE
	public String addDecharge() {
		System.out.println("Decharge begin");
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		// agent.setIp()
		OpSortie opSort = null;
		try {
			if (getDestinationDirec() == null) {
				opSort = usermetierimpl.reqSortirMateriel(this.getMateriel(), this.getMotifSortie(), agent);
			} else {
				opSort = usermetierimpl.reqSortirMateriel(this.getMateriel(), this.getMotifSortie(),
						this.getDestinationDirec(), agent);
			}

			setAllNull();

			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de la décharge",
					"Certaines champs ne respectent pas les contraintes ");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return ERROR;
		}

	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	// ------------------------------PRIME
	// FACES---------------------------------------
	// list service
	// list nomenclature
	// list marque
	// list etat

	private Nomenclature nomenclaturePrim;
	private List<Nomenclature> nomenclaturesPrim;

	private Marque marquePrim;
	private List<Marque> marquesPrim;

	private EtatMateriel etatMaterielPrim;
	private List<EtatMateriel> etatMaterielsPrim;

	private TypeObjet typeObjet;
	private CodeArticle codeArticle;

	public List<Article> getListArticle() {
		return listArticle;
	}

	public void setListArticle(List<Article> listArticle) {
		this.listArticle = listArticle;
	}

	public List<ArticleNouv> getListArticleNouv() {
		return listArticleNouv;
	}

	public void setListArticleNouv(List<ArticleNouv> listArticleNouv) {
		this.listArticleNouv = listArticleNouv;
	}

	private List<Article> listArticle;
	private List<ArticleNouv> listArticleNouv;

	public EtatMateriel getEtatMaterielPrim() {
		return etatMaterielPrim;
	}

	public void setEtatMaterielPrim(EtatMateriel etatMaterielPrim) {
		this.etatMaterielPrim = etatMaterielPrim;
	}

	public Marque getMarquePrim() {
		return marquePrim;
	}

	public void setMarquePrim(Marque marquePrim) {
		this.marquePrim = marquePrim;
	}

	public Nomenclature getNomenclaturePrim() {
		return nomenclaturePrim;
	}

	public void setNomenclaturePrim(Nomenclature nomenclaturePrim) {
		this.nomenclaturePrim = nomenclaturePrim;
	}

	public List<Nomenclature> getNomenclaturesPrim() {
		return nomenclaturesPrim;
	}

	public void setNomenclaturesPrim(List<Nomenclature> nomenclaturesPrim) {
		this.nomenclaturesPrim = nomenclaturesPrim;
	}

	public List<Marque> getMarquesPrim() {
		return marquesPrim;
	}

	public void setMarquesPrim(List<Marque> marquesPrim) {
		this.marquesPrim = marquesPrim;
	}

	public List<EtatMateriel> getEtatMaterielsPrim() {
		return etatMaterielsPrim;
	}

	public void setEtatMaterielsPrim(List<EtatMateriel> etatMaterielsPrim) {
		this.etatMaterielsPrim = etatMaterielsPrim;
	}

	// -------------NEW EDIT------------------
	// Affichage automatique de la marque, de la référence et du numéro de série
	private Materiel materielSeclected;
	private Marque marqueAutom;
	private String referenceAutom;
	private String codificationAutom;
	private String origine;
	private String especeUnite;
	private String numSerieAutom;
	private String nomenclatureAutom;
	private List<Referentiel> listDestinaiton;

	public void onChangeMateriel() {
		if (getMaterielSeclected() == null) {
			marqueAutom = null;
			setReferenceAutom(null);
			setNumSerie(null);
			setNomenclatureAutom(null);
			setCodificationAutom(null);
		}

	}

	public void setListDestinaiton(List<Referentiel> listDestinaiton) {
		this.listDestinaiton = listDestinaiton;
	}

	public String getReferenceAutom() {
		return this.referenceAutom;
	}

	public void setReferenceAutom(String referenceAutom) {
		this.referenceAutom = referenceAutom;
	}

	public String getOrigine() {
		return this.origine;
	}

	public void setOrigine(String o) {
		this.origine = o;
	}

	public String getEspeceUnite() {
		return this.especeUnite;
	}

	public void setEspeceUnite(String e) {
		this.especeUnite = e;
	}

	public String getNumSerieAutom() {
		return this.numSerieAutom;
	}

	public void setNumSerieAutom(String numSerieAutom) {
		this.numSerieAutom = numSerieAutom;
	}

	public Marque getMarqueAutom() {
		return this.marqueAutom;
	}

	public void setMarqueAutom(Marque marqueAutom) {
		this.marqueAutom = marqueAutom;
	}

	public Materiel getMaterielSeclected() {
		return materielSeclected;
	}

	public void setMaterielSeclected(Materiel materielSeclected) {
		this.materielSeclected = materielSeclected;
	}

	public MotifSortie getMotifSortie() {
		return motifSortie;
	}

	public void setMotifSortie(MotifSortie motifSortie) {
		this.motifSortie = motifSortie;
	}

	public List<Materiel> getListAllMaterilValide() {
		// System.out.println("----size------"+usermetierimpl.getMatByValidation(true).size());
		List<Materiel> listM = usermetierimpl.getListMat();
		System.out.println("----size listal------" + listM.size());
		List<Materiel> l = new ArrayList<Materiel>();
		for (Materiel m : listM) {
			System.out.println("----validation each list------" + m.isValidation());
			if (m.isValidation()) {
				l.add(m);
			}
		}
		System.out.println("----size l------" + l.size());
		return l;
		// return usermetierimpl.getMatByValidation(true);
	}

	public void setListAllMaterilValide(List<Materiel> listAllMaterilValide) {
		this.listAllMaterilValide = listAllMaterilValide;
	}

	public ArrayList<DocumentModel> getImageList() {
		return imageList;
	}

	public void setImageList(ArrayList<DocumentModel> imageList) {
		this.imageList = imageList;
	}

	public List<Materiel> getListAllMaterielValideSansDetenteur() {
		return usermetierimpl.getMatByDetenteurAndValidation(null, true);
	}

	public void setListAllMaterielValideSansDetenteur(List<Materiel> listAllMaterielValideSansDetenteur) {
		this.listAllMaterielValideSansDetenteur = listAllMaterielValideSansDetenteur;
	}

	public List<Materiel> getCurrentListMateriel() {
		/*
		 * // System.out.println("****************************SET LIST //
		 * ******************************** " +this.getClass().getName()); Long idAg =
		 * Long.valueOf(1); ExternalContext ec =
		 * FacesContext.getCurrentInstance().getExternalContext();
		 * 
		 * String userId = ec.getRequestParameterMap().get(1); Map<String, String> map =
		 * ec.getRequestParameterMap(); String input = ":det_input";
		 * 
		 * if(map!=null) { for (Map.Entry<String, String> entry : map.entrySet()) {
		 * 
		 * if (entry.getKey().toLowerCase().contains(input.toLowerCase())) idAg =
		 * Long.parseLong(entry.getValue());
		 * 
		 * // System.out.println(entry.getKey() + "/" + entry.getValue()); }
		 * //System.out.println(FacesContext.getCurrentInstance().toString()+" ITO AZA"
		 * );
		 * 
		 * if (!idAg.equals(Long.valueOf(1))) { System.out.println(
		 * "****************************SET LIST ******************************** " +
		 * this.getClass().getName()); List<Materiel> result ; try { result =
		 * (List<Materiel>)usermetierimpl.getMatByDetenteurAndValidation(usermetierimpl.
		 * findAgentByIm(idAg),true); if(result==null) { result = new
		 * ArrayList<Materiel>(); } for(Materiel mat : result) {
		 * System.out.println("list a "+mat.getIdMateriel()); }
		 * System.out.println("tsy error lust"); }catch(Exception e) {
		 * System.out.println("error lust"); result = new ArrayList<Materiel>(); }
		 * System.out.println("lasa"); return result;
		 * 
		 * 
		 * } else { System.out.println(
		 * "****************************SET LISTA ******************************** " +
		 * this.getClass().getName()); return new ArrayList<Materiel>(); } } else {
		 * System.out.println(
		 * "****************************SET LISTA ******************************** " +
		 * this.getClass().getName()); return new ArrayList<Materiel>(); } /*if
		 * (this.detenteur == null) { System.out.
		 * println("****************************SET LISTNULL ******************************** "
		 * ); this.setCurrentListMateriel(new ArrayList<Materiel>()); this.setNom(null);
		 * this.setPrenom(null); } else { this.detenteur = this.getDetenteur();
		 * this.setNom(detenteur.getNomAgent());
		 * this.setPrenom(detenteur.getPrenomAgent()); System.out.
		 * println("****************************SET LISTA ******************************** "
		 * + this.getDetenteur().getNomAgent()); this.setCurrentListMateriel(
		 * (List<Materiel>)
		 * usermetierimpl.getMatByDetenteurAndValidation(this.getDetenteur(), true));
		 * System.out.
		 * println("****************************SET LISTA ******************************** "
		 * + this.getDetenteur().getIm()); }
		 */
		if (this.detenteurDett == null) {
			currentListMateriel = new ArrayList<Materiel>();
		}

		return currentListMateriel;

	}

	public void setCurrentListMateriel(List<Materiel> currentListMateriel) {
		this.currentListMateriel = currentListMateriel;
	}

	/*
	 * public void mySetCurrentListMateriel1(ValueChangeEvent evt) {
	 * System.out.println(
	 * "****************************SET LIST ******************************** " +
	 * this.getClass().getName()); ValueChangeEvent e = evt;
	 * this.setDetenteur((Agent) evt.getNewValue()); this.setCurrentListMateriel(
	 * (List<Materiel>)
	 * usermetierimpl.getMatByDetenteurAndValidation(this.getDetenteur(), true)); }
	 */
	// ActionEvent actionEvent
	/*
	 * public void mySetCurrentListMateriel(ValueChangeEvent evt) { ValueChangeEvent
	 * e = evt; if(evt.getNewValue()!=null) { this.setDetenteur((Agent)
	 * evt.getNewValue()); }
	 * 
	 * if (this.detenteur == null) { this.setCurrentListMateriel(null);
	 * this.setNom(null); this.setPrenom(null); } else { this.detenteur =
	 * this.getDetenteur(); this.setNom(detenteur.getNomAgent());
	 * this.setPrenom(detenteur.getPrenomAgent()); System.out.
	 * println("****************************SET LISTA ******************************** "
	 * + this.getDetenteur().getNomAgent()); this.setCurrentListMateriel(
	 * (List<Materiel>)
	 * usermetierimpl.getMatByDetenteurAndValidation(this.getDetenteur(), true));
	 * System.out.
	 * println("****************************SET LISTA ******************************** "
	 * + this.getDetenteur().getIm()); } }
	 */
	public void mySetCurrentListMateriel() {

		if (this.detenteurDett == null) {
			System.out.println("****************************SET LISTNULLMYSET ******************************** ");
			this.setCurrentListMateriel(new ArrayList<Materiel>());
			this.setNom(null);
			this.setPrenom(null);
		} else {
			System.out.println("****************************SET LISTA ******************************** "
					+ this.detenteurDett.getNomAgent());
			this.setCurrentListMateriel(
					(List<Materiel>) usermetierimpl.getMatByDetenteurAndValidation(this.detenteurDett, true));
			System.out.println("****************************SET LISTA ******************************** "
					+ this.detenteurDett.getIm());
		}
	}

	/*
	 * public void setListLocalite(List<Localite> listLocalite) { this.listLocalite
	 * = listLocalite; }
	 */

	public List<MotifSortie> getListMotifSortie() {
		ArrayList<Referentiel> r = (ArrayList<Referentiel>) refmetierimpl.listRef(new MotifSortie());
		List<MotifSortie> ds = new ArrayList<MotifSortie>();
		for (Object d : r) {
			if (d instanceof MotifSortie) {
				ds.add((MotifSortie) d);
			}
		}
		return ds;
	}

	public void test() {
		System.out.println(
				"***************************SET LIST ******************************** " + this.getClass().getName());
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

	public String getNomenclatureAutom() {
		return nomenclatureAutom;
	}

	private int fileZipSize;

	public int getFileZipSize() throws IOException {
		try {
			if (RequestFilter.getSession().getAttribute("fileZipPath") == null)
				return 0;
			InputStream stream = new FileInputStream((String) RequestFilter.getSession().getAttribute("fileZipPath"));
			if (stream == null)
				return 0;
			fileZipSize = stream.available();
			return fileZipSize;
		} catch (Exception e) {
			return 0;
		}
	}

	public void setFileZipSize(int fileZipSize) {
		this.fileZipSize = fileZipSize;
	}

	private InputStream fileDownloadStream;

	public InputStream getFileDownloadStream() throws IOException {
		try {
			if (RequestFilter.getSession().getAttribute("fileZipPath") == null
					|| RequestFilter.getSession().getAttribute("fileZipPath") == "")
				return null;
			FileInputStream stream = new FileInputStream(
					(String) RequestFilter.getSession().getAttribute("fileZipPath"));
			if (stream == null)
				return null;
			if (stream.getChannel().size() == 0)
				return null;
			return stream;
		} catch (Exception e) {
			return null;
		}
	}

	public void setFileDownloadStream(InputStream fileZipSize) {
		this.fileDownloadStream = fileZipSize;
	}

	public void setNomenclatureAutom(String nomenclatureAutom) {
		this.nomenclatureAutom = nomenclatureAutom;
	}

	// -------DOWNLOAD FILE ZIP-----------
	private StreamedContent filedownload;

	public void beforeDown() {
		InputStream stream = this.getClass().getResourceAsStream("/chapter7/PFSamplePDF.pdf");
		filedownload = new DefaultStreamedContent(stream, "application/pdf", "PFSample.pdf");
	}

	public StreamedContent getFiledownload() throws IOException {
		if (getFileZipPath() == null || getFileZipPath() == "")
			return null;
		try {

			FileInputStream fstream = new FileInputStream(getFileZipPath());
			if (fstream == null)
				return null;
			if (fstream.getChannel().size() == 0) {
				System.out.println("file zip path :" + getFileZipPath());
				return null;
			}
			InputStream stream = new FileInputStream(getFileZipPath());
			fileZipSize = stream.available();
			filedownload = new DefaultStreamedContent(stream, "application/zip", "doc.zip");
		} catch (FileNotFoundException f) {
			return null;
		}
		// RequestFilter.getSession().setAttribute("fileZipPath",null);
		return filedownload;
	}

	public String getFilePathsecond() {
		return filePathsecond;
	}

	public void setFilePathsecond(String filePathsecond) {
		this.filePathsecond = filePathsecond;
	}

	private String filePathsecond;

	/*
	 * public ArticleNouv addArticleNouv(CodeArticle cde, Agent ben, Agent depo,
	 * Fournisseur fourn, Double prix, Long nombre) { ArticleNouv an =
	 * usermetierimpl.addArticleNouv( cde, ben, depo, fourn, prix, nombre); return
	 * an; }
	 * 
	 * public ArticleEx ticleEx(CodeArticle cde, Agent ben, Agent depo, Double prix,
	 * Long nombre) { ArticleEx ae = usermetierimpl.addArticleEx( cde, ben, depo,
	 * prix, nombre); return ae; }
	 */

	public TypeObjet getTypeObjet() {
		return typeObjet;
	}

	public void setTypeObjet(TypeObjet typeObjet) {
		this.typeObjet = typeObjet;
	}

	public CodeArticle getCodeArticle() {
		return codeArticle;
	}

	public void setCodeArticle(CodeArticle codeArticle) {
		// test
		this.codeArticle = codeArticle;
	}

	TypeMateriel typematerielToAdd;

	public TypeMateriel getTypematerielToAdd() {
		return typematerielToAdd;
	}

	public void setTypematerielToAdd(TypeMateriel typematerielToAdd) {
		this.typematerielToAdd = typematerielToAdd;
	}

	// -----------------GRAND II-----------------------------
	Double prix;

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	Long nombre;

	public Long getNombre() {
		return nombre;
	}

	public void setNombre(Long n) {
		this.nombre = n;
	}

	// public String addArticleEx() {
	// try {
	// clear();
	// ArticleEx a = new ArticleEx();
	//
	// Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
	// a.setCodeArticle(getCodeArticle());
	// a.setMarqueArticle(getMarq());
	// a.setCaracteristiqueArticle(getRenseignement());
	//
	// a.setDirecArt(agent.getDirection());
	// a.setValidation(true);
	//
	// a.setNombre(getNombre());
	//
	// // a.setTypeObjet(getTypeObjet());
	// OpEntreeArticle oeart = usermetierimpl.reqEntrerArticle(a, agent);
	// usermetierimpl.entrerArticle(oeart);
	//
	// } catch (IOException e) {
	// FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error
	// file not found",
	// "Facture's file not found ");
	// FacesContext.getCurrentInstance().addMessage(null, new
	// FacesMessage("Facture's file not found "));
	// listMaterielForOpEntree = null;
	// FacesContext.getCurrentInstance().addMessage(null, message);
	// return null;
	// }
	//
	// return SUCCESS;
	// }

	public String addArticleNouv() {
		try {
			ArticleNouv a = new ArticleNouv();

			Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
			a.setCodeArticle(getCodeArticle());
			a.setFournisseur(getFournisseur());
			a.setPrix(getPrix());
			a.setFinancementArt(getFinancement());

			a.setDirecArt(agent.getDirection());

			a.setMarqueArticle(getMarq());
			a.setCaracteristiqueArticle(getRenseignement());

			a.setOrigine(this.getMyorigine());
			a.setReference(this.getMyref());
			a.setEspeceunit(this.getMyesp());
			// a.setModAcq(getAcquisition());

			a.setNombre(getNombre());

			usermetierimpl.reqEntrerArticle(a, agent);
			clear();
		} catch (IOException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error file not found",
					"Facture's file not found ");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Facture's file not found "));
			listMaterielForOpEntree = null;
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		return SUCCESS;
	}

	// sortie
	private Agent agentDest;

	public Agent getAgentDest() {
		return agentDest;
	}

	public void setAgentDest(Agent agentDest) {
		this.agentDest = agentDest;
	}

	private ArticleNouv articleNouv;

	public void setArticleNouv(ArticleNouv a) {
		this.articleNouv = a;
	}

	public ArticleNouv getArticleNouv() {
		return articleNouv;
	}

	/*
	 * public String addRequeteSortieNouv() throws Exception { // ArticleNouv a =
	 * new ArticleNouv();
	 * 
	 * Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent"); //
	 * a.setFournisseur(getFournisseur()); // a.setPrix(getPrix());
	 * 
	 * usermetierimpl.reqSortirArticle(this.getArticle(), agent, getAgentDest());
	 * return SUCCESS; }
	 */

	public List<Materiel> getListAllMaterielValideSansDetenteurByDirection() {
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		// return usermetierimpl.getMatByDetenteurAndDirection(null,
		// agent.getDirection());
		return usermetierimpl.getMatByValidationAndDetenteurAndDirection(true, null, agent.getDirection());
	}

	public void setListAllMaterielValideSansDetenteurByDirection(
			List<Materiel> listAllMaterielValideSansDetenteurByDirection) {
		this.listAllMaterielValideSansDetenteurByDirection = listAllMaterielValideSansDetenteurByDirection;
	}

	private List<Materiel> listAllMaterielValideSansDetenteurByDirection;

	// ------TODO BY PRIORITE---------------
	private List<Agent> listDetenteurMatEx;

	private Agent detenteurMatEx;

	private ArrayList<Materiel> listMaterielForOpEntree;

	@PostConstruct
	public void init() {
		listMaterielForOpEntree = new ArrayList<Materiel>();
	}

	private String facturePath;

	private UploadedFile docFacture;

	private Materiel matForEntree;

	public void setListDetenteurMatEx(List<Agent> listDetenteurMatEx) {
		this.listDetenteurMatEx = listDetenteurMatEx;

	}

	public Agent getDetenteurMatEx() {
		return detenteurMatEx;
	}

	public void setDetenteurMatEx(Agent detenteurMatEx) {
		this.detenteurMatEx = detenteurMatEx;
	}

	public List<Materiel> getListMaterielForOpEntree() {
		return listMaterielForOpEntree;
	}

	public void setListMaterielForOpEntree(ArrayList<Materiel> listMaterielForOpEntree) {
		this.listMaterielForOpEntree = listMaterielForOpEntree;
	}

	public String getFacturePath() {
		return facturePath;
	}

	public void setFacturePath(String facturePath) {
		this.facturePath = facturePath;
	}

	public UploadedFile getDocFacture() {
		return docFacture;
	}

	public void setDocFacture(UploadedFile docFacture) {
		this.docFacture = docFacture;
	}

	public ArrayList<DocumentModel> getDocumentFacList() {
		return documentFacList;
	}

	public void setDocumentFacList(ArrayList<DocumentModel> documentFacList) {
		this.documentFacList = documentFacList;
	}

	public Materiel getMatForEntree() {
		return matForEntree;
	}

	public void setMatForEntree(Materiel matForEntree) {
		this.matForEntree = matForEntree;
	}

	private List<Article> listArticleValide;

	private List<Article> listArticleValideByDirecNondetenu;

	private List<Article> listArticleValideByDirec;

	public List<Article> getListArticleValide() {
		return usermetierimpl.getListArticleValideByDirection(getCurrentAgent().getDirection());
	}

	public void setListArticleValide(List<Article> listArticleValide) {
		this.listArticleValide = listArticleValide;
	}

	public List<Article> getListArticleValideByDirecNondetenu() {
		return usermetierimpl.getListArticleNonDetenuValideByDirection(getCurrentAgent().getDirection());
	}

	public void setListArticleValideByDirecNondetenu(List<Article> listArticleValide) {
		this.listArticleValideByDirecNondetenu = listArticleValide;
	}

	public List<Article> getListArticleValideByDirec() {
		return usermetierimpl.getListArticleByValidationByDirection(true, getCurrentAgent().getDirection());
	}

	public void setListArticleValideByDirec(List<Article> listArticleValideByDirec) {
		this.listArticleValideByDirec = listArticleValideByDirec;
	}

	// DEBUG ERREUR CURRENT MATERIEL
	private MaterielEx curentMaterielEx;

	public Materiel getCurentMaterielEx() {
		return curentMaterielEx;
	}

	public void setCurentMaterielEx(MaterielEx curentMaterielEx) {
		RequestFilter.getSession().setAttribute("fileZipPath", curentMaterielEx.getDesign().getDocumentPath());
		this.curentMaterielEx = curentMaterielEx;
		this.setIdMat(curentMaterielEx.getIdMateriel());
	}

	private MaterielNouv curentMaterielNouv;

	public Materiel getCurentMaterielNouv() {
		return curentMaterielNouv;
	}

	public void setCurentMaterielNouv(MaterielNouv curentMaterielNouv) {
		RequestFilter.getSession().setAttribute("fileZipPath", curentMaterielNouv.getDesign().getDocumentPath());
		this.curentMaterielNouv = curentMaterielNouv;
		this.setIdMat(curentMaterielNouv.getIdMateriel());
	}

	public String getCodificationAutom() {
		return codificationAutom;
	}

	public void setCodificationAutom(String codificationAutom) {
		this.codificationAutom = codificationAutom;
	}

	public void setCurentNull() {
		this.curentMaterielNouv = null;

		this.curentMaterielEx = null;

		this.idMat = null;
	}

	/*
	 * ADD DESCRIPTION EN COURS
	 **/
	public String addMaterielExistant() throws IOException {
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");

		System.out.println("ADD MATERIEL");
		try {
			uploadFilesDocument();

			ArrayList<DocumentModel> imagelist = imageList;

			/**
			 * CREATTION DE LA DESIGNATION ASSOCIEE
			 */
			// MaterielEx m = new MaterielEx();
			Designation des = new Designation();
			// System.out.println("---------------SIZE IMAGE BYTE
			// ARRAY="+imagelist.get(0).getByteArrayImage().length);
			if (imagelist != null) {
				// m.setImage(imagelist.get(0).getByteArrayImage());
				des.setImage(imagelist.get(0).getByteArrayImage());
			} else {
				// m.setImage(null);
				des.setImage(null);
			}

			des.setAnneeAcquisition(getAnneeAcquisition());

			des.setDocumentPath((String) RequestFilter.getSession().getAttribute("documentpath"));
			RequestFilter.getSession().removeAttribute("documentpath");
			des.setAutre(getAutre());
			des.setMarque(getMarq());
			des.setPu(getUnitPrice());
			des.setRenseignement(getRenseignement());
			des.setTypematerieladd(this.getTypematerielToAdd());
			des.setNomenMat(this.getTypematerielToAdd().getNomenclaureParent());
			des.setEspeceUnite(getEspeceUnite());
			des.setOrigine(getOrigine());
			des.setRefFacture(getRefFacture());

			/**
			 * ENTRER DES MATERIELS EXISTANTS QUI BOUCLENT SUIVANT Nombre par Type Voir: en
			 * bas addNewMateriel() et ArrayList<Materiel> materielspardesignation
			 */

			ArrayList<MaterielEx> listematerielParDesign = new ArrayList<MaterielEx>();
			for (Materiel m : this.getMaterielspardesignation()) {
				m.setValidation(true);
				m.setEtat(getEtat());
				listematerielParDesign.add((MaterielEx) m);
				System.out.println("Materiel :" + m.getNumSerie());
				m.setDirec(agent.getDirection());
				m.setTypematerieladd(this.getTypematerielToAdd());
			}

			/*
			 * Entrer les materiels existants sans creer une operation entrée
			 * 
			 */
			usermetierimpl.entrerMaterielExistant(des, listematerielParDesign, agent);

			/*
			 * Attribution si detenteurMatex existe
			 */

			// miboucle list op entree

			/*
			 * for (Materiel ma : listMaterielForOpEntree) { if (getDetenteurMatEx() !=
			 * null) { // usermetierimpl.attribuerMaterielEx((MaterielEx)
			 * ma,getDetenteurMatEx()); System.out.println("Begin Attribution");
			 * OpAttribution oa = usermetierimpl.reqAttribution((MaterielEx) ma, agent,
			 * getDetenteurMatEx()); usermetierimpl.attriuberMateriel(oa);
			 * System.out.println("End Attribution"); //
			 * m.setDetenteur(getDetenteurMatEx()); } }
			 */

			clear();

			// -----------DESTROY ALL SESSION------------------
			RequestFilter.getSession().setAttribute("documentpath", null);
			RequestFilter.getSession().setAttribute("documentList", null);
			RequestFilter.getSession().setAttribute("imageList", null);
			listMaterielForOpEntree = null;
			materielspardesignation = new ArrayList<Materiel>();
			this.documentList = initialize();
			this.imageList = initializeImageFile();
			this.documentFacList = initializeFacFile();

			setAllNull();

			return SUCCESS;
		} catch (JDBCException jdbce) {
			jdbce.getSQLException().getNextException().printStackTrace();
			listMaterielForOpEntree = null;
			return ERROR;
		} catch (IOException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur Fichier inconnue",
					"Erreur lors de l'upload des fichiers.  ");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Erreur lors de l'upload des fichiers "));
			listMaterielForOpEntree = null;
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
			return null;
		} catch (NullPointerException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erreur sur la validation des matériels", "Verfier que les champs sont remplies correctement.");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Erreur sur la validation des matériels"));
			listMaterielForOpEntree = null;
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erreur sur la validation des matériels", "Verfier que les champs sont remplies correctement.");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Verfier que les champs sont remplies correctement."));
			listMaterielForOpEntree = null;
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		/*
		 * catch(Exception e){ e.printStackTrace(); e.getNextException(); return ERROR;
		 * }
		 */
	}

	Map<Designation, List<MaterielNouv>> mappingdeslistmat = new HashMap<Designation, List<MaterielNouv>>();

	// ADD PRISE EN CHARGE DES NOUVEAUX MATERIELS
	public String addPriseEnchargeNouveMat() {
		try {
			uploadFilesDocument();
			Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
			// agent.setIp()
			ArrayList<DocumentModel> imagelist = this.imageList;

			// LAst Desigation
			Designation des = new Designation();

			if (imagelist != null) {
				des.setImage(imagelist.get(0).getByteArrayImage());
			} else {
				des.setImage(null);
			}

			des.setDocumentPath((String) RequestFilter.getSession().getAttribute("documentpath"));
			RequestFilter.getSession().removeAttribute("documentpath");

			des.setAnneeAcquisition((String) "" + Calendar.getInstance().get(Calendar.YEAR) + "");
			des.setAutre(getAutre());
			des.setMarque(getMarq());
			des.setTypematerieladd(this.getTypematerielToAdd());
			des.setNomenMat(this.getTypematerielToAdd().getNomenclaureParent());
			des.setEspeceUnite(getEspeceUnite());
			des.setOrigine(getOrigine());
			des.setPu(getUnitPrice());
			des.setRenseignement(getRenseignement());
			des.setRefFacture(getRefFacture());
			// propore aux materiels nouveaux
			des.setFinancement(getFinancement());
			des.setFournisseur(getFournisseur());
			des.setModAcq(getAcquisition());

			// END FILL DESIGNATION

			ArrayList<MaterielNouv> listematerielParDesign = new ArrayList<MaterielNouv>();
			for (Materiel m : this.getMaterielspardesignation()) {
				m.setValidation(false);// need to be validate
				m.setEtat(getEtat());
				listematerielParDesign.add((MaterielNouv) m);
				System.out.println("Materiel :" + m.getNumSerie());
				m.setDirec(agent.getDirection());
				m.setTypematerieladd(this.getTypematerielToAdd());
			}

			// END LISTE MAT BY DESTINATION

			// CONSTRUCT AN HASMAP
			mappingdeslistmat.put(des, listematerielParDesign);

			// ADD OPERATION ENTREE BASED ON HASHMAP

			List<String> facPathList = new ArrayList<String>();

			OpEntree opEntree = usermetierimpl.reqEntrerMaterielNouv(mappingdeslistmat, agent,
					(String) RequestFilter.getSession().getAttribute("documentpath"), getRefFacture());

			RequestFilter.getSession().setAttribute("documentpath", null);

			clear();
			documentList = initialize();
			imageList = initializeImageFile();
			materielspardesignation = new ArrayList<Materiel>();
			setAllNull();
			mappingdeslistmat = new HashMap<Designation, List<MaterielNouv>>();// reset the hasmap

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error validating materiel",
					e.getMessage());
			// FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error
			// operation "));
			listMaterielForOpEntree = null;
			FacesContext.getCurrentInstance().addMessage(null, message);
			return ERROR;
		}

	}

	public Map<Designation, List<MaterielNouv>> getMappingdeslistmat() {
		return mappingdeslistmat;
	}

	public void setMappingdeslistmat(Map<Designation, List<MaterielNouv>> mappingdeslistmat) {
		this.mappingdeslistmat = mappingdeslistmat;
	}

	private ArrayList<Materiel> materielspardesignation = new ArrayList<Materiel>();
	private MaterielEx matextoadd = new MaterielEx();
	private MaterielNouv matnouvtoadd = new MaterielNouv();

	public String addNewMateriel() throws IOException {
		System.out.println("List Materiel generation ");
		ArrayList<Materiel> list = getMaterielspardesignation();
		list.add(matextoadd);
		for (Materiel m : list) {
			System.out.println(m.getNumSerie());
			System.out.println(m.getReference());
		}
		System.out.println();

		setMaterielspardesignation(list);
		matextoadd = new MaterielEx();// Reset the materiel

		return null;

	}

	public String addNewMaterielNouv() throws IOException {
		System.out.println("List Materiel Nouveau generation ");
		ArrayList<Materiel> list = getMaterielspardesignation();
		if (list == null)
			list = new ArrayList<Materiel>();
		list.add(matnouvtoadd);
		for (Materiel m : list) {
			System.out.println(m.getNumSerie());
			System.out.println(m.getReference());
		}
		System.out.println();

		setMaterielspardesignation(list);
		matnouvtoadd = new MaterielNouv();// Reset the materiel

		return null;

	}

	public void deleteMateriel(int index) {
		System.out.println("List Materiel Nouveau generation to delete ");
		ArrayList<Materiel> list = getMaterielspardesignation();
		list.remove(index);
		for (Materiel m : list) {
			System.out.println(m.getNumSerie());
		}
		System.out.println();

		setMaterielspardesignation(list);
	}

	public ArrayList<Materiel> getMaterielspardesignation() {
		return materielspardesignation;
	}

	public void setMaterielspardesignation(ArrayList<Materiel> materielspardesignation) {
		this.materielspardesignation = materielspardesignation;
	}

	public MaterielEx getMatextoadd() {
		return matextoadd;
	}

	public void setMatextoadd(MaterielEx matextoadd) {
		this.matextoadd = matextoadd;
	}

	public String addIntoHashMapDesListMateriel() throws IOException {
		System.out.println("Add it into hash map designation list materiel");
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");

		uploadFilesDocument();
		ArrayList<DocumentModel> imagelist = this.imageList;
		// MaterielNouv m = new MaterielNouv();
		Designation des = new Designation();
		if (imagelist != null) {
			des.setImage(imagelist.get(0).getByteArrayImage());
		} else {
			des.setImage(null);
		}
		des.setDocumentPath((String) RequestFilter.getSession().getAttribute("documentpath"));
		des.setAnneeAcquisition((String) "" + Calendar.getInstance().get(Calendar.YEAR) + "");
		des.setAutre(getAutre());
		des.setMarque(getMarq());
		des.setTypematerieladd(this.getTypematerielToAdd());
		des.setNomenMat(this.getTypematerielToAdd().getNomenclaureParent());
		des.setEspeceUnite(getEspeceUnite());
		des.setOrigine(getOrigine());
		des.setPu(getUnitPrice());
		des.setRenseignement(getRenseignement());
		des.setRefFacture(getRefFacture());
		// propore aux materiels nouveaux
		des.setFinancement(getFinancement());
		des.setFournisseur(getFournisseur());
		des.setModAcq(getAcquisition());

		ArrayList<MaterielNouv> listematerielParDesign = new ArrayList<MaterielNouv>();
		for (Materiel m : this.getMaterielspardesignation()) {
			m.setValidation(false);// need to be validate
			m.setEtat(getEtat());
			listematerielParDesign.add((MaterielNouv) m);
			System.out.println("Materiel :" + m.getNumSerie());
			m.setDirec(agent.getDirection());
			m.setTypematerieladd(this.getTypematerielToAdd());// For simple request on db
		}

		if (mappingdeslistmat == null)
			mappingdeslistmat = new HashMap<Designation, List<MaterielNouv>>();

		mappingdeslistmat.put(des, listematerielParDesign);
		System.out.println("added to hashmap");
		clearPriseEnCharge();
		des = new Designation();// reset designation
		listematerielParDesign = new ArrayList<MaterielNouv>(); // reset
		materielspardesignation = new ArrayList<Materiel>();// reset
		// this.documentList = initialize();
		this.imageList = initializeImageFile();

		setAllNull();
		return null;
	}

	public MaterielNouv getMatnouvtoadd() {
		return matnouvtoadd;
	}

	public void setMatnouvtoadd(MaterielNouv matnouvtoadd) {
		this.matnouvtoadd = matnouvtoadd;
	}

	public MotifDecharge getMotifDettachement() {
		return motifDettachement;
	}

	public void setMotifDettachement(MotifDecharge motifDettachement) {
		this.motifDettachement = motifDettachement;
	}

	public List<MotifDecharge> getListMotifDettachement() {
		ArrayList<Referentiel> r = (ArrayList<Referentiel>) refmetierimpl.listRef(new MotifDecharge());
		List<MotifDecharge> ds = new ArrayList<MotifDecharge>();
		for (Object d : r) {
			if (d instanceof MotifDecharge) {
				ds.add((MotifDecharge) d);
			}
		}
		return ds;
	}

	public void setListMotifDettachement(List<MotifDecharge> listMotifDettachement) {
		this.listMotifDettachement = listMotifDettachement;
	}

	public List<Materiel> getListMaterielByDirection() {
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		return usermetierimpl.getListMatByDirection(agent.getDirection());
	}

	public void setListMaterielByDirection(List<Materiel> listMaterielByDirection) {
		this.listMaterielByDirection = listMaterielByDirection;
	}

	private List<Materiel> listMaterielByDirection;

	public void updateMaterielExistant(MaterielEx matex) {
		if (matex == null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur materiel", "Materiel null");
			FacesContext.getCurrentInstance().addMessage("editmatexerror", message);

		}
		try {
			matex.getDesign().setNomenMat(matex.getDesign().getTypematerieladd().getNomenclaureParent());
			usermetierimpl.updateMateriel(matex);

		} catch (Exception e) {
			FacesMessage messagea = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur Modification Materiel",
					"Ne respecte pas les contraintes");
			FacesContext.getCurrentInstance().addMessage("editmatexerror", messagea);
		} finally {

			this.curentMateriel = null;
			setCurentNull();
			setAllNull();
		}
	}

	public void updateMaterielNouv(MaterielNouv matNouv) {
		if (matNouv == null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur materiel", "Materiel null");
			FacesContext.getCurrentInstance().addMessage("editmatexerror", message);

		}
		try {
			matNouv.getDesign().setNomenMat(matNouv.getDesign().getTypematerieladd().getNomenclaureParent());

			// plus add document if changed

			/*
			 * uploadFilesDocument(); Agent agent = (Agent)
			 * RequestFilter.getSession().getAttribute("agent"); // agent.setIp()
			 * ArrayList<DocumentModel> imagelist = this.imageList;
			 * 
			 * // LAst Desigation Designation des = new Designation();
			 * 
			 * if (imagelist != null) { des.setImage(imagelist.get(0).getByteArrayImage());
			 * } else { des.setImage(null); }
			 * 
			 * des.setDocumentPath((String)
			 * RequestFilter.getSession().getAttribute("documentpath"));
			 * RequestFilter.getSession().removeAttribute("documentpath");
			 */

			usermetierimpl.updateMateriel(matNouv);

		} catch (Exception e) {
			FacesMessage messagea = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur Modification Materiel",
					"Ne respecte pas les contraintes");
			FacesContext.getCurrentInstance().addMessage("editmatexerror", messagea);
		} finally {

			this.curentMateriel = null;
			setCurentNull();
			setAllNull();
		}
	}

	public ArticleEx getArticleExToAdd() {
		return articleExToAdd;
	}

	public void setArticleExToAdd(ArticleEx articleExToAdd) {
		this.articleExToAdd = articleExToAdd;
	}

	private ArticleEx articleExToAdd = new ArticleEx();

	// REDEFINE
	public String addArticleEx() {
		try {
			clear();

			Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
			/*
			 * a.setCodeArticle(getCodeArticle()); a.setMarqueArticle(getMarq());
			 * a.setCaracteristiqueArticle(getRenseignement()); a.setNombre(getNombre());
			 */

			articleExToAdd.setDirecArt(agent.getDirection());
			articleExToAdd.setValidation(true);

			// a.setTypeObjet(getTypeObjet());
			OpEntreeArticle oeart = usermetierimpl.reqEntrerArticle(articleExToAdd, agent);
			usermetierimpl.entrerArticle(oeart);

		} catch (IOException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error file not found",
					"Facture's file not found ");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Facture's file not found "));
			listMaterielForOpEntree = null;
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		} finally {
			articleExToAdd = new ArticleEx();
		}

		return SUCCESS;
	}

	public Agent getDetenteurDett() {
		return detenteurDett;
	}

	public void setDetenteurDett(Agent detenteurDett) {
		this.detenteurDett = detenteurDett;
	}

	public Materiel getMaterielSeclectedDett() {
		return materielSeclectedDett;
	}

	public void setMaterielSeclectedDett(Materiel materielSeclectedDett) {
		this.materielSeclectedDett = materielSeclectedDett;
	}

	private Agent detenteurDett;
	private Materiel materielSeclectedDett;

	public void updateArticleEx(ArticleEx art) {
		if (art == null) {
			System.out.println("art null");
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur article", "Article null");
			FacesContext.getCurrentInstance().addMessage("editartexerror", message);

		}
		try {
			usermetierimpl.updateArticle(art);

		} catch (Exception e) {
			FacesMessage messagea = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur Modification Article",
					"Ne respecte pas les contraintes");
			FacesContext.getCurrentInstance().addMessage("editartexerror", messagea);
		} finally {

			this.curentArticle = null;
			setCurentNull();
			setAllNull();
		}

	}

	public void updateArticleNouv(ArticleNouv art) {
		if (art == null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur article", "Article null");
			FacesContext.getCurrentInstance().addMessage("editartnouverror", message);

		}
		try {
			usermetierimpl.updateArticle(art);

		} catch (Exception e) {
			FacesMessage messagea = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur Modification Article",
					"Ne respecte pas les contraintes");
			FacesContext.getCurrentInstance().addMessage("editartnouverror", messagea);
		} finally {

			this.curentArticle = null;
			setCurentNull();
			setAllNull();
		}
	}

	public Long getNombreArticleToDep() {
		return nombreArticleToDep;
	}

	public void setNombreArticleToDep(Long nombreArticleToDep) {
		this.nombreArticleToDep = nombreArticleToDep;
	}

	private Long nombreArticleToDep;

	public String addRequeteSortieNouv() {
		// ArticleNouv a = new ArticleNouv();

		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		// a.setFournisseur(getFournisseur());
		// a.setPrix(getPrix());
		System.out.println("request sortie article");
		try {
			// setAgentDest(null);
			// setArticle(null);
			// setNombreArticleToDep((long) 0);
			usermetierimpl.reqSortirArticle(this.getArticle(), agent, getAgentDest(), getNombreArticleToDep(),
					this.getDecision());
			return SUCCESS;
		} catch (Exception e) {
			System.out.println("error sortie article " + e.getMessage());
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur pour sortie de l'article",
					"La requête contient des erreurs");

			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
	}

	public Long calculNombreRestant(Article article) {

		return usermetierimpl.calculArticleRestant(article);
	}

	private CodeArticle articleToFiche;
	private Direction directionToFiche;

	public CodeArticle getArticleToFiche() {
		return articleToFiche;
	}

	public void setArticleToFiche(CodeArticle articleToFiche) {
		this.articleToFiche = articleToFiche;
	}

	public void setArticleToFiche(CodeArticle articleToFiche, Direction d) {
		this.articleToFiche = articleToFiche;
		this.directionToFiche = d;
	}

	public Direction getDirectionToFiche() {
		return this.directionToFiche;
	}

	private ArticleEx articleTomodify;

	public ArticleEx getArticleTomodify() {
		return articleTomodify;
	}

	public void setArticleTomodify(ArticleEx articleTomodify) {
		this.articleTomodify = articleTomodify;
	}

	private ArticleNouv articleToModifyNouv;

	public ArticleNouv getArticleToModifyNouv() {
		return articleToModifyNouv;
	}

	public void setArticleToModifyNouv(ArticleNouv articleToModifyNouv) {
		this.articleToModifyNouv = articleToModifyNouv;
	}

	private String myorigine;
	private String myref;
	private String myesp;

	public String getMyorigine() {
		return myorigine;
	}

	public void setMyorigine(String myorigine) {
		this.myorigine = myorigine;
	}

	public String getMyref() {
		return myref;
	}

	public void setMyref(String myref) {
		this.myref = myref;
	}

	public String getMyesp() {
		return myesp;
	}

	public void setMyesp(String myesp) {
		this.myesp = myesp;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	private String decision;

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

	private List<Operation> listOperatoinByDirection;

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

	private List<Operation> listOperations;

	public List<Operation> getListOperations() {
		if (listOperations == null) {
			// this.setListOperations(usermetierimpl.getListOp());
			Date date = new Date();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			int year = calendar.get(Calendar.YEAR);
			Date sdate = new GregorianCalendar(year - 2, Calendar.JANUARY, 1).getTime();
			Date edate = new GregorianCalendar(year + 1, Calendar.DECEMBER, 30).getTime();
			Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");

			this.setListOperations(
					usermetierimpl.getListAllOperationByDirectionByYearByDateAsc(agent.getDirection(), sdate, edate));
		}
		return listOperations;
	}

	public void setListOperations(List<Operation> listOperations) {
		this.listOperations = listOperations;
	}

	private List<Operation> listAllOperations;

	public List<Operation> getListAllOperations() {
		if (listAllOperations == null) {
			// this.setListOperations(usermetierimpl.getListOp());
			Date date = new Date();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			int year = calendar.get(Calendar.YEAR);
			Date sdate = new GregorianCalendar(year - 2, Calendar.JANUARY, 1).getTime();
			Date edate = new GregorianCalendar(year + 1, Calendar.DECEMBER, 30).getTime();

			this.setListOperations(usermetierimpl.getListAllOperationByYearByDateAsc(sdate, edate));
		}
		return listAllOperations;
	}

	public void setListAllOperations(List<Operation> listOperations) {
		this.listAllOperations = listOperations;
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

	private List<Operation> mesOperations;

	public List<Operation> getMesOperations() {
		if (this.mesOperations == null) {
			Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
			this.setMesOperations(usermetierimpl.getListOpByOperator(agent));
		}
		return mesOperations;
	}

	public void setMesOperations(List<Operation> mesOperations) {
		this.mesOperations = mesOperations;
	}

	private List<Operation> listOperatoinByDirectionFiltered;

	public List<Operation> getListOperatoinByDirectionFiltered() {
		return listOperatoinByDirectionFiltered;
	}

	public void setListOperatoinByDirectionFiltered(List<Operation> listOperatoinByDirectionFiltered) {
		this.listOperatoinByDirectionFiltered = listOperatoinByDirectionFiltered;
	}

	public void deleteMaterielEx(Materiel m) {
		try {
			System.out.println("deleting " + m.getReference());
			usermetierimpl.delMat(m);
			FacesMessage msg = new FacesMessage("Matériel ", m.getReference() + " supprimé");
			FacesContext.getCurrentInstance().addMessage("deleteMateriel", msg);
		} catch (Exception ex) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Matériel non supprimé ",
					m.getReference() + " ne peut pas être supprimé car en cours d'utilisation ");
			FacesContext.getCurrentInstance().addMessage("deleteMateriel", message);
		}

	}

	public void deleteMaterielNouv(Materiel m) {
		/*
		 * try { System.out.println("deleting "+ m.getReference());
		 * usermetierimpl.delMat(m); FacesMessage msg = new FacesMessage("Matériel ",
		 * m.getReference() + " supprimé");
		 * FacesContext.getCurrentInstance().addMessage("deleteMaterielnouv", msg); }
		 * catch (Exception ex) {
		 */
		System.out.println("Failed deleting " + m.getReference());
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Matériel non supprimé ",
				m.getReference() + " ne peut pas être supprimé car en cours d'utilisation ");
		FacesContext.getCurrentInstance().addMessage("deleteMaterielnouv", message);
		// }

	}

	public void upateMaterielImage(Materiel m) {
		ArrayList<DocumentModel> imagelist = this.imageList;

		// LAst Desigation
		// Designation des = new Designation();
		try {
			if (imagelist != null) {
				m.getDesign().setImage(imagelist.get(0).getByteArrayImage());
				usermetierimpl.updateMateriel(m);
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aucune image",
						"Aucune image a été téléchargée");
				FacesContext.getCurrentInstance().addMessage("imageuploaderror", message);
			}
			clear();

			// -----------DESTROY ALL SESSION------------------
			RequestFilter.getSession().setAttribute("documentpath", null);
			RequestFilter.getSession().setAttribute("documentList", null);
			RequestFilter.getSession().setAttribute("imageList", null);
			listMaterielForOpEntree = null;
			materielspardesignation = new ArrayList<Materiel>();
			this.documentList = initialize();
			this.imageList = initializeImageFile();
			this.documentFacList = initializeFacFile();
		} catch (JDBCException jdbce) {
			jdbce.getSQLException().getNextException().printStackTrace();
			listMaterielForOpEntree = null;
			
		} catch (IOException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur Fichier inconnue",
					"Erreur lors de l'upload des fichiers.  ");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Erreur lors de l'upload des fichiers "));
			listMaterielForOpEntree = null;
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
			
		} catch (NullPointerException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erreur sur la validation des matériels", "Verfier que les champs sont remplies correctement.");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Erreur sur la validation des matériels"));
			listMaterielForOpEntree = null;
			FacesContext.getCurrentInstance().addMessage(null, message);
			
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erreur sur la validation des matériels", "Verfier que les champs sont remplies correctement.");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Verfier que les champs sont remplies correctement."));
			listMaterielForOpEntree = null;
			FacesContext.getCurrentInstance().addMessage(null, message);
			
		}

		setAllNull();
	}
	
	public void upateMaterielDoc(Materiel m) {

		try {
			uploadFilesDocument();
			m.getDesign().setDocumentPath((String) RequestFilter.getSession().getAttribute("documentpath"));
			RequestFilter.getSession().removeAttribute("documentpath");
			usermetierimpl.updateMateriel(m);
			
			clear();

			// -----------DESTROY ALL SESSION------------------
			RequestFilter.getSession().setAttribute("documentpath", null);
			RequestFilter.getSession().setAttribute("documentList", null);
			RequestFilter.getSession().setAttribute("imageList", null);
			listMaterielForOpEntree = null;
			materielspardesignation = new ArrayList<Materiel>();
			this.documentList = initialize();
			this.imageList = initializeImageFile();
			this.documentFacList = initializeFacFile();
		} catch (JDBCException jdbce) {
			jdbce.getSQLException().getNextException().printStackTrace();
			listMaterielForOpEntree = null;
			
		} catch (IOException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur Fichier inconnue",
					"Erreur lors de l'upload des fichiers.  ");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Erreur lors de l'upload des fichiers "));
			listMaterielForOpEntree = null;
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
			
		} catch (NullPointerException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erreur sur la validation des matériels", "Verfier que les champs sont remplies correctement.");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Erreur sur la validation des matériels"));
			listMaterielForOpEntree = null;
			FacesContext.getCurrentInstance().addMessage(null, message);
			
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erreur sur la validation des matériels", "Verfier que les champs sont remplies correctement.");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Verfier que les champs sont remplies correctement."));
			listMaterielForOpEntree = null;
			FacesContext.getCurrentInstance().addMessage(null, message);
			
		}

		setAllNull();
	}

}
