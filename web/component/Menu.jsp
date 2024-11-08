<%-- 
    Document   : Menu
    Created on : Sep 11, 2024, 8:33:54 PM
    Author     : AnhVuNAV
--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Learnik Header</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <style>
        .header {
            background-color: #141624;
            padding: 10px 50px;
            color: #fff;
        }

        .navbar-brand {
            font-size: 24px;
            color: #fff;
            font-weight: bold;
            display: flex;
            align-items: center;
            text-decoration: none;
        }

        .navbar-brand .logo-icon {
            width: 30px;
            height: 30px;
            background-color: #4B6BFB;
            border-radius: 50%;
            display: inline-block;
            margin-right: 8px;
        }

        .navbar-nav .nav-link {
            color: #BABABF !important;
            font-size: 16px;
            margin-left: 20px;
        }

        .navbar-nav .dropdown-menu {
            background-color: #2E2E3D;
            border: none;
        }

        .navbar-nav .dropdown-item {
            color: #BABABF;
        }

        .navbar-nav .dropdown-item:hover {
            background-color: #4B6BFB;
            color: #fff;
        }

        .profile-img {
            width: 30px;
            height: 30px;
            border-radius: 50%;
            margin-right: 5px;
            object-fit: cover;
        }
    </style>
</head>
<body>
    <header class="header">
        <nav class="navbar navbar-expand-lg navbar-dark">
            <!-- Logo and Brand Name -->
            <a class="navbar-brand" href="/home">
                <div class="logo-icon"></div> Learnik
            </a>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <!-- Static Links -->
                    <li class="nav-item">
                        <a class="nav-link" href="/home">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/CourseList">Courses</a>
                    </li>

                    <!-- User Profile Section (Login/Signup or Profile/Logout based on session) -->
                    <c:choose>
                        <c:when test="${not empty sessionScope.user}">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <img src="${sessionScope.user.profilePictureUrl}" alt="Profile" class="profile-img">
                                </a>
                                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                                    <a class="dropdown-item" href="<c:url value='/UserProfile?userId=${sessionScope.user.id}' />">Profile</a>
                                    <a class="dropdown-item" href='<c:url value="/home?action=logout"/>'>Logout</a>
                                </div>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item">
                                <a class="nav-link" href="/signup">Sign Up</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value='/login?action=login'/>">Login</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </nav>
    </header>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
