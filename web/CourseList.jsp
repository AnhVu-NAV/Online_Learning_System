<%-- 
    Document   : CourseList
    Created on : Sep 16, 2024, 12:38:41 PM
    Author     : AnhVuNAV
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Course List</title>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link rel="stylesheet" href="css/courseList.css">
    </head>
    <body>
        <div class="container">
            <!-- Sidebar -->
            <div class="sidebar">
                <div class="search-container">
                    <form action="CourseList" method="get">
                        <div class="Search">
                            <!-- Search Input -->
                            <input type="text" name="search" class="search-input" placeholder="Search Courses..." 
                                   value="${param.search != null ? param.search : ''}">
                            <button type="submit" class="search-button">
                                <i class="fas fa-search"></i>
                            </button>
                        </div>

                        <!-- Course Categories -->
                        <div class="filter">
                            <div class="filter-category">
                                <h3>Course Categories</h3>
                                <ul>
                                    <c:forEach var="category" items="${categories}">
                                        <li>
                                            <input type="checkbox" name="category" value="${category.value}" 
                                                   ${param.category != null && param.category.contains(category.value) ? 'checked' : ''}>
                                            ${category.value}
                                        </li>
                                    </c:forEach>
                                </ul>

                                <!-- Submit button for filters -->
                                <button type="submit" class="filter-btn">Filters</button>
                            </div>
                            <!--                            <div class="filter-taglines">
                                                            <h3>Taglines</h3>
                                                            <ul>
                            <c:forEach var="tagline" items="${taglines}">  Lấy danh sách tagline từ backend 
                                <li>
                                    <input type="checkbox" name="tagline" value="${tagline.name}" 
                                ${param.tagline != null && param.tagline.contains(tagline.name) ? 'checked' : ''}>
                                ${tagline.name}
                            </li>
                            </c:forEach>
                        </ul>
                    </div>-->


                            <!-- Course Display Customization -->
                            <div class="customize-display">
                                <h3>Customize Display Courses</h3>
                                <form id="courseCustomization">
                                    <div class="customization-option">
                                        <h4>Select related course details to display:</h4>
                                        <label>
                                            <input type="checkbox" name="showTitle" checked onchange="toggleRelatedCourseField('title')"> 
                                            Course Title
                                        </label><br>
                                        <label>
                                            <input type="checkbox" name="showSubTitle" checked onchange="toggleRelatedCourseField('title')"> 
                                            Course SubTitle
                                        </label><br>
                                        <label>
                                            <input type="checkbox" name="showTagline" checked onchange="toggleRelatedCourseField('tagline')"> 
                                            Tagline
                                        </label><br>
                                        <label>
                                            <input type="checkbox" name="showPrice" checked onchange="toggleRelatedCourseField('price')"> 
                                            Price
                                        </label><br>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <!-- Main Content -->
            <div class="content">
                <div class="header">
                    <h1>Course List</h1>
                </div>

                <div class="Customization">
                    <!-- Course Per Page Customization -->
                    <div class="customize">
                        <h3>Customize Courses Per Page</h3>
                        <form id="coursePerPageForm" action="CourseList" method="get">
                            <label for="coursesPerPage">Number of courses per page:</label>
                            <select id="coursesPerPage" name="coursesPerPage" onchange="this.form.submit()">
                                <option value="15" ${param.coursesPerPage == '15' ? 'selected' : ''}>15 Courses</option>
                                <option value="25" ${param.coursesPerPage == '25' ? 'selected' : ''}>25 Courses</option>
                                <option value="30" ${param.coursesPerPage == '30' ? 'selected' : ''}>30 Courses</option>
                            </select>

                            <!-- Hidden fields to retain filter parameters -->
                            <input type="hidden" name="search" value="${param.search != null ? param.search : ''}">
                            <c:forEach var="category" items="${paramValues.category}">
                                <input type="hidden" name="category" value="${category}">
                            </c:forEach>
                            <input type="hidden" name="sort" value="${param.sort != null ? param.sort : ''}">
                        </form>
                    </div>
                    <div class="sort">
                        <label for="sortSelect">Sort By:</label>
                        <form action="CourseList" method="get">
                            <input type="hidden" name="search" value="${param.search != null ? param.search : ''}">
                            <c:forEach var="category" items="${paramValues.category}">
                                <input type="hidden" name="category" value="${category}">
                            </c:forEach>

                            <select id="sortSelect" name="sort" onchange="this.form.submit()">
                                <option value="default" ${param.sort == 'default' || param.sort == null ? 'selected' : ''}>Default</option>
                                <option value="latest" ${param.sort == 'latest' ? 'selected' : ''}>Latest</option>
                                <option value="priceLowHigh" ${param.sort == 'priceLowHigh' ? 'selected' : ''}>Price: Low to High</option>
                                <option value="priceHighLow" ${param.sort == 'priceHighLow' ? 'selected' : ''}>Price: High to Low</option>
                            </select>
                        </form>
                    </div>
                </div>

                <!-- Course Grid -->
                <div class="course-grid" id="courseGrid">
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
                            <button class="register-btn">Register</button>
                        </div>
                    </c:forEach>
                </div>


                <!-- Pagination -->
                <div class="pagination">
                    <c:if test="${currentPage > 1}">
                        <form action="CourseList" method="get">
                            <input type="hidden" name="search" value="${param.search != null ? param.search : ''}">
                            <c:forEach var="category" items="${paramValues.category}">
                                <input type="hidden" name="category" value="${category}">
                            </c:forEach>
                            <input type="hidden" name="sort" value="${param.sort != null ? param.sort : ''}">
                            <input type="hidden" name="coursesPerPage" value="${param.coursesPerPage != null ? param.coursesPerPage : '15'}">
                            <button type="submit" class="pagination-btn">Previous</button>
                            <input type="hidden" name="page" value="${currentPage - 1}"/>
                        </form>
                    </c:if>
                    <span id="page-info">Page ${currentPage} of ${totalPages}</span>
                    <c:if test="${currentPage < totalPages}">
                        <form action="CourseList" method="get">
                            <input type="hidden" name="search" value="${param.search != null ? param.search : ''}">
                            <c:forEach var="category" items="${paramValues.category}">
                                <input type="hidden" name="category" value="${category}">
                            </c:forEach>
                            <input type="hidden" name="sort" value="${param.sort != null ? param.sort : ''}">
                            <input type="hidden" name="coursesPerPage" value="${param.coursesPerPage != null ? param.coursesPerPage : '15'}">
                            <button type="submit" class="pagination-btn">Next</button>
                            <input type="hidden" name="page" value="${currentPage + 1}"/>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>

        <script>
            function toggleRelatedCourseField(field) {
                const relatedCourses = document.querySelectorAll('.course-card'); // Chọn tất cả các khóa học
                relatedCourses.forEach(course => {
                    const titleElement = course.querySelector('.course-title');
                    const subtitleElement = course.querySelector('.course-subtitle');
                    const taglineElement = course.querySelector('.course-tagline');
                    const priceElement = course.querySelector('.pricing');

                    // Điều chỉnh hiển thị dựa trên checkbox
                    const showTitle = document.querySelector('input[name="showTitle"]').checked;
                    const showSubTitle = document.querySelector('input[name="showSubTitle"]').checked;
                    const showTagline = document.querySelector('input[name="showTagline"]').checked;
                    const showPrice = document.querySelector('input[name="showPrice"]').checked;

                    titleElement.style.display = showTitle ? 'block' : 'none';
                    subtitleElement.style.display = showSubTitle ? 'block' : 'none';
                    taglineElement.style.display = showTagline ? 'block' : 'none';
                    priceElement.style.display = showPrice ? 'block' : 'none';
                });
            }

            // Gọi hàm toggle khi trang được tải
            document.addEventListener("DOMContentLoaded", function () {
                toggleRelatedCourseField(); // Khởi tạo hiển thị ban đầu
            });
        </script>
    </body>
</html>
