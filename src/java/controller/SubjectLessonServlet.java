package controller;

import dal.LessonDAO;
import model.Lesson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Comparator;

@WebServlet(urlPatterns = {"/subjectLesson"})
public class SubjectLessonServlet extends HttpServlet {

    private static final int RECORDS_PER_PAGE = 10;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String searchName = request.getParameter("searchName");
        String searchType = request.getParameter("searchType");
        String searchStatus = request.getParameter("searchStatus");
        String recordsParam = request.getParameter("records");

        int page = 1;
        int recordsPerPage = RECORDS_PER_PAGE;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Lấy số bản ghi từ người dùng
        if (recordsParam != null && !recordsParam.trim().isEmpty()) {
            try {
                recordsPerPage = Integer.parseInt(recordsParam);
                if (recordsPerPage < 1) {
                    recordsPerPage = RECORDS_PER_PAGE;
                }
            } catch (NumberFormatException e) {
                recordsPerPage = RECORDS_PER_PAGE;
            }
        }

        LessonDAO lessonDAO = new LessonDAO();
        int offset = (page - 1) * recordsPerPage;

        // Lấy dữ liệu bài học với Map<Lesson, String> để bao gồm lessonTypeValue
        Map<Lesson, String> lessonsWithTypeValueMap = lessonDAO.getPaginatedLessonsWithTypeValue(offset, recordsPerPage, searchName, searchType, searchStatus);
        List<Map.Entry<Lesson, String>> lessonsWithTypeValue = new ArrayList<>(lessonsWithTypeValueMap.entrySet());

        // Sắp xếp theo order, chapter_id, và id để đảm bảo thứ tự
        lessonsWithTypeValue.sort(Comparator
                .comparing((Map.Entry<Lesson, String> entry) -> entry.getKey().getOrder())
                .thenComparing(entry -> entry.getKey().getChapterId())
                .thenComparing(entry -> entry.getKey().getId()));

        int noOfRecords = lessonDAO.getNoOfRecords(searchName, searchType, searchStatus);
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        // Lấy danh sách các loại bài học cho searchType
        List<String> lessonTypes = lessonDAO.getAllLessonTypes();
        request.setAttribute("lessonTypes", lessonTypes);

        // Đặt List<Map.Entry<Lesson, String>> vào request để sử dụng trong JSP
        request.setAttribute("lessonsWithTypeValue", lessonsWithTypeValue);
        request.setAttribute("currentPage", page);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("records", recordsPerPage);
        request.setAttribute("searchName", searchName);
        request.setAttribute("searchType", searchType);
        request.setAttribute("searchStatus", searchStatus);

        request.getRequestDispatcher("SubjectLesson.jsp").forward(request, response);
    }

    // Thay đổi trạng thái của bài học
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lessonId = request.getParameter("lessonId");
        String newStatus = request.getParameter("newStatus");
        String currentPage = request.getParameter("page");

        if (lessonId != null && newStatus != null) {
            LessonDAO lessonDAO = new LessonDAO();
            lessonDAO.updateLessonStatus(Integer.parseInt(lessonId), Integer.parseInt(newStatus));
        }

        // Kiểm tra nếu currentPage là null, đặt mặc định về trang 1
        if (currentPage == null || currentPage.isEmpty()) {
            currentPage = "1";
        }

        response.sendRedirect("subjectLesson?page=" + currentPage);
    }
}
