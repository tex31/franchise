package come.douane.dao.operation;

import java.util.Date;
import java.util.List;

import com.douane.entite.Agent;
import com.douane.entite.CodeArticle;
import com.douane.entite.Direction;
import com.douane.entite.Materiel;
import com.douane.entite.MaterielEx;
import com.douane.entite.OpAttribution;
import com.douane.entite.OpDettachement;
import com.douane.entite.OpEntree;
import com.douane.entite.OpEntreeArticle;
import com.douane.entite.OpSortie;
import com.douane.entite.OpSortieArticle;
import com.douane.entite.Operation;
import com.douane.model.EtatOperation;

public interface IOperationDAO {
	public Agent detacherMat(OpDettachement det)throws Exception;
	public Materiel attribuerMat(OpAttribution attr)throws Exception;
	public Materiel attribuerMatEx(MaterielEx matex, Agent detenteur)throws Exception;
	
	public List<Operation> getListOpByDate(Date startDate, Date endDate , int maxresult);
	
	
	public List<OpEntree> getListOpEntreeOrderByDate(int maxresult);
	public List<OpSortie> getListOpSortieOrderByDate(int maxresult);
	public List<Operation> getListOperationOrderByDate(int maxresult);
	public List<OpAttribution> getListOpAttributionOrderByDate(int maxresult);
	public List<OpDettachement> getListOpDettachementOrderByDate(int maxresult);
	
	public List<OpEntree> getListOpEntreeByDirOrder(Direction dir, int maxresult);
	public List<OpSortie> getListOpSortieByDirOrder(Direction dir, int maxresult);
	public List<Operation> getListOperationByDirOrder(Direction dir, int maxresult);
	public List<OpAttribution> getListOpAttributionByDirOrder(Direction dir, int maxresult);
	public List<OpDettachement> getListOpDettachementByDirOrder(Direction dir, int maxresult);
	
	public List<OpEntree> getListOpEntreeByOperator(Agent operator, int maxresult);
	public List<OpSortie> getListOpSortieByOperator(Agent operator, int maxresult);
	public List<Operation> getListOperationByOperator(Agent operator, int maxresult);
	public List<OpAttribution> getListOpAttributionByOperator(Agent operator, int maxresult);
	public List<OpDettachement> getListOpDettachementByOperator(Agent operator, int maxresult);
	
	public List<OpEntree> getListOpEntreeByByMaterielBDate(Materiel m, Date startDate, Date endDate, int maxresult);
	public List<OpSortie> getListOpSortieByByMaterielBDate(Materiel m, Date startDate, Date endDate, int maxresult);
	
	public List<Operation> getListOpEntreeAndSortieByDirectionByYearByDateAsc(Direction d, Date startDate, Date endDate);
	
	public List<OpEntree> getListOpEntreeByDirectionByYearByDateAsc(Direction d, Date startDate, Date endDate);
	public List<OpSortie> getListOpSortieByDirectionByYearByDateAsc(Direction d, Date startDate, Date endDate);
	public List<Operation> getListOperationByDirectionByYearByDateAsc(Direction d, Date startDate, Date endDate);
	
	public List<Operation> getListAllOperationByYearByDateAsc(Date startDate, Date endDate);
	
	
	public List<OpEntreeArticle> getListOpEntreeArtByValideByDirection(EtatOperation etat, Direction direction, Date startDate, Date endDate);
	public List<OpSortieArticle> getListOpSortieArtByValideByDirection(EtatOperation etat, Direction direction, Date startDate, Date endDate);
	public List<Operation> getListOpESArtByValideByDirection(EtatOperation etat, Direction direction, Date startDate, Date endDate);
	
	
	public Long countOpSortieByYearByDirection(Date date,Direction d);
	public Long countOpEntreeByYearByDirection(Date date,Direction d);
	List<OpEntreeArticle> getListOpEntreeArtByDirection(Direction direction, Date startDate, Date endDate);
	List<OpSortieArticle> getListOpSortieArtByDirection(Direction direction, Date startDate, Date endDate);
	List<Operation> getListAllOperationByDirectionByYearByDateAsc(Direction d, Date startDate, Date endDate);
	
	List<Object[]> getDesingantionByOperationEntree(OpEntree oe);
	
	List<Object[]> getListForInventaire(Direction d, Date startDate, Date endDate);
	List<Object[]> getListForInventaireWithMatex(Direction d, Date startDate, Date endDate);
	public List<Operation> getListOpESArtByValideByDirectionByCod(EtatOperation accepted, Direction direction,
			Date startDate, Date endDate, CodeArticle codeart);
	public Long areporter(CodeArticle code, Direction direction, Date stopdat);
}
