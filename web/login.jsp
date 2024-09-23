<%-- 
<<<<<<< HEAD
    Document   : login.jsp
    Created on : Sep 13, 2024, 3:35:54 PM
    Author     : ADMIN
=======
    Document   : login
    Created on : Sep 20, 2024, 9:59:58 PM
    Author     : mocun
>>>>>>> fbf38e88c7423732c03f084d6c211a2ea03af5a2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<<<<<<< HEAD
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
    <style>
        .button {
            border-style: solid;
            border-radius: 40px;
            height: 60px;
            text-align: center;
            background-color: red;
            color: white;
        }
        .button1 {
            border-style: solid;
            border-radius: 40px;
            height: 60px;
            text-align: center;
            background-color: blue;
            color: white;
        }
    </style>
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
            </div> <br/>
            
            <p style="color: green" class="text-center">${requestScope.message}</p> 
            
<!--            <div class="container-login100-form-btn">                
                <p id="loginBtn" class="txt2">Or</p><i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i><br/>
                <a href="login?action=gmail" class="login100-form-btn button">Login With Google</a> 
                <a href="login?action=facebook" class="login100-form-btn button1">Login With Facebook</a> 
            </div>-->

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
=======
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title><dec:title default="Login"/></title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="././css/styleLogin.css"/>
    </head>
    <body id ="LoginForm"/>
    <dec:body/>
    <div class="container">
        <div class="login-box">
            <div class="login-left">
                <div class="featured-image">
                    <img src="img/1.png" alt="Be Verified">
                </div>
                <p class="title">Be Verified</p>
                <p class="subtitle">Join experienced Designers on this platform.</p>
            </div>
            <div class="login-right">
                <div class="alert alert-${alert}">
                ${message}</div>
                <h2>Hello,Again</h2>
                <p>We are happy to have you back.</p>
                <form action="<c:url value="/login"/>" id="formLogin" method="POST">
                    <input type="text" name="email" id="email" placeholder="Email address" class="input-field">
                    <input type="password" name="password" id="password" placeholder="Password" class="input-field">
                    <div class="remember-forgot">
                        <label><input type="checkbox" name="remember"> Remember Me</label>
                        <a href="#">Forgot Password?</a>
                    </div>
                    <input type="hidden" value="login" name="action"/>
                    <button type="submit" class="btn-login">Login</button>
                    <!-- Nut Back to Home -->
                    <a href="./customer" class="btn-back-home">Back to Home</a>
                    <!--                    <button type="button" class="btn-google"><img src="images/google.png" alt="Google Icon"> Sign In with Google</button>-->
                </form>
                <p class="signup">Don't have account? <a href="register">Sign Up</a></p>
            </div>
        </div>
    </div>
</body>
>>>>>>> fbf38e88c7423732c03f084d6c211a2ea03af5a2
</html>

