/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ChapterDAO;
import dal.CourseDAO;
import dal.LessonDAO;
import dal.PricePackageDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Chapter;
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
            String courseIdParam = request.getParameter("courseId");
            if (courseIdParam == null || courseIdParam.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing courseId parameter");
                return;
            }

            int courseId = Integer.parseInt(courseIdParam);

            CourseDAO courseDAO = new CourseDAO();
            Course course = courseDAO.getCourseById(courseId);
            if (course == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Course not found");
                return;
            }

            ChapterDAO chapterDAO = new ChapterDAO();
            LessonDAO lessonDAO = new LessonDAO();

            // Lấy danh sách chương
            List<Chapter> chapters = chapterDAO.getChaptersByCourseId(courseId);

            // Lấy bài học cho từng chương
            for (Chapter chapter : chapters) {
                List<Lesson> lessons = lessonDAO.getLessonsByChapterId(chapter.getId());
                chapter.setLessons(lessons);
            }
            PricePackage pricePackage = courseDAO.getPricePackageByCourseId(courseId);

            // Lấy danh sách taglines
            List<String> taglines = courseDAO.getTaglinesByCourseId(courseId);

            // Lấy các khóa học liên quan dựa trên taglines hoặc category
            List<Course> relatedCourses = courseDAO.getRelatedCoursesByTaglines(taglines, courseId);
            if (relatedCourses.isEmpty()) {
                relatedCourses = courseDAO.getRelatedCourses(course.getCategoryId(), course.getExpertId(), course.getId());
            }
            PricePackageDAO pricePackageDAO = new PricePackageDAO();
            List<PricePackage> pricePackages = pricePackageDAO.getPricePackagesByCourseId(courseId);
            request.setAttribute("course", course);
            request.setAttribute("chapters", chapters);
            request.setAttribute("pricePackage", pricePackage);
            request.setAttribute("pricePackages", pricePackages);
            request.setAttribute("taglines", taglines);
            request.setAttribute("relatedCourses", relatedCourses);

            request.getRequestDispatcher("CourseDetails.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid course ID format");
        } catch (Exception e) {
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
