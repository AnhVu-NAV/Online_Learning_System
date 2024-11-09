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
                <label for="phone2" id="labelPhone2" class="phone2" style="display: none;">Phone 2:</label> 
                <input type="text" id="phone2" class="phone2" name="phone2" placeholder="Enter your second phone number" style="display: none; width: 100%;">
                <button type="button" class="add-btn" id="addPhoneBtn" onclick="addPhone()">+ Add another phone</button>
            </div>

            <div id="emailSection">
                <label for="email1" style="display: block">Email 1:</label>
                <input type="email" id="email1" style="width: 100%" name="email1" placeholder="Enter your email">
                <label for="email2" id="labelEmail2" class="email2" style="display: none;">Email 2:</label>
                <input type="email" id="email2" class="email2" name="email2" placeholder="Enter your second email" style="display: none; width: 100%;">
                <button type="button" class="add-btn" id="addEmailBtn" onclick="addEmail()">+ Add another email</button>
            </div>

            <label for="contactMethod">Preferred Contact Method:</label>
            <select id="contactMethod" name="contactMethod">
                <!-- Options will be dynamically updated -->
            </select>

            <label for="gender">Gender:</label>
            <select id="gender" name="gender">
                <option value="1">Male</option>
                <option value="2">Female</option>
                <option value="3">Other</option>
            </select>

            <button type="submit" class="register-btn primary">Register Now</button>
        </form>
        <div id="message" class="message"></div>
    </div>
</div>

<c:if test="${not empty sessionScope.user}">
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            document.getElementById('fullName').value = '${sessionScope.user.firstName} ${sessionScope.user.lastName}';
            document.getElementById('phone1').value = '${sessionScope.user.firstPhone != null ? sessionScope.user.firstPhone : ''}';
            document.getElementById('email1').value = '${sessionScope.user.primaryEmail != null ? sessionScope.user.primaryEmail : ''}';

            if ('${sessionScope.user.secondPhone != null ? sessionScope.user.secondPhone : ''}' !== '') {
                document.getElementById('phone2').value = '${sessionScope.user.secondPhone}';
                document.getElementById('labelPhone2').style.display = 'block';
                document.getElementById('phone2').style.display = 'block';
            }

            if ('${sessionScope.user.secondaryEmail != null ? sessionScope.user.secondaryEmail : ''}' !== '') {
                document.getElementById('email2').value = '${sessionScope.user.secondaryEmail}';
                document.getElementById('labelEmail2').style.display = 'block';
                document.getElementById('email2').style.display = 'block';
            }

            updateContactMethodDropdown();
        });
    </script>
</c:if>



<script>
    function addPhone() {
        document.getElementById('labelPhone2').style.display = 'block';
        document.getElementById('phone2').style.display = 'block';
        document.getElementById('addPhoneBtn').style.display = 'none';
        updateContactMethodDropdown();
    }

    function addEmail() {
        document.getElementById('labelEmail2').style.display = 'block';
        document.getElementById('email2').style.display = 'block';
        document.getElementById('addEmailBtn').style.display = 'none';
        updateContactMethodDropdown();
    }

    function updateContactMethodDropdown() {
        const phone1 = document.getElementById('phone1').value.trim();
        const phone2 = document.getElementById('phone2').value.trim();
        const email1 = document.getElementById('email1').value.trim();
        const email2 = document.getElementById('email2').value.trim();
        const contactMethod = document.getElementById('contactMethod');

        // Clear existing options
        contactMethod.innerHTML = '';

        if (phone1) {
            const option = document.createElement('option');
            option.value = 'phone1';
            option.textContent = 'Phone 1';
            contactMethod.appendChild(option);
        }
        if (phone2) {
            const option = document.createElement('option');
            option.value = 'phone2';
            option.textContent = 'Phone 2';
            contactMethod.appendChild(option);
        }
        if (email1) {
            const option = document.createElement('option');
            option.value = 'email1';
            option.textContent = 'Email 1';
            contactMethod.appendChild(option);
        }
        if (email2) {
            const option = document.createElement('option');
            option.value = 'email2';
            option.textContent = 'Email 2';
            contactMethod.appendChild(option);
        }
    }

    // Event listeners to update contact method dropdown on input change
    document.getElementById('phone1').addEventListener('input', updateContactMethodDropdown);
    document.getElementById('phone2').addEventListener('input', updateContactMethodDropdown);
    document.getElementById('email1').addEventListener('input', updateContactMethodDropdown);
    document.getElementById('email2').addEventListener('input', updateContactMethodDropdown);
</script>
