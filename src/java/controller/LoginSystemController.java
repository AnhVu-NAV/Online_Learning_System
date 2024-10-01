/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import util.PasswordEncoding;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "LoginSystemController", urlPatterns = {"/login"})
public class LoginSystemController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDAO adao = new UserDAO();
        try {
            User account = adao.getUserByPrimaryEmail(email);
            if (account == null) {
                throw new Exception("This email is not existed");
            } else {
                if (!isValidPassword(account, password)) {
                    throw new Exception("Password is incorrect");
                }
                session.setAttribute("account", account);
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private boolean isValidPassword(User account, String password) {
        password = PasswordEncoding.getEncodingPassword(password);
        return account.getPassword().equals(password);
    }
}
