package com.douane.converter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.douane.entite.Article;
import com.douane.entite.Materiel;
import com.douane.metier.user.IUserMetier;

@ManagedBean
@RequestScoped
public class ArticleConverter implements Converter {
	@ManagedProperty(value="#{usermetier}")
    IUserMetier userMetier;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && value.trim().length() > 0)
        {
            try {
                System.out.println("***************************ARTICLE CONV BEGIN********************************");
                return this.userMetier.getArticleById(Long.parseLong(value));
            } catch(Exception e) {
                System.out.println("***************************ARTICLE CONV EXCEPTION********************************");
                e.printStackTrace();
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur Article", "Article non valide"));
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        if(object != null) {
            return String.valueOf(((Article) object).getIdArticle());
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
