/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDAO;
import dal.SettingDAO;
import dal.SettingTypeDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Vector;
import model.*;
import sendEmail.EmailService;
import sendEmail.IJavaMail;
import util.*;

/**
 *
 * @author 84941
 */
public class UserDashboardController extends HttpServlet {

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
        UserDAO userDAO = new UserDAO();
        SettingDAO settingDAO = new SettingDAO();
        SettingTypeDAO settingTypeDAO = new SettingTypeDAO();
        DataConvert dataConvert = new DataConvert();
        String service = request.getParameter("service");
        if (service == null) {
            service = "viewAllUser";
        }
        if (service.equals("viewAllUser")) {

            String fillterSubmit = request.getParameter("fillterSubmit");
            //get data
            Vector<User> userVector = null;
            Vector<Setting> settingVector = null;
            SettingType settingType = null;
            String sql = "select * from user ";
            String checked = "sortById";
            //Display all fields by default
            request.setAttribute("displayId", "true");
            request.setAttribute("displayFullName", "true");
            request.setAttribute("displayGender", "true");
            request.setAttribute("displayEmail", "true");
            request.setAttribute("displayFirstPhone", "true");
            request.setAttribute("displaySecondPhone", "true");
            request.setAttribute("displayRole", "true");
            request.setAttribute("displayStatus", "true");
            
            if (fillterSubmit != null) {
                String searchByName = request.getParameter("searchByName");
                String searchByEmail = request.getParameter("searchByEmail");
                String searchByPhone = request.getParameter("searchByPhone");
                String fillterByGender = request.getParameter("fillterByGender");
                String fillterByRole = request.getParameter("fillterByRole");
                String fillterByStatus = request.getParameter("fillterByStatus");
                String sortBy = request.getParameter("sortBy");
                //reset all display fields
                request.setAttribute("displayId", request.getParameter("displayId"));
                request.setAttribute("displayFullName", request.getParameter("displayFullName"));
                request.setAttribute("displayGender", request.getParameter("displayGender"));
                request.setAttribute("displayEmail", request.getParameter("displayEmail"));
                request.setAttribute("displayFirstPhone", request.getParameter("displayFirstPhone"));
                request.setAttribute("displaySecondPhone", request.getParameter("displaySecondPhone"));
                request.setAttribute("displayRole", request.getParameter("displayRole"));
                request.setAttribute("displayStatus", request.getParameter("displayStatus"));
                sql += " where (lower(first_name) like '%" + searchByName.toLowerCase() + "%' or lower(last_name) like '%" + searchByName.toLowerCase() + "%' ) "
                        + " and primary_email like '%" + searchByEmail + "%' "
                        + " and (lower(first_phone) like '%" + searchByPhone + "%' or lower(second_phone) like '%" + searchByPhone + "%' )";
                if (!fillterByGender.equals("all")) {
                    sql += " and gender= " + fillterByGender;
                }
                if (!fillterByRole.equals("all")) {
                    sql += " and role_id =" + fillterByRole;
                }
                if (!fillterByStatus.equals("all")) {
                    sql += " and status =" + fillterByStatus;
                }
                if (sortBy != null) {
                    switch (sortBy) {
                        case "sortById": {
                            sql += " order by id asc";
                            break;
                        }
                        case "sortByName": {
                            sql += " order by last_name asc,first_name asc ";
                            checked = "sortByName";
                            break;
                        }
                        case "sortByGender": {
                            sql += " order by gender asc ";
                            checked = "sortByGender";
                            break;
                        }
                        case "sortByEmail": {
                            sql += " order by primary_email asc ";
                            checked = "sortByEmail";
                            break;
                        }
                        case "sortByPhone": {
                            sql += " order by first_phone asc, second_phone asc ";
                            checked = "sortByPhone";
                            break;
                        }
                        case "sortByRole": {
                            sql += " order by role_id asc ";
                            checked = "sortByRole";
                            break;
                        }
                    }
                }
            }
            //paging
            int nrpp = 5;
            userVector = userDAO.getUsers(sql);
            int totalPage = (userVector.size() + nrpp - 1) / nrpp;
            String indexRaw = request.getParameter("index");
            int index = 1;
            if (indexRaw != null) {
                index = Integer.parseInt(indexRaw);
            }
            sql += " limit " + (index - 1) * nrpp + "," + nrpp;
            userVector = userDAO.getUsers(sql);
            settingType = settingTypeDAO.getSettingTypeByName("User Role");
            settingVector = settingDAO.getSettings("select * from Setting where setting_type_id=" + settingType.getId());
            //set data for views
            request.setAttribute("data", userVector);
            request.setAttribute("setting", settingVector);
            request.setAttribute("checked", checked);
            request.setAttribute("totalPage", totalPage);
            // select view
            RequestDispatcher dispath = request.getRequestDispatcher("jsp/ViewUserList.jsp");
            //run
            dispath.forward(request, response);

        }
        if (service.equals("viewUserDetails")) {
            String idRaw = request.getParameter("id");
            User user = userDAO.getUserById(Integer.parseInt(idRaw));
            Vector<Setting> settingVector = null;
            SettingType settingType = null;
            settingType = settingTypeDAO.getSettingTypeByName("User Role");
            settingVector = settingDAO.getSettings("select * from Setting where setting_type_id=" + settingType.getId());
            //set data for views
            request.setAttribute("user", user);
            request.setAttribute("setting", settingVector);
            // select view
            RequestDispatcher dispath = request.getRequestDispatcher("jsp/ViewUserDetails.jsp");
            //run
            dispath.forward(request, response);
        }
        if (service.equals("addNewUser")) {
            String firstName = request.getParameter("addNewUserFirstName");
            String lastName = request.getParameter("addNewUserLastName");
            Date dob = dataConvert.StringToSqlDate(request.getParameter("addNewUserDob"));
            int gender = Integer.parseInt(request.getParameter("addNewUserGender"));
            String email = request.getParameter("addNewUserEmail");
            String phone = request.getParameter("addNewUserPhone");
            int roleId = Integer.parseInt(request.getParameter("addNewUserRole"));
            String password = CreateRandom.generate6DigitCode();
            Date createdDate = GetTodayDate.getTodayDate();
            User user = new User(roleId, email, PasswordEncryption.EncryptBySHA256(password), roleId, createdDate, 2, firstName, lastName, dob, gender, phone, "", "", "", phone);
            userDAO.insertUser(user);
            
            String subject = "New user successfully created. Please login with your default password";
            String emailContent = "Your default password is " + password;
            IJavaMail emailService = new EmailService();
            emailService.send(email, subject, emailContent);
            String message = "Add new user successfuly";
            //set data for views
            request.setAttribute("message", message);
            // select view
            RequestDispatcher dispath = request.getRequestDispatcher("UserDashboardController?service=viewAllUser");
            //run
            dispath.forward(request, response);

        }
        if (service.equals("updateUser")) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            String roleIdRaw = request.getParameter("roleId");
            String statusRaw = request.getParameter("status");
            if (roleIdRaw != null) {
                int roleId = Integer.parseInt(roleIdRaw);
                User user = userDAO.getUserById(userId);
                user.setRoleId(roleId);
                userDAO.updateUser(user);
                String message = "Update user role successfuly";
                //set data for views
                request.setAttribute("message", message);
                // select view
                RequestDispatcher dispath = request.getRequestDispatcher("UserDashboardController?service=viewAllUser");
                //run
                dispath.forward(request, response);

            }
            if (statusRaw != null) {
                int status = Integer.parseInt(statusRaw);
                User user = userDAO.getUserById(userId);
                user.setStatus(status);
                userDAO.updateUser(user);
                String message = "Update user status successfuly";
                //set data for views
                request.setAttribute("message", message);
                // select view
                RequestDispatcher dispath = request.getRequestDispatcher("UserDashboardController?service=viewAllUser");
                //run
                dispath.forward(request, response);
            }

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
