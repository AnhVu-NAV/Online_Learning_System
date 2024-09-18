<%-- 
    Document   : slider.jsp
    Created on : Sep 18, 2024, 10:32:37 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css" />
        <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css"/>
        <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css"/>
        <link rel="stylesheet" type="text/css" href="css/util.css" />
        <link rel="stylesheet" type="text/css" href="css/main.css" />
        <style>
            body {
                font-family: Arial, sans-serif;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
                background-color: #f0f0f0;
            }

            .slider {
                position: relative;
                width: 80%;
                max-width: 800px;
                overflow: hidden;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            .slides {
                display: flex;
                transition: transform 0.5s ease-in-out;
            }

            .slide {
                min-width: 100%;
                box-sizing: border-box;
            }

            .slide img {
                width: 100%;
                border-radius: 10px;
            }

            .prev, .next {
                position: absolute;
                top: 50%;
                transform: translateY(-50%);
                background-color: rgba(0, 0, 0, 0.5);
                color: white;
                border: none;
                padding: 10px;
                cursor: pointer;
                border-radius: 50%;
            }

            .prev {
                left: 10px;
            }

            .next {
                right: 10px;
            }

            img {
                width: 797px;
                height: 400px;
            }
        </style>
    </head>
    <body>

        <div class="slider">

            <div class="slides">
                <div class="slide"><img src="img/001.jpg" alt=""></div>
                <div class="slide"><img src="img/002.jpg" alt=""></div>
                <div class="slide"><img src="img/003.jpg" alt=""></div>
            </div>

            <button class="prev" onclick="prevSlide()">&#10094;</button>
            <button class="next" onclick="nextSlide()">&#10095;</button>

        </div>


        <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
        <script src="vendor/bootstrap/js/popper.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
        <script src="vendor/select2/select2.min.js"></script>
        <script src="vendor/tilt/tilt.jquery.min.js"></script>
        <script>
                $(".js-tilt").tilt({
                    scale: 1.1,
                });
                let currentSlide = 0;

                function showSlide(index) {
                    const slides = document.querySelectorAll('.slide');
                    if (index >= slides.length) {
                        currentSlide = 0;
                    } else if (index < 0) {
                        currentSlide = slides.length - 1;
                    } else {
                        currentSlide = index;
                    }
                    const offset = -currentSlide * 100;
                    document.querySelector('.slides').style.transform = `translateX(${offset}%)`;
                }

                function nextSlide() {
                    showSlide(currentSlide + 1);
                }

                function prevSlide() {
                    showSlide(currentSlide - 1);
                }

                document.addEventListener('DOMContentLoaded', () => {
                    showSlide(currentSlide);
                });

        </script>
        <script src="js/main.js"></script>
    </body>
</html>

