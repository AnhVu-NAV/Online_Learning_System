/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CourseDAO;
import dal.PersonalCourseDAO;
import dal.PricePackageDAO;
import dal.SaleNoteDAO;
import dal.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import util.DataConvert;
import model.*;

/**
 *
 * @author 84941
 */
public class SaleRegistrationDashboardController extends HttpServlet {

    private String imageUploadDirectory;

    @Override
    public void init() throws ServletException {
        // Lấy đường dẫn tuyệt đối cho thư mục /web/img
        imageUploadDirectory = getServletContext().getRealPath("/img");
    }

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
        PersonalCourseDAO personalCourseDAO = new PersonalCourseDAO();
        UserDAO userDAO = new UserDAO();
        CourseDAO courseDAO = new CourseDAO();
        PricePackageDAO pricePackageDAO = new PricePackageDAO();
        SaleNoteDAO saleNoteDAO = new SaleNoteDAO();
        DataConvert dataConvert = new DataConvert();
        String service = request.getParameter("service");
        if (service == null) {
            service = "viewAllRegistration";
        }
        if (service.equals("viewAllRegistration")) {
            String fillterSubmit = request.getParameter("fillterSubmit");
            Vector<User> userVector = null;
            Vector<Course> courseVector = null;
            Vector<PricePackage> pricePackageVector = null;
            Vector<PersonalCourse> personalCourseVector = null;
            Vector<User> customerVector = null;
            String sql = "select * from personalcourse";
            String checked = "sortByPersonalCourseId"; //by default
            //Display all fields by default
            request.setAttribute("displayPersonalCourseId", "true");
            request.setAttribute("displayCustomerId", "true");
            request.setAttribute("displayCourseId", "true");
            request.setAttribute("displayPrice", "true");
            request.setAttribute("displayStatus", "true");
            request.setAttribute("displayEnrollDate", "true");
            request.setAttribute("displayExpireDate", "true");
            request.setAttribute("displayPricePackageId", "true");
            if (fillterSubmit != null) {
                //get all filter
                String searchByPersonalCourseId = request.getParameter("searchByPersonalCourseId");
                String searchByCustomerId = request.getParameter("searchByCustomerId");
                String searchByCourseId = request.getParameter("searchByCourseId");
                String searchByPrice = request.getParameter("searchByPrice");
                String filterByStatus = request.getParameter("filterByStatus");
                String searchByEnrollDate = request.getParameter("searchByEnrollDate");
                String searchByExpireDate = request.getParameter("searchByExpireDate");
                String searchByPricePackageId = request.getParameter("searchByPricePackageId");
                String priceRangeFrom = request.getParameter("priceRangeFrom");
                String priceRangeTo = request.getParameter("priceRangeTo");
                String enrollDateFrom = request.getParameter("enrollDateFrom");
                String enrollDateTo = request.getParameter("enrollDateTo");
                String expireDateFrom = request.getParameter("expireDateFrom");
                String expireDateTo = request.getParameter("expireDateTo");

                //get sort
                String sortBy = request.getParameter("sortBy");
                //reset all display fields
                request.setAttribute("displayPersonalCourseId", request.getParameter("displayPersonalCourseId"));
                request.setAttribute("displayCustomerId", request.getParameter("displayCustomerId"));
                request.setAttribute("displayCourseId", request.getParameter("displayCourseId"));
                request.setAttribute("displayPrice", request.getParameter("displayPrice"));
                request.setAttribute("displayStatus", request.getParameter("displayStatus"));
                request.setAttribute("displayEnrollDate", request.getParameter("displayEnrollDate"));
                request.setAttribute("displayExpireDate", request.getParameter("displayExpireDate"));
                request.setAttribute("displayPricePackageId", request.getParameter("displayPricePackageId"));
                sql += " where id like '%" + searchByPersonalCourseId + "%' and customer_id like '%" + searchByCustomerId + "%' and course_id like '%" + searchByCourseId + "%' and price like '%" + searchByPrice + "%' and enroll_date like '%" + searchByEnrollDate + "%' and expire_date like '%" + searchByExpireDate + "%' and price_package_id like '%" + searchByPricePackageId + "%' ";
                if (!filterByStatus.equals("all")) {
                    sql += " and status = " + filterByStatus;
                }
                if (!priceRangeFrom.isEmpty() && !priceRangeTo.isEmpty() && searchByPrice.isEmpty()) {
                    sql += "and price between " + priceRangeFrom + " and " + priceRangeTo;
                }
                if (!enrollDateFrom.isEmpty() && !enrollDateTo.isEmpty() && searchByEnrollDate.isEmpty()) {
                    sql += "and enroll_date between '" + enrollDateFrom + "' and '" + enrollDateTo + "'";
                }
                if (!expireDateFrom.isEmpty() && !expireDateTo.isEmpty() && searchByExpireDate.isEmpty()) {
                    sql += "and expire_date between '" + expireDateFrom + "' and '" + expireDateTo + "'";
                }
                if (sortBy != null) {
                    switch (sortBy) {
                        case "sortByPersonalCourseId":
                            sql += " order by id asc ";
                            break;
                        case "sortByCustomerId":
                            sql += " order by customer_id asc ";
                            checked = "sortByCustomerId";
                            break;
                        case "sortByCourseId":
                            sql += " order by course_id asc ";
                            checked = "sortByCourseId";
                            break;
                        case "sortByPrice":
                            sql += " order by price asc ";
                            checked = "sortByPrice";
                            break;
                        case "sortByStatus":
                            sql += " order by status asc ";
                            checked = "sortByStatus";
                            break;
                        case "sortByEnrollDate":
                            sql += " order by enroll_date asc ";
                            checked = "sortByEnrollDate";
                            break;
                        case "sortByExpireDate":
                            sql += " order by expire_date asc ";
                            checked = "sortByExpireDate";
                            break;

                    }

                }

            }
            //paging
            int nrpp = 10;
            personalCourseVector = personalCourseDAO.getPersonalCourses(sql);
            int totalPage = (personalCourseVector.size() + nrpp - 1) / nrpp;
            String indexRaw = request.getParameter("index");
            int index = 1;
            if (indexRaw != null) {
                index = Integer.parseInt(indexRaw);
            }
            sql += " limit " + (index - 1) * nrpp + "," + nrpp;
            personalCourseVector = personalCourseDAO.getPersonalCourses(sql);

            userVector = userDAO.getUsers("select * from user");
            courseVector = courseDAO.getCourses("select * from course");
            pricePackageVector = pricePackageDAO.getPricePackages("select * from pricepackage");
            customerVector = userDAO.getUsers("select * from user where role_id = 2");
            //set data for view 
            request.setAttribute("userVector", userVector);
            request.setAttribute("courseVector", courseVector);
            request.setAttribute("pricePackageVecotr", pricePackageVector);
            request.setAttribute("personalCourseVector", personalCourseVector);
            request.setAttribute("customerVector", customerVector);
            request.setAttribute("checked", checked);
            request.setAttribute("totalPage", totalPage);

            // select view
            RequestDispatcher dispath = request.getRequestDispatcher("jsp/ViewRegistrationList.jsp");
            //run
            dispath.forward(request, response);

        }
        if (service.equals("recommendCourse")) {
            // Tạo đường dẫn thư mục lưu ảnh trong project
            String uploadPath = imageUploadDirectory;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            // Danh sách lưu tên ảnh
            List<String> imageFileNames = new ArrayList<>();

            // Duyệt qua tất cả các phần của file upload
            for (Part part : request.getParts()) {
                if ("courseImages".equals(part.getName()) && part.getSize() > 0) {
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    imageFileNames.add(fileName);
                    // Lưu ảnh vào thư mục uploadPath
                    part.write(uploadPath + File.separator + fileName);
                }
            }

        }
        if (service.equals("viewRegistrationDetails")) {
            String idRaw = request.getParameter("id");
            PersonalCourse personalCourse = personalCourseDAO.getPersonalCourseById(Integer.parseInt(idRaw));
            Vector<User> userVector = null;
            Vector<Course> courseVector = null;
            Vector<PricePackage> pricePackageVector = null;
            userVector = userDAO.getUsers("select * from user");
            courseVector = courseDAO.getCourses("select * from course");
            pricePackageVector = pricePackageDAO.getPricePackages("select * from pricepackage");
            //set data for views
            request.setAttribute("userVector", userVector);
            request.setAttribute("courseVector", courseVector);
            request.setAttribute("pricePackageVector", pricePackageVector);
            request.setAttribute("personalCourse", personalCourse);
            // select view
            RequestDispatcher dispath = request.getRequestDispatcher("jsp/ViewRegistrationDetail.jsp");
            //run
            dispath.forward(request, response);
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
