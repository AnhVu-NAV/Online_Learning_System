/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.PricePackageDAO;
import dal.UserCourseDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.PricePackage;
import model.UserCourse;

/**
 *
 * @author AnhVuNAV
 */
public class RegisterCourseController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterCourseController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterCourseController at " + request.getContextPath() + "</h1>");
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
        int courseId = Integer.parseInt(request.getParameter("courseId"));

        // Lấy danh sách gói giá cho courseId
        PricePackageDAO pricePackageDAO = new PricePackageDAO();
        List<PricePackage> pricePackages = pricePackageDAO.getPricePackagesByCourseId(courseId);

        // Đặt danh sách gói giá vào request attribute để hiển thị trên JSP
        request.setAttribute("pricePackages", pricePackages);

        // Forward đến RegisterPopup.jsp để hiển thị popup
        RequestDispatcher dispatcher = request.getRequestDispatcher("RegisterPopup.jsp");
        dispatcher.forward(request, response);
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
        // Lấy thông tin từ form đăng ký
        String fullName = request.getParameter("fullName");
        String phone1 = request.getParameter("phone1");
        String phone2 = request.getParameter("phone2");
        String email1 = request.getParameter("email1");
        String email2 = request.getParameter("email2");
        String contactMethod = request.getParameter("contactMethod");
        String gender = request.getParameter("gender");
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int pricePackageId = Integer.parseInt(request.getParameter("pricePackageId"));

        // Tạo đối tượng UserCourse để lưu vào cơ sở dữ liệu
        UserCourse userCourse = new UserCourse(fullName, phone1, phone2, email1, email2, contactMethod, gender, courseId, pricePackageId);
        UserCourseDAO userCourseDAO = new UserCourseDAO();
        userCourseDAO.registerUserForCourse(userCourse);

        // Chuyển hướng về trang xác nhận hoặc hiển thị thông báo thành công
        response.sendRedirect("confirmation.jsp");
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