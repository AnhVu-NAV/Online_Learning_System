<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home Page</title>
        <link href="./css/bootstrap.min.css" rel="stylesheet">
        <link href="./css/home.css" rel="stylesheet">
        <link rel="stylesheet" href="./fontawesome/css/all.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap" rel="stylesheet">
        <link href="./css/templatemo-learnik-blog.css" rel="stylesheet">
    </head>
    <body>
        <!-- Header -->
        <header>
            <%@ include file="component/Header.jsp" %>
        </header>

        <!-- Main Content with Sidebar -->
        <div class="container mt-5">
            <div class="row">

                <!-- Slider Section (Temporary static slider) -->
                <!-- Static Bootstrap Carousel -->
                <div class="col-md-8">
                    <!-- Slider Section (Temporary static slider) -->
                    <div id="carouselExampleIndicators" class="carousel slide mb-5" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                        </ol>
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img class="d-block w-100" src="img/img-01.jpg" alt="First slide">
                                <div class="carousel-caption d-none d-md-block">
                                    <h5>First Slide Title</h5>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <img class="d-block w-100" src="img/img-02.jpg" alt="Second slide">
                                <div class="carousel-caption d-none d-md-block">
                                    <h5>Second Slide Title</h5>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <img class="d-block w-100" src="img/img-03.jpg" alt="Third slide">
                                <div class="carousel-caption d-none d-md-block">
                                    <h5>Third Slide Title</h5>
                                </div>
                            </div>
                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
                <!-- Slider Section -->
                <!--    <div class="container mt-4">
                        <div id="slider" class="carousel slide" data-ride="carousel">
                            <div class="carousel-inner">
                <c:forEach var="slide" items="${sliderList}">
                    <div class="carousel-item ${slide.index == 0 ? 'active' : ''}">
                        <a href="${slide.link}">
                            <img src="${slide.imageUrl}" class="d-block w-100" alt="${slide.title}">
                            <div class="carousel-caption d-none d-md-block">
                                <h5>${slide.title}</h5>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
            <a class="carousel-control-prev" href="#slider" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            </a>
            <a class="carousel-control-next" href="#slider" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
            </a>
            </div>
            </div>-->

                <!-- Sidebar (Latest Blogs, Show/Hide Fields, Contact Us, Useful Links) -->
                <div class="col-md-4">
                    <!-- Latest Blogs Section -->
                    <h3>Latest Blogs</h3>
                    <ul>
                        <c:forEach var="blog" items="${blogList}" begin="0" end="2">
                            <li><a href="blogDetails?id=${blog.id}">${blog.title}</a></li>
                            </c:forEach>
                    </ul>

                    <!-- Show/Hide Course Fields -->
                    <h3>Show/Hide Course Fields</h3>
                    <form action="applyCourseFields" method="post" class="form-inline">
                        <label class="mr-3"><input type="radio" name="fields" value="title"> Title</label>
                        <label class="mr-3"><input type="radio" name="fields" value="tagline"> Tagline</label>
                        <label class="mr-3"><input type="radio" name="fields" value="thumbnail"> Thumbnail</label>
                        <button type="submit" class="btn btn-primary ml-3">Apply</button>
                    </form>

                    <!-- Contact Us -->
                    <h3>Contact Us</h3>
                    <p>Email: contact@yourwebsite.com</p>
                    <p>Phone: +123456789</p>

                    <!-- Useful Links -->
                    <h3>Useful Links</h3>
                    <ul>
                        <li><a href="#">Privacy Policy</a></li>
                        <li><a href="#">Terms of Service</a></li>
                    </ul>
                </div>
            </div>

            <!-- Hot Blogs Section -->
            <div class="row mt-5">
                <div class="col-md-12">
                    <h2>Hot Blogs</h2>
                    <form action="filterBlogs" method="post" class="form-inline mb-4">
                        <select name="filter" class="form-control mr-3" onchange="this.form.submit()">
                            <option value="hot" ${param.filter == 'hot' ? 'selected' : ''}>Hot Blogs</option>
                            <option value="newest" ${param.filter == 'newest' ? 'selected' : ''}>Newest</option>
                            <option value="mostViewed" ${param.filter == 'mostViewed' ? 'selected' : ''}>Most Viewed</option>
                        </select>
                    </form>

                    <!-- Sử dụng BlogItem.jsp để hiển thị mỗi bài viết (blog) -->
                    <div class="row">
                        <c:forEach var="blog" items="${blogList}">
                            <div class="col-md-6 mb-4">
                                <jsp:include page="component/BlogItem.jsp" />
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>


            <!-- Courses Section -->
            <div class="row mt-5">
                <div class="col-md-12">
                    <h2>Courses</h2>
                    <form action="filterCourses" method="post" class="form-inline mb-4">
                        <select name="filter" class="form-control mr-3" onchange="this.form.submit()">
                            <option value="newest" ${param.filter == 'newest' ? 'selected' : ''}>Newest</option>
                            <option value="popular" ${param.filter == 'popular' ? 'selected' : ''}>Most Popular</option>
                            <option value="hotSale" ${param.filter == 'hotSale' ? 'selected' : ''}>Hot Sale</option>
                        </select>
                    </form>

                    <!-- Sử dụng CourseItem.jsp để hiển thị mỗi khóa học (course) -->
                    <div class="row">
                        <%--<c:forEach var="course" items="${courseList}">--%>
                        <div class="col-md-6 mb-4">
                            <jsp:include page="component/CourseItem.jsp" />
                        </div>
                        <%--</c:forEach>--%>
                    </div>
                </div>
            </div>
        

        <!-- Pagination -->
        <div class="pagination mt-5">
            <form action="setPageSize" method="post" class="form-inline mb-4">
                <label for="pageSize" class="mr-3">Records per page:</label>
                <select name="pageSize" class="form-control" onchange="this.form.submit()">
                    <option value="5">5</option>
                    <option value="10">10</option>
                    <option value="20">20</option>
                </select>
            </form>

            <div class="pagination">
                <c:forEach var="pageNum" items="${pagination.pageNumbers}">
                    <a href="home?page=${pageNum}" class="btn btn-secondary mr-2">${pageNum}</a>
                </c:forEach>
            </div>
        </div>
</div>



        <!-- Footer -->
        <footer class="mt-5">
            <%@ include file="component/Footer.jsp" %>
        </footer>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
