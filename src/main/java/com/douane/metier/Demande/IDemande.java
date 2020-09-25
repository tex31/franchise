package com.douane.metier.Demande;

import org.springframework.data.domain.Page;

import com.douane.entite.Agent;
import com.douane.entite.AttribuDemande;
import com.douane.entite.Demande;
import com.douane.entite.FDossier;
import com.douane.model.FEtatDemande;

public interface IDemande {
	public Demande consulterDemande(Long numeroDemande);
	public void traiter(Agent agent, Long numeroDemande,String observation, String motif, FEtatDemande etatDemande, FDossier dossier);
	public Page<AttribuDemande> listeAttribuDemande(Long numeroDemande, int page, int size);
}
