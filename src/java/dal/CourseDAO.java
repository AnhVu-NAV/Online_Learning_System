/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Course;
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
public class CourseDAO extends DBContext {

    DataConvert dc = new DataConvert();

    public int insertCourse(Course obj) {
        int n = 0;
        String sql = "INSERT INTO Course\n"
                + "           (title\n"
                + "           ,subtitle\n"
                + "           ,expert_id\n"
                + "           ,total_duration\n"
                + "           ,category_id\n"
                + "           ,description\n"
                + "           ,thumbnail_url\n"
                + "           ,status\n"
                + "           ,updated_date\n"
                + "           ,created_date\n"
                + "           ,number_of_learner)\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, obj.getTitle());
            pre.setString(2, obj.getSubtitle());
            pre.setInt(3, obj.getExpertId());
            pre.setInt(4, obj.getTotalDuration());
            pre.setInt(5, obj.getCategoryId());
            pre.setString(6, obj.getDescription());
            pre.setString(7, obj.getThumbnailUrl());
            pre.setInt(8, obj.getStatus());
            pre.setDate(9, dc.UtilDateToSqlDate(obj.getUpdatedDate()));
            pre.setDate(10, dc.UtilDateToSqlDate(obj.getCreatedDate()));
            pre.setInt(11, obj.getNumberOfLearner());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;

    }
    
    
    public Vector<Course> getCourses(String sql) {
        Vector<Course> vector = new Vector<Course>();
        try {
            Statement state = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String subtitle = rs.getString(3);
                int expertId = rs.getInt(4);
                int totalDuration = rs.getInt(5);
                int categoryId = rs.getInt(6);
                String description = rs.getString(7);
                String thumbnailUrl=rs.getString(8);
                int status = rs.getInt(9);               
                Date updatedDate=rs.getDate(10);
                Date createdDate = rs.getDate(11);
                int numberOfLearner=rs.getInt(12);
                Course obj = new Course(id, title, subtitle, expertId, totalDuration, categoryId, description, thumbnailUrl, status, updatedDate, createdDate, numberOfLearner);
                vector.add(obj);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;

    }
    public Vector<Course> getAll() {
        Vector<Course> vector = new Vector<>();
        String sql = "SELECT id\n"
                + "      ,title\n"
                + "      ,subtitle\n"
                + "      ,expert\n"
                + "      ,total_duration\n"
                + "      ,category_id\n"
                + "      ,description\n"
                + "      ,thumbnail_url\n"
                + "      ,status\n"
                + "      ,updated_date\n"
                + "      ,created_date\n"
                + "      ,number_of_learner\n"
                + "  FROM course";
        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String subtitle = rs.getString(3);
                int expertId = rs.getInt(4);
                int totalDuration = rs.getInt(5);
                int categoryId = rs.getInt(6);
                String description = rs.getString(7);
                String thumbnailUrl=rs.getString(8);
                int status = rs.getInt(9);               
                Date updatedDate=rs.getDate(10);
                Date createdDate = rs.getDate(11);
                int numberOfLearner=rs.getInt(12);
                Course obj = new Course(id, title, subtitle, expertId, totalDuration, categoryId, description, thumbnailUrl, status, updatedDate, createdDate, numberOfLearner);
                vector.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    public Course getCourseById(int search_id) {
        String sql = "SELECT id\n"
                + "      ,title\n"
                + "      ,subtitle\n"
                + "      ,expert\n"
                + "      ,total_duration\n"
                + "      ,category_id\n"
                + "      ,description\n"
                + "      ,thumbnail_url\n"
                + "      ,status\n"
                + "      ,updated_date\n"
                + "      ,created_date\n"
                + "      ,number_of_learner\n"
                + "  FROM course"
                + "  WHERE Course.id = " + search_id;

        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String subtitle = rs.getString(3);
                int expertId = rs.getInt(4);
                int totalDuration = rs.getInt(5);
                int categoryId = rs.getInt(6);
                String description = rs.getString(7);
                String thumbnailUrl=rs.getString(8);
                int status = rs.getInt(9);               
                Date updatedDate=rs.getDate(10);
                Date createdDate = rs.getDate(11);
                int numberOfLearner=rs.getInt(12);
                Course obj = new Course(id, title, subtitle, expertId, totalDuration, categoryId, description, thumbnailUrl, status, updatedDate, createdDate, numberOfLearner);
                return obj;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;

    }

}
