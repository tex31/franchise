package com.douane.repository;

import com.douane.entite.Materiel;
import com.douane.entite.MaterielEx;
import com.douane.entite.*;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 * Created by hasina on 11/11/17.
 */
public interface MaterielExRepository extends CrudRepository<MaterielEx, Long> {
	public List<MaterielEx> findByDirec(Direction direc);
	public List<MaterielEx> findByDirecOrderByIdMaterielDesc(Direction direc);
	public List<MaterielEx> findByDirecOrderByIdMaterielAsc(Direction direc);
}
