<%-- 
    Document   : Header
    Created on : Sep 11, 2024, 8:33:04 PM
    Author     : HuyLVN
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!--<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/globals.css" />

    </head>

    <body>
        <div class="web">
            <div class="div">
                <div class="overlap">
                    <img class="man-working-night" src="${pageContext.request.contextPath}/img/man-working-night-1.png" alt="home-img" />
                    <div class="group">
                        <p class="text-wrapper">Save for the right education</p>
                        <div class="frame"><div class="text-wrapper-2">Let’s Connect</div></div>
                        <p class="p">
                            Get courses from ₫249,000 and create a self-paced learning lifestyle that works for you.
                            Sale ends Sept 13.
                        </p>
                    </div>
                </div>
                <div class="overlap-group-wrapper">
                    <div class="overlap-group">
                        <div class="ellipse"></div>
                        <p class="learnik"><span class="span">A</span> <span class="text-wrapper-3">learnik</span></p>
                    </div>
                </div>
                <div>
                    <input type="search" placeholder="Search..." class="search-bar">
                </div>
                <ul class="navbar ml-auto">
                    <li class="nav-item active">
                        <a class="text-wrapper-4 nav-link" href="#">OUR BUSINESS</a>
                    </li>
                    <li class="nav-item active">
                        <a class="text-wrapper-5 nav-link" href="#">TEACH</a>
                    </li>

<c:choose>
    <c:when test="${not empty sessionScope.user}">
        <li class="nav-item active">
            <a class="text-wrapper-6 nav-link" style="color:green">Hello, ${sessionScope.fullname}</a>
        </li>
        <li class="nav-item active">
            <a class="text-wrapper-7 nav-link" href='<c:url value="/home?action=logout"/>'>LOG OUT</a>
        </li>
    </c:when>
    <c:otherwise>
        <li class="nav-item active">
            <a class="text-wrapper-6 nav-link" href="#">SIGN UP</a>
        </li>
        <li class="nav-item active">
            <a class="text-wrapper-7 nav-link" href='<c:url value="/login?action=login"/>'>LOG IN</a>
        </li>
    </c:otherwise>
</c:choose>
</ul>
</div>
</div>
</body>

</html>-->

<%-- 
    Document   : Header
    Created on : Sep 11, 2024, 8:33:04 PM
    Author     : HuyLVN
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/globals.css" />
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>
        <div class="web">
            <div class="div">
                <div class="overlap">
                    <img class="man-working-night" src="${pageContext.request.contextPath}/img/homethumbnail.webp" alt="home-img" />
                </div>
                <div class="overlap-group-wrapper">
                    <div class="overlap-group">
                        <!-- Learnik Logo directing to Home -->
                        <a href="<c:url value='/home'/>" class="brand-link">
                            <div class="ellipse"></div>
                            <p class="learnik"><span class="span">A</span> <span class="text-wrapper-3">learnik</span></p>
                        </a>
                    </div>
                </div>

                <!-- Navigation Menu -->
                <ul class="navbar ml-auto">
                    <!-- Default Links visible to all users -->
                    <li class="nav-item active">
                        <a class="text-wrapper-4 nav-link" href="${pageContext.request.contextPath}/CourseList">COURSES</a>
                    </li>
                    <li class="nav-item active">
                        <a class="text-wrapper-5 nav-link" href="${pageContext.request.contextPath}/ChapterDisplayController">My Course</a>
                    </li>
                    <!-- Dynamic User Menu based on login status -->
                    <c:choose>
                        <c:when test="${not empty sessionScope.user}">
                            <!-- Profile dropdown for logged-in users -->
                            <li class="nav-item dropdown active">
                                <a href="#" class="nav-link dropdown-toggle" id="profileDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <img src="img/${sessionScope.user.imageUrl}" alt="Profile" class="profile-img">
                                    ${sessionScope.user.firstName}
                                </a>
                                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="profileDropdown">
                                    <a class="dropdown-item" href="<c:url value='/UserProfile?userId=${sessionScope.user.id}' />">Profile</a>
                                    <a class="dropdown-item" href='<c:url value="/home?action=logout"/>'>Log Out</a>
                                </div>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <!-- Buttons for users who are not logged in -->
                            <li class="nav-item active">
                                <a class="text-wrapper-6 nav-link" href="/signup">SIGN UP</a>
                            </li>
                            <li class="nav-item active">
                                <a class="text-wrapper-7 nav-link" href='<c:url value="/login?action=login"/>'>LOG IN</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <script>
            $('#profileDropdown').click(function () {
                $(this).next('.dropdown-menu').toggleClass('show');
            });
        </script>
    </body>

</html>
