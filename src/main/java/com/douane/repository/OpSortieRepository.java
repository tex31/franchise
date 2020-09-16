package com.douane.repository;

import java.util.Date;
import java.util.List;

import com.douane.entite.*;
import com.douane.model.EtatOperation;
import org.springframework.data.repository.CrudRepository;

public interface OpSortieRepository extends CrudRepository<OpSortie, Long>{
	public List<OpSortie> findAll();
	public List<OpSortie> findByOperateur(Agent operateur);
	public List<OpSortie> findByDirection(Direction direction);
	public List<OpSortie> findByMat(Materiel m);
	public List<OpSortie> findByMatAndState(Materiel m, EtatOperation e);
	public List<OpSortie> findByDirectionOrderByDateDesc(Direction direction);
	public List<OpSortie> findByDirectionAndState(Direction direction, EtatOperation accepted);
	public List<OpSortie> findByDirectionOrderByIdDesc(Direction direction);
	public List<OpSortie> findByDirectionAndStateAndDateBetweenOrderByIdDesc(Direction direction,
			EtatOperation accepted, Date sdate, Date edate);
	public List<OpSortie> findByDirectionAndDateBetweenOrderByIdDesc(Direction direction, Date sdate, Date edate);

}

