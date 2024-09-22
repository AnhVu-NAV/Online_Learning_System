<%-- 
    Document   : edit_sliders
    Created on : Sep 20, 2024, 4:41:37 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Simply Amazed HTML Template by Tooplate</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="preconnect" href="https://fonts.gstatic.com" />
        <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@300;400&display=swap" rel="stylesheet" />
        <link rel="stylesheet" href="css/bootstrap1.min.css" type="text/css" />
        <link rel="stylesheet" href="fonts/fontawesome/css/all.min.css" type="text/css" />
        <link rel="stylesheet" href="css/slick.css" type="text/css" />
        <link rel="stylesheet" href="css/tooplate-simply-amazed.css" type="text/css" />
        <style>
            #outer {
                display: flex;
            }
            .header {
                position: fixed;
                left: 0;
                top: 0;
                width: 400px;
                z-index: 1000;
                height: 100vh;
            }
            main {
                margin-left: 400px;
                width: calc(100% - 400px);
            }
            .header-title {
                color: white;
                font-size: 24px;
                text-align: center;
                margin: 0 0 20px;
                padding: 10px 0;
                font-weight: bold;
            }
            .search-input {
                flex-grow: 1;
            }
            .search-button {
                margin-left: 8px;
            }
            .action-buttons {
                margin-left: 16px;
            }
            .action-buttons button {
                min-width: 60px;
            }
            section {
                padding-top: 50px;
            }
            p {
                color: black;
            }
            .slider_detail {
                border-style: solid;
                border-radius: 20px;
            }
            .popup {
                display: none;
                position: fixed;
                z-index: 1001;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgba(0, 0, 0, 0.7);
                text-align: left;
            }
            .popup-content {
                background-color: #fefefe;
                margin: 15% auto;
                padding: 20px;
                border: 1px solid #888;
                width: 80%;
                max-width: 500px;
            }
            .close {
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }
            .close:hover,
            .close:focus {
                color: black;
                text-decoration: none;
                cursor: pointer;
            }
        </style>
    </head>

    <body>
        <div id="outer">
            <div class="header">
                <nav class="navbar">
                    <div class="collapse navbar-collapse single-page-nav">
                        <form action="" method="POST">
                            <div class="navbar-nav">
                                <li class="header-title">
                                    <h3 style="color: white">Filter</h3>
                                </li>
                                <li class="nav-item">
                                    <input type="checkbox" value="byDate" name="choice"/>
                                    <a class="nav-link"><span class="icn"></span>By Date</a>
                                </li>
                                <li class="nav-item">
                                    <input type="checkbox" value="byId" name="choice"/>
                                    <a class="nav-link"><span class="icn"></span>By Id</a>
                                </li>
                                <li class="nav-item">
                                    <input type="checkbox" value="byAccount" name="choice"/>
                                    <a class="nav-link"><span class="icn"></span>By Account</a>
                                </li>
                                <li class="nav-item">
                                    <input type="checkbox" value="byStatus"/>
                                    <a class="nav-link"><span class="icn"></span>By Status</a>
                                </li>
                            </div>
                        </form>
                    </div>
                </nav>
            </div>

            <main id="content-box">
                <div class="banner-section parallax-window" data-parallax="scroll" id="section-1">
                    <div class="container">
                        <div class="item">
                            <form action="slider" method="POST" class=" d-flex align-items-center search-bar">
                                <div class="mx-auto d-flex flex-grow-1">
                                    <input type="text" placeholder="Search..." name="result" class="search-input me-2"/>
                                    <input type="submit" value="Search" class="search-button"/>
                                </div>

                                <div class="d-flex action-buttons">
                                    <button type="submit" class="btn btn-outline-primary me-1" name="crud" value="Show">Show</button>
                                    <button type="submit" class="btn btn-outline-secondary me-1" name="crud" value="Hide">Hide</button>
                                    <button type="submit" class="btn btn-outline-success me-1" name="crud" value="Add">Add</button>
                                    <button type="submit" class="btn btn-outline-warning" name="crud" value="Edit">Edit</button>
                                </div>
                            </form>
                        </div>

                        <section class="row">
                            <div class="container">
                                <c:set var="id" value="0"/>
                                <c:if test="${showAllSlider ne null}">
                                    <c:forEach items="${requestScope.showAllSlider}" var="s">
                                        <div class="row slider_detail">
                                            <div class="item col-md-4">
                                                <div class="tm-work-item-inner">
                                                    <div class="icn"></div>
                                                    <h3 class="text-left">Slider: ${id + 1}</h3>
                                                    <ul class="text-left">
                                                        <li>Id: ${s.getId()}</li> 
                                                        <li>Account: </li>
                                                        <li>Image Url: ${s.getImage_url()}</li>
                                                        <li>Back Link Url: ${s.getBacklink_url()}</li>
                                                        <li>Status: ${s.getStatus()}</li>
                                                        <li><a href="#">Live Demo</a></li>
                                                    </ul>

                                                </div>
                                            </div>
                                        </div>
                                        <br/>
                                    </c:forEach>  
                                </c:if>

                            </div>
                        </section>

                        <!-- Cua so Popup -->
                        <div id="editSliderPopup" class="popup">
                            <div class="popup-content">
                                <span class="close" id="closePopup">&times;</span>
                                <h3>Edit Slider</h3>
                                <form action="">
                                    Image Url:<br/>
                                    <input type="text" name="image_url" placeholder="Image Url..."/><br/>
                                    Back Link Url:<br/>
                                    <input type="text" name="backlink_url" placeholder="Back Link Url..."/><br/>
                                    Status:<br/>
                                    <select name="status"> 
                                        <option value="1">Activate</option>
                                        <option value="0" selected>Inactivate</option>
                                    </select>
                                    <br/>
                                    <button type="submit">Save</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>

        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/jquery.singlePageNav.min.js"></script>
        <script src="js/slick.js"></script>
        <script src="js/parallax.min.js"></script>
        <script src="js/templatemo-script.js"></script>
        <script>
            var popup = document.getElementById("editSliderPopup");
            var editButton = document.querySelector(".btn-outline-warning");
            var closeButton = document.getElementById("closePopup");
            editButton.onclick = function () {
                popup.style.display = "block";
            }
            closeButton.onclick = function () {
                popup.style.display = "none";
            }
            window.onclick = function (event) {
                if (event.target === popup) {
                    popup.style.display = "none";
                }
            }
        </script>
    </body>
</html>
