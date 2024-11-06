/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import model.User;

/**
 *
 * @author 84941
 */
<<<<<<<< HEAD:src/java/controller/UpdateUserProfileController.java
public class UpdateUserProfileController extends HttpServlet {
========
public class UserListController extends HttpServlet {
>>>>>>>> 53e8082 (Add model Account, UserLog, Setting, SettingType and their respectively DAO):src/java/controller/UserListController.java
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
<<<<<<<< HEAD:src/java/controller/UpdateUserProfileController.java
            out.println("<title>Servlet UpdateUserProfileController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateUserProfileController at " + request.getContextPath () + "</h1>");
========
            out.println("<title>Servlet UserListController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserListController at " + request.getContextPath () + "</h1>");
>>>>>>>> 53e8082 (Add model Account, UserLog, Setting, SettingType and their respectively DAO):src/java/controller/UserListController.java
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserById(userId);

        if (user == null) {
            response.sendRedirect("error.jsp");
        }

        request.setAttribute("user", user);
        request.getRequestDispatcher("UpdateUserProfile.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String primaryEmail = request.getParameter("primaryEmail");
            String secondaryEmail = request.getParameter("secondaryEmail");
            String firstPhone = request.getParameter("firstPhone");
            String secondPhone = request.getParameter("secondPhone");
            String genderParam = request.getParameter("gender");
            int gender = genderParam != null ? Integer.parseInt(genderParam) : 3; // Default to 'Other'
            String address = request.getParameter("address");
            String imageUrl = request.getParameter("imageFile"); // File upload handling might need additional code
            String preferContact = request.getParameter("preferContact");
            Date dob = request.getParameter("dob") != null ? new java.text.SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dob")) : null;

            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserById(userId);

            if (user == null) {
                response.sendRedirect("error.jsp");
                return;
            }

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPrimaryEmail(primaryEmail);
            user.setSecondaryEmail(secondaryEmail);
            user.setFirstPhone(firstPhone);
            user.setSecondPhone(secondPhone);
            user.setGender(gender);
            user.setAddress(address);
            user.setImageUrl(imageUrl);
            user.setPreferContact(preferContact);
            user.setDob(dob);

            userDAO.updateUser(user);
            response.sendRedirect("UserProfile?userId=" + userId);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Update User Profile";
    }// </editor-fold>

}
