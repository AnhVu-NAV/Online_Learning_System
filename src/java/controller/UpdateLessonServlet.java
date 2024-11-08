package controller;

import dal.LessonDAO;
import java.io.IOException;
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
            // Xác định `lessonTypeId` dựa trên loại bài học
            int lessonTypeId;
            if ("1".equals(type)) {  // ID 1 ứng với LearningMaterial
                lessonTypeId = 1;
            } else if ("2".equals(type)) { // ID 2 ứng với Quiz
                lessonTypeId = 2;
            } else {
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
