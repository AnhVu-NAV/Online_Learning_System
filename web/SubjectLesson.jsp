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
                <!-- Nút "Create New Lesson" -->
                <button class="Button" onclick="window.location.href = '${pageContext.request.contextPath}/createLesson'">+ New Lesson</button>


                <!-- Form tìm kiếm -->
                <form action="subjectLesson" method="get" style="display: inline;">
                    <input type="text" name="searchName" placeholder="Search by lesson name..." value="${searchName}">
                    <select name="searchType">
                        <option value="">All Types</option>
                        <c:forEach var="type" items="${lessonTypes}">
                            <option value="${type}" <c:if test="${type == searchType}">selected</c:if>>${type}</option>
                        </c:forEach>
                    </select>
                    <button type="submit">Search</button>
                </form>
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
                            <td>${lesson.title}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${lesson.status == 1}">
                                        <span class="StatusBadge Active"> Active</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="StatusBadge Inactive"> Inactive</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${lesson.lessonTypeId.value}</td>
                            <td>${lesson.order}</td>
                            <td>
                                <!-- Icon button for Activate/Deactivate -->
                                <form action="subjectLesson" method="post" style="display:inline;">
                                    <input type="hidden" name="lessonId" value="${lesson.lessonId}">
                                    <input type="hidden" name="newStatus" value="${lesson.status == 1 ? 0 : 1}">
                                    <button type="submit" class="IconButton" title="${lesson.status == 1 ? 'Deactivate' : 'Activate'}">
                                        <c:choose>
                                            <c:when test="${lesson.status == 1}">
                                                ❌ <!-- Deactivate icon -->
                                            </c:when>
                                            <c:otherwise>
                                                ✔️ <!-- Activate icon -->
                                            </c:otherwise>
                                        </c:choose>
                                    </button>
                                </form>
                                <button class="IconButton" onclick="window.location.href = '${pageContext.request.contextPath}/editLesson?lessonId=${lesson.lessonId}'">
                                    <span class="Edit">✎</span>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div class="Pagination">
                <button class="PaginationPrev" onclick="window.location.href = 'subjectLesson?page=${currentPage - 1}'" 
                        <c:if test="${currentPage == 1}">disabled</c:if>>«</button>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <button class="PaginationItem ${i == currentPage ? 'active' : ''}" 
                            onclick="window.location.href = 'subjectLesson?page=${i}'">${i}</button>
                </c:forEach>
                <button class="PaginationNext" onclick="window.location.href = 'subjectLesson?page=${currentPage + 1}'" 
                        <c:if test="${currentPage == noOfPages}">disabled</c:if>>»</button>
                        <select class="PageSelect" onchange="window.location.href = 'subjectLesson?page=1&records=' + this.value;">
                            <option value="10" <c:if test="${records == 10}">selected</c:if>>10</option>
                        <option value="20" <c:if test="${records == 20}">selected</c:if>>20</option>
                </select> / Page
            </div>
        </div>

    </body>
</html>
