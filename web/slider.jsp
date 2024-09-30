<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Question List Control</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="lib/dist/css/AdminLTE.min.css"/>
        <link rel="stylesheet" href="lib/dist/css/skins/_all-skins.min.css"/>
        <style>
            .pagination {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-top: 20px;
                padding-bottom: 20px;
            }
            .pagination .links {
                display: flex;
                padding-left: 400px;
                flex-grow: 1;
            }
            .pagination p {
                margin: 0;
                padding: 8px 12px;
                color: #007BFF;
            }
            .pagination a {
                margin: 0 5px;
                padding: 8px 12px;
                border: 1px solid #007BFF;
                color: #007BFF;
                text-decoration: none;
                border-radius: 4px;
                transition: background-color 0.3s, color 0.3s;
            }
            .pagination a:hover {
                background-color: #007BFF;
                color: white;
            }
            .active {
                background-color: red;
                color: white;
            }
            .input-group input.form-control {
                width: 400px;
                border-radius: 20px;
            }
        </style>
    </head>
    <body class="skin-blue">
        <div class="wrapper">
            <jsp:include page="component/DashboardSidebar.jsp"></jsp:include>
                <div class="main">
                    <div class="container-fluid full-width">
                        <section class="content-header" style="display: flex;">
                            <h1>Filter</h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <ul style="display: flex; justify-content: flex-start; align-items: center; padding: 0; list-style: none; padding-top: 10px;">
                                <li class="treeview" style="margin-right: 20px;">
                                    <a href="slider?filter=id"><i class="fa fa-circle-o"></i> <span>By Id</span></a>
                                </li>

                                <li class="treeview" style="margin-right: 20px;">
                                    <a href="slider?filter=account"><i class="fa fa-circle-o"></i> <span>By Account</span></a>
                                </li>

                                <li class="treeview" style="margin-right: 20px;">
                                    <a href="slider?filter=status"><i class="fa fa-circle-o"></i> <span>By Status</span></a>
                                </li>
                            </ul>
                            <form action="slider" method="post" style="padding-left: 200px;">
                                <div class="input-group">
                                    <input type="text" name="result" class="form-control" placeholder="Search...">
                                    <span class="input-group-btn">
                                        <button type="submit" id="search-btn" class="btn btn-flat" style="border-style: solid;">
                                            <i class="fa fa-search"></i>
                                        </button>
                                    </span>
                                </div>
                            </form>
                            <ol class="breadcrumb">
                                <li><a href="slider?crud=Add"><i class="fa fa-fw fa-plus-circle"></i>Add More</a></li>
                            </ol>
                        </section>
                        <section class="content">
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="box">
                                        <p style="color: red; text-align: left;">${requestScope.error}</p>
                                    <c:if test="${requestScope.showAllSlider ne null}"> 
                                        <div class="box-header">
                                            <h3 class="box-title">Question List</h3>
                                        </div>
                                        <div class="box-body">
                                            <table id="example2" class="table table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>No.</th>
                                                        <th>Id</th>
                                                        <th>Image Url</th>
                                                        <th>Back Link Url</th>
                                                        <th>Status</th>
                                                        <th>Account</th>
                                                        <th>Show/Hide</th> 
                                                        <th>Live demo</th> 
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:set var="number" value="0"/>
                                                    <c:forEach items="${requestScope.showAllSlider}" var="s" varStatus="status">
                                                        <c:if test="${number >= 10}">
                                                            <c:set var="number" value="${number - 10}"/>
                                                        </c:if>
                                                        <c:if test="${number < 10}">
                                                            <c:set var="number" value="${number + 1}" />
                                                        </c:if>
                                                        <tr>
                                                            <td>${number}</td> 
                                                            <td>${s.getId()}</td> 
                                                            <td>${s.getImageURL()}</td>
                                                            <td>${s.getBacklinkURL()}</td>
                                                            <td>${s.getStatus()}</td>
                                                            <td>Name of author</td>
                                                            <td> 
                                                                <a href="slider?action=show&id=${s.getId()}">Show</a><br/> 
                                                                <a href="slider?action=hide&id=${s.getId()}">Hide</a>
                                                            </td>
                                                            <td><a href="slider?action=view&id=${s.getId()}">View</a></td> 
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div> 

                                        <c:set var="numberPages" value="${requestScope.numberPages}"/>
                                        <c:set var="page" value="${requestScope.page}"/>
                                        <div class="pagination">
                                            <p class="text-left">Showing ${page} of ${numberPages} pages</p>  
                                            <div class="links">
                                                <c:forEach begin="1" end="${numberPages}" var="i">
                                                    <a href="slider?pageIndex=${i}" class="${page == i ? 'active' : ''}">${i}</a> 
                                                </c:forEach>
                                            </div>
                                        </div> 
                                    </c:if>

                                    <c:if test="${requestScope.add ne null}">
                                        <div id="add" style="display: flex; text-align: left; justify-content: center;">
                                            <div>
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
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
        <script src="lib/bootstrap/js/bootstrap.min.js"></script>
        <script src="lib/plugins/fastclick/fastclick.js"></script>
        <script src="lib/dist/js/app.min.js"></script>
    </body>
</html>
