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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        request.getRequestDispatcher("create_account.jsp").forward(request, response);
        String action = request.getParameter("action");
        try {
            if (action != null) {
                if (action.equals("gmail")) {
                    loginByGmailAccount();
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
        AccountDAO adao = new AccountDAO();

        try {
            email = InputValidation.getEmail(email);
            phoneNumber = InputValidation.getPhone(phoneNumber);
            if (isEmailExisted(email)) {
                throw new Exception("This email is already existed");
            }
            if (password.equals(rewritePassword) && InputValidation.checkFormatOfPassword(password)) {
                Account account = getAccount(email, phoneNumber, password);
                adao.insertAccount(account);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                throw new Exception("Password is not duplicated");
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

    private void loginByGmailAccount() throws Exception {
        IJavaMail emailService = new EmailService();
        String toEmail = "tranxuanhoan04@gmail.com";
        String subject = "Test my module";
        String messageContent = "Can you read it? I'm very happy if you can see it";
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
