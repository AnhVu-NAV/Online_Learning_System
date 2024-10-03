<%-- 
    Document   : UpdateUserProfile
    Created on : Sep 23, 2024, 11:59:10 AM
    Author     : AnhVuNAV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update User Profile</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="css/userProfile.css">
</head>
<body>
    <div class="page-container">
        <div class="content-container">
            <!-- Sidebar -->
            <aside class="sidebar">
                <div class="account-officer">
                    <img class="profile-img" src="${user.image_url}" alt="${user.first_name} ${user.last_name}">
                    <div class="profile-details">
                        <h3>${user.first_name} ${user.last_name}</h3>
                        <p>Customer Operations</p>
                    </div>
                </div>
                <nav class="menu">
                    <ul class="menu">
                        <li><a href="#"><i class='bx bx-grid-alt'></i> Dashboard</a></li>
                        <li><a href="#"><i class='bx bx-task'></i> Task</a></li>
                        <li class="submenu">
                            <a href="#"><i class='bx bx-user'></i> Customers <i class='bx bx-chevron-right'></i></a>
                            <ul class="submenu-items">
                                <li><a href="#">All</a></li>
                                <li><a href="#">Account Upgrade</a></li>
                            </ul>
                        </li>
                        <li><a href="#"><i class='bx bx-chat'></i> Chats</a></li>
                        <li><a href="#"><i class='bx bx-error'></i> Disputes</a></li>
                        <li><a href="#"><i class='bx bx-cog'></i> Settings</a></li>
                    </ul>
                </nav>
            </aside>

            <!-- Main Content -->
            <main class="main-content">
                <header class="header">
                    <button class="back-button" onclick="history.back()">← Back</button>
                    <h1>Update Personal Details</h1>
                </header>
                <form action="UpdateUserProfile" method="post">
                    <input type="hidden" name="userId" value="${user.id}" />
                    <section class="personal-section">
                        <div class="personal-details">
                            <img class="verification-photo" src="${user.image_url}" alt="Verification">
                            <div class="details">
                                <label><strong>First Name:</strong></label>
                                <input type="text" name="first_name" value="${user.first_name}" required />
                                <label><strong>Last Name:</strong></label>
                                <input type="text" name="last_name" value="${user.last_name}" required />
                            </div>
                        </div>
                    </section>

                    <section class="contact-section">
                        <h2>Contact Details</h2>
                        <label><strong>Phone Number:</strong></label>
                        <input type="text" name="phone" value="${user.phone}" required />
                    </section>

                    <section class="address-section">
                        <h2>Address</h2>
                        <label><strong>Address Line:</strong></label>
                        <input type="text" name="address" value="${user.address}" required />
                    </section>

                    <section class="image-section">
                        <h2>Profile Image</h2>
                        <label><strong>Image URL:</strong></label>
                        <input type="text" name="image_url" value="${user.image_url}"  />
                    </section>

                    <section class="gender-section">
                        <h2>Gender</h2>
                        <label><input type="radio" name="gender" value="true" ${user.gender ? 'checked' : ''}> Male</label>
                        <label><input type="radio" name="gender" value="false" ${!user.gender ? 'checked' : ''}> Female</label>
                    </section>

                    <!-- Action Buttons -->
                    <div class="action-buttons">
                        <button class="decline-button" type="button" onclick="history.back()">Cancel</button>
                        <button class="approve-button" type="submit">Update</button>
                    </div>
                </form>
            </main>
        </div>
    </div>
</body>
</html>