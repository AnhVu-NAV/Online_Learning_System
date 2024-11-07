package controller;

import dal.LessonDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/updateLesson")
public class UpdateLessonServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Logic để lấy dữ liệu từ form và cập nhật vào cơ sở dữ liệu
        String lessonId = request.getParameter("lessonId");
        String title = request.getParameter("title");
        String type = request.getParameter("type");
        int chapterId = Integer.parseInt(request.getParameter("chapter_id"));
        int order = Integer.parseInt(request.getParameter("order"));
        String videoLink = request.getParameter("video_link");
        String htmlContent = request.getParameter("html_content");

        System.out.println("Updating Lesson ID: " + lessonId);

        LessonDAO lessonDAO = new LessonDAO();

        try {
            // Gọi phương thức cập nhật bài học từ DAO
            lessonDAO.UpdateLesson(Integer.parseInt(lessonId), title, type, chapterId, order, videoLink, htmlContent);
            response.sendRedirect("subjectLesson");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to update lesson. Error: " + e.getMessage()); // Hiển thị thông báo lỗi chính xác
            request.getRequestDispatcher("LessonDetails.jsp").forward(request, response);
        }

    }
}
