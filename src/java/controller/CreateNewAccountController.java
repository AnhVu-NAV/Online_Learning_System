/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import dal.SettingDAO;
import dal.SettingTypeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.*;
import java_mail.EmailService;
import java_mail.IJavaMail;
import model.Account;
import model.Setting;
import util.*;

/**
 *
 * @author ADMIN
 */
public class CreateNewAccountController extends HttpServlet {

    private static String randomCode = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        request.getRequestDispatcher("create_account.jsp").forward(request, response);
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
        AccountDAO adao = new AccountDAO();
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
                        Account account = getAccount((String) session.getAttribute("email"), phoneNumber, password);
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
        AccountDAO adao = new AccountDAO();
        Vector<String> list = adao.getAllEmail();
        for (String string : list) {
            if (string.equals(email)) {
                return true;
            }
        }
        return false;
    }

    private Account getAccount(String email, String phoneNumber, String password) throws Exception {
        SettingDAO setdao = new SettingDAO();
        SettingTypeDAO setttingTypeDAO = new SettingTypeDAO();
        setttingTypeDAO.insert("");
        Setting setting = new Setting(setttingTypeDAO.getSettingTypeByName(""), "", 0);
        setdao.insert(setting);
        if (setdao.getMaxId() != 0) {
            Account account = new Account(email, phoneNumber, password, setdao.getSettingById(setdao.getMaxId()));
            return account;
        } else {
            throw new Exception("Cannot create account according to unidentified error");
        }
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
