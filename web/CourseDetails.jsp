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
            <% 
                // Lấy courseId từ request (URL)
                String courseId = request.getParameter("courseId");

                // Kiểm tra nếu courseId không tồn tại, đặt giá trị mặc định
                if (courseId == null || courseId.isEmpty()) {
                    courseId = "1"; // Giá trị mặc định nếu không có courseId
                }
            %>

            <!-- Sidebar with Course Info -->
            <div class="course-info">
                <div class="course-thumbnail">
                    <img src="${course.thumbnailUrl}" alt="Course Image">
                </div>
                <h2 class="course-title-detail">${course.title}</h2>
                <h4 class="course-tagline-detail">${course.subtitle}</h4>
                <div class="course-rating-detail">
                    <span class="stars">
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star-half-alt"></i>
                    </span>
                    <span class="rating-number">4.5 (8,377 ratings) <br> 51,887 students</span>
                </div>
                <div class="course-price-detail">
                    <span class="list-price">$<fmt:formatNumber value="${pricePackage.price}" /></span>
                    <span class="sale-price">$<fmt:formatNumber value="${pricePackage.salePrice}" /></span>
                </div>
                <button class="register-btn primary" data-courseid="${course.id}" onclick="openRegisterPopup(this)">Register Now</button>
                <button class="register-btn secondary">Save for Later</button>
                <p class="money-back">30-Day Money-Back Guarantee</p>

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
                                <input type="checkbox" name="showSubTitle" checked onchange="toggleRelatedCourseField('subtitle')"> 
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

                        <!-- Lựa chọn số lượng khóa học liên quan -->
                        <div class="customization-option">
                            <h4>Number of related courses to display:</h4>
                            <select id="numRelatedCourses" name="numRelatedCourses" onchange="updateRelatedCourses()">
                                <option value="3">3 Courses</option>
                                <option value="4">4 Courses</option>
                                <option value="5">5 Courses</option>
                            </select>
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
                        <li><i class="fas fa-check"></i> ${course.description}</li>
                    </ul>
                </div>

                <!-- Course Content -->
                <div class="course-content">
                    <h3>Course content</h3>
                    <div class="sections">
                        <!-- Lặp qua các chương -->
                        <c:forEach var="chapter" items="${chapters}">
                            <div class="section">
                                <h4>${chapter.title}</h4>
                                <h5>${chapter.subtitle}</h5>
                                <ul>
                                    <!-- Lặp qua các bài học của từng chương -->
                                    <c:forEach var="lesson" items="${chapter.lessons}">
                                        <li>Lesson Title: ${lesson.title} 
                                            <span>Lesson Type: ${lesson.lessonTypeId == 1 ? 'Learning Material' : 'Quiz'}</span>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:forEach>
                    </div>
                    <button class="view-all">View All Sections</button>
                </div>


                <!-- Course Taglines -->
                <div class="course-taglines">
                    <h3>Taglines</h3>
                    <div class="tags">
                        <c:forEach var="tagline" items="${taglines}">
                            <div class="tag">
                                <i class="fas fa-tag"></i> ${tagline}
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <!-- Related Courses Section -->
                <div class="related-courses">
                    <h3>Related Courses</h3>
                    <div class="courses-list slider" id="relatedCourses">
                        <c:forEach var="relatedCourse" items="${relatedCourses}">
                            <div class="course-card">
                                <img src="${relatedCourse.thumbnailUrl}" alt="Related Course ${relatedCourse.title}">
                                <h4 class="course-title">${relatedCourse.title}</h4>
                                <h5 class="course-subtitle">${relatedCourse.subtitle}</h5>

                                <!-- Hiển thị danh sách các taglines -->
                                <div class="course-taglines">
                                    <c:forEach var="tagline" items="${relatedCourse.taglines}">
                                        <span class="tag">${tagline}</span>
                                    </c:forEach>
                                </div>

                                <div class="course-price">
                                    <span class="list-price">$<fmt:formatNumber value="${relatedCourse.price}" /></span>
                                    <span class="sale-price">$<fmt:formatNumber value="${relatedCourse.salePrice}" /></span>
                                </div>
                                <button class="register-btn primary" onclick="openRegisterPopup('<%= courseId %>')">Register</button>
                            </div>
                        </c:forEach>

                    </div>
                </div>
            </div>
        </div>

        <!-- Popup Register Form -->
        <%@ include file="RegisterPopup.jsp" %>
        <%--<jsp:include page="/RegisterPopup.jsp"></jsp:include>--%>

        <script>
            // Function to open the registration popup
            function openRegisterPopup(button) {
                const courseId = button.getAttribute('data-courseid');
                document.getElementById('hiddenCourseId').value = courseId;
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
                var showSubTitle = document.querySelector('input[name="showSubTitle"]').checked;
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
                    var subtitleElement = card.querySelector('.course-subtitle');
                    var taglineElement = card.querySelector('.course-taglines');
                    var priceElement = card.querySelector('.course-price');

                    titleElement.style.display = showTitle ? 'block' : 'none';
                    subtitleElement.style.display = showSubTitle ? 'block' : 'none';
                    taglineElement.style.display = showTagline ? 'block' : 'none';
                    priceElement.style.display = showPrice ? 'block' : 'none';
                });
            }

            // Toggle visibility of related course fields when checkboxes change
            function toggleRelatedCourseField(field) {
                updateRelatedCourses();
            }

            // Khởi tạo khóa học liên quan ban đầu
            updateRelatedCourses(); // Initial load
        </script>
    </body>
</html>
