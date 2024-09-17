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
import java.util.*;
import model.Account;
import util.InputValidation;

/**
 *
 * @author ADMIN
 */
public class CreateNewAccountController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateNewAccountController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateNewAccountController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone_number");
        String password = request.getParameter("password");
        String rewritePassword = request.getParameter("rewrite_password");

        try {
            email = InputValidation.getEmail(email);
            phoneNumber = InputValidation.getPhone(phoneNumber);
            if (isEmailExisted(email)) {
                throw new Exception("This email is already existed");
            }
            if (password.equals(rewritePassword)) {
                Account account = new Account(email, phoneNumber, password);
                PrintWriter out = response.getWriter();
                out.println(account.toString());
            } else {
                throw new Exception("Password is not duplicated");
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("createAccount.jsp").forward(request, response);
        }
    }

    private boolean isEmailExisted(String email) {
        AccountDAO adao = new AccountDAO();
        Vector<String> list = adao.getAllEmail();
        for (String string : list) {
            if (string.equals(email)) {
                return true;
            }
        }
        return false;
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
