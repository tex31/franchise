package com.douane.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.douane.entite.Agent;
import com.douane.entite.Direction;

public interface AgentRepository extends CrudRepository<Agent, Long> {
	public List<Agent> findByNomAgentContainingIgnoreCase(String nom);
	public List<Agent> findByDirection(Direction direction);
	public Agent findByIm(Long im_agent);
	public List<Agent> findByDirectionAndActive(Direction direction, boolean b);
}
