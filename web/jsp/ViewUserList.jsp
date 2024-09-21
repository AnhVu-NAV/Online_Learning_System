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
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="wrapper">
            <aside id="sidebar">
                <div class="d-flex">
                    <button class="toggle-btn" type="button">
                        <i class="lni lni-grid-alt"></i>
                    </button>
                    <div class="sidebar-logo">
                        <a href="#">Online Learning System</a>
                    </div>
                </div>
                <ul class="sidebar-nav">
                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link">
                            <i class="lni lni-user"></i>
                            <span>Users List</span>
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link">
                            <i class="lni lni-list"></i>
                            <span>Accounts Log</span>
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link">
                            <i class="lni lni-cog"></i>
                            <span>Settings List</span>
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link">
                            <i class="lni lni-circle-plus"></i>
                            <span>Add new user</span>
                        </a>
                    </li>
                </ul>
                <div class="sidebar-footer">
                    <a href="#" class="sidebar-link">
                        <i class="lni lni-exit"></i>
                        <span>Logout</span>
                    </a>
                </div>
            </aside>
            <div class="main">
                <main class="content px-3 py-4">
                    <div class="container-fluid">
                        <div class="mb-3">
                            <h3 class="fw-bold fs-4 mb-3">Admin Dashboard</h3>
                            <h3 class="fw-bold fs-4 my-3">Users List</h3>
                            <form action="AdminDashboardController" method="post">
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
                                        <label class="form-label visually-hidden" for="searchByPhoneNumber">Search by phone number</label>
                                        <div class="input-group input-group-merge">
                                            <span class="input-group-prepend input-group-text">
                                                <i class="bi-search"></i>
                                            </span>
                                            <input type="text" class="form-control form-control-lg" name="search_by_phone_number" placeholder="Search by phone number" aria-label="Search by phone number">
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
                                            <option selected>All gender</option>
                                            <option value="0">Male</option>
                                            <option value="1">Female</option>
                                        </select>
                                    </div>
                                    <!-- End Fillter by gender -->

                                    <!-- Fillter by role -->
                                    <div class="col-sm-6 col-md-4 mb-2 mb-sm-0">
                                        <label class="form-label visually-hidden" for="fillterByRole">Select role</label>
                                        <select class="form-select form-select-lg" name="fillter_by_role" aria-label="Select role">
                                            <option selected>All role</option>
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
                                            <option selected>All status</option>
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
                                                <input class="form-check-input" type="radio" name="sort_by" id="sort_by_phone_number" value="sort_by_phone_number" ${checked == 'sort_by_phone_number' ? 'checked' : ''}>
                                                <label class="form-check-label" for="sort_by_phone_number">
                                                    Phone number
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
                                <button type="submit" name="submit"  value="submit" class="btn btn-outline-primary">Submit</button>

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
                                                <th scope="col">Phone number</th>
                                                <th scope="col">Role</th>
                                                <th scope="col">Status</th>
                                                <th scope="col">View</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${requestScope.data}" var="account">
                                                <tr>
                                                    <td>${account.id}</td>
                                                    <td>${account.first_name} ${account.last_name}</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${account.gender == false}">
                                                                Male
                                                            </c:when>
                                                            <c:when test="${account.gender == true}">
                                                                Female
                                                            </c:when>                                                       
                                                        </c:choose>
                                                    </td>
                                                    <td>${account.email}</td>
                                                    <td>${account.phone_number}</td>
                                                    <td>${account.role_id}</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${account.status == 0}">
                                                                Deactivated
                                                            </c:when>
                                                            <c:when test="${account.status == 1}">
                                                                Activated
                                                            </c:when>
                                                            <c:when test="${account.status == 2}">
                                                                Default Account
                                                            </c:when>
                                                        </c:choose>
                                                    </td>
                                                    <td><a class="btn btn-primary" href="AdminDashboardController?service=viewUserDetails&search_id=${account.id}" role="button">View</a></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
        <script src="js/script.js"></script>
    </body>
</html>
