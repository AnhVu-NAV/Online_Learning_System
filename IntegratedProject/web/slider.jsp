<%-- 
    Document   : index.jsp
    Created on : Sep 19, 2024, 9:52:46 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta content="" name="keywords">
        <meta content="" name="description">
        <link href="img/favicon.ico" rel="icon">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="lib/animate/animate.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
        <div class="container-fluid p-0 mb-5">
            <div class="owl-carousel header-carousel position-relative">
                <c:forEach items="${requestScope.list}" var="s">
                    <div class="owl-carousel-item position-relative" style="width: 100%; height: 600px;">
                        <a href="${s.getBacklink_url()}" target="_blank"/>
                        <img class="img-fluid" src="${s.getImage_url()}">
                        <div class="position-absolute top-0 start-0 w-100 h-100 d-flex align-items-center" style="background: rgba(24, 29, 56, .7);">
                            <div class="container">
                                <div class="row justify-content-start">
                                    <div class="col-sm-10 col-lg-8">
                                        <h5 class="text-primary text-uppercase mb-3 animated slideInDown">Best Online Courses</h5>
                                        <h1 class="display-3 text-white animated slideInDown">The Best Online Learning Platform</h1>
                                        <!--<p class="fs-5 text-white mb-4 pb-2">Vero elitr justo clita lorem. Ipsum dolor at sed stet sit diam no. Kasd rebum ipsum et diam justo clita et kasd rebum sea sanctus eirmod elitr.</p>-->
                                        <a href="${s.getBacklink_url()}" class="btn btn-primary py-md-3 px-md-5 me-3 animated slideInLeft">Read More</a>
                                        <a href="" class="btn btn-light py-md-3 px-md-5 animated slideInRight">Join Now</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <c:if test="${requestScope.slider ne null}">
                    <c:set var="s" value="${requestScope.slider}"/>
                    <div class="owl-carousel-item position-relative" style="width: 100%; height: 600px;">
                        <a href="${s.getBacklink_url()}" target="_blank"/>
                        <img class="img-fluid" src="${s.getImage_url()}">
                        <div class="position-absolute top-0 start-0 w-100 h-100 d-flex align-items-center" style="background: rgba(24, 29, 56, .7);">
                            <div class="container">
                                <div class="row justify-content-start">
                                    <div class="col-sm-10 col-lg-8">
                                        <h5 class="text-primary text-uppercase mb-3 animated slideInDown">Best Online Courses</h5>
                                        <h1 class="display-3 text-white animated slideInDown">The Best Online Learning Platform</h1>
                                        <!--<p class="fs-5 text-white mb-4 pb-2">Vero elitr justo clita lorem. Ipsum dolor at sed stet sit diam no. Kasd rebum ipsum et diam justo clita et kasd rebum sea sanctus eirmod elitr.</p>-->
                                        <a href="${s.getBacklink_url()}" class="btn btn-primary py-md-3 px-md-5 me-3 animated slideInLeft">Read More</a>
                                        <a href="" class="btn btn-light py-md-3 px-md-5 animated slideInRight">Join Now</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
        <!-- Carousel End -->

        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script> 
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/wow/wow.min.js"></script> 
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="js/main.js"></script> 
    </body>
</html>
