//package controller;
//
//import dal.UserDAO;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import java.io.IOException;
//import model.User;
//
//@WebServlet(urlPatterns = {"/login"})
//public class LoginController extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("/login.jsp").forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String primaryEmail = request.getParameter("primary_email");
//        String password = request.getParameter("password");
//
//        UserDAO userDao = new UserDAO();
//        User user = userDao.getOne(primaryEmail, password, 1); // Giả định trạng thái 1 là không bị cấm
//
//        HttpSession session = request.getSession();
//        session.setAttribute("user", user);
//
//        if (user == null) {
//            request.setAttribute("message", "Email hoặc mật khẩu không chính xác");
//            request.setAttribute("alert", "danger");
//            request.getRequestDispatcher("/login.jsp").forward(request, response);
//        } else if (user.getStatus() == 0) {
//            session.removeAttribute("user");
//            request.setAttribute("message", "Tài khoản của bạn đã bị cấm");
//            request.setAttribute("alert", "danger");
//            request.getRequestDispatcher("/login.jsp").forward(request, response);
//        } else {
//            switch (user.getRoleId()) {
//                case 3:
//                    response.sendRedirect(request.getContextPath() + "/UserDashboardController");
//                    break;
//                case 4:
//                case 7:
//                    response.sendRedirect(request.getContextPath() + "/home");
//                    break;
//                case 5:
//                    response.sendRedirect(request.getContextPath() + "/MarketingDashboardController");
//                    break;
//                case 6:
//                    response.sendRedirect(request.getContextPath() + "/CourseController?action=list");
//                    break;
//                default:
//                    session.removeAttribute("user");
//                    response.sendRedirect(request.getContextPath() + "/home");
//                    break;
//            }
//        }
//    }
//}
