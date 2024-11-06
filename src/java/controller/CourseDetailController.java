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
            Course course = courseDAO.getCourseByID(courseId);
            if (course == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Course not found");
                return;
            }

            // Get all thumbnail URLs for the course
            List<String> thumbnailUrls = courseDAO.getThumbnailUrlsByCourseId(courseId);
            course.setThumbnailUrls(thumbnailUrls);

            ChapterDAO chapterDAO = new ChapterDAO();
            LessonDAO lessonDAO = new LessonDAO();

            // Get chapters and lessons
            List<Chapter> chapters = chapterDAO.getChaptersByCourseID(courseId);
            for (Chapter chapter : chapters) {
                List<Lesson> lessons = lessonDAO.getLessonsByChapterID(chapter.getId());
                chapter.setLessons(lessons);
            }

            // Get price packages
            PricePackageDAO pricePackageDAO = new PricePackageDAO();
            List<PricePackage> pricePackages = pricePackageDAO.getPricePackagesByCourseId(courseId);

            // Set attributes for the JSP
            request.setAttribute("course", course);
            request.setAttribute("chapters", chapters);
            request.setAttribute("pricePackages", pricePackages);
            request.setAttribute("taglines", courseDAO.getTaglinesByCourseId(courseId));
            request.setAttribute("relatedCourses", courseDAO.getRelatedCourses(course.getCategoryId(), course.getExpertId(), course.getId()));
            System.out.println("Related Courses: " + courseDAO.getRelatedCourses(course.getCategoryId(), course.getExpertId(), course.getId()));
            // Forward to the JSP
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
