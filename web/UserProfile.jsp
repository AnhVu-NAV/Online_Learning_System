<%-- 
    Document   : UserProfile
    Created on : Sep 23, 2024, 11:41:05 AM
    Author     : AnhVuNAV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Profile</title>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="css/userProfile.css">
    </head>
    <body>
        <div class="ccontainer">
            <!-- Navigation -->
            <div class="nav-buttons">
                <div class="top-page">
                    <div>
                   <button class="btn btn-back" onclick="window.location.href='${pageContext.request.contextPath}/home'">← Home</button>
                    </div>
                    <div>
                    <h2>User Profile</h2>
                    </div>
                </div>

            </div>
            <div class="profile-container">

                <!-- Profile Card - Left Side -->
                <div class="profile-card">
                    <img src="img/${user.imageUrl}" alt="Profile Image" class="profile-image">
                    <h1 class="profile-name">${user.firstName} ${user.lastName}</h1>
                    <div class="profile-contact">
                        <p><strong>Email:</strong> ${user.primaryEmail}</p>
                        <p><strong>Phone:</strong> ${user.firstPhone}</p>
                        <p><strong>Gender:</strong> 
                        <c:choose>
                            <c:when test="${user.gender == 1}">Male</c:when>
                            <c:when test="${user.gender == 2}">Female</c:when>
                            <c:otherwise>Other</c:otherwise>
                        </c:choose>
                        </p>
                        <p><strong>Date of Birth:</strong> ${user.dob}</p>
                    </div>
                    <a href="UpdateUserProfile?userId=${user.id}">
                        <button class="btn btn-update">Update Profile</button>
                    </a>
                </div>

                <!-- Details Cards - Right Side -->
                <div class="details-container">

                    <!-- Contact Details Card -->
                    <div class="details-card">
                        <div class="card-header">
                            <h2 class="card-title">Contact Details</h2>
                        </div>
                        <div class="details-list">
                            <div class="details-item">
                                <div class="item-label">
                                    <i class='bx bx-envelope'></i>
                                    <span>Primary Email</span>
                                </div>
                                <span class="item-status status-active">${user.primaryEmail}</span>
                            </div>
                            <c:if test="${not empty user.secondaryEmail}">
                                <div class="details-item">
                                    <div class="item-label">
                                        <i class='bx bx-envelope'></i>
                                        <span>Secondary Email</span>
                                    </div>
                                    <span class="item-status">${user.secondaryEmail}</span>
                                </div>
                            </c:if>
                            <div class="details-item">
                                <div class="item-label">
                                    <i class='bx bx-phone'></i>
                                    <span>Primary Phone</span>
                                </div>
                                <span class="item-status status-active">${user.firstPhone}</span>
                            </div>
                            <c:if test="${not empty user.secondPhone}">
                                <div class="details-item">
                                    <div class="item-label">
                                        <i class='bx bx-phone'></i>
                                        <span>Secondary Phone</span>
                                    </div>
                                    <span class="item-status">${user.secondPhone}</span>
                                </div>
                            </c:if>
                            <div class="details-item">
                                <div class="item-label">
                                    <i class='bx bx-home'></i>
                                    <span>Address</span>
                                </div>
                                <span class="item-status">${user.address}</span>
                            </div>
                            <div class="details-item">
                                <div class="item-label">
                                    <i class='bx bx-chat'></i>
                                    <span>Preferred Contact</span>
                                </div>
                                <span class="item-status status-active">${user.preferContact}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>