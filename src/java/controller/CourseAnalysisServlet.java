/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CourseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AnhVuNAV
 */
public class CourseAnalysisServlet extends HttpServlet {

    private static final String OPENAI_API_KEY = "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";

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
            out.println("<title>Servlet CourseAnalysisServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CourseAnalysisServlet at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String message;

        if ("initial".equals(action)) {
            List<String> courseNames = fetchCourseNamesFromDatabase();
            message = "Assuming you are an expert in training and teaching, analyze the courses below and check which ones are about to become outdated and which ones need to be updated with new knowledge: " + String.join(", ", courseNames);
        } else if ("followUp".equals(action)) {
            message = request.getParameter("message");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
            return;
        }

        String aiResponse = queryOpenAI(message);
        response.setContentType("text/plain");
        response.getWriter().write(aiResponse);
    }

    private List<String> fetchCourseNamesFromDatabase() {
        // Fetch course names from your database
        List<String> courseNames = new ArrayList<>();
        // Query database and populate courseNames...
        CourseDAO course = new CourseDAO();
        try {
            courseNames = course.getAllNameCourseByCategory();
        } catch (SQLException ex) {
            Logger.getLogger(CourseAnalysisServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courseNames;
    }

    private String queryOpenAI(String userMessage) throws IOException {
        URL url = new URL("https://api.openai.com/v1/chat/completions");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + OPENAI_API_KEY);
        connection.setDoOutput(true);

        String jsonInput = "{ \"model\": \"gpt-4\", \"messages\": [{ \"role\": \"user\", \"content\": \"" + userMessage + "\" }] }";
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInput.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }
        // Extract response content
        // Implement JSON parsing to extract AI's message content
        return response.toString();
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
