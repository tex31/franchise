package com.douane.converter;

import com.douane.entite.Agent;
import com.douane.entite.Materiel;
import com.douane.metier.user.IUserMetier;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Created by hasina on 11/11/17.
 */

@ManagedBean
@RequestScoped
public class AgentConverter implements Converter{



    @ManagedProperty(value="#{usermetier}")
    IUserMetier userMetier;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("***************************AGENT CONV BEGIN************************************");
        if(value != null && value.trim().length() > 0)
        {
        	Long idUser;
        	try {
        		idUser=Long.parseLong(value);
        	}catch(Exception e) {
        		return null;
        	}
          try {
                return (Agent)this.userMetier.findAgentByIm(idUser);
            } catch(Exception e) {
                System.out.println("***************************AGENT CONV EXCEPTION********************************");
                /*e.printStackTrace();
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));*/
                return null;
            }
        }
        System.out.println("***************************AGENT CONV OK********************************");
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        if(object != null) {
            return String.valueOf(((Agent) object).getIm());
        }
        else {
            return null;
        }
    }

    public IUserMetier getUserMetier() {
        return userMetier;
    }

    public void setUserMetier(IUserMetier userMetier) {
        this.userMetier = userMetier;
    }
}
