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

@WebServlet(urlPatterns = {"/subjectLesson"})
public class SubjectLessonServlet extends HttpServlet {

    private static final int RECORDS_PER_PAGE = 10;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String searchName = request.getParameter("searchName");
        String searchType = request.getParameter("searchType");
        String searchStatus = request.getParameter("searchStatus"); // Lấy searchStatus từ request
        String recordsParam = request.getParameter("records");

        int page = 1;
        int recordsPerPage = 10;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        //get no of record from user
        // Kiểm tra giá trị recordsParam để đảm bảo nó là số hợp lệ
        if (recordsParam != null && !recordsParam.trim().isEmpty()) {
            try {
                recordsPerPage = Integer.parseInt(recordsParam);
                if (recordsPerPage < 1) { // Nếu số bản ghi nhỏ hơn 1, dùng giá trị mặc định
                    recordsPerPage = 10;
                }
            } catch (NumberFormatException e) {
                recordsPerPage = 10; // Nếu không thể chuyển thành số, dùng giá trị mặc định
            }
        }

        LessonDAO lessonDAO = new LessonDAO();
        int offset = (page - 1) * recordsPerPage;

        // Lấy dữ liệu bài học dựa trên tìm kiếm, bao gồm cả searchStatus
        List<Lesson> lessons = lessonDAO.getPaginatedLessons(offset, recordsPerPage, searchName, searchType, searchStatus);
        int noOfRecords = lessonDAO.getNoOfRecords(searchName, searchType, searchStatus); // Đếm bản ghi có searchStatus
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        // Lấy danh sách các loại bài học cho searchType
        List<String> lessonTypes = lessonDAO.getAllLessonTypes();
        request.setAttribute("lessonTypes", lessonTypes);

        request.setAttribute("lessons", lessons);
        request.setAttribute("currentPage", page);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("records", recordsPerPage);
        request.setAttribute("searchName", searchName);
        request.setAttribute("searchType", searchType);
        request.setAttribute("searchStatus", searchStatus); // Đặt thuộc tính searchStatus cho JSP

        request.getRequestDispatcher("SubjectLesson.jsp").forward(request, response);
    }

    // Thay đổi trạng thái của bài học
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lessonId = request.getParameter("lessonId");
        String newStatus = request.getParameter("newStatus");

        if (lessonId != null && newStatus != null) {
            LessonDAO lessonDAO = new LessonDAO();
            lessonDAO.updateLessonStatus(Integer.parseInt(lessonId), Integer.parseInt(newStatus));
        }

        response.sendRedirect("subjectLesson");
    }
}
