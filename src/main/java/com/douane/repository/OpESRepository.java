package com.douane.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.douane.entite.Direction;
import com.douane.entite.OperationES;
import com.douane.model.EtatOperation;

public interface OpESRepository extends CrudRepository<OperationES, Long>{
	
	List<OperationES> findByDirectionAndStateAndDateBetween(Direction d, EtatOperation etat, Date startdate, Date endDate);
}
