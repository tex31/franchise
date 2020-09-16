package com.douane.dao.referentiel;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.douane.entite.Direction;
import com.douane.entite.Referentiel;

public class RefDAOImpl  implements IRefDAO{

	@PersistenceContext
	private EntityManager em;
	
	
	public RefDAOImpl() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public List<Referentiel> listRef(Referentiel r) {
		// TODO Auto-generated method stub
		Query req= em.createQuery("select n from "+r.getClass().getName()+" n");
		return (List<Referentiel>)req.getResultList();
	}


}
