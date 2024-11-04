<%-- 
    Document   : LessonDetails
    Created on : Oct 27, 2024, 11:10:02 AM
    Author     : mocun
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Lesson Details</title>
        <link rel="stylesheet" href="./css/LessonDetails.css">
        <script>
            window.onload = function () {
                handleLessonTypeChange(); // Kiểm tra và hiển thị các phần tử khi trang tải
                updatePreview(); // Hiển thị preview ban đầu nếu có nội dung
            };

            function handleLessonTypeChange() {
                const lessonType = document.getElementById('lessonType').value;
                const videoLinkSection = document.getElementById('videoLinkSection');
                const htmlContentSection = document.getElementById('htmlContentSection');

                if (lessonType === 'LearningMaterial') {
                    videoLinkSection.style.display = 'flex';
                    htmlContentSection.style.display = 'flex';
                } else if (lessonType === 'Quiz') {
                    videoLinkSection.style.display = 'none';
                    htmlContentSection.style.display = 'none';
                }
            }

            function updatePreview() {
                const htmlContent = document.getElementById('html_content').value;
                document.getElementById('preview').innerHTML = htmlContent;
            }
        </script>
    </head>
    <body>
        <div class="LessonDetails">
            <div class="Main">
                <!-- Page Header -->
                <div class="PageHeader">
                    <div class="TitleSection">
                        <div class="Title">
                            <span>&larr;</span>
                            <h3>Lesson Details</h3>
                        </div>
                        <div class="Breadcrumbs">
                            <span class="BreadcrumbItem">Dashboard</span>
                            <span class="BreadcrumbSeparator">/</span>
                            <span class="BreadcrumbItem">Lesson</span>
                            <span class="BreadcrumbSeparator">/</span>
                            <span class="BreadcrumbItem active">Lesson Details</span>
                        </div>
                    </div>
                </div>

                <div class="Wrapper">
                    <!-- Form Section -->
                    <div class="Form">
                        <c:if test="${not empty errorMessage}">
                            <div class="ErrorMessage" style="color: red;">
                                ${errorMessage}
                            </div>
                        </c:if>

                        <form action="${pageContext.request.contextPath}/${lesson != null ? 'updateLesson' : 'createLesson'}" method="post">
                            <input type="hidden" name="lessonId" value="${lesson.lessonId}">

                            <!-- Title and Type -->
                            <div class="FormRow">
                                <div class="FormSelect">
                                    <label class="Label">Title<span class="Required">*</span></label>
                                    <input type="text" class="Input" name="title" placeholder="Title" required
                                           value="${lesson != null ? lesson.title : ''}"/>
                                </div>
                                <div class="FormSelect">
                                    <label class="Label">Type<span class="Required">*</span></label>
                                    <select class="Select" id="lessonType" name="type" onchange="handleLessonTypeChange()" required>
                                        <option value="" disabled ${lesson == null ? 'selected' : ''}>Please select type</option>
                                        <option value="LearningMaterial" ${lesson != null && lesson.lessonTypeId.value == 'LearningMaterial' ? 'selected' : ''}>Learning Material</option>
                                        <option value="Quiz" ${lesson != null && lesson.lessonTypeId.value == 'Quiz' ? 'selected' : ''}>Quiz</option>
                                    </select>
                                </div>
                            </div>

                            <!-- Chapter and Order -->
                            <div class="FormRow">
                                <div class="FormSelect">
                                    <label class="Label">Chapter<span class="Required">*</span></label>
                                    <select class="Select" name="chapter_id" required>
                                        <option value="1" ${lesson != null && lesson.courseId != null && lesson.courseId.chapterId == 1 ? 'selected' : ''}>Chapter 1</option>
                                        <option value="2" ${lesson != null && lesson.courseId != null && lesson.courseId.chapterId == 2 ? 'selected' : ''}>Chapter 2</option>
                                    </select>
                                </div>
                                <div class="FormSelect">
                                    <label class="Label">Order<span class="Required">*</span></label>
                                    <input type="number" class="Input" name="order" placeholder="Order" required
                                           value="${lesson != null ? lesson.order : ''}"/>
                                </div>
                            </div>

                            <!-- Video Link Section for Learning Material -->
                            <div class="FormTextField" id="videoLinkSection" style="display: ${lesson != null && lesson.lessonTypeId != null && lesson.lessonTypeId.value == 'LearningMaterial' ? 'flex' : 'none'};">
                                <label class="Label">Video link<span class="Required">*</span></label>
                                <input type="url" class="Input" name="video_link" placeholder=""
                                       value="${lesson != null && lesson.learningMaterials != null && lesson.learningMaterials.size() > 0 ? lesson.learningMaterials[0].videoUrl : ''}"/>
                            </div>

                            <!-- HTML Content Section for Learning Material -->
                            <div class="FormTextField" id="htmlContentSection" style="display: ${lesson != null && lesson.lessonTypeId != null && lesson.lessonTypeId.value == 'LearningMaterial' ? 'flex' : 'none'};">
                                <label class="Label">HTML content<span class="Required">*</span></label>
                                <textarea class="Input" name="html_content" id="htmlContent" rows="5" oninput="updatePreview()">${lesson != null && lesson.learningMaterials != null && lesson.learningMaterials.size() > 0 ? lesson.learningMaterials[0].htmlContent : ''}</textarea>
                                <span class="Extra">The HTML should be 10 characters.</span>
                            </div>

                            <!-- Footer buttons -->
                            <div class="Footer">
                                <button type="submit" class="Button">${lesson != null ? 'UPDATE' : 'CREATE'}</button>
                                <button type="reset" class="Button">DISCARD CHANGES</button>
                                <button type="button" class="Button" onclick="window.history.back();">CANCEL</button>
                            </div>
                        </form>
                    </div>

                    <!-- Document Preview Section -->
                    <div class="Document">
                        <h3>HTML Content Preview:</h3>
                        <div id="preview" class="PreviewBox"></div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

