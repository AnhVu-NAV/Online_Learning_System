<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>QR Payment</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                line-height: 1.6;
                margin: 0;
                padding: 0;
                text-align: center;
                background-color: #f7f7f7;
            }
            .container {
                margin: 20px auto;
                padding: 20px;
                background: #fff;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                max-width: 500px;
                border-radius: 10px;
            }
            h1 {
                color: #333;
            }
            .course-details {
                margin-bottom: 20px;
                text-align: center;
            }
            .course-details h2 {
                margin-bottom: 10px;
                color: #007bff;
            }
            img {
                width: 400px;
                height: auto;
                margin: 20px 0;
                border-radius: 10px;
            }
            button {
                background-color: #28a745;
                color: #fff;
                border: none;
                padding: 10px 20px;
                font-size: 16px;
                cursor: pointer;
                border-radius: 5px;
            }
            button:hover {
                background-color: #218838;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Payment via VietQR</h1>
            <p>Please scan the QR code below to complete your payment.</p>

            <!-- Thông tin khóa h?c -->
            <div class="course-details">
                <h2>Bill Overview</h2>
                <img src="${sessionScope.paymentThumbnail}" alt="Course Thumbnail">
                <p><strong>Course Name:</strong> ${sessionScope.paymentCourseTitle}</p>
                <p><strong>Price Package:</strong> ${sessionScope.paymentPricePackageTitle}</p>
                <p><strong>Price:</strong> $<fmt:formatNumber value="${sessionScope.paymentPrice}" /></p>
            </div>

            <!-- Mã QR -->
            <div>
                <img src="GenerateVietQR?amount=${sessionScope.paymentPrice}&description=${sessionScope.userFullName} thanh toán cho khóa ${sessionScope.paymentCourseTitle}" alt="VietQR Payment" style="display: none" />
                <img src="${sessionScope.qrUrl}" alt="VietQR Payment" />
            </div>

            <!-- Nút xác nh?n thanh toán -->
            <form action="VerifyPayment" method="POST">
                <input type="hidden" name="courseId" value="${sessionScope.paymentCourseId}" />
                <input type="hidden" name="pricePackageId" value="${sessionScope.paymentPricePackageId}" />
                <button type="submit">Verify Payment</button>
            </form>
        </div>
    </body>
</html>
