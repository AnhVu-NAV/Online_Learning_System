<%-- 
    Document   : BlogDetail
    Created on : Oct 7, 2024, 11:08:33 AM
    Author     : mocun
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${blog.title}</title>
        <link rel="stylesheet" href="fontawesome/css/all.min.css"> <!-- https://fontawesome.com/ -->
        <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap" rel="stylesheet"> <!-- https://fonts.google.com/ -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/templatemo-learnik-blog.css" rel="stylesheet">
    </head>
    <body>

        <header class="tm-header" id="tm-header">
            <div class="tm-header-wrapper">
                <button class="navbar-toggler" type="button" aria-label="Toggle navigation">
                    <i class="fas fa-bars"></i>
                </button>
                <div class="tm-site-header">
                    <h1 class="text-center">Learnik Blog</h1>
                </div>
                <nav class="tm-nav" id="tm-nav">            
                    <ul>
                        <li class="tm-nav-item"><a href="blogList.jsp" class="tm-nav-link">
                                <i class="fas fa-home"></i>
                                Blog Home
                            </a></li>
                        <li class="tm-nav-item active"><a href="#" class="tm-nav-link">
                                <i class="fas fa-pen"></i>
                                Single Post
                            </a></li>
                        <li class="tm-nav-item"><a href="about.jsp" class="tm-nav-link">
                                <i class="fas fa-users"></i>
                                About Learnik
                            </a></li>
                        <li class="tm-nav-item"><a href="contact.jsp" class="tm-nav-link">
                                <i class="far fa-comments"></i>
                                Contact Us
                            </a></li>
                    </ul>
                </nav>
                <p class="tm-mb-80 pr-5 text-white">
                    Learnik Blog is a multi-purpose HTML template. Left side is a sticky menu bar, while the right side content scrolls up and down.
                </p>
            </div>
        </header>

        <div class="container-fluid">
            <main class="tm-main">         
                <img src="${blog.thumbnailUrl}" alt="${blog.title}" class="img-fluid">
                <!-- Blog Details Section -->
                <div class="row tm-row">
                    <div class="col-lg-8 tm-post-col">
                        <div class="tm-post-full">                    
                            <div class="mb-4">
                                <h2 class="pt-2 tm-color-primary tm-post-title">${blog.title}</h2>
                                <p class="tm-mb-40">Last updated ${fn:substring(blog.updatedDate.toString(), 0, 10)} posted by ${blog.authorName}</p>
                                <p>${blog.content}</p>
                                <span class="d-block text-right tm-color-primary">Category: ${blog.category}</span>
                            </div>
                        </div>
                    </div>

                    <!-- Sidebar with Search, Categories, Latest Posts, and Static Links -->
                    <aside class="col-lg-4 tm-aside-col">
                        <div class="tm-post-sidebar">
                            <!-- Search Box -->
                            <div class="tm-sidebar-searchbox">
                                <h2 class="mb-4 tm-post-title tm-color-primary">Search</h2>
                                <form method="GET" action="blogSearch.jsp" class="form-inline tm-mb-80 tm-search-form">
                                    <input class="form-control tm-search-input" name="query" type="text" placeholder="Search..." aria-label="Search">
                                    <button class="tm-search-button" type="submit">
                                        <i class="fas fa-search tm-search-icon"></i>
                                    </button>
                                </form>
                            </div>

                            <!-- Categories -->
                            <hr class="mb-3 tm-hr-primary">
                            <h2 class="mb-4 tm-post-title tm-color-primary">Categories</h2>
                            <ul class="tm-mb-75 pl-5 tm-category-list">
                                <c:forEach var="category" items="${categories}">
                                    <li><a href="category.jsp?category=${category}" class="tm-color-primary">${category}</a></li>
                                    </c:forEach>
                            </ul>

                            <!-- Latest Posts -->
                            <hr class="mb-3 tm-hr-primary">
                            <h2 class="tm-mb-40 tm-post-title tm-color-primary">Latest Posts</h2>
                            <ul>
                                <c:forEach var="blog" items="${blogList}" begin="0" end="2">
                                    <li><a href="BlogDetails?id=${blog.blogId}">${blog.title}</a></li>
                                    </c:forEach>
                            </ul>
                            <!-- Static Contact/Links -->
                            <hr class="mb-3 tm-hr-primary">
                            <h2 class="tm-mb-40 tm-post-title tm-color-primary">Contact Us</h2>
                            <p>Address: 123 Learnik St, Technology City</p>
                            <p>Email: contact@learnik.com</p>
                            <p>Phone: +123 456 7890</p>
                            <div class="tm-mb-65">
                                <a href="https://facebook.com" class="tm-social-link">
                                    <i class="fab fa-facebook tm-social-icon"></i>
                                </a>
                                <a href="https://twitter.com" class="tm-social-link">
                                    <i class="fab fa-twitter tm-social-icon"></i>
                                </a>
                                <a href="https://instagram.com" class="tm-social-link">
                                    <i class="fab fa-instagram tm-social-icon"></i>
                                </a>
                                <a href="https://linkedin.com" class="tm-social-link">
                                    <i class="fab fa-linkedin tm-social-icon"></i>
                                </a>
                            </div>
                        </div>                    
                    </aside>
                </div>

                <!-- Footer -->
                <footer class="row tm-row">
                    <div class="col-md-6 col-12 tm-color-gray">
                        Design: <a rel="nofollow" target="_parent" href="https://templatemo.com" class="tm-external-link">Learnik</a>
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


