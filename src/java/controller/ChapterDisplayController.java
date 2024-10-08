/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ChapterDAO;
import dal.LessonDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Chapter;
import model.Lesson;

/**
 *
 * @author Admin
 */
public class ChapterDisplayController extends HttpServlet {

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
            out.println("<title>Servlet ChapterDisplayController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChapterDisplayController at " + request.getContextPath() + "</h1>");
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

        int courseId = 17;

        // Lấy danh sách chapter
        ChapterDAO cDao = new ChapterDAO();
        List<Chapter> chapters = cDao.getChaptersByCourseId(courseId);
        request.setAttribute("chapters", chapters);

        LessonDAO lDao = new LessonDAO();
        if (chapters != null && !chapters.isEmpty()) {
            for (Chapter chapter : chapters) {
                List<Lesson> lessons = lDao.getLessonsByChapterId(chapter.getId());

                // Kiểm tra và in ra log số lượng bài học
                if (lessons != null && !lessons.isEmpty()) {
                    System.out.println("Number of lessons for Chapter ID " + chapter.getId() + ": " + lessons.size());
                    for (Lesson lesson : lessons) {
                        System.out.println("Lesson Title: " + lesson.getTitle());
                    }
                } else {
                    System.out.println("No lessons found for Chapter ID: " + chapter.getId());
                }

                // Gán danh sách bài học vào request
                request.setAttribute("lessons_" + chapter.getId(), lessons);
            }
        }

        request.getRequestDispatcher("LessonView.jsp").forward(request, response);
    }

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
        return "Short description";
    }// </editor-fold>

}
