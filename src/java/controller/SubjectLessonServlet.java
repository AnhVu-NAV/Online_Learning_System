/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author mocun
 */

import dal.LessonDAO;
import model.Lesson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/subjectLesson"})
public class SubjectLessonServlet extends HttpServlet {
    private static final int RECORDS_PER_PAGE = 10;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int page = 1;
        try {
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
                if (page < 1) {
                    page = 1; // Điều chỉnh để trang không bao giờ nhỏ hơn 1
                }
            }
        } catch (NumberFormatException e) {
            page = 1; // Thiết lập giá trị mặc định nếu có lỗi chuyển đổi
        }
        
        LessonDAO lessonDAO = new LessonDAO();
        int offset = (page - 1) * RECORDS_PER_PAGE;
        
        List<Lesson> lessons = lessonDAO.getPaginatedLessons(offset, RECORDS_PER_PAGE);
        System.out.println("Number of lessons: " + lessons.size());
        int noOfRecords = lessonDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);
        
        request.setAttribute("lessons", lessons);
        request.setAttribute("currentPage", page);
        request.setAttribute("noOfPages", noOfPages);
        
        // Điều chỉnh đường dẫn tới SubjectLesson.jsp nếu cần
        request.getRequestDispatcher("SubjectLesson.jsp").forward(request, response);
    }
}

