/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Vector;
import java_email.*;
import model.User;
import util.CreateRandom;
import util.InputValidation;

/**
 *
 * @author AnhVuNAV
 */
@WebServlet(name = "CraeteNewAccountController", urlPatterns = {"/create"})
public class CreateNewAccountController extends HttpServlet {

    private static String randomCode = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action != null) {
                if (action.equals("gmail")) {
                    request.setAttribute("message", "Please check your email!");
                    request.getRequestDispatcher("create_account.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("create_account.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone_number");
        String password = request.getParameter("password");
        String rewritePassword = request.getParameter("rewrite_password");
        String code = request.getParameter("code");
        PrintWriter out = response.getWriter();
        UserDAO adao = new UserDAO();
        HttpSession session = request.getSession();
        try {
            if (email != null) {
                if (isEmailExisted(email)) {
                    throw new Exception("This email is already existed");
                }
                randomCode = CreateRandom.generate6_DigitCode();
                request.setAttribute("vetifyCode", "aa");
                loginByGmailAccount(email, randomCode);
                request.getRequestDispatcher("create_account.jsp").forward(request, response);
                session.setAttribute("email", email);
            } else {
                if (code != null) {
                    if (code.equals(randomCode)) {
                        request.setAttribute("vetifyCode", "bb");
                        request.getRequestDispatcher("create_account.jsp").forward(request, response);
                    } else {
                        request.setAttribute("vetifyCode", "aa");
                        throw new Exception("Wrong code");
                    }
                }
                if (phoneNumber != null && password != null && rewritePassword != null) {
                    request.setAttribute("vetifyCode", "bb");
                    phoneNumber = InputValidation.getPhone(phoneNumber);
                    if (password.equals(rewritePassword) && InputValidation.getFormattedPassword(password)) {
                        User account = new User((String) session.getAttribute("email"), phoneNumber, password, 2);
                        adao.insertAccount(account);
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    } else {
                        out.println("hehe");
                    }
                }
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("create_account.jsp").forward(request, response);
        }
    }

    private boolean isEmailExisted(String email) throws SQLException {
        UserDAO adao = new UserDAO();
        Vector<String> list = adao.getAllEmail();
        for (String string : list) {
            if (string.equals(email)) {
                return true;
            }
        }
        return false;
    }

    private void loginByGmailAccount(String email, String code) throws Exception {
        IJavaMail emailService = new EmailService();
        String toEmail = email;
        String messageContent = code;
        String subject = "Vertified Code";
        if (!emailService.send(toEmail, subject, messageContent)) {
            throw new Exception("Failed to send email.");
        }
    }

}
