<%-- 
    Document   : UserProfile
    Created on : Sep 23, 2024, 11:41:05 AM
    Author     : AnhVuNAV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="css/userProfile.css">
</head>
<body>
    <div class="page-container">
        <div class="content-container">
            <!-- Sidebar -->
            <aside class="sidebar">
                 <!--Your sidebar content here--> 
            </aside>

            <!-- Main Content -->
            <main class="main-content">
                <header class="header">
                    <button class="back-button" onclick="history.back()">← Back</button>
                    <h1>Personal Details</h1>
                    <a href="UpdateUserProfile?userId=${user.id}">
                        <button class="next-profile-button">Update Profile →</button>
                    </a>
                </header>
                <section class="personal-section">
                    <div class="personal-details">
                        <img class="verification-photo" src="${user.image_url}" alt="Verification">
                        <div class="details">
                            <p><strong>Name:</strong> ${user.first_name} ${user.last_name}</p>
                            <p><strong>Gender:</strong> ${user.gender ? 'Male' : 'Female'}</p>
                            <p><strong>Date of Birth:</strong> ${user.dob}</p>
                            <p><strong>Nationality:</strong> ${user.address}</p>
                        </div>
                    </div>
                </section>

                <section class="address-section">
                    <h2>Address</h2>
                    <p><strong>Address Line:</strong> ${user.address}</p>
                </section>

                <section class="contact-section">
                    <h2>Contact Details</h2>
                    <p><strong>Phone Number:</strong> ${user.phone}</p>
                    <p><strong>Email:</strong> ${user.email}</p>
                </section>

                <!-- Account Tier Section -->
                <section class="account-tier-section">
                    <h2>Account Tier</h2>
                    <div class="account-tier">
                        <label><input type="radio" name="tier" checked> Tier 1 (Current)</label>
                        <label><input type="radio" name="tier"> Tier 2</label>
                        <label><input type="radio" name="tier"> Tier 3</label>
                    </div>
                </section>
            </main>
        </div>
    </div>
</body>
</html>
