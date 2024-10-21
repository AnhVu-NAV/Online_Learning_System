<%-- 
    Document   : ViewRegistrationDetail
    Created on : Oct 13, 2024, 9:28:04 PM
    Author     : 84941
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Details</title>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <style>
            .wrapper {
                display: flex;
                height: 150vh;

            }

            #ViewRegistrationDetail {
                flex-grow: 1;
                display: flex;
                justify-content: center;
                background-color: #eee;
                padding: 20px;
            }
            .form-container {
                background: white;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 100%;
                max-width: 500px;
            }
        </style>
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="/component/AdminDashboardSidebar.jsp"></jsp:include>
            <c:set var="setting" value="${requestScope.setting}"></c:set>
                <section style="background-color: #eee;" id="ViewRegistrationDetail">
                    <div class="container py-5">                     
                        <div class="row">
                            <div class="col">
                                <nav aria-label="breadcrumb" class="bg-body-tertiary rounded-3 p-3 mb-4">
                                    <ol class="breadcrumb mb-0">
                                        <li class="breadcrumb-item"><a href="SaleRegostrationDashboardController?service=viewAllRegistration">Registration List</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">Registration Details</li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                        <section id="ViewRegistrationDetail">
                            <div class="form-container">
                                <form action="${pageContext.request.contextPath}/SaleRegostrationDashboardController" method="post">
                                <div class="form-group mb-3">
                                    <label for="settingValue">Subject Title</label>
                                    <input type="text" class="form-control" readonly="true" value="${setting.getId()}" name="subjectName">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="settingValue">Price</label>
                                    <input type="text" class="form-control" readonly="true" value="${setting.getId()}" name="subjectName">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="settingValue">Full Name</label>
                                    <input type="text" class="form-control" value="${setting.getValue()}" name="settingValue">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="settingValue">Gender</label>
                                    <input type="text" class="form-control" value="${setting.getValue()}" name="settingValue">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="settingValue">Email</label>
                                    <input type="text" class="form-control" value="${setting.getValue()}" name="settingValue">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="settingValue">Phone</label>
                                    <input type="text" class="form-control" value="${setting.getValue()}" name="settingValue">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="settingValue">Registration Time</label>
                                    <input type="text" class="form-control" value="${setting.getValue()}" name="settingValue">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="settingStatus">Status</label>
                                    <select id="settingStatus" class="form-control" name="settingStatus">
                                        <option value="-1">Cancel</option>
                                        <option value="0">Submitted</option>
                                        <option value="1">Learning</option>
                                        <option value="2">Expired</option>
                                        <option value="3">Finished</option>
                                    </select>
                                </div>
                                <div class="form-group mb-3">
                                    <label for="settingValue">Valid from</label>
                                    <input type="text" class="form-control" name="settingDescription" value="${setting.getDescription()}">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="settingValue">Valid to</label>
                                    <input type="text" class="form-control" name="settingDescription" value="${setting.getDescription()}">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="settingValue">Note</label>
                                    <input type="text" class="form-control" name="settingDescription" value="${setting.getDescription()}">
                                </div>
                                <button type="submit" class="btn btn-primary w-100">Update</button>
                                <input type="hidden" name="service" value="updateSetting">
                            </form>
                        </div>
                    </section>
                </div>
            </section>



        </div>
    </body>
</html>
