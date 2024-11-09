<%-- 
    Document   : PaymentSuccess
    Created on : Nov 9, 2024, 3:47:38 PM
    Author     : AnhVuNAV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Payment Successful</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                line-height: 1.6;
                margin: 0;
                padding: 0;
                background-color: #f9f9f9;
                color: #333;
            }
            .container {
                max-width: 600px;
                margin: 50px auto;
                padding: 20px;
                background: #fff;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                border-radius: 10px;
                text-align: center;
            }
            h1 {
                color: #28a745;
            }
            .course-details {
                text-align: left;
                margin: 20px 0;
            }
            .course-details h2 {
                color: #007bff;
            }
            .thumbnail {
                width: 100%;
                max-height: 200px;
                object-fit: cover;
                border-radius: 5px;
                margin-bottom: 20px;
            }
            .btn {
                display: inline-block;
                margin-top: 20px;
                padding: 10px 20px;
                background-color: #007bff;
                color: #fff;
                text-decoration: none;
                border-radius: 5px;
                transition: background-color 0.3s;
            }
            .btn:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Payment Successful!</h1>
            <p>Thank you, <strong>${sessionScope.user.firstName}</strong>, for your payment.</p>
            <p>You have successfully enrolled in the following course:</p>
            
            <!-- Thông tin khóa học -->
            <div class="course-details">
                <img src="${sessionScope.paymentThumbnail}" alt="Course Thumbnail" class="thumbnail">
                <h2>${sessionScope.courseDetails.title}</h2>
                <p><strong>Price Package:</strong> ${sessionScope.paymentPricePackageTitle}</p>
                <p><strong>Price Paid:</strong> $<fmt:formatNumber value="${sessionScope.paymentPrice}" /></p>
            </div>
            
            <!-- Nút điều hướng -->
            <a href="CourseDetail?courseId=${sessionScope.courseDetails.id}" class="btn">Go to Course</a>
        </div>
    </body>
</html>
