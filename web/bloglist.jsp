<%-- 
    Document   : bloglist
    Created on : Sep 16, 2024, 10:57:12 AM
    Author     : mocun
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Learnik Blog</title>
        <link rel="stylesheet" href="fontawesome/css/all.min.css"> <!-- https://fontawesome.com/ -->
        <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap" rel="stylesheet"> <!-- https://fonts.google.com/ -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/templatemo-learnik-blog.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">  
    </head>
    <body>
        <header class="tm-header" id="tm-header">
            <!-- Header content remains the same -->
        </header>

        <div class="container-fluid">
            <main class="tm-main">
                <!-- Search form -->
                <div class="row tm-row">
                    <div class="col-12">
                        <form method="GET" class="form-inline tm-mb-80 tm-search-form">
                            <input class="form-control tm-search-input" name="query" type="text" placeholder="Search..." aria-label="Search">
                            <button class="tm-search-button" type="submit">
                                <i class="fas fa-search tm-search-icon" aria-hidden="true"></i>
                            </button>
                        </form>
                    </div>
                </div>

                <!-- Dynamic Blog Posts Section -->
                <div class="row tm-row">
                    <c:forEach var="blog" items="${blogList}">
                        <!--                        <article class="col-12 col-md-6 tm-post">
                                                    <hr class="tm-hr-primary">
                                                    <a href="post.jsp?blogId=${blog.blogId}" class="effect-lily tm-post-link tm-pt-60">
                                                        <div class="tm-post-link-inner">
                                                            <img src="${blog.thumbnailUrl}" alt="Image" class="img-fluid">
                                                        </div>
                                                        <span class="position-absolute tm-new-badge">New</span>
                                                        <h2 class="tm-pt-30 tm-color-primary tm-post-title">${blog.title}</h2>
                                                    </a>
                                                    <p class="tm-pt-30">${blog.briefInfo}</p>
                                                    <div class="d-flex justify-content-between tm-pt-45">
                                                        <span class="tm-color-primary">${blog.category}</span>
                                                        <span class="tm-color-primary">${fn:substring(blog.createdDate.toString(), 0, 10)}</span>
                                                    </div>
                                                    <hr>
                                                    <div class="d-flex justify-content-between">
                                                        <span>comments</span>
                                                        <span>by ${blog.authorName}</span>
                                                    </div>
                                                </article>-->

                        <jsp:include page="component/BlogItem.jsp">
                            <jsp:param name="blogId" value="${blog.blogId}" />
                            <jsp:param name="title" value="${blog.title}" />
                            <jsp:param name="briefInfo" value="${blog.briefInfo}" />
                            <jsp:param name="thumbnailUrl" value="${blog.thumbnailUrl}" />
                            <jsp:param name="category" value="${blog.category}" />
                            <jsp:param name="createdDate" value="${blog.updatedDate}" />
                            <jsp:param name="authorName" value="${blog.authorName}" />

                        </jsp:include>

                    </c:forEach>
                </div>

                <!-- Pagination -->
                <div class="row tm-row tm-mt-100 tm-mb-75">
                    <div class="row tm-row tm-mt-100 tm-mb-75">
                        <div class="tm-prev-next-wrapper">
                            <c:if test="${currentPage > 1}">
                                <a href="blogList?page=${currentPage - 1}" class="mb-2 tm-btn tm-btn-primary tm-prev-next tm-mr-20">Prev</a>
                            </c:if>
                            <c:if test="${currentPage <= 1}">
                                <a href="#" class="mb-2 tm-btn tm-btn-primary tm-prev-next disabled tm-mr-20">Prev</a>
                            </c:if>

                            <c:if test="${currentPage < noOfPages}">
                                <a href="blogList?page=${currentPage + 1}" class="mb-2 tm-btn tm-btn-primary tm-prev-next">Next</a>
                            </c:if>
                            <c:if test="${currentPage >= noOfPages}">
                                <a href="#" class="mb-2 tm-btn tm-btn-primary tm-prev-next disabled">Next</a>
                            </c:if>
                        </div>
                    </div>

                    <div class="tm-paging-wrapper">
                        <span class="d-inline-block mr-3">Page</span>
                        <nav class="tm-paging-nav d-inline-block">
                            <ul>
                                <c:forEach begin="1" end="${noOfPages}" var="i">
                                    <li class="tm-paging-item ${i == currentPage ? 'active' : ''}">
                                        <a href="blogList?page=${i}" class="mb-2 tm-btn tm-paging-link">${i}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </nav>
                    </div>
                </div>


                <!-- Footer -->
                <footer class="row tm-row">
                    <hr class="col-12">
                    <div class="col-md-6 col-12 tm-color-gray">
                        Design: <a rel="nofollow" target="_parent" href="" class="tm-external-link">Template</a>
                    </div>
                    <div class="col-md-6 col-12 tm-color-gray tm-copyright">
                        Copyright 2024 Learnik Blog Company Co. Ltd.
                    </div>
                </footer>
            </main>
        </div>

        <script src="js/jquery.min.js"></script>
        <script src="js/templatemo-script.js"></script>
    </body>
</html>
