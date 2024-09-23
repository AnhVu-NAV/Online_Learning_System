/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author ADMIN
 */
<<<<<<<< HEAD:src/java/controller/SliderDetailController.java
@WebServlet(name="SliderDetailController", urlPatterns={"/sliderDetail"})
public class SliderDetailController extends HttpServlet {
========
public class UpdateUserProfileController extends HttpServlet {
>>>>>>>> fbf38e88c7423732c03f084d6c211a2ea03af5a2:src/java/controller/UpdateUserProfileController.java
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
<<<<<<<< HEAD:src/java/controller/SliderDetailController.java
            out.println("<title>Servlet SliderDetailController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SliderDetailController at " + request.getContextPath () + "</h1>");
========
            out.println("<title>Servlet UpdateUserProfileController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateUserProfileController at " + request.getContextPath () + "</h1>");
>>>>>>>> fbf38e88c7423732c03f084d6c211a2ea03af5a2:src/java/controller/UpdateUserProfileController.java
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
<<<<<<<< HEAD:src/java/controller/SliderDetailController.java
        String action = request.getParameter("edit");
        String id_raw = request.getParameter("id");
        try {
            if (action != null) {
                request.setAttribute("edit", "edit"); 
                request.getRequestDispatcher("slider.jsp").forward(request, response); 
            }
        } catch (Exception e) {
        }
========
        int userId = Integer.parseInt(request.getParameter("userId"));

        AccountDAO accountDAO = new AccountDAO();
        Account user = accountDAO.getUserById(userId);

        if (user == null) {
            response.sendRedirect("error.jsp");
            return;
        }

        request.setAttribute("user", user);
        request.getRequestDispatcher("UpdateUserProfile.jsp").forward(request, response);
>>>>>>>> fbf38e88c7423732c03f084d6c211a2ea03af5a2:src/java/controller/UpdateUserProfileController.java
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String phone = request.getParameter("phone");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String address = request.getParameter("address");
        String imageUrl = request.getParameter("image_url");

        AccountDAO accountDAO = new AccountDAO();
        Account user = accountDAO.getUserById(userId);

        if (user == null) {
            response.sendRedirect("error.jsp");
            return;
        }

        user.setFirst_name(firstName);
        user.setLast_name(lastName);
        user.setPhone(phone);
        user.setGender(gender);
        user.setAddress(address);
        user.setImage_url(imageUrl);

        accountDAO.updateUser(user);
        response.sendRedirect("UserProfile?userId=" + userId);
    }

    @Override
    public String getServletInfo() {
        return "Update User Profile";
    }// </editor-fold>

}
