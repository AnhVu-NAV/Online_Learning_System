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
                margin-left: 300px;
                padding: 20px;
                transition: margin-left 0.3s ease;
                margin-top: 60px;
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
                padding: 5px 0;
            }

            .lesson a {
                color: #fff;
                text-decoration: none;
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

        </style>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
        </script>
    </head>
    <body>
        <div id="header">
            <div class="logo">

                <span>LEARNIK</span>  
            </div>
            <div class="course-name">
                <!-- Hiển thị tên khóa học -->
                ${courseName}  
            </div>
        </div>

        <!-- Sidebar -->
        <div id="sidebar">
            <h2>Nội dung khóa học</h2>


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
                                <a href="LessonVideoController?lesson_id=${lesson.id}">${lesson.title}</a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>
        </div>


        <button id="toggle-btn">☰</button>


        <div id="main-content">
            <div class="video-container">
                <iframe width="560" height="315" 
                        src="https://www.youtube.com/embed/${videocontent.videoId}?list=${videocontent.listId}&index=${videocontent.index_vid}" 
                        frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
                </iframe>
            </div>

            

        </div>

    </body>
</html>


