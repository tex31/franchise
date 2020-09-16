package com.douane.entite;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

@Entity
public class TypeMateriel extends Referentiel{
	@Column(unique=true)
	private String codeTypeMate;

	public TypeMateriel(){
		this.setTable("TypeMateriel");
	}
	public TypeMateriel(String designation){
		super(designation);
		this.setTable("TypeMateriel");
	}
	@ManyToOne
	@JoinColumn(name="idNomenclature")
	private Nomenclature nomenclaureParent;
	




	public Nomenclature getNomenclaureParent() {
		return nomenclaureParent;
	}
	public void setNomenclaureParent(Nomenclature nomenclaureParent) {
		this.nomenclaureParent = nomenclaureParent;
	}
	public String getCodeTypeMate() {
		return codeTypeMate;
	}
	public void setCodeTypeMate(String codeTypeMate) {
		this.codeTypeMate = codeTypeMate;
	}
	
	@OneToMany(mappedBy="typematerieladd")
    @OrderColumn(name="materielcount")
	private List<Materiel> listMateriel;
	
}
