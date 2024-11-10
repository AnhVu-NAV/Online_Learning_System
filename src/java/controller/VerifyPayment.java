package controller;

import dal.CourseDAO;
import dal.PersonalCourseDAO;
import dal.PricePackageDAO;
import java.io.IOException;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Calendar;
import model.Course;
import model.PersonalCourse;
import model.PricePackage;
import model.User;

public class VerifyPayment extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy thông tin user từ session
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            // Lấy tham số từ request
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            int pricePackageId = Integer.parseInt(request.getParameter("pricePackageId"));

            // Lấy thông tin giá từ PricePackage
            PricePackageDAO pricePackageDAO = new PricePackageDAO();
            PricePackage pricePackage = pricePackageDAO.getPricePackageById(pricePackageId);
            if (pricePackage == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Price Package.");
                return;
            }
            
            // Lấy thông tin khóa học từ CourseDAO
            CourseDAO courseDAO = new CourseDAO();
            Course course = courseDAO.getCourseByID(courseId);
            if (course == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Course not found.");
                return;
            }
            
            // Lấy duration từ khóa học
            int duration = (int) course.getTotalDuration();
            if (duration <= 0) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid course duration.");
                return;
            }
            
            // Tính toán ngày hết hạn từ duration
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, duration); // Thêm số ngày từ duration
            Date expireDate = calendar.getTime();

            // Xác thực thanh toán (giả lập)
            if (!verifyPayment(courseId, pricePackageId, pricePackage.getSalePrice())) {
                response.sendRedirect("PaymentFailed.jsp");
                return;
            }
            
           // Lưu thông tin vào bảng PersonalCourse
            PersonalCourseDAO personalCourseDAO = new PersonalCourseDAO();
            PersonalCourse personalCourse = new PersonalCourse();
            personalCourse.setCustomerId(user.getId());
            personalCourse.setCourseId(courseId);
            personalCourse.setEnrollDate(new Date());
            personalCourse.setExpireDate(expireDate);
            personalCourse.setProgress(0); // Ban đầu progress là 0
            personalCourse.setStatus(1); // Đặt trạng thái là "Learning"
            personalCourse.setPricePackageId(pricePackageId);
            personalCourse.setPrice(pricePackage.getSalePrice());
            personalCourse.setSaleNoteId(1); // Nếu không có thông tin giảm giá, đặt null

            personalCourseDAO.savePersonalCourse(personalCourse);
            
            // Đặt thông tin khóa học vào session
            request.getSession().setAttribute("courseDetails", course);

            // Chuyển hướng đến trang thành công
            response.sendRedirect("PaymentSuccess.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while verifying payment.");
        }
    }

    /**
     * Giả lập xác thực thanh toán từ cổng thanh toán.
     *
     * @param courseId ID của khóa học.
     * @param pricePackageId ID của gói giá.
     * @param amount Số tiền thanh toán.
     * @return true nếu thanh toán thành công, false nếu thất bại.
     */
    private boolean verifyPayment(int courseId, int pricePackageId, int amount) {
        // Giả lập logic xác thực thanh toán
        return true; // Trả về true để giả lập thanh toán thành công
    }

    @Override
    public String getServletInfo() {
        return "Servlet to verify payment and enroll user to the course.";
    }
}
