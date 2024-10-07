<%-- 
    Document   : CourseDetails
    Created on : Oct 3, 2024, 11:36:32 AM
    Author     : AnhVuNAV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Course Details</title>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/courseDetail.css">
</head>
<body>
    <div class="container">
        <!-- Sidebar with Course Info -->
        <div class="course-info">
            <div class="course-thumbnail">
                <img src="${course.thumbnailUrl}" alt="Course Image">
            </div>
            <h2 class="course-title-detail">${course.title}</h2>
            <p class="course-tagline-detail">${course.description}</p>
            <div class="course-rating-detail">
                <span class="stars">
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star-half-alt"></i>
                </span>
                <span class="rating-number">4.5 (8,377 ratings) 51,887 students</span>
            </div>
            <div class="course-price-detail">
                <span class="list-price">$<fmt:formatNumber value="${pricePackage.price}" /></span>
                <span class="sale-price">$<fmt:formatNumber value="${pricePackage.salePrice}" /></span>
            </div>
            <button class="register-btn primary" onclick="openRegisterPopup()">Register Now</button>
            <button class="register-btn secondary">Save for Later</button>
            <p class="money-back">30-Day Money-Back Guarantee</p>
        </div>

        <!-- Main Content -->
        <div class="content">
            <!-- What you'll learn -->
            <div class="what-you-learn">
                <h3>What you'll learn</h3>
                <ul>
                    <li><i class="fas fa-check"></i> Learn to build Web Applications, REST API with Spring Boot and Spring Framework.</li>
                    <li><i class="fas fa-check"></i> Understand Spring Framework fundamentals from an expert with 20 years of experience.</li>
                    <li><i class="fas fa-check"></i> Develop a REAL-TIME project with Spring and Spring Boot from SCRATCH.</li>
                    <li><i class="fas fa-check"></i> Understand the LATEST frameworks and technologies, including Spring Boot, Maven, Eclipse, JUnit, and Mockito.</li>
                </ul>
            </div>

            <!-- Course Content -->
            <div class="course-content">
                <h3>Course content</h3>
                <div class="sections">
                    <!-- Sử dụng JSTL để lặp qua các bài học của khóa học -->
                    <c:forEach var="lesson" items="${lessons}">
                        <div class="section">
                            <h4>${lesson.title}</h4>
                            <ul>
                                <li>Lesson Type: ${lesson.lessonType} 
                                    <span>${lesson.status == 1 ? 'Active' : 'Inactive'}</span>
                                </li>
                            </ul>
                        </div>
                    </c:forEach>
                </div>
                <button class="view-all">View All Sections</button>
            </div>

            <!-- Related Courses Section (Slider) -->
            <div class="related-courses">
                <h3>Related Courses</h3>
                <div class="courses-list slider" id="relatedCourses">
                    <!-- Sử dụng JSTL để lặp qua các khóa học liên quan -->
                    <c:forEach var="relatedCourse" items="${relatedCourses}">
                        <div class="course-card">
                            <img src="${relatedCourse.thumbnailUrl}" alt="Related Course ${relatedCourse.title}">
                            <h4 class="course-title">${relatedCourse.title}</h4>
                            <p class="course-tagline">${relatedCourse.description}</p>
                            <div class="course-price">
                                <span class="list-price">₫<fmt:formatNumber value="${relatedCourse.price}" /></span>
                                <span class="sale-price">₫<fmt:formatNumber value="${relatedCourse.salePrice}" /></span>
                            </div>
                            <button class="register-btn primary" onclick="openRegisterPopup()">Register</button>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

    <!-- Popup Register Form -->
    <%@ include file="RegiterPopup.jsp" %>

    <script>
        // Function to open the registration popup
        function openRegisterPopup() {
            document.getElementById('registerPopup').style.display = 'flex';
        }

        // Function to close the registration popup
        function closeRegisterPopup() {
            document.getElementById('registerPopup').style.display = 'none';
        }

        // Function to add a second email field
        function addEmail() {
            const email2 = document.getElementById('email2');
            if (email2.style.display === 'none') {
                email2.style.display = 'block';
            } else {
                alert('Maximum 2 emails allowed');
            }
        }

        // Function to display registration success message
        function submitRegistration() {
            const message = document.getElementById('message');
            message.className = 'message success';
            message.innerText = 'Registration Successful!';
            message.style.display = 'block';
        }

        // Function to update the display of related courses based on user input
        function updateRelatedCourses() {
            var numCourses = document.getElementById('numRelatedCourses').value;
            var relatedCourses = document.getElementById('relatedCourses');

            // Lưu trạng thái của các checkbox
            var showTitle = document.querySelector('input[name="showTitle"]').checked;
            var showTagline = document.querySelector('input[name="showTagline"]').checked;
            var showPrice = document.querySelector('input[name="showPrice"]').checked;

            var courseCards = relatedCourses.querySelectorAll('.course-card');
            courseCards.forEach((card, index) => {
                if (index < numCourses) {
                    card.style.display = 'block';
                } else {
                    card.style.display = 'none';
                }

                var titleElement = card.querySelector('.course-title');
                var taglineElement = card.querySelector('.course-tagline');
                var priceElement = card.querySelector('.course-price');

                titleElement.style.display = showTitle ? 'block' : 'none';
                taglineElement.style.display = showTagline ? 'block' : 'none';
                priceElement.style.display = showPrice ? 'block' : 'none';
            });
        }

        // Toggle visibility of related course fields
        function toggleRelatedCourseField(field) {
            var elements = document.querySelectorAll(`.course-${field}`);
            elements.forEach(function (element) {
                element.style.display = element.style.display === 'none' ? 'block' : 'none';
            });
        }

        // Khởi tạo khóa học liên quan ban đầu
        updateRelatedCourses(); // Initial load
    </script>
</body>
</html>
