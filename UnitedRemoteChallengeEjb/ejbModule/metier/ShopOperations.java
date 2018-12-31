package metier;

import java.awt.Point;

import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import entities.*;




@Remote
public interface ShopOperations {
	
	void addUser(User _user);
	Long isUser(String email, String password);
	Map<Double, Shop> shopSortedByDistance(Long userID);
	List<Shop> likedShops(Long uderID);
	List<Shop> dislikedShops(Long uderID);
	void like(Long userID, Long shopID);
	void dislike(Long userID, Long shopID);
	Point userPosition(Long userID); 
	void  RemoveFromPreferred(Long userID, Long shopID);
	double distance2Points( double x1, double y1, double x2, double y2);
}
