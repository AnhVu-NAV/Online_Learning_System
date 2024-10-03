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
                        <!-- Search Input -->
                        <input type="text" name="search" class="search-input" placeholder="Search Courses..." 
                               value="${param.search != null ? param.search : ''}">
                        <button type="submit" class="search-button">
                            <i class="fas fa-search"></i>
                        </button>

                        <!-- Course Categories -->
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

                        <!-- Submit button for filters (if needed) -->
                        <button type="submit" class="filter-btn" style="">Filters</button>
                    </form>
                </div>
            </div>

            <!-- Main Content -->
            <div class="content">
                <div class="header">
                    <h2>We found ${courses.size()} courses for you</h2>
                    <div class="sort">
                        <label for="sortSelect">Sort By:</label>
                        <form action="CourseList" method="get">
                            <!-- Giữ lại từ khóa tìm kiếm và danh mục đã chọn trong form -->
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
                                <img src="${course.thumbnailUrl}" class="thumbnail" alt="${course.title}">
                                <div class="course-info">
                                    <h3>${course.title}</h3>
                                    <p>${course.description}</p>
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
                        <a href="CourseList?page=${currentPage - 1}" class="pagination-btn">Previous</a>
                    </c:if>
                    <span id="page-info">Page ${currentPage} of ${totalPages}</span>
                    <c:if test="${currentPage < totalPages}">
                        <a href="CourseList?page=${currentPage + 1}" class="pagination-btn">Next</a>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
