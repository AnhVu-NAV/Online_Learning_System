<%-- 
    Document   : login
    Created on : Sep 20, 2024, 9:59:58 PM
    Author     : mocun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="stylesheet" href="css/styleLogin.css"/>
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
                <h2>Hello,Again</h2>
                <p>We are happy to have you back.</p>
                <form action="<c:url value="/login"/>" id="formLogin" method="POST">
                    <input type="text" name="username" id="username" placeholder="Email address" class="input-field">
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
</html>

