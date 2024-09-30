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
        <style>
            .modal-body {
                max-height: 400px;
                overflow-y: auto;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid p-0 mb-5">
            <div class="owl-carousel header-carousel position-relative">
                <c:forEach items="${requestScope.list}" var="s">
                    <div class="owl-carousel-item position-relative" style="width: 100%; height: 600px;">
                        <a href="${s.getBacklinkURL()}" target="_blank"/>
                        <img class="img-fluid" src="${s.getImageURL()}">
                        <div class="position-absolute top-0 start-0 w-100 h-100 d-flex align-items-center" style="background: rgba(24, 29, 56, .7);">
                            <div class="container">
                                <div class="row justify-content-start">
                                    <div class="col-sm-10 col-lg-8">
                                        <h5 class="text-primary text-uppercase mb-3 animated slideInDown">Best Online Courses</h5>
                                        <h1 class="display-3 text-white animated slideInDown">The Best Online Learning Platform</h1>
                                        <a href="${s.getBacklinkURL()}" class="btn btn-primary py-md-3 px-md-5 me-3 animated slideInLeft">Read More</a>
                                        <a href="" class="btn btn-light py-md-3 px-md-5 animated slideInRight">Join Now</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <c:if test="${requestScope.slider ne null}">
                    <c:set var="s" value="${requestScope.slider}"/>
                    <div style="width: 100%; height: 600px; position: relative;">
                        <img class="img-fluid" src="${s.getImageURL()}" style="width: 100%; height: 100%; object-fit: cover;">
                        <div class="position-absolute top-0 start-0 w-100 h-100 d-flex align-items-center" style="background: rgba(24, 29, 56, .7);">
                            <div class="container">
                                <div class="row justify-content-start">
                                    <div class="col-sm-10 col-lg-8">
                                        <h5 class="text-primary text-uppercase mb-3 animated slideInDown">Best Online Courses</h5>
                                        <h1 class="display-3 text-white animated slideInDown">The Best Online Learning Platform</h1>
                                        <!--<p class="fs-5 text-white mb-4 pb-2">Vero elitr justo clita lorem. Ipsum dolor at sed stet sit diam no. Kasd rebum ipsum et diam justo clita et kasd rebum sea sanctus eirmod elitr.</p>-->
                                        <a href="${s.getBacklinkURL()}" class="btn btn-primary py-md-3 px-md-5 me-3 animated slideInLeft">Backlink Url</a>
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
        <c:if test="${requestScope.slider ne null}">
            <c:set var="s" value="${requestScope.slider}"/>
            <table border="0" class="table">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Image Url</th>
                        <th>Back Link Url</th>
                        <th>Status</th>
                        <th>Edit</th>  
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${s.getId()}</td> 
                        <td>${s.getImage_url()}</td>
                        <td>${s.getBacklink_url()}</td>
                        <td>${s.getStatus()}</td>
                        <td><a href="sliderDetail?action=edit&id=${s.getId()}" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#editModal">Edit</a></td>
                    </tr>
                </tbody>
            </table>
        </c:if>
        <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editModalLabel">Edit Slider</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="slider" method="POST">
                            <input type="hidden" name="id" value="${s.getId()}"/>
                            <div class="mb-3">
                                <label for="imageUrl" class="form-label">Image Url:</label>
                                <input type="text" class="form-control" name="imageUrl" placeholder="Image Url..." value="${s.getImage_url()}"/>
                            </div>
                            <div class="mb-3">
                                <label for="backlinkUrl" class="form-label">Back Link Url:</label>
                                <input type="text" class="form-control" name="backlinkUrl" placeholder="Back Link Url..." value="${s.getBacklink_url()}"/>
                            </div>
                            <div class="mb-3">
                                <label for="status" class="form-label">Status:</label>
                                <select name="status" class="form-select">
                                    <option value="1" ${s.getStatus() == 1 ? 'selected' : ''}>Activate</option>
                                    <option value="0" ${s.getStatus() == 0 ? 'selected' : ''}>Inactivate</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script> 
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/wow/wow.min.js"></script> 
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="js/main.js"></script> 
    </body>
</html>
