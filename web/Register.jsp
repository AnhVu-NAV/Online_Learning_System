<%-- 
    Document   : Register
    Created on : Aug 19, 2024, 10:05:14 AM
    Author     : HuyLVN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Register</title>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/styleLogin.css"/>
    </head>
    <body>
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

                    <!-- Display success or error messages -->
                    <c:if test="${not empty registerSuccess}">
                        <p style="color: green; font-size: 1.5rem; text-align: center">${registerSuccess}</p>
                    </c:if>
                    
                    <c:if test="${not empty duplicateEmail}">
                        <p style="color: red; font-size: 1.25rem; text-align: center">${duplicateEmail}</p>
                    </c:if>
                    
                    <c:if test="${not empty duplicatePhone}">
                        <p style="color: red; font-size: 1.25rem; text-align: center">${duplicatePhone}</p>
                    </c:if>
                    
                    <c:if test="${not empty errorMessage}">
                        <p style="color: red; font-size: 1.25rem; text-align: center">${errorMessage}</p>
                    </c:if>

                    <h2>Register</h2>
                    <form action="register" method="POST">
                        <div class="input-group">
                            <input type="email" name="primaryEmail" placeholder="Enter Primary Email" class="input-field" required>
                        </div>

                        <div class="input-group">
                            <input type="password" name="password" placeholder="Enter Password" class="input-field" required>
                        </div>

                        <div class="input-group">
                            <input type="text" name="firstName" placeholder="Enter First Name" class="input-field" required>
                        </div>
                        
                        <div class="input-group">
                            <input type="text" name="lastName" placeholder="Enter Last Name" class="input-field" required>
                        </div>

                        <div class="input-group">
                            <input type="date" name="dob" placeholder="Enter Date of Birth" class="input-field" required>
                        </div>

                        <div class="input-group">
                            <select name="gender" class="input-field" required>
                                <option value="" disabled selected>Select Gender</option>
                                <option value="1">Male</option>
                                <option value="2">Female</option>
                                <option value="3">Other</option>
                            </select>
                        </div>

                        <div class="input-group">
                            <input type="tel" name="firstPhone" placeholder="Enter Primary Phone Number" class="input-field" required>
                        </div>

                        <div class="input-group">
                            <input type="text" name="address" placeholder="Enter Address" class="input-field" required>
                        </div>

                        <button type="submit" class="btn-login">Register</button>
                        
                        <!-- Back to Home Button -->
                        <a href="./customer" class="btn-back-home">Back to Home</a>
                    </form>

                    <p class="signup">Already have an account? <a href='<c:url value="/home?action=login"/>'>Login Now</a></p>
                </div>
            </div>
        </div>
    </body>
</html>
