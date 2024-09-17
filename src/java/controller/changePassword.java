/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

<<<<<<<< HEAD:src/java/controller/UserProfileController.java
import dal.UserDAO;
========
import dal.CustomerDAO;
>>>>>>>> 87dc0d2 (Added ChangePass and ResetPass function):src/java/controller/changePassword.java
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
<<<<<<<< HEAD:src/java/controller/UserProfileController.java
import model.User;
========
import jakarta.servlet.http.HttpSession;
import model.Customer;
>>>>>>>> 87dc0d2 (Added ChangePass and ResetPass function):src/java/controller/changePassword.java

/**
 *
 * @author admin
 */
<<<<<<<< HEAD:src/java/controller/UserProfileController.java
public class UserProfileController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
========
public class changePassword extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
>>>>>>>> 87dc0d2 (Added ChangePass and ResetPass function):src/java/controller/changePassword.java
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
<<<<<<<< HEAD:src/java/controller/UserProfileController.java
            out.println("<title>Servlet UserProfileController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserProfileController at " + request.getContextPath () + "</h1>");
========
            out.println("<title>Servlet changePass</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet changePass at " + request.getContextPath() + "</h1>");
>>>>>>>> 87dc0d2 (Added ChangePass and ResetPass function):src/java/controller/changePassword.java
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
<<<<<<<< HEAD:src/java/controller/UserProfileController.java
    throws ServletException, IOException {
        // Lấy thông tin người dùng từ request hoặc session
        int userId = Integer.parseInt(request.getParameter("userId"));

        // Khởi tạo đối tượng UserDAO
        UserDAO accountDAO = new UserDAO();
        
        // Lấy thông tin chi tiết của người dùng từ cơ sở dữ liệu
        User user = accountDAO.getUserById(userId);
        
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
========
            throws ServletException, IOException {
        processRequest(request, response);
    }
>>>>>>>> 87dc0d2 (Added ChangePass and ResetPass function):src/java/controller/changePassword.java

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
        Customer nuser = (Customer) session.getAttribute("us");

        String new_pass = request.getParameter("new_pass");
        String old_pass = request.getParameter("old_pass");
        String c_new_pass = request.getParameter("c_new_pass");

        if (nuser.getPassword().equals(old_pass)) {
            if (new_pass.equals(c_new_pass)){
                CustomerDAO cus = new CustomerDAO();
                cus.updatePass(nuser.getEmail(), new_pass);
                response.sendRedirect("AccountController");
            } else {
                request.setAttribute("error", "You entered confirm password diffent new password!");
                request.getRequestDispatcher("changePass.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Password is not correct!");
            request.getRequestDispatcher("changePass.jsp").forward(request, response);

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
