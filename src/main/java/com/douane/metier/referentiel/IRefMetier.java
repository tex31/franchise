package com.douane.metier.referentiel;

import java.util.List;

import com.douane.entite.Agent;
import com.douane.entite.Direction;
import com.douane.entite.DirectionTitleHist;
import com.douane.entite.EtatMateriel;
import com.douane.entite.FournisseurDetail;
import com.douane.entite.Referentiel;

public interface IRefMetier {
	public Referentiel addRef(Referentiel r,Agent operat);
	public Referentiel addRefWithoutOper(Referentiel r);
	public void removeRef(Referentiel r);	
	public List<Referentiel> listRef(Referentiel r);
	public void saveRefs(List<Referentiel> listrefs,Agent operat);
	
	public void addFournisseur(FournisseurDetail f, Agent operat);
	public void removeFournisseur(FournisseurDetail f);
	public List<FournisseurDetail> listFournisseur();
	
	public Referentiel findById(Long id);
	Referentiel updateRef(Referentiel r, Agent oper);
	
	public DirectionTitleHist saveDirHisto(DirectionTitleHist dh);
	Direction updateDirection(Direction d);
}
