package com.douane.entite;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("4")
public class ArticleNouv extends Article{
    @ManyToOne
    @JoinColumn(name="idFournisseur")
    private Fournisseur fournisseur;


    @ManyToOne
    @JoinColumn(name="idModAcqArt")
    private ModeAcquisition modAcqArt;
    @ManyToOne
    @JoinColumn(name="idFin")
    private Financement financementArt;

    public ModeAcquisition getModAcqArt() {
        return modAcqArt;
    }

    public void setModAcqArt(ModeAcquisition modAcqArt) {
        this.modAcqArt = modAcqArt;
    }

    public Financement getFinancementArt() {
        return financementArt;
    }

    public void setFinancementArt(Financement financementArt) {
        this.financementArt = financementArt;
    }

   

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

   

    public ArticleNouv(Fournisseur fournisseur, Double prix) {
        super();
        this.fournisseur = fournisseur;
        this.prix = prix;
    }

    public ArticleNouv() {
        super();
        // TODO Auto-generated constructor stub
    }



}