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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        int courseId = 17;  // Giả định courseId đã được cung cấp

        // Lấy danh sách chapter từ cơ sở dữ liệu
        ChapterDAO cDAO = new ChapterDAO();
        List<Chapter> chapters = cDAO.getChaptersByCourseId(courseId);

        // Tạo một Map để lưu trữ danh sách bài học theo chapterId
        Map<Integer, List<Lesson>> lessonsMap = new HashMap<>();

        LessonDAO lDAO = new LessonDAO();
        if (chapters != null && !chapters.isEmpty()) {
            for (Chapter chapter : chapters) {
                int chapterId = chapter.getId();  
                List<Lesson> lessons = lDAO.getLessonsByChapterId(chapterId);
                lessonsMap.put(chapterId, lessons);
            }
        }

        // Gán cả chapters và lessonsMap vào request
        request.setAttribute("chapters", chapters);
        request.setAttribute("lessonsMap", lessonsMap);

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
