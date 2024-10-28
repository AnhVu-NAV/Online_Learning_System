<%-- 
    Document   : LessonDetails
    Created on : Oct 27, 2024, 11:10:02 AM
    Author     : mocun
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lesson Details</title>
    <link rel="stylesheet" href="./css/LessonDetails.css"> <!-- Liên kết file CSS -->
</head>
<body>
<div class="LessonDetails">
    <div class="Main">
        <!-- Page Header -->
        <div class="PageHeader">
            <!-- Title Section with Breadcrumb below -->
            <div class="TitleSection">
                <div class="Title">
                    <span>&larr;</span> <!-- Arrow icon or symbol -->
                    <h3>Lesson Details</h3>
                </div>
                <!-- Breadcrumb Navigation -->
                <div class="Breadcrumbs">
                    <span class="BreadcrumbItem">Dashboard</span>
                    <span class="BreadcrumbSeparator">/</span>
                    <span class="BreadcrumbItem">Lesson</span>
                    <span class="BreadcrumbSeparator">/</span>
                    <span class="BreadcrumbItem active">Lesson Details</span>
                </div>
            </div>

            <!-- Profile Section -->
            <div class="ProfileSection">
                <div class="Profile">
                    <img class="Rectangle1" src="https://via.placeholder.com/24x24" alt="Profile"/>
                    <span class="DerekAlvarado">Derek Alvarado</span>
                    <div class="ChevronDown">
                        <span>&#9662;</span> <!-- Use an icon or SVG for the chevron -->
                    </div>
                </div>
            </div>
        </div>

        <!-- Main Content Area -->
        <div class="Wrapper">
            <!-- Form Section -->
            <div class="Form">
                <div class="FormRow">
                    <div class="FormSelect">
                        <label class="Label">Name<span class="Required">*</span></label>
                        <input type="text" class="Input" placeholder="Topic"/>
                    </div>
                    <div class="FormSelect">
                        <label class="Label">Type<span class="Required">*</span></label>
                        <select class="Select">
                            <option>Subject Topic</option>
                        </select>
                    </div>
                </div>

                <div class="FormRow">
                    <div class="FormSelect">
                        <label class="Label">Topic<span class="Required">*</span></label>
                        <select class="Select">
                            <option>a</option>
                        </select>
                    </div>
                    <div class="FormSelect">
                        <label class="Label">Order<span class="Required">*</span></label>
                        <input type="number" class="Input" placeholder="2"/>
                    </div>
                </div>

                <div class="FormTextField">
                    <label class="Label">Video link<span class="Required">*</span></label>
                    <input type="url" class="Input" placeholder=""/>
                </div>

                <div class="FormTextField">
                    <label class="Label">HTML content<span class="Required">*</span></label>
                    <textarea class="Input" rows="5"></textarea>
                    <span class="Extra">The html should be 10 characters.</span>
                </div>

                <!-- Footer buttons -->
                <div class="Footer">
                    <button class="Button" disabled>CREATE</button>
                    <button class="Button" disabled>DISCARD CHANGES</button>
                    <button class="Button">CANCEL</button>
                </div>
            </div>

            <!-- Document Preview Section -->
            <div class="Document">
                <img class="Image4" src="https://via.placeholder.com/362x322" alt="Document Preview"/>
                <div class="ReplaceFileButtonWrapper">
                    <button class="Button">
                        <span>&#128190;</span> <!-- Icon for file upload -->
                        <span>REPLACE ANOTHER FILE</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
