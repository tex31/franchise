package com.douane.metier.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.douane.entite.*;
import com.douane.model.EtatOperation;

public interface IUserMetier {
	/**
	 * Affichage
	 */
	public void seeMat(Materiel m);
	public void seeAgent(Agent a);
	
	/*
	 * GESTION DES UTILISATEURS
	 * */
	
	public Useri addUser(Useri u);
	public void remUser(Useri u);
	public Agent addAgent(Agent a);
	public Agent changeAgentPass(Agent a, String codedPassword);
	public void remAgent (Agent a);
	public Agent addAgentUser(Agent a, Useri u);
	public Agent findAgentByIm (Long im_agent);
	public void desactiveAgent(Agent a);
	
	public List<Useri> listUser();
	//temporary
	public List<Agent> findAllAgents();
	public List<Agent> findAgentByNom(String name);
	public List<Agent> listAgentByDirection(Direction direction);

	//les requetes
	public OpEntree reqEntrerMateriel(List<Materiel> m, Agent dc, String facturePath, String refFacture);
	//public OpSortie reqSortirMateriel(Materiel m, MotifSortie motif, Direction d, Service s, Bureau b, Agent op)throws Exception;
	public OpAttribution reqAttribution(Materiel m, Agent oper, Agent detenteur) throws Exception;
	public OpDettachement reqDettachement(Materiel mat1, Agent agent2, Agent agent1,MotifDecharge m)throws Exception;
	
	//les validations
	public Materiel entrerMateriel(OpEntree op);
	public Materiel sortirMateriel(OpSortie sortie) throws Exception;
	public Agent detacherMateriel(OpDettachement det) throws Exception;
	public Materiel attriuberMateriel(OpAttribution attr) throws Exception;
	
	public OpEntree reqMatAModifier(OpEntree entree, String motif)throws Exception;
	public OpSortie reqSortirAModifier(OpSortie sort, String motif);
	public OpEntree reqMatRefuser(OpEntree entree, String string)throws Exception;
	public OpSortie reqSortirRefuser(OpSortie sortie, String string);
	public OpDettachement reqDetRefuser(OpDettachement det, String string);
	public OpAttribution reqAttrAModifier(OpAttribution attr, String motif)throws Exception;
	public OpAttribution reqAttrRefuser(OpAttribution attr, String motif)throws Exception;

	public Materiel updateMateriel(Materiel m);
	//Miasa ve ???
	public Materiel attribuerMaterielEx (MaterielEx matex, Agent detenteur)throws Exception;
	
	//Gestion des materiels
	public void delMat(Materiel m);


	/*
	 * GETTERS
	 *
	 */
	
	//Get List Materiel
	public Materiel getMatById(Long idmat);
	public List<Materiel> getListMatByDet(Agent detenteur);
	public List<Materiel> getListMatByNom(Nomenclature nomenclature);
	public List<Materiel> getListMatByDirection(Direction direction);
	public List<Materiel> getMatByDetenteurAndValidation(Agent detenteur,boolean validation);
	public List<Materiel> getMatByDetenteurAndDirection(Agent detenteur,Direction direction);
	public List<MaterielNouv> getListMaterielNouvValide(Direction d);
	
	public List<MaterielNouv> getListMaterielNouvNonValide(Direction d);
	

	
	public List<Materiel> getListMat();//pour test
	public List<MaterielEx> getListMatEx(Direction d);//pour test
	public List<MaterielNouv> getListMatNouv();//POut test seulement
	public List<Materiel> getMatByValidation(boolean validation);//pour test
	
	//Listes des Operations

	public List<Operation> getListOp(); //pour test
	public List<OpEntree> getListOpEntree();//pour test 
	public List<OpSortie> getListOpSortie();//pour test
	public List<OpAttribution> getListOpAttribution();//pour test
	public List<OpDettachement> getListOpDettachement();//pour test
	public List<Operation> getListOpBetween(Date startDate, Date endDate);//pour test
	
	public List<Operation> getListOpByOperator(Agent operator);
	public List<OpEntree> getListOpEntreeByOperator(Agent operator);
	public List<OpSortie> getListOpSortieByOperator(Agent operator);

	public List<Operation> getListOpByDirection(Direction direction);
	/*public List<OpEntree> getListOpEntreeByDirection(Direction direction);
	public List<OpSortie> getListOpSortieByDirection(Direction direction);*/

	public List<OpAttribution> getListOpAttrByOperator(Agent operator);
	public List<OpDettachement> getListOpDettByOperatort(Agent operator);
	public List<OpAttribution> getListOpAttrByDirection(Direction direction, Date sdate, Date edate);
	public List<OpAttribution> getListOpAttrByValideByDirection(Direction direction, Date sdate, Date edate,EtatOperation e);
	
	
	List<OpDettachement> getListOpDettByDirection(Direction direction, Date sdate, Date edate);


	
	public List<OpEntree> getListOpEntreeByMat(Materiel m);
	public List<OpSortie> getListOpSortieByMat(Materiel m);

	public List<OpAttribution> getListOpAttrByMat(Materiel m);
	public List<OpDettachement> getListOpDettByMat(Materiel m);

	public List<OpEntree> getListOpEntreeByMatAndByState(Materiel m, EtatOperation e);
	public List<OpSortie> getListOpSortieByMatAndByState(Materiel m, EtatOperation e);
	public List<OpAttribution> getListOpAttrByMatAndByState(Materiel m, EtatOperation e);
	public List<OpDettachement> getListOpDettByMatAndByState(Materiel m, EtatOperation e);

