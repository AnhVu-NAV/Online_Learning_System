/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Vector;
import model.Account;
import send_email.EmailService;
import send_email.IJavaMail;
import util.*;


/**
 *
 * @author 84941
 */
public class DashboardController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        AccountDAO accountDAO = new AccountDAO();
        DataConvert dataConvert = new DataConvert();
        String service = request.getParameter("service");
        if (service == null) {
            service = "viewAllAccount";
        }
        if (service.equals("viewAllAccount")) {
            
            String fillterSubmit = request.getParameter("fillterSubmit");
            //get data
            Vector<Account> vector = null;
            String sql = "select * from account ";
            String checked = "sort_by_id";
            if (fillterSubmit != null) {
                String search_by_name = request.getParameter("search_by_name");
                String search_by_email = request.getParameter("search_by_email");
                String search_by_phone = request.getParameter("search_by_phone");
                String fillter_by_gender = request.getParameter("fillter_by_gender");
                String fillter_by_role = request.getParameter("fillter_by_role");
                String fillter_by_status = request.getParameter("fillter_by_status");
                String sort_by = request.getParameter("sort_by");
                sql += " where (lower(first_name) like '%" + search_by_name.toLowerCase() + "%' or lower(last_name) like '%" + search_by_name.toLowerCase() + "%' ) "
                        + " and email like '%" + search_by_email + "%' "
                        + " and phone like '%" + search_by_phone + "%' ";
                if (!fillter_by_gender.equals("all")) {
                    sql += " and gender= " + fillter_by_gender;
                }
                if (!fillter_by_role.equals("all")) {
                    sql += " and role_id =" + fillter_by_role;
                }
                if (!fillter_by_status.equals("all")) {
                    sql += " and status =" + fillter_by_status;
                }
                if (sort_by != null) {
                    switch (sort_by) {
                        case "sort_by_id": {
                            sql += " order by id asc";
                            break;
                        }
                        case "sort_by_name": {
                            sql += " order by last_name asc,first_name asc ";
                            checked = "sort_by_name";
                            break;
                        }
                        case "sort_by_gender": {
                            sql += " order by gender asc ";
                            checked = "sort_by_gender";
                            break;
                        }
                        case "sort_by_email": {
                            sql += " order by email asc";
                            checked = "sort_by_email";
                            break;
                        }
                        case "sort_by_phone": {
                            sql += " order by phone asc ";
                            checked = "sort_by_phone";
                            break;
                        }
                        case "sort_by_role": {
                            sql += " order by role_id asc ";
                            checked = "sort_by_role";
                            break;
                        }
                    }
                }
            }

            vector = accountDAO.getAccounts(sql);
            //set data for views
            request.setAttribute("data", vector);
            request.setAttribute("checked", checked);
            // select view
            RequestDispatcher dispath = request.getRequestDispatcher("jsp/ViewUserList.jsp");
            //run
            dispath.forward(request, response);

        }
        if (service.equals("viewUserDetails")) {
            String id_raw = request.getParameter("id");
            Account account = accountDAO.getAccountById(Integer.parseInt(id_raw));
            //set data for views
            request.setAttribute("account", account);
            // select view
            RequestDispatcher dispath = request.getRequestDispatcher("jsp/ViewUserDetails.jsp");
            //run
            dispath.forward(request, response);
        }
        if(service.equals("addNewUser")){
            String first_name= request.getParameter("addNewUserFirstName");
            String last_name = request.getParameter("addNewUserLastName");
            Date dob = dataConvert.StringToSqlDate(request.getParameter("addNewUserDob"));
            Boolean gender = Boolean.parseBoolean(request.getParameter("addNewUserGender"));
            String email = request.getParameter("addNewUserEmail");
            String phone = request.getParameter("addNewUserPhone");
            String address = request.getParameter("addNewUserAddress");
            int role_id = Integer.parseInt(request.getParameter("addNewUserRole"));
            String password = CreateRandom.generate6_DigitCode();
            Date created_date = GetTodayDate.getTodayDate();
            Account account =  new Account(0, email, first_name, last_name, password, dob, role_id, created_date, 2, phone, gender, address, null);
            accountDAO.insertAccount(account);
            String subject="New account successfully created. Please login with your default password";
            String email_content="Your default password is "+password;
            IJavaMail emailService = new EmailService();
            emailService.send(email, subject , email_content);
            String message = "Add new user successfuly";
            //set data for views
            request.setAttribute("message", message);
            // select view
            RequestDispatcher dispath = request.getRequestDispatcher("DashboardController?service=viewAllAccount");
            //run
            dispath.forward(request, response);
                              
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
