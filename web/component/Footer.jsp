<%-- 
    Document   : Footer
    Created on : Sep 11, 2024, 8:32:43 PM
    Author     : AnhVuNAV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./style.css">
    <style>
        .footer {
    background-color: #141624;
    color: #BABABF;
    padding: 64px 0;
    text-align: left;
}

.footer-container {
    display: flex;
    justify-content: space-between;
    padding: 0 352px;
}

.footer-section {
    width: 25%;
}

.footer-section h3 {
    color: white;
    font-size: 18px;
    margin-bottom: 12px;
}

.footer-section p,
.footer-section ul li {
    font-size: 16px;
    margin-bottom: 8px;
}

.footer-section ul {
    list-style: none;
    padding: 0;
}

.footer-section ul li a {
    color: #BABABF;
    text-decoration: none;
}

.footer-newsletter input {
    padding: 12px;
    border: 1px solid #3B3C4A;
    background-color: #181A2A;
    color: #97989F;
    border-radius: 6px;
    margin-bottom: 10px;
    width: 100%;
}

.footer-newsletter button {
    padding: 12px 20px;
    background-color: #4B6BFB;
    color: white;
    border: none;
    border-radius: 6px;
    cursor: pointer;
}

.footer-bottom {
    display: flex;
    justify-content: space-between;
    padding: 32px 352px;
    border-top: 1px solid #242535;
    color: #BABABF;
}

.footer-bottom ul {
    display: flex;
    gap: 16px;
    list-style: none;
    padding: 0;
}

.footer-bottom ul li a {
    color: #BABABF;
    text-decoration: none;
}

    </style>
</head>

<body>
    <footer class="footer">
        <div class="footer-container">
            <div class="footer-section">
                <h3>About</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                <p>Email: info@jstemplate.net</p>
                <p>Phone: 880 123 456 789</p>
            </div>
            <div class="footer-section">
                <h3>Quick Links</h3>
                <ul>
                    <li><a href="#">Home</a></li>
                    <li><a href="#">About</a></li>
                    <li><a href="#">Blog</a></li>
                    <li><a href="#">Archived</a></li>
                    <li><a href="#">Author</a></li>
                    <li><a href="#">Contact</a></li>
                </ul>
            </div>
            <div class="footer-section">
                <h3>Categories</h3>
                <ul>
                    <li><a href="#">Lifestyle</a></li>
                    <li><a href="#">Technology</a></li>
                    <li><a href="#">Travel</a></li>
                    <li><a href="#">Business</a></li>
                    <li><a href="#">Economy</a></li>
                    <li><a href="#">Sports</a></li>
                </ul>
            </div>
            <div class="footer-newsletter">
                <h3>Weekly Newsletter</h3>
                <p>Get blog articles and offers via email</p>
                <input type="email" placeholder="Your Email" />
                <button>Subscribe</button>
            </div>
        </div>
        <div class="footer-bottom">
            <p>© JS Template 2023. All Rights Reserved.</p>
            <ul>
                <li><a href="#">Terms of Use</a></li>
                <li><a href="#">Privacy Policy</a></li>
                <li><a href="#">Cookie Policy</a></li>
            </ul>
        </div>
    </footer>
</body>

</html>
