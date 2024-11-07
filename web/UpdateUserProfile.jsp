<%-- 
    Document   : UpdateUserProfile
    Created on : Sep 23, 2024, 11:59:10 AM
    Author     : AnhVuNAV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Update User Profile</title>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="css/UpdateUserProfile.css">
    </head>
    <body>
        <div class="ccontainer">
            <div class="nav-buttons">
                <div class="top-page">
                    <div>
                        <button class="btn btn-back" onclick="history.back()">← Back</button>
                    </div>
                    <div>
                        <h2>Update User Profile</h2>
                    </div>
                </div>
            </div>

            <div class="profile-container">
                <!-- Form for updating profile -->
                <form action="UpdateUserProfile" method="post">
                <form action="UpdateUserProfileServlet" method="post">
                    <input type="hidden" name="userId" value="${user.id}" />

                    <!-- Profile Image Section -->
                    <div class="profile-card">
<!--                        <img src="${user.imageUrl}" alt="Profile Image" class="profile-image">-->
                        <img id="imagePreview" src="${user.imageUrl}" alt="Profile Image" class="profile-image" style="display: block; max-width: 200px; margin-top: 10px;">
                        <div class="details">
                            <label for="imageFile"><strong>Upload New Profile Image:</strong></label>
                            <input type="file" name="imageFile" id="profileImage" accept="image/*" onchange="previewImage()"/>
                        </div>
                    </div>

                    <!-- Personal Details Section -->
                    <div class="details-container">
                        <div class="details-card">
                            <div class="card-header">
                                <h2 class="card-title">Personal Details</h2>
                            </div>
                            <div class="details-list">
                                <div class="details-item">
                                    <label for="firstName"><strong>First Name:</strong></label>
                                    <input type="text" name="firstName" id="firstName" value="${user.firstName}" required />
                                    <span id="firstNameError" class="error-message"></span> <!-- Thêm phần hiển thị lỗi -->
                                </div>
                                <div class="details-item">
                                    <label for="lastName"><strong>Last Name:</strong></label>
                                    <input type="text" name="lastName" id="lastName" value="${user.lastName}" required />
                                    <span id="lastNameError" class="error-message"></span>
                                </div>
                                <div class="details-item">
                                    <label for="dob"><strong>Date of Birth:</strong></label>
                                    <input type="date" name="dob" id="dob" value="<fmt:formatDate value='${user.dob}' pattern='yyyy-MM-dd'/>" required />
                                </div>
                                <div class="details-item">
                                    <label><strong>Gender:</strong></label>
                                    <label><input type="radio" name="gender" value="1" ${user.gender == 1 ? 'checked' : ''}> Male</label>
                                    <label><input type="radio" name="gender" value="2" ${user.gender == 2 ? 'checked' : ''}> Female</label>
