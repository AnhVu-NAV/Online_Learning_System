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
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {
                // Kiểm tra vai trò để chuyển hướng
                switch (user.getRoleId()) {
                    case 3: // Admin
                        response.sendRedirect(request.getContextPath() + "/UserDashboardController");
                        break;
                    case 4: // Customer
                        response.sendRedirect(request.getContextPath() + "/home");
                        break;
                    case 5: // Marketer
                        response.sendRedirect(request.getContextPath() + "/MarketingDashboardController");
                        break;
                    case 6: // Expert
                        response.sendRedirect(request.getContextPath() + "/CourseController?action=list");
                        break;
                    case 7: // Sale
                        response.sendRedirect(request.getContextPath() + "/home");
                        break;
                    default:
                        session.removeAttribute("user");
                        response.sendRedirect(request.getContextPath() + "/home");
                        break;
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
            handleHomeContent(request, response);
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

    private void handleHomeContent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Thiết lập nội dung cho trang chủ
        BlogDAO blogDAO = new BlogDAO();
        CourseDAO courseDAO = new CourseDAO();

        int page = 1;
        int recordsPerPage = 6;

        try {
            String pageParam = request.getParameter("page");
            String pageSizeParam = request.getParameter("pageSize");

            if (pageParam != null) {
                page = Integer.parseInt(pageParam);
            }

            if (pageSizeParam != null) {
                recordsPerPage = Integer.parseInt(pageSizeParam);
            }

            List<Blogs> blogList = blogDAO.getPaginatedBlogs((page - 1) * recordsPerPage, recordsPerPage);
            int noOfRecords = blogDAO.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            List<Course> randomCourses = courseDAO.getRandomCourses(8);

            request.setAttribute("courses", randomCourses);
            request.setAttribute("blogList", blogList);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("recordsPerPage", recordsPerPage);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra khi tải nội dung trang chủ.");
        }
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

            // Kiểm tra nếu user là null
            if (user == null) {
                // Sai thông tin đăng nhập, quay lại trang login
                request.setAttribute("message", "Email hoặc mật khẩu không chính xác");
                request.setAttribute("alert", "danger");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else if (user.getStatus() == 0) {
                // Tài khoản bị cấm, quay lại trang login với thông báo
                HttpSession session = request.getSession();
                session.removeAttribute("user");
                request.setAttribute("message", "Tài khoản của bạn đã bị cấm");
                request.setAttribute("alert", "danger");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {
                // Đăng nhập thành công, thêm user và roleId vào session
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("roleId", user.getRoleId());

                // Chuyển hướng dựa trên vai trò của user
                switch (user.getRoleId()) {
                    //admin
                    case 3:
                        response.sendRedirect(request.getContextPath() + "/UserDashboardController");
                        break;
                    //customer
                    case 4:
                        response.sendRedirect(request.getContextPath() + "/home");
                        break;
                    //marketing
                    case 5:
                        response.sendRedirect(request.getContextPath() + "/MarketingDashboardController");
                        break;
                    //expert
                    case 6:
                        response.sendRedirect(request.getContextPath() + "/CourseController?action=list");
                        break;
                    //sale
                    case 7:
                        response.sendRedirect(request.getContextPath() + "/SaleRegistrationDashboardController");
                        break;
                    default:
                        session.removeAttribute("user");
                        response.sendRedirect(request.getContextPath() + "/home");
                        break;
                }
            }
        }
    }

}
