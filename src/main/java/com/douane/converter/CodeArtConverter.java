package com.douane.converter;

import com.douane.entite.*;
import com.douane.metier.referentiel.IRefMetier;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
@ManagedBean
@RequestScoped
public class CodeArtConverter implements Converter {

    @ManagedProperty(value="#{refmetier}")
    IRefMetier refmetierimpl;

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
    	System.out.println("************* CODE ARTICLE CONV BEGIN ***********");
    	
    	if(value==null) {
    		return null;
    	}
        
        if(value != null && value.trim().length() > 0) {
            try {
                //refmetierimpl
            	System.out.println("Code art converter "+value);
            	if(value.equals("null")) {
            		return new CodeArticle();
            	}
            	
                Referentiel ref = refmetierimpl.findById(Long.parseLong(value));
                
                 if(ref instanceof CodeArticle) {
                	return (CodeArticle)ref;
                }
            } catch(NumberFormatException e) {
            	System.out.println("************* CODE ARTICLE CONV EXCEPTION ***********");
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, " Erreur Code Article", "Referentiel non valide"));
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
