<%-- 
    Document   : userlist
    Created on : Sep 15, 2024, 4:03:22 PM
    Author     : 84941
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,model.Account" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Users List</title>
        <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ViewUserList.css">
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="/component/DashboardSidebar.jsp"></jsp:include>
                <div class="main">
                    <main class="content px-3 py-4">
                        <div class="container-fluid">
                            <div class="mb-3">

                                <h3 class="fw-bold fs-4 mb-3">Admin Dashboard</h3>
                                <h3 class="fw-bold fs-4 my-3">Users List</h3>
                                <form action="DashboardController" method="post">
                                    <div class="row gx-2 gx-md-3 mb-4">
                                        <!-- Search by name -->
                                        <div class="col-md-4 mb-2 mb-md-0">
                                            <label class="form-label visually-hidden" for="searchByName">Search by name</label>
                                            <div class="input-group input-group-merge">
                                                <span class="input-group-prepend input-group-text">
                                                    <i class="bi-search"></i>
                                                </span>
                                                <input type="text" class="form-control form-control-lg" name="search_by_name" placeholder="Search by name" aria-label="Search by name">
                                            </div>
                                        </div>
                                        <!-- End Search by name -->

                                        <!-- Search by email -->
                                        <div class="col-md-4 mb-2 mb-md-0">
                                            <label class="form-label visually-hidden" for="searchByEmail">Search by email</label>
                                            <div class="input-group input-group-merge">
                                                <span class="input-group-prepend input-group-text">
                                                    <i class="bi-search"></i>
                                                </span>
                                                <input type="text" class="form-control form-control-lg" name="search_by_email" placeholder="Search by email" aria-label="Search by email">
                                            </div>
                                        </div>
                                        <!-- End Search by email -->

                                        <!-- Search by phone -->
                                        <div class="col-md-4 mb-2 mb-md-0">
                                            <label class="form-label visually-hidden" for="searchByPhone">Search by phone </label>
                                            <div class="input-group input-group-merge">
                                                <span class="input-group-prepend input-group-text">
                                                    <i class="bi-search"></i>
                                                </span>
                                                <input type="text" class="form-control form-control-lg" name="search_by_phone" placeholder="Search by phone " aria-label="Search by phone">
                                            </div>
                                        </div>
                                        <!-- End Search by phone -->
                                    </div>
                                    <!-- End Row 1 -->
                                    <div class="row gx-2 gx-md-3 mb-7">
                                        <!-- Fillter by gender -->
                                        <div class="col-sm-6 col-md-4 mb-2 mb-sm-0">
                                            <label class="form-label visually-hidden" for="fillterByGender">Select gender</label>
                                            <select class="form-select form-select-lg" name="fillter_by_gender" aria-label="Select gender">
                                                <option value="all" selected>All gender</option>
                                                <option value="0">Male</option>
                                                <option value="1">Female</option>
                                            </select>
                                        </div>
                                        <!-- End Fillter by gender -->

                                        <!-- Fillter by role -->
                                        <div class="col-sm-6 col-md-4 mb-2 mb-sm-0">
                                            <label class="form-label visually-hidden" for="fillterByRole">Select role</label>
                                            <select class="form-select form-select-lg" name="fillter_by_role" aria-label="Select role">
                                                <option value="all" selected>All role</option>
                                                <option value="1">Admin</option>
                                                <option value="2">Customer</option>
                                                <option value="3">Expert</option>
                                                <option value="4">Marketing</option>
                                            </select>
                                        </div>
                                        <!-- End Fillter by role -->

                                        <!-- Fillter by status -->
                                        <div class="col-sm-6 col-md-4 mb-2 mb-sm-0">
                                            <label class="form-label visually-hidden" for="fillterByStatus">Select status</label>
                                            <select class="form-select form-select-lg" name="fillter_by_status" aria-label="Select status">
                                                <option value="all" selected>All status</option>
                                                <option value="0">Deactivate</option>
                                                <option value="1">Activated</option>                                            
                                                <option value="2">Default Account</option>
                                            </select>
                                        </div>
                                        <!-- End Fillter by status -->

                                    </div>
                                    <!-- End Row 2 -->                               
                                    <br/>
                                    <!-- Start sorting -->
                                    <div class="container">
                                    <c:set var="checked" value="${requestScope.checked}"></c:set>
                                        <h5 class="mb-3">Sort by</h5>
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="form-check">
                                                    <input class="form-check-input" type="radio" name="sort_by" id="sort_by_id"  value="sort_by_id" ${checked == 'sort_by_id' ? 'checked' : ''}>
                                                <label class="form-check-label" for="sort_by_id">
                                                    ID
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="sort_by" id="sort_by_name" value="sort_by_name" ${checked == 'sort_by_name' ? 'checked' : ''}>
                                                <label class="form-check-label" for="sort_by_name">
                                                    Full name
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="sort_by" id="sort_by_gender" value="sort_by_gender" ${checked == 'sort_by_gender' ? 'checked' : ''}>
                                                <label class="form-check-label" for="sort_by_gender">
                                                    Gender
                                                </label>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="sort_by" id="sort_by_email" value="sort_by_email" ${checked == 'sort_by_email' ? 'checked' : ''}>
                                                <label class="form-check-label" for="sort_by_email">
                                                    Email
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="sort_by" id="sort_by_phone" value="sort_by_phone" ${checked == 'sort_by_phone' ? 'checked' : ''}>
                                                <label class="form-check-label" for="sort_by_phone">
                                                    Phone 
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="sort_by" id="sort_by_role" value="sort_by_role"  ${checked == 'sort_by_role' ? 'checked' : ''}>
                                                <label class="form-check-label" for="sort_by_role">
                                                    Role
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- End sorting -->
                                <br/>
                                <input type="hidden" name="service" value="viewAllAccount">
                                <button type="submit" name="fillterSubmit"  value="submit" class="btn btn-outline-primary">Submit</button>

                            </form>
                            <!-- End Form -->
                            <br/>
                            <div class="row">
                                <div class="col-12">
                                    <table class="table table-striped">    
                                        <thead>
                                            <tr class="highlight">
                                                <th scope="col">ID</th>
                                                <th scope="col">Full Name</th>
                                                <th scope="col">Gender</th>
                                                <th scope="col">Email</th>
                                                <th scope="col">Phone</th>
                                                <th scope="col">Role</th>
                                                <th scope="col">Status</th>
                                                <th scope="col">View</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${requestScope.data}" var="account">
                                                <tr>
                                                    <td>${account.getId()}</td>
                                                    <td>${account.getFirst_name()} ${account.getLast_name()}</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${account.isGender() == false}">
                                                                Male
                                                            </c:when>
                                                            <c:when test="${account.isGender() == true}">
                                                                Female
                                                            </c:when>                                                       
                                                        </c:choose>
                                                    </td>
                                                    <td>${account.getEmail()}</td>
                                                    <td>${account.getPhone()}</td>
                                                    <td>${account.getRole_id()}</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${account.getStatus() == 0}">
                                                                Deactivated
                                                            </c:when>
                                                            <c:when test="${account.getStatus() == 1}">
                                                                Activated
                                                            </c:when>
                                                            <c:when test="${account.getStatus() == 2}">
                                                                Default Account
                                                            </c:when>
                                                        </c:choose>
                                                    </td>
                                                    <td><a class="btn btn-primary" href="DashboardController?service=viewUserDetails&id=${account.getId()}" role="button">View</a></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <!-- Registration Form Section start-->
                            <h3 class="fw-bold fs-4 my-3">Add new user</h3>                           
                            <div class="wrapper rounded bg-white p-4">                               
                                <form class="form" action="DashboardController" method="post">
                                    <div class="row">
                                        <div class="col-md-6 mt-md-0 mt-3">
                                            <label for="firstName">First Name</label>
                                            <input type="text" class="form-control w-100" id="addNewUserFirstName" name="addNewUserFirstName"> 
                                        </div>
                                        <div class="col-md-6 mt-md-0 mt-3">
                                            <label for="lastName">Last Name</label>
                                            <input type="text" class="form-control w-100" id="addNewUserLastName" name="addNewUserLastName"> 
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6 mt-md-0 mt-3">
                                            <label for="birthday">Dob</label>
                                            <input type="date" class="form-control w-100" id="addNewUserDob" name="addNewUserDob"> 
                                        </div>
                                        <div class="col-md-6 mt-md-0 mt-3">
                                            <label>Gender</label>
                                            <div class="d-flex align-items-center mt-2">
                                                <label class="option">
                                                    <input type="radio" name="addNewUserGender" value="0" required> Male
                                                    <span class="checkmark"></span>
                                                </label>
                                                <label class="option ms-4">
                                                    <input type="radio" name="addNewUserGender" value="1" required> Female
                                                    <span class="checkmark"></span>
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6 mt-md-0 mt-3">
                                            <label for="email">Email</label>
                                            <input type="email" class="form-control w-100" id="addNewUserEmail" name="addNewUserEmail" required> 
                                        </div>
                                        <div class="col-md-6 mt-md-0 mt-3">
                                            <label for="phone">Phone</label>
                                            <input type="tel" class="form-control w-100" id="addNewUserPhone" name="addNewUserPhone"> 
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6 mt-md-0 mt-3">
                                            <label for="firstName">Address</label>
                                            <input type="text" class="form-control w-100" id="addNewUserAddress" name="addNewUserAddress"> 
                                        </div>
                                    </div>
                                    <div class="my-md-2 my-3">
                                        <label for="subject">Role</label>
                                        <select id="addNewUserRole" class="form-select w-100" name="addNewUserRole" required> 
                                            <option value="2">Customer</option>
                                            <option value="3">Marketing</option>
                                            <option value="4">Sale</option>
                                            <option value="5">Expert</option>
                                        </select>
                                    </div>
                                    <input type="hidden" name="service" value="addNewUser">
                                    <div class="text-center mt-4">
                                        <button type="submit" name="addNewUserSubmit" class="btn btn-primary w-100">Add new user</button> 
                                    </div>
                                </form>
                            </div>
                            <!-- Registration Form Section end-->
                        </div>
                    </div>
                </main>
            </div>
        </div>

    </body>
</html>
