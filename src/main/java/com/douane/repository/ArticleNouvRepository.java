package com.douane.repository;

import com.douane.entite.Article;
import com.douane.entite.ArticleNouv;
import com.douane.entite.Direction;
import com.douane.entite.MaterielNouv;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hasina on 12/11/17.
 */
public interface ArticleNouvRepository extends CrudRepository<ArticleNouv,Long>  {
    public List<ArticleNouv> findAll();
    public List<ArticleNouv> findByValidationAndDirecArt(boolean validation, Direction direc);
	public List<ArticleNouv> findByValidationAndDirecArtOrderByIdArticleDesc(boolean val, Direction d);
	public List<ArticleNouv> findTop200ByValidationAndDirecArtOrderByIdArticleDesc(boolean val, Direction d);
    
}
