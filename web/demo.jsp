<%-- 
    Document   : demo
    Created on : Sep 21, 2024, 5:00:20 PM
    Author     : ADMIN
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<<<<<<< HEAD:web/demo.jsp
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
=======
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
>>>>>>> fbf38e88c7423732c03f084d6c211a2ea03af5a2:web/component/Header.jsp
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
        <link rel="stylesheet" href="../css/style.css" />
        <link rel="stylesheet" href="../css/globals.css" />

    </head>

    <body>
<<<<<<< HEAD:web/demo.jsp
        <jsp:include page="slider"></jsp:include>
        
=======
        <div class="web">
            <div class="div">
                <div class="overlap">
                    <img class="man-working-night" src="../img/man-working-night-1.png" />
                    <!--<img class="man-working-night" src="../../public/images/man-working-night-1.png" />-->
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
                                <a class="text-wrapper-6 nav-link">Hello, ${sessionScope.fullname}</a>
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
>>>>>>> fbf38e88c7423732c03f084d6c211a2ea03af5a2:web/component/Header.jsp
    </body>

</html>