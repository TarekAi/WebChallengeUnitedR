package actions;

import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.struts2.interceptor.SessionAware;


import metier.ShopOperations;

public class AuthenticationAction implements SessionAware{

	public AuthenticationAction() {
		super();
	}

	private Map<String ,Object> sessionMap;
	private String email;
	private String password;
	private Long id;
	
	// Login action
	public String login() {
		try {
			Context context = new InitialContext();			
			ShopOperations op = (ShopOperations) context.lookup("ejb:/UnitedRemoteChallengeEjb/shop!metier.ShopOperations");
			
			if(sessionMap.containsKey("id")) return "success";
			if(op.isUser(getEmail(), getPassword()) != 0) 
			{
				sessionMap.put("id", op.isUser(getEmail(), getPassword()));
				return "success";
			}
			return "error";
		} catch (NamingException e) {
			System.out.println(" Naming Exception ");
			return null;
		}
	}
	
	
	// Logout action
	public String logout() {		
		sessionMap.remove("id");
		return "success";
	}
	
	
	// Like action
	public String like() 
	{
		try {
			Context  context = new InitialContext();
			ShopOperations op = (ShopOperations) context.lookup("ejb:/UnitedRemoteChallengeEjb/shop!metier.ShopOperations");
			op.like((Long) sessionMap.get("id"), getId());
			System.out.println(getId());
			return "success";
		} catch (NamingException e) {
			return null;
		}
	}
	
	
	// Dislike action
	public String dislike() 
	{
		try {
			Context  context = new InitialContext();
			ShopOperations op = (ShopOperations) context.lookup("ejb:/UnitedRemoteChallengeEjb/shop!metier.ShopOperations");
			op.dislike((Long) sessionMap.get("id"), getId());
			return "success";
		} catch (NamingException e) {
			return "error";
		}
	}
	
	
	// Remove from liked action
	public String remove() {
		
		try {
			Context  context = new InitialContext();
			ShopOperations op = (ShopOperations) context.lookup("ejb:/UnitedRemoteChallengeEjb/shop!metier.ShopOperations");
			op.RemoveFromPreferred((Long) sessionMap.get("id"), getId());
			return "success";
		} catch (NamingException e) {
			return "error";
		}
	}
	
	
	// Class methods
	
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
		
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
