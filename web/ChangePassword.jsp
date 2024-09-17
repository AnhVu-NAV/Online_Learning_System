<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Traveland</title>
        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f8f9fa;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            .verify-otp {
                background-color: #ffffff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .form-title {
                margin-bottom: 20px;
            }
            .form-group {
                margin-bottom: 15px;
            }
            .form-submit {
                width: 100%;
                background-color: #86B817;
                color: #ffffff;
                border: none;
                padding: 10px;
                border-radius: 5px;
                cursor: pointer;
            }
            .form-submit:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="main">
            <section class="verify-otp">
                <div class="container">
                    <div class="verify-otp-content">
                        <div class="verify-otp-form">
                            <h2 class="form-title text-center">Change Password</h2>
                            <form action="changePass" method="POST" class="register-form" id="verify-form">
                                <c:choose>
                                    <c:when test="${not empty error}">
                                        <div class="alert alert-danger">${error}</div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="alert alert-danger d-none"></div>
                                    </c:otherwise>
                                </c:choose>
                                <div class="form-group">
                                    <label for="old_pass">Current Password</label>
                                    <input type="text" name="old_pass" id="old_pass" class="form-control" placeholder="Enter Current Password" required/>
                                </div>
                                <div class="form-group">
                                    <label for="new_pass">New Password</label>
                                    <input type="text" name="new_pass" id="new_pass" class="form-control" placeholder="Enter New Password" required/>
                                </div>
                                <div class="form-group">
                                    <label for="c_new_pass">Confirm New Password</label>
                                    <input type="text" name="c_new_pass" id="c_new_pass" class="form-control" placeholder="Confirm New Password" required/>
                                </div>
                                <div class="form-group form-button">
                                    <input type="submit" name="verify" id="verify" class="form-submit" value="Save"/>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
