package com.douane.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.douane.entite.ArticleEx;
import com.douane.entite.ArticleNouv;
import com.douane.entite.CodeArticle;
import com.douane.entite.Direction;

public interface ArticleExRepository extends CrudRepository<ArticleEx,Long>{
	public List<ArticleEx> findByDirecArt(Direction direc);
	public List<ArticleNouv> findByValidationAndDirecArt(boolean validation, Direction direc);
	public List<ArticleEx> findByDirecArtOrderByIdArticleDesc(Direction d);
	public List<ArticleEx> findTop200ByDirecArtOrderByIdArticleDesc(Direction d);
    
    
}