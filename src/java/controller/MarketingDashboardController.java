/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CourseDAO;
import dal.PersonalCourseDAO;
import dal.SettingDAO;
import dal.SettingTypeDAO;
import dal.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Vector;
import model.Course;
import model.PersonalCourse;
import model.RevenueByCourseCategoryId;
import model.Setting;
import model.SettingType;
import model.User;
import util.CalculateRevenue;
import util.DataConvert;

/**
 *
 * @author 84941
 */
public class MarketingDashboardController extends HttpServlet {

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
        CourseDAO courseDAO = new CourseDAO();
        UserDAO userDAO = new UserDAO();
        SettingDAO settingDAO = new SettingDAO();
        SettingTypeDAO settingTypeDAO = new SettingTypeDAO();
        PersonalCourseDAO personalCourseDAO = new PersonalCourseDAO();
        DataConvert dataConvert = new DataConvert();
        //Set new course data for the first time
        //Get new course in the last 7 day by default
        String newCourseSql = "SELECT * FROM Course WHERE created_date >= CURDATE() - INTERVAL 7 DAY";
        //fillter by the last 7 days by default
        request.setAttribute("newCourseFillterBy", "fillterByTheLast7Days");
        //Display all fields by default
        request.setAttribute("newCourseDisplayID", "true");
        request.setAttribute("newCourseDisplayTitle", "true");
        request.setAttribute("newCourseDisplaySubTitle", "true");
        request.setAttribute("newCourseDisplayExpert", "true");
        request.setAttribute("newCourseDisplayTotalDuration", "true");
        request.setAttribute("newCourseDisplayStatus", "true");
        request.setAttribute("newCourseDisplayDescription", "true");
        request.setAttribute("newCourseDisplayCategory", "true");
        request.setAttribute("newCourseDisplayCreatedDate", "true");
        request.setAttribute("newCourseDisplayUpdatedDate", "true");
        request.setAttribute("newCourseDisplayThumbnail", "true");
        request.setAttribute("newCourseDisplayNumberOfLearner", "true");
        //Get all user
        Vector<User> userVector = userDAO.getUsers("select*from user");
        Vector<Setting> settingVector = settingDAO.getSettings("select*from setting");
        String newCourseSubmit = request.getParameter("newCourseSubmit");
        if (newCourseSubmit != null) {
            //fillter               
            String newCourseFillterBy = request.getParameter("newCourseFillterBy");
            if (newCourseFillterBy.equals("fillterByTheLast7Days")) {
                newCourseSql = "SELECT * FROM Course WHERE created_date >= CURDATE() - INTERVAL 7 DAY";
                request.setAttribute("newCourseFillterBy", "fillterByTheLast7Days");
            }
            if (newCourseFillterBy.equals("fillterByThisMonth")) {
                newCourseSql = "SELECT * FROM Course WHERE YEAR(created_date) = YEAR(CURDATE()) AND MONTH(created_date) = MONTH(CURDATE())";
                request.setAttribute("newCourseFillterBy", "fillterByThisMonth");
            }
            if (newCourseFillterBy.equals("fillterByThisQuarter")) {
                newCourseSql = "SELECT * FROM Course WHERE YEAR(created_date) = YEAR(CURDATE()) AND QUARTER(created_date) = QUARTER(CURDATE())";
                request.setAttribute("newCourseFillterBy", "fillterByThisQuarter");
            }
            if (newCourseFillterBy.equals("fillterByThisYear")) {
                newCourseSql = "SELECT * FROM Course WHERE YEAR(created_date) = YEAR(CURDATE())";
                request.setAttribute("newCourseFillterBy", "fillterByThisYear");
            }
            if (newCourseFillterBy.equals("fillterByDateRange")) {
                String newCourseStartDate = request.getParameter("newCourseStartDate");
                String newCourseEndDate = request.getParameter("newCourseEndDate");
                newCourseSql = "SELECT * FROM Course WHERE created_date BETWEEN '" + newCourseStartDate + "' AND '" + newCourseEndDate + "'";
                request.setAttribute("newCourseFillterBy", "fillterByDateRange");
            }
            if (newCourseFillterBy.equals("allTime")) {
                newCourseSql = "SELECT * FROM Course  WHERE 1=1  ";
                request.setAttribute("newCourseFillterBy", "allTime");
            }
            // fillter by status
            String newCourseFillterByStatus = request.getParameter("newCourseFillterByStatus");
            if (!newCourseFillterByStatus.equals("all")) {
                newCourseSql += " and status =" + newCourseFillterByStatus;
            }
            //fillter by category
            String newCourseFillterByCategory = request.getParameter("newCourseFillterByCategory");
            if (!newCourseFillterByCategory.equals("all")) {
                newCourseSql += " and category_id =" + newCourseFillterByCategory;
            }

            //reset all display fileds
            request.setAttribute("newCourseDisplayID", request.getParameter("newCourseDisplayID"));
            request.setAttribute("newCourseDisplayTitle", request.getParameter("newCourseDisplayTitle"));
            request.setAttribute("newCourseDisplaySubTitle", request.getParameter("newCourseDisplaySubTitle"));
            request.setAttribute("newCourseDisplayExpert", request.getParameter("newCourseDisplayExpert"));
            request.setAttribute("newCourseDisplayTotalDuration", request.getParameter("newCourseDisplayTotalDuration"));
            request.setAttribute("newCourseDisplayStatus", request.getParameter("newCourseDisplayStatus"));
            request.setAttribute("newCourseDisplayDescription", request.getParameter("newCourseDisplayDescription"));
            request.setAttribute("newCourseDisplayCategory", request.getParameter("newCourseDisplayCategory"));
            request.setAttribute("newCourseDisplayCreatedDate", request.getParameter("newCourseDisplayCreatedDate"));
            request.setAttribute("newCourseDisplayUpdatedDate", request.getParameter("newCourseDisplayUpdatedDate"));
            request.setAttribute("newCourseDisplayThumbnail", request.getParameter("newCourseDisplayThumbnail"));
            request.setAttribute("newCourseDisplayNumberOfLearner", request.getParameter("newCourseDisplayNumberOfLearner"));

        }
        //new registrion
        //set new registration for the first time
        //Get new registration in the last 7 day by default
        String newRegistrationSql = "SELECT * FROM PersonalCourse WHERE enroll_date >= CURDATE() - INTERVAL 7 DAY";
        //fillter by the last 7 days by default
        request.setAttribute("newRegistrationFillterBy", "fillterByTheLast7Days");
        //Display all fields by default
        request.setAttribute("newRegistrationDisplayId", "true");
        request.setAttribute("newRegistrationDisplayCustomer", "true");
        request.setAttribute("newRegistrationDisplayCourseId", "true");
        request.setAttribute("newRegistrationDisplayEnrollDate", "true");
        request.setAttribute("newRegistrationDisplayExpireDate", "true");
        request.setAttribute("newRegistrationDisplayStatus", "true");
        request.setAttribute("newRegistrationDisplayProgress", "true");
        request.setAttribute("newRegistrationDisplayPricePackageId", "true");
        String newRegistrationSubmit = request.getParameter("newRegistrationSubmit");
        if (newRegistrationSubmit != null) {
            String newRegistrationFillterBy = request.getParameter("newRegistrationFillterBy");
            if (newRegistrationFillterBy.equals("fillterByTheLast7Days")) {
                newRegistrationSql = "SELECT * FROM PersonalCourse WHERE enroll_date >= CURDATE() - INTERVAL 7 DAY";
                request.setAttribute("newRegistrationFillterBy", "fillterByTheLast7Days");
            }
            if (newRegistrationFillterBy.equals("fillterByThisMonth")) {
                newRegistrationSql = "SELECT * FROM PersonalCourse WHERE YEAR(enroll_date) = YEAR(CURDATE()) AND MONTH(enroll_date) = MONTH(CURDATE())";
                request.setAttribute("newRegistrationFillterBy", "fillterByThisMonth");
            }
            if (newRegistrationFillterBy.equals("fillterByThisQuarter")) {
                newRegistrationSql = "SELECT * FROM PersonalCourse WHERE YEAR(enroll_date) = YEAR(CURDATE()) AND QUARTER(enroll_date) = QUARTER(CURDATE())";
                request.setAttribute("newRegistrationFillterBy", "fillterByThisQuarter");
            }
            if (newRegistrationFillterBy.equals("fillterByThisYear")) {
                newRegistrationSql = "SELECT * FROM PersonalCourse WHERE YEAR(enroll_date) = YEAR(CURDATE())";
                request.setAttribute("newRegistrationFillterBy", "fillterByThisYear");
            }
            if (newRegistrationFillterBy.equals("fillterByDateRange")) {
                String newCourseStartDate = request.getParameter("newRegistrationStartDate");
                String newCourseEndDate = request.getParameter("newRegistrationEndDate");
                newRegistrationSql = "SELECT * FROM PersonalCourse WHERE enroll_date BETWEEN '" + newCourseStartDate + "' AND '" + newCourseEndDate + "'";
                request.setAttribute("newRegistrationFillterBy", "fillterByDateRange");
            }
            if (newRegistrationFillterBy.equals("allTime")) {
                newRegistrationSql = "SELECT * FROM PersonalCourse WHERE 1=1 ";
                request.setAttribute("newRegistrationFillterBy", "allTime");
            }
            // fillter by status
            String newRegistrationsFillterByStatus = request.getParameter("newRegistrationsFillterByStatus");
            if (!newRegistrationsFillterByStatus.equals("all")) {
                newRegistrationSql += " and status =" + newRegistrationsFillterByStatus;
            }
            //reset all display fileds
            request.setAttribute("newRegistrationDisplayId", request.getParameter("newRegistrationDisplayId"));
            request.setAttribute("newRegistrationDisplayCustomer", request.getParameter("newRegistrationDisplayCustomer"));
            request.setAttribute("newRegistrationDisplayCourseId", request.getParameter("newRegistrationDisplayCourseId"));
            request.setAttribute("newRegistrationDisplayEnrollDate", request.getParameter("newRegistrationDisplayEnrollDate"));
            request.setAttribute("newRegistrationDisplayExpireDate", request.getParameter("newRegistrationDisplayExpireDate"));
            request.setAttribute("newRegistrationDisplayStatus", request.getParameter("newRegistrationDisplayStatus"));
            request.setAttribute("newRegistrationDisplayProgress", request.getParameter("newRegistrationDisplayProgress"));
            request.setAttribute("newRegistrationDisplayPricePackageId", request.getParameter("newRegistrationDisplayPricePackageId"));
        }

