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
import java.util.Vector;
import model.Account;

/**
 *
 * @author 84941
 */
public class AdminDashboardController extends HttpServlet {

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
        String service = request.getParameter("service");
        if (service == null) {
            service = "viewAllAccount";
        }
        if (service.equals("viewAllAccount")) {
            //check service
            String submit = request.getParameter("submit");
            //get data
            Vector<Account> vector = null;
            String sql = "select * from accounts ";
            String checked="sort_by_id";
            if (submit != null) {
                String search_by_name = request.getParameter("search_by_name");
                String search_by_email = request.getParameter("search_by_email");
                String search_by_phone_number = request.getParameter("search_by_phone_number");
                String fillter_by_gender = request.getParameter("fillter_by_gender");
                String fillter_by_role = request.getParameter("fillter_by_role");
                String fillter_by_status = request.getParameter("fillter_by_status");
                String sort_by = request.getParameter("sort_by");
                sql += " where (lower(first_name) like '%" + search_by_name.toLowerCase() + "%' or lower(last_name) like '%" + search_by_name.toLowerCase() + "%' ) "
                        + " and email like '%" + search_by_email + "%' "
                        + " and phone_number like '%" + search_by_phone_number + "%' ";
                if (!fillter_by_gender.equals("All gender")) {
                    sql += " and gender =" + fillter_by_gender;
                }
                if (!fillter_by_role.equals("All role")) {
                    sql += " and role_id =" + fillter_by_role;
                }
                if (!fillter_by_status.equals("All status")) {
                    sql += " and status =" + fillter_by_status;
                }               
                if (sort_by != null) {
                    switch (sort_by) {
                        case "sort_by_id": {
                            sql += "order by id asc";
                            break;
                        }
                        case "sort_by_name": {
                            sql += " order by last_name asc,first_name asc ";
                            checked="sort_by_name";
                            break;
                        }
                        case "sort_by_gender": {
                            sql += " order by gender asc ";
                            checked="sort_by_gender";
                            break;
                        }
                        case "sort_by_email": {
                            sql += "order by email asc";
                            checked="sort_by_email";
                            break;
                        }
                        case "sort_by_phone_number": {
                            sql += " order by phone_number asc ";
                            checked="sort_by_phone_number";
                            break;
                        }
                        case "sort_by_role": {
                            sql += " order by role_id asc ";
                            checked="sort_by_role";
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
            RequestDispatcher dispath = request.getRequestDispatcher("ViewUserList.jsp");
            //run
            dispath.forward(request, response);

        }
        if (service.equals("viewUserDetails")) {
            String search_id_raw = request.getParameter("search_id");
            Account account = accountDAO.getAccountById(Integer.parseInt(search_id_raw));
            //set data for views
            request.setAttribute("account", account);
            // select view
            RequestDispatcher dispath = request.getRequestDispatcher("ViewUserDetails.jsp");
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
