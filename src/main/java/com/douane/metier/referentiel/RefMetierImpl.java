package com.douane.metier.referentiel;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.douane.dao.referentiel.IRefDAO;
import com.douane.entite.Agent;
import com.douane.entite.Direction;
import com.douane.entite.DirectionTitleHist;
import com.douane.entite.EtatMateriel;
import com.douane.entite.FournisseurDetail;
import com.douane.entite.OpSaisie;
import com.douane.entite.Referentiel;
import com.douane.repository.DirHistRepository;
import com.douane.repository.DirRepository;
import com.douane.repository.FournRepository;
import com.douane.repository.OpRepository;
import com.douane.repository.RefRepository;

@Transactional
public class RefMetierImpl implements IRefMetier{

	private IRefDAO refdao;
	
	@Autowired
	private RefRepository refrepos;
	@Autowired
	private FournRepository fournrepos;
	@Autowired
	private OpRepository oprepos;
	@Autowired
	private DirHistRepository dirhistrepos;
	@Autowired
	private DirRepository dirrepos;

	public IRefDAO getRefdao() {
		return refdao;
	}

	public void setRefdao(IRefDAO dao) {
		this.refdao = dao;
	}
	public RefMetierImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Referentiel addRef(Referentiel r, Agent oper) {
		// TODO Auto-generated method stub
		Referentiel refx = refrepos.save(r);
		OpSaisie saisiref = new OpSaisie(new Date(), new Date(), oper.getIp(), oper, r.getLeref(),refx.getId());
		saisiref.valider();
		oprepos.save(saisiref);
		return refx;
	}
	@Override
	public Direction updateDirection(Direction d) {
		System.out.println("id : "+d.getId());
		return dirrepos.save(d);
	}
	@Override
	public Referentiel updateRef(Referentiel r, Agent oper) {
		// TODO Auto-generated method stub
		Referentiel refx = refrepos.save(r);
		OpSaisie saisiref = new OpSaisie(new Date(), new Date(), oper.getIp(), oper, r.getLeref(),refx.getId());
		saisiref.valider();
		oprepos.save(saisiref);
		return refx;
	}
	public Referentiel addRefWithoutOper(Referentiel r) {
		// TODO Auto-generated method stub
		Referentiel refx = refrepos.save(r);
		return refx;
	}

	@Override
	public void removeRef(Referentiel r) {
		// TODO Auto-generated method stub
		//dao.delRef((Nomenclature)r);
		refrepos.delete(r);
	}

	@Override
	public List<Referentiel> listRef(Referentiel r) {
		// TODO Auto-generated method stub
		return refdao.listRef(r);
	}

	@Override
	public void saveRefs(List<Referentiel> listrefs,Agent operat) {
		// TODO Auto-generated method stub
		OpSaisie saisiref = new OpSaisie(new Date(), new Date(), operat.getIp(), operat, "des referentiels",null);
		oprepos.save(saisiref);
		refrepos.save(listrefs);
	}

	@Override
	public void addFournisseur(FournisseurDetail f, Agent operat) {
		// TODO Auto-generated method stub
		FournisseurDetail fx= fournrepos.save(f);
		OpSaisie saisiref = new OpSaisie(new Date(), new Date(), operat.getIp(), operat, f.getLeref(),f.getIdFourn());
		oprepos.save(saisiref);
	}

	@Override
	public void removeFournisseur(FournisseurDetail f) {
		// TODO Auto-generated method stub
		fournrepos.delete(f);
	}

	@Override
	public List<FournisseurDetail> listFournisseur() {
		// TODO Auto-generated method stub
		return (List<FournisseurDetail>)fournrepos.findAll();
	}

	@Override
	public Referentiel findById(Long id) {
		// TODO Auto-generated method stub
		return refrepos.findOne(id);
	}

	@Override
	public DirectionTitleHist saveDirHisto(DirectionTitleHist dh) {
		// TODO Auto-generated method stub
		dh = dirhistrepos.save(dh);
		return dh;
	}

	public DirRepository getDirrepos() {
		return dirrepos;
	}

	public void setDirrepos(DirRepository dirrepos) {
		this.dirrepos = dirrepos;
	}

	

}
