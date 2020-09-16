package com.douane.converter;

import com.douane.entite.*;
import com.douane.metier.referentiel.IRefMetier;
import org.springframework.asm.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Created by hasina on 11/9/17.
 */
//@FacesConverter("refConverter")
@ManagedBean
@RequestScoped
public class RefConverter implements Converter {


    @ManagedProperty(value="#{refmetier}")
    IRefMetier refmetierimpl;

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
    	
    	
    	if(value==null) {
    		return null;
    	}
        
        if(value != null && value.trim().length() > 0) {
            try {
                //refmetierimpl
            	System.out.println("**************** REFERENTIEL CONVERTER BEGIN ********** "+value);
            	if(value.equals("null")) {
            		return new Marque();
            	}
            	
            	

                Referentiel ref = refmetierimpl.findById(Long.parseLong(value));
                if(ref instanceof EtatMateriel)
                {
                	EtatMateriel e = (EtatMateriel) ref;
                    return e;
                }
                else if(ref instanceof Marque)
                {
                    return (Marque) ref;
                }
                else if(ref instanceof Nomenclature)
                {
                    return (Nomenclature) ref;
                }
                
                else if(ref instanceof TypeMateriel)
                {
                    return (TypeMateriel) ref;
                }
                else if(ref instanceof ModeAcquisition) {
                	return (ModeAcquisition) ref;
                }
                else if(ref instanceof Financement) {
                	return (Financement) ref;
                }
                else if(ref instanceof Fournisseur) {
                	return (Fournisseur) ref;
                }
                else if(ref instanceof Direction) {
                	return (Direction) ref;
                }
                else if(ref instanceof Poste) {
                	return (Poste) ref;
                }
                else if(ref instanceof MotifSortie) {
                	return (MotifSortie) ref;
                }
                else if(ref instanceof MotifDecharge) {
                	return (MotifDecharge) ref;
                }
                else if(ref instanceof TypeObjet) {
                	return (TypeObjet) ref;
                }
                else if(ref instanceof CodeArticle) {
                	return (CodeArticle)ref;
                }
            } catch(NumberFormatException e) {
            	System.out.println("************* REF CONV EXCEPTION ***********");
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, " Erreur Referentiel", "Referentiel non valide"));
            }
        }

        return null;


    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
    	
    	if(object != null) {
            return String.valueOf(((Referentiel) object).getId());
        }
        else {
            return "";
        }
    }


    public IRefMetier getRefmetierimpl() {
        return refmetierimpl;
    }

    public void setRefmetierimpl(IRefMetier refmetierimpl) {
        this.refmetierimpl = refmetierimpl;
    }
}
