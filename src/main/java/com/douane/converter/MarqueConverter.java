package com.douane.converter;

import com.douane.entite.EtatMateriel;
import com.douane.metier.referentiel.IRefMetier;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Created by hasina on 11/9/17.
 */
@FacesConverter("marqueConverter")
@RequestScoped
public class MarqueConverter implements Converter {

    @Autowired
    IRefMetier refmetierimpl;

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                //refmetierimpl
                return (EtatMateriel) refmetierimpl.findById(Long.parseLong(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((EtatMateriel) object).getId());
        }
        else {
            return null;
        }
    }
}
