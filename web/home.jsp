<%-- 
    Document   : home
    Created on : Sep 21, 2024, 12:32:29 AM
    Author     : mocun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="icon" href="./favicon.ico">
        <!-- Bootstrap core CSS -->
        <link href="./css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="./css/home.css" rel="stylesheet">


        <link rel="stylesheet" href="./fontawesome/css/all.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap" rel="stylesheet">
        <link href="./css/templatemo-learnik-blog.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    </head>
    <body>
        <!--//Header-->
        <header>
            <%@ include file="component/Header.jsp" %>
        </header>

        <!--//Slider-->
        <div class="slider">
            <c:forEach var="slide" items="${sliderList}">
                <div class="slide">
                    <a href="${slide.link}">
                        <img src="${slide.imageUrl}" alt="${slide.title}">
                        <h2>${slide.title}</h2>
                    </a>
                </div>
            </c:forEach>
        </div>

        <!-- Posts -->
        <div id="posts">
            <h2>Hot Posts</h2>
            <form action="filterPosts" method="post">
                <select name="filter" onchange="this.form.submit()">
                    <option value="hot" ${param.filter == 'hot' ? 'selected' : ''}>Hot Posts</option>
                    <option value="newest" ${param.filter == 'newest' ? 'selected' : ''}>Newest</option>
                    <option value="mostViewed" ${param.filter == 'mostViewed' ? 'selected' : ''}>Most Viewed</option>
                </select>
            </form>

            <c:forEach var="post" items="${postList}">
                <div class="post-item">
                    <a href="postDetails?id=${post.id}">
                        <img src="${post.thumbnailUrl}" alt="${post.title}">
                        <h3>${post.title}</h3>
                        <span>${post.publishedDate}</span>
                    </a>
                </div>
            </c:forEach>
        </div>


        <div>
            <%--<%@ include file="component/BlogItem2.jsp" %>--%>
        </div>

        <!-- Courses -->
        <div id="courses">
            <h2>Courses</h2>
            <form action="filterCourses" method="post">
                <select name="filter" onchange="this.form.submit()">
                    <option value="newest" ${param.filter == 'newest' ? 'selected' : ''}>Newest</option>
                    <option value="popular" ${param.filter == 'popular' ? 'selected' : ''}>Most Popular</option>
                    <option value="hotSale" ${param.filter == 'hotSale' ? 'selected' : ''}>Hot Sale</option>
                </select>
            </form>

            <c:forEach var="course" items="${courseList}">
                <div class="course-item">
                    <a href="courseDetails?id=${course.id}">
                        <img src="${course.thumbnailUrl}" alt="${course.title}">
                        <h3>${course.title}</h3>
                        <p>${course.tagline}</p>
                    </a>
                </div>
            </c:forEach>
        </div>



        <!--//Filter search course-->
        <form action="applyCourseFields" method="post">
            <label><input type="radio" name="fields" value="title"> Title</label>
            <label><input type="radio" name="fields" value="tagline"> Tagline</label>
            <label><input type="radio" name="fields" value="thumbnail"> Thumbnail</label>
            <button type="submit">Apply</button>
        </form>



        <!--Pagination-->
        <div class="pagination">
            <form action="setPageSize" method="post">
                <label>Records per page:</label>
                <select name="pageSize" onchange="this.form.submit()">
                    <option value="5">5</option>
                    <option value="10">10</option>
                    <option value="20">20</option>
                </select>
            </form>
            <c:forEach var="pageNum" items="${pagination.pageNumbers}">
                <a href="home?page=${pageNum}">${pageNum}</a>
            </c:forEach>
        </div>



        <!--Sidebar & New post-->
        <div class="sidebar">
            <h3>Latest Posts</h3>
            <ul>
                <c:forEach var="post" items="${latestPosts}">
                    <li><a href="${post.link}">${post.title}</a></li>
                    </c:forEach>
            </ul>

            <h3>Contact Us</h3>
            <p>Email: contact@yourwebsite.com</p>
            <p>Phone: +123456789</p>

            <h3>Useful Links</h3>
            <ul>
                <li><a href="#">Privacy Policy</a></li>
                <li><a href="#">Terms of Service</a></li>
            </ul>
        </div>


        <main role="main">
            <section class="jumbotron text-center">
                <div class="container">
                    <h1 class="jumbotron-heading">Album example</h1>
                    <p class="lead text-muted">Something short and leading about the collection below—its contents, the creator, etc. Make it short and sweet, but not too short so folks don't simply skip over it entirely.</p>
                    <p>
                        <a href="#" class="btn btn-primary my-2">Main call to action</a>
                        <a href="#" class="btn btn-secondary my-2">Secondary action</a>
                    </p>
                </div>
            </section>

            <div class="album py-5 bg-light">
                <div class="container">

                    <div class="row">
                        <label>Courses</label>
                        <div class="col-md-12">
                            <%@ include file="./component/CardItem.jsp" %>
                        </div>

                        <div class="col-md-6">
                            <div class="card mb-6 box-shadow">
                                <jsp:include page="./component/BlogItem.jsp" />
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="card mb-6 box-shadow">
                                <jsp:include page="./component/BlogItem.jsp" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </main>

        <footer>
            <%@ include file="./component/Footer.jsp" %>
        </footer>

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script>window.jQuery || document.write('<script src="../../../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
        <script src="../../../../assets/js/vendor/popper.min.js"></script>
        <script src="../../../../dist/js/bootstrap.min.js"></script>
        <script src="../../../../assets/js/vendor/holder.min.js"></script>
    </body>
</html>
