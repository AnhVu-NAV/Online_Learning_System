/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.LessonDAO;
import model.Lesson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editLesson")
public class EditLessonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy lessonId từ tham số yêu cầu
        String lessonIdParam = request.getParameter("lessonId");
        if (lessonIdParam != null) {
            int lessonId = Integer.parseInt(lessonIdParam);
            LessonDAO lessonDAO = new LessonDAO();

            // Truy xuất thông tin bài học từ cơ sở dữ liệu
            Lesson lesson = lessonDAO.getLessonByID(lessonId); // Lấy dữ liệu từ DAO
            request.setAttribute("lesson", lesson);
            request.getRequestDispatcher("LessonDetails.jsp").forward(request, response);
        }
        // Chuyển tiếp đến trang LessonDetails.jsp
        request.getRequestDispatcher("LessonDetails.jsp").forward(request, response);
    }
}
