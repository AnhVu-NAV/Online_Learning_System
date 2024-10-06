<%-- 
    Document   : question_list
    Created on : Oct 6, 2024, 11:23:40 AM
    Author     : ADMIN
--%>

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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css"/>
        <link rel="stylesheet" href="lib/plugins/datatables/dataTables.bootstrap.css"/>
        <link rel="stylesheet" href="lib/dist/css/AdminLTE.min.css"/>
        <link rel="stylesheet" href="lib/dist/css/skins/_all-skins.min.css"/>
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">
            <jsp:include page="component/QuestionSidebar.jsp"></jsp:include>
                <div class="content-wrapper">
                    <section class="content-header" style="display: flex;">

                        <!-- Filter -->
                        <h1>Filter</h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <ul style="display: flex; justify-content: flex-start; align-items: center; padding: 0; list-style: none; padding-top: 5px;">
                            <li class="treeview" style="margin-right: 20px;">
                                <a href="QuestionList?action=course"><i class="fa fa-circle-o"></i> <span>By Course</span></a>
                            </li>
                            <li class="treeview" style="margin-right: 20px;">
                                <a href="QuestionList?action=lesson"><i class="fa fa-circle-o"></i> <span>By Lesson</span></a>
                            </li>
                            <li class="treeview" style="margin-right: 20px;">
                                <a href="QuestionList?action=chapter"><i class="fa fa-circle-o"></i> <span>By Chapter</span></a>
                            </li>
                            <li class="treeview" style="margin-right: 20px;">
                                <a href="QuestionList?action=status"><i class="fa fa-circle-o"></i> <span>By Status</span></a>
                            </li>
                            <li class="treeview" style="margin-right: 20px;">
                                <a href="QuestionList?"><i class="fa fa-circle-o"></i> <span>All</span></a>
                            </li>
                        </ul>

                        <!-- Search -->
                        <form action="QuestionList" method="post" style="padding-left: 200px;">
                            <div class="input-group">
                                <input type="text" name="result" class="form-control" placeholder="Search for question...">
                                <span class="input-group-btn">
                                    <button type="submit" id="search-btn" class="btn btn-flat" style="border-style: solid;">
                                        <i class="fa fa-search"></i>
                                    </button>
                                </span>
                            </div>
                        </form>
                        <ol class="breadcrumb">
                            <li><a href="#" data-toggle="modal" data-target="#addQuestionModal"><i class="fa fa-fw fa-plus-circle"></i>Add More</a></li>
                        </ol>
                    </section>
                    <section class="content">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="box">
                                    <div class="box-header" style="display: flex;">
                                        <h3 class="box-title">Question List</h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <form action="action" id="filterForm">
                                            <input type="checkbox" name="course" onchange="updateTable()"> Course&nbsp;&nbsp;&nbsp;&nbsp;
                                            <input type="checkbox" name="quiz" onchange="updateTable()"> Quiz&nbsp;&nbsp;&nbsp;&nbsp;
                                            <input type="checkbox" name="lesson" onchange="updateTable()"> Lesson&nbsp;&nbsp;&nbsp;&nbsp; 
                                            <input type="checkbox" name="chapter" onchange="updateTable()"> Chapter&nbsp;&nbsp;&nbsp;&nbsp;        
                                            <input type="checkbox" name="status" onchange="updateTable()"> Status                                  
                                        </form>
                                    </div>
                                    <div class="box-body">
                                        <p style="color: red;">${requestScope.error}</p>
                                    <c:if test="${requestScope.data ne null}">
                                        <table id="example2" class="table table-bordered table-hover">
                                            <thead>
                                                <tr id="tableHeader">
                                                    <th>Id</th>
                                                    <th>Content</th>
                                                    <th>True Answer</th>
                                                    <th>Explanation</th>
                                                    <th>Level of difficulty</th>
                                                    <th>Show/Hide</th>
                                                    <th>Edit</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="item" items="${requestScope.data}">
                                                    <c:set var="properties" value="${item}"/>
                                                <script>
                                                var obj = ${properties};
                                                var course = obj.getValue().get(2);
                                                var chapter = obj.getValue().get(3);
                                                var lesson = obj.getValue().get(4);
                                                var quiz = obj.getValue().get(5);
                                                var status = obj.getKey().getStatus();
                                                var id = obj.getKey().getId();
                                                </script>
                                                <tr id="tableBody">
                                                    <td>${item.getKey().getId()}</td>  <!-- question id  -->
                                                    <td>${item.getKey().getContent()}</td>  <!-- question content  --> 
                                                    <td>${item.getValue().get(0)}</td>  <!-- option is_true  --> 
                                                    <td>${item.getValue().get(1)}</td>  <!-- option explaination  --> 
                                                    <td> <!-- question level_id -->
                                                        <c:if test="${item.getKey().getLevelId() eq 0}">
                                                            Easy
                                                        </c:if>
                                                        <c:if test="${item.getKey().getLevelId() eq 1}">
                                                            Normal
                                                        </c:if>
                                                        <c:if test="${item.getKey().getLevelId() eq 2}">
                                                            Difficult
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <a href="QuestionList?action=show">Show</a><br/> 
                                                        <a href="QuestionList?action=hide">Hide</a><br/> 
                                                    </td>
                                                    <td><a href="#" onclick="importData(${item.getKey().getId()})" data-toggle="modal" data-target="#editQuestionModal">Edit</a></td> <!-- checked --> 
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </c:if>
                                    <c:if test="${requestScope.data eq null}">
                                        <p>No data</p> 
                                    </c:if>
                                </div> 
                            </div>
                        </div>
                    </div>
                </section>
            </div>
            <footer class="main-footer">
                <div class="pull-right hidden-xs">
                    <b>Version</b> 2.3.12
                </div>
                <strong>Copyright &copy; 2014-2016 <a href="http://almsaeedstudio.com">Almsaeed Studio</a>.</strong> All rights
                reserved.
            </footer>
            <div class="control-sidebar-bg"></div>

            <!-- Add -->
            <div class="modal fade" id="addQuestionModal" tabindex="-1" role="dialog" aria-labelledby="addQuestionModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h3 class="modal-title text-center" id="addQuestionModalLabel">Add a New Question</h3>
                        </div>
                        <div class="modal-body">
                            <!--<p style="color: red;">${requestScope.postError}</p>--> 
                            <form action="QuestionList" method="POST" enctype="multipart/form-data">
                                <div class="form-group">
                                    <label for="fileUpload">Upload File:</label>
                                    <input type="file" class="form-control-file" name="fileUpload"/>
                                </div>
                                <button type="submit" class="btn btn-primary">Save</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Edit --> 
            <script>
                function importData(index) {
                    document.querySelector('input[name="id"]').value = index;
                }
            </script>
            <div class="modal fade" id="editQuestionModal" tabindex="-1" role="dialog" aria-labelledby="addQuestionModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h3 class="modal-title text-center" id="addQuestionModalLabel">Edit a New Question</h3>
                            <form action="QuestionList" method="POST">
                                <input type="hidden" name="id" value=""/> 
                                <table>
                                    <tr>
                                        <td>Content:</td>
                                        <td><input type="tel" name="content"/></td>
                                    </tr>
                                    <tr>
                                        <td>The correct answer:</td>
                                        <td><input type="tel" name="answer"/></td>
                                    </tr>
                                    <tr>
                                        <td>Explanation:</td>
                                        <td><input type="tel" name="explaination"/></td>
                                    </tr>
                                    <tr>
                                        <td>Level of difficulty:</td>
                                        <td>
                                            <select name="level">
                                                <option value="0">Easy</option>
                                                <option value="1">Normal</option>
                                                <option value="2">Difficult</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Type of Question:</td>
                                        <td>
                                            <select name="type">
                                                <option value="0">Multiple Choice</option>
                                                <option value="1">Essay</option>
                                            </select>
                                        </td>
                                    </tr>
                                </table>
                                <input type="submit" value="Submit"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="lib/plugins/jQuery/jquery-2.2.3.min.js"></script>
        <script src="lib/bootstrap/js/bootstrap.min.js"></script>
        <!--<script src="lib/plugins/datatables/jquery.dataTables.min.js"></script>-->
        <!--<script src="lib/plugins/datatables/dataTables.bootstrap.min.js"></script>-->
        <script src="lib/plugins/fastclick/fastclick.js"></script>
        <script src="lib/dist/js/app.min.js"></script>
        <script>
                function updateTable() {
                    const header = document.getElementById("tableHeader");
                    header.innerHTML = "<th>Id</th><th>Content</th><th>True Answer</th><th>Explanation</th><th>Level of difficulty</th><th>Show/Hide</th><th>Edit</th>";
                    const checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
                    checkboxes.forEach(checkbox => {
                        switch (checkbox.name) {
                            case "quiz":
                                header.innerHTML += "<th>Quiz</th>";
                                break;
                            case "course":
                                header.innerHTML += "<th>Course</th>";
                                break;
                            case "lesson":
                                header.innerHTML += "<th>Lesson</th>";
                                break;
                            case "chapter":
                                header.innerHTML += "<th>Chapter</th>";
                                break;
                            case "status":
                                header.innerHTML += "<th>Status</th>";
                                break;
                        }
                    });
                    const bodyContent = document.getElementById("tableBody");
                    bodyContent.innerHTML = "";
                    const questions = [
                        {
                            id: 1,
                            content: "How about your day?",
                            explanation: "X"
                        },
                    ];
                    questions.forEach(question => {
                        let row = "<tr>";
                        row += `<td>` + obj.getKey().getId() + `</td>`;
                        row += `<td>` + obj.getKey().getContent() + `</td>`;
                        row += `<td>` + obj.getValue().get(0) + `</td>`;
                        row += `<td>` + obj.getValue().get(1) + `</td>`;
                        switch (obj.getKey().getLevelId()) {
                            case 0:
                                row += `<td>Easy</td>`;
                                break;
                            case 1:
                                row += `<td>Normal</td>`;
                                break;
                            case 2:
                                row += `<td>Difficult</td>`;
                                break;
                        }
                        row += `<td><a href="QuestionList?action=show">Show</a><br/><a href="QuestionList?action=hide">Hide</a></td>`;
                        row += `<td><td><a href="#" onclick="importData(` + id + `)" data-toggle="modal" data-target="#editQuestionModal">Edit</a></td></td>`;
                        checkboxes.forEach(checkbox => {
                            switch (checkbox.name) {
                                case "quiz":
                                    row += "<td>" + quiz + "</td>";
                                    break;
                                case "course":
                                    row += "<td>" + course + "</td>";
                                    break;
                                case "lesson":
                                    row += "<td>" + lesson + "</td>";
                                    break;
                                case "chapter":
                                    row += "<td>" + chapter + "</td>";
                                    break;
                                case "status":
                                    row += "<td>" + status + "</td>";
                                    break;
                            }
                        });
                        row += "</tr>";
                        bodyContent.innerHTML += row;
                    });
                }
        </script>
    </body>
</html>

