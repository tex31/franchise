package com.douane.managed.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.douane.security.SecurityExecption;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;


@ManagedBean(name="loginMgmtBean")
@RequestScoped
public class LoginBean {


	private String immatriculation = null;
	    private String password = null;
	    private Object im;
	    
	    @ManagedProperty(value="#{authenticationManager}")
	    private AuthenticationManager authenticationManager = null;

	    public String login() throws Exception {
	    	FacesMessage message = null;
	        try
			{
	            Authentication request = new UsernamePasswordAuthenticationToken(this.getImmatriculation(), this.getPassword());
	            Authentication result = authenticationManager.authenticate(request);
	            SecurityContextHolder.getContext().setAuthentication(result);
	            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    			session.setAttribute("im", this.getImmatriculation());
				//message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", this.getImmatriculation());
				//FacesContext.getCurrentInstance().addMessage(null, message);

				return "correct";
	        } catch (AuthenticationException e) {

	            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Erreur lors de l'authentification");
	            FacesContext.getCurrentInstance().addMessage(null, message);
				//throw new SecurityExecption("Wrong credentials");
				return "";
	        }

	    }

	    public String cancel() {
	        return null;
	    }
	    
	    public String signUp() {
	        return "success";
	    }

	    public String logout(){
	        SecurityContextHolder.clearContext();
	        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    		session.invalidate();
	        return "loggedout";
	        
	    }



	    public void setIm(Object im){
	    	this.im = im;
	    }

	    public Object getIm(){
	    	return ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("im");
	    }

	    public void test() throws Exception {
	    	System.out.println("***************************LOGIN REDIRECT TEST ********************************");

	    	if (this.im != null) {
	    		FacesContext.getCurrentInstance().getExternalContext().redirect("/pages/secure/choice.xhtml"); 
	    		
	    	}
	    }
	 
	    public AuthenticationManager getAuthenticationManager() {
	        return authenticationManager;
	    }

	    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
	        this.authenticationManager = authenticationManager;
	    }



	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }
	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}
}
