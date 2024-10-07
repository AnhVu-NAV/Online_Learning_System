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
                            <!-- Start fillter by date -->
                            <h5 class="mb-3">Fillter by</h5>
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="fillterBy" id="fillterBy"  value="fillterByTheLast7Days" ${checked == 'fillterByTheLast7Days' ? 'checked' : ''}>
                                        <label class="form-check-label" for="fillterByTheLast7Days">
                                            The last 7 days
                                        </label>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="fillterBy" id="fillterBy"  value="fillterByTheLastMonth" ${checked == 'fillterByTheLastMonth' ? 'checked' : ''}>
                                        <label class="form-check-label" for="fillterByTheLastMonth">
                                            The last month
                                        </label>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="fillterBy" id="fillterBy"  value="fillterByTheLastQuarter" ${checked == 'fillterByTheLastQuarter' ? 'checked' : ''}>
                                        <label class="form-check-label" for="fillterByTheLastQuarter">
                                            The last quarter
                                        </label>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="fillterBy" id="fillterBy"  value="fillterByTheLastYear" ${checked == 'fillterByTheLastYear' ? 'checked' : ''}>
                                        <label class="form-check-label" for="fillterByTheLastYear">
                                            The last year
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="row">

                                <div class="col-md-4">
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="fillterBy" id="fillterBy"  value="fillterByInputDates" ${checked == 'fillterByInputDates' ? 'checked' : ''}>
                                        <label class="form-check-label" for="fillterByInputDates">
                                            Date range
                                        </label>
                                        <div class="row mt-3" id="dateRangeInputs">
                                            <div class="col-md-6">
                                                <label for="startDate">Start Date:</label>
                                                <input type="date" class="form-control" id="startDate" name="startDate">
                                            </div>
                                            <div class="col-md-6">
                                                <label for="endDate">End Date:</label>
                                                <input type="date" class="form-control" id="endDate" name="endDate">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div> 

                            <!-- End fillter by date -->
                            <br/>
                            <div class="row">
                                <div class="col-12 col-md-4 ">
                                    <div class="card border-0">
                                        <div class="card-body py-4">
                                            <h5 class="mb-2 fw-bold">
                                                New Course
                                            </h5>
                                            <div class="mb-0">
                                                <p class="mb-2 fw-bold">
                                                    Total:
                                                </p>
                                                <span class="badge text-success me-2">
                                                    +10
                                                </span>
                                                <span class=" fw-bold">
                                                    For the last 7 day
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-md-4 ">
                                    <div class="card  border-0">
                                        <div class="card-body py-4">
                                            <h5 class="mb-2 fw-bold">
                                                New Registrations
                                            </h5>
                                            <p class="mb-2 fw-bold">
                                                Total:
                                            </p>
                                            <div class="mb-0">
                                                <span class="badge text-success me-2">
                                                    +10
                                                </span>
                                                <span class="fw-bold">
                                                    For the last 7 day
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-md-4 ">
                                    <div class="card border-0">
                                        <div class="card-body py-4">
                                            <h5 class="mb-2 fw-bold">
                                                Revenues
                                            </h5>
                                            <p class="mb-2 fw-bold">
                                                Total:
                                            </p>
                                            <div class="mb-0">
                                                <span class="badge text-success me-2">
                                                    +10.0%
                                                </span>
                                                <span class="fw-bold">
                                                    For the last 7 day
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12 col-md-4 ">
                                    <div class="card border-0">
                                        <div class="card-body py-4">
                                            <h5 class="mb-2 fw-bold">
                                                New Customers
                                            </h5>
                                            <p class="mb-2 fw-bold">
                                                Total:
                                            </p>
                                            <div class="mb-0">
                                                <span class="badge text-success me-2">
                                                    +100
                                                </span>
                                                <span class=" fw-bold">
                                                    For the last 7 day
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <h3 class="fw-bold fs-4 my-3">New Course</h3>
                            <div class="row">
                                <div class="col-md-6">
                                    <!-- Start course fillter -->
                                    <div class="col-sm-6 col-md-4 mb-2 mb-sm-0">
                                        <select class="form-select form-select-lg" name="courseFillter">
                                            <option value="new">New</option>
                                            <option value="all">All</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <!-- Button trigger modal -->
                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                        View pie chart
                                    </button>
                                    <!-- Modal -->
                                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">New course</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <jsp:include page="/jsp/NewCoursePieChart.jsp"></jsp:include>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <!-- End course fillter -->
                            <br/>
                            <!-- Start choosing field  -->
                            <div class="row">
                                <div class="col-md-2">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                        <label class="form-check-label" for="flexCheckDefault">
                                            ID
                                        </label>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked">
                                        <label class="form-check-label" for="flexCheckChecked">
                                            Title
                                        </label>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked">
                                        <label class="form-check-label" for="flexCheckChecked">
                                            Expert
                                        </label>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked">
                                        <label class="form-check-label" for="flexCheckChecked">
                                            Total Duration
                                        </label>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked">
                                        <label class="form-check-label" for="flexCheckChecked">
                                            Category
                                        </label>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked">
                                        <label class="form-check-label" for="flexCheckChecked">
                                            Description
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-2">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked">
                                        <label class="form-check-label" for="flexCheckChecked">
                                            Status
                                        </label>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked">
                                        <label class="form-check-label" for="flexCheckChecked">
                                            Created date
                                        </label>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked">
                                        <label class="form-check-label" for="flexCheckChecked">
                                            Updated date
                                        </label>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked">
                                        <label class="form-check-label" for="flexCheckChecked">
                                            Number of lesson
                                        </label>
                                    </div>
                                </div>                               
                            </div>
                            <!-- End choosing field  -->
                            <br/>
                            <div class="row">
                                <div class="col-12">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr class="highlight">
                                                <th scope="col">ID</th>
                                                <th scope="col">Title</th>
                                                <th scope="col">Expert</th>
                                                <th scope="col">Total Duration</th>
                                                <th scope="col">Category</th>
                                                <th scope="col">Description</th>
                                                <th scope="col">Status</th>
                                                <th scope="col">Created date</th>
                                                <th scope="col">Updated date</th>
                                                <th scope="col">Number of lesson</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <th scope="row">1</th>
                                                <td>Java Programming</td>
                                                <td>Charlie Davis</td>
                                                <td>20 hours</td>
                                                <td>Activated</td>
                                                <td>Java course for beginners</td>
                                                <td>Technology</td>
                                                <td>2023-01-01</td>
                                                <td>2023-01-10</td>
                                                <td>10</td>
                                            </tr>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <h3 class="fw-bold fs-4 my-3">New Registrations</h3>
                            <!-- Start registration fillter -->
                            <div class="col-sm-6 col-md-4 mb-2 mb-sm-0">
                                <select class="form-select form-select-lg" name="registrationFillter">
                                    <option value="1">Success</option>
                                    <option value="0">Cancel</option>
                                    <option value="2">Submitted</option>
                                </select>
                            </div>
                            <!-- End registration fillter -->
                            <br/>
                            <!-- Start choosing field -->
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                        <label class="form-check-label" for="flexCheckDefault">
                                            Customer
                                        </label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                        <label class="form-check-label" for="flexCheckDefault">
                                            Course
                                        </label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                        <label class="form-check-label" for="flexCheckDefault">
                                            Status
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                        <label class="form-check-label" for="flexCheckDefault">
                                            Price package
                                        </label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                        <label class="form-check-label" for="flexCheckDefault">
                                            Enroll date
                                        </label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                        <label class="form-check-label" for="flexCheckDefault">
                                            Expire date
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <!-- End choosing field -->
                            <br/>
                            <div class="row">
                                <div class="col-12">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr class="highlight">
                                                <th scope="col">Customer</th>
                                                <th scope="col">Course</th>
                                                <th scope="col">Status</th>
                                                <th scope="col">Price package</th>
                                                <th scope="col">Enroll date</th>
                                                <th scope="col">Expire date</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <th>John Doe</th>
                                                <td>Java programming</td>
                                                <td>Submitted</td>
                                                <td>Basic package</td>
                                                <td></td>
                                                <td></td>
                                            </tr>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <h3 class="fw-bold fs-4 my-3">Revenues</h3>
                            <!-- Start revenue fillter -->
                            <div class="col-sm-6 col-md-4 mb-2 mb-sm-0">
                                <select class="form-select form-select-lg" name="revenuesFillter">
                                    <option value="total">Total</option>
                                    <option value="byDateRange">By date range</option>
                                </select>
                            </div>
                            <!-- End revenue fillter -->
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
                                            <tr>
                                                <th scope="row">Technology</th>
                                                <td>1000000VND</td>
                                            </tr>
                                            <tr>
                                                <th scope="row">All course</th>
                                                <td>100000000VND</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <h3 class="fw-bold fs-4 my-3">New Customer</h3>
                            <!-- Start customer fillter -->
                            <div class="col-sm-6 col-md-4 mb-2 mb-sm-0">
                                <select class="form-select form-select-lg" name="revenuesCustomer">
                                    <option value="all">All</option>
                                    <option value="registered">Registered at least 1 course</option>
                                    <option value="notRegistered">Have not registered any course</option>
                                </select>
                            </div>
                            <!-- End customer fillter -->
                            <br/>
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="col-md-4">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                            <label class="form-check-label" for="flexCheckDefault">
                                                ID
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                        <label class="form-check-label" for="flexCheckDefault">
                                            Full name
                                        </label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                        <label class="form-check-label" for="flexCheckDefault">
                                            Dob
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                        <label class="form-check-label" for="flexCheckDefault">
                                            Email
                                        </label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                        <label class="form-check-label" for="flexCheckDefault">
                                            Contact
                                        </label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                        <label class="form-check-label" for="flexCheckDefault">
                                            Phone
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <br/>
                            <div class="row">
                                <div class="col-12">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr class="highlight">
                                                <th scope="col">ID</th>
                                                <th scope="col">Full Name</th>
                                                <th scope="col">Dob</th>
                                                <th scope="col">Email</th>
                                                <th scope="col">Contact</th>
                                                <th scope="col">Phone</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <th scope="row">4</th>
                                                <td>John Doe</td>
                                                <td>1999-01-01</td>
                                                <td>customer1@example.com</td>
                                                <td>customer2@example.com</td>
                                                <td>0987654321</td>
                                            </tr>
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
