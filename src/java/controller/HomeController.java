/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
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
                if (user.getRole_id() == 6) {
                    // Admin user
                    response.sendRedirect(request.getContextPath() + "/admin");
                } else if (user.getRole_id() == 1) {
                    // Customer user
                    response.sendRedirect(request.getContextPath() + "/customer");
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
            if (user != null) {
                String fullname = user.getFirst_name() + " " + user.getLast_name();
                session.setAttribute("fullname", fullname);
            }
            request.getRequestDispatcher("/home.jsp").forward(request, response);
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
            // Handle login via LoginController logic
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            UserDAO userDao = new UserDAO();
            User user = userDao.getOne(email, password, 1); // Assume not banned for login

            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            if (user == null) {
                // Invalid credentials, return to login page
                request.setAttribute("message", "Email or password is invalid");
                request.setAttribute("alert", "danger");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                // Add redirect after forwarding to login page
//                response.sendRedirect(request.getContextPath() + "/login?action=login&message='Username or password is invalid'&alert=danger");
            } else if (user.getStatus() == 0) {
                // Banned user, redirect to access denied page
                session.removeAttribute("user");
//                response.sendRedirect("AccessDenied.jsp");
                request.setAttribute("message", "Account has been banned");
                request.setAttribute("alert", "danger");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {
                // Login successful, handle role-based forwarding
                String fullname = (String) session.getAttribute("fullname");
                if (user.getRole_id() == 6) {
                    // Admin user
//                    response.sendRedirect(request.getContextPath() + "/dashboard");
                    response.sendRedirect(request.getContextPath() + "/admin/dashboard");

                } else if (user.getRole_id() == 2) {
                    // Customer user
                    response.sendRedirect(request.getContextPath() + "/home");
                } else if (user.getRole_id() == 0) {
                    // Default user
                    response.sendRedirect(request.getContextPath() + "/change-password");
                } else {
                    session.removeAttribute("user");
                    response.sendRedirect(request.getContextPath() + "/home");
                }
            }
        }
    }

}
