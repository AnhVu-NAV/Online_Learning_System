<%-- 
    Document   : ViewRegistrationList
    Created on : Oct 13, 2024, 8:10:40 PM
    Author     : 84941
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,model.PersonalCourse" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registrations List</title>
        <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ViewUserList.css">
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="/component/AdminDashboardSidebar.jsp"></jsp:include>
                <div class="main">
                    <div class="content px-3 py-4">
                        <div class="container-fluid">
                            <div class="mb-3">
                                <!-- Display error message if any -->
                            <c:set var="errorMessage" value="${requestScope.errorMessage}"></c:set>
                            <c:if test="${not empty errorMessage}">
                                <div class="alert alert-danger" role="alert">
                                    ${pageScope.errorMessage}
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
                            <!-- Profile start -->
                            <nav class="navbar navbar-expand px-4 py-3">
                                <div class="navbar-collapse collapse">
                                    <ul class="navbar-nav ms-auto">
                                        <li class="nav-item dropdown">
                                            <c:set var="user" value="${sessionScope.user}"/>
                                            <a href="" data-bs-toggle="dropdown" class="nav-icon pe-md-0">
                                                <img src="${user.getImageURL()}" class="avatar img-fluid" alt="">
                                            </a>
                                            <div class="dropdown-menu dropdown-menu-end rounded">

                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </nav>
                            <!-- Profile end -->
                            <h3 class="fw-bold fs-4 mb-3">Sale Dashboard</h3>
                            <h3 class="fw-bold fs-4 my-3">Registration List</h3>
                            <form action="SaleRegistrationDashboardController" method="post">
                                <div class="row gx-2 gx-md-3 mb-4">
                                    <!-- Search by personal course id -->
                                    <div class="col-md-4 mb-2 mb-md-0">
                                        <label class="form-label" for="searchByCourseId">Search by course id</label>
                                        <div class="input-group input-group-merge">
                                            <span class="input-group-prepend input-group-text">
                                                <i class="bi-search"></i>
                                            </span>
                                            <input type="text" class="form-control form-control-lg" name="searchByCourseId" placeholder="Search by course id" aria-label="Search by course id">
                                        </div>
                                    </div>
                                    <!-- End Search by course id -->

                                    <!-- Search by customer email -->
                                    <div class="col-md-4 mb-2 mb-md-0">
                                        <label class="form-label" for="searchByEmail">Search by customer email</label>
                                        <div class="input-group input-group-merge">
                                            <span class="input-group-prepend input-group-text">
                                                <i class="bi-search"></i>
                                            </span>
                                            <input type="text" class="form-control form-control-lg" name="searchByCustomerEmail" placeholder="Search by customer email" aria-label="Search by customer email">
                                        </div>
                                    </div>
                                    <!-- End Search by customer email -->

                                    <!-- Search by course title -->
                                    <div class="col-md-4 mb-2 mb-md-0">
                                        <label class="form-label" for="searchByCourseTitle">Search by course title</label>
                                        <div class="input-group input-group-merge">
                                            <span class="input-group-prepend input-group-text">
                                                <i class="bi-search"></i>
                                            </span>
                                            <input type="text" class="form-control form-control-lg" name="searchByCourseTitle" placeholder="Search by course title" aria-label="Search by course title">
                                        </div>
                                    </div>
                                    <!-- End Search by course title -->
                                </div>

                                <div class="row gx-2 gx-md-3 mb-4">
                                    <!-- Search by price -->
                                    <div class="col-md-4 mb-2 mb-md-0">
                                        <label class="form-label" for="searchByPrice">Search by price</label>
                                        <div class="input-group input-group-merge">
                                            <span class="input-group-prepend input-group-text">
                                                <i class="bi-search"></i>
                                            </span>
                                            <input type="text" class="form-control form-control-lg" name="searchByPrice" placeholder="Search by price" aria-label="Search by price">
                                        </div>
                                    </div>
                                    <!-- End Search by price -->

                                    <!-- Filter by status -->
                                    <div class="col-md-4 mb-2 mb-md-0">
                                        <label class="form-label" for="filterByStatus">Select status</label>
                                        <select class="form-select form-select-lg" name="filterByStatus" aria-label="Select status">
                                            <option value="all" selected>All status</option>
                                            <option value="-1">Cancel</option>
                                            <option value="0">Submitted</option>
                                            <option value="1">Learning</option>
                                            <option value="2">Expired</option>
                                            <option value="3">Finished</option>
                                        </select>
                                    </div>
                                    <!-- End filter by status -->

                                    <!-- Search by enroll date -->
                                    <div class="col-md-4 mb-2 mb-md-0">
                                        <label class="form-label" for="searchByEnrollDate">Search by enroll date</label>
                                        <div class="input-group input-group-merge">
                                            <span class="input-group-prepend input-group-text">
                                                <i class="bi-calendar"></i>
                                            </span>
                                            <input type="date" class="form-control form-control-lg" name="searchByEnrollDate" aria-label="Search by enroll date">
                                        </div>
                                    </div>
                                    <!-- End search by enroll date -->
                                </div>

                                <div class="row gx-2 gx-md-3 mb-4">
                                    <!-- Search by expire date -->
                                    <div class="col-md-4 mb-2 mb-md-0">
                                        <label class="form-label" for="searchByExpireDate">Search by expire date</label>
                                        <div class="input-group input-group-merge">
                                            <span class="input-group-prepend input-group-text">
                                                <i class="bi-calendar"></i>
                                            </span>
                                            <input type="date" class="form-control form-control-lg" name="searchByExpireDate" aria-label="Search by expire date">
                                        </div>
                                    </div>
                                    <!-- End search by expire date -->

                                    <!-- Search by price package id -->
                                    <div class="col-md-4 mb-2 mb-md-0">
                                        <label class="form-label" for="searchByPricePackageId">Search by price package id</label>
                                        <div class="input-group input-group-merge">
                                            <span class="input-group-prepend input-group-text">
                                                <i class="bi-search"></i>
                                            </span>
                                            <input type="text" class="form-control form-control-lg" name="searchByPricePackageId" placeholder="Search by price package id" aria-label="Search by price package id">
                                        </div>
                                    </div>
                                    <!-- End search by price package id -->

                                    <!-- Search by price range -->
                                    <div class="col-md-4 mb-2 mb-md-0">
                                        <label class="form-label" for="searchByPriceRange">Search by price range</label>
                                        <div class="input-group input-group-merge">
                                            <input type="number" class="form-control form-control-lg" name="priceRangeFrom" placeholder="Price from" aria-label="Price from">
                                            <input type="number" class="form-control form-control-lg" name="priceRangeTo" placeholder="Price to" aria-label="Price to">
                                        </div>
                                    </div>
                                    <!-- End search by price range -->
                                </div>

                                <div class="row gx-2 gx-md-3 mb-4">
                                    <!-- Enroll date range -->
                                    <div class="col-md-4 mb-2 mb-md-0">
                                        <label class="form-label" for="enrollDateRange">Enroll date range</label>
                                        <div class="input-group input-group-merge">
                                            <input type="date" class="form-control form-control-lg" name="enrollDateFrom" placeholder="Start date" aria-label="Start date">
                                            <input type="date" class="form-control form-control-lg" name="enrollDateTo" placeholder="End date" aria-label="End date">
                                        </div>
                                    </div>
                                    <!-- End enroll date range -->

                                    <!-- Expire date range -->
                                    <div class="col-md-4 mb-2 mb-md-0">
                                        <label class="form-label" for="expireDateRange">Expire date range</label>
                                        <div class="input-group input-group-merge">
                                            <input type="date" class="form-control form-control-lg" name="expireDateFrom" placeholder="Start date" aria-label="Start date">
                                            <input type="date" class="form-control form-control-lg" name="expireDateTo" placeholder="End date" aria-label="End date">
                                        </div>
                                    </div>
                                    <!-- End expire date range -->
                                </div>
                                <br/>
                                <!-- Start sorting -->
                                <c:set var="checked" value="${requestScope.checked}"></c:set>
                                    <h5 class="mb-3">Sort by</h5>
                                    <div class="row">
                                        <!-- Sort by Personal Course ID -->
                                        <div class="col-md-3">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="sortBy" id="sortByCourseId" value="sortByCourseId" ${checked == 'sortByCourseId' ? 'checked' : ''}>
                                            <label class="form-check-label" for="sortByCourseId">
                                                Personal course id
                                            </label>
                                        </div>
                                    </div>

                                    <!-- Sort by Customer Email -->
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="sortBy" id="sortByCustomerEmail" value="sortByCustomerEmail" ${checked == 'sortByCustomerEmail' ? 'checked' : ''}>
                                            <label class="form-check-label" for="sortByCustomerEmail">
                                                Customer email
                                            </label>
                                        </div>
                                    </div>

                                    <!-- Sort by Course Title -->
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="sortBy" id="sortByCourseTitle" value="sortByCourseTitle" ${checked == 'sortByCourseTitle' ? 'checked' : ''}>
                                            <label class="form-check-label" for="sortByCourseTitle">
                                                Course title
                                            </label>
                                        </div>
                                    </div>

                                    <!-- Sort by Price -->
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="sortBy" id="sortByPrice" value="sortByPrice" ${checked == 'sortByPrice' ? 'checked' : ''}>
                                            <label class="form-check-label" for="sortByPrice">
                                                Price
                                            </label>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <!-- Sort by Price Package ID -->
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="sortBy" id="sortByPricePackageId" value="sortByPricePackageId" ${checked == 'sortByPricePackageId' ? 'checked' : ''}>
                                            <label class="form-check-label" for="sortByPricePackageId">
                                                Price package id
                                            </label>
                                        </div>
                                    </div>

                                    <!-- Sort by Status -->
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="sortBy" id="sortByStatus" value="sortByStatus" ${checked == 'sortByStatus' ? 'checked' : ''}>
                                            <label class="form-check-label" for="sortByStatus">
                                                Status
                                            </label>
                                        </div>
                                    </div>

                                    <!-- Sort by Enroll Date -->
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="sortBy" id="sortByEnrollDate" value="sortByEnrollDate" ${checked == 'sortByEnrollDate' ? 'checked' : ''}>
                                            <label class="form-check-label" for="sortByEnrollDate">
                                                Enroll date
                                            </label>
                                        </div>
                                    </div>

                                    <!-- Sort by Expire Date -->
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="sortBy" id="sortByExpireDate" value="sortByExpireDate" ${checked == 'sortByExpireDate' ? 'checked' : ''}>
                                            <label class="form-check-label" for="sortByExpireDate">
                                                Expire date
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <!-- End sorting -->
                                <br/>
                                <!-- Start choosing field -->
                                <h5 class="mb-3">Display fields: </h5>
                                <div class="row">
                                    <!-- Personal course id -->
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckCourseId" name="displayCourseId" ${requestScope.displayCourseId == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckCourseId">
                                                Personal course id
                                            </label>
                                        </div>
                                    </div>

                                    <!-- Customer email -->
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckCustomerEmail" name="displayCustomerEmail" ${requestScope.displayCustomerEmail == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckCustomerEmail">
                                                Customer email
                                            </label>
                                        </div>
                                    </div>

                                    <!-- Course title -->
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckCourseTitle" name="displayCourseTitle" ${requestScope.displayCourseTitle == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckCourseTitle">
                                                Course title
                                            </label>
                                        </div>
                                    </div>

                                    <!-- Price -->
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckPrice" name="displayPrice" ${requestScope.displayPrice == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckPrice">
                                                Price
                                            </label>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <!-- Status -->
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckStatus" name="displayStatus" ${requestScope.displayStatus == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckStatus">
                                                Status
                                            </label>
                                        </div>
                                    </div>

                                    <!-- Enroll date -->
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckEnrollDate" name="displayEnrollDate" ${requestScope.displayEnrollDate == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckEnrollDate">
                                                Enroll date
                                            </label>
                                        </div>
                                    </div>

                                    <!-- Expire date -->
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckExpireDate" name="displayExpireDate" ${requestScope.displayExpireDate == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckExpireDate">
                                                Expire date
                                            </label>
                                        </div>
                                    </div>

                                    <!-- Price package id -->
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckPricePackageId" name="displayPricePackageId" ${requestScope.displayPricePackageId == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckPricePackageId">
                                                Price package id
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <!-- End choosing field -->


                                <br/>
                                <input type="hidden" name="service" value="viewAllRegistration">
                                <button type="submit" name="fillterSubmit"  value="submit" class="btn btn-outline-primary">Submit</button>
                            </form> 
                            <br/>
                            <div class="row">
                                <div class="col-12">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr class="highlight">
                                                <th scope="col">RegistrationId </th>
                                                <th scope="col">CustomerEmail </th>
                                                <th scope="col">CourseTitle</th>
                                                <th scope="col">Price</th>
                                                <th scope="col">Status</th>
                                                <th scope="col">EnrollDate</th>
                                                <th scope="col">ExpireDate</th>
                                                <th scope="col">PricePackageId</th>
                                                <th scope="col">View</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <!-- Start paging -->
                            <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-center">
                                    <c:forEach begin="1" end="${requestScope.totalPage}" var="i">
                                        <li class="page-item"><a class="page-link" href="SaleRegistrationDashboardController?service=viewRegistration&index=${i}">${i}</a></li>
                                        </c:forEach>
                                </ul>
                            </nav>
                            <!-- End paging -->
                            <!-- Registration Form Section start-->
                            <h3 class="fw-bold fs-4 my-3">Add new registration</h3>                           
                            <div class="container">                               
                                <form class="form" action="SaleRegistrationDashboardController" method="post">
                                    <div class="row">
                                        <div class="col-md-3 mt-md-0 mt-3">
                                            <label for="addNewRegistrationCustomerId">Customer ID</label>
                                            <select id="addNewRegistrationCustomerId" class="form-select w-100" name="addNewRegistrationCustomerId" required> 
                                                <c:forEach items="${requestScope.customerVector}" var="customer">
                                                    <option value="${customer.getId()}">${customer.getId()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-md-2 mt-md-0 mt-3">
                                            <p>Or</p>
                                        </div>
                                        <div class="col-md-3 mt-md-0 mt-3">
                                            <label for="addNewRegistrationCustomerEmail">New customer</label>
                                            <input type="email" class="form-control w-100" id="addNewRegistrationEmail" name="addNewRegistrationEmail" required>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-4 mt-md-0 mt-3">
                                            <label for="addNewRegistrationEnrollDate">Enroll date</label>
                                            <input type="date" class="form-control w-100" id="addNewRegistrationEnrollDate" name="addNewRegistrationEnrollDate"> 
                                        </div>
                                        <div class="col-md-4 mt-md-0 mt-3">
                                            <label for="addNewRegistrationExpireDate">Expire date</label>
                                            <input type="date" class="form-control w-100" id="addNewRegistrationExpireDate" name="addNewRegistrationExpireDate"> 
                                        </div>
                                    </div>                                  
                                    <div class="row">
                                        <div class="col-md-4 mt-md-0 mt-3">
                                            <label for="addNewRegistrationCourseId">Course ID</label>
                                            <select id="addNewRegistrationCourseId" class="form-select w-100" name="addNewRegistrationCourseId" required> 
                                                <c:forEach items="${requestScope.courseVector}" var="course">
                                                    <option value="${course.getId()}">${course.getId()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="col-md-4 mt-md-0 mt-3">
                                            <label for="addNewRegistrationPricePackageId">Price Package Id</label>
                                            <select id="addNewRegistrationPricePackageId" class="form-select w-100" name="addNewRegistrationPricePackageId" required> 
                                                <c:forEach items="${requestScope.pricePackageVector}" var="pricePackage">
                                                    <option value="${pricePackage.getId()}">${pricePackage.getId()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <input type="hidden" name="service" value="addNewRegistration">
                                    <div class="col-md-6 mt-md-0 mt-3">
                                        <div class="text-center mt-4">
                                            <button type="submit" name="addNewRegistrationSubmit" class="btn btn-primary w-100">Add new registration</button> 
                                        </div>
                                    </div>

                                </form>
                            </div>
                            <!-- Registration Form Section end-->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
