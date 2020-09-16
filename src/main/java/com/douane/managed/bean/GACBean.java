package com.douane.managed.bean;

import com.douane.entite.*;
import com.douane.metier.user.IUserMetier;
import com.douane.repository.OpRepository;
import com.douane.requesthttp.RequestFilter;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/*__________itext pdf____________*/

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

import com.sun.faces.facelets.util.Path;
import org.apache.commons.io.FileUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@SessionScoped
/**
 * Created by hasina on 11/3/17.
 */

@ManagedBean(name = "gacBean")

public class GACBean {
	@ManagedProperty(value = "#{usermetier}")
	IUserMetier usermetierimpl;
	
	@ManagedProperty(value="#{ordreEntree}")
    ordreEntreeBean ordreEntreeBean;

	// @ManagedProperty(value="#{suivieditionBean}")
	// private SuiviEditionBean suivibean;

	public ordreEntreeBean getOrdreEntreeBean() {
		return ordreEntreeBean;
	}

	public void setOrdreEntreeBean(ordreEntreeBean ordreEntreeBean) {
		this.ordreEntreeBean = ordreEntreeBean;
	}

	private List<Agent> listAgent;

	private HashMap<Agent, List<Operation>> AgentOp = new HashMap<Agent, List<Operation>>();
	private Agent gac;

	private Operation curentOperation;

	private OpSortie curentopSortieForValidation;

	private Operation curentOperation1;

	private List<Operation> listAllOperation;

	private List<Materiel> listMaterielByDet;

	private int annee;

	private List<Integer> listAnnee;

	private String motif;

	private Double total;


	private List<Operation> listOperationByDirectionByYearByDateAsc;

	private List<Object[]> listeInventaire;

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

	public HashMap<Agent, List<Operation>> getOperationAndDepositaire() {
		// first get direction of GAC
		gac = (Agent) RequestFilter.getSession().getAttribute("agent");
		// get all operation request for each agent
		listAgent = getAllAgents();
		for (Agent a : listAgent) {
			if (a.getDirection().getCodeDirection().equals(gac.getDirection().getCodeDirection())) {
				AgentOp.put(a, usermetierimpl.getListOpByOperator(a));
			}
		}

		return AgentOp;
	}

	public void validatePrisEnChargeEntreMat(Operation op) {
		// usermetierimpl.entrerMateriel(op);
		usermetierimpl.entrerMateriel((OpEntree) op);
		setCurentNull();
		setAllNull();
		this.setCurentOperation(null);

	}

	public void setTotal(Double t) {
		this.total = t;
	}

	public Double getTotal() {
		return this.total;
	}

	public void setAnnee(int t) {
		this.annee = t;
	}

	public String setAnnee1(int t) {
		this.annee = t;
		return "annee";
	}

	public String setAnneeEtatAp(int t) {
		this.annee = t;
		return "anneeEtatAp";
	}

	public String setAnneeInv(int t, List <Object[]> li) {
		this.annee = t;
		this.setListeInventaire(li);
		return "anneeInv";
	}

	public int getAnnee() {
		return this.annee;
	}

	public void setListAnnee(List<Integer> listAnnee) {
		this.listAnnee = listAnnee;
	}

	public List<Integer> getListAnnee() {
		this.listAnnee = new ArrayList<Integer>();
		int k = 2018;
		while (k <= Calendar.getInstance().get(Calendar.YEAR)) {
			this.listAnnee.add(k);
			k = k + 1;
		}
		return this.listAnnee;
	}

	public void refusePrisEnChargeEntreMat(Operation op) {
		// usermetierimpl.entrerMateriel(op);
		System.out.println("begin refuser prise en charge");

		try {
			usermetierimpl.reqMatRefuser((OpEntree) op, this.getMotif());
			setCurentNull();
			setAllNull();
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage("myerror",
					new FacesMessage("Erreur", "La prise en charge n'a pas pu être refusée car: " + e.getMessage()));
			// context.addMessage(null, new FacesMessage("Second Message", "Additional
			// Message Detail"));
			System.out.println("erreur refuser prise en charge");
			e.printStackTrace(System.out);
		}

		System.out.println("end refuser prise en charge");
		this.setCurentOperation(null);
		this.setMotif(null);
	}

