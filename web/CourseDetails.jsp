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

                <!-- Course Display Customization (Related Courses Only) -->
                <div class="customize-display">
                    <h3>Customize Related Courses</h3>
                    <form id="courseCustomization">
                        <div class="customization-option">
                            <label for="numRelatedCourses">Number of related courses:</label>
                            <select id="numRelatedCourses" name="numRelatedCourses" onchange="updateRelatedCourses()">
                                <option value="3">3 Courses</option>
                                <option value="4">4 Courses</option>
                                <option value="5">5 Courses</option>
                            </select>
                        </div>
                        <div class="customization-option">
                            <h4>Select related course details to display:</h4>
                            <label><input type="checkbox" name="showTitle" checked onchange="toggleRelatedCourseField('title')"> Course Title</label><br>
                            <label><input type="checkbox" name="showTagline" checked onchange="toggleRelatedCourseField('tagline')"> Tagline</label><br>
                            <label><input type="checkbox" name="showPrice" checked onchange="toggleRelatedCourseField('price')"> Price</label><br>
                        </div>
                    </form>
                </div>
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
                                    <li>Lesson Type: ${lesson.lessonType} <span>${lesson.status == 1 ? 'Active' : 'Inactive'}</span></li>
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
                                    <span class="list-price">$<fmt:formatNumber value="${relatedCourse.price}" /></span>
                                    <span class="sale-price">$<fmt:formatNumber value="${relatedCourse.salePrice}" /></span>
                                </div>
                                <button class="register-btn primary">Register</button>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

        <!-- Registration Popup -->
        <div class="popup-background" id="registerPopup" style="display:none;">
            <div class="popup-content">
                <button class="close-btn" onclick="closeRegisterPopup()">&times;</button>
                <h2>Register for Course</h2>
                <form class="register-form">
                    <label for="fullName">Full Name:</label>
                    <input type="text" id="fullName" placeholder="Enter your full name">
                    <label for="email">Email:</label>
                    <input type="email" id="email" placeholder="Enter your email">
                    <button type="submit" class="register-btn primary">Submit</button>
                </form>
            </div>
        </div>

        <script>
            function openRegisterPopup() {
                document.getElementById('registerPopup').style.display = 'flex';
            }

            function closeRegisterPopup() {
                document.getElementById('registerPopup').style.display = 'none';
            }

            function updateRelatedCourses() {
                var numCourses = document.getElementById('numRelatedCourses').value;
                var relatedCourses = document.getElementById('relatedCourses');

                // Lưu trạng thái của các checkbox
                var showTitle = document.querySelector('input[name="showTitle"]').checked;
                var showTagline = document.querySelector('input[name="showTagline"]').checked;
                var showPrice = document.querySelector('input[name="showPrice"]').checked;

                relatedCourses.innerHTML = '';

                for (let i = 0; i < numCourses; i++) {
                    relatedCourses.innerHTML += `
                        <div class="course-card">
                            <img src="course-thumbnail.jpg" alt="Related Course ${i+1}">
                            <h4 class="course-title course-title-${i+1}" style="display: ${showTitle ? 'block' : 'none'};">Course Title ${i+1}</h4>
                            <p class="course-tagline course-tagline-${i+1}" style="display: ${showTagline ? 'block' : 'none'};">Tagline for course ${i+1}</p>
                            <div class="course-price course-price-${i+1}" style="display: ${showPrice ? 'block' : 'none'};">
                                <span class="list-price">₫1,999,000</span>
                                <span class="sale-price">₫999,000</span>
                            </div>
                            <button class="register-btn primary" onclick="openRegisterPopup()">Register</button>
                        </div>
                    `;
                }
            }

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
