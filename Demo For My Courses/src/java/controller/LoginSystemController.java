/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import util.*;

/**
 *
 * @author ADMIN
 */
public class LoginSystemController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
//        String action = request.getParameter("action"); 
        AccountDAO adao = new AccountDAO();

        try {
            Account account = adao.getAccountByEmail(email);
            if (account == null) {
                throw new Exception("This email is not existed");
            } else {
                if (!isValidPassword(account, password)) {
                    throw new Exception("Password is incorrect");
                }
                session.setAttribute("account", account);
                request.getRequestDispatcher("home_page_demo.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private boolean isValidPassword(Account account, String password) {
        password = PasswordEncoding.getEncodingPassword(password);
        return account.getPassword().equals(password);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
