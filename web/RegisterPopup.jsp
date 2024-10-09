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
                    <option value="${pricePackage.id}">
                        ${pricePackage.title} - 
                        $<fmt:formatNumber value="${pricePackage.price}" /> 
                    <c:if test="${pricePackage.salePrice > 0}">
                        (<span class="sale-price">Sale: $<fmt:formatNumber value="${pricePackage.salePrice}" /></span>)
                    </c:if>
                    </option>
                </c:forEach>
            </select>

            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" name="fullName" placeholder="Enter your full name">

            <div id="phoneSection">
                <label for="phone1" style="display: block">Phone 1:</label> 
                <input type="text" id="phone1" style="width: 100%" name="phone1" placeholder="Enter your phone number">
                <label for="phone2" class="phone2" style="display: none;">Phone 2:</label> 
                <input type="text" class="phone2" name="phone2" placeholder="Enter your second phone number" style="display: none; width: 100%;">
                <button type="button" class="add-btn" onclick="addPhone()">+ Add another phone</button>
            </div>

            <div id="emailSection">
                <label for="email1" style="display: block">Email 1:</label>
                <input type="email" id="email1" style="width: 100%" name="email1" placeholder="Enter your email">
                <label for="email2" class="email2" style="display: none;">Email 2:</label>
                <input type="email" class="email2" name="email2" placeholder="Enter your second email" style="display: none; width: 100%;">
                <button type="button" class="add-btn" onclick="addEmail()">+ Add another email</button>
            </div>

            <label for="contactMethod">Preferred Contact Method:</label>
            <select id="contactMethod" name="contactMethod">
                <!-- Các option này sẽ được cập nhật động -->
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
   // Function to add a second email field
function addEmail() {
    const email2Elements = document.getElementsByClassName('email2');
    for (let i = 0; i < email2Elements.length; i++) {
        if (email2Elements[i].style.display === 'none') {
            email2Elements[i].style.display = 'block'; // Hiển thị email thứ 2
            // Thêm sự kiện lắng nghe cho email2 sau khi nó hiển thị
            email2Elements[i].addEventListener('input', updateContactMethodOptions);
        } else {
            alert('Maximum 2 emails allowed');
        }
    }
    updateContactMethodOptions(); // Cập nhật dropdown sau khi hiển thị email thứ 2
}

// Function to add a second phone field
function addPhone() {
    const phone2Elements = document.getElementsByClassName('phone2');
    for (let i = 0; i < phone2Elements.length; i++) {
        if (phone2Elements[i].style.display === 'none') {
            phone2Elements[i].style.display = 'block';
            // Thêm sự kiện lắng nghe cho phone2 sau khi nó hiển thị
            phone2Elements[i].addEventListener('input', updateContactMethodOptions);
        } else {
            alert('Maximum 2 phones allowed');
        }
    }
    updateContactMethodOptions(); // Cập nhật dropdown sau khi hiển thị phone thứ 2
}

// Function to update the contact method dropdown based on entered values
function updateContactMethodOptions() {
    const contactMethod = document.getElementById('contactMethod');
    const email1 = document.getElementById('email1').value;
    const email2Element = document.querySelector('.email2');
    const email2 = email2Element ? email2Element.value : ''; // Kiểm tra nếu tồn tại email2
    const phone1 = document.getElementById('phone1').value;
    const phone2Element = document.querySelector('.phone2');
    const phone2 = phone2Element ? phone2Element.value : ''; // Kiểm tra nếu tồn tại phone2

    // Xóa tất cả các tùy chọn hiện tại trong dropdown
    contactMethod.innerHTML = '';

    // Thêm vào các lựa chọn tùy thuộc vào giá trị đã nhập
    if (email1) {
        const optionEmail1 = document.createElement('option');
        optionEmail1.value = 'email1';
        optionEmail1.text = 'Email 1';
        contactMethod.add(optionEmail1);
    }
    if (email2) {
        const optionEmail2 = document.createElement('option');
        optionEmail2.value = 'email2';
        optionEmail2.text = 'Email 2';
        contactMethod.add(optionEmail2);
    }
    if (phone1) {
        const optionPhone1 = document.createElement('option');
        optionPhone1.value = 'phone1';
        optionPhone1.text = 'Phone 1';
        contactMethod.add(optionPhone1);
    }
    if (phone2) {
        const optionPhone2 = document.createElement('option');
        optionPhone2.value = 'phone2';
        optionPhone2.text = 'Phone 2';
        contactMethod.add(optionPhone2);
    }
}

// Lắng nghe sự kiện nhập liệu trên các trường và cập nhật dropdown
document.getElementById('email1').addEventListener('input', updateContactMethodOptions);
document.getElementById('phone1').addEventListener('input', updateContactMethodOptions);

// Function to open and close the popup
function openRegisterPopup() {
    document.getElementById('registerPopup').style.display = 'flex';
}

function closeRegisterPopup() {
    document.getElementById('registerPopup').style.display = 'none';
}

// Call this on page load to ensure the contact method dropdown is populated
window.onload = function() {
    updateContactMethodOptions();
};

</script>
