package com.douane.repository;

import java.util.Date;
import java.util.List;

import com.douane.model.EtatOperation;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.douane.entite.Agent;
import com.douane.entite.Direction;
import com.douane.entite.Materiel;
import com.douane.entite.OpEntree;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

public interface OpEntreeRepository extends CrudRepository<OpEntree, Long>{
	public List<OpEntree> findAll();
	public List<OpEntree> findByOperateur(Agent operateur);
	public List<OpEntree> findByDirection(Direction direction);
	public List<OpEntree> findByDirectionOrderByDateDesc(Direction direction);
	public List<OpEntree> findByMat(Materiel m);
	public List<OpEntree> findByMatAndState(Materiel m, EtatOperation e);
	public List<OpEntree> findByStateAndDirection(EtatOperation  e, Direction d);
	public List<OpEntree> findByDirectionOrderByIdDesc(Direction direction);
	public List<OpEntree> findByStateAndDirectionAndDateBetween(EtatOperation etat, Direction d, Date sdate,
			Date edate);
	public List<OpEntree> findByStateAndDirectionAndDateBetweenOrderByIdDesc(EtatOperation etat, Direction d,
			Date sdate, Date edate);
	public List<OpEntree> findByDirectionAndDateBetweenOrderByIdDesc(Direction direction, Date sdate, Date edate);
	
}

