/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

//import dal.UserDAO;
import dal.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.User;
//import model.User;

/**
 *
 * @author AnhVuNAV
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.getRequestDispatcher("/Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            Integer banned = Integer.parseInt(request.getParameter("banned"));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid banned status.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        UserDAO userDao = new UserDAO();
        User user = userDao.getOne(username, password, banned);

        // Set user in session
        session.setAttribute("user", user);

        if (user == null) {
            // Invalid username or password
            request.setAttribute("invalidUser", "Username or Password is invalid");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else if (user.getBanned() == 1) {
            // User is banned
            response.sendRedirect("AccessDenied.jsp");
            session.removeAttribute("user");
        } else {
            // Set fullname in session
            session.setAttribute("fullname", user.getFullname());

//            // Role-based redirection
//            if (user.getRole_id() == 0) {
//                // Admin user
//                request.getRequestDispatcher("admin").forward(request, response);
//            } else if (user.getRole_id() == 1) {
//                // Customer user
//                response.sendRedirect("customer");
//            }
            
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
