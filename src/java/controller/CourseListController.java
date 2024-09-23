/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CourseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Arrays;
import model.Course;
import model.Setting;
import model.Tagline;

/**
 *
 * @author AnhVuNAV
 */
public class CourseListController extends HttpServlet {

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
            out.println("<title>Servlet CourseListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CourseListController at " + request.getContextPath() + "</h1>");
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
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CourseDAO courseDAO = new CourseDAO();

        List<Setting> categories = courseDAO.getCourseCategories();

        // Lấy danh sách các Taglines
        List<Tagline> taglines = courseDAO.getAllTaglines(); // Thêm dòng này
        request.setAttribute("taglines", taglines); // Đặt danh sách tagline vào request

        // Xử lý lọc danh sách khóa học theo danh mục
        String[] selectedCategories = request.getParameterValues("category");
        String[] selectedTaglines = request.getParameterValues("tagline"); // Lấy các tagline đã chọn
        String searchKeyword = request.getParameter("search");
        String sortOption = request.getParameter("sort");

        // Lấy trạng thái checkbox từ request
        String showTitle = request.getParameter("showTitle");
        String showTagline = request.getParameter("showTagline");
        String showPrice = request.getParameter("showPrice");

        // Chuyển selectedCategories thành danh sách
        List<String> selectedCategoriesList = selectedCategories != null ? Arrays.asList(selectedCategories) : new ArrayList<>();
        List<String> selectedTaglinesList = selectedTaglines != null ? Arrays.asList(selectedTaglines) : new ArrayList<>();

        // Xử lý phân trang
        int page = 1;
        int pageSize = 15; // Giá trị mặc định
        // Xử lý lọc danh sách khóa học theo danh mục
        String[] selectedCategories = request.getParameterValues("category");
        String searchKeyword = request.getParameter("search");
        String sortOption = request.getParameter("sort");

        // Chuyển selectedCategories thành danh sách
        List<String> selectedCategoriesList = selectedCategories != null ? Arrays.asList(selectedCategories) : new ArrayList<>();

        // Xử lý phân trang
        int page = 1;
        int pageSize = 15;
        try {
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            if (request.getParameter("coursesPerPage") != null) {
                pageSize = Integer.parseInt(request.getParameter("coursesPerPage"));
            }
        } catch (NumberFormatException e) {
            page = 1; // Mặc định về trang 1 nếu lỗi định dạng
        }

        // Lấy danh sách các khóa học dựa trên danh mục, từ khóa và phân trang
        List<Course> courses = courseDAO.getCoursesByCategoriesAndKeyword(selectedCategoriesList, searchKeyword, sortOption, page, pageSize);

        
        // Tổng số khóa học để tính tổng số trang
        int totalCourses = courseDAO.getTotalCoursesByCategoriesAndKeyword(selectedCategoriesList, searchKeyword);
        int totalPages = (int) Math.ceil((double) totalCourses / pageSize);

        // Đặt các thuộc tính cho request để chuyển tới JSP
        request.setAttribute("courses", courses);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("selectedCategories", selectedCategoriesList);
        request.setAttribute("selectedTaglines", selectedTaglinesList); // Truyền danh sách tagline đã chọn
        request.setAttribute("searchKeyword", searchKeyword);
        request.setAttribute("sortOption", sortOption);
        request.setAttribute("showTitle", showTitle); // Truyền trạng thái checkbox
        request.setAttribute("showTagline", showTagline); // Truyền trạng thái checkbox
        request.setAttribute("showPrice", showPrice); // Truyền trạng thái checkbox
        request.setAttribute("searchKeyword", searchKeyword);
        request.setAttribute("sortOption", sortOption); 

        // Chuyển tiếp tới JSP
        request.getRequestDispatcher("CourseList.jsp").forward(request, response);
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

    private List<Course> fetchCourses(String searchQuery, String sort, int currentPage) {
        // Mock database fetching logic
        List<Course> allCourses = new ArrayList<>();
        // Populate the list of courses here

        // Apply sorting, filtering (if search query exists), and pagination logic
        return allCourses;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "CourseListController - Handles the course listing and filtering.";
    }// </editor-fold>

}
