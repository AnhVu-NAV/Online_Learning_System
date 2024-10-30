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

    String searchName = request.getParameter("searchName");
    String searchType = request.getParameter("searchType");

    int page = 1;
    if (request.getParameter("page") != null) {
        page = Integer.parseInt(request.getParameter("page"));
    }

    LessonDAO lessonDAO = new LessonDAO();
    int offset = (page - 1) * RECORDS_PER_PAGE;

    // Lấy dữ liệu bài học dựa trên tìm kiếm
    List<Lesson> lessons = lessonDAO.getPaginatedLessons(offset, RECORDS_PER_PAGE, searchName, searchType);
    int noOfRecords = lessonDAO.getNoOfRecords(searchName, searchType);
    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);

    // Lấy danh sách các loại bài học cho searchType
    List<String> lessonTypes = lessonDAO.getAllLessonTypes();
    request.setAttribute("lessonTypes", lessonTypes);

    request.setAttribute("lessons", lessons);
    request.setAttribute("currentPage", page);
    request.setAttribute("noOfPages", noOfPages);
    request.setAttribute("searchName", searchName);
    request.setAttribute("searchType", searchType);

    request.getRequestDispatcher("SubjectLesson.jsp").forward(request, response);
}

//change status
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String lessonId = request.getParameter("lessonId");
    String newStatus = request.getParameter("newStatus");

    if (lessonId != null && newStatus != null) {
        LessonDAO lessonDAO = new LessonDAO();
        // Chuyển đổi newStatus thành số nguyên trước khi gọi phương thức updateLessonStatus
        lessonDAO.updateLessonStatus(Integer.parseInt(lessonId), Integer.parseInt(newStatus));
    }

    // Redirect lại trang SubjectLesson để hiển thị danh sách sau khi cập nhật
    response.sendRedirect("subjectLesson");
}


}

