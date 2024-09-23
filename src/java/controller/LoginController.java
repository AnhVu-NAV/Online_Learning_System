///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package controller;
//
////import dal.UserDAO;
//import dal.UserDAO;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import java.io.IOException;
//import model.User;
////import model.User;
//
///**
// *
// * @author AnhVuNAV
// */
//public class LoginController extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        request.getRequestDispatcher("/Login.jsp").forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//
//        // Retrieve form data
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        Integer status = null;
//        try {
//            status = Integer.parseInt(request.getParameter("status"));
//        } catch (NumberFormatException e) {
//            status = 1;
//            request.setAttribute("error", "Invalid status.");
//            request.getRequestDispatcher("/login.jsp").forward(request, response);
//            return;
//        }
//
//        HttpSession session = request.getSession();
//        UserDAO userDao = new UserDAO();
//        User user = userDao.getOne(email, password, status);
//
//        // Set user in session
//        session.setAttribute("user", user);
//
//        if (user == null) {
//            // Invalid email or password
//            request.setAttribute("invalidUser", "Email or Password is invalid");
//            request.getRequestDispatcher("/login.jsp").forward(request, response);
//        } else if (user.getStatus() == 0) {
//            // User is banned
//            session.removeAttribute("user");
//            response.sendRedirect("AccessDenied.jsp");
//        } else {
//            // Set fullname in session
//            String fullname = user.getFirst_name() + " " + user.getLast_name();
//            session.setAttribute("fullname", fullname);
//
//            if (user.getRole_id() == 0) {
//                // Admin user
//                response.sendRedirect(request.getContextPath() + "/admin");
//            } else if (user.getRole_id() == 1) {
//                // Customer user
//                response.sendRedirect(request.getContextPath() + "/customer");
//            }
//
//        }
//    }
//
//   
//}
//
//
//
