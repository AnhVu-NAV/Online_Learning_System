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
        </style>
    </head>

    <body>
        <div id="outer">
            <header class="header order-last" id="tm-header">
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
            </header>

            <button class="navbar-button collapsed" type="button">
                <span class="menu_icon">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </span>
            </button>

            <main id="content-box" class="order-first">
                <div class="banner-section section parallax-window" data-parallax="scroll" id="section-1">
                    <div class="container">
                        <div class="item">
                            <!-- <div class="bg-blue-transparent logo-fa"><span><i class="fas fa-2x fa-atom"></i></span> Simply Amazed</div>
                                      <div class="bg-blue-transparent simple"><p>Your simplest HTML template, the most amazing page ever, yet free!</p></div> -->
                            <form action="index.html" method="POST" class=" d-flex align-items-center search-bar">
                                <div class="mx-auto d-flex flex-grow-1">
                                    <input type="text" placeholder="Search..." name="result" class="search-input me-2"/>
                                    <input type="submit" value="Search" class="search-button"/>
                                </div>

                                <div class="d-flex action-buttons">
                                    <button type="button" class="btn btn-outline-primary me-1">Show</button>
                                    <button type="button" class="btn btn-outline-secondary me-1">Hide</button>
                                    <button type="button" class="btn btn-outline-success me-1">Add</button>
                                    <button type="button" class="btn btn-outline-warning">Edit</button>
                                </div>
                            </form>
                        </div>

                        <section class="row">
                            <div class="container">
                                <%--<c:forEach items="${requestScope.data}" var="s">--%>
                                <div class="row slider_detail">
                                    <div class="item col-md-4">
                                        <div class="tm-work-item-inner">
                                            <div class="icn"></div>
                                            <h3 class="text-left">Slider: </h3>
                                            <ul class="text-left">
                                                <li>Id: </li> 
                                                <li>Account: </li>
                                                <li>Image Url: </li>
                                                <li>Back Link Url: </li>
                                                <li>Status: </li>
                                                <li><a href="#">Live Demo</a></li>
                                            </ul>

                                        </div>
                                    </div>
                                </div>
                                <br/>
                                <%--</c:forEach>--%><div class="row slider_detail">
                                <div class="item col-md-4">
                                    <div class="tm-work-item-inner">
                                        <div class="icn"></div>
                                            <h3 class="text-left">Slider: </h3>
                                            <ul class="text-left">
                                                <li>Id: </li> 
                                                <li>Account: </li>
                                                <li>Image Url: </li>
                                                <li>Back Link Url: </li>
                                                <li>Status: </li>
                                                <li><a href="#">Live Demo</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>

                        <!-- <section class="gallery-section section parallax-window" data-parallax="scroll" data-image-src="img/section-3-bg.jpg" id="section-3">
                          
                        </section> -->

                        <!-- <section class="contact-section section" id="section-4">
                          
                        </section> -->
                        </main>
                    </div>

                    <script src="js/jquery-3.3.1.min.js"></script>
                    <script src="js/bootstrap.bundle.min.js"></script>
                    <script src="js/jquery.singlePageNav.min.js"></script>
                    <script src="js/slick.js"></script>
                    <script src="js/parallax.min.js"></script>
                    <script src="js/templatemo-script.js"></script>
                    </body>
                    </html>
