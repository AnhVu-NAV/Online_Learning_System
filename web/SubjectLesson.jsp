<%-- 
    Document   : SubjectLesson
    Created on : Oct 27, 2024, 10:34:11 AM
    Author     : mocun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<%
    Integer roleId = (session != null) ? (Integer) session.getAttribute("roleId") : null;

    // Nếu roleId trống hoặc khác 3, chuyển hướng về trang chủ
    if (roleId == null || roleId != 6 || roleId != 3) {
        response.sendRedirect(request.getContextPath() + "/home");
        return;
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subject Lesson</title>
        <link rel="stylesheet" href="./css/SubjectLesson.css">
        <script>
            function toggleColumn(columnClass) {
                const elements = document.querySelectorAll('.' + columnClass);
                elements.forEach(element => {
                    element.style.display = element.style.display === 'none' ? '' : 'none';
                });
            }
        </script>
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
                <!-- Tùy chọn hiển thị cột -->
                <div class="ColumnToggle">
                    <label><input type="checkbox" checked onclick="toggleColumn('col-id')"> ID</label>
                    <label><input type="checkbox" checked onclick="toggleColumn('col-lesson')"> Lesson</label>
                    <label><input type="checkbox" checked onclick="toggleColumn('col-status')"> Status</label>
                    <label><input type="checkbox" checked onclick="toggleColumn('col-type')"> Type</label>
                    <label><input type="checkbox" checked onclick="toggleColumn('col-order')"> Order</label>
                    <label><input type="checkbox" checked onclick="toggleColumn('col-actions')"> Actions</label>
                </div>

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

                    <!-- Bộ lọc trạng thái (Status) -->
                    <select name="searchStatus">
                        <option value="">All Status</option>
                        <option value="1" <c:if test="${searchStatus == '1'}">selected</c:if>>Active</option>
                        <option value="0" <c:if test="${searchStatus == '0'}">selected</c:if>>Inactive</option>
                        </select>
                        <button type="submit">Search</button>
                    </form>
                </div>

                <table class="Table">
                    <thead>
                        <tr>
                            <th class="col-id">#</th>
                            <th class="col-lesson">Lesson</th>
                            <th class="col-status">Status</th>
                            <th class="col-type">Type</th>
                            <th class="col-order">Order</th>
                            <th class="col-actions">Actions</th>
                        </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="entry" items="${lessonsWithTypeValue}">
                        <tr>
                            <td class="col-id">${entry.key.id}</td>
                            <td class="col-lesson">${entry.key.title}</td>
                            <td class="col-status">
                                <c:choose>
                                    <c:when test="${entry.key.status == 1}">
                                        <span class="StatusBadge Active">Active</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="StatusBadge Inactive">Inactive</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="col-type">${entry.value}</td> <!-- Sử dụng entry.value để hiển thị lessonTypeValue -->
                            <td class="col-order">${entry.key.order}</td>
                            <td class="col-actions">
                                <!-- Icon button for Activate/Deactivate -->
                                <form action="subjectLesson" method="post" style="display:inline;">
                                    <input type="hidden" name="lessonId" value="${entry.key.id}">
                                    <input type="hidden" name="newStatus" value="${entry.key.status == 1 ? 0 : 1}">
                                    <input type="hidden" name="page" value="${currentPage}">
                                    <button type="submit" class="IconButton" title="${entry.key.status == 1 ? 'Deactivate' : 'Activate'}">
                                        <c:choose>
                                            <c:when test="${entry.key.status == 1}">
                                                ❌ <!-- Deactivate icon -->
                                            </c:when>
                                            <c:otherwise>
                                                ✔️ <!-- Activate icon -->
                                            </c:otherwise>
                                        </c:choose>
                                    </button>
                                </form>
                                <button class="IconButton" onclick="window.location.href = '${pageContext.request.contextPath}/updateLesson?lessonId=${entry.key.id}'">
                                    <span class="Edit">✎</span>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>

            <div class="Pagination">
                <button class="PaginationPrev" onclick="window.location.href = 'subjectLesson?page=${currentPage - 1}&records=${records}'" 
                        <c:if test="${currentPage == 1}">disabled</c:if>>«</button>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <button class="PaginationItem ${i == currentPage ? 'active' : ''}" 
                            onclick="window.location.href = 'subjectLesson?page=${i}&records=${records}'">${i}</button>
                </c:forEach>
                <button class="PaginationNext" onclick="window.location.href = 'subjectLesson?page=${currentPage + 1}&records=${records}'" 
                        <c:if test="${currentPage == noOfPages}">disabled</c:if>>»</button>

                        <!-- Input cho người dùng nhập số bản ghi trên mỗi trang -->
                        <form action="subjectLesson" method="get" style="display: inline;">
                            <label for="recordsPerPage">Records per page:</label>
                            <input type="number" id="recordsPerPage" name="records" value="${records != null && records != '' ? records : 10}" min="1" style="width: 60px;" />
                        <button type="submit">Apply</button>
                        <!-- Giữ lại các tham số tìm kiếm và trang hiện tại -->
                        <input type="hidden" name="page" value="${currentPage}">
                        <input type="hidden" name="searchName" value="${searchName}">
                        <input type="hidden" name="searchType" value="${searchType}">
                        <input type="hidden" name="searchStatus" value="${searchStatus}">
                </form>
            </div>        
        </div>
    </body>
</html>

