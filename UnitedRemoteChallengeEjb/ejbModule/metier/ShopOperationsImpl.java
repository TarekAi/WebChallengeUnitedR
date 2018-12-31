package metier;

import java.awt.Point;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.ejb.Stateless;
import javax.persistence.*;
import entities.*;


@Stateless(name="shop")
public class ShopOperationsImpl implements ShopOperations{
	
	
	@PersistenceContext
	EntityManager em;
	

	// Add user to the database when registration
	public void addUser(User _user) { 
		em.persist(_user);
	}

	
	// Check the username and password before logging in 
	public Long isUser(String email, String password) {
		
		String hql = "FROM User U WHERE U.email = :email and U.password= :password";
		TypedQuery<User> query = em.createQuery(hql,User.class);
		query.setParameter("email", email);
		query.setParameter("password", password);		
		List<User> results = query.getResultList();
		if(results.size() != 1) 
			return 0L;		
		return results.get(0).getUserID();
	}
	
	 // Like a shop  
	 public void like(Long userID, Long shopID) {
		
			 //  Remove from disliked if exist
			UserDislikedPK dislikedPK = new UserDislikedPK();
			dislikedPK.setShopID(shopID);
			dislikedPK.setUserID(userID);
			UserDisliked userDisliked = em.find(UserDisliked.class, dislikedPK);
			if(userDisliked != null) em.remove(userDisliked);
			
			
		     //  Add to liked if not exist
			UserLikedPK likedPK = new UserLikedPK();
			likedPK.setShopID(shopID);
			likedPK.setUserID(userID);
			UserLiked userLiked = em.find(UserLiked.class, likedPK);
			
			if(userLiked==null) {
				UserLiked liked = new UserLiked(userID, shopID);
				em.persist(liked);
			}
			
		}
	
	  // Dislike a shop
	public void dislike(Long userID, Long shopID) {
	
		 //  Add to disliked if not exist
		UserDislikedPK dislikedPK = new UserDislikedPK();
		dislikedPK.setShopID(shopID);
		dislikedPK.setUserID(userID);
		UserDisliked userDisliked = em.find(UserDisliked.class, dislikedPK);

		if(userDisliked == null ) {
			RemoveFromPreferred(userID, shopID); // remove from liked if exist
			UserDisliked disliked = new UserDisliked(userID, shopID);
			em.persist(disliked);
		}
	}

	
	    //  Remove from liked 
	public void RemoveFromPreferred(Long userID, Long shopID) {
		
		UserLikedPK likedPK = new UserLikedPK();
		likedPK.setShopID(shopID);
		likedPK.setUserID(userID);
		UserLiked liked = em.find(UserLiked.class, likedPK);
		if(liked != null) em.remove(liked);
	}
	
	
	 // All liked shops for a specific user
	@SuppressWarnings("unchecked")
	public List<Shop> likedShops(Long userID) {
		
		//  Shops id's liked by the user "userID"
		String hql1 = "SELECT sh.shopID FROM UserLiked AS sh "
				+ "WHERE sh.userID = :userID";
		Query query1 = em.createQuery(hql1);
		query1.setParameter("userID", userID);
		List<Long> results1 = query1.getResultList();
		
		//  List shops from table shop using shopID returned above
		if(!results1.isEmpty()) {
			String hql2 = "FROM Shop AS sh WHERE sh.shopID IN (:results1)";
			Query query2 = em.createQuery(hql2,Shop.class);
			query2.setParameter("results1", results1);
			List<Shop> results2 = query2.getResultList();
			return results2;
		}
		return null;
	}

	
	   //  All disliked shops for a user (Same principle as likedShops method) 
	@SuppressWarnings("unchecked")
	public List<Shop> dislikedShops(Long uderID) {

		String hql1 = "SELECT sh.shopID FROM UserDisliked AS sh "
				+ "WHERE sh.userID = :userID";
		Query query1 = em.createQuery(hql1);
		query1.setParameter("userID", uderID);
		List<Long> results1 = query1.getResultList();
		
		
		String hql2 = "FROM Shop AS sh WHERE sh.shopID IN (:results1)";
		Query query2 = em.createQuery(hql2,Shop.class);
		query2.setParameter("results1", results1);
		List<Shop> results2 = query2.getResultList();
		return results2;
	}
	
	@SuppressWarnings("unchecked")
	public Map<Double,Shop> shopSortedByDistance(Long userID) {
		
		  //  Look for the user (userID) liked shops
		String likedShposID ="SELECT sh.shopID FROM UserLiked AS sh WHERE sh.userID= :userID";
		Query query2 = em.createQuery(likedShposID);
		query2.setParameter("userID", userID);
		List<Long> ListID = query2.getResultList();

		 //  Test if there is liked shop to expect  
		String hql;
		Query query;
		if(ListID.isEmpty()) {
			hql="FROM Shop AS sh";
			query = em.createQuery(hql,Shop.class);
		}
		
		else {
			hql="FROM Shop AS sh WHERE sh.shopID NOT IN (:ListID)";
			query = em.createQuery(hql,Shop.class);
			query.setParameter("ListID", ListID);
		}

		 
	     //  List the nearby shops 
		List<Shop> shops = query.getResultList();
		Map<Double,Shop> storedShops =  new TreeMap<Double, Shop>();
		for(Shop sh:shops){
			Point userposition = userPosition(userID);
			storedShops.put(distance2Points(sh.getLatitude(), sh.getLongitude(), userposition.getX(), userposition.getY()), sh);	
		}
		
		return storedShops;
	}

	
	   //  The position of the user
	@SuppressWarnings("unchecked")
	public Point userPosition(Long userID) {
		String hql1 = " FROM User U "
				+"WHERE U.userID = :userID";
		Query query = em.createQuery(hql1,User.class);
		query.setParameter("userID", userID);
		List<User> user = query.getResultList();

		return user.get(0).getUserLocation();
		
	}

		//  Distance between 2 points
	public double distance2Points( double x1, double y1, 
			double x2, double y2){       
		return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));		    
	}


}
