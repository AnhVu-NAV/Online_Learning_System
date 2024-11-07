<%-- 
    Document   : ViewRegistrationDetail
    Created on : Oct 13, 2024, 9:28:04 PM
    Author     : 84941
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Details</title>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <style>
            .wrapper {
                display: flex;
                height: 150vh;

            }

            #ViewRegistrationDetail {
                flex-grow: 1;
                display: flex;
                justify-content: center;
                background-color: #eee;
                padding: 20px;
            }
            .form-container {
                background: white;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 100%;
                max-width: 500px;
            }
        </style>
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="/component/AdminDashboardSidebar.jsp"></jsp:include>
            <c:set var="personalCourse" value="${requestScope.personalCourse}"></c:set>
                <section style="background-color: #eee;" id="ViewRegistrationDetail">
                    <div class="container py-5">                     
                        <div class="row">
                            <div class="col">
                                <nav aria-label="breadcrumb" class="bg-body-tertiary rounded-3 p-3 mb-4">
                                    <ol class="breadcrumb mb-0">
                                        <li class="breadcrumb-item"><a href="SaleRegistrationDashboardController?service=viewAllRegistration">Registration List</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">Registration Details</li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                        <section id="ViewRegistrationDetail">
                            <div class="form-container">
                                <form action="${pageContext.request.contextPath}/SaleRegistrationDashboardController" method="post">
                                <div class="form-group mb-3">
                                    <label for="settingValue">ID</label>
                                    <input type="text" class="form-control" readonly="true" value="${personalCourse.getId()}" name="id">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="customerName">Customer Name</label>
                                    <c:forEach items="${requestScope.userVector}" var="user">
                                        <c:if test="${user.getId()==personalCourse.getCustomerId()}">
                                            <input type="text" class="form-control" readonly="true" value="${user.getFirstName()} ${user.getLastName()}" name="customerName">
                                        </c:if>
                                    </c:forEach>
                                </div>
                                <div class="form-group mb-3">
                                    <label for="customerEmail">Customer Email</label>
                                    <c:forEach items="${requestScope.userVector}" var="user">
                                        <c:if test="${user.getId()==personalCourse.getCustomerId()}">
                                            <input type="text" class="form-control" readonly="true" value="${user.getPrimaryEmail()}" name="customerEmail">
                                        </c:if>
                                    </c:forEach>
                                </div>
                                <div class="form-group mb-3">
                                    <label for="enrollDate">Enroll date</label>
                                    <input type="text" class="form-control" readonly="true" value="${personalCourse.getEnrollDate()}" name="enrollDate">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="expireDate">Expire date</label>
                                    <input type="text" class="form-control" readonly="true" value="${personalCourse.getExpireDate()}" name="expireDate">
                                </div>                                
                                <div class="form-group mb-3">
                                    <label for="status">Status</label>
                                    <input type="text" class="form-control" readonly="true" value="${personalCourse.getStatus()}" name="status">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="pricePackageId">Price Package Id</label>
                                    <input type="text" class="form-control" readonly="true" value="${personalCourse.getPricePackageId()}" name="pricePackageId">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="pricePackageTitle">Price Package Title</label>
                                    <c:forEach items="${requestScope.pricePackageVector}" var="pricePackage">
                                        <c:if test="${pricePackage.getId()==personalCourse.getPricePackageId()}">
                                            <input type="text" class="form-control" readonly="true" value="${pricePackage.getTitle()}" name="pricePackageTitle">
                                        </c:if>
                                    </c:forEach>
                                </div>
                                <div class="form-group mb-3">
                                    <label for="progress">Progress</label>
                                    <input type="text" class="form-control" readonly="true" value="${personalCourse.getProgress()}%" name="progress">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="settingValue">Note</label>
                                    <input type="text" class="form-control" name="settingDescription" value="${setting.getDescription()}">
                                </div>
                                <button type="submit" class="btn btn-primary w-100">Update</button>
                                <input type="hidden" name="service" value="updateSetting">
                            </form>
                        </div>
                    </section>
                </div>
            </section>



        </div>
    </body>
</html>
