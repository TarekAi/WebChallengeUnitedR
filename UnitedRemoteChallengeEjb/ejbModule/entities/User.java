package entities;

import java.awt.Point;
import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.validator.constraints.Email;


/**
 * Entity implementation class for Entity: user
 *
 */
@Entity

public class User implements Serializable {
	
	private Long userID;
	private String name;
	@Email
	@Id
	private String email;
	private String password;	
	private Point userLocation;

	public User(Long userID, String name, String email, String password, Point userLocation) {
		super();
		this.userID = userID; 
		this.name = name;
		this.email = email;
		this.password = password;
		this.userLocation = userLocation;
	}

	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}     
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}   
	public Point getUserLocation() {
		return this.userLocation;
	}

	public void setUserLocation(Point userLocation) {
		this.userLocation = userLocation;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
   
}
