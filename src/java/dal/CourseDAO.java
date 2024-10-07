/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Course;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Collections;
import model.PricePackage;
import model.Setting;
import model.Tagline;

/**
 *
 * @author AnhVuNAV
 */
public class CourseDAO extends DBContext {

    public List<Course> getCourses(int page, int pageSize) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.*, pp.price, pp.sale_price FROM Course c "
                + "JOIN PricePackage pp ON c.id = pp.course_id "
                + "WHERE c.status = 1  LIMIT ?, ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (page - 1) * pageSize);
            ps.setInt(2, pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setTitle(rs.getString("title"));
                course.setDescription(rs.getString("description"));
                course.setThumbnailUrl(rs.getString("thumbnail_url"));
                course.setNumberOfLesson(rs.getInt("number_of_lesson"));
                course.setPrice(rs.getInt("price"));
                course.setSalePrice(rs.getInt("sale_price"));
                course.setCreatedDate(rs.getDate("created_date"));
                course.setUpdatedDate(rs.getDate("updated_date"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public int getTotalCourses() {
        String sql = "SELECT COUNT(*) AS total FROM Course WHERE status = 1";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Setting> getCourseCategories() {
        List<Setting> categories = new ArrayList<>();
        String query = "SELECT id, setting_type_id, value, status, description FROM Setting WHERE setting_type_id = 2";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Setting category = new Setting();
                category.setId(rs.getInt("id"));
                category.setSettingTypeId(rs.getInt("setting_type_id"));
                category.setValue(rs.getString("value"));
                category.setStatus(rs.getInt("status"));
                category.setDescription(rs.getString("description"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public List<String> getTaglinesByCourseId(int courseId) {
        List<String> taglines = new ArrayList<>();
        String sql = "SELECT t.name FROM Tagline t "
                + "JOIN Course_Tagline ct ON t.id = ct.tagline_id "
                + "WHERE ct.course_id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                taglines.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taglines;
    }

    public List<Course> getCoursesByCategoriesAndKeyword(List<String> categories, String keyword, String sortOption, int page, int pageSize) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT c.*, pp.price, pp.sale_price FROM Course c "
                + "JOIN PricePackage pp ON c.id = pp.course_id "
                + "WHERE c.status = 1";

        // Điều kiện lọc theo danh mục và từ khóa
        if (!categories.isEmpty()) {
            String categoryFilter = String.join(",", Collections.nCopies(categories.size(), "?"));
            query += " AND c.category_id IN (SELECT id FROM Setting WHERE value IN (" + categoryFilter + ") AND setting_type_id = 2)";
        }
        if (keyword != null && !keyword.isEmpty()) {
            query += " AND c.title LIKE ?";
        }

        // Thêm điều kiện sắp xếp
        if (sortOption != null) {
            switch (sortOption) {
                case "latest":
                    query += " ORDER BY c.updated_date DESC";
                    break;
                case "priceLowHigh":
                    query += " ORDER BY pp.price ASC";
                    break;
                case "priceHighLow":
                    query += " ORDER BY pp.price DESC";
                    break;
                default:
                    query += " ORDER BY c.updated_date DESC";
                    break;
            }
        } else {
            query += " ORDER BY c.updated_date DESC";
        }

        query += " LIMIT ?, ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            int index = 1;
            for (String category : categories) {
                ps.setString(index++, category);
            }
            if (keyword != null && !keyword.isEmpty()) {
                ps.setString(index++, "%" + keyword + "%");
            }
            ps.setInt(index++, (page - 1) * pageSize);
            ps.setInt(index, pageSize);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setTitle(rs.getString("title"));
                course.setExpertId(rs.getInt("expert_id"));
                course.setTotalDuration(rs.getFloat("total_duration"));
                course.setDescription(rs.getString("description"));
                course.setSubtitle(rs.getString("subtitle"));
                course.setCategoryId(rs.getInt("category_id"));
                course.setCreatedDate(rs.getDate("created_date"));
                course.setUpdatedDate(rs.getDate("updated_date"));
                course.setThumbnailUrl(rs.getString("thumbnail_url"));
                course.setNumberOfLesson(rs.getInt("number_of_learner"));
                course.setPrice(rs.getInt("price"));
                course.setSalePrice(rs.getInt("sale_price"));

                // Thêm tagline cho khóa học
                course.setTaglines(getTaglinesByCourseId(course.getId())); // Đảm bảo rằng bạn đã thêm phương thức setTaglines trong lớp Course

                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public int getTotalCoursesByCategoriesAndKeyword(List<String> categories, String keyword) {
        int total = 0;
        String query = "SELECT COUNT(*) FROM Course WHERE status = 1";

        // Bổ sung điều kiện lọc theo danh mục và từ khóa
        if (!categories.isEmpty()) {
            String categoryFilter = String.join(",", Collections.nCopies(categories.size(), "?"));
            query += " AND category_id IN (SELECT id FROM Setting WHERE value IN (" + categoryFilter + ") AND setting_type_id = 2)";
        }
        if (keyword != null && !keyword.isEmpty()) {
            query += " AND title LIKE ?";
        }

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            int index = 1;
            for (String category : categories) {
                ps.setString(index++, category);
            }
            if (keyword != null && !keyword.isEmpty()) {
                ps.setString(index, "%" + keyword + "%");
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public Course getCourseById(int courseId) {
        String sql = "SELECT * FROM Course WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setTitle(rs.getString("title"));
                course.setDescription(rs.getString("description"));
                course.setThumbnailUrl(rs.getString("thumbnail_url"));
                course.setCategoryId(rs.getInt("category_id"));
                return course;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PricePackage getPricePackageByCourseId(int courseId) {
        String sql = "SELECT * FROM PricePackage WHERE course_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                PricePackage pricePackage = new PricePackage();
                pricePackage.setPrice(rs.getInt("price"));
                pricePackage.setSalePrice(rs.getInt("sale_price"));
                return pricePackage;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Course> getRelatedCourses(int categoryId) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM Course WHERE category_id = ? LIMIT 5";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setTitle(rs.getString("title"));
                course.setDescription(rs.getString("description"));
                course.setThumbnailUrl(rs.getString("thumbnail_url"));
                courses.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }

    public List<Course> getRelatedCourses(int categoryId, int expertId, int courseId) {
        List<Course> relatedCourses = new ArrayList<>();
        String sql = "SELECT * FROM Course WHERE (category_id = ? OR expert_id = ?) AND id != ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ps.setInt(2, expertId);
            ps.setInt(3, courseId); // Tránh lấy lại chính khóa học hiện tại

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setTitle(rs.getString("title"));
                course.setDescription(rs.getString("description"));
                course.setThumbnailUrl(rs.getString("thumbnail_url"));
                // Các thuộc tính khác của khóa học
                relatedCourses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return relatedCourses;
    }

    public List<Tagline> getAllTaglines() {
        List<Tagline> taglines = new ArrayList<>();
        String query = "SELECT * FROM Tagline"; // Lấy tất cả tag từ bảng Tagline

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tagline tagline = new Tagline();
                tagline.setId(rs.getInt("id"));
                tagline.setName(rs.getString("name"));
                taglines.add(tagline);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taglines;
    }

}
