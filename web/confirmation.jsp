<%-- 
    Document   : confirmation
    Created on : Oct 10, 2024, 1:25:56 AM
    Author     : AnhVuNAV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Confirmation</title>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/confirmation.css">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background-color: #f4f7fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .confirmation-container {
            background-color: #fff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            text-align: center;
            max-width: 500px;
            width: 100%;
        }
        .confirmation-container h1 {
            color: #4CAF50;
            font-size: 28px;
            margin-bottom: 20px;
        }
        .confirmation-container p {
            font-size: 18px;
            color: #333;
            margin-bottom: 20px;
        }
        .confirmation-container .fa-check-circle {
            font-size: 48px;
            color: #4CAF50;
            margin-bottom: 20px;
        }
        .confirmation-container a {
            text-decoration: none;
            color: #fff;
            background-color: #4CAF50;
            padding: 10px 20px;
            border-radius: 5px;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }
        .confirmation-container a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

    <div class="confirmation-container">
        <i class="fas fa-check-circle"></i>
        <h1>Registration Successful!</h1>
        <p>Thank you for registering for the course. We have received your information and will get in touch with you soon.</p>

        <p>We hope you enjoy your learning journey with us!</p>

        <a href="home.jsp">Go to Home</a>
    </div>

</body>
</html>
