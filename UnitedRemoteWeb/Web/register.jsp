<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Register</title>
    <link rel="stylesheet" href="css/core-style.css">
    <link rel="stylesheet" href="style.css">

</head>

<body>
   
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <header class="card-header">
                    <h4 class="card-title mt-2">Sign up</h4>
                </header>
                <article class="card-body">
                    <form action="register" method="post">
                        <div class="form-row">
                            <div class="col form-group">
                                <label>First name </label>   
                                <input type="text" class="form-control" name="firstname">
                            </div> 
                            <div class="col form-group">
                                <label>Last name</label>
                                <input type="text" class="form-control" name="lastname">
                            </div> 
                        </div> 
                        <div class="form-group">
                            <label>Email address</label>
                            <input type="email" class="form-control" name="email">
                            <small class="form-text text-muted">We'll never share your email with anyone else.</small>
                        </div>  

                        <div class="form-group">
                            <label> Password</label>
                            <input class="form-control" type="password" name="password">
                        </div> 

                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block"> Register  </button>
                        </div>                                              
                    </form>
                </article> 
                <div class="border-top card-body text-center">Have an account? <a href="/UnitedRemoteWeb/login.jsp">Log In</a></div>
            </div> 
        </div> 
    </div> 
</div> 



    <script src="js/jquery/jquery-2.2.4.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/plugins.js"></script>
    <script src="js/classy-nav.min.js"></script>
    <script src="js/active.js"></script>

</body>

</html>