<%-- 
    Document   : login.jsp
    Created on : Sep 13, 2024, 3:35:54 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Login V1</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="icon" type="image/png" href="img/icons/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/util.css" />
    <link rel="stylesheet" type="text/css" href="css/main.css" />
  </head>
  <body>
    <div class="limiter">
      <div class="container-login100">
        <div class="wrap-login100">
            
          <div class="login100-pic js-tilt" data-tilt>
            <img src="img/img-01.png" alt="IMG" />
          </div>

            <form class="login100-form validate-form" action="login" method="POST">
            <span class="login100-form-title"> Login in here </span>

            <div class="wrap-input100 validate-input" data-validate="Valid email is required: ex@abc.xyz">
              <input class="input100" type="text" name="email" placeholder="Email"/>
              <span class="focus-input100"></span>
              <span class="symbol-input100">
                <i class="fa fa-envelope" aria-hidden="true"></i>
              </span>
            </div>

            <div class="wrap-input100 validate-input" data-validate="Password is required">
              <input class="input100" type="password" name="password" placeholder="Password"/>
              <span class="focus-input100"></span>
              <span class="symbol-input100">
                <i class="fa fa-lock" aria-hidden="true"></i>
              </span>
            </div>
            
            <p style="color: red" class="text-center">${requestScope.error}</p> 
            
            <div class="container-login100-form-btn">
              <button class="login100-form-btn">Login</button>
            </div>
            
            <div class="text-center p-t-12">
              <span class="txt1"> Forgot </span>
              <a class="txt2" href="#"> Username / Password? </a>
            </div>

            <div class="text-center p-t-136">
              <a id="loginBtn" class="txt2" href="create_account.jsp">
                Create your Account
                <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
              </a>
            </div>
            
          </form>
        </div>
      </div>
    </div>

    <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
    <script src="vendor/tilt/tilt.jquery.min.js"></script>
    <script>
      $(".js-tilt").tilt({
        scale: 1.1,
      });
    </script>
    <script src="js/main.js"></script>
  </body>
</html>

