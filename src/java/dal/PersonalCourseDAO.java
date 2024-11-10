/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.PersonalCourse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import util.DataConvert;

/**
 *
 * @author 84941
 */
public class PersonalCourseDAO extends DBContext {

    //VuLH
    DataConvert dc = new DataConvert();

    public Vector<PersonalCourse> getPersonalCourses(String sql) {
        Vector<PersonalCourse> vector = new Vector<>();
        ResultSet rs = null;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int customerId = rs.getInt("customer_id");
                int courseId = rs.getInt("course_id");
                Date enrollDate = rs.getDate("enroll_date");
                Date expireDate = rs.getDate("expire_date");
                int progress = rs.getInt("progress");
                int status = rs.getInt("status");

                int pricePackageId = rs.getInt("price_package_id");
                int saleNoteId = rs.getInt("sale_note_id");
                int price = rs.getInt("price");
                PersonalCourse obj = new PersonalCourse(id, customerId, courseId, enrollDate, expireDate, progress, status, pricePackageId, saleNoteId, price);
                vector.add(obj);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public PersonalCourse getPersonalCourseById(int search_id) {
        String sql = "SELECT id, customer_id, course_id, enroll_date, expire_date, progress, status, price_package_id, sale_note_id, price "
                + "FROM PersonalCourse "
                + "WHERE id = " + search_id;

        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);

            // Kiểm tra nếu có bản ghi nào trong ResultSet
            if (rs.next()) {
                int id = rs.getInt("id");
                int customerId = rs.getInt("customer_id");
                int courseId = rs.getInt("course_id");
                Date enrollDate = rs.getDate("enroll_date");
                Date expireDate = rs.getDate("expire_date");
                int progress = rs.getInt("progress");
                int status = rs.getInt("status");
                int pricePackageId = rs.getInt("price_package_id");
                int saleNoteId = rs.getInt("sale_note_id");
                int price = rs.getInt("price");

                // Tạo đối tượng PersonalCourse và trả về
                return new PersonalCourse(id, customerId, courseId, enrollDate, expireDate, progress, status, pricePackageId, saleNoteId, price);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null; // Trả về null nếu không tìm thấy hoặc có lỗi xảy ra
    }

    public void savePersonalCourse(PersonalCourse personalCourse) throws SQLException {
        String sql = "INSERT INTO PersonalCourse (customer_id, course_id, enroll_date, status, price_package_id, price, expire_date, progress) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Set các giá trị vào PreparedStatement
            stmt.setInt(1, personalCourse.getCustomerId());  // customer_id
            stmt.setInt(2, personalCourse.getCourseId());     // course_id
            stmt.setDate(3, new java.sql.Date(personalCourse.getEnrollDate().getTime()));  // enroll_date
            stmt.setInt(4, personalCourse.getStatus());       // status (Learning)
            stmt.setInt(5, personalCourse.getPricePackageId());  // price_package_id
            stmt.setDouble(6, personalCourse.getPrice());     // price
            stmt.setDate(7, new java.sql.Date(personalCourse.getExpireDate().getTime()));  // expire_date
            stmt.setInt(8, personalCourse.getProgress());     // progress (Ban đầu là 0)
//            stmt.setInt(9, personalCourse.getSaleNoteId());   // sale_note_id (Nếu có thông tin giảm giá thì lưu vào, nếu không thì null)

            // Thực hiện câu lệnh SQL
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();  // In ra lỗi nếu có
            throw e;  // Ném lại lỗi để xử lý ở ngoài
        }
    }

}
