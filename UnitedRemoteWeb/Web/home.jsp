<%@page import=" java.util.Base64" %>
<%@page import="actions.AuthenticationAction"%>
<%@page import="java.util.Map"%>
<%@page import="entities.Shop"%>
<%@page import="java.util.List"%>
<%@page import="metier.ShopOperations"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<%@taglib uri="/struts-tags" prefix="s"%> 

 <%
    if (session.getAttribute("id")==null)
    {
    %>
        <jsp:forward page="login.jsp"></jsp:forward>
    <%
    }
%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   
    <title>Shops</title>

    <link rel="stylesheet" href="css/core-style.css">
    <link rel="stylesheet" href="style.css">

</head>

<body>


 <header class="header_area">        
        <nav class="navbar navbar-expand-lg navbar-light bg-light ">
      
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>

          <div class="collapse navbar-collapse w-100 order-3 dual-collapse2" id="navbarNavAltMarkup">
            <div class="navbar-nav  ml-auto">
                   	<a class="nav-item nav-link" href="/UnitedRemoteWeb/logout.action">Logout</a>
         		 	<a class="nav-item nav-link" href="/UnitedRemoteWeb/home.jsp">All Shops</a>
              		<a class="nav-item nav-link" href="/UnitedRemoteWeb/preferred.jsp">My preferred Shops</a>
            </div>
          </div>
        </nav>
    </header>
    
    <section class="shop_grid_area section-padding-80">
        <div class="container">
            <div class="row">
                <div class="col-12 col-md-12 col-lg-12">
                    <div class="shop_grid_product_area">    
                        <div class="row">
                        <%
                        	Context context = new InitialContext();
                        	ShopOperations  op = (ShopOperations) context.lookup("ejb:/UnitedRemoteChallengeEjb/shop!metier.ShopOperations");
                        	Map<Double,Shop> shops = op.shopSortedByDistance((Long) session.getAttribute("id"));
               
	                        	for(Map.Entry<Double, Shop> shop :shops.entrySet()){
	                        		byte[]  shopImage = Base64.getEncoder().encode(shop.getValue().getImage());
	                        		String  imageEncoded = new String(shopImage,"UTF8");
	                        		session.setAttribute("shopID", shop.getValue().getShopID());
                        
                        %>
                            <div class="col-3 col-sm-6 col-lg-4">

                                <div class="single-product-wrapper">
                                    <h3>
                                        <small class="text-muted"><%= shop.getValue().getName() %></small>
                                        
                                    </h3>
                                    <div class="product-img">
										<img style="width: 400px; height: 300px" alt="img" src="data:image/jpeg;base64,<%= imageEncoded %>"/>
                                    </div>

                                    <div class="mx-auto product-description" style="width: 150px;">
                                    
                                        <button type="button"  class="btn btn-outline-success mx-auto" onclick="window.location = 'like.action?id=' + <%= shop.getValue().getShopID()%>">  Like </button>
                                        <button type="button" class="btn btn-outline-danger" onclick="window.location = 'dislike.action?id=' + <%= shop.getValue().getShopID()%>"> Dislike </button>
                                    </div>

                                </div>

                            </div>
							<%} %>
                        </div>
                    </div>
                   
                </div>
            </div>
        </div>
    </section>

    <script src="js/jquery/jquery-2.2.4.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/plugins.js"></script>
    <script src="js/classy-nav.min.js"></script>
    <script src="js/active.js"></script>

</body>

</html>