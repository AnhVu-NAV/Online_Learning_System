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

    public void insert(Course course) {
        String sql = "insert into Course (id, title, subtitle, expert_id, total_duration, status, description, category_id, created_date, uploaded_date, thumbnail_url, number_of_learner"
                + "values (?,?,?,?,?,?,?,?,?,?,?,?);";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, course.getId()); //id
            pre.setString(2, course.getTitle()); // title
            pre.setString(3, course.getTitle()); // subtitle  
            pre.setInt(4, course.getExpertId()); // expert id
            pre.setFloat(5, course.getTotalDuration()); // total duration
            pre.setInt(6, course.getStatus()); // status
            pre.setString(7, course.getDescription()); // description 
            pre.setInt(8, course.getCategoryId()); //category id
            pre.setDate(9, DateConvert.convertToSQLDate(course.getCreatedDate()));
            pre.setDate(10, DateConvert.convertToSQLDate(course.getUpdatedDate()));
            pre.setString(11, course.getThumbnailUrl()); // thumbnail url
            pre.setInt(12, course.getNumberOfLearner()); // number of learner
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, e);
        }
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
                    course.setSubtitle(resultSet.getString("subtitle"));
                    course.setExpertId(resultSet.getInt("expert_id"));
                    course.setTotalDuration(resultSet.getFloat("total_duration"));
                    course.setStatus(resultSet.getInt("status"));
                    course.setDescription(resultSet.getString("description"));
                    course.setCategoryId(resultSet.getInt("category_id"));
                    course.setCreatedDate(DateConvert.convertToUtilDate(resultSet.getDate("created_date")));
                    course.setCreatedDate(DateConvert.convertToUtilDate(resultSet.getDate("updated_date")));
                    course.setThumbnailUrl(resultSet.getString("thumbnail_url"));
                    course.setNumberOfLearner(resultSet.getInt("number_of_learner"));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return course;
    }

    // test done
    public Vector<Course> getAllCourses() {
        Vector<Course> list = new Vector();
        String sql = "select * from Course";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = pre.executeQuery()) {
                while (resultSet.next()) {
                    Course course = new Course();
                    course.setId(resultSet.getInt("id"));
                    course.setTitle(resultSet.getString("title"));
                    course.setSubtitle(resultSet.getString("subtitle"));
                    course.setExpertId(resultSet.getInt("expert_id"));
                    course.setTotalDuration(resultSet.getFloat("total_duration"));
                    course.setStatus(resultSet.getInt("status"));
                    course.setDescription(resultSet.getString("description"));
                    course.setCategoryId(resultSet.getInt("category_id"));
                    course.setCreatedDate(DateConvert.convertToUtilDate(resultSet.getDate("created_date")));
                    course.setCreatedDate(DateConvert.convertToUtilDate(resultSet.getDate("updated_date")));
                    course.setThumbnailUrl(resultSet.getString("thumbnail_url"));
                    course.setNumberOfLearner(resultSet.getInt("number_of_learner"));
                    list.add(course);
                }
            }
        } catch (Exception e) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    public int getIdByTitle(String title) {
        String sql = "select id from Course where title = ?";
        String request = "%" + title + "%";
        int id = 0;
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, request);
            try (ResultSet resultSet = pre.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getInt("id");
                }
            }
        } catch (Exception e) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return id;
    }

    public void deleteCourseById(int id) {
        String sql = "delete from Course where id = ?";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, id);
            pre.executeQuery();
        } catch (Exception e) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public static void main(String[] args) {
        CourseDAO courseDAO = new CourseDAO();
        for (Course c: courseDAO.getAllCourses()) {
            System.out.println(c.toString());
        }
    }
}
