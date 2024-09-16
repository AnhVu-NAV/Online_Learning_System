<%-- 
    Document   : bloglist
    Created on : Sep 16, 2024, 10:57:12 AM
    Author     : mocun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%--<%@ page import="your.package.Blogs" %>--%>
<!DOCTYPE html>
<html>
   <head>
    <title>Blogs List</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="blog-list">
        <h1>Blogs</h1>
        <div class="blog-items">
            <c:forEach var="blog" items="${blogList}">
                <div class="blog-item">
                    <img src="${blog.thumbnailUrl}" alt="Thumbnail" />
                    <h2>${blog.title}</h2>
                    <p>${blog.briefInfo}</p>
                    <a href="blogDetails?id=${blog.blogId}">Read more</a>
                </div>
            </c:forEach>
        </div>
        <!-- Pagination Controls -->
        <c:forEach var="i" begin="1" end="${noOfPages}">
            <a href="blogList?page=${i}">${i}</a>
        </c:forEach>
    </div>

    <div class="sidebar">
        <div class="search-box">
            <form action="searchBlogs" method="get">
                <input type="text" name="query" placeholder="Search posts" />
                <button type="submit">Search</button>
            </form>
        </div>

        <div class="categories">
            <h3>Categories</h3>
            <!-- Display categories dynamically if you have them -->
        </div>

        <div class="latest-posts">
            <h3>Latest Posts</h3>
            <!-- Display latest posts dynamically if you have them -->
        </div>

        <div class="static-links">
            <h3>Contacts / Links</h3>
            <!-- Add your static links here -->
        </div>
    </div>
</body>
