package controller;

import dal.LessonDAO;
import model.Lesson;
import model.Chapter;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateLesson")
public class UpdateLessonServlet extends HttpServlet {

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    String lessonId = request.getParameter("lessonId");

    if (lessonId != null) {
        LessonDAO lessonDAO = new LessonDAO();

        try {
            // Lấy thông tin chi tiết của bài học và đặt chapterTitle vào request
            Lesson lesson = lessonDAO.getLessonByID(Integer.parseInt(lessonId), request);
            request.setAttribute("lesson", lesson);

            // Lấy danh sách các chương để hiển thị trong dropdown
            List<Chapter> chapters = lessonDAO.getAllChapters();
            request.setAttribute("chapters", chapters);

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid lesson ID.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to load lesson data. Error: " + e.getMessage());
        }
    }

    // Forward tới JSP để hiển thị form cập nhật
    request.getRequestDispatcher("LessonDetails.jsp").forward(request, response);
}


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String lessonId = request.getParameter("lessonId");
        String title = request.getParameter("title");
        String type = request.getParameter("type");
        int chapterId = Integer.parseInt(request.getParameter("chapter_id"));
        int order;

        try {
            order = Integer.parseInt(request.getParameter("order"));
            if (order <= 0) {
                request.setAttribute("errorMessage", "Order must be greater than 0.");
                request.getRequestDispatcher("LessonDetails.jsp").forward(request, response);
                return;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Order must be a valid number.");
            request.getRequestDispatcher("LessonDetails.jsp").forward(request, response);
            return;
        }

        String videoLink = request.getParameter("video_link");
        String htmlContent = request.getParameter("html_content");

        LessonDAO lessonDAO = new LessonDAO();

        try {
            int lessonTypeId = "1".equals(type) ? 1 : "2".equals(type) ? 2 : -1;

            if (lessonTypeId == -1) {
                request.setAttribute("errorMessage", "Invalid lesson type.");
                request.getRequestDispatcher("LessonDetails.jsp").forward(request, response);
                return;
            }

            // Gọi phương thức cập nhật từ DAO
            lessonDAO.UpdateLesson(Integer.parseInt(lessonId), title, lessonTypeId, chapterId, order, videoLink, htmlContent);
            response.sendRedirect("subjectLesson"); // Quay về trang danh sách sau khi cập nhật thành công
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to update lesson. Error: " + e.getMessage());
            request.getRequestDispatcher("LessonDetails.jsp").forward(request, response);
        }
    }
}
