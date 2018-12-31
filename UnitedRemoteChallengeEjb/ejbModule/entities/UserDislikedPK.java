package entities;

import java.io.Serializable;
import java.lang.Long;

/**
 * ID class for entity: UserDisliked
 *
 */ 
public class UserDislikedPK  implements Serializable {   
   
	         
	private Long userID;         
	private Long shopID;
	private static final long serialVersionUID = 1L;

	public UserDislikedPK() {}

	

	public Long getUserID() {
		return this.userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}
	

	public Long getShopID() {
		return this.shopID;
	}

	public void setShopID(Long shopID) {
		this.shopID = shopID;
	}
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof UserDislikedPK)) {
			return false;
		}
		UserDislikedPK other = (UserDislikedPK) o;
		return true
			&& (getUserID() == null ? other.getUserID() == null : getUserID().equals(other.getUserID()))
			&& (getShopID() == null ? other.getShopID() == null : getShopID().equals(other.getShopID()));
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getUserID() == null ? 0 : getUserID().hashCode());
		result = prime * result + (getShopID() == null ? 0 : getShopID().hashCode());
		return result;
	}
   
   
}
