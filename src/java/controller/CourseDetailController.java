/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CourseDAO;
import dal.LessonDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Course;
import model.Lesson;
import model.PricePackage;

/**
 *
 * @author AnhVuNAV
 */
public class CourseDetailController extends HttpServlet {

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
            out.println("<title>Servlet CourseDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CourseDetailController at " + request.getContextPath() + "</h1>");
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
        try {
            // Lấy courseId từ request, kiểm tra giá trị hợp lệ
            String courseIdParam = request.getParameter("courseId");
            if (courseIdParam == null || courseIdParam.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing courseId parameter");
                return;
            }

            int courseId = Integer.parseInt(courseIdParam);

            // Lấy thông tin khóa học dựa vào courseId
            CourseDAO courseDAO = new CourseDAO();
            Course course = courseDAO.getCourseById(courseId);

            // Kiểm tra nếu khóa học không tồn tại
            if (course == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Course not found");
                return;
            }

            // Lấy danh sách các bài học của khóa học
            LessonDAO lessonDAO = new LessonDAO();
            List<Lesson> lessons = lessonDAO.getLessonsByCourseId(courseId);

            // Lấy gói giá của khóa học
            PricePackage pricePackage = courseDAO.getPricePackageByCourseId(courseId);

            // Lấy các khóa học liên quan dựa trên category_id và expert_id của khóa học hiện tại
            List<Course> relatedCourses = courseDAO.getRelatedCourses(course.getCategoryId(), course.getExpertId(), course.getId());

            // Đưa dữ liệu vào request
            request.setAttribute("course", course);
            request.setAttribute("lessons", lessons);
            request.setAttribute("pricePackage", pricePackage);
            request.setAttribute("relatedCourses", relatedCourses);

            // Chuyển tiếp tới trang JSP để hiển thị thông tin
            request.getRequestDispatcher("CourseDetails.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            // Xử lý ngoại lệ khi courseId không phải là số nguyên hợp lệ
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid course ID format");
        } catch (Exception e) {
            // Xử lý các lỗi khác, trả về mã lỗi 500
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
            e.printStackTrace();
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Course Detail";
    }// </editor-fold>

}
