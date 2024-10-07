package controller;

import dal.BlogDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Blogs;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BlogListServlet", urlPatterns = {"/blogList"})
public class BlogListServlet extends HttpServlet {

    private BlogDAO blogDAO;

    @Override
    public void init() throws ServletException {
        blogDAO = new BlogDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 5;

        // Xử lý lỗi khi chuyển đổi tham số 'page'
        try {
            String pageParam = request.getParameter("page");
            if (pageParam != null) {
                page = Integer.parseInt(pageParam);
            }
        } catch (NumberFormatException e) {
            // Nếu gặp lỗi, giữ nguyên giá trị mặc định của page là 1
            page = 1;
        }

        try {
            // Fetch paginated blogs
            List<Blogs> blogList = blogDAO.getPaginatedBlogs((page - 1) * recordsPerPage, recordsPerPage);
            int noOfRecords = blogDAO.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

            // Log retrieved data
            System.out.println("No of records: " + noOfRecords);
            System.out.println("Blog List: " + blogList);

            // Set attributes for the JSP
            request.setAttribute("blogList", blogList);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);

            // Forward to JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("/BlogList.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            // Log lỗi để debug
            e.printStackTrace();
            // Chuyển hướng đến trang lỗi
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Something went wrong while fetching blog data.");
        }
    }
}
