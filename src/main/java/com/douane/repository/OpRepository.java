package com.douane.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.douane.entite.Agent;
import com.douane.entite.Direction;
import com.douane.entite.Operation;

public interface OpRepository extends CrudRepository<Operation, Long>{
	public List<Operation> findAll();
	public List<Operation> findByOperateur(Agent operateur);
	public List<Operation> findByDirection(Direction direction);
	public List<Operation> findByDirectionOrderByDateDesc(Direction direction);
	public List<Operation> findByDirectionOrderByIdDesc(Direction direction);
	public List<Operation> findByDirectionAndDateBetweenOrderByIdDesc(Direction direction, Date sdate, Date edate);

}
