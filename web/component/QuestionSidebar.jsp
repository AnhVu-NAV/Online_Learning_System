<%-- 
    Document   : QuestionSidebar
    Created on : Oct 2, 2024, 10:14:42 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .main-sidebar {
                background-color: #222d32; /* Màu nền cho sidebar */
                color: white; /* Màu chữ */
                height: 100vh; /* Đảm bảo sidebar chiếm toàn bộ chiều cao */
            }
            .sidebar-logo {
                font-size: 24px; /* Kích thước chữ cho logo */
                color: white; /* Màu chữ cho logo */
                padding: 15px; /* Khoảng cách bên trong */
                text-align: center; /* Căn giữa logo */
                border-bottom: 1px solid #444; /* Đường viền dưới logo */
            }
            .sidebar-nav {
                padding: 0; /* Bỏ padding */
                list-style: none; /* Bỏ dấu gạch đầu dòng */
            }
            .sidebar-item {
                padding: 10px 15px; /* Khoảng cách cho từng mục */
                transition: background 0.3s; /* Hiệu ứng chuyển tiếp khi hover */
            }
            .sidebar-item:hover {
                background: #1e282c; /* Màu nền khi hover */
            }
            .sidebar-link {
                color: white; /* Màu chữ cho link */
                text-decoration: none; /* Bỏ gạch chân */
                display: flex; /* Sử dụng flex để căn giữa icon và text */
                align-items: center; /* Căn giữa dọc */
            }
            .sidebar-link i {
                margin-right: 10px; /* Khoảng cách giữa icon và text */
            }
            .sidebar-footer {
                position: absolute; /* Đặt footer ở cuối */
                bottom: 0;
                width: 100%; /* Chiều rộng đầy đủ */
                background: #1c1f23; /* Màu nền cho footer */
                padding: 10px; /* Khoảng cách */
                text-align: center; /* Căn giữa */
            }
        </style>
    </head>
    <body>
        <aside class="main-sidebar">
            <section class="sidebar">
                <div class="sidebar-logo">
                    <a href="#">Learnik</a>
                </div>
                <ul class="sidebar-nav">
                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link">
                            <i class="fa fa-fw fa-users"></i>
                            <span>Users List</span>
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link">
                            <i class="fa fa-fw fa-gear"></i>
                            <span>Setting List</span>
                        </a>
                    </li>
                </ul>
                <div class="sidebar-footer">
                    <a href="#" class="sidebar-link">
                        <i class="fa fa-fw fa-sign-out"></i>
                        <span>Logout</span>
                    </a>
                </div>
            </section>
        </aside>
    </body>
</html>
