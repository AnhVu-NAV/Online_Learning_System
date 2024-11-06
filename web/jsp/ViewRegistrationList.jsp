<%-- 
    Document   : ViewRegistrationList
    Created on : Oct 13, 2024, 8:10:40 PM
    Author     : 84941
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,model.*" %>
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
                                            <input type="text" class="form-control form-control-lg" name="searchByPersonalCourseId" placeholder="Search by personal course id" aria-label="Search by personal course id">
                                        </div>
                                    </div>
                                    <!-- End Search by course id -->

                                    <!-- Search by customer id -->
                                    <div class="col-md-4 mb-2 mb-md-0">
                                        <label class="form-label" for="searchByCustomerId">Search by customer id</label>
                                        <div class="input-group input-group-merge">
                                            <span class="input-group-prepend input-group-text">
                                                <i class="bi-search"></i>
                                            </span>
                                            <input type="text" class="form-control form-control-lg" name="searchByCustomerId" placeholder="Search by customer id" aria-label="Search by customer id">
                                        </div>
                                    </div>
                                    <!-- End Search by customer id -->

                                    <!-- Search by course id -->
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
                                                <input class="form-check-input" type="radio" name="sortBy" id="sortByPersonalCourseId" value="sortByPersonalCourseId" ${checked == 'sortByPersonalCourseId' ? 'checked' : ''}>
                                            <label class="form-check-label" for="sortByPersonalCourseId">
                                                Personal course id
                                            </label>
                                        </div>
                                    </div>

                                    <!-- Sort by Customer ID -->
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="sortBy" id="sortByCustomerId" value="sortByCustomerId" ${checked == 'sortByCustomerId' ? 'checked' : ''}>
                                            <label class="form-check-label" for="sortByCustomerId">
                                                Customer id
                                            </label>
                                        </div>
                                    </div>

                                    <!-- Sort by Course ID -->
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="sortBy" id="sortByCourseId" value="sortByCourseId" ${checked == 'sortByCourseId' ? 'checked' : ''}>
                                            <label class="form-check-label" for="sortByCourseId">
                                                Course id
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
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckCourseId" name="displayPersonalCourseId" ${requestScope.displayPersonalCourseId == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckCourseId">
                                                Personal course id
                                            </label>
                                        </div>
                                    </div>

                                    <!-- Customer ID -->
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckCustomerId" name="displayCustomerId" ${requestScope.displayCustomerId == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckCustomerId">
                                                Customer ID
                                            </label>
                                        </div>
                                    </div>

                                    <!-- Course ID -->
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckCourseId" name="displayCourseId" ${requestScope.displayCourseId == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckCourseId">
                                                Course ID
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
                                                <c:if test="${requestScope.displayPersonalCourseId=='true'}"><th scope="col">Personal Course ID </th></c:if>
                                                <c:if test="${requestScope.displayCustomerId=='true'}"><th scope="col">Customer ID</th></c:if>
                                                <c:if test="${requestScope.displayCourseId=='true'}"><th scope="col">Course ID</th></c:if>
                                                <c:if test="${requestScope.displayPrice=='true'}"><th scope="col">Price</th></c:if>
                                                <c:if test="${requestScope.displayStatus=='true'}"><th scope="col">Status</th></c:if>
                                                <c:if test="${requestScope.displayEnrollDate=='true'}"><th scope="col">EnrollDate</th></c:if>
                                                <c:if test="${requestScope.displayExpireDate=='true'}"><th scope="col">ExpireDate</th></c:if>
                                                <c:if test="${requestScope.displayPricePackageId=='true'}"><th scope="col">PricePackageId</th></c:if>
                                                    <th scope="col">View</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${requestScope.personalCourseVector}" var="personalCourse">
                                                <tr>
                                                    <c:if test="${requestScope.displayPersonalCourseId=='true'}"><td>${personalCourse.getId()}</td></c:if>
                                                    <c:if test="${requestScope.displayCustomerId=='true'}"><td>${personalCourse.getCustomerId()}</td></c:if>
                                                    <c:if test="${requestScope.displayCourseId=='true'}"><td>${personalCourse.getCourseId()}</td></c:if>
                                                    <c:if test="${requestScope.displayPrice=='true'}"><td>${personalCourse.getPrice()}</td></c:if>
                                                    <c:if test="${requestScope.displayStatus=='true'}"><td>${personalCourse.getStatus()}</td></c:if>
                                                    <c:if test="${requestScope.displayEnrollDate=='true'}"><td>${personalCourse.getEnrollDate()}</td></c:if>
                                                    <c:if test="${requestScope.displayExpireDate=='true'}"><td>${personalCourse.getExpireDate()}</td></c:if>
                                                    <c:if test="${requestScope.displayPricePackageId=='true'}"><td>${personalCourse.getPricePackageId()}</td></c:if>
                                                        <td>
                                                            <a class="btn btn-primary" href="SaleRegistrationDashboardController?service=viewRegistrationDetails&id=${personalCourse.getId()}" role="button">View</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>

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
                            <!-- Recommend course start-->
                            <h3 class="fw-bold fs-4 my-3">Recommend courses</h3>                           
                            <div class="container">                               
                                <form class="form" action="SaleRegistrationDashboardController" method="post" enctype="multipart/form-data">
                                    <div class="row">
                                        <div class="col-md-4 mt-md-0 mt-3">
                                            <label for="recommendCourseCustomerId">Customer ID</label>
                                            <select id="recommendCourseCustomerId" class="form-select w-100" name="recommendCourseCustomerId" required> 
                                                <c:forEach items="${requestScope.customerVector}" var="customer">
                                                    <option value="${customer.getId()}">${customer.getPrimaryEmail()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-md-4 mt-md-0 mt-3">
                                            <label for="recommendCourseCustomerId">Customer ID</label>
                                            <select id="recommendCourseCourseId" class="form-select w-100" name="recommendCourseCourseId" required> 
                                                <c:forEach items="${requestScope.courseVector}" var="course">
                                                    <option value="${course.getId()}">${course.getTitle()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>


                                    </div>
                                    <br/>
                                    <div class="row">
                                        <div class="col-md-8 mt-md-0 mt-3">
                                            <input type="text" class="form-control" placeholder="Note" aria-label="Note" name="recommendCourseTextContent">
                                        </div>
                                    </div>

                                    <!-- Image Upload and Display Section -->
                                    <div class="row mt-4">
                                        <!-- Image Upload Input -->
                                        <div class="col-md-6">
                                            <label for="courseImages">Add Images</label>
                                            <input type="file" class="form-control" id="courseImages" name="courseImages" multiple accept="image/*">
                                        </div>

                                        <!-- Image Preview Section -->
                                        <div class="col-md-12 mt-3">
                                            <div id="imagePreview" class="d-flex flex-wrap gap-2">
                                                <!-- Placeholder for dynamically added image previews -->
                                            </div>
                                        </div>
                                    </div>
                                    <!-- JavaScript to handle image preview and deletion -->
                                    <script>
                                        const imagePreviewContainer = document.getElementById('imagePreview');
                                        const imageInput = document.getElementById('courseImages');

                                        imageInput.addEventListener('change', () => {
                                            imagePreviewContainer.innerHTML = '';
                                            Array.from(imageInput.files).forEach(file => {
                                                const reader = new FileReader();
                                                reader.onload = (event) => {
                                                    const imgWrapper = document.createElement('div');
                                                    imgWrapper.classList.add('position-relative');

                                                    const img = document.createElement('img');
                                                    img.src = event.target.result;
                                                    img.classList.add('img-thumbnail', 'mb-2');
                                                    img.style.width = '100px';
                                                    img.style.height = '100px';

                                                    const deleteBtn = document.createElement('button');
                                                    deleteBtn.classList.add('btn', 'btn-sm', 'btn-danger', 'position-absolute', 'top-0', 'end-0');
                                                    deleteBtn.innerHTML = '&times;';
                                                    deleteBtn.onclick = () => {
                                                        imgWrapper.remove();
                                                    };

                                                    imgWrapper.appendChild(img);
                                                    imgWrapper.appendChild(deleteBtn);
                                                    imagePreviewContainer.appendChild(imgWrapper);
                                                };
                                                reader.readAsDataURL(file);
                                            });
                                        });
                                    </script>

                                    <input type="hidden" name="service" value="recommendCourse">
                                    <div class="col-md-6 mt-md-0 mt-3">
                                        <div class="text-center mt-4">
                                            <button type="submit" name="recommendCourse" class="btn btn-primary w-100">Recommend course</button> 
                                        </div>
                                    </div>

                                </form>
                            </div>
                            <!-- Reconmmend course end-->
                            <!-- Recommend course using AI start-->
                            <h3 class="fw-bold fs-4 my-3">Recommend courses using AI</h3>                           
                            <div class="container">                               
                                <form class="form" action="SaleRegistrationDashboardController" method="post">
                                    <div class="row">
                                        <div class="col-md-4 mt-md-0 mt-3">
                                            <label for="recommendCourseUsingAICustomerId">Customer ID</label>
                                            <select id="recommendCourseUsingAICustomerId" class="form-select w-100" name="recommendCourseUsingAICustomerId" required> 
                                                <c:forEach items="${requestScope.customerVector}" var="customer">
                                                    <option value="${customer.getId()}">${customer.getPrimaryEmail()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-md-4 mt-md-0 mt-3">
                                            <label for="recommendCourseUsingAICustomerId">Customer ID</label>
                                            <select id="recommendCourseUsingAICustomerId" class="form-select w-100" name="recommendCourseUsingAICustomerId" required> 
                                                <c:forEach items="${requestScope.courseVector}" var="course">
                                                    <option value="${course.getId()}">${course.getTitle()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                    </div>  
                                    <br/>
                                    <div class="row">
                                        <div class="col-md-8 mt-md-0 mt-3">
                                            <input type="text" class="form-control" placeholder="AI-Generated Recommendation" aria-label="AI-Generated Recommendation">
                                        </div>
                                    </div>

                                    <input type="hidden" name="service" value="recommendCourseUsingAI">
                                    <div class="col-md-6 mt-md-0 mt-3">
                                        <div class="text-center mt-4">
                                            <button type="submit" name="recommendCourseUsingAI" class="btn btn-primary w-100">Recommend course using AI</button> 
                                        </div>
                                    </div>

                                </form>
                            </div>
                            <!-- Reconmmend course using AI end-->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
