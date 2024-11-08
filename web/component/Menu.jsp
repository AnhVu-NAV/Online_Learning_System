<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Header</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
</head>
<body>
    <header>
        <div class="header-container">
            <div class="logo">
                <a href="${pageContext.request.contextPath}/home">
                    <span class="logo-icon">A</span> <span class="logo-text">Learnik</span>
                </a>
            </div>
            <nav class="nav-links">
                <a href="${pageContext.request.contextPath}/home">Home</a>
                <a href="${pageContext.request.contextPath}/CourseList">Courses</a>
            </nav>
            <div class="user-options">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <div class="user-profile">
                            <img src="${sessionScope.user.profilePictureUrl}" alt="Profile" class="profile-img">
                            <div class="dropdown">
                                <button class="dropdown-toggle" id="userMenu" data-toggle="dropdown">
                                    ${sessionScope.user.firstName}
                                </button>
                                <div class="dropdown-menu" aria-labelledby="userMenu">
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/UserProfile?userId=${sessionScope.user.id}">Profile</a>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/home?action=logout">Log Out</a>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/login" class="login-btn">Log In</a>
                        <a href="${pageContext.request.contextPath}/signup" class="signup-btn">Sign Up</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </header>

    <style>
        /* Basic styling for header */
        .header-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 20px;
            background-color: #141624;
            color: #fff;
        }
        .logo {
            font-size: 24px;
            color: #fff;
        }
        .logo-icon {
            font-weight: bold;
            color: #4B6BFB;
        }
        .logo-text {
            font-weight: 600;
        }
        .nav-links a {
            color: #BABABF;
            margin: 0 15px;
            text-decoration: none;
            font-weight: 500;
        }
        .user-options {
            display: flex;
            align-items: center;
        }
        .profile-img {
            width: 30px;
            height: 30px;
            border-radius: 50%;
            margin-right: 10px;
        }
        .dropdown-toggle {
            background: none;
            border: none;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
        }
        .dropdown-menu {
            background-color: #2A2C3B;
            border: none;
            color: #fff;
        }
        .dropdown-item {
            color: #BABABF;
            padding: 10px 20px;
            text-decoration: none;
        }
        .dropdown-item:hover {
            background-color: #4B6BFB;
            color: #fff;
        }
        .login-btn, .signup-btn {
            color: #BABABF;
            margin-left: 15px;
            text-decoration: none;
            font-weight: 500;
        }
        .signup-btn {
            font-weight: bold;
        }
    </style>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
