package com.douane.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.douane.entite.AttribuDemande;

public interface AttribuDemandeRepository extends CrudRepository<AttribuDemande, Long>{
	@Query("select a from AttribuDemande a where a.demande.numeroDemande=:x")
	public Page<AttribuDemande> listeAttributionDemande(@Param("x") Long numeroDemande, Pageable pageable);
}
