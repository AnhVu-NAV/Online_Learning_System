<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Course Content</title>
        <style>
            /* Sidebar định dạng */
            #sidebar {
                width: 250px;
                background-color: #f8f9fa;
                padding: 15px;
                position: fixed;
                height: 100%;
                overflow-y: auto;
            }

            .chapter {
                margin-bottom: 10px;
            }

            .chapter-title {
                font-size: 18px;
                cursor: pointer;
                background-color: #007bff;
                color: white;
                padding: 10px;
                border: none;
                width: 100%;
                text-align: left;
                border-radius: 5px;
                margin-bottom: 5px;
            }

            .lesson-list {
                display: none;
                margin-left: 15px;
            }

            .lesson {
                margin-top: 5px;
            }

            .lesson a {
                text-decoration: none;
                color: #007bff;
            }
        </style>
        <script>
            // Hiển thị hoặc ẩn danh sách bài học
            function toggleLessons(chapterId) {
                var lessonList = document.getElementById('lessons_' + chapterId);
                if (lessonList.style.display === 'none') {
                    lessonList.style.display = 'block';
                } else {
                    lessonList.style.display = 'none';
                }
            }
        </script>
    </head>
    <body>
        <div id="sidebar">
            <h2>Course Chapters</h2>

            <!-- Hiển thị danh sách chapters -->
            <c:if test="${not empty chapters}">
                <c:forEach var="chapter" items="${chapters}">
                    <div class="chapter">
                        <!-- Hiển thị title của chapter -->
                        <button class="chapter-title" onclick="toggleLessons(${chapter.id})">
                            ${chapter.title} (${chapter.duration} minutes)
                        </button>

                        <!-- Hiển thị danh sách bài học nếu có -->
                       <c:if test="${currentChapterId == chapter.id && not empty lessons}">
                        <div id="lessons_${chapter.id}" class="lesson-list">
                            <c:forEach var="lesson" items="${lessons}">
                                <div class="lesson">
                                    <a href="#">${lesson.title}</a>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                    </div>
                </c:forEach>
            </c:if>

            
    </body>
</html>
