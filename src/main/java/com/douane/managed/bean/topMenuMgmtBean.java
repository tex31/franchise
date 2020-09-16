package com.douane.managed.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.douane.entite.Agent;
import com.douane.entite.Materiel;
import com.douane.requesthttp.RequestFilter;

import org.springframework.transaction.annotation.Transactional;


@ManagedBean(name="topMenuMB")
@RequestScoped
@Transactional
public class topMenuMgmtBean {

	private Agent curentAgent;


	public Agent getCurentAgent(){
		return (Agent)RequestFilter.getSession().getAttribute("agent");
	}

	public void setCurentAgent(Agent a){
		this.curentAgent = a;
	}
	
	public String goToDashboard(){
		return "dashboard";
		
	}

	public String goToDashboardadmin(){
		return "dashboardCA";
		
	}

	public String goToDashboardCM(){
		return "cm";
	}
	public String goToDashboardCA(){
		return "ca";
	}
	public boolean isMaterialNonAcceptedAndAgentDC(Materiel m ) {
		return (!m.isValidation() && this.getCurentAgent().getRoleAgent().getRole().equals("ROLE_DC"));
	}
	
	public String goToDashboardGeneral(){
		return "dashboardGeneral";
	}
}
