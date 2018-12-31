package entities;

import java.io.Serializable;
import java.lang.Long;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: UserDisliked
 *
 */
@Entity

@IdClass(UserDislikedPK.class)
public class UserDisliked implements Serializable {

	   
	@Id
	private Long userID;   
	@Id
	private Long shopID;
	private static final long serialVersionUID = 1L;

	public UserDisliked() {
		super();
	}   
	public UserDisliked(Long userID, Long shopID) {
		this.userID = userID;
		this.shopID = shopID;
	}
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
   
}
