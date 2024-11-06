package controller;

import dal.LessonDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/createLesson")
public class CreateLessonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Kiểm tra lỗi nếu có
        String error = request.getParameter("error");
        if (error != null) {
            request.setAttribute("errorMessage", "Failed to create lesson. Please try again.");
        }

        // Forward tới LessonDetails.jsp để hiển thị form
        request.getRequestDispatcher("LessonDetails.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        String type = request.getParameter("type");
        int chapterId = Integer.parseInt(request.getParameter("chapter_id"));
        int order = Integer.parseInt(request.getParameter("order"));
        String videoLink = request.getParameter("video_link");
        String htmlContent = request.getParameter("html_content");

        LessonDAO lessonDAO = new LessonDAO();

        try {
            if ("LearningMaterial".equals(type)) {
                lessonDAO.createLearningMaterialLesson(title, chapterId, order, videoLink, htmlContent);
            } else if ("Quiz".equals(type)) {
                lessonDAO.createQuizLesson(title, chapterId, order);
            }
            response.sendRedirect("subjectLesson"); // Quay lại trang Course Lesson sau khi tạo thành công
        } catch (Exception e) {
            e.printStackTrace();
            // Trả về trang LessonDetails.jsp với thông báo lỗi cụ thể hơn
            request.setAttribute("errorMessage", "Failed to create lesson: " + e.getMessage());
            request.getRequestDispatcher("LessonDetails.jsp").forward(request, response);
        }
    }
}