	public List<OpEntree> getListOpEntreeByMatBDate(Materiel m, Date startDate, Date endDate);
	public List<OpSortie> getListOpSortieByMatBDate(Materiel m, Date startDate, Date endDate);
	public List<Operation> getListOpEntreeAndSortieByDirectionByYearByDateAsc(Direction d,  Date startDate, Date endDate);
	
	public List<OpEntree> getListOpEntreeByDirectionByYearByDateAsc(Direction d,  Date startDate, Date endDate);
	public List<OpSortie> getListOpSortieByDirectionByYearByDateAsc(Direction d,  Date startDate, Date endDate);
	public List<Operation> getListOperationByDirectionByYearByDateAsc(Direction d,  Date startDate, Date endDate);

	public List<Operation> getListAllOperationByYearByDateAsc(Date startDate, Date endDate);

	
	//okay
	public OpEntree getOperationEntreeById(Long idopentree);
	


	//FOR CA
	public CodeArticle addCodeArticle(CodeArticle codeArticle);
	public void removeCodeArticle(CodeArticle codearticle);
	public List<CodeArticle> listCodeArticle();
	public List<CodeArticle> listCodeArticleByTypeObj(TypeObjet typeObj);

	public OpEntreeArticle reqEntrerArticle(Article article, Agent dc);
	public OpSortieArticle reqSortirArticle(Article article, Agent op, Agent destinataire, Long nbr, String decision)throws Exception;


	public OpEntreeArticle reqArtAModifier(OpEntreeArticle entreeArt, String motif)throws Exception;
	public OpSortieArticle reqSortirArtAModifier(OpSortieArticle sortArt, String motif) throws Exception;
	public OpEntreeArticle reqArtRefuser(OpEntreeArticle entreeArt, String motif)throws Exception;
	public OpSortieArticle reqSortirRefuser(OpSortieArticle sortArt, String motif)throws Exception;



	public Article entrerArticle(OpEntreeArticle opentreeart);
	public Article sortirArticle(OpSortieArticle sortieart) throws Exception;
	public List<ArticleEx> getListArticleEx(Direction d);
	public List<ArticleNouv> getListAllArticleNouv();
	public List<Article> getListAllArticle();
	public List<Article> getListArticleValideByDirection(Direction d);
	public List<Article> getListArticleNonDetenuValideByDirection(Direction d);
	public List<Article> getListArticleByDetenteurByValida(boolean valide, Agent detenteur);
	public List<Article> getListArticleByValidationByDirection(boolean valide, Direction d);
	
	
	public List<OpEntreeArticle> getListOpEntreeArtByValideByDirection(EtatOperation etat, Direction direction, Date startDate, Date endDate);
	public List<OpEntreeArticle> getListOpEntreeArtByDirection(Direction direction, Date startDate, Date endDate);
	
	public List<OpSortieArticle> getListOpSortieArtByValideByDirection(EtatOperation etat,Direction direction, Date startDate, Date endDate);
	public List<OpSortieArticle> getListOpSortieArtByDirection(Direction direction, Date startDate, Date endDate);

	

	public ArticleNouv addArticleNouv(CodeArticle cde, Agent ben, Agent depo, Fournisseur fourn, Double prix, Long nombre, Marque marqueArt,String caraArt);
	public ArticleEx addArticleEx(CodeArticle cde, Agent ben, Agent depo, Double prix, Long nombre, Marque marqueArt,String caraArt);
	
	
	public OpSortie reqSortirMateriel(Materiel m, MotifSortie motif, Direction d, Agent oper)throws Exception;;
	public OpSortie reqSortirMateriel(Materiel m, MotifSortie motif, Agent oper)throws Exception;;
	
	public List<ArticleNouv> getListArtNouvValideByDirection(Direction d);
	public List<ArticleNouv> getListArtNouvByValidationByDirection(boolean val,Direction d);
	public List<ArticleEx> getListArtExByDirction(Direction d);
	
	public Article getArticleById(Long id);

	List<Materiel> getMatByValidationAndDetenteurAndDirection(boolean val, Agent detenteur, Direction direction);

	List<Operation> getListAllOperationByDirectionByYearByDateAsc(Direction d, Date startDate, Date endDate);

	public List<OperationES> getListOpESForJournal(Direction direction, Date sdate, Date edate);
	public List<OpSortie> getListOpSortieValideByDirection(Direction direction);

	List<Object[]> getListObjectForinvetaire(Direction d, Date startDate, Date endDate);
	
	public void entrerMaterielExistant(Designation des, List <MaterielEx> matexs, Agent dc);
	public OpEntree reqEntrerMaterielNouv(Designation des,List<Materiel> l, Agent dc, String facturePath, String refFacture);
	public OpEntree reqEntrerMaterielNouv(Map<Designation, List<MaterielNouv>> mappingdeslistmat, Agent agent,
			String attribute, String refFacture);
	
	public List<Object[]> listDesignationByOperationEntree(OpEntree operationentree);
	public List<OpEntree> listOpentreeByStateByDirection(EtatOperation etat, Direction d);
	public void updateArticle(Article art);
	public Long calculArticleRestant(Article article);
	
	
	public List<Operation> getListOpESArtValideByDirection(Direction direction, Date startDate,	Date endDate);
	
	public List<Operation> getListOpESArtValideByDirectionByCod(CodeArticle codeart, Direction direction, Date startDate,	Date endDate);
	public Long getAreporter(CodeArticle code, Direction direction, Date stopdate);
	List<OpSortie> getListOpSortieByDirection(Direction direction, Date sdate, Date edate);
	List<OpEntree> getListOpEntreeByDirection(Direction direction, Date sdate, Date edate);
	

}