<!--                                <label><input type="radio" name="gender" value="true" ${user.gender ? 'checked' : ''}> Male</label>
                                    <label><input type="radio" name="gender" value="false" ${!user.gender ? 'checked' : ''}> Female</label>-->
                                    <label><input type="radio" name="gender" value="3" ${user.gender != 1 && user.gender != 2 ? 'checked' : ''}> Other</label>
                                </div>
                            </div>
                        </div>

                        <!-- Contact Details Section -->
                        <div class="details-card">
                            <div class="card-header">
                                <h2 class="card-title">Contact Details</h2>
                            </div>
                            <div class="details-list">
                                <div class="details-item">
                                    <label for="primaryEmail"><strong>Primary Email:</strong></label>
                                    <input type="email" name="primaryEmail" id="primaryEmail" value="${user.primaryEmail}" required oninput="updatePreferredContactDropdown()"/>
                                    <span id="primaryEmailError" class="error-message"></span>
                                </div>
                                <div class="details-item">
                                    <label for="secondaryEmail"><strong>Secondary Email:</strong></label>
                                    <input type="email" name="secondaryEmail" id="secondaryEmail" value="${user.secondaryEmail}" oninput="updatePreferredContactDropdown()"/>
                                </div>
                                <div class="details-item">
                                    <label for="firstPhone"><strong>Primary Phone:</strong></label>
                                    <input type="text" name="firstPhone" id="firstPhone" value="${user.firstPhone}" oninput="updatePreferredContactDropdown()"/>
                                </div>
                                <div class="details-item">
                                    <label for="secondPhone"><strong>Secondary Phone:</strong></label>
                                    <input type="text" name="secondPhone" id="secondPhone" value="${user.secondPhone}" oninput="updatePreferredContactDropdown()"/>
                                </div>
                            </div>
                        </div>

                        <!-- Address Section -->
                        <div class="details-card">
                            <div class="card-header">
                                <h2 class="card-title">Address</h2>
                            </div>
                            <div class="details-list">
                                <div class="details-item">
                                    <label for="address"><strong>Address:</strong></label>
                                    <input type="text" name="address" id="address" value="${user.address}" />
                                </div>
                            </div>
                        </div>

                        <!-- Preferred Contact Method Section -->
                        <div class="details-card">
                            <div class="card-header">
                                <h2 class="card-title">Preferred Contact Method</h2>
                            </div>
                            <div class="details-list">
                                <div class="details-item">
                                    <label for="preferContact"><strong>Preferred Contact:</strong></label>
                                    <select name="preferContact" id="preferContact">
                                        <c:if test="${not empty user.primaryEmail}">
                                            <option value="Primary Email" 
                                                    ${user.preferContact == 'Primary Email' || empty user.preferContact ? 'selected' : ''}>
                                                Primary Email
                                            </option>
                                        </c:if>
                                        <c:if test="${not empty user.secondaryEmail}">
                                            <option value="Secondary Email" 
                                                    ${user.preferContact == 'Secondary Email' ? 'selected' : ''}>
                                                Secondary Email
                                            </option>
                                        </c:if>
                                        <c:if test="${not empty user.firstPhone}">
                                            <option value="Primary Phone" 
                                                    ${user.preferContact == 'Primary Phone' ? 'selected' : ''}>
                                                Primary Phone
                                            </option>
                                        </c:if>
                                        <c:if test="${not empty user.secondPhone}">
                                            <option value="Secondary Phone" 
                                                    ${user.preferContact == 'Secondary Phone' ? 'selected' : ''}>
                                                Secondary Phone
                                            </option>
                                        </c:if>
                                    </select>

                                </div>
                            </div>
                        </div>

                        <!-- Action Buttons -->
                        <div class="action-buttons">
                            <button class="btn btn-back" type="button" onclick="history.back()">Cancel</button>
                            <button class="btn btn-update" type="submit">Update</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <script>
            function updatePreferredContactDropdown() {
                const primaryEmail = document.getElementById('primaryEmail').value;
                const secondaryEmail = document.getElementById('secondaryEmail').value;
                const firstPhone = document.getElementById('firstPhone').value;
                const secondPhone = document.getElementById('secondPhone').value;
                const dropdown = document.getElementById('preferContact');

                // Clear existing options
                dropdown.innerHTML = '';

                // Add primary email option if it has content
                if (primaryEmail) {
                    const option = document.createElement('option');
                    option.value = 'Primary Email';
                    option.textContent = 'Primary Email';
                    dropdown.appendChild(option);
                }

                // Add secondary email option if it has content
                if (secondaryEmail) {
                    const option = document.createElement('option');
                    option.value = 'Secondary Email';
                    option.textContent = 'Secondary Email';
                    dropdown.appendChild(option);
                }

                // Add primary phone option if it has content
                if (firstPhone) {
                    const option = document.createElement('option');
                    option.value = 'Primary Phone';
                    option.textContent = 'Primary Phone';
                    dropdown.appendChild(option);
                }

                // Add secondary phone option if it has content
                if (secondPhone) {
                    const option = document.createElement('option');
                    option.value = 'Secondary Phone';
                    option.textContent = 'Secondary Phone';
                    dropdown.appendChild(option);
                }

                // If no options were added, add a default 'Other' option
                if (dropdown.options.length === 0) {
                    const option = document.createElement('option');
                    option.value = 'Other';
                    option.textContent = 'Other';
                    dropdown.appendChild(option);
                }

                // Set the default selected option to 'Primary Email' if available
                if (primaryEmail) {
                    dropdown.value = 'Primary Email';
                }
            }

            function previewImage() {
                const fileInput = document.getElementById('profileImage');
                const preview = document.getElementById('imagePreview');

                if (fileInput.files && fileInput.files[0]) {
                    const reader = new FileReader();

                    reader.onload = function (e) {
                        preview.src = e.target.result;
                        preview.style.display = 'block'; // Ensure the image is displayed
                    };

                    reader.readAsDataURL(fileInput.files[0]);
                }
            }

            document.querySelector(".btn-update").addEventListener("click", function (event) {
                let isValid = true;

                // Reset all error messages
                document.querySelectorAll(".error-message").forEach(el => el.style.display = 'none');

                // Kiểm tra trường First Name
                const firstName = document.getElementById("firstName").value.trim();
                if (!firstName) {
                    const firstNameError = document.getElementById("firstNameError");
                    firstNameError.innerText = "First Name is required.";
                    firstNameError.style.display = 'block';
                    isValid = false;
                }

                // Kiểm tra trường Last Name
                const lastName = document.getElementById("lastName").value.trim();
                if (!lastName) {
                    const lastNameError = document.getElementById("lastNameError");
                    lastNameError.innerText = "Last Name is required.";
                    lastNameError.style.display = 'block';
                    isValid = false;
                }

                // Kiểm tra trường Primary Email
                const primaryEmail = document.getElementById("primaryEmail").value.trim();
                if (!primaryEmail) {
                    const primaryEmailError = document.getElementById("primaryEmailError");
                    primaryEmailError.innerText = "Primary Email is required.";
                    primaryEmailError.style.display = 'block';
                    isValid = false;
                } else if (!validateEmail(primaryEmail)) {
                    const primaryEmailError = document.getElementById("primaryEmailError");
                    primaryEmailError.innerText = "Please enter a valid email address.";
                    primaryEmailError.style.display = 'block';
                    isValid = false;
                }

                // Nếu có lỗi, ngăn form không được submit
                if (!isValid) {
                    event.preventDefault();
                }
            });

            // Hàm kiểm tra định dạng email
            function validateEmail(email) {
                const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                return regex.test(email);
            }


        </script>
    </body>
</html>
