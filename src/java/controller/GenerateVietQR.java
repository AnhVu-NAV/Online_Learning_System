/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author AnhVuNAV
 */
public class GenerateVietQR extends HttpServlet {

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
            out.println("<title>Servlet GenerateVietQR</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GenerateVietQR at " + request.getContextPath() + "</h1>");
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
        try {
            // Các tham số API
            String bankCode = "970422"; // Mã ngân hàng
            String accountNumber = "9993104999999"; // Số tài khoản
            String accountName = "3YVaX8i"; // Mã hóa tên tài khoản
            int amount = Integer.parseInt(request.getParameter("amount")) * 1000; // Số tiền
            String description = request.getParameter("description"); // Nội dung

            // Tạo URL VietQR
            String qrUrl = String.format("https://api.vietqr.io/image/%s-%s-%s.jpg?amount=%d&addInfo=%s",
                    bankCode, accountNumber, accountName, amount, description.replace(" ", "%20"));
            request.getSession().setAttribute("qrUrl", qrUrl); // Lưu vào session để JSP hiển thị

//            // Gửi yêu cầu GET tới API VietQR
//            URL url = new URL(qrUrl);
//            System.out.println(qrUrl);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            // Kiểm tra phản hồi từ API
//            int responseCode = connection.getResponseCode();
//            if (responseCode != HttpURLConnection.HTTP_OK) {
//                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Failed to fetch QR code");
//                return;
//            }
//
//            // Đọc dữ liệu hình ảnh từ phản hồi API
//            InputStream inputStream = connection.getInputStream();
//            response.setContentType("image/jpeg");
//
//            // Gửi hình ảnh đến client
//            OutputStream outputStream = response.getOutputStream();
//            byte[] buffer = new byte[1024];
//            int bytesRead;
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                outputStream.write(buffer, 0, bytesRead);
//            }
//
//            inputStream.close();
//            outputStream.flush();
//            request.setAttribute("qrUrl", qrUrl);
            response.sendRedirect("PaymentQR.jsp"); // Chuyển hướng về trang thanh toán
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while generating the QR Code.");
        }
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
        processRequest(request, response);
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
