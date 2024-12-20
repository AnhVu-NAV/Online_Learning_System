<%-- 
    Document   : createAccount.jsp
    Created on : Sep 13, 2024, 3:49:29 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Create New Account</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="icon" type="image/png" href="img/icons/favicon.ico" />
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css" />
        <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css"/>
        <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css"/>
        <link rel="stylesheet" type="text/css" href="fonts/fontawesome-free-6.6.0-web/css/all.css"/>
        <link rel="stylesheet" type="text/css" href="css/util.css" />
        <link rel="stylesheet" type="text/css" href="css/main.css" />
        <style>
            .wrap-login100 {
                width: 500px;
                transition: all 0.5s ease;
                position: relative;
            }
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
                    <%--<c:choose>--%>
                        <c:if test="${requestScope.vetifyCode eq null}">
                            <div class="form-step active" id="step1">
                                <form class="login100-form validate-form" action="create" method="POST">
                                    <span class="login100-form-title"> Create new account </span>
                                    <div class="wrap-input100 validate-input" data-validate="Valid email is required: ex@abc.xyz">
                                        <input class="input100" type="text" placeholder="Email" name="email"/>
                                        <span class="focus-input100"></span>
                                        <span class="symbol-input100">
                                            <i class="fa fa-envelope" aria-hidden="true"></i>
                                        </span>
                                    </div>
                                    <p class="text-center" style="color: red">${requestScope.error}</p>
                                    <div class="container-login100-form-btn">
                                        <input type="submit" class="login100-form-btn" value="Check vertify code"/>
                                    </div>
                                    <p style="color: green" class="text-center">${requestScope.message}</p>
                                    <div class="text-center p-t-136">
                                        <a id="loginBtn" class="txt2" href="login.jsp">
                                            Have an account?
                                            <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
                                        </a>
                                    </div>
                                </form>
                            </div>
                        </c:if>

                        <c:if test="${requestScope.vetifyCode eq 'aa'}">
                            <div class="form-step active" id="step2">
                                <form action="create" method="POST">
                                    <span class="login100-form-title"> Verify Email </span>
                                    <div class="wrap-input100 validate-input" data-validate="Code is required">
                                        <input class="input100" type="password" placeholder="Verify Code" name="code"/>
                                        <span class="focus-input100"></span>
                                        <span class="symbol-input100">
                                            <i class="fa fa-long-arrow-alt-right" aria-hidden="true"></i>
                                        </span>
                                    </div>
                                    <p class="text-center" style="color: red">${requestScope.error}</p>
                                    <div class="container-login100-form-btn">
                                        <input type="submit" class="login100-form-btn" value="Check"/>
                                    </div>
                                </form>
                            </div>
                        </c:if>

                        <c:if test="${requestScope.vetifyCode eq 'bb'}">
                            <div class="form-step active" id="step3">
                                <form action="create" method="POST">
                                    <span class="login100-form-title"> Your information </span>
                                    <div class="wrap-input100 validate-input">
                                        <input type="text" class="input100" placeholder="Phone Number" name="phone_number">
                                        <span class="focus-input100"></span>
                                        <span class="symbol-input100">
                                            <i class="fa-solid fa-phone" aria-hidden="true"></i>
                                        </span>
                                    </div>
                                    <div class="wrap-input100 validate-input" data-validate="Password is required">
                                        <input class="input100" type="password" placeholder="Password" name="password"/>
                                        <span class="focus-input100"></span>
                                        <span class="symbol-input100">
                                            <i class="fa fa-lock" aria-hidden="true"></i>
                                        </span>
                                    </div>
                                    <div class="wrap-input100 validate-input" data-validate="Password is required">
                                        <input class="input100" type="password" placeholder="Rewrite Password" name="rewrite_password"/>
                                        <span class="focus-input100"></span>
                                        <span class="symbol-input100">
                                            <i class="fa fa-lock" aria-hidden="true"></i>
                                        </span>
                                    </div>
                                    <p class="text-center" style="color: red">${requestScope.error}</p>
                                    <div class="container-login100-form-btn">
                                        <input type="submit" class="login100-form-btn" value="Create your account"/>
                                    </div>
                                </form>
                            </div>
                        </c:if>

                    <%--</c:choose>--%>
                </div>
            </div>
        </div>

        <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
        <script src="vendor/bootstrap/js/popper.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
        <script src="vendor/select2/select2.min.js"></script>
        <script src="vendor/tilt/tilt.jquery.min.js"></script>
        <script>
            $(".js-tilt").tilt({
                scale: 1.1,
            });
        </script>
        <script src="js/main.js"></script>
    </body>
</html>

