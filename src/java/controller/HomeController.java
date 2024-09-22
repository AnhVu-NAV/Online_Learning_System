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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
        String action = request.getParameter("action");
        if (action != null && action.equals("login")) {
            String message = request.getParameter("message");
            String alert = request.getParameter("alert");
            if (message != null && alert != null) {
                request.setAttribute("message", message);
                request.setAttribute("alert", alert);

            }

            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        } else if (action != null && action.equals("logout")) {
            HttpSession session = request.getSession();
            session.invalidate(); // Invalidate the current session
            response.sendRedirect(request.getContextPath() + "/home"); // Redirect to login page
        } else {
            // Check if user is already logged in
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");
//            RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
//            rd.forward(request, response);
            if (user == null) {
                // If no user is logged in, redirect to login
                response.sendRedirect(request.getContextPath() + "/login?action=login");
            } else {
                // If user is logged in, handle role-based forwarding
                if (user.getRole_id() == 0) {
                    // Admin user
                    response.sendRedirect(request.getContextPath() + "/admin");
                } else if (user.getRole_id() == 1) {
                    // Customer user
                    response.sendRedirect(request.getContextPath() + "/customer");
                }
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("login")) {
//            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
//            rd.forward(request, response);
            // Handle login via LoginController logic
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            UserDAO userDao = new UserDAO();
            User user = userDao.getOne(username, password, 0); // Assume not banned for login

            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            if (user == null) {
                // Invalid credentials, return to login page
                request.setAttribute("invalidUser", "Username or Password is invalid");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                // Add redirect after forwarding to login page
                response.sendRedirect(request.getContextPath() + "/login?action=login&message='Username or password is invalid'&alert=danger");
            } else if (user.getBanned() == 1) {
                // Banned user, redirect to access denied page
                response.sendRedirect("AccessDenied.jsp");
                session.removeAttribute("user");
            } else {
                // Login successful, handle role-based forwarding
                session.setAttribute("fullname", user.getFullname());

                if (user.getRole_id() == 0) {
                    // Admin user
                    response.sendRedirect(request.getContextPath() + "/dashboard");
                } else if (user.getRole_id() == 1) {
                    // Customer user
                    response.sendRedirect(request.getContextPath() + "/home");
                } else if (user.getRole_id() == 2) {
                    // Default user
                    response.sendRedirect(request.getContextPath() + "/change-password");
                }
            }
        }
    }
}