package com.douane.entite;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.douane.model.FEtatDemande;

@Entity
public class Demande implements Serializable{
	//private static final String mappedBy = null;

	@Id 
	@SequenceGenerator (name = "generatorDemande", sequenceName = "DE_SEQ", allocationSize = 1) 
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "generatorDemande")
	private Long numeroDemande;
	
	@ManyToOne
	@JoinColumn(name="idtypeFranchise")
	private FTypeFranchise typeFranchise;
	
	@Enumerated(EnumType.STRING)
	private FEtatDemande etatDemande;
	
	private Date dateDepot;
	private String motif;
	
	@ManyToOne
	@JoinColumn(name="idClient")
	private Client client;
	
	@OneToOne
	@JoinColumn(name="idCuo")
	private FCuo fcuo;
	
	@OneToOne
	@JoinColumn(name="idRequerant")
	private FRequerant requerant;
	
	@OneToOne
	@JoinColumn(name="idDossier")
	private FDossier dossier;
	
	@OneToOne
	@JoinColumn(name="idMarchandise")
	private Marchandise marchandise;
	
	public Demande() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Demande(Client client, FTypeFranchise typeFranchise, Date dateDepot, String motif,
			FCuo fcuo, FRequerant requerant, FDossier dossier, Marchandise marchandise) {
		super();
		this.typeFranchise = typeFranchise;
		this.dateDepot = dateDepot;
		this.motif = motif;
		this.fcuo = fcuo;
		this.etatDemande = FEtatDemande.EN_ATTENTE;
		this.requerant = requerant;
		this.dossier = dossier;
		this.marchandise = marchandise;
		this.client = client;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setNumeroDemande(Long numeroDemande) {
		this.numeroDemande = numeroDemande;
	}

	public void setEtatDemande(FEtatDemande etatDemande) {
		this.etatDemande = etatDemande;
	}

	public FTypeFranchise getTypeFranchise() {
		return typeFranchise;
	}

	public void setTypeFranchise(FTypeFranchise typeFranchise) {
		this.typeFranchise = typeFranchise;
	}

	public Long getNumeroDemande() {
		return numeroDemande;
	}
	
	public FEtatDemande getEtatDemande() {
		return etatDemande;
	}
	public Date getDateDepot() {
		return dateDepot;
	}
	public void setDateDepot(Date dateDepot) {
		this.dateDepot = dateDepot;
	}
	public String getMotif() {
		return motif;
	}
	public void setMotif(String motif) {
		this.motif = motif;
	}
	public Marchandise getMarchandise() {
		return marchandise;
	}
	public void setMarchandise(Marchandise marchandise) {
		this.marchandise = marchandise;
	}

	public FCuo getFcuo() {
		return fcuo;
	}

	public void setFcuo(FCuo fcuo) {
		this.fcuo = fcuo;
	}

	public FRequerant getRequerant() {
		return requerant;
	}

	public void setRequerant(FRequerant requerant) {
		this.requerant = requerant;
	}

	public FDossier getDossier() {
		return dossier;
	}

	public void setDossier(FDossier dossier) {
		this.dossier = dossier;
	}
	
}
