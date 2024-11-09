<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Course Chapters and Lessons</title>
        <style>
            /* CSS cho body */
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                background-color: #1e1e1e;  /* Đặt màu nền chung */
                color: #fff;  /* Màu chữ trắng để dễ đọc trên nền tối */
            }

            /* CSS cho sidebar */
            #sidebar {
                width: 300px;
                background-color: #2c2f33;
                color: #fff;
                height: 100vh;
                position: fixed;
                left: 0;
                top: 0;
                overflow-y: auto;
                transition: all 0.3s ease;
                padding: 10px;
            }

            /* Khi sidebar ẩn */
            #sidebar.hidden {
                width: 0;
                padding: 0;
                transform: translateX(-300px);
            }

            /* Nút để ẩn/hiện sidebar */
            #toggle-btn {
                position: fixed;
                top: 15px;
                left: 300px;
                font-size: 20px;
                background-color: #2c2f33;
                color: #fff;
                border: none;
                padding: 10px;
                cursor: pointer;
                z-index: 1000;
                top: 75px;
            }

            /* Khi sidebar bị ẩn thì nút di chuyển lại gần */
            #sidebar.hidden + #toggle-btn {
                left: 0;
            }

            /* Phần main content */
            #main-content {
                margin-left: 333px;
                padding: 20px;
                transition: margin-left 0.3s ease;
                margin-top: 60px;
                min-height: 100vh;
                background-color: #1e1e1e;
                display: flex;
                flex-direction: column;
                justify-content: space-between;
            }

            /* Khi sidebar ẩn, nội dung sẽ mở rộng */
            #sidebar.hidden ~ #main-content {
                margin-left: 40px;
            }

            /* Phong cách cho danh sách chapter và lesson */
            .chapter-title {
                background-color: #40444b;
                padding: 10px;
                margin: 10px 0;
                cursor: pointer;
                border-radius: 5px;
                display: flex;
                justify-content: space-between;
                align-items: center;
                flex-direction: column;

            }

            .chapter-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                width: 100%;

            }

            .chapter-title span {
                display: inline-block;

            }


            .chapter-duration {
                display: flex;
                font-size: 12px;
                color: #bbb;
                margin-top: 5px;
                align-self: flex-start;

            }

            .chapter-title:hover {
                background-color: #546e7a;
            }

            .lesson-list {
                padding-left: 20px;
                margin-bottom: 10px;
                display: none;
            }

            .lesson {
                display: flex;
                align-items: center;
                padding: 5px 0;
                border-bottom: 1px solid #ccc;
            }

            .lesson a {
                color: #fff;
                text-decoration: none;
                margin-left: 10px;
            }

            .lesson a.current-lesson {
                font-weight: bold;
                color: #5a67d8; /* Màu bôi đậm cho bài học hiện tại */
            }

            .lesson a:hover {
                text-decoration: underline;
            }

            .dropdown-icon {
                font-size: 12px;
                margin-left: auto;
            }

            #header {
                width: 100%;
                background-color: #2c2f33;  /* Màu nền tối */
                color: white;               /* Màu chữ trắng */
                height: 60px;
                display: flex;
                align-items: center;
                justify-content: space-between;
                position: fixed;
                top: 0;
                left: 0;
                padding: 0 20px;
                z-index: 1000;  /* Đảm bảo header luôn ở trên các thành phần khác */
                box-shadow: 0 2px 5px rgba(0,0,0,0.1);  /* Hiệu ứng bóng */
            }

            #header span {
                font-size: 30px;
            }

            .video-container {
                width: 100%;  /* Chiếm toàn bộ chiều ngang */
                display: flex;
                justify-content: center;  /* Canh giữa */
            }

            .video-container iframe {
                width: 90%;  /* Chiếm 80% màn hình */
                height: 550px;  /* Chiều cao video */
            }

            /* CSS cho phần mô tả video */
            .video-description {
                margin-top: 20px;
                padding: 15px;
                background-color: #2c2f33;
                border-radius: 8px;
                margin-bottom: 0;
            }

            .video-description h3 {
                margin-bottom: 10px;
                font-size: 24px;

            }

            .video-description p {
                font-size: 16px;
                line-height: 1.6;
                height: 100%;
                color: #ccc;  /* Màu chữ cho đoạn mô tả */
            }

            #show-summary-btn {
                position: absolute;
                top: 15px;  /* Cách đỉnh màn hình một khoảng nhỏ */
                right: 20px;  /* Cách cạnh phải của màn hình */
                background-color: blanchedalmond;  /* Màu xanh chủ đạo */
                color: black;
                border: none;
                padding: 10px 20px;  /* Tăng kích thước padding cho nút */
                cursor: pointer;
                border-radius: 8px;  /* Góc bo tròn */
                font-size: 16px;  /* Tăng kích thước chữ */
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);  /* Thêm hiệu ứng đổ bóng */
                transition: background-color 0.3s ease, transform 0.3s ease;  /* Hiệu ứng khi hover */
                z-index: 1000;  /* Đảm bảo nút luôn hiển thị trên cùng */
                margin-right: 40px;
            }

            #show-summary-btn:hover {
                background-color: #0056b3;  /* Màu xanh đậm hơn khi hover */
                transform: translateY(-2px);  /* Hiệu ứng di chuyển khi hover */
            }

            /* CSS cho popup */
            .popup {
                display: none;
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                width: 400px;
                background-color: #fff;
                box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.5);
                z-index: 1000;
                border-radius: 10px;
                padding: 20px;
            }

            .popup-content {
                position: relative;
            }

            .popup h3 {
                margin-top: 0;
                color: #333;
            }

            .popup p {
                color: #555;
            }

            .close-btn {
                position: absolute;
                top: 10px;
                right: 10px;
                font-size: 20px;
                cursor: pointer;
                color: black;
            }

            /* Hiển thị khi popup được bật */
            .popup.show {
                display: block;
            }

            .popup-actions {
                display: flex;
                justify-content: space-between; /* Căn đều giữa các nút */
                margin-top: 20px; /* Thêm khoảng cách giữa các nút và nội dung phía trên */
            }

            .popup-actions button,
            .popup-actions a button {
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                font-size: 14px;
                cursor: pointer;
            }

            /* Style nút Start Quiz */
            .popup-actions a button {
                background-color: #007bff;
                color: white;
            }

            .popup-actions a button:hover {
                background-color: #0056b3;
            }

            /* Style nút Close */
            .popup-actions .close-btn-quiz {
                background-color: #ff4d4d;
                color: white;
            }

            .popup-actions .close-btn-quiz:hover {
                background-color: #e60000;
            }





        </style>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> <!-- Đảm bảo jQuery được tải -->

        <script>
            // Script để xử lý ẩn/hiện sidebar
            $(document).ready(function () {
                $("#toggle-btn").click(function () {
                    $("#sidebar").toggleClass("hidden");
                });

                // Script để xử lý ẩn/hiện danh sách bài học và đổi icon
                $(".chapter-title").click(function () {
                    $(this).next(".lesson-list").slideToggle();

                    // Đổi icon giữa '▼' và '▲'
                    var icon = $(this).find(".dropdown-icon");
                    if (icon.text() === "▼") {
                        icon.text("▲");
                    } else {
                        icon.text("▼");
                    }
                });
            });

            // Script để hiển thị và ẩn popup
            document.addEventListener("DOMContentLoaded", function () {
                var showSummaryBtn = document.getElementById("show-summary-btn");
                var popup = document.getElementById("summary-popup");
                var closeBtn = document.querySelector(".close-btn");

                // Khi nhấn vào nút Show Summary, popup sẽ hiển thị
                showSummaryBtn.addEventListener("click", function () {
                    // Kiểm tra nếu đúng nút Show Summary được nhấn

                    popup.classList.add("show");

                });

                // Khi nhấn vào nút Close (x), popup sẽ bị ẩn
                closeBtn.addEventListener("click", function () {
                    popup.classList.remove("show");
                });

                // Khi nhấn bên ngoài popup, popup cũng sẽ ẩn
                window.addEventListener("click", function (event) {
                    if (event.target === popup) {
                        popup.classList.remove("show");
                    }
                });
            });


            // Lưu trạng thái hoàn thành cho các checkbox
            const lessons = document.querySelectorAll('.lesson input[type="checkbox"]');
            lessons.forEach(checkbox => {
                const lessonId = checkbox.id.replace("completed_", "");
                const isCompleted = localStorage.getItem("completed_" + lessonId) === "true";
                checkbox.checked = isCompleted;
                // Gán sự kiện toggleCompleted cho từng checkbox
                checkbox.addEventListener("change", function () {
                    toggleCompleted(lessonId);
                });
            });



            // Hàm lưu trạng thái hoàn thành của bài học vào localStorage
            function toggleCompleted(lessonId) {
                const checkbox = document.getElementById("completed_" + lessonId);
                localStorage.setItem("completed_" + lessonId, checkbox.checked);
            }
            function closeQuizPopup() {
                document.getElementById("quiz-popup").style.display = "none";
            }
        </script>

    </head>
    <body>
        <div id="header">
            <div class="logo">
                <a href="home" style="text-decoration: none; color: inherit;">
                    <span>LEARNIK</span>
                </a>
            </div>

            <div class="course-name">
                ${courseName}  
            </div>
            <button id="show-summary-btn">Show Summary</button>
        </div>

        <!-- Sidebar -->
        <div id="sidebar">
            <h2>Break Tab</h2>


            <c:forEach var="chapter" items="${chapters}">
                <div class="chapter">
                    <div class="chapter-title">
                        <div class="chapter-header">
                            <span> ${chapter.title}</span>
                            <span class="dropdown-icon">▼</span> 
                        </div>
                        <div class="chapter-duration">
                            ${chapter.duration} minutes
                        </div>
                    </div>




                    <div class="lesson-list">
                        <c:forEach var="lesson" items="${lessonsMap[chapter.id]}">
                            <div class="lesson">
                                <input type="checkbox" id="completed_${lesson.id}" name="completed_${lesson.id}" onclick="toggleCompleted(${lesson.id})">
                                <a href="LessonController?lesson_id=${lesson.id}"class="<c:if test='${currentLessonId == lesson.id}'>current-lesson</c:if>">${lesson.title}</a>
                                </div>
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>
        </div>


        <button id="toggle-btn">☰</button>


        <div id="main-content">
            <c:choose>

                <c:when test="${not empty textHtmlContent}">
                    <div class="text-html-content">

                        <div class="html-content">
                            <c:out value="${textHtmlContent}" escapeXml="false"/> 
                        </div>
                    </div>
                </c:when>


                <c:when test="${not empty videocontent}">
                    <div class="video-container">
                        <iframe width="560" height="315" 
                                src="https://www.youtube.com/embed/${videocontent.videoId}?list=${videocontent.listId}&index=${videocontent.index_vid}" 
                                frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
                        </iframe>
                    </div>

                    <div id="summary-popup" class="popup" >
                        <div class="popup-content">
                            <span class="close-btn" onclick="closeSummaryPopup()">&times;</span>
                            <h3>Summary</h3>
                            <p>${videocontent.videoSummary}</p>
                        </div>
                    </div>

                    <div class="video-description">
                        <h3>Description</h3>
                        <p>${videocontent.description}</p> 
                    </div>
                </c:when>


            </c:choose>
        </div>
        <c:if test="${showQuizPopup}">
            <div id="quiz-popup" class="popup" style="display: flex;">
                <div class="popup-content">
                    <h3>${quizTitle}</h3>
                    <p><strong>Description:</strong> ${quizDescription}</p>
                    <p><strong>Total Questions:</strong> ${totalQuestions}</p>


                    <div class="popup-actions">
                        <a href="QuizHandleController?quizId=${quizId}&questionNumber=1">
                            <button>Start Quiz</button>
                        </a>
                        <button class="close-btn-quiz" onclick="closeQuizPopup()">Close</button>
                    </div>
                </div>
            </div>
        </c:if>



    </body>
</html>


