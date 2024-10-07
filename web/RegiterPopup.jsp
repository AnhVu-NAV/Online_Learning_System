<%-- 
    Document   : RegiterPopup
    Created on : Oct 6, 2024, 10:56:15 PM
    Author     : AnhVuNAV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Popup Background -->
<div class="popup-background" id="registerPopup" style="display:none;">
    <div class="popup-content">
        <button class="close-btn" onclick="closeRegisterPopup()">&times;</button>
        <h2>Register for Course</h2>
        <form class="register-form">
            <label for="pricePackage">Choose a Price Package:</label>
            <select id="pricePackage">
                <option value="standard">Standard - $150</option>
                <option value="premium">Premium - $200</option>
            </select>

            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" placeholder="Enter your full name">

            <div id="emailSection">
                <label for="email1">Email 1:</label>
                <input type="email" id="email1" placeholder="Enter your email">
                <label for="email2" style="display: none;">Email 2:</label>
                <input type="email" id="email2" placeholder="Enter your email" style="display: none;">
                <button type="button" class="add-btn" onclick="addEmail()">+ Add another email</button>
            </div>

            <label for="contactMethod">Preferred Contact Method:</label>
            <select id="contactMethod">
                <option value="email1">Email 1</option>
                <option value="email2">Email 2</option>
            </select>

            <label for="gender">Gender:</label>
            <select id="gender">
                <option value="male">Male</option>
                <option value="female">Female</option>
                <option value="other">Other</option>
            </select>

            <button type="button" class="register-btn primary" onclick="submitRegistration()">Register Now</button>
        </form>
        <div id="message" class="message"></div>
    </div>
</div>

