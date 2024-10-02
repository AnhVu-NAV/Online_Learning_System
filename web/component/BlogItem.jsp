<%-- 
    Document   : BlogItem
    Created on : Sep 30, 2024, 4:11:46 PM
    Author     : mocun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--<article class="col-12 col-md-6 tm-post">
    <hr class="tm-hr-primary">
    <a href="${postLink}" class="effect-lily tm-post-link tm-pt-60">
        <div class="tm-post-link-inner">
            <img src="${postImage}" alt="Image" class="img-fluid">                            
        </div>
        <span class="position-absolute tm-new-badge">New</span>
        <h2 class="tm-pt-30 tm-color-primary tm-post-title">${postTitle}</h2>
    </a>                    
    <p class="tm-pt-30">
        ${postDescription}
    </p>
    <div class="d-flex justify-content-between tm-pt-45">
        <span class="tm-color-primary">${postCategory}</span>
        <span class="tm-color-primary">${postDate}</span>
    </div>
    <hr>
    <div class="d-flex justify-content-between">
        <span>${postCommentsCount} comments</span>
        <span>by ${postAuthor}</span>
    </div>
</article>-->
    

        
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="fontawesome/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/templatemo-learnik-blog.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
<article class="col-12 col-md-6 tm-post">
    <hr class="tm-hr-primary">
    <a href="post.html" class="effect-lily tm-post-link tm-pt-60">
        <div class="tm-post-link-inner">
            <img src="img/img-01.jpg" alt="Image" class="img-fluid">
        </div>
        <span class="position-absolute tm-new-badge">New</span>
        <h2 class="tm-pt-30 tm-color-primary tm-post-title">Simple and useful HTML layout</h2>
    </a>
    <p class="tm-pt-30">
        There is a clickable image with beautiful hover effect and active title link for each post item. 
        Left side is a sticky menu bar. Right side is a blog content that will scroll up and down.
    </p>
    <div class="d-flex justify-content-between tm-pt-45">
        <span class="tm-color-primary">Travel . Events</span>
        <span class="tm-color-primary">June 24, 2020</span>
    </div>
    <hr>
    <div class="d-flex justify-content-between">
        <span>36 comments</span>
        <span>by Admin Nat</span>
    </div>
</article>
</body>
</html>

