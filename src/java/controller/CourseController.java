/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CourseDAO;
import dal.SettingDAO;
import dal.TaglineDAO;
import dal.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Course;
import model.Setting;
import model.Tagline;
import model.User;

/**
 *
 * @author AnhVuNAV
 */
public class CourseController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CourseDAO courseDAO;
    private SettingDAO settingDAO;
    private TaglineDAO taglineDAO;
    private UserDAO userDAO;

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
            out.println("<title>Servlet CourseController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CourseController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    public void init() {
        courseDAO = new CourseDAO();
        settingDAO = new SettingDAO();
        taglineDAO = new TaglineDAO();
        userDAO = new UserDAO();
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
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertCourse(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateCourse(request, response);
                    break;
                default:
                    listCourses(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listCourses(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        // Nhận thông tin tìm kiếm, danh mục, trạng thái từ request
        String searchQuery = request.getParameter("searchQuery") != null ? request.getParameter("searchQuery") : "";
        String categoryFilter = request.getParameter("category") != null ? request.getParameter("category") : "";
        String statusFilter = request.getParameter("status") != null ? request.getParameter("status") : "";

        // Nhận thông tin phân trang từ request (trang hiện tại và số khóa học trên mỗi trang)
        int page = 1;
        int subjectsPerPage = 10;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("subjectsPerPage") != null) {
            subjectsPerPage = Integer.parseInt(request.getParameter("subjectsPerPage"));
        }

        // Tính toán chỉ số bắt đầu cho trang hiện tại
        int startIndex = (page - 1) * subjectsPerPage;

        // Lấy danh sách khóa học theo phân trang
        List<Course> listCourse = courseDAO.listCoursesWithPagination(searchQuery, categoryFilter, statusFilter, startIndex, subjectsPerPage);

        // Lấy tổng số khóa học để tính số trang
        int totalCourses = courseDAO.getTotalCourses(searchQuery, categoryFilter, statusFilter);
        int totalPages = (int) Math.ceil((double) totalCourses / subjectsPerPage);

        // Lấy danh sách danh mục để hiển thị filter
        List<Setting> categoryList = settingDAO.getAllCategories();

        // Đặt các thuộc tính vào request để truyền cho JSP
        request.setAttribute("listCourse", listCourse);
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("subjectsPerPage", subjectsPerPage);

        // Forward đến trang subjectList.jsp
        request.getRequestDispatcher("subjectList.jsp").forward(request, response);
    }

    // Hiển thị form thêm khóa học mới
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
//        List<Setting> categoryList = settingDAO.getAllCategories();
//        List<User> expertList = userDAO.getExperts();
//        List<Tagline> taglineList = taglineDAO.getAllTaglines();
//
//        request.setAttribute("categoryList", categoryList);
//        request.setAttribute("expertList", expertList);
//        request.setAttribute("taglineList", taglineList);

        List<Tagline> taglineList = courseDAO.getAllTaglines(); // Fetch all taglines
        List<Setting> categoryList = courseDAO.getCourseCategories(); // Fetch all course categories
        List<User> expertList = userDAO.getExperts();

        request.setAttribute("taglineList", taglineList); // Set taglines in request scope
        request.setAttribute("categoryList", categoryList); // Set categories in request scope
        request.setAttribute("expertList", expertList);

        request.getRequestDispatcher("newSubject.jsp").forward(request, response);
    }

    // Thêm khóa học mới vào database
    private void insertCourse(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        // Get course details from the request
        String name = request.getParameter("name");
        String subtitle = request.getParameter("subtitle");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        int ownerId = Integer.parseInt(request.getParameter("ownerId"));
        int status = Integer.parseInt(request.getParameter("status"));
        String description = request.getParameter("description");

        // Handle thumbnail images
        Part[] thumbnailParts = request.getParts().stream()
                .filter(part -> "thumbnail".equals(part.getName()) && part.getSize() > 0)
                .toArray(Part[]::new);

        // Limit to 5 images
        List<String> imageUrls = new ArrayList<>();
        // Capture descriptions for each thumbnail image
        List<String> descriptions = new ArrayList<>();
        for (int i = 0; i < thumbnailParts.length && i < 5; i++) {
            Part part = thumbnailParts[i];
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            String imagePath = "img/" + fileName;

            // Save file to the server
            part.write(request.getServletContext().getRealPath("/") + imagePath);
            imageUrls.add(imagePath);

            // Get the description for each image
            String descriptionParam = "thumbnailDescription" + (i + 1); // Assuming description fields are named sequentially
            String imageDescription = request.getParameter(descriptionParam);
            descriptions.add(imageDescription != null ? imageDescription : ""); // Add description or empty if null
        }

        // Insert the course into the database
        Course newCourse = new Course(name, subtitle, categoryId, ownerId, description, status);
        int courseId = courseDAO.insertCourse(newCourse);

        // Insert the thumbnail URLs into the Course_Thumbnails table
        if (!imageUrls.isEmpty()) {
            courseDAO.insertCourseThumbnails(courseId, imageUrls, descriptions);
        }

        // Handle taglines
        String[] taglineIds = request.getParameterValues("tagline[]");
        if (taglineIds != null) {
            courseDAO.insertTaglines(courseId, taglineIds);
        }

        // Redirect to the course list page
        response.sendRedirect("CourseController?action=list");
    }

    // Hiển thị form chỉnh sửa khóa học
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Course existingCourse = courseDAO.getCourseById(id);
        System.out.println(id);

        // Check if the course exists
        if (existingCourse == null) {
            // Redirect to the course list with an error message
            request.setAttribute("errorMessage", "The course you're trying to edit does not exist.");
            request.getRequestDispatcher("CourseController?action=list").forward(request, response);
            return;
        }

        // Get related data such as categories and experts
        List<Setting> categoryList = settingDAO.getAllCategories();
        List<User> expertList = userDAO.getExperts();
        List<Integer> courseTaglineIds = courseDAO.getTaglineIdsByCourseId(id);
        List<Tagline> taglineList = taglineDAO.getAllTaglines();

        // Set the checked status for the taglines
        for (Tagline tagline : taglineList) {
            tagline.setChecked(courseTaglineIds.contains(tagline.getId()));
        }

        // Get existing thumbnails if any
        List<String> thumbnailUrls = existingCourse.getThumbnailUrls();

        // Set attributes for the request
        request.setAttribute("course", existingCourse);
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("expertList", expertList);
        request.setAttribute("taglineList", taglineList);
        request.setAttribute("thumbnailUrls", thumbnailUrls);

        // Forward to the edit JSP
        request.getRequestDispatcher("editSubject.jsp").forward(request, response);
    }

    // Cập nhật khóa học đã tồn tại trong database
    private void updateCourse(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String subtitle = request.getParameter("subtitle");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        int expertId = Integer.parseInt(request.getParameter("expertId"));
        String description = request.getParameter("description");
        int status = Integer.parseInt(request.getParameter("status"));
        String[] taglines = request.getParameterValues("tagline[]");

        Course updatedCourse = new Course(id, title, subtitle, categoryId, expertId, description, status);
        courseDAO.updateCourse(updatedCourse, taglines);

        response.sendRedirect("CourseController?action=list");
    }

    // In CourseController.java
    private void saveTagline(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String taglineName = request.getParameter("taglineName");
        if (taglineName == null || taglineName.trim().isEmpty()) {
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": false}");
            return;
        }

        // Save the tagline to the database
        int newTaglineId = taglineDAO.insertTagline(taglineName);

        // Respond with the new tagline ID
        response.setContentType("application/json");
        if (newTaglineId > 0) {
            response.getWriter().write("{\"success\": true, \"id\": " + newTaglineId + "}");
        } else {
            response.getWriter().write("{\"success\": false}");
        }
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
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "insert":
                    insertCourse(request, response);
                    break;
                case "saveTagline":
                    saveTagline(request, response);
                    break;
                default:
                    listCourses(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
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