	public void aModifierPrisEnChargeEntreMat(Operation op) {
		// usermetierimpl.entrerMateriel(op);

		// ((OpEntree)this.getCurentOperation()).getMat().setAModifier(true);
		try {
			usermetierimpl.reqMatAModifier((OpEntree) op, this.getMotif());
			setAllNull();
			setCurentNull();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// TODO: handle exception
			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage("myerror", new FacesMessage("Erreur",
					"La prise en charge n'a pas pu être à modifier car: " + this.getMotif() + "   " + e.getMessage()));
			// context.addMessage(null, new FacesMessage("Second Message", "Additional
			// Message Detail"));
			System.out.println("erreur à modifier prise en charge");
			e.printStackTrace(System.out);
		}

		this.setCurentOperation(null);
		this.setMotif(null);
	}

	public void validateAttributionDetenteur(OpAttribution attr) {
		// usermetierimpl.attriuberMateriel(attr);
		try {
			usermetierimpl.attriuberMateriel(attr);
			setAllNull();
			setCurentNull();

		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			String mess= "";
			if(attr ==null) {
				mess = "attribution null";
			}
			context.addMessage("myerror",
					new FacesMessage("Erreur", "l'attribution n'a pas pu être validée car "+mess + e.getMessage()));
			System.out.println(
					"EEEEEEEEERRRRRRRRRRRRRRROOOOOOOOOOORRRRRRRRR *******T******:" + e.getMessage() + "*******");
			// e.printStackTrace();
		}

		this.setCurentOperation(null);

	}


	public void refuseAttributionDetenteur(OpAttribution attr) {
		// usermetierimpl.attriuberMateriel(attr);
		try {
			usermetierimpl.reqAttrRefuser(attr, this.getMotif());
			setAllNull();
			setCurentNull();
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage("myerror",
					new FacesMessage("Error Attribution", "L'attribution n'a pas pu être refusée car :"+ e.getMessage()));
			// context.addMessage(null, new FacesMessage("Second Message", "Additional
			// Message Detail"));
			System.out.println("erreur refuser Attribution");
			e.printStackTrace(System.out);
		}
		this.setCurentOperation(null);
		this.setMotif(null);
	}

	public void aModifierAttributionDetenteur(OpAttribution attr) {
		// usermetierimpl.attriuberMateriel(attr);
		try {
			usermetierimpl.reqAttrAModifier(attr, this.getMotif());
			setAllNull();
			setCurentNull();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage("myerror",
					new FacesMessage("Erreur Attribution", "L'attribution n'a pas pu être à modifier"));
			// context.addMessage(null, new FacesMessage("Second Message", "Additional
			// Message Detail"));
			System.out.println("erreur à modifier Attribution");
			e.printStackTrace(System.out);
		}
		this.setCurentOperation(null);
		this.setMotif(null);
	}

	public void validateDechargeSortie(OpSortie sortie) {
		// usermetierimpl.sortirMateriel(sortie);
		System.out.println("VALDATION DECHARGE SORTIE");
		try {
			usermetierimpl.sortirMateriel(sortie);
			setAllNull();
			setCurentNull();
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage("myerror",
					new FacesMessage("Erreur Décharge", "La Décharge n'a pas pu être validée car: " + e.getMessage()));
			// context.addMessage(null, new FacesMessage("Second Message", "Additional
			// Message Detail"));
			System.out.println("erreur valider Decharge");
			e.printStackTrace(System.out);
		} finally {

			this.setCurentOperation(null);
		}

	}

