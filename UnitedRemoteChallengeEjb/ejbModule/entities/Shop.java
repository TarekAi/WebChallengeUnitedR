package entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Shop
 *
 */
@Entity

public class Shop implements Serializable {

	   
	@Id
	@GeneratedValue
	private Long shopID;
	private String name;
	@Basic(fetch=FetchType.LAZY)
	@Lob
	private byte[] image;
	private double latitude;
	private double longitude;
	private static final long serialVersionUID = 1L;

	public Shop() {
		super();
	}   
	public Long getShopID() {
		return this.shopID;
	}

	public void setShopID(Long shopID) {
		this.shopID = shopID;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}   
	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}   
	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
   
}
