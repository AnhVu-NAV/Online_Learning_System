/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;


import dal.UserDAO;
import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.User;
import model.Account;

/**
 *
 * @author admin
 */
public class ChangePasswordController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
========
public class UserProfileController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
>>>>>>>> 4d0982c745e6175e3e5a1a5b1ff350c362a92147:src/java/controller/UserProfileController.java
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
            out.println("</body>");
            out.println("</html>");
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
        // Lấy thông tin người dùng từ request hoặc session
        int userId = Integer.parseInt(request.getParameter("userId"));

        // Khởi tạo đối tượng AccountDAO
        AccountDAO accountDAO = new AccountDAO();
        
        // Lấy thông tin chi tiết của người dùng từ cơ sở dữ liệu
        Account user = accountDAO.getUserById(userId);
        
        // Kiểm tra nếu người dùng không tồn tại
        if (user == null) {
            // Xử lý lỗi hoặc chuyển hướng về trang lỗi
            response.sendRedirect("error.jsp");
            return;
        }
        
        // Đặt thông tin người dùng vào request
        request.setAttribute("user", user);
        
        // Chuyển tiếp đến trang JSP hiển thị thông tin người dùng
        request.getRequestDispatcher("UserProfile.jsp").forward(request, response);
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
        HttpSession session = request.getSession(false);
        User nuser = (User) session.getAttribute("us");

        String new_pass = request.getParameter("new_pass");
        String old_pass = request.getParameter("old_pass");
        String c_new_pass = request.getParameter("c_new_pass");

        if (nuser.getPassword().equals(old_pass)) {
            if (new_pass.equals(c_new_pass)){
                UserDAO cus = new UserDAO();
                cus.updatePass(nuser.getEmail(), new_pass);
                response.sendRedirect("ChangePassword.jsp");
            } else {
                request.setAttribute("error", "You entered confirm password diffent new password!");
                request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Password is not correct!");
            request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);

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
