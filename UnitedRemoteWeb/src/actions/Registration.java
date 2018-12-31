package actions;

import java.awt.Point;
import java.util.Random;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.User;
import metier.ShopOperations;

public class Registration {
	

	private String firstname;
	private String lastname;
	private String email;
	private String password;
	
	
	// Sign up action
	public String register() {
		try {
			Context context = new InitialContext();			
			ShopOperations op = (ShopOperations) context.lookup("ejb:/UnitedRemoteChallengeEjb/shop!metier.ShopOperations");
			
			// The location and the id of a user  
			Point userLocation = new Point(new Random().nextInt() % 7, new Random().nextInt()% 30);
			Long userID;
			
			// id < 0 unacceptable 
			do {
				userID = new Random().nextLong()%300;
			}while(userID <= 0);
			
			
			User user = new User(userID, firstname+" "+lastname, email, password, userLocation);
			op.addUser(user);
			return "success";
		} catch (NamingException e) {
			return null;
		}
		
	}
	
	
	// Class methods
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	
}