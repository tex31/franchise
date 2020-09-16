package com.douane.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.douane.entite.Article;
import com.douane.entite.OpEntree;
import com.douane.entite.OpSortieArticle;
import com.douane.model.EtatOperation;

public interface OpSortArtRepository extends CrudRepository<OpSortieArticle, Long>{
	@Query("select sum(opsort.nombreToS) "
			+ " from OpSortieArticle opsort join  opsort.article art where art=:curentarts"
			+ " and opsort.state =:status"
			+ "")
	Object getNumberOfSortieByArticle(@Param("curentarts")Article curentart
			, @Param("status") EtatOperation status
			);
}