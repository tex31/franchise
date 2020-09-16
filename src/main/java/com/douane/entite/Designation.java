package com.douane.entite;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.jboss.resteasy.util.Base64;

@Entity
public class Designation implements Serializable{
	@Id
	@SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="designation_id_seq", name="designation_id_seq")
	@GeneratedValue(generator="designation_id_seq", strategy=GenerationType.SEQUENCE)
	private Long idDesignation;
	
	private Double pu;
	private String autre;
	private String renseignement;
	private byte[] image;
	
	@ManyToOne
	@JoinColumn(name="idNom")
	private Nomenclature nomenMat;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idCar", columnDefinition="integer", nullable = true , insertable=false, updatable=false)
	private TypeMateriel caract;
	@ManyToOne
	@JoinColumn(name="idMarque")
	private Marque marque;
	private String anneeAcquisition;
	@ManyToOne
	@JoinColumn(name="idTypeMateriel")
	private TypeMateriel typematerieladd;
	
	private String especeUnite;
	
	
	@ManyToOne
	@JoinColumn(name="idModAcq")
	private ModeAcquisition modAcq;
	
	@ManyToOne
	@JoinColumn(name="idFin")
	private Financement financement;
	@ManyToOne
	@JoinColumn(name="idFourn")
	private Fournisseur fournisseur;
	
	private String refFacture;
	
	private String documentPath;
	private String origine;
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
		//return imagebuff;
		//return image;
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



	public Double getPu() {
		return pu;
	}
	public void setPu(Double pu) {
		this.pu = pu;
	}



	public String getAutre() {
		return autre;
	}
	public void setAutre(String autre) {
		this.autre = autre;
	}



	public String getRenseignement() {
		return renseignement;
	}
	public void setRenseignement(String renseignement) {
		this.renseignement = renseignement.substring(0,1).toUpperCase() + renseignement.substring(1).toLowerCase();
	}



	public String getAnneeAcquisition() {
		return anneeAcquisition;
	}
	public void setAnneeAcquisition(String anneeAcquisition) {
		this.anneeAcquisition = anneeAcquisition;
	}



	public String getEspeceUnite() {
		return especeUnite;
	}
	public void setEspeceUnite(String especeUnite) {
		this.especeUnite = especeUnite.toUpperCase();
	}
	/*public List<Materiel> getMateriels() {
		return materiels;
	}
	public void setMateriels(List<Materiel> materiels) {
		this.materiels = materiels;
	}
	public void addMateriel(Materiel m) {
		this.materiels.add(m);
		m.setDesign(this);
	}*/
	public Nomenclature getNomenMat() {
		return nomenMat;
	}
	public void setNomenMat(Nomenclature nomenMat) {
		this.nomenMat = nomenMat;
	}
	
	public TypeMateriel getCaract() {
		return caract;
	}
	public void setCaract(TypeMateriel caract) {
		this.caract = caract;
	}
	public Marque getMarque() {
		return marque;
	}
	public void setMarque(Marque marque) {
		this.marque = marque;
	}
	public TypeMateriel getTypematerieladd() {
		return typematerieladd;
	}
	public void setTypematerieladd(TypeMateriel typematerieladd) {
		this.typematerieladd = typematerieladd;
	}
	public ModeAcquisition getModAcq() {
		return modAcq;
	}
	public void setModAcq(ModeAcquisition modAcq) {
		this.modAcq = modAcq;
	}
	public Financement getFinancement() {
		return financement;
	}
	public void setFinancement(Financement financement) {
		this.financement = financement;
	}
	public Fournisseur getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}
	public String getRefFacture() {
		return refFacture;
	}
	public void setRefFacture(String refFacture) {
		this.refFacture = refFacture;
	}
	public Long getIdDesignation() {
		return idDesignation;
	}
	public String getDocumentPath() {
		return documentPath;
	}
	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}
	public String getOrigine() {
		return origine;
	}
	public void setOrigine(String origine) {
		this.origine = origine.toUpperCase();
	}

}
