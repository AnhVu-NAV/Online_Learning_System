/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.BlogDAO;
import dal.CourseDAO;
import dal.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.Blogs;
import model.Course;
import model.Tagline;
import model.User;

/**
 *
 * @author mocun
 */
@WebServlet(urlPatterns = {"/home", "/login", "/logout"})
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        CourseDAO courseDAO = new CourseDAO();
//        RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);
        User user = (User) (session != null ? session.getAttribute("user") : null);

        if (action != null && action.equals("login")) {
            String message = request.getParameter("message");
            String alert = request.getParameter("alert");

            if (message != null && alert != null) {
                request.setAttribute("message", message);
                request.setAttribute("alert", alert);
            }

            if (user == null) {
                // If no user is logged in, display home
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {
                // If user is logged in, handle role-based forwarding
                if (user.getRoleId() == 1) {
                    // Admin user
                    response.sendRedirect(request.getContextPath() + "/admin");
                } else if (user.getRoleId() == 2) {
                    // Customer user
                    response.sendRedirect(request.getContextPath() + "/home");
                }
            }
        } else if (action != null && action.equals("logout")) {
            if (session != null) {
                session.invalidate(); // Invalidate the current session
            }
            response.sendRedirect(request.getContextPath() + "/home"); // Redirect to login page
        } else {
            // Check if user is already logged in
//            HttpSession session = request.getSession(false);
//            User user = (User) session.getAttribute("user");

            // Hiển thị danh sách blog trên trang home
            BlogDAO blogDAO = new BlogDAO();

// Default values for page and page size
            int page = 1;
            int recordsPerPage = 6; // Default page size

            // Get the 'page' and 'pageSize' parameters from the request
            try {
                String pageParam = request.getParameter("page");
                String pageSizeParam = request.getParameter("pageSize");

                // Update page number
                if (pageParam != null) {
                    page = Integer.parseInt(pageParam);
                }

                // Update page size (recordsPerPage) if pageSize is provided
                if (pageSizeParam != null) {
                    recordsPerPage = Integer.parseInt(pageSizeParam);
                }

            } catch (NumberFormatException e) {
                e.printStackTrace(); // In case of invalid page or pageSize values
            }

            try {
                // Fetch paginated blogs
                List<Blogs> blogList = blogDAO.getPaginatedBlogs((page - 1) * recordsPerPage, recordsPerPage);
                int noOfRecords = blogDAO.getNoOfRecords();
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                // Fetch the courses based on filters
                List<Course> randomCourses = courseDAO.getRandomCourses(8); // Fetch 8 random courses

                // Set attributes for the JSP
                request.setAttribute("courses", randomCourses);
                request.setAttribute("blogList", blogList);
                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("currentPage", page);
                request.setAttribute("recordsPerPage", recordsPerPage);  // Pass the current page size to the JSP

                // Forward to JSP
                RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
                dispatcher.forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();  // Handle exception
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Something went wrong while fetching blog data.");
            }

            if (user != null) {
                String fullname = user.getFirstName() + " " + user.getLastName();
                session.setAttribute("fullname", fullname);
            }
//            request.getRequestDispatcher("/home.jsp").forward(request, response);
        }

//        String filter = request.getParameter("filter");
//
//        List<Post> filteredPosts;
//        if ("hot".equals(filter)) {
//            filteredPosts = postService.getHotPosts();
//        } else if ("newest".equals(filter)) {
//            filteredPosts = postService.getNewestPosts();
//        } else {
//            filteredPosts = postService.getMostViewedPosts();
//        }
//
//        request.setAttribute("postList", filteredPosts);
//        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("login")) {
            // Xử lý logic đăng nhập
            String primaryEmail = request.getParameter("primary_email");
            String password = request.getParameter("password");

            UserDAO userDao = new UserDAO();
            User user = userDao.getOne(primaryEmail, password, 1); // Giả định trạng thái 1 là không bị cấm

            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            if (user == null) {
                // Sai thông tin đăng nhập, quay lại trang login
                request.setAttribute("message", "Email hoặc mật khẩu không chính xác");
                request.setAttribute("alert", "danger");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else if (user.getStatus() == 0) {
                // Tài khoản bị cấm, quay lại trang login với thông báo
                session.removeAttribute("user");
                request.setAttribute("message", "Tài khoản của bạn đã bị cấm");
                request.setAttribute("alert", "danger");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {
                // Đăng nhập thành công, chuyển hướng dựa trên vai trò
                if (user.getRoleId() == 1) {
                    // Người dùng admin
                    response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                } else if (user.getRoleId() == 2) {
                    // Người dùng khách hàng
                    response.sendRedirect(request.getContextPath() + "/home");
                } else if (user.getRoleId() == 0) {
                    // Người dùng mặc định, yêu cầu đổi mật khẩu
                    response.sendRedirect(request.getContextPath() + "/change-password");
                } else {
                    // Người dùng không xác định, quay lại trang chủ
                    session.removeAttribute("user");
                    response.sendRedirect(request.getContextPath() + "/home");
                }
            }
        }
    }

}
