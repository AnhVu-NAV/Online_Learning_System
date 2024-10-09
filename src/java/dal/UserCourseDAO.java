/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.UserCourse;

/**
 *
 * @author AnhVuNAV
 */
public class UserCourseDAO extends DBContext{
    public void registerUserForCourse(UserCourse userCourse) {
        String sql = "INSERT INTO UserCourse (fullName, phone1, phone2, email1, email2, contactMethod, gender, courseId, pricePackageId) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userCourse.getFullName());
            ps.setString(2, userCourse.getPhone1());
            ps.setString(3, userCourse.getPhone2());
            ps.setString(4, userCourse.getEmail1());
            ps.setString(5, userCourse.getEmail2());
            ps.setString(6, userCourse.getContactMethod());
            ps.setString(7, userCourse.getGender());
            ps.setInt(8, userCourse.getCourseId());
            ps.setInt(9, userCourse.getPricePackageId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
