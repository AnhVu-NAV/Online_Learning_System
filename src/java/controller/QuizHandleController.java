/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.OptionDAO;
import dal.QuestionDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Option;
import model.Question;

/**
 *
 * @author Admin
 */
public class QuizHandleController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet QuizHandleController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuizHandleController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quizId;
        int questionNumber;

        try {
            // Lấy giá trị từ request hoặc đặt giá trị mặc định cho thử nghiệm
            quizId = Integer.parseInt(request.getParameter("quizId"));
            questionNumber = Integer.parseInt(request.getParameter("questionNumber"));
        } catch (NumberFormatException e) {
            quizId = 16; // Giá trị mặc định để thử nghiệm
            questionNumber = 1;
        }

        // Khởi tạo các DAO
        QuestionDAO questionDAO = new QuestionDAO();
        OptionDAO optionDAO = new OptionDAO();

        // Lấy câu hỏi và các lựa chọn dựa trên quizId và questionNumber
        Question question = questionDAO.getQuestionByQuizAndNumber(quizId, questionNumber);
        List<Option> options = optionDAO.getOptionsByQuestionId(question.getId());
        int totalQuestions = questionDAO.getQuestionCountForQuiz(quizId);

        // Kiểm tra dữ liệu question và options có hợp lệ không
        if (question == null) {
            throw new ServletException("No question found for quiz ID " + quizId + " and question number " + questionNumber);
        }
        if (options == null || options.isEmpty()) {
            throw new ServletException("No options found for question ID " + question.getId());
        }

        // Lấy danh sách các câu hỏi đã trả lời từ session
        HttpSession session = request.getSession();
        Map<Integer, Integer> answeredQuestions = (Map<Integer, Integer>) session.getAttribute("answeredQuestions");

        // Nếu chưa có bản đồ answeredQuestions, tạo mới
        if (answeredQuestions == null) {
            answeredQuestions = new HashMap<>();
        }

        // Đưa các đối tượng vào request attribute để sử dụng trong JSP
        request.setAttribute("question", question);
        request.setAttribute("options", options);
        request.setAttribute("questionNumber", questionNumber);
        request.setAttribute("totalQuestions", totalQuestions);
        request.setAttribute("answeredQuestions", answeredQuestions);

        // Chuyển tiếp tới QuizHandle.jsp
        request.getRequestDispatcher("QuizHandle.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy quizId và questionNumber từ request để xác định câu hỏi hiện tại
        String quizIdParam = request.getParameter("quizId");
        String questionNumberParam = request.getParameter("questionNumber");
        String selectedOptionParam = request.getParameter("selectedOption");

        // Kiểm tra các giá trị đầu vào
        System.out.println("Received quizId: " + quizIdParam);
        System.out.println("Received questionNumber: " + questionNumberParam);
        System.out.println("Received selectedOption: " + selectedOptionParam);

        // Nếu bất kỳ tham số nào là null hoặc rỗng, trả về lỗi
        if (quizIdParam == null || quizIdParam.isEmpty() ||
            questionNumberParam == null || questionNumberParam.isEmpty() ||
            selectedOptionParam == null || selectedOptionParam.isEmpty()) {
            System.out.println("Missing or empty parameters in POST request.");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Chuyển đổi các tham số thành số nguyên
        int quizId = Integer.parseInt(quizIdParam);
        int questionNumber = Integer.parseInt(questionNumberParam);
        int selectedOptionId = Integer.parseInt(selectedOptionParam);

        // Lấy session và bản đồ các câu hỏi đã trả lời
        HttpSession session = request.getSession();
        
        // Lấy dữ liệu từ session với kiểu Map<Integer, Integer>
        Map<Integer, Integer> answeredQuestions = (Map<Integer, Integer>) session.getAttribute("answeredQuestions");

        // Nếu chưa có bản đồ answeredQuestions, tạo mới
        if (answeredQuestions == null) {
            answeredQuestions = new HashMap<>();
        }

        // Cập nhật câu trả lời đã chọn cho câu hỏi hiện tại
        answeredQuestions.put(questionNumber, selectedOptionId);

        // Lưu lại bản đồ đã cập nhật vào session
        session.setAttribute("answeredQuestions", answeredQuestions);

        // Đặt trạng thái phản hồi là OK (200) để kết thúc xử lý mà không cần trả về HTML
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
