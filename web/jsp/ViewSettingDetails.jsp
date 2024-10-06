<%-- 
    Document   : ViewSettingDetails
    Created on : Sep 24, 2024, 8:25:33 PM
    Author     : 84941
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,model.Setting,model.SettingType" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Setting Details</title>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <style>
            .wrapper {
                display: flex;
                height: 100vh;

            }

            #ViewSettingDetail {
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
                <section style="background-color: #eee;" id="ViewSettingDetail">
                    <div class="container py-5">                     
                    <div class="row">
                        <div class="col">
                            <nav aria-label="breadcrumb" class="bg-body-tertiary rounded-3 p-3 mb-4">
                                <ol class="breadcrumb mb-0">
                                    <li class="breadcrumb-item"><a href="SettingDashboardController?service=viewAllSetting">Setting List</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Setting Details</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                    <section id="ViewSettingDetail">

                        <div class="form-container">
                            <form action="${pageContext.request.contextPath}/SettingDashboardController" method="post">
                                <div class="form-group mb-3">
                                    <label for="settingValue">Setting ID</label>
                                    <input type="text" class="form-control" readonly="true" value="${setting.getId()}" name="settingId">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="settingType">Setting Type</label>
                                    <select  class="form-control" name="settingTypeId">
                                        <c:forEach items="${requestScope.settingType}" var="settingType">
                                            <option value="${settingType.getId()}"  ${setting.getSettingTypeId()==settingType.getId()?"selected":""}>${settingType.getName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group mb-3">
                                    <label for="settingValue">Value</label>
                                    <input type="text" class="form-control" value="${setting.getValue()}" name="settingValue">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="settingStatus">Status</label>
                                    <select id="settingStatus" class="form-control" name="settingStatus">
                                        <option value="1" ${setting.getStatus()==1?"selected":""}>Activated</option>
                                        <option value="0" ${setting.getStatus()==0?"selected":""}>Deactivated</option>
                                    </select>
                                </div>
                                <div class="form-group mb-3">
                                    <label for="settingValue">Description</label>
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
