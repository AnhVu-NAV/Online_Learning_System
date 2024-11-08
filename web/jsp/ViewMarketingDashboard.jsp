<%-- 
    Document   : ViewMarketingDashboard
    Created on : Sep 27, 2024, 10:00:30 PM
    Author     : 84941
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>View Marketing Dashboard</title>
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="/component/MarketingDashboardSidebar.jsp"></jsp:include>
                <div class="main">
                    <nav class="navbar navbar-expand px-4 py-3">
                        <div class="navbar-collapse collapse">
                            <ul class="navbar-nav ms-auto">
                                <li class="nav-item dropdown">
                                    <a href="#" data-bs-toggle="dropdown" class="nav-icon pe-md-0">
                                        <img src="/account.png" class="avatar img-fluid" alt="">
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-end rounded">

                                    </div>
                                </li>
                            </ul>
                        </div>
                    </nav>
                    <main class="content px-3 py-4">
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
                            <h3 class="fw-bold fs-4 mb-3">Marketing Dashboard</h3>

                            <br/>
                            
                            
                            <!-- New course start -->
                            <h3 class="fw-bold fs-4 my-3">New Course</h3>
                            <form action="MarketingDashboardController" method="post">
                                <!-- Start filler by date -->
                                <h5 class="mb-3">Filler by: </h5>
                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="newCourseFillterBy" id="newCourseFillterBy"  value="fillterByTheLast7Days" ${newCourseFillterBy == 'fillterByTheLast7Days' ? 'checked' : ''}>
                                            <label class="form-check-label" for="fillterByTheLast7Days">
                                                The last 7 days
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="newCourseFillterBy" id="newCourseFillterBy"  value="fillterByThisMonth" ${newCourseFillterBy == 'fillterByThisMonth' ? 'checked' : ''}>
                                            <label class="form-check-label" for="fillterByTheLastMonth">
                                                This month
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="newCourseFillterBy" id="newCourseFillterBy"  value="fillterByThisQuarter" ${newCourseFillterBy == 'fillterByThisQuarter' ? 'checked' : ''}>
                                            <label class="form-check-label" for="fillterByTheLastQuarter">
                                                This quarter
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="newCourseFillterBy" id="newCourseFillterBy"  value="fillterByThisYear" ${newCourseFillterBy == 'fillterByThisYear' ? 'checked' : ''}>
                                            <label class="form-check-label" for="fillterByTheLastYear">
                                                This year
                                            </label>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="newCourseFillterBy" id="newCourseFillterBy"  value="fillterByDateRange" ${newCourseFillterBy == 'fillterByDateRange' ? 'checked' : ''}>
                                            <label class="form-check-label" for="fillterByDateRange">
                                                Date range
                                            </label>
                                            <div class="row mt-3" id="dateRangeInputs">
                                                <div class="col-md-6">
                                                    <label for="startDate">Start Date:</label>
                                                    <input type="date" class="form-control" id="startDate" name="newCourseStartDate">
                                                </div>
                                                <div class="col-md-6">
                                                    <label for="endDate">End Date:</label>
                                                    <input type="date" class="form-control" id="endDate" name="newCourseEndDate">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="newCourseFillterBy" id="newCourseFillterBy"  value="allTime" ${newCourseFillterBy == 'allTime' ? 'checked' : ''}>
                                            <label class="form-check-label" for="fillterByTheLastYear">
                                                All time
                                            </label>
                                        </div>
                                    </div>        
                                </div> 

                                <!-- End filler by date -->                                                               
                                <br/>
                                <div class="row">
                                    <!-- Fillter by status start -->
                                    <div class="col-md-3">
                                        <label class="form-label visually-hidden" for="newCourseFillterByStatus">Select status</label>
                                        <select class="form-select form-select-lg" name="newCourseFillterByStatus" aria-label="Select status">
                                            <option value="all" selected>All status</option>
                                            <option value="0">Deactivate</option>
                                            <option value="1">Activated</option>                                            
                                        </select>                                        
                                    </div>
                                    <!-- Fillter by status end --> 
                                    <!-- Fillter by category start -->
                                    <div class="col-md-3">
                                        <label class="form-label visually-hidden" for="newCourseFillterByCategory">Select category</label>
                                        <select class="form-select form-select-lg" name="newCourseFillterByCategory" aria-label="Select category">
                                            <option value="all" selected>All category</option>
                                            <c:forEach items="${requestScope.categoryVector}" var="setting">
                                                <option value="${setting.getId()}">${setting.getValue()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <!-- Fillter by category end -->
                                </div>
                                <br/>
                                <!-- Start choosing field  -->
                                <h5 class="mb-3">Display fields: </h5>
                                <div class="row">
                                    <div class="col-md-2">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckDefault" name="newCourseDisplayID" ${requestScope.newCourseDisplayID == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckDefault">
                                                ID
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckChecked" name="newCourseDisplayTitle" ${requestScope.newCourseDisplayTitle == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckChecked">
                                                Title
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckChecked" name="newCourseDisplaySubTitle" ${requestScope.newCourseDisplaySubTitle == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckChecked">
                                                Subtitle
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckChecked" name="newCourseDisplayExpert" ${requestScope.newCourseDisplayExpert == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckChecked">
                                                Expert
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckChecked" name="newCourseDisplayTotalDuration" ${requestScope.newCourseDisplayTotalDuration == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckChecked">
                                                Total Duration
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckChecked" name="newCourseDisplayStatus" ${requestScope.newCourseDisplayStatus == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckChecked">
                                                Status
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-2">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckChecked" name="newCourseDisplayDescription" ${requestScope.newCourseDisplayDescription == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckChecked">
                                                Description
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckChecked" name="newCourseDisplayCategory" ${requestScope.newCourseDisplayCategory == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckChecked">
                                                Category
                                            </label>
                                        </div>
                                    </div>   
                                    <div class="col-md-2">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckChecked" name="newCourseDisplayCreatedDate" ${requestScope.newCourseDisplayCreatedDate == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckChecked">
                                                Created date
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckChecked" name="newCourseDisplayUpdatedDate" ${requestScope.newCourseDisplayUpdatedDate == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckChecked">
                                                Updated date
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckChecked" name="newCourseDisplayThumbnail" ${requestScope.newCourseDisplayThumbnail == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckChecked">
                                                Thumbnail
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckChecked" name="newCourseDisplayNumberOfLearner" ${requestScope.newCourseDisplayNumberOfLearner == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckChecked">
                                                Number of learner
                                            </label>
                                        </div>
                                    </div>                               
                                </div>
                                <!-- End choosing field  -->
                                <br/>
                                <input class="btn btn-primary" type="submit" value="Submit" name="newCourseSubmit">
                            </form>

                            <br/>
                            <div class="row">
                                <div class="col-12">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr class="highlight">
                                                <c:if test="${requestScope.newCourseDisplayID=='true'}"><th scope="col">ID</th></c:if>
                                                <c:if test="${requestScope.newCourseDisplayTitle=='true'}"><th scope="col">Title</th></c:if> 
                                                <c:if test="${requestScope.newCourseDisplaySubTitle=='true'}"><th scope="col">Subtitle</th></c:if> 
                                                <c:if test="${requestScope.newCourseDisplayExpert=='true'}"><th scope="col">Expert</th></c:if> 
                                                <c:if test="${requestScope.newCourseDisplayTotalDuration=='true'}"><th scope="col">Total Duration</th></c:if>
                                                <c:if test="${requestScope.newCourseDisplayStatus=='true'}"><th scope="col">Status</th></c:if>                                                        
                                                <c:if test="${requestScope.newCourseDisplayDescription=='true'}"><th scope="col">Description</th></c:if>     
                                                <c:if test="${requestScope.newCourseDisplayCategory=='true'}"><th scope="col">Category</th></c:if>
                                                <c:if test="${requestScope.newCourseDisplayCreatedDate=='true'}"><th scope="col">Created date</th></c:if>
                                                <c:if test="${requestScope.newCourseDisplayUpdatedDate=='true'}"><th scope="col">Updated date</th></c:if>
                                                <c:if test="${requestScope.newCourseDisplayThumbnail=='true'}"><th scope="col">Thumbnail</th></c:if>
                                                <c:if test="${requestScope.newCourseDisplayNumberOfLearner=='true'}"><th scope="col">Number of learner</th></c:if>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${requestScope.newCourseVector}" var="course">
                                                <tr>
                                                    <c:if test="${requestScope.newCourseDisplayID=='true'}"><td>${course.getId()}</td></c:if>
                                                    <c:if test="${requestScope.newCourseDisplayTitle=='true'}"><td>${course.getTitle()}</td></c:if>
                                                    <c:if test="${requestScope.newCourseDisplaySubTitle=='true'}"><td>${course.getSubtitle()}</td></c:if>
                                                    <c:if test="${requestScope.newCourseDisplayExpert=='true'}">
                                                        <c:forEach items="${requestScope.userVector}" var="user">
                                                            <c:if test="${course.getExpertId()==user.getId()}"><td>${user.getFirstName()} ${user.getLastName()}</td></c:if>
                                                        </c:forEach>
                                                    </c:if>
                                                    <c:if test="${requestScope.newCourseDisplayTotalDuration=='true'}"><td>${course.getTotalDuration()}</td></c:if>
                                                    <c:if test="${requestScope.newCourseDisplayStatus=='true'}">
                                                        <c:choose>
                                                            <c:when test="${course.getStatus() == 0}">
                                                                <td>Deactivated</td>
                                                            </c:when>
                                                            <c:when test="${course.getStatus() == 1}">
                                                                <td>Activated</td>
                                                            </c:when>                                                           
                                                        </c:choose> 
                                                    </c:if>
                                                    <c:if test="${requestScope.newCourseDisplayDescription=='true'}"><td>${course.getDescription()}</td></c:if>
                                                    <c:if test="${requestScope.newCourseDisplayCategory=='true'}">
                                                        <c:forEach items="${requestScope.settingVector}" var="setting">
                                                            <c:if test="${course.getCategoryId()==setting.getId()}"><td>${setting.getValue()}</td></c:if>
                                                        </c:forEach>
                                                    </c:if>
                                                    <c:if test="${requestScope.newCourseDisplayCreatedDate=='true'}"><td>${course.getCreatedDate()}</td></c:if>
                                                    <c:if test="${requestScope.newCourseDisplayUpdatedDate=='true'}"><td>${course.getUpdatedDate()}</td></c:if>
                                                    <c:if test="${requestScope.newCourseDisplayThumbnail=='true'}"><td>${course.getThumbnailUrl()}</td></c:if>
                                                    <c:if test="${requestScope.newCourseDisplayNumberOfLearner=='true'}"><td>${course.getNumberOfLearner()}</td></c:if>
                                                    </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>    
                            <!-- New course end -->                    
                            <!-- New registrations start -->
                            <h3 class="fw-bold fs-4 my-3">New Registrations</h3>
                            <form action="MarketingDashboardController" method="post">
                                <!-- Start filler by date -->
                                <h5 class="mb-3">Filler by: </h5>
                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="newRegistrationFillterBy" id="newRegistrationFillterBy"  value="fillterByTheLast7Days" ${newRegistrationFillterBy == 'fillterByTheLast7Days' ? 'checked' : ''}>
                                            <label class="form-check-label" for="fillterByTheLast7Days">
                                                The last 7 days
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="newRegistrationFillterBy" id="newRegistrationFillterBy"  value="fillterByThisMonth" ${newRegistrationFillterBy == 'fillterByThisMonth' ? 'checked' : ''}>
                                            <label class="form-check-label" for="fillterByTheLastMonth">
                                                This month
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="newRegistrationFillterBy" id="newRegistrationFillterBy"  value="fillterByThisQuarter" ${newRegistrationFillterBy == 'fillterByThisQuarter' ? 'checked' : ''}>
                                            <label class="form-check-label" for="fillterByTheLastQuarter">
                                                This quarter
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="newRegistrationFillterBy" id="newRegistrationFillterBy"  value="fillterByThisYear" ${newRegistrationFillterBy == 'fillterByThisYear' ? 'checked' : ''}>
                                            <label class="form-check-label" for="fillterByTheLastYear">
                                                This year
                                            </label>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="newRegistrationFillterBy" id="newRegistrationFillterBy"  value="fillterByDateRange" ${newRegistrationFillterBy == 'fillterByDateRange' ? 'checked' : ''}>
                                            <label class="form-check-label" for="fillterByDateRange">
                                                Date range
                                            </label>
                                            <div class="row mt-3" id="dateRangeInputs">
                                                <div class="col-md-6">
                                                    <label for="startDate">Start Date:</label>
                                                    <input type="date" class="form-control" id="startDate" name="newRegistrationStartDate">
                                                </div>
                                                <div class="col-md-6">
                                                    <label for="endDate">End Date:</label>
                                                    <input type="date" class="form-control" id="endDate" name="newRegistrationEndDate">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="newRegistrationFillterBy" id="newRegistrationFillterBy"  value="allTime" ${newRegistrationFillterBy == 'allTime' ? 'checked' : ''}>
                                            <label class="form-check-label" for="fillterByTheLastYear">
                                                All time
                                            </label>
                                        </div>
                                    </div>        
                                </div> 

                                <!-- End filler by date --> 
                                <br/>
                                <!-- Fillter by status start -->
                                <div class="row">
                                    <div class="col-md-3">
                                        <label class="form-label visually-hidden" for="newRegistrationsFillterByStatus">Select status</label>
                                        <select class="form-select form-select-lg" name="newRegistrationsFillterByStatus" aria-label="Select status">
                                            <option value="all" selected>All status</option>
                                            <option value="0">Cancel</option>
                                            <option value="1">Learning</option>
                                            <option value="1">Expired</option> 
                                            <option value="1">Finished</option> 
                                        </select>                                        
                                    </div>
                                </div>

                                <!-- Fillter by status end -->
                                <br/>
                                <!-- Start choosing field -->
                                <h5 class="mb-3">Display fields: </h5>
                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckDefault" name="newRegistrationDisplayId" ${requestScope.newRegistrationDisplayId == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckDefault">
                                                ID
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckDefault" name="newRegistrationDisplayCustomer" ${requestScope.newRegistrationDisplayCustomer == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckDefault">
                                                Customer
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckDefault" name="newRegistrationDisplayCourseId" ${requestScope.newRegistrationDisplayCourseId == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckDefault">
                                                Course Id
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckDefault" name="newRegistrationDisplayEnrollDate" ${requestScope.newRegistrationDisplayEnrollDate == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckDefault">
                                                Enroll date
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckDefault" name="newRegistrationDisplayExpireDate" ${requestScope.newRegistrationDisplayExpireDate == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckDefault">
                                                Expire package
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckDefault" name="newRegistrationDisplayStatus" ${requestScope.newRegistrationDisplayStatus == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckDefault">
                                                Status
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckDefault" name="newRegistrationDisplayProgress" ${requestScope.newRegistrationDisplayProgress == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckDefault">
                                                Progress
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckDefault" name="newRegistrationDisplayPricePackageId" ${requestScope.newRegistrationDisplayPricePackageId == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckDefault">
                                                Price Package Id
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <!-- End choosing field -->
                                <br/>
                                <input class="btn btn-primary" type="submit" value="Submit" name="newRegistrationSubmit">

                            </form>
                            <br/>
                            <div class="row">
                                <div class="col-12">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr class="highlight">
                                                <c:if test="${requestScope.newRegistrationDisplayId=='true'}"><th scope="col">ID</th></c:if>
                                                <c:if test="${requestScope.newRegistrationDisplayCustomer=='true'}"><th scope="col">Customer</th></c:if>
                                                <c:if test="${requestScope.newRegistrationDisplayCourseId=='true'}"><th scope="col">Course ID</th></c:if>
                                                <c:if test="${requestScope.newRegistrationDisplayEnrollDate=='true'}"><th scope="col">Enroll Date</th></c:if>
                                                <c:if test="${requestScope.newRegistrationDisplayExpireDate=='true'}"><th scope="col">Expire Date</th></c:if>
                                                <c:if test="${requestScope.newRegistrationDisplayStatus=='true'}"><th scope="col">Status</th></c:if>
                                                <c:if test="${requestScope.newRegistrationDisplayProgress=='true'}"><th scope="col">Progress</th></c:if>
                                                <c:if test="${requestScope.newRegistrationDisplayPricePackageId=='true'}"><th scope="col">Price Package Id</th></c:if>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${requestScope.newRegistrationVector}" var="personalCourse">
                                                <tr>
                                                    <c:if test="${requestScope.newRegistrationDisplayId=='true'}"><td>${personalCourse.getId()}</td></c:if>
                                                    <c:if test="${requestScope.newRegistrationDisplayCustomer=='true'}">
                                                        <c:forEach items="${requestScope.userVector}" var="user">
                                                            <c:if test="${personalCourse.getCustomerId()==user.getId()}"><td>${user.getFirstName()} ${user.getLastName()}</td></c:if>
                                                        </c:forEach>
                                                    </c:if>
                                                    <c:if test="${requestScope.newRegistrationDisplayCourseId=='true'}"><td>${personalCourse.getCourseId()}</td></c:if>
                                                    <c:if test="${requestScope.newRegistrationDisplayEnrollDate=='true'}"><td>${personalCourse.getEnrollDate()}</td></c:if>
                                                    <c:if test="${requestScope.newRegistrationDisplayExpireDate=='true'}"><td>${personalCourse.getExpireDate()}</td></c:if>
                                                    <c:if test="${requestScope.newRegistrationDisplayStatus=='true'}">
                                                        <c:choose>
                                                            <c:when test="${personalCourse.getStatus() == 0}">
                                                                <td>Cancel</td>
                                                            </c:when>
                                                            <c:when test="${personalCourse.getStatus() == 1}">
                                                                <td>Learning</td>
                                                            </c:when>
                                                            <c:when test="${personalCourse.getStatus() == 2}">
                                                                <td>Expired</td>
                                                            </c:when>
                                                            <c:when test="${personalCourse.getStatus() == 3}">
                                                                <td>Finished</td>
                                                            </c:when>
                                                        </c:choose>
                                                    </c:if>
                                                    <c:if test="${requestScope.newRegistrationDisplayProgress=='true'}"><td>${personalCourse.getProgress()}</td></c:if>
                                                    <c:if test="${requestScope.newRegistrationDisplayPricePackageId=='true'}"><td>${personalCourse.getPricePackageId()}</td></c:if>
                                                    </tr>

                                            </c:forEach>

                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <!-- Revenue start -->                    
                            <h3 class="fw-bold fs-4 my-3">Revenues</h3>
                            <form action="MarketingDashboardController" method="post">
                                <!-- Start filler by date -->
                                <h5 class="mb-3">Filler by: </h5>
                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="revenueFillterBy" id="revenueFillterBy"  value="fillterByTheLast7Days" ${revenueFillterBy == 'fillterByTheLast7Days' ? 'checked' : ''}>
                                            <label class="form-check-label" for="fillterByTheLast7Days">
                                                The last 7 days
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="revenueFillterBy" id="revenueFillterBy"  value="fillterByThisMonth" ${revenueFillterBy == 'fillterByThisMonth' ? 'checked' : ''}>
                                            <label class="form-check-label" for="fillterByTheLastMonth">
                                                This month
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="revenueFillterBy" id="revenueFillterBy"  value="fillterByThisQuarter" ${revenueFillterBy == 'fillterByThisQuarter' ? 'checked' : ''}>
                                            <label class="form-check-label" for="fillterByTheLastQuarter">
                                                This quarter
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="revenueFillterBy" id="revenueFillterBy"  value="fillterByThisYear" ${revenueFillterBy == 'fillterByThisYear' ? 'checked' : ''}>
                                            <label class="form-check-label" for="fillterByTheLastYear">
                                                This year
                                            </label>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="revenueFillterBy" id="revenueFillterBy"  value="fillterByDateRange" ${revenueFillterBy == 'fillterByDateRange' ? 'checked' : ''}>
                                            <label class="form-check-label" for="fillterByDateRange">
                                                Date range
                                            </label>
                                            <div class="row mt-3" id="dateRangeInputs">
                                                <div class="col-md-6">
                                                    <label for="startDate">Start Date:</label>
                                                    <input type="date" class="form-control" id="startDate" name="revenueStartDate">
                                                </div>
                                                <div class="col-md-6">
                                                    <label for="endDate">End Date:</label>
                                                    <input type="date" class="form-control" id="endDate" name="revenueEndDate">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="revenueFillterBy" id="revenueFillterBy"  value="allTime" ${revenueFillterBy == 'allTime' ? 'checked' : ''}>
                                            <label class="form-check-label" for="fillterByTheLastYear">
                                                All time
                                            </label>
                                        </div>
                                    </div>        
                                </div> 

                                <!-- End filler by date --> 
                                <br/>
                                <input class="btn btn-primary" type="submit" value="Submit" name="revenueSubmit">
                            </form>
                            <br/>
                            <div class="row">
                                <div class="col-12">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr class="highlight">
                                                <th scope="col">Course category</th>
                                                <th scope="col">Total revenues</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            
                                            <c:forEach items="${requestScope.revenueByCourseCategoryIdVector}" var="revenueByCourseCategoryId">
                                                <tr>
                                                    <th scope="col">
                                                        <c:forEach items="${requestScope.categoryVector}" var="setting">
                                                            <c:if test="${revenueByCourseCategoryId.getCourseCategoryId()==setting.getId()}">${setting.getValue()}</c:if>
                                                        </c:forEach>
                                                    </th>
                                                    <th scope="col">
                                                        ${revenueByCourseCategoryId.getRevenue()}
                                                    </th>
                                                    <c:set var="totalRevenue" value="${totalRevenue + revenueByCourseCategoryId.getRevenue()}" />
                                                </tr>
                                            </c:forEach>
                                            <tr>
                                                <th scope="row">All course</th>
                                                <td>${totalRevenue}</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <!-- Revenue end -->


                            <h3 class="fw-bold fs-4 my-3">New Customer</h3>
                            <form action="MarketingDashboardController" method="post"> 
                                <!-- Start filler by date -->
                                <h5 class="mb-3">Filler by: </h5>
                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="newCustomerFillterBy" id="newCustomerFillterBy"  value="fillterByTheLast7Days" ${newCustomerFillterBy == 'fillterByTheLast7Days' ? 'checked' : ''}>
                                            <label class="form-check-label" for="fillterByTheLast7Days">
                                                The last 7 days
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="newCustomerFillterBy" id="newCustomerFillterBy"  value="fillterByThisMonth" ${newCustomerFillterBy == 'fillterByThisMonth' ? 'checked' : ''}>
                                            <label class="form-check-label" for="fillterByTheLastMonth">
                                                This month
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="newCustomerFillterBy" id="newCustomerFillterBy"  value="fillterByThisQuarter" ${newCustomerFillterBy == 'fillterByThisQuarter' ? 'checked' : ''}>
                                            <label class="form-check-label" for="fillterByTheLastQuarter">
                                                This quarter
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="newCustomerFillterBy" id="newCustomerFillterBy"  value="fillterByThisYear" ${newCustomerFillterBy == 'fillterByThisYear' ? 'checked' : ''}>
                                            <label class="form-check-label" for="fillterByTheLastYear">
                                                This year
                                            </label>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="newCustomerFillterBy" id="newCustomerFillterBy"  value="fillterByDateRange" ${newCustomerFillterBy == 'fillterByDateRange' ? 'checked' : ''}>
                                            <label class="form-check-label" for="fillterByDateRange">
                                                Date range
                                            </label>
                                            <div class="row mt-3" id="dateRangeInputs">
                                                <div class="col-md-6">
                                                    <label for="startDate">Start Date:</label>
                                                    <input type="date" class="form-control" id="startDate" name="newCustomerStartDate">
                                                </div>
                                                <div class="col-md-6">
                                                    <label for="endDate">End Date:</label>
                                                    <input type="date" class="form-control" id="endDate" name="newCustomerEndDate">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="newCustomerFillterBy" id="newCustomerFillterBy"  value="allTime" ${newCustomerFillterBy == 'allTime' ? 'checked' : ''}>
                                            <label class="form-check-label" for="fillterByTheLastYear">
                                                All time
                                            </label>
                                        </div>
                                    </div>        
                                </div> 
                                <!-- End filler by date -->  
                                <br/>

                                <div class="col-sm-6 col-md-4 mb-2 mb-sm-0">
                                    <select class="form-select form-select-lg" name="newCustomerFillterByRegistration">
                                        <option value="all" selected="">All</option>
                                        <option value="registered">Registered at least 1 course</option>
                                        <option value="notRegistered">Have not registered any course</option>
                                    </select>
                                </div>
                                <br/>
                                <!-- Fields display start -->
                                <h5 class="mb-3">Display fields: </h5>
                                <div class="row">
                                    <div class="col-md-2">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckDefault" name="newCustomerDisplayId" ${requestScope.newCustomerDisplayId == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckDefault">
                                                ID
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckDefault" name="newCustomerDisplayEmail" ${requestScope.newCustomerDisplayEmail == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckDefault">
                                                Email
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckDefault" name="newCustomerDisplayCreateDate" ${requestScope.newCustomerDisplayCreateDate == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckDefault">
                                                Created date
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckDefault" name="newCustomerDisplayStatus" ${requestScope.newCustomerDisplayStatus == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckDefault">
                                                Status
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckDefault" name="newCustomerDisplayFirstName" ${requestScope.newCustomerDisplayFirstName == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckDefault">
                                                First Name
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckDefault" name="newCustomerDisplayLastName" ${requestScope.newCustomerDisplayLastName == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckDefault">
                                                Last Name
                                            </label>
                                        </div>
                                    </div>

                                </div>
                                <div class="row">
                                    <div class="col-md-2">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckDefault" name="newCustomerDisplayDob" ${requestScope.newCustomerDisplayDob == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckDefault">
                                                Dob
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckDefault" name="newCustomerDisplayGender" ${requestScope.newCustomerDisplayGender == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckDefault">
                                                Gender
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckDefault" name="newCustomerDisplayImageUrl" ${requestScope.newCustomerDisplayImageUrl == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckDefault">
                                                Image 
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="flexCheckDefault" name="newCustomerDisplayPreferContact" ${requestScope.newCustomerDisplayPreferContact == 'true'?'checked':''}>
                                            <label class="form-check-label" for="flexCheckDefault">
                                                Prefer Contact
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <!-- Fields display end -->
                                <br/>
                                <input class="btn btn-primary" type="submit" value="Submit" name="newCustomerSubmit">
                            </form>
                            <br/>
                            <div class="row">
                                <div class="col-12">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr class="highlight">
                                                <c:if test="${requestScope.newCustomerDisplayId=='true'}"><th scope="col">ID</th></c:if>
                                                <c:if test="${requestScope.newCustomerDisplayEmail=='true'}"><th scope="col">Email</th></c:if>
                                                <c:if test="${requestScope.newCustomerDisplayCreateDate=='true'}"><th scope="col">Created date</th></c:if>
                                                <c:if test="${requestScope.newCustomerDisplayStatus=='true'}"><th scope="col">Status</th></c:if>
                                                <c:if test="${requestScope.newCustomerDisplayFirstName=='true'}"><th scope="col">First Name</th></c:if>
                                                <c:if test="${requestScope.newCustomerDisplayLastName=='true'}"><th scope="col">Last Name</th></c:if>
                                                <c:if test="${requestScope.newCustomerDisplayDob=='true'}"><th scope="col">Dob</th></c:if>
                                                <c:if test="${requestScope.newCustomerDisplayGender=='true'}"><th scope="col">Gender</th></c:if>
                                                <c:if test="${requestScope.newCustomerDisplayImageUrl=='true'}"><th scope="col">Image</th></c:if>
                                                <c:if test="${requestScope.newCustomerDisplayPreferContact=='true'}"><th scope="col">Prefer Contact</th></c:if>

                                                </tr>
                                            </thead>
                                            <tbody>

                                            <c:forEach items="${requestScope.newCustomerVector}" var="user">
                                                <tr>
                                                    <c:if test="${requestScope.newCustomerDisplayId=='true'}"><td>${user.getId()}</td></c:if>
                                                    <c:if test="${requestScope.newCustomerDisplayEmail=='true'}"><td>${user.getPrimaryEmail()}</td></c:if>
                                                    <c:if test="${requestScope.newCustomerDisplayCreateDate=='true'}"><td>${user.getCreatedDate()}</td></c:if>
                                                    <c:if test="${requestScope.newCustomerDisplayStatus=='true'}">
                                                        <c:choose>
                                                            <c:when test="${user.getStatus() == 0}">
                                                                <td>Deactivated</td>
                                                            </c:when>
                                                            <c:when test="${user.getStatus() == 1}">
                                                                <td>Activated</td>
                                                            </c:when>                                                           
                                                        </c:choose>
                                                    </c:if>
                                                    <c:if test="${requestScope.newCustomerDisplayFirstName=='true'}"><td>${user.getFirstName()}</td></c:if>        
                                                    <c:if test="${requestScope.newCustomerDisplayLastName=='true'}"><td>${user.getLastName()}</td></c:if> 
                                                    <c:if test="${requestScope.newCustomerDisplayDob=='true'}"><td>${user.getDob()}</td></c:if> 
                                                    <c:if test="${requestScope.newCustomerDisplayGender=='true'}">
                                                        <c:choose>
                                                            <c:when test="${user.getGender() == 1}">
                                                                <td>Male</td>
                                                            </c:when>
                                                            <c:when test="${user.getGender() == 0}">
                                                                <td>Female</td>
                                                            </c:when>                                                           
                                                        </c:choose>
                                                    </c:if>
                                                    <c:if test="${requestScope.newCustomerDisplayImageUrl=='true'}"><td>${user.getImageURL()}</td></c:if>     
                                                    <c:if test="${requestScope.newCustomerDisplayPreferContact=='true'}"><td>${user.getPreferContact()}</td></c:if>
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
    </body>
</html>
