package com.douane.entite;

import org.jboss.resteasy.util.Base64;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="typeMateriels", discriminatorType=DiscriminatorType.INTEGER)
public class Materiel implements Serializable{
	@Column(unique=true)
	private String code;

	@Id
	@SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="account_id_seq", name="account_id_seq")
	@GeneratedValue(generator="account_id_seq", strategy=GenerationType.SEQUENCE)
	private Long idMateriel;

	private Double pu;
	private String reference;
	private String numSerie;
	private String autre;
	//private String codification;
	private boolean validation;
	private String renseignement;
	private int serienumero;
	
	private byte[] image;

	private String documentPath;

	public String getImage() throws IOException
	{
		ByteArrayInputStream bais;
		
		if(image != null)
		{
			bais = new ByteArrayInputStream(image);
		}
		else
		{
			return null;
		}
		try {
			//BufferedImage imagebuff = ImageIO.read(bais);
			
			String encodedImage;
			
			BufferedImage resizedImg = resize(ImageIO.read(bais), 500, 500);
			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			
			if (resizedImg == null) {
				return null;
			}
			ImageIO.write(resizedImg, "jpg", os);
			
			encodedImage = new String(Base64.encodeBytes(os.toByteArray()));
			
			return encodedImage;
		}
		catch(NullPointerException e)
		{
			return "";
		}
		
	}

	private  BufferedImage resize(BufferedImage image, int newWidth, int newHeight)
	{
		int currentWidth = image.getWidth();
		int currentHeight = image.getHeight();
		BufferedImage newImage = new BufferedImage(newWidth, newHeight, image.getType());
		Graphics2D graphics2d = newImage.createGraphics();
		graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2d.drawImage(image, 0, 0, newWidth, newHeight, 0, 0,
				currentWidth, currentHeight, null);
		graphics2d.dispose();
		return newImage;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}



	@ManyToOne
	@JoinColumn(name="idNom")
	private Nomenclature nomenMat;
	@ManyToOne
	@JoinColumn(name="idEtat")
	private EtatMateriel etat;
	


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idCar", columnDefinition="integer", nullable = true , insertable=false, updatable=false)
	private TypeMateriel caract;
	@ManyToOne
	@JoinColumn(name="idMarque")
	private Marque marque;

	//localisation
	@ManyToOne
	@JoinColumn(name="idDirection")
	private Direction direc;


	private String anneeAcquisition;
	@ManyToOne
	@JoinColumn(name="idTypeMateriel")
	private TypeMateriel typematerieladd;



	
	
	@ManyToOne
	@JoinColumn(name="imDetenteur")
	private Agent detenteur;

	@ManyToOne
	@JoinColumn(name="imDepositaire")
	
	private Agent dc;

	/*
	 * MANAGE LATER
	Collection Operations
	File documents;
	Photo Blob
	Codification
	List<Agent> Liste des agents detenteurs successifs
	Code Barre
	Localisation
	*/

	public Long getIdMateriel(){
		return this.idMateriel;
	}
	public void setidMateriel(Long idMateriel){ this.idMateriel = idMateriel;}
	public Double getPu() {
		return pu;
	}
	public void setPu(Double pu) {
		this.pu = pu;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getNumSerie() {
		return numSerie;
	}
	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}
	public String getAutre() {
		return autre;
	}
	public void setAutre(String autre) {
		this.autre = autre;
	}
	public Nomenclature getNomenMat() {
		return nomenMat;
	}
	public void setNomenMat(Nomenclature nomenMat) {
		this.nomenMat = nomenMat;
	}
	public Agent getDetenteur() {
		return detenteur;
	}
	public void setDetenteur(Agent detenteur) {
		this.detenteur = detenteur;
	}
	public EtatMateriel getEtat() {
		return etat;
	}
	public void setEtat(EtatMateriel etat) {
		this.etat = etat;
	}
	public TypeMateriel getCaract() {
		return caract;
	}
	public void setCaract(TypeMateriel caract) {
		this.caract = caract;
	}
	public Agent getDc() {
		return dc;
	}
	public void setDc(Agent dc) {
		this.dc = dc;
	}

	@Override
	public boolean equals(Object o) {
		/*if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Materiel materiel = (Materiel) o;


		if (!idMateriel.equals(materiel.idMateriel)) return false;

		return leref.equals(materiel.leref);*/
		if (this.getIdMateriel().equals(((Materiel)o).getIdMateriel()))
			return true;
		if (this.getIdMateriel()==(((Materiel)o).getIdMateriel()))
			return true;
		return false;

	}


	public String getAnneeAcquisition(){
		return anneeAcquisition;
	}

	public void setAnneeAcquisition(String annee){
		this.anneeAcquisition = annee;
	}


	
	public Marque getMarque() {
		return marque;
	}
	public void setMarque(Marque marque) {
		this.marque = marque;
	}


	public Materiel(Double pu, String reference, String numSerie, String autre, String codification,
					Nomenclature nomenMat, EtatMateriel etat, TypeMateriel caract, Agent dc, Marque m) {
		super();
		this.pu = pu;
		this.reference = reference;
		this.numSerie = numSerie;
		this.autre = autre;
		this.nomenMat = nomenMat;
		this.etat = etat;
		this.caract = caract;
		this.dc = dc;
		this.marque = m;
	}
	public Materiel() {
		super();
		this.numeroType = 0L;
	}
	@Transient
	protected String leref;


	public String getLeref() {
		return leref;
	}
	public void setLeref(String leref) {
		this.leref = leref;
	}
	public boolean isValidation() {
		return validation;
	}
	public void setValidation(boolean validation) {
		this.validation = validation;
	}

	public Direction getDirec() {
		return direc;
	}
	public void setDirec(Direction direc) {
		this.direc = direc;
	}
	
	public String getCode() {
		return code;
	}
	
	public void generateCode(Long numerotype) {
		String codeDirection = "xxx";
		String codeTypeMateriel = "xxx";
		String anneeacquisition = "xxx";
		String immatriculedete = "xxx";
		if(this.getDirec()!=null) {
			codeDirection = this.direc.getCodeDirection();
		}
		if(this.getDesign().getTypematerieladd()!=null) {
			codeTypeMateriel = this.getTypematerieladd().getCodeTypeMate();
			//codeNomenclature = this.getTypematerieladd().getNomenclaureParent().getNomenclature();
		}
		if(this.getDesign().getAnneeAcquisition()!=null) {
			anneeacquisition = this.getDesign().getAnneeAcquisition();
		}
		if(this.getDetenteur()!=null) {
			immatriculedete = this.getDetenteur().getIm().toString();
		}
		this.code = ""+
				""+codeDirection+ "/" +
				""+codeTypeMateriel+ "/" +
				""+numerotype+ "/" +
				""+immatriculedete+ "/" +
				""+anneeacquisition;
	}

	
	public String getRenseignement() {
		return renseignement;
	}
	public void setRenseignement(String renseignement) {
		this.renseignement = renseignement;
	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	public TypeMateriel getTypematerieladd() {
		return typematerieladd;
	}

	public void setTypematerieladd(TypeMateriel typematerieladd) {
		this.typematerieladd = typematerieladd;
	}
	
	@ManyToOne
	@JoinColumn(name="opentreeid")
	private OpEntree myoperationEntree;

	public OpEntree getMyoperationEntree() {
		return myoperationEntree;
	}

	public void setMyoperationEntree(OpEntree myoperationEntree) {
		this.myoperationEntree = myoperationEntree;
	}
	
	public String getEspeceUnite() {
		return especeUnite;
	}

	public void setEspeceUnite(String especeUnite) {
		this.especeUnite = especeUnite.toUpperCase();
	}



	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine.toUpperCase();
	}



	private String especeUnite;
	private String origine;
	
	@ManyToOne
	  @JoinColumn(name="desingationid")
	Designation design;

	public Designation getDesign() {
		return design;
	}

	public void setDesign(Designation design) {
		this.design = design;
	}

	public int getSerienumero() {
		return serienumero;
	}

	public void setSerienumero(int serienumero) {
		this.serienumero = serienumero;
	}
	
	
	private Long numeroType;
	public Long getNumeroType() {
		return numeroType;
	}

	public void setNumeroType(Long numeroType) {
		this.numeroType = numeroType;
	}


}
