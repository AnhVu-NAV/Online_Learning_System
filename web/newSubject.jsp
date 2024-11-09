<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    Integer roleId = (session != null) ? (Integer) session.getAttribute("roleId") : null;

    // Nếu roleId trống hoặc khác 6, chuyển hướng về trang chủ
    if (roleId == null || roleId != 6) {
        response.sendRedirect(request.getContextPath() + "/home");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>New Course</title>
        <link rel="stylesheet" href="css/newSubject.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> <!-- Include jQuery for AJAX -->
    </head>

    <body>
        <div class="container">
            <h1>New Course</h1>

            <!-- Form tạo mới khóa học -->
            <form action="CourseController?action=insert" method="POST" enctype="multipart/form-data">
                <!-- Input Tên khóa học -->
                <div class="form-group">
                    <label for="name">Course Name</label>
                    <input type="text" id="name" name="name" placeholder="Enter course name" class="form-input" required>
                </div>

                <!-- Input Subtitle -->
                <div class="form-group">
                    <label for="subtitle">Subtitle</label>
                    <input type="text" id="subtitle" name="subtitle" placeholder="Enter course subtitle" class="form-input">
                </div>

                <!-- Input hình thumbnail (cho phép tải lên tối đa 5 ảnh) -->
                <div class="form-group">
                    <label for="thumbnail">Thumbnail Images (Max 5)</label>
                    <input type="file" id="thumbnail" name="thumbnail[]" class="form-input" multiple accept="image/*" onchange="previewImages()">
                    <!-- Preview container for selected images -->
                    <div id="imagePreview" class="image-preview-container"></div>
                </div>


                <!-- Select danh mục khóa học -->
                <div class="form-group">
                    <label for="category">Category</label>
                    <select id="category" name="categoryId" class="form-input" required>
                        <c:forEach var="category" items="${categoryList}">
                            <option value="${category.id}">${category.value}</option>
                        </c:forEach>
                    </select>
                </div>



                <!-- Select chủ sở hữu khóa học -->
                <div class="form-group">
                    <label for="owner">Owner</label>
                    <select id="owner" name="ownerId" class="form-input" >
                        <c:forEach var="expert" items="${expertList}">
                            <option value="${expert.id}">${expert.firstName} ${expert.lastName}</option>
                        </c:forEach>
                    </select>
                </div>

                <!-- Select trạng thái khóa học -->
                <div class="form-group">
                    <label for="status">Status</label>
                    <select id="status" name="status" class="form-input">
                        <option value="1">Active</option>
                        <option value="0">Inactive</option>
                    </select>
                </div>

                <!-- Input mô tả khóa học -->
                <div class="form-group" style="grid-column: span 2;">
                    <label for="description">Description</label>
                    <textarea id="description" name="description" placeholder="Enter course description" class="form-input" required></textarea>
                </div>

                <!-- Checkbox chọn Tagline -->
                <div class="form-group" style="grid-column: span 2;">
                    <label for="tagline">Tagline</label>
                    <div id="taglines-list" class="tagline-container">
                        <c:forEach var="tagline" items="${taglineList}">
                            <div>
                                <input type="checkbox" id="tagline${tagline.id}" name="tagline[]" value="${tagline.id}">
                                <label for="tagline${tagline.id}">${tagline.name}</label>
                            </div>
                        </c:forEach>
                    </div>
                    <!-- Input to add a new tagline -->
                    <div class="form-group">
                        <input type="text" id="newTaglineInput" placeholder="Enter new tagline" class="form-input">
                        <button type="button" onclick="saveTagline()">Save Tagline</button>
                    </div>
                </div>

                <!-- Add a hidden input for each description in the form submission -->
                <div id="hiddenDescriptions"></div>

                <!-- Nút lưu -->
                <div class="form-footer">
                    <button type="submit" class="submit-btn">Save</button>
                    <button type="button" class="cancel-btn" onclick="location.href = 'CourseController?action=list';">Cancel</button>
                </div>
            </form>
        </div>

        <script>
            // Function to save a new tagline
            function saveTagline() {
                const newTagline = $('#newTaglineInput').val().trim();
                if (newTagline === '') {
                    alert('Please enter a tagline.');
                    return;
                }

                // Send an AJAX request to save the tagline
                $.ajax({
                    url: 'CourseController?action=saveTagline',
                    type: 'POST',
                    data: {taglineName: newTagline},
                    success: function (response) {
                        // If the tagline is saved successfully, add it to the list
                        if (response.success) {
                            const newTaglineId = response.id;
                            const newTaglineDiv = `<div>
                                <input type="checkbox" id="tagline${newTaglineId}" name="tagline[]" value="${newTaglineId}">
                                <label for="tagline${newTaglineId}">${newTagline}</label>
                            </div>`;
                            $('#taglines-list').append(newTaglineDiv);
                            $('#newTaglineInput').val(''); // Clear the input
                        } else {
                            alert('Failed to save the tagline.');
                        }
                    },
                    error: function () {
                        alert('An error occurred while saving the tagline.');
                    }
                });
            }

            let selectedFiles = [];
            let imageDescriptions = {}; // Object to store descriptions for each image by index

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
                if (selectedFiles.length > 5) {
                    alert('You can upload a maximum of 5 images.');
                    selectedFiles = selectedFiles.slice(0, 5);
                }

                // Loop through the accumulated files and create image previews
                selectedFiles.forEach((file, index) => {
                    if (!file.type.startsWith('image/')) {
                        return;// Skip non-image files
                    }

                    const reader = new FileReader();

                    reader.onload = function (e) {
                        const imgElement = document.createElement('img');
                        imgElement.src = e.target.result;
                        imgElement.classList.add('image-preview');

                        // Create a delete button for each preview
                        const deleteButton = document.createElement('button');
                        deleteButton.innerText = 'x';
                        deleteButton.classList.add('delete-btn');
                        deleteButton.onclick = function () {
                            removeImage(index);
                        };

                        // Create a description input for each image
                        const descriptionInput = document.createElement('input');
                        descriptionInput.type = 'text';
                        descriptionInput.classList.add('description-input');
                        descriptionInput.placeholder = 'Enter description';
                        descriptionInput.value = imageDescriptions[index] || ''; // Load existing description if available

                        // Update the imageDescriptions object when description changes
                        descriptionInput.oninput = function () {
                            imageDescriptions[index] = descriptionInput.value;
                        };

                        // Add the image, delete button, and description input to a wrapper
                        const wrapper = document.createElement('div');
                        wrapper.classList.add('image-wrapper');
                        wrapper.appendChild(imgElement);
                        wrapper.appendChild(deleteButton);
                        wrapper.appendChild(descriptionInput);

                        previewContainer.appendChild(wrapper);
                    };

                    reader.readAsDataURL(file);
                });

                // Clear the file input after processing to prevent re-triggering on the same files
                document.getElementById('thumbnail').value = '';

                // Update the file input with the selected files
                updateFileInput();
            }

            function addDescriptionsToForm() {
                const hiddenDescriptions = document.getElementById('hiddenDescriptions');
                hiddenDescriptions.innerHTML = ''; // Clear previous hidden inputs

                // Add each description as a hidden input field
                Object.keys(imageDescriptions).forEach(index => {
                    const descriptionInput = document.createElement('input');
                    descriptionInput.type = 'hidden';
                    descriptionInput.name = 'thumbnailDescription[]'; // Array notation for multiple descriptions
                    descriptionInput.value = imageDescriptions[index];
                    hiddenDescriptions.appendChild(descriptionInput);
                });
            }

            // Function to remove an image and its description
            function removeImage(index) {
                selectedFiles.splice(index, 1); // Remove the file from the list
                delete imageDescriptions[index]; // Remove the description
                updateFileInput(); // Update the file input after removing an image
                previewImages(); // Refresh the preview to reflect the change
            }

            // Function to update the file input field with the current selected files
            function updateFileInput() {
                const dataTransfer = new DataTransfer();
                selectedFiles.forEach(file => dataTransfer.items.add(file));
                document.getElementById('thumbnail').files = dataTransfer.files;
            }

            // Event listener for file input change
            document.getElementById('thumbnail').addEventListener('change', previewImages);

        </script>
    </body>

</html>
