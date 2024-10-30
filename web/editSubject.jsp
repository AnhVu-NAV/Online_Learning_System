<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Course</title>
        <link rel="stylesheet" href="css/editSubject.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </head>
    <body>
        <div class="edit-course-container">
            <h1 class="edit-course-title">Edit Course</h1>

            <!-- Form chỉnh sửa khóa học -->
            <form action="CourseController?action=update" method="POST" enctype="multipart/form-data" class="edit-course-form">
                <input type="hidden" name="id" value="${course.id}">

                <!-- Input tên khóa học -->
                <div class="form-group">
                    <label for="title" class="form-label">Course Title:</label>
                    <input type="text" name="title" class="form-input" value="${course.title}" required>
                </div>

                <!-- Input phụ đề -->
                <div class="form-group">
                    <label for="subtitle" class="form-label">Subtitle:</label>
                    <input type="text" name="subtitle" class="form-input" value="${course.subtitle}" required>
                </div>

                <!-- Select danh mục -->
                <div class="form-group">
                    <label for="category" class="form-label">Category:</label>
                    <select name="categoryId" class="form-select">
                        <c:forEach var="category" items="${categoryList}">
                            <option value="${category.id}" ${category.id == course.categoryId ? 'selected' : ''}>${category.value}</option>
                        </c:forEach>
                    </select>
                </div>

                <!-- Select chuyên gia -->
                <div class="form-group">
                    <label for="expert" class="form-label">Expert:</label>
                    <select name="expertId" class="form-select">
                        <c:forEach var="expert" items="${expertList}">
                            <option value="${expert.id}" ${expert.id == course.expertId ? 'selected' : ''}>${expert.firstName} ${expert.lastName}</option>
                        </c:forEach>
                    </select>
                </div>

                <!-- Input thời gian khóa học -->
                <div class="form-group">
                    <label for="duration" class="form-label">Total Duration (hours):</label>
                    <input type="number" name="duration" class="form-input" value="${course.totalDuration}" required>
                </div>

                <!-- Input mô tả khóa học -->
                <div class="form-group">
                    <label for="description" class="form-label">Description:</label>
                    <textarea name="description" class="form-textarea" >${course.description}</textarea>
                </div>

                <!-- Tagline chọn sẵn -->
                <div class="form-group">
                    <label for="tagline" class="form-label">Tagline</label>
                    <div class="tagline-container">
                        <c:forEach var="tagline" items="${taglineList}">
                            <div class="tagline-item">
                                <input type="checkbox" class="tagline-checkbox" name="tagline[]" value="${tagline.id}" ${tagline.checked ? 'checked' : ''}>
                                <label>${tagline.name}</label>
                            </div>
                        </c:forEach>
                    </div>
                </div>


                <!-- Input hình thumbnail (cho phép tải lên tối đa 5 ảnh) -->
                <div class="form-group">
                    <label for="thumbnail" class="form-label">Thumbnail Images (Max 5)</label>
                    <input type="file" id="thumbnail" name="thumbnail[]" class="form-file-input" multiple accept="image/*" onchange="previewImages()">

                    <!-- Preview container for existing and newly selected images -->
                    <div id="imagePreview" class="image-preview-container">
                        <!-- Display existing images -->
                        <c:forEach var="imageUrl" items="${thumbnailUrls}">
                            <div class="image-wrapper">
                                <img src="${imageUrl}" class="image-preview">
                                <button type="button" class="delete-btn" onclick="removeExistingImage('${imageUrl}')">×</button>
                            </div>
                        </c:forEach>
                    </div>
                </div>


                <!-- Nút cập nhật -->
                <button type="submit" class="btn btn-primary">Update Course</button>
                <!-- Nút cancel -->
                <button type="button" class="btn btn-danger" onclick="window.location.href = 'CourseController?action=list'">Cancel</button>
            </form>
        </div>

        <script>
            let selectedFiles = [];
            let imagesToRemove = []; // Array to keep track of images marked for removal

            function previewImages() {
                const previewContainer = document.getElementById('imagePreview');
                const newFiles = Array.from(document.getElementById('thumbnail').files);

                // Add the new files to the existing list, ensuring no duplicates
                newFiles.forEach(file => {
                    if (!selectedFiles.some(selectedFile => selectedFile.name === file.name && selectedFile.size === file.size)) {
                        selectedFiles.push(file);
                    }
                });

                // Clear the previous preview container content
                previewContainer.innerHTML = '';

                // Limit the total number of images to 5
                if (selectedFiles.length + imagesToRemove.length > 5) {
                    alert('You can upload a maximum of 5 images.');
                    selectedFiles = selectedFiles.slice(0, 5 - imagesToRemove.length);
                }

                // Show previews of selected images
                selectedFiles.forEach((file, index) => {
                    if (!file.type.startsWith('image/')) {
                        return;
                    }

                    const reader = new FileReader();
                    reader.onload = function (e) {
                        const imgElement = document.createElement('img');
                        imgElement.src = e.target.result;
                        imgElement.classList.add('image-preview');

                        const deleteButton = document.createElement('button');
                        deleteButton.innerText = '×';
                        deleteButton.classList.add('delete-btn');
                        deleteButton.onclick = function () {
                            removeImage(index);
                        };

                        const wrapper = document.createElement('div');
                        wrapper.classList.add('image-wrapper');
                        wrapper.appendChild(imgElement);
                        wrapper.appendChild(deleteButton);

                        previewContainer.appendChild(wrapper);
                    };
                    reader.readAsDataURL(file);
                });

                // Show previews of existing images
                imagesToRemove.forEach(url => {
                    const imgElement = document.createElement('img');
                    imgElement.src = url;
                    imgElement.classList.add('image-preview');

                    const deleteButton = document.createElement('button');
                    deleteButton.innerText = '×';
                    deleteButton.classList.add('delete-btn');
                    deleteButton.onclick = function () {
                        removeExistingImage(url);
                    };

                    const wrapper = document.createElement('div');
                    wrapper.classList.add('image-wrapper');
                    wrapper.appendChild(imgElement);
                    wrapper.appendChild(deleteButton);

                    previewContainer.appendChild(wrapper);
                });

                // Update the file input with the selected files
                updateFileInput();
            }

            function removeExistingImage(url) {
                imagesToRemove.push(url); // Mark the image for removal
                previewImages(); // Refresh the preview to reflect the change
            }

            function removeImage(index) {
                selectedFiles.splice(index, 1); // Remove the file from the list
                previewImages(); // Refresh the preview to reflect the change
            }

            function updateFileInput() {
                const dataTransfer = new DataTransfer();
                selectedFiles.forEach(file => dataTransfer.items.add(file));
                document.getElementById('thumbnail').files = dataTransfer.files;
            }

            document.getElementById('thumbnail').addEventListener('change', previewImages);

        </script>
    </body>
</html>
