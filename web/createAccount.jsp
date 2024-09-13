<%-- 
    Document   : createAccount.jsp
    Created on : Sep 13, 2024, 3:49:29 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
      }
    </style>
  </head>
  <body>
    <div class="limiter">
      <div class="container-login100">
        <div class="wrap-login100">

            <form class="login100-form validate-form" action="create" method="POST">
              
            <span class="login100-form-title"> Create new password </span>

            <div class="wrap-input100 validate-input" data-validate="Valid email is required: ex@abc.xyz">
              <input class="input100" type="text" name="email" placeholder="Email"/>
              <span class="focus-input100"></span>
              <span class="symbol-input100">
                <i class="fa fa-envelope" aria-hidden="true"></i>
              </span>
            </div>

            <div class="wrap-input100 validate-input">
                <input type="text" class="input100" placeholder="Name" name="name">
                <span class="focus-input100"></span>
                <span class="symbol-input100">
                  <i class="fa-solid fa-user" aria-hidden="true"></i>
                </span>
            </div>
            

            <div class="wrap-input100 validate-input">
                <input type="text" class="input100" placeholder="Date of birth" name="dob">
                <span class="focus-input100"></span>
                <span class="symbol-input100">
                  <i class="fa-solid fa-cake-candles" aria-hidden="true"></i>
                </span>
            </div>
            

            <div class="wrap-input100 validate-input">
                <input type="text" class="input100" placeholder="Address" name="address">
                <span class="focus-input100"></span>
                <span class="symbol-input100">
                  <i class="fa-solid fa-address-book" aria-hidden="true"></i>
                </span>
            </div>
            

            <div class="wrap-input100 validate-input">
                <input type="text" class="input100" placeholder="Phone Number" name="phone_number">
                <span class="focus-input100"></span>
                <span class="symbol-input100">
                  <i class="fa-solid fa-phone" aria-hidden="true"></i>
                </span>
            </div>

            <div class="wrap-input100 validate-input" data-validate="Password is required">
              <input class="input100" type="password" name="password" placeholder="Password"/>
              <span class="focus-input100"></span>
              <span class="symbol-input100">
                <i class="fa fa-lock" aria-hidden="true"></i>
              </span>
            </div>

            <div class="wrap-input100 validate-input" data-validate="Password is required">
              <input class="input100" type="password" name="rewrite_password" placeholder="Rewrite Password"/>
              <span class="focus-input100"></span>
              <span class="symbol-input100">
                <i class="fa fa-lock" aria-hidden="true"></i>
              </span>
            </div>

            <div class="container-login100-form-btn">
              <button class="login100-form-btn">Create</button>
            </div>

            <div class="text-center p-t-136">
              <a id="loginBtn" class="txt2" href="login.jsp">
                Have an account?
                <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
              </a>
            </div>
            
          </form>            
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

