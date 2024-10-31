package dal;

import model.Course;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import util.DataConvert;

public class CourseDAO extends DBContext {

    DataConvert dc = new DataConvert();

    // Insert a new course
    public int insertCourse(Course obj) {
        int n = 0;
        String sql = "INSERT INTO Course "
                + "(title, subtitle, expert_id, total_duration, category_id, description, status, updated_date, created_date, number_of_learner) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, obj.getTitle());
            pre.setString(2, obj.getSubtitle());
            pre.setInt(3, obj.getExpertId());
            pre.setInt(4, obj.getTotalDuration());
            pre.setInt(5, obj.getCategoryId());
            pre.setString(6, obj.getDescription());
            pre.setInt(7, obj.getStatus());
            pre.setDate(8, dc.UtilDateToSqlDate(obj.getUpdatedDate()));
            pre.setDate(9, dc.UtilDateToSqlDate(obj.getCreatedDate()));
            pre.setInt(10, obj.getNumberOfLearner());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    // Get courses based on custom SQL query
    public Vector<Course> getCourses(String sql) {
        Vector<Course> vector = new Vector<>();
        try {
            PreparedStatement state = connection.prepareStatement(sql);
            ResultSet rs = state.executeQuery();
            while (rs.next()) {
                Course obj = mapResultSetToCourse(rs);
                vector.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    // Get all courses
    public Vector<Course> getAll() {
        Vector<Course> vector = new Vector<>();
        String sql = "SELECT id, title, subtitle, expert_id, total_duration, category_id, description, status, updated_date, created_date, number_of_learner FROM course";
        try {
            PreparedStatement state = connection.prepareStatement(sql);
            ResultSet rs = state.executeQuery();
            while (rs.next()) {
                Course obj = mapResultSetToCourse(rs);
                vector.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    // Get course by ID
    public Course getCourseById(int searchId) {
        String sql = "SELECT id, title, subtitle, expert_id, total_duration, category_id, description, status, updated_date, created_date, number_of_learner FROM course WHERE id = ?";
        try {
            PreparedStatement state = connection.prepareStatement(sql);
            state.setInt(1, searchId);
            ResultSet rs = state.executeQuery();
            if (rs.next()) {
                return mapResultSetToCourse(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Helper method to map ResultSet to Course object
    private Course mapResultSetToCourse(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String subtitle = rs.getString("subtitle");
        int expertId = rs.getInt("expert_id");
        int totalDuration = rs.getInt("total_duration");
        int categoryId = rs.getInt("category_id");
        String description = rs.getString("description");
        int status = rs.getInt("status");
        Date updatedDate = rs.getDate("updated_date");
        Date createdDate = rs.getDate("created_date");
        int numberOfLearner = rs.getInt("number_of_learner");

        return new Course(id, title, subtitle, expertId, totalDuration, categoryId, description, status, updatedDate, createdDate, numberOfLearner);
    }
}
