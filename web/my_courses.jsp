<%-- 
    Document   : my_courses
    Created on : Sep 24, 2024, 9:59:30 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="model.*" %>
<%@page import="dal.*" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta content="" name="keywords">
        <meta content="" name="description">
        <link href="img/favicon.ico" rel="icon">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
        <link href="css/bootstrap1.min.css" rel="stylesheet">
        <link href="css/style1.css" rel="stylesheet">
    </head>
    <body>
        <!-- Spinner Start -->
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->

        <!-- Navbar Start -->
        <nav class="navbar navbar-expand-lg bg-white navbar-light shadow sticky-top p-0">
            <a href="#" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
                <h2 class="m-0 text-primary"><i class="fa fa-book me-3"></i>LEARNIK</h2>
            </a>
            <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <div class="navbar-nav ms-auto p-4 p-lg-0">
                    <a href="#" class="nav-item nav-link">Home</a>
                    <a href="#" class="nav-item nav-link active">Courses</a>
                    <!-- <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Pages</a>
                        <div class="dropdown-menu fade-down m-0">
                            <a href="team.html" class="dropdown-item">Our Team</a>
                            <a href="testimonial.html" class="dropdown-item">Testimonial</a>
                            <a href="404.html" class="dropdown-item">404 Page</a>
                        </div>
                    </div> -->
                    <a href="#" class="nav-item nav-link">Notification</a>
                    <a href="#" class="nav-item nav-link">My Profile</a>
                </div>
            </div>
        </nav>
        <!-- Navbar End -->


        <!-- Header Start -->
        <div class="container-fluid bg-primary py-5 mb-5 page-header">
            <div class="container py-5">
                <div class="row justify-content-center">
                    <div class="col-lg-10 text-center">
                        <h1 class="display-3 text-white animated slideInDown">My Courses</h1>
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb justify-content-center">
                                <li class="breadcrumb-item"><a class="text-white" href="#">Home</a></li>
                                <li class="breadcrumb-item text-white active" aria-current="page">Courses</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
        <!-- Header End -->

        <!-- Search -->
        <div class="container-fluid align-content-sm-center d-flex justify-content-end">
            <!-- Lựa chọn In-progress và Completed -->
            <div class="d-md-inline-flex me-auto">
                <a href="myCourse?view=in_progress"><h4 class="selection">In-Progress</h4></a> &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="myCourse?view=completed"><h4 class="selection">Completed</h4></a> &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="myCourse?action=list"><h4 class="selection">All Courses</h4></a>
            </div>
            <form action="myCourse" method="POST" class="d-flex" style="max-width: 600px; width: 100%;">
                <input type="text" name="result" class="form-control me-2" placeholder="Search..." aria-label="Search" style="border-radius: 30px;"/>
                <button type="submit" class="btn btn-primary" style="border-radius: 30px;">SEARCH</button>
            </form>
        </div>
        <c:if test="${requestScope.error ne null}">
            <p style="color: red">${requestScope.error}</p>
        </c:if>

        <!-- Courses Start -->
        <c:if test="${requestScope.in_progress ne null}">
            <div class="container-xxl py-5">
                <div class="container">
                    <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                        <h6 class="section-title bg-white text-center text-primary px-3">Courses</h6>
                        <h1 class="mb-5">In Progress</h1>
                    </div>
                    <!--Course-->
                    <div class="row g-4 justify-content-center">
                        <c:forEach items="${requestScope.in_progress}" var="i">
                            <div class="col-lg-4 wow fadeInUp" data-wow-delay="0.1s">
                                <div class="course-item bg-light">
                                    <div class="position-relative overflow-hidden">
                                        <img class="img-fluid" src="img/course-1.jpg" alt="">
                                        <!--<img class="img-fluid" src="{i.getValue().getThumbnailUrl()}" alt="">-->
                                        <div class="w-100 d-flex justify-content-center position-absolute bottom-0 start-0 mb-4">
                                            <a href="#" class="flex-shrink-0 btn btn-sm btn-primary px-3 border-end" style="border-radius: 30px 30px 30px 30px;">Study Now</a>
                                        </div>
                                    </div>
                                    <div class="text-center p-4 pb-0">
                                        <div class="mb-3">
                                            <small class="fa fa-star text-primary"></small>
                                            <small class="fa fa-star text-primary"></small>
                                            <small class="fa fa-star text-primary"></small>
                                            <small class="fa fa-star text-primary"></small>
                                            <small class="fa fa-star text-primary"></small>
                                        </div>
                                        <h5 class="mb-4">${i.getKey().getTitle()}</h5>
                                        <p class="mb-3">${i.getKey().getDescription()}</p> 
                                    </div>
                                    <div class="d-flex border-top">
                                        <small class="flex-fill text-center border-end py-2"><i class="fa fa-user-tie text-primary me-2"></i>
                                            Progress: ${i.getValue()}%</small>
                                        <small class="flex-fill text-center border-end py-2"><i class="fa fa-clock text-primary me-2"></i>${i.getKey().getTotalDuration()} hours</small>
                                        <small class="flex-fill text-center py-2"><i class="fa fa-user text-primary me-2"></i>${i.getKey().getNumberOfLesson()} lessons</small>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <!--Course End-->
                </div>
            </div>
        </c:if>
        <!-- In Progress Courses End -->

        <!--Completed Courses-->
        <c:if test="${requestScope.completed ne null}">
            <div class="container-xxl py-5">
                <div class="container">
                    <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                        <h6 class="section-title bg-white text-center text-primary px-3">Courses</h6>
                        <h1 class="mb-5">Completed</h1>
                    </div>
                    <!--Course-->
                    <div class="row g-4 justify-content-center">
                        <c:forEach items="${requestScope.completed}" var="i">
                            <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                                <div class="course-item bg-light">
                                    <div class="position-relative overflow-hidden">
                                        <img class="img-fluid" src="img/course-1.jpg" alt="">
                                        <!--<img class="img-fluid" src="{i.getValue().getThumbnailUrl()}" alt="">-->
                                        <div class="w-100 d-flex justify-content-center position-absolute bottom-0 start-0 mb-4">
                                            <a href="#" class="flex-shrink-0 btn btn-sm btn-primary px-3 border-end" style="border-radius: 30px 30px 30px 30px;">View Detail</a>
                                        </div>
                                    </div>
                                    <div class="text-center p-4 pb-0">
                                        <div class="mb-3">
                                            <small class="fa fa-star text-primary"></small>
                                            <small class="fa fa-star text-primary"></small>
                                            <small class="fa fa-star text-primary"></small>
                                            <small class="fa fa-star text-primary"></small>
                                            <small class="fa fa-star text-primary"></small>
                                        </div>
                                        <h5 class="mb-4">${i.getTitle()}</h5>
                                        <p class="mb-3">${i.getDescription()}</p>
                                    </div>
                                    <div class="d-flex border-top">
                                        <small class="flex-fill text-center border-end py-2"><i class="fa fa-user-tie text-primary me-2"></i>Progress: 100%</small>
                                        <small class="flex-fill text-center border-end py-2"><i class="fa fa-clock text-primary me-2"></i>${i.getTotalDuration()} hours</small>
                                        <small class="flex-fill text-center py-2"><i class="fa fa-user text-primary me-2"></i>${i.getNumberOfLesson()} lessons</small>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <!--Course End-->
                </div>
            </div>  
        </c:if>

        <c:if test="${requestScope.course ne null}">
            <div class="container-xxl py-5">
                <div class="container">
                    <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                        <h6 class="section-title bg-white text-center text-primary px-3">Courses</h6>
                        <h1 class="mb-5">Course</h1>
                    </div>
                    <!--Course-->
                    <div class="row g-4 justify-content-center">
                        <c:forEach items="${requestScope.course}" var="i">
                            <div class="col-lg-4 wow fadeInUp" data-wow-delay="0.1s">
                                <div class="course-item bg-light">
                                    <div class="position-relative overflow-hidden">
                                        <img class="img-fluid" src="img/course-1.jpg" alt="">
                                        <!--<img class="img-fluid" src="{i.getValue().getThumbnailUrl()}" alt="">-->
                                        <div class="w-100 d-flex justify-content-center position-absolute bottom-0 start-0 mb-4">
                                            <a href="#" class="flex-shrink-0 btn btn-sm btn-primary px-3 border-end" style="border-radius: 30px 30px 30px 30px;">Study Now</a>
                                        </div>
                                    </div>
                                    <div class="text-center p-4 pb-0">
                                        <div class="mb-3">
                                            <small class="fa fa-star text-primary"></small>
                                            <small class="fa fa-star text-primary"></small>
                                            <small class="fa fa-star text-primary"></small>
                                            <small class="fa fa-star text-primary"></small>
                                            <small class="fa fa-star text-primary"></small>
                                        </div>
                                        <h5 class="mb-4">${i.getKey().getTitle()}</h5>
                                        <p class="mb-3">${i.getKey().getDescription()}</p> 
                                    </div>
                                    <div class="d-flex border-top">
                                        <small class="flex-fill text-center border-end py-2"><i class="fa fa-user-tie text-primary me-2"></i>
                                            Progress: ${i.getValue()}%</small>
                                        <small class="flex-fill text-center border-end py-2"><i class="fa fa-clock text-primary me-2"></i>${i.getKey().getTotalDuration()} hours</small>
                                        <small class="flex-fill text-center py-2"><i class="fa fa-user text-primary me-2"></i>${i.getKey().getNumberOfLesson()} lessons</small>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <!--Course End-->
                </div>
            </div>
        </c:if>

        <!-- Footer Start -->
        <div class="container-fluid bg-dark text-light footer pt-5 mt-5 wow fadeIn" data-wow-delay="0.1s">
            <div class="container py-5">
                <div class="row g-5">
                    <div class="col-lg-3 col-md-6">
                        <h4 class="text-white mb-3">Quick Link</h4>
                        <a class="btn btn-link" href="">About Us</a>
                        <a class="btn btn-link" href="">Contact Us</a>
                        <a class="btn btn-link" href="">Privacy Policy</a>
                        <a class="btn btn-link" href="">Terms & Condition</a>
                        <a class="btn btn-link" href="">FAQs & Help</a>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <h4 class="text-white mb-3">Contact</h4>
                        <p class="mb-2"><i class="fa fa-map-marker-alt me-3"></i>123 Street, New York, USA</p>
                        <p class="mb-2"><i class="fa fa-phone-alt me-3"></i>+012 345 67890</p>
                        <p class="mb-2"><i class="fa fa-envelope me-3"></i>info@example.com</p>
                        <div class="d-flex pt-2">
                            <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-twitter"></i></a>
                            <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-facebook-f"></i></a>
                            <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-youtube"></i></a>
                            <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-linkedin-in"></i></a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="copyright">
                    <div class="row">
                        <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
                            &copy; <a class="border-bottom" href="#">LEARNIK</a>, All Right Reserved.

                            <!--/*** This template is free as long as you keep the footer author’s credit link/attribution link/backlink. If you'd like to use the template without the footer author’s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
                            Designed By <a class="border-bottom" href="https://htmlcodex.com">HTML Codex</a>
                        </div>
                        <div class="col-md-6 text-center text-md-end">
                            <div class="footer-menu">
                                <a href="">Home</a>
                                <a href="">Cookies</a>
                                <a href="">Help</a>
                                <a href="">FQAs</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer End -->

        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>

        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/main1.js"></script>
    </body>
</html>
