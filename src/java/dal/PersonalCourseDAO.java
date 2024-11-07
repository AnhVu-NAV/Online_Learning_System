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

}