	public void exit() {
		System.out.println(
				"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$   APPEL EXIT $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

		setCurentNull();

		this.curentOperation = null;
		this.motif = null;
		//return null;
	}

	public void exity() {
		System.out.println(
				"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$   APPEL EXITY $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

		setCurentNull();

		this.curentOperation = null;
		this.motif = null;

		setAllNull();

	}

	public void refuseDechargeSortie(OpSortie sortie) {
		// usermetierimpl.sortirMateriel(sortie);
		usermetierimpl.reqSortirRefuser(sortie, this.getMotif());
		this.setCurentOperation(null);
		this.setMotif(null);
		setAllNull();
		setCurentNull();
	}

	public void aModifierDechargeSortie(OpSortie sortie) {
		// usermetierimpl.sortirMateriel(sortie);
		usermetierimpl.reqSortirAModifier(sortie, this.getMotif());
		this.setCurentOperation(null);
		this.setMotif(null);
		setAllNull();
		setCurentNull();
	}

	public void validateDetachement(OpDettachement det) {
		System.out.println("VALIDATION DETACHEMENT");
		// usermetierimpl.sortirMateriel(sortie);
		try {
			usermetierimpl.detacherMateriel(det);
			setAllNull();
			setCurentNull();
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage("myerror", new FacesMessage("Erreur detachement",
					"Le Détachement n'a pas pu être validée car: " + e.getMessage()));
			// context.addMessage(null, new FacesMessage("Second Message", "Additional
			// Message Detail"));
			System.out.println("erreur valider Détachement");
			e.printStackTrace(System.out);
			System.out.println(e.getMessage());
		} /*
			 * finally { this.setCurentOperation(null); this.setMotif(null); }
			 */

		// usermetierimpl.sortirMateriel((OpDettachement)this.getCurentOperation());

	}

	public void refuseDetachement(OpDettachement det) {
		// usermetierimpl.sortirMateriel(sortie);
		usermetierimpl.reqDetRefuser(det, this.getMotif());
		this.setCurentOperation(null);
		this.setMotif(null);
		setCurentNull();
		setAllNull();
	}

	public void aModifierDetachement(OpDettachement det) {
		// usermetierimpl.sortirMateriel(sortie);
		// usermetierimpl.reqSortirAModifier((OpDettachement)this.getCurentOperation(),
		// this.getMotif());
		usermetierimpl.reqDetRefuser(det, this.getMotif());
		this.setCurentOperation(null);
		this.setMotif(null);
		setCurentNull();
		setAllNull();
	}

	public void setListAllOperation(List<Operation> listAllOperation) {
		this.listAllOperation = listAllOperation;
	}

	public List<Operation> getListAllOperation() {
		return usermetierimpl.getListOp();
	}

	private List<Agent> getAllAgents() {
		return usermetierimpl.findAllAgents();
	}

	public IUserMetier getUsermetierimpl() {
		return usermetierimpl;
	}

	public void setUsermetierimpl(IUserMetier usermetierimpl) {
		this.usermetierimpl = usermetierimpl;
	}

	/*
	 * public void setSuivibean(SuiviEditionBean svbean){ this.suivibean = svbean; }
	 */
	public void setCurentOperation(Operation operation) {
		this.curentOperation = operation;

		System.out.println(
				"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

	}

	public void setCurentOperation3(Operation operation) {
		this.curentOperation = operation;

	}

	public void setCurentOperation1(Operation operation) {
		this.curentOperation1 = operation;

	}

	public String setCurentOperation6(Operation operation) {
		setCurentOperation(operation);

		return "dialog";

	}

	public String setCurentOperation2(Operation operation) {
		setCurentOperation(operation);

		if (usermetierimpl.getListMatByDet(((OpAttribution) getCurentOperation()).getDetenteur()) != null) {
			this.setListMaterielByDet(
					usermetierimpl.getListMatByDet(((OpAttribution) getCurentOperation()).getDetenteur()));
			/*
			 * ListIterator<Materiel> it = this.getListMaterielByDet().listIterator(); if
			 * (it!=null) { this.setTotal(Double.parseDouble("0")); while(it.hasNext()){
			 * setTotal(this.total+(Double)(it.next().getPu())); } }
			 */
			return "dialog";
		}

		return null;

	}

	public String setCurentOperationOrdre(Operation operation) {
		System.out.println(" code SOA : " + operation.getDirection().getBudget());
		this.ordreEntreeBean.setBudget(operation.getDirection().getBudget());
		setCurentOperation1(operation);
		
		return "ordre";
	}

	public String setCurentOperationSortie(Operation operation) {
		setCurentOperation1(operation);
		return "ordreSortie";
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

	public Operation getCurentOperation() {
		// return this.curentOperation;
		// get opreation by id;
		/*
		 * if(this.curentOperation!=null) {
		 * System.out.println("it s not null and id is "+this.curentOperation.getId());
		 * OpEntree e =
		 * usermetierimpl.getOperationEntreeById(this.curentOperation.getId());
		 * System.out.println("Ok we  got it"); if(e.getListMat() ==null) {
		 * System.out.println("Fucking null"); } else {
		 * System.out.println("its not null bb "); }
		 * //System.out.println("liste en cours = "); return e; }
		 */
		// okay
		return this.curentOperation;

	}

	public Operation getCurentOperation1() {
		return this.curentOperation1;
	}

	public void setMotif(String m) {
		this.motif = m;
	}

	public String getMotif() {
		return this.motif;
	}

	// ----------------GRAND II --------------------
	Fournisseur fournisseur;

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

	Double prix;

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

	private OpEntreeArticle opEntreeArticle;
	private OpSortieArticle opSortieArticle;

	public CodeArticle getCodeArticle() {
		return codeArticle;
	}

	public void setCodeArticle(CodeArticle codeArticle) {
		this.codeArticle = codeArticle;
	}

	private CodeArticle codeArticle;

	public OpEntreeArticle addRequeteOpEntree() {
		ArticleNouv a = new ArticleNouv();
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		a.setFournisseur(getFournisseur());
		a.setPrix(getPrix());
		return usermetierimpl.reqEntrerArticle(a, agent);
	}

	public void validateArticleSaisieExistant() {
		OpEntreeArticle o = addRequeteOpEntree();
		usermetierimpl.entrerArticle(o);
	}

	public void reqArtAModifier() throws Exception {
		usermetierimpl.reqArtAModifier(getOpEntreeArticle(), getMotif());
	}

	public void reqSortirArtAModifier() throws Exception {
		usermetierimpl.reqSortirArtAModifier(getOpSortieArticle(), getMotif());
	}

	/*
	 * public void reqArtRefuser() throws Exception{
	 * usermetierimpl.reqArtRefuser(getOpEntreeArticle(),getMotif()); }
	 */
	/*
	 * public void reqSortirRefuser() throws Exception {
	 * usermetierimpl.reqSortirRefuser(getOpSortieArticle(),getMotif()); }
	 */
	public void entrerArticle()  {
		usermetierimpl.entrerArticle(getOpEntreeArticle());
	}

	public void sortirArticle()  {
		try {
			usermetierimpl.sortirArticle(getOpSortieArticle());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addArticleEx() {
		ArticleEx a = new ArticleEx();

		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		a.setCodeArticle(getCodeArticle());
		a.setDirecArt(agent.getDirection());
		// a.setTypeObjet(getTypeObjet());
		usermetierimpl.reqEntrerArticle(a, agent);
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

	public void validateSortieArticleEx(OpSortieArticle operation) throws Exception {
		usermetierimpl.sortirArticle(operation);
	}

	// ------------DEBUG FARANY
	private StreamedContent filedownload;
	private int fileZipSize;
	private String fileZipPath;

	public StreamedContent getFiledownload() throws IOException {
		RequestFilter.getSession().setAttribute(getFileZipPath(), "fileZipPath");
		if (RequestFilter.getSession().getAttribute("fileZipPath") == null
				|| RequestFilter.getSession().getAttribute("fileZipPath") == "")
			return null;
		try {
			FileInputStream fstream = new FileInputStream(
					(String) RequestFilter.getSession().getAttribute("fileZipPath"));
			if (fstream == null)
				return null;
			if (fstream.getChannel().size() == 0)
				return null;

			InputStream stream = new FileInputStream((String) RequestFilter.getSession().getAttribute("fileZipPath"));
			fileZipSize = stream.available();
			filedownload = new DefaultStreamedContent(stream, "application/zip", "doc.zip");
		} catch (FileNotFoundException f) {
			return null;
		}
		// RequestFilter.getSession().setAttribute("fileZipPath",null);
		return filedownload;
	}

	private static void copyFileUsingStream(File source, File dest) throws IOException {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} finally {
			is.close();
			os.close();
		}
	}

	public void setFileZipPath(String filePath) {
		this.fileZipPath = filePath;
	}

	public String getFileZipPath() {

		List<Materiel> lstM = opEntreeToValidate.getListMat();
		FileOutputStream fos = null;
		ZipOutputStream zipOut = null;
		FileInputStream fis = null;
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
		String datetime = ft.format(dNow);
		try {

			File file = new File(datetime);
			String absolutePath = file.getAbsolutePath();

			RequestFilter.getSession().setAttribute("documentpath", absolutePath + ".zip");
			// eto miset an le documentpath
			String a = (String) RequestFilter.getSession().getAttribute("documentpath");

			fos = new FileOutputStream(datetime + ".zip");
			RequestFilter.getSession().setAttribute("fileZipPath", datetime + ".zip");
			zipOut = new ZipOutputStream(new BufferedOutputStream(fos));
			int counter_file_name = 0;
			for (Materiel m : lstM) {
				if (m.getDesign().getDocumentPath() != null)
				{
					File input = new File(m.getDesign().getDocumentPath());
					try {


						fis = new FileInputStream(input);
						ZipEntry ze = new ZipEntry(input.getName());
						System.out.println("Zipping the file: " + input.getName());
						zipOut.putNextEntry(ze);
						byte[] tmp = new byte[4 * 1024];
						int size = 0;
						while ((size = fis.read(tmp)) != -1) {
							zipOut.write(tmp, 0, size);
						}
						// zipOut.flush();
						counter_file_name++;
					}catch(ZipException e)
					{
						String input_file_name = m.getDesign().getDocumentPath();
						input_file_name = input_file_name.replace(".zip","");
						File input_new = new File(input_file_name+"_"+counter_file_name+".zip");
						FileUtils.copyFile(input,input_new);
						fis = new FileInputStream(input_new);
						ZipEntry ze = new ZipEntry(input_new.getName());
						System.out.println("Zipping the file: " + input_new.getName());
						zipOut.putNextEntry(ze);
						byte[] tmp = new byte[4 * 1024];
						int size = 0;
						while ((size = fis.read(tmp)) != -1) {
							zipOut.write(tmp, 0, size);
						}
						// zipOut.flush();

						counter_file_name++;
					}finally {
						try {
							if (fis != null)
								fis.close();
						} catch (Exception ex) {

						}
					}

				}
			}
			zipOut.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (Exception ex) {

			}
		}
		return datetime+ ".zip";
	}



	public void reqArtRefuser(OpEntreeArticle e) {
		try {
			usermetierimpl.reqArtRefuser(e, this.getMotif());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage("myerror",
					new FacesMessage("Erreur refuser", "L'article n'a pas pu être validée car: " + e1.getMessage()));
			// context.addMessage(null, new FacesMessage("Second Message", "Additional
			// Message Detail"));
			System.out.println("erreur refuser article");
			e1.printStackTrace(System.out);
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		} finally {
			this.setCurentOperation(null);
			this.setMotif(null);
		}
	}

	public void validateArticleENouv(OpEntreeArticle operation) {
		usermetierimpl.entrerArticle(operation);
	}

	public void validateSortieArticleNouv(OpSortieArticle operation) {
		try {
			if(usermetierimpl.calculArticleRestant(operation.getArticle()) - operation.getNombreToS() <0) {
				FacesContext context = FacesContext.getCurrentInstance();

				context.addMessage("myerror",
						new FacesMessage("Erreur validation sortie", 
								"L'article n'a pas pu être sortie car il ne reste plus assez d'articles dans le stock " ));
				// context.addMessage(null, new FacesMessage("Second Message", "Additional
				// Message Detail"));
				System.out.println("erreur valider sortie article");
				return;
			}
			usermetierimpl.sortirArticle(operation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage("myerror",
					new FacesMessage("Erreur valider", "L'article n'a pas pu être sortie car: " + e.getMessage()));
			// context.addMessage(null, new FacesMessage("Second Message", "Additional
			// Message Detail"));
			System.out.println("erreur valider sortie article");
			e.printStackTrace(System.out);
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			this.setCurentOperation(null);
			this.setMotif(null);
		}
	}

	public void reqSortirRefuser(OpSortieArticle operation) {
		try {
			usermetierimpl.reqSortirRefuser(operation, getMotif());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("myerror",
					new FacesMessage("Erreur refuser", "L'article n'a pas pu être sortie car: " + e.getMessage()));
			// context.addMessage(null, new FacesMessage("Second Message", "Additional
			// Message Detail"));
			System.out.println("erreur valider sortie article");
			e.printStackTrace(System.out);
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			this.setCurentOperation(null);
			this.setMotif(null);
		}
	}

	public OpEntree getCurrentOpEntree() {
		return currentOpEntree;
	}

	public void setCurrentOpEntree(OpEntree currentOpEntree) {
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

	public void setCurentNull() {
		this.curentOperation = null;
		this.curentOperation1 = null;
		this.currentOpDettachement = null;
		this.currentOpAttribution = null;
		this.currentOpSortie = null;
		this.currentOpEntree = null;
		this.curentopSortieForValidation = null;
		this.opEntreeToValidate = null;
		this.opSortieToValidate = null;
		this.opDetToValidate = null;
		this.opAttrToValidate = null;
		opEntreeArticleToValidate = null;
		opSortieArticleToValidate = null;

	}

	public void setAllNull() {
		this.annee = 0;
		this.listOperationByDirectionByYearByDateAsc = null;
		this.listMaterielByDet = null;
	}

	private String fileFacPath;
	private StreamedContent facdownload;

	public String getFileFacPath() {
		return opEntreeToValidate.getPathDoc();
	}

	public void setFileFacPath(String fileFacPath) {
		this.fileFacPath = fileFacPath;
	}

	public StreamedContent getFacdownload() throws IOException {
		String fac = getFileFacPath();
		if (fac == null)
			return null;
		try {
			FileInputStream fstream = new FileInputStream(fac);
			if (fstream == null)
				return null;
			if (fstream.getChannel().size() == 0)
				return null;

			InputStream stream = new FileInputStream(fac);
			fileZipSize = stream.available();
			filedownload = new DefaultStreamedContent(stream, "application/zip", "doc.zip");
		} catch (FileNotFoundException f) {
			return null;
		}
		// RequestFilter.getSession().setAttribute("fileZipPath",null);
		return filedownload;
	}

	public void setFacdownload(StreamedContent s) {
		this.facdownload = s;
	}

	public OpSortie getCurentopSortieForValidation() {
		return curentopSortieForValidation;
	}

	public void setCurentopSortieForValidation(OpSortie curentopSortieForValidation) {
		this.curentopSortieForValidation = curentopSortieForValidation;
	}

	private OpEntree opEntreeToValidate;
	private OpSortie opSortieToValidate;
	private OpDettachement opDetToValidate;
	private OpAttribution opAttrToValidate;

	private Operation opEntreeArticleToValidate;
	private Operation opSortieArticleToValidate;

	public OpEntree getOpEntreeToValidate() {
		return opEntreeToValidate;
	}

	public void setOpEntreeToValidate(OpEntree opEntreeil) {
		System.out.println("sfsd");
		this.opEntreeToValidate = opEntreeil;
	}

	public OpSortie getOpSortieToValidate() {
		return opSortieToValidate;
	}

	public void setOpSortieToValidate(OpSortie opSortieToValidate) {
		this.opSortieToValidate = opSortieToValidate;
	}

	public OpDettachement getOpDetToValidate() {
		return opDetToValidate;
	}

	public void setOpDetToValidate(OpDettachement opDetToValidate) {
		this.opDetToValidate = opDetToValidate;
	}

	public OpAttribution getOpAttrToValidate() {
		return opAttrToValidate;
	}

	public void setOpAttrToValidate(OpAttribution opAttrToValidate) {
		this.opAttrToValidate = opAttrToValidate;
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

	public Operation getOpSortieArticleToValidate() {
		return opSortieArticleToValidate;
	}

	public void setOpSortieArticleToValidate(OpSortieArticle opSortieArticleToValidate) {
		this.opSortieArticleToValidate = opSortieArticleToValidate;
	}

	public Operation getOpEntreeArticleToValidate() {
		return opEntreeArticleToValidate;
	}

	public void setOpEntreeArticleToValidate(OpEntreeArticle opEntreeArticleToValidate) {
		this.opEntreeArticleToValidate = opEntreeArticleToValidate;
	}

	public List<Object[]> getListeInventaire() {
		return listeInventaire;
	}

	public void setListeInventaire(List<Object[]> listeInventaire) {
		this.listeInventaire = listeInventaire;
	}
	
	public int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

}
// r
