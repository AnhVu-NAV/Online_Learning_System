/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.BlogDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Blogs;

/**
 *
 * @author mocun
 */
@WebServlet(name="BlogDetailController", urlPatterns={"/blogDetail"})
public class BlogDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String blogIdParam = request.getParameter("blogId");
        
        if (blogIdParam != null) {
            int blogId = Integer.parseInt(blogIdParam);
            
            // Gọi BlogDAO để lấy chi tiết blog
            BlogDAO blogDAO = new BlogDAO();
            Blogs blog = blogDAO.getBlogById(blogId);
            
            if (blog != null) {
                // Truyền dữ liệu blog vào request
                request.setAttribute("blog", blog);
                request.getRequestDispatcher("/BlogDetail.jsp").forward(request, response);
            } else {
                // Nếu không tìm thấy blog, chuyển hướng tới trang lỗi hoặc thông báo
                response.sendRedirect("error.jsp");
            }
        } else {
            // Nếu không có blogId, chuyển hướng tới trang lỗi hoặc thông báo
            response.sendRedirect("error.jsp");
        }
    }
}
