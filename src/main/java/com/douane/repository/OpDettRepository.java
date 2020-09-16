package com.douane.repository;

import java.util.Date;
import java.util.List;

import com.douane.model.EtatOperation;
import org.springframework.data.repository.CrudRepository;

import com.douane.entite.Agent;
import com.douane.entite.Direction;
import com.douane.entite.Materiel;
import com.douane.entite.OpAttribution;
import com.douane.entite.OpDettachement;

public interface OpDettRepository extends CrudRepository<OpDettachement, Long>{
    public List<OpDettachement> findAll();
    public List<OpDettachement> findByOperateur(Agent operateur);
    public List<OpDettachement> findByDirection(Direction direction);
    public List<OpDettachement> findByMat(Materiel m);
    public List<OpDettachement> findByMatAndState(Materiel m, EtatOperation e);
	public List<OpDettachement> findByDirectionOrderByDateDesc(Direction direction);
	public List<OpDettachement> findByDirectionAndDateBetweenOrderByDateDesc(Direction direction, Date sdate,
			Date edate);
	public List<OpDettachement> findByDirectionAndDateBetweenOrderByIdDesc(Direction direction, Date sdate, Date edate);
}

