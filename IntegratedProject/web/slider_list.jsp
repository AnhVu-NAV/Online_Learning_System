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
            .table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }

            th, td {
                padding: 10px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            th {
                background-color: #f2f2f2;
                font-weight: bold;
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
                                <p style="color: red; text-align: left;">${requestScope.error}</p>
                                <c:set var="id" value="0"/>
                                <c:if test="${requestScope.showAllSlider ne null}">
                                    <div class="row slider_detail">
                                        <div class="item col-md-4">
                                            <div class="tm-work-item-inner">
                                                <table border="0" class="table">
                                                    <thead>
                                                        <tr>
                                                            <th>No.</th>
                                                            <th>Id</th>
                                                            <th>Image Url</th>
                                                            <th>Back Link Url</th>
                                                            <th>Status</th>
                                                            <th>Account</th>
                                                            <th>Live demo</th> 
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${requestScope.showAllSlider}" var="s">
                                                            <c:set var="id" value="${id + 1}" />
                                                            <tr>
                                                                <td>${id}</td> 
                                                                <td>${s.getId()}</td>
                                                                <td>${s.getImage_url()}</td>
                                                                <td>${s.getBacklink_url()}</td>
                                                                <td>${s.getStatus()}</td>
                                                                <td>${s.getAccount().getFirstName()} ${s.getAccount().getLastName()}</td>
                                                                <td><a href="slider?id=${s.getId()}">View</a></td> 
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <br/>
                                </c:if>

                                <c:if test="${requestScope.add ne null}">
                                    <div id="add" style="text-align: left;">
                                        <div>
                                            <%
                                                String crudType = "Add";
                                                request.setAttribute("crudType", crudType);
                                            %>
                                            <h3>Add a new slider</h3>
                                            <form action="slider" method="POST">
                                                <table>
                                                    <tr>
                                                        <th>Image Url:</th>
                                                        <th><input type="text" name="imageUrl" placeholder="Image Url..."/></th>
                                                    </tr>
                                                    <tr>
                                                        <td>Back Link Url:</td>
                                                        <td><input type="text" name="backlinkUrl" placeholder="Back Link Url..."/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Status:</td>
                                                        <td>
                                                            <select name="status"> 
                                                                <option value="1">Activate</option>
                                                                <option value="0" selected>Inactivate</option>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                </table>
                                                <div style="margin-left: 150px; margin-top: 20px;">
                                                    <button type="submit">Save</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </c:if>

                                <c:if test="${requestScope.edit ne null}">
                                    <div id="add" style="text-align: left;">
                                        <div>
                                            <h3>Edit a slider</h3>
                                            <form action="slider" method="POST">
                                                <%
                                                    String crudType = "Edit";
                                                    request.setAttribute("crudType", crudType);
                                                %>
                                                <table>
                                                    <tr>
                                                        <th>Slider's id:</th>
                                                        <th><input type="text" name="id" placeholder="Id..."/></th>
                                                    </tr>
                                                    <tr>
                                                        <th>Image Url:</th>
                                                        <th><input type="text" name="imageUrl" placeholder="Image Url..."/></th>
                                                    </tr>
                                                    <tr>
                                                        <td>Back Link Url:</td>
                                                        <td><input type="text" name="backlinkUrl" placeholder="Back Link Url..."/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Status:</td>
                                                        <td>
                                                            <select name="status"> 
                                                                <option value="1">Activate</option>
                                                                <option value="0" selected>Inactivate</option>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                </table>
                                                <div style="margin-left: 150px; margin-top: 20px;">
                                                    <button type="submit">Save</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                        </section>

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

        </script>
    </body>
</html>
