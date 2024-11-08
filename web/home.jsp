<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home Page</title>
        <link href="./css/bootstrap.min.css" rel="stylesheet">
        <link href="./css/home.css" rel="stylesheet">
        <link rel="stylesheet" href="./fontawesome/css/all.min.css">
    </head>
    <body>

        <!-- Header -->
        <header>
            <%@ include file="component/Header.jsp" %>
        </header>

        <div class="container mt-5">
            <div class="row">
                <!-- Slider Section -->
                <div class="col-md-8">
                    <div id="carouselExampleIndicators" class="carousel slide mb-5" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                        </ol>
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <a href="CourseDetail?courseId=11">
                                <img class="d-block w-100" src="img/grammar_basics_thumbnail1.avif" alt="First slide">
                                <div class="carousel-caption d-none d-md-block">
                                    <h5>Grammar Basics</h5>
                                </div>
                                </a>
                            </div>
                            <div class="carousel-item">
                                <a href="CourseDetail?courseId=13">
                                <img class="d-block w-100" src="img/conversation_practice_thumbnail1.avif" alt="Second slide">
                                <div class="carousel-caption d-none d-md-block">
                                    <h5>Conversation Practive</h5>
                                </div>
                                </a>
                            </div>
                            <div class="carousel-item">
                                <a href="CourseDetail?courseId=23">
                                <img class="d-block w-100" src="img/conversation_beginners_thumbnail1.jpg" alt="Third slide">
                                <div class="carousel-caption d-none d-md-block">
                                    <h5>Conversation Beginners</h5>
                                </div>
                                </a>
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

                <!-- Sidebar Section -->
                <div class="col-md-4 latest-blogs-container">
                    <h3>Latest Blogs</h3>
                    <ul>
                        <c:forEach var="blog" items="${blogList}" begin="0" end="2">
                            <li><a href="BlogDetails?id=${blog.blogId}">${blog.title}</a></li>
                            </c:forEach>
                    </ul>
                </div>
            </div>

            <!-- Hot Blogs Section -->
            <div class="row mt-5">
                <div class="col-md-12">
                    <h2 class="titleModul">Hot Blogs</h2>
                    <div class="row align-items-center">
                        <!-- Filter dropdown -->
                        <div class="col-md-4">
                            <form action="filterBlogs" method="post" class="form-inline">
                                <select name="filter" class="form-control mr-3" onchange="this.form.submit()">
                                    <option value="" ${param.filter == '' ? 'selected' : ''}>No Filter</option>
                                    <option value="hot" ${param.filter == 'hot' ? 'selected' : ''}>Hot Blogs</option>
                                    <option value="newest" ${param.filter == 'newest' ? 'selected' : ''}>Newest</option>
                                    <option value="mostViewed" ${param.filter == 'mostViewed' ? 'selected' : ''}>Most Viewed</option>
                                </select>
                            </form>
                        </div>

                        <!-- View All Button -->
                        <div class="col-md-8 text-right view-all-container">
                            <a href="${pageContext.request.contextPath}/blogList" class="btn btn-primary">View All</a>
                        </div>
                    </div>

                    <!-- Blog List -->
                    <div class="row mt-4">
                        <c:forEach var="blog" items="${blogList}">
                            <article class="col-12 col-md-6 tm-post">
                                <hr class="tm-hr-primary">
                                <a href="blogDetail?blogId=${blog.blogId}" class="effect-lily tm-post-link tm-pt-60">
                                    <div class="tm-post-link-inner">
                                        <img src="img/${blog.thumbnailUrl}" alt="${blog.title}" class="img-fluid">
                                    </div>
                                    <h2 class="tm-pt-30 tm-color-primary tm-post-title">${blog.title}</h2>
                                </a>
                                <p class="tm-pt-30">${blog.briefInfo}</p>
                                <div class="d-flex justify-content-between tm-pt-45">
                                    <span class="tm-color-primary">${blog.category}</span>
                                    <span class="tm-color-primary">${fn:substring(blog.updatedDate.toString(), 0, 10)}</span>
                                </div>
                                <hr>
                            </article>
                        </c:forEach>
                    </div>
                </div>
                <div class="paginationContainer">
                    <!-- Pagination -->
                    <div class="pagination mt-3">
                        <c:forEach var="pageNum" begin="1" end="${noOfPages}">
                            <a href="home?page=${pageNum}&pageSize=${recordsPerPage}" class="btn btn-secondary mr-2 ${currentPage == pageNum ? 'active' : ''}">
                                ${pageNum}
                            </a>
                        </c:forEach>
                    </div>
                    <!-- Pagination dropdown -->
                    <div class="col-md-9 ">
                        <form action="home" method="get" class="form-inline">
                            <input type="number" name="pageSize" class="form-control mr-3" min="1" max="100" value="${recordsPerPage}" onchange="this.form.submit()">
                            <input type="hidden" name="page" value="${currentPage}">
                        </form>
                    </div>
                </div>
            </div>

            <div class="row mt-5">
                <div class="col-md-12">
                    <h2 class="titleModul">Hot Course</h2>
                    <!-- Course Grid Section -->
                    <div class="course-grid mt-4" id="courseGrid">
                        <c:forEach var="course" items="${courses}">
                            <div class="course-card">
                                <a href="CourseDetail?courseId=${course.id}">
                                    <c:if test="${course.thumbnailUrls != null && !course.thumbnailUrls.isEmpty()}">
                                        <img src="${course.thumbnailUrls[0]}" class="thumbnail" alt="${course.title}">
                                    </c:if>
                                    <c:if test="${course.thumbnailUrls == null || course.thumbnailUrls.isEmpty()}">
                                        <img src="default-thumbnail.jpg" class="thumbnail" alt="${course.title}">
                                    </c:if>
                                    <div class="course-info">
                                        <h3 class="course-title">${course.title}</h3>
                                        <p class="course-subtitle">${course.subtitle}</p>
                                        <div class="course-tagline">
                                            <c:if test="${course.taglines != null && !course.taglines.isEmpty()}">
                                                <c:forEach var="tagline" items="${course.taglines}">
                                                    <div class="tag">
                                                        <i class="fas fa-tag"></i>
                                                        <span class="tagline">${tagline}</span>
                                                    </div>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${course.taglines == null || course.taglines.isEmpty()}">
                                                <p>No taglines available.</p>
                                            </c:if>
                                        </div>
                                        <div class="pricing">
                                            <span class="list-price">$${course.price}</span>
                                            <span class="sale-price">$${course.salePrice}</span>
                                        </div>
                                    </div>
                                </a>
                                <!--                                <button class="register-btn">Register</button>-->
                            </div>
                        </c:forEach>
                    </div>
                    <!-- Show/Hide Course Fields -->
                    <h3 class="mt-5">Show/Hide Course Fields</h3>
                    <form class="form-inline mb-4">
                        <label class="mr-3">
                            <input type="checkbox" id="toggleTitle" checked> Title
                        </label>
                        <label class="mr-3">
                            <input type="checkbox" id="toggleTagline" checked> Tagline
                        </label>
                        <label class="mr-3">
                            <input type="checkbox" id="toggleThumbnail" checked> Thumbnail
                        </label>
                    </form>
                </div>
            </div>
        </div>
        <!-- Footer -->
        <footer class="mt-5">
            <%@ include file="component/Footer.jsp" %>
        </footer>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>

        <script>

                                // Show/Hide Course Fields Toggle Script
                                const toggleTitle = document.getElementById('toggleTitle');
                                const toggleTagline = document.getElementById('toggleTagline');
                                const toggleThumbnail = document.getElementById('toggleThumbnail');

                                toggleTitle.addEventListener('change', () => {
                                    document.querySelectorAll('.course-title').forEach(item => item.style.display = toggleTitle.checked ? 'block' : 'none');
                                });
                                toggleTagline.addEventListener('change', () => {
                                    document.querySelectorAll('.course-tagline').forEach(item => item.style.display = toggleTagline.checked ? 'block' : 'none');
                                });
                                toggleThumbnail.addEventListener('change', () => {
                                    document.querySelectorAll('.thumbnail').forEach(item => item.style.display = toggleThumbnail.checked ? 'block' : 'none');
                                });
        </script>

    </body>
</html>
