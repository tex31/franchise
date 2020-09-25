package com.douane.metier.Demande;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.douane.entite.Agent;
import com.douane.entite.AttribuDemande;
import com.douane.entite.Demande;
import com.douane.entite.FDossier;
import com.douane.entite.TraiterDemande;
import com.douane.model.FEtatDemande;
import com.douane.repository.AttribuDemandeRepository;
import com.douane.repository.DemandeRepository;

public class DemandeImpl implements IDemande {
	@Autowired
	private DemandeRepository demandeRepository;
	@Autowired
	private AttribuDemandeRepository attributionRepository;
	
	@Override
	public Demande consulterDemande(Long numeroDemande) {
		
		return demandeRepository.findOne(numeroDemande);
	}

	@Override
	public void traiter(Agent agent, Long numeroDemande, String observation, String motif, FEtatDemande etatDemande, FDossier dossier) {
		Demande demande = consulterDemande(numeroDemande);
		TraiterDemande traitement = new TraiterDemande(agent, new Date(), demande, observation, motif,
				etatDemande, dossier);
		attributionRepository.save(traitement);
		demande.setEtatDemande(etatDemande);
		demande.setMotif(motif);
		demande.setDossier(dossier);
	}

	@Override
	public Page<AttribuDemande> listeAttribuDemande(Long numeroDemande, int page, int size) {
		
		return attributionRepository.listeAttributionDemande(numeroDemande, new PageRequest(page, size));
	}

}
