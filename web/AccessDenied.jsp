<%-- 
    Document   : AccessDenied
    Created on : Nov 10, 2024, 2:20:06 AM
    Author     : AnhVuNAV
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Access Denied</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8d7da;
            color: #721c24;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }
        .container {
            text-align: center;
            background-color: #fff;
            padding: 30px;
            border: 1px solid #f5c6cb;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 400px;
        }
        h1 {
            margin-bottom: 10px;
            font-size: 24px;
        }
        p {
            margin-bottom: 20px;
        }
        .home-button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }
        .home-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Access Denied</h1>
        <p>You do not have permission to access this page.</p>
        <a href="home" class="home-button">Return to Home</a>
    </div>
</body>
</html>
