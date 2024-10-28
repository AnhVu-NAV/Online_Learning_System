<%-- 
    Document   : SubjectLesson
    Created on : Oct 27, 2024, 10:34:11 AM
    Author     : mocun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Subject Lesson</title>
    <link rel="stylesheet" href="./css/SubjectLesson.css">
</head>
<body>

    <div class="CourseLesson">
        <header class="Header">
            <h1>Subject Lesson</h1>
            <nav class="Breadcrumbs">
                <a href="#">Dashboard</a> / <span>Subject / Lesson1</span>
            </nav>
        </header>

        <div class="Actions">
            <button class="Button">+ New Lesson</button>
            <div class="Searchbox">
                <input type="text" placeholder="Search...">
            </div>
        </div>

        <table class="Table">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Lesson</th>
                    <th>Status</th>
                    <th>Type</th>
                    <th>Order</th>
                    <th>Actions</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="lesson" items="${lessons}">
                    <tr>
                        <td>${lesson.lessonId}</td>
                        <td>${lesson.name}</td>
                        <td><span class="StatusBadge Active"></span> Active</td> <!-- Sửa trạng thái nếu cần -->
                        <td>${lesson.lessonTypeId.value}</td>
                        <td>${lesson.order}</td>
                        <td>
                            <button class="IconButton"><span class="Arrow">→</span></button>
                            <button class="IconButton"><span class="Edit">✎</span></button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="Pagination">
            <button class="PaginationPrev" onclick="window.location.href='subjectLesson?page=${currentPage - 1}'" 
                    <c:if test="${currentPage == 1}">disabled</c:if>>«</button>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <button class="PaginationItem ${i == currentPage ? 'active' : ''}" 
                        onclick="window.location.href='subjectLesson?page=${i}'">${i}</button>
            </c:forEach>
            <button class="PaginationNext" onclick="window.location.href='subjectLesson?page=${currentPage + 1}'" 
                    <c:if test="${currentPage == noOfPages}">disabled</c:if>>»</button>
            <select class="PageSelect" onchange="window.location.href='subjectLesson?page=1&records=' + this.value;">
                <option value="10" <c:if test="${records == 10}">selected</c:if>>10</option>
                <option value="20" <c:if test="${records == 20}">selected</c:if>>20</option>
            </select> / Page
        </div>
    </div>

</body>
</html>
