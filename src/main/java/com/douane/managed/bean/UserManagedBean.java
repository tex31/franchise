package com.douane.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.douane.entite.*;
import com.douane.metier.referentiel.IRefMetier;
import com.douane.requesthttp.RequestFilter;
import org.primefaces.context.RequestContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.douane.metier.user.IUserMetier;
import com.douane.model.User;

import java.sql.SQLException;



@ManagedBean(name="userMB")
@RequestScoped
@Transactional
public class UserManagedBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR   = "error";
	
	/*//Spring User Service is injected...
	@ManagedProperty(value="#{UserService}")
	IUserService userService;
	*/


	@ManagedProperty(value="#{usermetier}")
	IUserMetier usermetierimpl;



	@ManagedProperty(value="#{refmetier}")
	IRefMetier refmetierimpl;


	List<Agent> userList;
	
	
	private int id;
	private String name;
	private String firstname;
	private String password;
	private Long im;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private String username;


	private String role;
	
	private Useri roleuser;
	private boolean haveRole;
	private Poste poste;
	
	
	private String designation;
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private Direction direction;
	
	private String designationDir;
	private String codeDir;

	/**
	 * Add User
	 * 
	 * @return String - Response Message
	 */
	public void updateAgent(Agent a) {
		if(a==null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur agent null", "Agent null");
			FacesContext.getCurrentInstance().addMessage("editagenterror", message);
			
		}
		try {
		usermetierimpl.addAgent(a);
		
		}catch(Exception e) {
			FacesMessage messagea = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur Modification Agent", "Ne respecte pas les contraintes");
			FacesContext.getCurrentInstance().addMessage("editagenterror", messagea);
		}
		finally {
			//Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			//sessionMapObj.remove("editAgent");
		}
	}
	
	public String addUser() throws SQLException {
		try {
			Agent user = new Agent();
			Useri useri = new Useri();
			//user.setName(getName());

			user.setNomAgent(getName());

			//user.setUsername(getUsername());

			Direction dir = new Direction();

			if(designationDir!=null){
				dir.setDesignation(this.getDesignationDir());
				dir.setCodeDirection(this.getCodeDir());
			}

			user.setPrenomAgent(this.getFirstname());
			user.setIm(getIm());
			
			String hashedPassword = passwordEncoder.encode(getPassword());
			user.setPassword(hashedPassword);
			user.setPassword(hashedPassword);
			useri.setDesignation(designation);
			useri.setRole(role);
			user.setRoleAgent(useri);
			//user.setDirection(dir);
			
			user.setPosteny(getPoste());
			//getUsermetierimpl().addAgent(user);
			//refmetierimpl.addRef(new Useri(designation,role), new Agent(getIm(),getName(),hashedPassword,new Useri(designation,role)));
			//refmetierimpl.addRef(useri,user);



			usermetierimpl.addUser(useri);
			usermetierimpl.addAgent(user);
			//refmetierimpl.addRef(dir,user);
			

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Data Saved"));
			return SUCCESS;
		} catch(DataIntegrityViolationException e)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error creating user", "Role or User already exists");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Role or User already exists"));
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error creating user", "Role or User already exists");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Role or User already exists"));
			FacesContext.getCurrentInstance().addMessage(null, message);
			/*
			FacesContext.getCurrentInstance().addMessage("myForm:password1", new FacesMessage("Password Doesnt Match"));
			return ERROR;
			*/
			return null;
		}

	}

	public String addAgent() {
		try {
			Agent user = new Agent();
			//Useri useri = new Useri();
			//user.setName(getName());

			user.setNomAgent(getName());

			//user.setUsername(getUsername());

			user.setPrenomAgent(this.getFirstname());
			user.setIm(getIm());
			
			String hashedPassword = passwordEncoder.encode(getPassword());
			user.setPassword(hashedPassword);
			user.setPassword(hashedPassword);
			//useri.setDesignation(designation);
			//useri.setRole(role);
			user.setRoleAgent(this.getRoleuser());
			user.setDirection(this.getDirection());
			
			user.setPosteny(getPoste());
			//getUsermetierimpl().addAgent(user);
			//refmetierimpl.addRef(new Useri(designation,role), new Agent(getIm(),getName(),hashedPassword,new Useri(designation,role)));
			//refmetierimpl.addRef(useri,user);
			usermetierimpl.addAgent(user);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Données sauvegardées"));
			return SUCCESS;

		}catch(DataIntegrityViolationException e)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error creation", "La fonction ou l'utilisateur existe déjà");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La fonction ou l'utilisateur existe déjà"));
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error creating user", "La fonction ou l'utilisateur existe déjà");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La fonction ou l'utilisateur existe déjà"));
			FacesContext.getCurrentInstance().addMessage(null, message);
			/*
			FacesContext.getCurrentInstance().addMessage("myForm:password1", new FacesMessage("Password Doesnt Match"));
			return ERROR;
			*/
			return null;
		}
	}



	public String addAgentCA() {
		try {
			Agent user = new Agent();
			//Useri useri = new Useri();
			//user.setName(getName());

			user.setNomAgent(getName());

			//user.setUsername(getUsername());

			user.setPrenomAgent(this.getFirstname());
			user.setIm(getIm());
			
			String hashedPassword = passwordEncoder.encode(getPassword());
			user.setPassword(hashedPassword);
			user.setPassword(hashedPassword);
			//useri.setDesignation(designation);
			//useri.setRole(role);
			user.setRoleAgent(this.getRoleuser());
			user.setDirection(this.getDirection());
			
			user.setPosteny(getPoste());
			//getUsermetierimpl().addAgent(user);
			//refmetierimpl.addRef(new Useri(designation,role), new Agent(getIm(),getName(),hashedPassword,new Useri(designation,role)));
			//refmetierimpl.addRef(useri,user);
			usermetierimpl.addAgent(user);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Données sauvegardées"));
			return SUCCESS;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} 	
		FacesContext.getCurrentInstance().addMessage("myForm:password1", new FacesMessage("Erreur Mot de Passe"));
		return ERROR;
	}


	public String addUserRef() {
		try {
			Agent user = new Agent();
			Useri useri = new Useri();
			//user.setName(getName());

			user.setNomAgent(getName());

			//user.setUsername(getUsername());

			user.setPrenomAgent(getFirstname());
			user.setIm(getIm());
			
			String hashedPassword = passwordEncoder.encode(getPassword());
			user.setPassword(hashedPassword);
			user.setPassword(hashedPassword);
			useri.setDesignation(designation);
			useri.setRole(role);
			user.setRoleAgent(useri);
			user.setDirection(direction);
			//getUsermetierimpl().addAgent(user);
			//refmetierimpl.addRef(new Useri(designation,role), new Agent(getIm(),getName(),hashedPassword,new Useri(designation,role)));
			//refmetierimpl.addRef(useri,user);
			usermetierimpl.addUser(useri);
			usermetierimpl.addAgent(user);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Données sauvegardées"));
			return SUCCESS;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} 	
		FacesContext.getCurrentInstance().addMessage("myForm:password1", new FacesMessage("Erreur mot de passe"));
		return ERROR;
	}
	
	public String addUtilisateur() throws SQLException{
		try {
			Agent user = new Agent();
			user.setNomAgent(getName());

			//user.setUsername(getUsername());

			if(designationDir!=null){
				direction.setDesignation(designationDir);
				direction.setCodeDirection(codeDir);
			}

			user.setPrenomAgent(getFirstname());
			user.setIm(getIm());
			
			String hashedPassword = passwordEncoder.encode(getPassword());
			user.setPassword(hashedPassword);
			user.setPassword(hashedPassword);
			//useri.setDesignation(designation);
			//useri.setRole(role);
			user.setRoleAgent(getRoleuser());
			user.setDirection(direction);
			
			user.setPosteny(getPoste());
			
			//getUsermetierimpl().addAgent(user);
			//refmetierimpl.addRef(new Useri(designation,role), new Agent(getIm(),getName(),hashedPassword,new Useri(designation,role)));
			//refmetierimpl.addRef(useri,user);
			//usermetierimpl.addUser(useri);
			usermetierimpl.addAgent(user);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Données Sauvegardées"));
			return SUCCESS;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} 	
		FacesContext.getCurrentInstance().addMessage("myForm:password1", new FacesMessage("Erreur Mot de Passe"));
		return ERROR;
	}
	
	/**
	 * Reset Fields
	 * 
	 */
	public void reset() {
	
		this.setName(null);
		this.setFirstname(null);
		this.setPassword(null);
	}
	
	public String backToAddUser(){
		return "backform";
	}
	
	/**
	 * Get User List
	 * 
	 * @return List - User List
	 */
	public List<Agent> getUserList() {
		userList = new ArrayList<Agent>();
		userList.addAll(getUsermetierimpl().findAllAgents());
		return userList;
	}
	
	

	/**
	 * Get User Service
	 * 
	 * @return IUserService - User Service
	 
	public IUserService getUserService() {
		return userService;
	}

	
	 * Set User Service
	 * 
	 * @param IUserService - User Service
	 
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	
	 * Set User List
	 * 
	 * @param List - User List
	 */
	public void setUserList(List<Agent> userList) {
		this.userList = userList;
	}
	
	/**
	 * Get User Id
	 * 
	 * @return int - User Id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set User Id
	 * 
	 * @param int - User Id
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * Get User Direction
	 * 
	 * @return Direction - User direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Set User direction
	 * 
	 * @param Direction - User direction
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}



	/**
	 * Get User Name
	 * 
	 * @return String - User Name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set User Name
	 * 
	 * @param String - User Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public IUserMetier getUsermetierimpl() {
		return usermetierimpl;
	}

	public void setUsermetierimpl(IUserMetier usermetierimpl) {
		this.usermetierimpl = usermetierimpl;
	}

	public Long getIm() {
		return im;
	}

	public void setIm(Long im) {
		this.im = im;
	}


	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public String getDesignationDir() {
		return designationDir;
	}

	public void setDesignationDir(String designation) {
		this.designationDir = designation;
	}

	public String getCodeDir() {
		return codeDir;
	}

	public void setCodeDir(String designation) {
		this.codeDir = designation;
	}


	public IRefMetier getRefmetierimpl() {
		return refmetierimpl;
	}

	public void setRefmetierimpl(IRefMetier refmetierimpl) {
		this.refmetierimpl = refmetierimpl;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public Poste getPoste() {
		return poste;
	}


	public void setPoste(Poste poste) {
		this.poste = poste;
	}


	public Useri getRoleuser() {
		return roleuser;
	}


	public void setRoleuser(Useri roleuser) {
		this.roleuser = roleuser;
	}

	public void setHaveRole(boolean b)
	{
		this.haveRole = b;
	}

	public boolean getHaveRole(String role)
	{

		return ((Agent) RequestFilter.getSession().getAttribute("agent")).getRoleAgent().getRole().equals(role);

	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	//---------UPDATE PASSWORD--------------
	private Agent agent;

	private String pass;

	private String newPass;

	public boolean checkPreviousPass()
	{
		String passWordCur = ((Agent) RequestFilter.getSession().getAttribute("agent")).getPassword();
		if(passwordEncoder.matches(passWordCur, getPass()))
			return true;
		return false;
	}



	public Agent getAgent() {
		return (Agent) RequestFilter.getSession().getAttribute("agent");
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	@ManagedProperty(value="#{authenticationManager}")
	private AuthenticationManager authenticationManager = null;

	public void changePass() {

			FacesMessage message;
			try {
				Authentication request = new UsernamePasswordAuthenticationToken(((Agent) RequestFilter.getSession().getAttribute("agent")).getIm(), getPass());
				Authentication result = authenticationManager.authenticate(request);
				SecurityContextHolder.getContext().setAuthentication(result);
				Agent user = ((Agent) RequestFilter.getSession().getAttribute("agent"));
				//user.setPassword(passwordEncoder.encode(getNewPass()));


				Agent agent = usermetierimpl.findAgentByIm(user.getIm());
				usermetierimpl.changeAgentPass(agent, passwordEncoder.encode(getNewPass()));

				if (!passwordEncoder.matches(getNewPass(), usermetierimpl.findAgentByIm(user.getIm()).getPassword()))
				{

				}
				//agent.setPassword(passwordEncoder.encode(getNewPass()));




				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Succes", "Mot de passe mise à jour");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mot de passe mise à jour"));
				FacesContext.getCurrentInstance().addMessage(null, message);

			} catch (AuthenticationException e) {

				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur mot de passe", "Mot de passe actuelle erronée");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mot de passe actuelle erronée"));
				//throw new SecurityExecption("Wrong credentials");

			}


	}
	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	private Agent agentToModify;
	
	public Agent getAgentToModify() {
		return agentToModify;
	}

	public void setAgentToModify(Agent agentToModify) {
		if(agentToModify==null) {
			System.out.println("Tsy misy eh");
		}else {
			System.out.println("misy");
		}
		this.agentToModify = agentToModify;
	}
	public void setCurrentAgenttomodify(Agent a) {
		Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMapObj.put("editAgent", a);
		this.setAgentToModify(a);
	}
	
	public String exit(){
		Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		FacesMessage messagea = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modification Agent", "La modification a été annulée");
		FacesContext.getCurrentInstance().addMessage(null, messagea);
		//sessionMapObj.remove("editAgent");
		
		return SUCCESS;
	}
	

	List<Agent> userListByDirection;
	
	public void setUserListByDirection(List<Agent> userListByDirection) {
		this.userListByDirection = userListByDirection;
	}

	public List<Agent> getUserListByDirection() {
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		if(agent == null) {
			return null;
		}
		return getUsermetierimpl().listAgentByDirection(agent.getDirection());
	}
	
	public void changePassWithoutConfirm(Agent user) {
		System.out.println("change password");
		FacesMessage message;
		try {
			
			//user.setPassword(passwordEncoder.encode(getNewPass()));
			Agent agent = usermetierimpl.findAgentByIm(user.getIm());
			usermetierimpl.changeAgentPass(agent, passwordEncoder.encode(getNewPass()));

			if (!passwordEncoder.matches(getNewPass(), usermetierimpl.findAgentByIm(user.getIm()).getPassword()))
			{

			}
			//agent.setPassword(passwordEncoder.encode(getNewPass()));

			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Succes", "Mot de passe mise à jour");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mot de passe mise à jour"));
			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (Exception e) {

			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur changement mot de passe", "Mot de passe n'a pas pu être changer");
			FacesContext.getCurrentInstance().addMessage(null, message);
			//throw new SecurityExecption("Wrong credentials");

		}


	}
	
	
}
