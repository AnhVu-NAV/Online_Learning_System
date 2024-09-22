<%-- 
    Document   : ViewUserDetails
    Created on : Sep 17, 2024, 8:09:20 AM
    Author     : 84941
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,model.Account" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Details</title>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <style>
            .wrapper {
                display: flex;
                height: 100vh;
            }

            #ViewUserDetail {
                flex-grow: 1;
                overflow-y: auto;
            }
        </style>

    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="/component/DashboardSidebar.jsp"></jsp:include>
            <c:set var="account" value="${requestScope.account}"></c:set>
                <section style="background-color: #eee;" id="ViewUserDetail" >
                    <div class="container py-5">
                        <div class="row">
                            <div class="col">
                                <nav aria-label="breadcrumb" class="bg-body-tertiary rounded-3 p-3 mb-4">
                                    <ol class="breadcrumb mb-0">
                                        <li class="breadcrumb-item"><a href="DashboardController?service=viewAllAccount">User List</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">User Details</li>
                                    </ol>
                                </nav>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-4">
                                <div class="card mb-4">
                                    <div class="card-body text-center">
                                        <img src="${account.getImage_url()}" alt="avatar"
                                         class="rounded-circle img-fluid" style="width: 150px;">
                                    <h5 class="my-3">${account.getFirst_name()} ${account.getLast_name()}</h5>
                                    <!-- Update account start -->                                                                                                                        
                                    <select class="form-select w-100" onchange="window.location.href=this.value">  
                                            <c:forEach items="${requestScope.setting}" var="setting">
                                                <option value="${pageContext.request.contextPath}/DashboardController?service=updateAccount&accountId=${account.getId()}&roleId=${setting.getId()}" ${account.getRole_id()==setting.getId()?"selected":""} >${setting.getValue()}</option>
                                            </c:forEach>
                                        </select>
                                        <br/>
                                        <div class="d-flex justify-content-center mb-2">
                                            <button  type="button" data-mdb-button-init data-mdb-ripple-init class="btn ${account.getStatus() == 1 ? 'btn-success' : 'btn-outline-success'} mx-2" onclick="window.location.href='${pageContext.request.contextPath}/DashboardController?service=updateAccount&accountId=${account.getId()}&status=1';">Activated</button>
                                            <button  type="button" data-mdb-button-init data-mdb-ripple-init class="btn ${account.getStatus() == 0 ? 'btn-danger' : 'btn-outline-danger'} mx-2" onclick="window.location.href='${pageContext.request.contextPath}/DashboardController?service=updateAccount&accountId=${account.getId()}&status=0';">Deactivated</button>
                                            <button  type="button" data-mdb-button-init data-mdb-ripple-init class="btn ${account.getStatus() == 2 ? 'btn-warning' : 'btn-outline-warning'} mx-2" onclick="window.location.href='${pageContext.request.contextPath}/DashboardController?service=updateAccount&accountId=${account.getId()}&status=2';">Default</button>
                                        </div>                                                                           
                                    <!-- Update account end -->
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-8">
                            <div class="card mb-4">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Full Name</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <p class="text-muted mb-0">${account.getFirst_name()} ${account.getLast_name()}</p>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Gender</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <p class="text-muted mb-0">
                                                <c:choose>
                                                    <c:when test="${account.isGender() == false}">
                                                        Male
                                                    </c:when>
                                                    <c:when test="${account.isGender() == true}">
                                                        Female
                                                    </c:when>                                                       
                                                </c:choose>
                                            </p>                                         
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Email</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <p class="text-muted mb-0">${account.getEmail()}</p>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Phone</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <p class="text-muted mb-0">${account.getPhone()}</p>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Date of birth</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <p class="text-muted mb-0">${account.getDob()}</p>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Address</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <p class="text-muted mb-0">${account.getAddress()}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </section>
        </div>

    </body>
</html>
