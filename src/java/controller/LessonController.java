/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.LessonDAO;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import model.TextContent;
import model.VideoContent;

/**
 *
 * @author Admin
 */
public class LessonController extends HttpServlet {

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
            out.println("<title>Servlet LessonVideoController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LessonVideoController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String lessonIdParam = request.getParameter("lesson_id");
        if (lessonIdParam != null && !lessonIdParam.isEmpty()) {

            int lessonId = Integer.parseInt(lessonIdParam);
            LessonDAO lDAO = new LessonDAO();
            if (lessonId == 3 || lessonId == 6 || lessonId == 9 || lessonId == 12 || lessonId == 15) {
                String textContent = readHtmlFile(lessonIdParam, request);

                if (textContent != null) {

                    
                    request.setAttribute("textHtmlContent", textContent);
                } else {
                    request.setAttribute("textHtmlContent", "No HTML content available.");
                }
                request.getRequestDispatcher("ChapterDisplayController").forward(request, response);

            } else {

                VideoContent videocontent = lDAO.getVideoContentById(lessonId);
                
                String descriptionContent = readDescriptionFromFile(lessonIdParam, request);
                videocontent.setDescription(descriptionContent);

                String videoSummary = videocontent.getVideoSummary();
                videocontent.setVideoSummary(videoSummary);

                request.setAttribute("videocontent", videocontent);

                request.getRequestDispatcher("ChapterDisplayController").forward(request, response);
            }
        }
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

    private String readDescriptionFromFile(String lessonId, HttpServletRequest request) throws IOException {
        // Sử dụng ServletContext để lấy đường dẫn tuyệt đối đến thư mục chứa các file .txt
        ServletContext context = request.getServletContext();
        String filePath = context.getRealPath("/video_descriptions/description_video" + lessonId + ".txt");

        File file = new File(filePath);
        StringBuilder description = new StringBuilder();
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    description.append(line).append("\n");
                }
            }
        } else {
            description.append("No description available.");
        }

        return description.toString();
    }

    private String readHtmlFile(String lessonId, HttpServletRequest request) throws IOException {
        // Sử dụng ServletContext để lấy đường dẫn tuyệt đối đến thư mục chứa các file .html
        ServletContext context = request.getServletContext();
        String filePath = context.getRealPath("/text_descriptions/lesson_" + lessonId + "_text.html");  // Đảm bảo đường dẫn đúng

        File file = new File(filePath);
        StringBuilder htmlContent = new StringBuilder();

        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    htmlContent.append(line).append("\n");
                }
            }
        } else {
            htmlContent.append("No HTML content available.");
        }

        return htmlContent.toString();
    }

}
