package controller;

import dal.LessonDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Chapter;

@WebServlet("/createLesson")
public class CreateLessonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String error = request.getParameter("error");
        if (error != null) {
            request.setAttribute("errorMessage", "Failed to create lesson. Please try again.");
        }

        LessonDAO lessonDAO = new LessonDAO();
        List<Chapter> chapters = lessonDAO.getAllChapters(); // Get chapters from DAO
        request.setAttribute("chapters", chapters); // Set chapters as a request attribute

        // Forward to LessonDetails.jsp to display the form
        request.getRequestDispatcher("LessonDetails.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        String type = request.getParameter("type"); // Giá trị này sẽ là "1" hoặc "2"
        int chapterId = Integer.parseInt(request.getParameter("chapter_id"));
        int order;

        try {
            order = Integer.parseInt(request.getParameter("order"));
            if (order <= 0) {
                request.setAttribute("errorMessage", "Order must be greater than 0.");
                request.getRequestDispatcher("LessonDetails.jsp").forward(request, response);
                return;
            }

            LessonDAO lessonDAO = new LessonDAO();

            // So sánh `type` với `id` của Lesson Type để xác định loại bài học
            if ("1".equals(type)) { // `1` đại diện cho LearningMaterial
                lessonDAO.createLearningMaterialLesson(title, chapterId, order, request.getParameter("video_link"), request.getParameter("html_content"));
            } else if ("2".equals(type)) { // `2` đại diện cho Quiz
                lessonDAO.createQuizLesson(title, chapterId, order);
            } else {
                request.setAttribute("errorMessage", "Invalid lesson type.");
                request.getRequestDispatcher("LessonDetails.jsp").forward(request, response);
                return;
            }

            response.sendRedirect("subjectLesson");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Order or Chapter ID must be a valid number.");
            request.getRequestDispatcher("LessonDetails.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to create lesson: " + e.getMessage());
            request.getRequestDispatcher("LessonDetails.jsp").forward(request, response);
        }
    }

}
