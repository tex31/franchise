package com.douane.entite;

import java.util.Date;

import javax.persistence.Entity;

import com.douane.model.FEtatDemande;
@Entity
public class TraiterDemande extends AttribuDemande{

	public TraiterDemande() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TraiterDemande(Agent agent, Date date, Demande demande, String observation, String motif,
			FEtatDemande etatDemande, FDossier dossier) {
		super(agent, date, demande, observation, motif, etatDemande, dossier);
		// TODO Auto-generated constructor stub
	}
	
}
