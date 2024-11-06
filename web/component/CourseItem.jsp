<%-- 
    Document   : CardItem
    Created on : Sep 28, 2024, 5:27:17 PM
    Author     : mocun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    /* Course Cards Grid Layout */
    .course-grid {
        display: grid;
        grid-template-columns: repeat(5, 1fr); /* 5 courses per row */
        gap: 20px;
        margin-top: 20px;
        margin-bottom: 20px;

    }

    /* Individual Course Cards */
    .course-card {
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        transition: transform 0.3s ease;
        cursor: pointer;
    }

    .course-card:hover {
        transform: translateY(-5px); /* Card lifts on hover */
    }

    /* Remove underline for course card links */
    .course-card a {
        text-decoration: none; /* Removes underline */
        color: inherit; /* Inherits color from parent */
    }

    .course-card a:hover {
        text-decoration: none; /* Ensures no underline on hover as well */
        color: inherit; /* Keep the color the same on hover */
    }

    .thumbnail {
        width: 100%;
        height: 150px;
        object-fit: cover;
        border-radius: 8px 8px 0 0;
    }

    .course-info {
        padding: 15px;
    }

    .course-info h3 {
        font-size: 18px;
        margin-bottom: 8px;
        font-weight: 600;
        color: #333;
    }

    .course-info .instructor, .course-info .students, .course-info .lessons {
        font-size: 13px;
        color: #777;
        margin-bottom: 5px;
    }

    .course-info .rating {
        font-size: 14px;
        color: #f39c12;
        margin-bottom: 10px;
    }

    /* Pricing Section */
    .pricing {
        display: flex;
        align-items: center;
        margin-bottom: 10px;
    }

    .pricing .list-price {
        text-decoration: line-through;
        margin-right: 10px;
        color: #999;
    }

    .pricing .sale-price {
        font-weight: bold;
        color: #e74c3c;
    }

    /* Register Button */
    .register-btn {
        width: 100%;
        padding: 10px 15px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    .register-btn:hover {
        background-color: #0056b3;
    </style>
    
    <div class="course-grid" id="courseGrid">
        <c:forEach var="course" items="${courses}">
            <div class="course-card">
                <a href="courseDetails?id=${course.id}">
                    <img src="${course.thumbnailUrl}" class="thumbnail" alt="${course.title}">
                    <div class="course-info">
                        <h3>${course.title}</h3>
                        <p>${course.description}</p>
                        <div class="pricing">
                            <span class="list-price">$${course.price}</span>
                            <span class="sale-price">$${course.salePrice}</span>
                        </div>
                    </div>
                </a>
                <button class="register-btn">Register</button>
            </div>
        </c:forEach>
    </div>


Test data
<div class="course-grid" id="courseGrid">
     Card 1 
    <div class="course-card course-item">
        <a href="courseDetails?id=1">
            <img src="./img/about-01.jpg" class="thumbnail" alt="Course 1">
            <div class="course-info course-item">
                <h3 class="course-title">Course 1</h3>
                <p>This is a description for Course 1.</p>
                <div class="pricing">
                    <span class="list-price">$100</span>
                    <span class="sale-price">$50</span>
                </div>
            </div>
        </a>
        <button class="register-btn">Register</button>
    </div>

     Card 2 
    <div class="course-card course-item">
        <a href="courseDetails?id=2">
            <img src="./img/img-06.jpg" class="thumbnail" alt="Course 2">
            <div class="course-info">
                <h3 class="course-title">Course 2</h3>
                <p>This is a description for Course 2.</p>
                <div class="pricing">
                    <span class="list-price">$120</span>
                    <span class="sale-price">$60</span>
                </div>
            </div>
        </a>
        <button class="register-btn">Register</button>
    </div>

     Card 3 
    <div class="course-card course-item">
        <a href="courseDetails?id=3">
            <img src="./img/img-04.jpg" class="thumbnail" alt="Course 3">
            <div class="course-info">
                <h3>Course 3</h3>
                <p>This is a description for Course 3.</p>
                <div class="pricing">
                    <span class="list-price">$150</span>
                    <span class="sale-price">$75</span>
                </div>
            </div>
        </a>
        <button class="register-btn">Register</button>
    </div>
</div>


