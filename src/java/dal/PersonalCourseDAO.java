/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.PersonalCourse;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import util.*;

/**
 *
 * @author ADMIN
 */
public class PersonalCourseDAO extends DBContext {

    public void insertPersonalCourse(PersonalCourse personalCourse) {
        String sql = "INSERT INTO PersonalCourse (customer_id, course_id, status, enroll_date, expire_date, progress, price_package_id)\n"
                + "values (?,?,?,?,?,?,?);";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, personalCourse.getCustomerId());
            pre.setInt(2, personalCourse.getCourseId());
            pre.setInt(3, personalCourse.getStatus());
            pre.setDate(4, DateConvert.convertToSQLDate(personalCourse.getEnrollDate()));
            pre.setDate(5, DateConvert.convertToSQLDate(personalCourse.getExpireDate()));
            pre.setInt(6, personalCourse.getProgress());
            pre.setInt(7, personalCourse.getPricePackageId());
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(PersonalCourseDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void deletePersonalCourseById(int id) {
        String sql = "DELETE FROM PersonalCourse WHERE id = ?;";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, id);
            pre.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(PersonalCourseDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public Vector<PersonalCourse> getPersonalCourses(String query) {
        String sql = "select * from PersonalCourse " + query;
        Vector<PersonalCourse> list = new Vector();
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                PersonalCourse personalCourse = new PersonalCourse();
                personalCourse.setId(resultSet.getInt("id"));
                personalCourse.setCustomerId(resultSet.getInt("customer_id"));
                personalCourse.setCourseId(resultSet.getInt("course_id"));
                personalCourse.setStatus(resultSet.getInt("status"));
                personalCourse.setEnrollDate(DateConvert.convertToUtilDate(resultSet.getDate("enroll_date")));
                personalCourse.setExpireDate(DateConvert.convertToUtilDate(resultSet.getDate("expire_date")));
                personalCourse.setProgress(resultSet.getInt("progress"));
                personalCourse.setPricePackageId(resultSet.getInt("price_package_id"));
                list.add(personalCourse);
            }
            return list;
        } catch (SQLException e) {
            Logger.getLogger(PersonalCourseDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public Vector<PersonalCourse> getCourseBySearching(String string) {
        Vector<PersonalCourse> list = new Vector();
        String sql = "select * from PersonalCourse p inner join Course c\n"
                + "on p.course_id = c.id and (c.title LIKE ? OR c.description LIKE ? OR c.number_of_leaener = ? OR c.total_duration = ?)\n";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            String searchString = "%" + string + "%";
            pre.setString(1, searchString);
            pre.setString(2, searchString);
            pre.setString(3, string);
            pre.setString(4, string);
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                PersonalCourse personalCourse = new PersonalCourse();
                personalCourse.setId(resultSet.getInt("id"));
                personalCourse.setCustomerId(resultSet.getInt("customer_id"));
                personalCourse.setCourseId(resultSet.getInt("course_id"));
                personalCourse.setStatus(resultSet.getInt("status"));
                personalCourse.setEnrollDate(DateConvert.convertToUtilDate(resultSet.getDate("enroll_date")));
                personalCourse.setExpireDate(DateConvert.convertToUtilDate(resultSet.getDate("expire_date")));
                personalCourse.setProgress(resultSet.getInt("progress"));
                personalCourse.setPricePackageId(resultSet.getInt("price_package_id"));
                list.add(personalCourse);
            }
        } catch (Exception e) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    public void updatePersonalCourse(int courseId, int customerId, PersonalCourse personalCourse) {
        String sql = "update PersonalCourse\n"
                + "set status = ?, enroll_date = ?, expire_date = ?, progress = ?, customer_id = ?, course_id = ?\n"
                + "where id = ?";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, personalCourse.getStatus());
            pre.setDate(2, DateConvert.convertToSQLDate(personalCourse.getEnrollDate()));
            pre.setDate(3, DateConvert.convertToSQLDate(personalCourse.getExpireDate()));
            pre.setInt(4, personalCourse.getProgress());
            pre.setInt(5, customerId);
            pre.setInt(6, courseId);
            pre.setInt(7, personalCourse.getId());
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(PersonalCourseDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
