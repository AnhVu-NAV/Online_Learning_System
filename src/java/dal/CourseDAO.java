/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import model.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DateConvert;

/**
 *
 * @author ADMIN
 */
public class CourseDAO extends DBContext {

    public void insert() {

    }

    public void update() {

    }

    public Course getCourseById(int id) {
        Course course = new Course();
        String sql = "select * from Course where id = ?";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, id);
            try (ResultSet resultSet = pre.executeQuery()) {
                if (resultSet.next()) {
                    course.setId(resultSet.getInt("id"));
                    course.setTitle(resultSet.getString("title"));
                    course.setExpertId(resultSet.getInt("expert_id"));
                    course.setTotalDuration(resultSet.getFloat("total_duration"));
                    course.setStatus(resultSet.getInt("status"));
                    course.setDescription(resultSet.getString("description"));
                    course.setCategoryId(resultSet.getInt("category_id"));
                    course.setCreatedDate(DateConvert.convertToUtilDate(resultSet.getDate("created_date")));
                    course.setCreatedDate(DateConvert.convertToUtilDate(resultSet.getDate("updated_date")));
                    course.setThumbnailUrl(resultSet.getString("thumbnail_url"));
                    course.setNumberOfLesson(resultSet.getInt("number_of_lesson"));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return course;
    }

    public void delete() {

    }
}
