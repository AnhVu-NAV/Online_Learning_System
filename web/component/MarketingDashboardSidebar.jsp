<%-- 
    Document   : MarketingDashboardSidebar
    Created on : Sep 27, 2024, 10:03:31 PM
    Author     : 84941
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Marketing Dashboard Sidebar</title>
        <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ViewUserList.css">

    </head>
    <body>
        <aside id="sidebar">
            <div class="d-flex">
                <button class="toggle-btn" type="button">
                    <i class="lni lni-grid-alt"></i>
                </button>
                <div class="sidebar-logo">
                    <a href="#">Learnik</a>
                </div>
            </div>
            <ul class="sidebar-nav">
                <li class="sidebar-item">
                    <a href="${pageContext.request.contextPath}/MarketingDashboardController?service=viewMarketingDashboard" class="sidebar-link">
                        <i class="lni lni-user"></i>
                        <span>Marketing Dashboard</span>
                    </a>
                </li>              
            </ul>
            <ul class="sidebar-nav">
                <li class="sidebar-item">
                    <a href="${pageContext.request.contextPath}/PostListController?service=viewPostList" class="sidebar-link">
                        <i class="lni lni-user"></i>
                        <span>Post List</span>
                    </a>
                </li>              
            </ul>
            <div class="sidebar-footer">
                <a href="#" class="sidebar-link">
                    <i class="lni lni-exit"></i>
                    <span>Logout</span>
                </a>
            </div>
        </aside>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/js/script.js"></script>
    </body>
</html>
