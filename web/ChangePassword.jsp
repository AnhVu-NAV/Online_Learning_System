<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Integer roleId = (session != null) ? (Integer) session.getAttribute("roleId") : null;

    // Nếu roleId trống hoặc khác 3, chuyển hướng về trang chủ
    if (roleId == null) {
        response.sendRedirect(request.getContextPath() + "/home");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>E-Learning</title>
        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f3e8ff; /* Màu nền tím nhạt */
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            .verify-otp {
                background-color: #ffffff;
                padding: 40px;
                border-radius: 15px;
                box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
                max-width: 400px; /* Đặt chiều rộng tối đa */
                width: 100%;
            }
            .form-title {
                margin-bottom: 30px;
                color: #5d3b91; /* Màu tím cho tiêu đề */
            }
            .form-group {
                margin-bottom: 20px;
            }
            .form-control {
                border: 2px solid #d3bdf0; /* Màu viền tím nhạt */
                padding: 10px;
                border-radius: 5px;
            }
            .form-submit {
                width: 100%;
                background-color: #a166ff; /* Màu tím nhạt cho nút */
                color: #ffffff;
                border: none;
                padding: 10px;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }
            .form-submit:hover {
                background-color: #8241cc; /* Màu tím đậm hơn khi hover */
            }
            .alert {
                text-align: center;
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
                            <form action="changePassword" method="POST" class="register-form" id="verify-form">
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
