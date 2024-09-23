<%-- 
    Document   : ViewSettingList
    Created on : Sep 21, 2024, 9:41:33 PM
    Author     : 84941
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,model.Setting" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ViewUserList.css">
        <title>View Setting List</title>
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="/component/DashboardSidebar.jsp"></jsp:include>
                <div class="main">
                    <main class="content px-3 py-4">
                        <div class="container-fluid">
                            <div class="mb-3">
                                <!-- Display error message if any -->
                            <c:set var="error_message" value="${requestScope.error_message}"></c:set>
                            <c:if test="${not empty error_message}">
                                <div class="alert alert-danger" role="alert">
                                    ${pageScope.error_message}
                                </div>
                            </c:if>                                           
                            <!-- End error message -->

                            <!-- Display message if any -->
                            <c:set var="message" value="${requestScope.message}"></c:set>
                            <c:if test="${not empty message}">
                                <div class="alert alert-success" role="alert">
                                    ${pageScope.message}
                                </div>
                            </c:if>                                            
                            <!-- End message -->
                            <h3 class="fw-bold fs-4 mb-3">Admin Dashboard</h3>
                            <h3 class="fw-bold fs-4 my-3">Setting List</h3>
                            <form action="SettingDashboardController" method="post">
                                <div class="row gx-2 gx-md-3 mb-4">
                                    <!-- Search by value -->
                                    <div class="col-md-4 mb-2 mb-md-0">
                                        <label class="form-label visually-hidden" for="searchByValue">Search by value</label>
                                        <div class="input-group input-group-merge">
                                            <span class="input-group-prepend input-group-text">
                                                <i class="bi-search"></i>
                                            </span>
                                            <input type="text" class="form-control form-control-lg" name="search_by_value" placeholder="Search by value" aria-label="Search by value">
                                        </div>
                                    </div>
                                    <!-- End Search by value -->

                                    <!-- Fillter by type -->
                                    <div class="col-sm-6 col-md-4 mb-2 mb-sm-0">
                                        <label class="form-label visually-hidden" for="fillterByType">Select type</label>
                                        <select class="form-select form-select-lg" name="fillter_by_type" aria-label="Select type">
                                            <option value="all" selected>All type</option>
                                            <c:forEach items="${requestScope.setting_type}" var="setting_type">
                                                <option value="${setting_type.getId()}">${setting_type.getName()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <!-- End Fillter by type -->

                                    <!-- Fillter by status -->
                                    <div class="col-sm-6 col-md-4 mb-2 mb-sm-0">
                                        <label class="form-label visually-hidden" for="fillterByStatus">Select status</label>
                                        <select class="form-select form-select-lg" name="fillter_by_status" aria-label="Select status">
                                            <option value="all" selected>All status</option>
                                            <option value="1">Activated</option>
                                            <option value="0">Deactivated</option>
                                        </select>
                                    </div>
                                    <!-- End Fillter by status -->
                                </div>

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
                                                <input class="form-check-input" type="radio" name="sort_by" id="sort_by_setting_type" value="sort_by_setting_type" ${checked == 'sort_by_setting_type' ? 'checked' : ''}>
                                                <label class="form-check-label" for="sort_by_setting_type">
                                                    Type
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="sort_by" id="sort_by_value" value="sort_by_value" ${checked == 'sort_by_value' ? 'checked' : ''}>
                                                <label class="form-check-label" for="sort_by_value">
                                                    Value
                                                </label>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="sort_by" id="sort_by_created_date" value="sort_by_created_date" ${checked == 'sort_by_created_date' ? 'checked' : ''}>
                                                <label class="form-check-label" for="sort_by_created_date">
                                                    Created date
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="sort_by" id="sort_by_updated_date" value="sort_by_updated_date" ${checked == 'sort_by_updated_date' ? 'checked' : ''}>
                                                <label class="form-check-label" for="sort_by_updated_date">
                                                    Updated date 
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="sort_by" id="sort_by_status" value="sort_by_status"  ${checked == 'sort_by_status' ? 'checked' : ''}>
                                                <label class="form-check-label" for="sort_by_status">
                                                    Status
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- End sorting -->
                                <br/>
                                <input type="hidden" name="service" value="viewAllSetting">
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
                                                <th scope="col">Type</th>
                                                <th scope="col">Value</th>
                                                <th scope="col">Created date</th>
                                                <th scope="col">Updated date</th>
                                                <th scope="col">Status</th>
                                                <th scope="col">View</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${requestScope.data}" var="setting">
                                                <tr>
                                                    <td>${setting.getId()}</td>
                                                    <td>
                                                        <c:forEach items="${requestScope.setting_type}" var="setting_type">
                                                            <c:if test="${setting.getSetting_type_id()==setting_type.getId()}">
                                                                ${setting_type.getName()}
                                                            </c:if>
                                                        </c:forEach>
                                                    </td>                                                
                                                    <td>${setting.getValue()}</td>
                                                    <td>${setting.getCreated_date()}</td>
                                                    <td>${setting.getUpdated_date()}</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${setting.getStatus() == 0}">
                                                                Deactivated
                                                            </c:when>
                                                            <c:when test="${setting.getStatus() == 1}">
                                                                Activated
                                                            </c:when>
                                                        </c:choose>
                                                    </td>
                                                    <td><a class="btn btn-primary" href="SettingDashboardController?service=viewSettingDetails&id=${setting.getId()}" role="button">View</a></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-center">
                                    <c:forEach begin="1" end="${requestScope.totalPage}" var="i">
                                        <li class="page-item"><a class="page-link" href="SettingDashboardController?service=viewAllSetting&index=${i}">${i}</a></li>
                                        </c:forEach>
                                </ul>
                            </nav>
                            <!-- Registration Form Section start-->
                            <h3 class="fw-bold fs-4 my-3">Add new setting</h3>                           
                            <div class="wrapper rounded bg-white p-4">                               
                                <form class="form" action="SettingDashboardController" method="post">
                                    <div class="row">
                                        <div class="col-md-6 mt-md-0 mt-3">
                                            <label for="firstName">Value</label>
                                            <input type="text" class="form-control w-100" id="addNewSettingValue" name="addNewSettingValue" required=""> 
                                        </div>
                                        <div class="col-md-6 mt-md-0 mt-3">
                                            <label for="lastName">Description</label>
                                            <input type="text" class="form-control w-100" id="addNewSettingDescription" name="addNewSettingDescription" required=""> 
                                        </div>
                                    </div>
                                    <div class="my-md-2 my-3">
                                        <label for="subject">Role</label>
                                        <select id="addNewSettingSettingType" class="form-select w-100" name="addNewSettingSettingType" required> 
                                            <c:forEach items="${requestScope.setting_type}" var="setting_type">
                                                <option value="${setting_type.getId()}">${setting_type.getName()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <input type="hidden" name="service" value="addNewSetting">
                                    <div class="text-center mt-4">
                                        <button type="submit" name="addNewSettingSubmit" class="btn btn-primary w-100">Add new setting</button> 
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
