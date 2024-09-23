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
import model.Setting;

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

    public List<Course> getCoursesByCategoriesAndKeyword(List<String> categories, String keyword, String sortOption, int page, int pageSize) {
    List<Course> courses = new ArrayList<>();
    String query = "SELECT c.*, pp.price, pp.sale_price FROM Course c "
                 + "JOIN PricePackage pp ON c.id = pp.course_id "
                 + "WHERE c.status = 1";

    // Bổ sung điều kiện lọc theo danh mục và từ khóa
    if (!categories.isEmpty()) {
        String categoryFilter = String.join(",", Collections.nCopies(categories.size(), "?"));
        query += " AND c.category_id IN (SELECT id FROM Setting WHERE value IN (" + categoryFilter + ") AND setting_type_id = 2)";
    }
    if (keyword != null && !keyword.isEmpty()) {
        query += " AND c.title LIKE ?";
    }
    
    // Thêm điều kiện sắp xếp dựa vào sortOption
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
                query += " ORDER BY c.updated_date DESC"; // Mặc định sắp xếp theo ngày cập nhật
                break;
        }
    } else {
        query += " ORDER BY c.updated_date DESC"; // Mặc định sắp xếp theo ngày cập nhật
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
            course.setCategoryId(rs.getInt("category_id"));
            course.setCreatedDate(rs.getDate("created_date"));
            course.setUpdatedDate(rs.getDate("updated_date"));
            course.setThumbnailUrl(rs.getString("thumbnail_url"));
            course.setNumberOfLesson(rs.getInt("number_of_lesson"));
            course.setPrice(rs.getInt("price"));
            course.setSalePrice(rs.getInt("sale_price"));
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

}