<%-- 
    Document   : BlogItem
    Created on : Sep 30, 2024, 4:11:46 PM
    Author     : mocun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
        
        <article class="col-12 col-md-6 tm-post">
    <hr class="tm-hr-primary">
    <a href="postDetail.jsp?blogId=${param.blogId}" class="effect-lily tm-post-link tm-pt-60">
        <div class="tm-post-link-inner">
            <img src="${param.thumbnailUrl}" alt="${param.title}" class="img-fluid">
        </div>
        <span class="position-absolute tm-new-badge">New</span>
        <h2 class="tm-pt-30 tm-color-primary tm-post-title">${param.title}</h2>
    </a>
    <p class="tm-pt-30">
        ${param.briefInfo}
    </p>
    <div class="d-flex justify-content-between tm-pt-45">
        <span class="tm-color-primary">${param.category}</span>
        <span class="tm-color-primary">${fn:substring(param.createdDate.toString(), 0, 10)}</span>
    </div>
    <hr>
    <div class="d-flex justify-content-between">
        <span>${param.category}</span>
        <span>by ${param.authorName}</span>
    </div>
</article>
    

        