        //new customer
        //set new customer for the first time
        //Get new customer in the last 7 day by default
        String newCustomerSql = "SELECT * FROM user WHERE created_date >= CURDATE() - INTERVAL 7 DAY";
        //fillter by the last 7 days by default
        request.setAttribute("newCustomerFillterBy", "fillterByTheLast7Days");
        //Display all fields by default
        request.setAttribute("newCustomerDisplayId", "true");
        request.setAttribute("newCustomerDisplayEmail", "true");
        request.setAttribute("newCustomerDisplayCreateDate", "true");
        request.setAttribute("newCustomerDisplayStatus", "true");
        request.setAttribute("newCustomerDisplayFirstName", "true");
        request.setAttribute("newCustomerDisplayLastName", "true");
        request.setAttribute("newCustomerDisplayDob", "true");
        request.setAttribute("newCustomerDisplayGender", "true");
        request.setAttribute("newCustomerDisplayImageUrl", "true");
        request.setAttribute("newCustomerDisplayPreferContact", "true");
        String newCustomerSubmit = request.getParameter("newCustomerSubmit");
        if (newCustomerSubmit != null) {
            String newCustomerFillterBy = request.getParameter("newCustomerFillterBy");
            if (newCustomerFillterBy.equals("fillterByTheLast7Days")) {
                newCustomerSql = "SELECT * FROM user WHERE created_date >= CURDATE() - INTERVAL 7 DAY ";
                request.setAttribute("newCustomerFillterBy", "fillterByTheLast7Days");
            }
            if (newCustomerFillterBy.equals("fillterByThisMonth")) {
                newCustomerSql = "SELECT * FROM user WHERE YEAR(created_date) = YEAR(CURDATE()) AND MONTH(created_date) = MONTH(CURDATE()) ";
                request.setAttribute("newCustomerFillterBy", "fillterByThisMonth");
            }
            if (newCustomerFillterBy.equals("fillterByThisQuarter")) {
                newCustomerSql = "SELECT * FROM user WHERE YEAR(created_date) = YEAR(CURDATE()) AND QUARTER(created_date) = QUARTER(CURDATE()) ";
                request.setAttribute("newCustomerFillterBy", "fillterByThisQuarter");
            }
            if (newCustomerFillterBy.equals("fillterByThisYear")) {
                newCustomerSql = "SELECT * FROM user WHERE YEAR(created_date) = YEAR(CURDATE()) ";
                request.setAttribute("newCustomerFillterBy", "fillterByThisYear");
            }
            if (newCustomerFillterBy.equals("fillterByDateRange")) {
                String newCustomerStartDate = request.getParameter("newCustomerStartDate");
                String newCustomerEndDate = request.getParameter("newCustomerEndDate");
                newCustomerSql = "SELECT * FROM user WHERE created_date BETWEEN '" + newCustomerStartDate + "' AND '" + newCustomerEndDate + "'";
                request.setAttribute("newCustomerFillterBy", "fillterByDateRange");
            }
            if (newCustomerFillterBy.equals("allTime")) {
                newCustomerSql = "SELECT * FROM user WHERE 1=1 ";
                request.setAttribute("newCustomerFillterBy", "allTime");
            }
            // fillter by registration
            String newCustomerFillterByRegistration = request.getParameter("newCustomerFillterByRegistration");
            if (!newCustomerFillterByRegistration.equals("all")) {
                if (newCustomerFillterByRegistration.equals("registered")) {
                    newCustomerSql += " and id IN (SELECT customer_id FROM PersonalCourse) ";
                }
                if (newCustomerFillterByRegistration.equals("notRegistered")) {
                    newCustomerSql += " and id  NOT IN (SELECT customer_id FROM PersonalCourse) ";
                }
            }
            //reset all display fileds
            request.setAttribute("newCustomerDisplayId", request.getParameter("newCustomerDisplayId"));
            request.setAttribute("newCustomerDisplayEmail", request.getParameter("newCustomerDisplayEmail"));
            request.setAttribute("newCustomerDisplayCreateDate", request.getParameter("newCustomerDisplayCreateDate"));
            request.setAttribute("newCustomerDisplayStatus", request.getParameter("newCustomerDisplayStatus"));
            request.setAttribute("newCustomerDisplayFirstName", request.getParameter("newCustomerDisplayFirstName"));
            request.setAttribute("newCustomerDisplayLastName", request.getParameter("newCustomerDisplayLastName"));
            request.setAttribute("newCustomerDisplayDob", request.getParameter("newCustomerDisplayDob"));
            request.setAttribute("newCustomerDisplayGender", request.getParameter("newCustomerDisplayGender"));
            request.setAttribute("newCustomerDisplayImageUrl", request.getParameter("newCustomerDisplayImageUrl"));
            request.setAttribute("newCustomerDisplayPreferContact", request.getParameter("newCustomerDisplayPreferContact"));
        }
        // revenue
        //set revenue for the first time
        //Get revenue in the last 7 day by default
        String revenueSql = "SELECT * FROM personalcourse WHERE (enroll_date >= CURDATE() - INTERVAL 7 DAY) and status=1";
        //fillter by the last 7 days by default
        request.setAttribute("revenueFillterBy", "fillterByTheLast7Days");
        String revenueSubmit = request.getParameter("revenueSubmit");
        if(revenueSubmit!=null){
            String revenueFillterBy = request.getParameter("revenueFillterBy");
            if (revenueFillterBy.equals("fillterByTheLast7Days")) {
                revenueSql = "SELECT * FROM personalcourse WHERE (enroll_date >= CURDATE() - INTERVAL 7 DAY) and status=1";
                request.setAttribute("revenueFillterBy", "fillterByTheLast7Days");
            }
            if (revenueFillterBy.equals("fillterByThisMonth")) {
                revenueSql = "SELECT * FROM personalcourse WHERE (YEAR(enroll_date) = YEAR(CURDATE()) AND MONTH(enroll_date) = MONTH(CURDATE())) and status=1";
                request.setAttribute("revenueFillterBy", "fillterByThisMonth");
            }
            if (revenueFillterBy.equals("fillterByThisQuarter")) {
                revenueSql = "SELECT * FROM personalcourse WHERE (YEAR(enroll_date) = YEAR(CURDATE()) AND QUARTER(enroll_date) = QUARTER(CURDATE())) and status=1";
                request.setAttribute("revenueFillterBy", "fillterByTheLast7Days");
            }
            if (revenueFillterBy.equals("fillterByThisYear")) {
                revenueSql = "SELECT * FROM personalcourse WHERE (YEAR(enroll_date) = YEAR(CURDATE())) and status=1";
                request.setAttribute("revenueFillterBy", "fillterByThisYear");
            }
            if (revenueFillterBy.equals("fillterByDateRange")) {
                String revenueStartDate = request.getParameter("revenueStartDate");
                String revenueEndDate = request.getParameter("revenueEndDate");
                revenueSql = "SELECT * FROM personalcourse WHERE (enroll_date BETWEEN '" + revenueStartDate + "' AND '" + revenueEndDate + "') and status=1";
                request.setAttribute("revenueFillterBy", "fillterByDateRange");
            }
            if (revenueFillterBy.equals("allTime")) {
                revenueSql = "SELECT * FROM personalcourse WHERE status=1";
                request.setAttribute("revenueFillterBy", "allTime");
            }
            
        }
        Vector<PersonalCourse> personalCourseVector = personalCourseDAO.getPersonalCourses(revenueSql);
        Map<Integer, Integer> revenueByCourseCategoryIdMap = CalculateRevenue.calculateRevenueByCourseCategoryId(personalCourseVector);
        Vector<RevenueByCourseCategoryId> revenueByCourseCategoryIdVector = new Vector<>();
        for(int key:revenueByCourseCategoryIdMap.keySet()){
            revenueByCourseCategoryIdVector.add(new RevenueByCourseCategoryId(key, revenueByCourseCategoryIdMap.get(key)));
        }
        
        

        Vector<User> newCustomerVector = userDAO.getUsers(newCustomerSql);
        Vector<PersonalCourse> newRegistrationVector = personalCourseDAO.getPersonalCourses(newRegistrationSql);
        Vector<Course> newCourseVector = courseDAO.getCourses(newCourseSql);
        SettingType courseCategory = settingTypeDAO.getSettingTypeByName("Course Category");
        Vector<Setting> categoryVector = settingDAO.getSettings("select*from setting where setting_type_id=" + courseCategory.getId());
        //set data for view
        request.setAttribute("newCourseVector", newCourseVector);
        request.setAttribute("newCustomerVector", newCustomerVector);
        request.setAttribute("newRegistrationVector", newRegistrationVector);
        request.setAttribute("userVector", userVector);
        request.setAttribute("settingVector", settingVector);
        request.setAttribute("categoryVector", categoryVector);
        request.setAttribute("revenueByCourseCategoryIdVector", revenueByCourseCategoryIdVector);

        // select view
        RequestDispatcher dispath = request.getRequestDispatcher("jsp/ViewMarketingDashboard.jsp");
        //run
        dispath.forward(request, response);

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
