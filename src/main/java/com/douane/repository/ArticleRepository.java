package com.douane.repository;


import org.springframework.data.repository.CrudRepository;

import com.douane.entite.Agent;
import com.douane.entite.Article;
import com.douane.entite.Direction;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article,Long>{
    public List<Article> findAll();

	public List<Article> findByValidation(boolean validation);

	public List<Article> findByValidationAndBeneficiaire(boolean b, Agent beneficiaire);

	public List<Article> findByValidationAndDirecArt(boolean b, Direction d);


	public List<Article> findByValidationAndBeneficiaireAndDirecArt(boolean b, Object object, Direction d);
}