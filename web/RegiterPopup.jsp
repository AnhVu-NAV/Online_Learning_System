<%-- 
    Document   : RegisterPopup
    Created on : Oct 6, 2024, 10:56:15 PM
    Author     : AnhVuNAV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Popup Background -->
<div class="popup-background" id="registerPopup" style="display:none;">
    <div class="popup-content">
        <button class="close-btn" onclick="closeRegisterPopup()">&times;</button>
        <h2>Register for Course</h2>
        <form class="register-form" action="RegisterCourse" method="POST">
            <input type="hidden" id="hiddenCourseId" name="courseId" value="">
            
            <!-- Lấy gói giá từ cơ sở dữ liệu -->
            <label for="pricePackage">Choose a Price Package:</label>
            <select id="pricePackage" name="pricePackageId">
                <c:forEach var="pricePackage" items="${pricePackages}">
                    <option value="${pricePackage.id}">${pricePackage.title} - $<fmt:formatNumber value="${pricePackage.price}" /></option>
                </c:forEach>
            </select>

            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" name="fullName" placeholder="Enter your full name">

            <div id="phoneSection">
                <label for="phone1">Phone 1:</label>
                <input type="text" id="phone1" name="phone1" placeholder="Enter your phone number">
                <label for="phone2" style="display: none;">Phone 2:</label>
                <input type="text" id="phone2" name="phone2" placeholder="Enter your second phone number" style="display: none;">
                <button type="button" class="add-btn" onclick="addPhone()">+ Add another phone</button>
            </div>

            <div id="emailSection">
                <label for="email1">Email 1:</label>
                <input type="email" id="email1" name="email1" placeholder="Enter your email">
                <label for="email2" style="display: none;">Email 2:</label>
                <input type="email" id="email2" name="email2" placeholder="Enter your second email" style="display: none;">
                <button type="button" class="add-btn" onclick="addEmail()">+ Add another email</button>
            </div>

            <label for="contactMethod">Preferred Contact Method:</label>
            <select id="contactMethod" name="contactMethod">
                <option value="email1">Email 1</option>
                <option value="email2">Email 2</option>
                <option value="phone1">Phone 1</option>
                <option value="phone2">Phone 2</option>
            </select>

            <label for="gender">Gender:</label>
            <select id="gender" name="gender">
                <option value="male">Male</option>
                <option value="female">Female</option>
                <option value="other">Other</option>
            </select>

            <button type="submit" class="register-btn primary">Register Now</button>
        </form>
        <div id="message" class="message"></div>
    </div>
</div>

<script>
    // Function to add a second phone field
    function addPhone() {
        const phone2 = document.getElementById('phone2');
        if (phone2.style.display === 'none') {
            phone2.style.display = 'block';
        } else {
            alert('Maximum 2 phones allowed');
        }
    }

    // Function to add a second email field
    function addEmail() {
        const email2 = document.getElementById('email2');
        if (email2.style.display === 'none') {
            email2.style.display = 'block';
        } else {
            alert('Maximum 2 emails allowed');
        }
    }
</script>
