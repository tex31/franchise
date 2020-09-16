package com.douane.repository;

import java.util.Date;
import java.util.List;

import com.douane.entite.*;
import com.douane.model.EtatOperation;
import org.springframework.data.repository.CrudRepository;

public interface OpAttrRepository extends CrudRepository<OpAttribution, Long>{
    public List<OpAttribution> findAll();
    public List<OpAttribution> findByOperateur(Agent operateur);
    public List<OpAttribution> findByDirection(Direction direction);
    public List<OpAttribution> findByMat(Materiel m);
    public List<OpAttribution> findByMatAndState(Materiel m, EtatOperation e);
	public List<OpAttribution> findByDirectionOrderByDateDesc(Direction direction);
	public List<OpAttribution> findByDirectionOrderByIdDesc(Direction direction);
	public List<OpAttribution> findByDirectionAndDateBetweenOrderByIdDesc(Direction direction, Date sdate, Date edate);
	public List<OpAttribution> findByDirectionAndStateAndDateBetweenOrderByIdDesc(Direction direction, EtatOperation e,
			Date sdate, Date edate);

}
