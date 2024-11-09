<%-- 
    Document   : PaymentFailed
    Created on : Nov 9, 2024, 5:13:33 PM
    Author     : AnhVuNAV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Payment Failed</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f7f7f7;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            .container {
                text-align: center;
                background: #fff;
                padding: 20px 40px;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            h1 {
                color: #e74c3c;
                font-size: 2.5rem;
            }
            p {
                color: #555;
                font-size: 1.1rem;
                margin: 10px 0;
            }
            .details {
                margin: 20px 0;
                text-align: left;
                background-color: #fdfdfd;
                padding: 15px;
                border: 1px solid #f0f0f0;
                border-radius: 8px;
            }
            a {
                display: inline-block;
                margin-top: 20px;
                padding: 10px 20px;
                background-color: #3498db;
                color: #fff;
                text-decoration: none;
                border-radius: 5px;
            }
            a:hover {
                background-color: #2980b9;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Payment Failed</h1>
            <p>Unfortunately, your payment could not be processed.</p>
            
            <!-- Hiển thị thông tin lỗi nếu có -->
            <div class="details">
                <p><strong>Course Name:</strong> ${sessionScope.paymentCourseTitle}</p>
                <p><strong>Price Package:</strong> ${sessionScope.paymentPricePackageTitle}</p>
                <p><strong>Amount:</strong> $<fmt:formatNumber value="${sessionScope.paymentPrice}" /></p>
                <p><strong>Reason:</strong> Payment verification failed.</p>
            </div>

            <a href="RegisterCourseController?courseId=${sessionScope.paymentCourseId}">Try Again</a>
        </div>
    </body>
</html>
