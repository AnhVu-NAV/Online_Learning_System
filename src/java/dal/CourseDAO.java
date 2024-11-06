/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PricePackage;
import model.Setting;
import model.Tagline;
import model.Course;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import util.DataConvert;

/**
 *
 * @author AnhVuNAV
 */
public class CourseDAO extends DBContext {

    //VuNA
    public List<Course> getCourses(int page, int pageSize) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.*, GROUP_CONCAT(cth.thumbnail_url) AS thumbnail_urls, pp.price, pp.sale_price "
                + "FROM Course c "
                + "LEFT JOIN PricePackage pp ON c.id = pp.course_id "
                + "LEFT JOIN Course_Thumbnails cth ON c.id = cth.course_id "
                + "WHERE c.status = 1 "
                + "GROUP BY c.id "
                + "LIMIT ?, ?";

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

                // Set the list of thumbnail URLs
                String thumbnailUrls = rs.getString("thumbnail_urls");
                if (thumbnailUrls != null) {
                    List<String> thumbnailUrlList = Arrays.asList(thumbnailUrls.split(","));
                    course.setThumbnailUrls(thumbnailUrlList);
                } else {
                    course.setThumbnailUrls(new ArrayList<>()); // Set an empty list if no thumbnails
                }

                // Set the number of learners
                course.setNumberOfLearner(rs.getInt("number_of_learner"));

                // Set the price and sale price
                course.setPrice(rs.getInt("price"));
                course.setSalePrice(rs.getInt("sale_price"));

                // Set the created and updated dates
                course.setCreatedDate(rs.getDate("created_date"));
                course.setUpdatedDate(rs.getDate("updated_date"));

                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the error as needed
        }
        return courses;
    }

    // Lấy tổng số khóa học
    public int getTotalCourses() {
        String sql = "SELECT COUNT(*) AS total FROM Course WHERE status = 1";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Thêm logger nếu cần thiết
        }
        return 0;
    }

    // Lấy danh sách danh mục khóa học
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

    // Lấy danh sách các tagline của khóa học dựa theo id khóa học
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
        StringBuilder query = new StringBuilder(
                "SELECT c.id, c.title, c.subtitle, c.description, c.category_id, c.expert_id, "
                + "c.number_of_learner, c.status, c.created_date, c.updated_date, "
                + "GROUP_CONCAT(DISTINCT cth.thumbnail_url) AS thumbnail_urls, "
                + "MAX(pp.price) AS price, MAX(pp.sale_price) AS sale_price, "
                + "GROUP_CONCAT(DISTINCT t.name) AS taglines "
                + "FROM Course c "
                + "LEFT JOIN PricePackage pp ON c.id = pp.course_id "
                + "LEFT JOIN Course_Thumbnails cth ON c.id = cth.course_id "
                + "LEFT JOIN Course_Tagline ct ON c.id = ct.course_id "
                + "LEFT JOIN Tagline t ON ct.tagline_id = t.id "
                + "WHERE c.status = 1"
        );

        // Add filters for categories
        if (categories != null && !categories.isEmpty()) {
            String categoryFilter = String.join(",", Collections.nCopies(categories.size(), "?"));
            query.append(" AND c.category_id IN (SELECT id FROM Setting WHERE value IN (").append(categoryFilter).append(") AND setting_type_id = 2)");
        }

        // Add keyword filter
        if (keyword != null && !keyword.isEmpty()) {
            query.append(" AND c.title LIKE ?");
        }

        // Add GROUP BY clause before ORDER BY
        query.append(" GROUP BY c.id, c.title, c.subtitle, c.description, c.category_id, c.expert_id, "
                + "c.number_of_learner, c.status, c.created_date, c.updated_date");

        // Add sorting
        if (sortOption == null) {
            sortOption = "default";
        }
        switch (sortOption) {
            case "priceLowHigh":
                query.append(" ORDER BY price ASC");
                break;
            case "priceHighLow":
                query.append(" ORDER BY price DESC");
                break;
            default:
                query.append(" ORDER BY c.updated_date DESC");
                break;
        }

        // Add LIMIT for pagination
        query.append(" LIMIT ?, ?");

        try {
            PreparedStatement ps = connection.prepareStatement(query.toString());
            int index = 1;

            // Set parameters for categories
            if (categories != null && !categories.isEmpty()) {
                for (String category : categories) {
                    ps.setString(index++, category);
                }
            }

            // Set parameter for keyword
            if (keyword != null && !keyword.isEmpty()) {
                ps.setString(index++, "%" + keyword + "%");
            }

            // Set pagination parameters
            ps.setInt(index++, (page - 1) * pageSize);
            ps.setInt(index, pageSize);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setTitle(rs.getString("title"));
                course.setSubtitle(rs.getString("subtitle"));
                course.setDescription(rs.getString("description"));
                course.setCategoryId(rs.getInt("category_id"));
                course.setExpertId(rs.getInt("expert_id"));
                course.setNumberOfLearner(rs.getInt("number_of_learner"));
                course.setStatus(rs.getInt("status"));
                course.setCreatedDate(rs.getDate("created_date"));
                course.setUpdatedDate(rs.getDate("updated_date"));
                course.setThumbnailUrls(Arrays.asList(rs.getString("thumbnail_urls").split(",")));
                course.setPrice(rs.getInt("price"));
                course.setSalePrice(rs.getInt("sale_price"));
                course.setTaglines(Arrays.asList(rs.getString("taglines").split(",")));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // Lấy tổng số khóa học dựa trên danh mục và từ khóa tìm kiếm
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

    // Lấy khóa học theo id
    public Course getCourseByID(int courseId) {
        String sql = "SELECT * FROM Course WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setTitle(rs.getString("title"));
                course.setTotalDuration(rs.getFloat("total_duration"));
                course.setDescription(rs.getString("description"));
                course.setSubtitle(rs.getString("subtitle"));
                course.setCategoryId(rs.getInt("category_id"));
                course.setExpertId(rs.getInt("expert_id")); // Set the expert ID
                course.setStatus(rs.getInt("status")); // Set the status
                course.setNumberOfLearner(rs.getInt("number_of_learner"));
                course.setCreatedDate(rs.getDate("created_date")); // Set the created date
                course.setUpdatedDate(rs.getDate("updated_date")); // Set the updated date

                // Retrieve and set the thumbnail URLs
                course.setThumbnailUrls(getThumbnailUrlsByCourseId(courseId));

                return course; // Return the populated course object
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return null if the course is not found
    }

    // Lấy thông tin gói giá của khóa học
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
        String sql = "SELECT c.*, GROUP_CONCAT(cth.thumbnail_url) AS thumbnail_urls "
                + "FROM Course c "
                + "LEFT JOIN Course_Thumbnails cth ON c.id = cth.course_id "
                + "WHERE c.category_id = ? "
                + "GROUP BY c.id "
                + "LIMIT 5"; // Limit to 5 related courses

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setTitle(rs.getString("title"));
                course.setSubtitle(rs.getString("subtitle"));
                course.setDescription(rs.getString("description"));
                course.setExpertId(rs.getInt("expert_id"));
                course.setTotalDuration(rs.getFloat("total_duration"));
                course.setNumberOfLearner(rs.getInt("number_of_learner"));
                course.setCreatedDate(rs.getDate("created_date"));
                course.setUpdatedDate(rs.getDate("updated_date"));

                // Handle multiple thumbnail URLs
                String thumbnailUrls = rs.getString("thumbnail_urls");
                if (thumbnailUrls != null) {
                    List<String> thumbnailUrlList = Arrays.asList(thumbnailUrls.split(","));
                    course.setThumbnailUrls(thumbnailUrlList);
                } else {
                    course.setThumbnailUrls(new ArrayList<>());
                }

                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public List<Course> getRelatedCourses(int categoryId, int expertId, int courseId) {
        List<Course> relatedCourses = new ArrayList<>();
        String sql = "SELECT c.*, GROUP_CONCAT(cth.thumbnail_url) AS thumbnail_urls, "
                + "MIN(pp.price) AS price, MIN(pp.sale_price) AS sale_price "
                + "FROM Course c "
                + "LEFT JOIN Course_Thumbnails cth ON c.id = cth.course_id "
                + "LEFT JOIN PricePackage pp ON c.id = pp.course_id "
                + "WHERE (c.category_id = ? OR c.expert_id = ?) AND c.id != ? "
                + "GROUP BY c.id";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            ps.setInt(2, expertId);
            ps.setInt(3, courseId); // Exclude the current course

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setTitle(rs.getString("title"));
                course.setSubtitle(rs.getString("subtitle"));
                course.setDescription(rs.getString("description"));
                course.setExpertId(rs.getInt("expert_id"));
                course.setTotalDuration(rs.getFloat("total_duration"));
                course.setNumberOfLearner(rs.getInt("number_of_learner"));
                course.setCreatedDate(rs.getDate("created_date"));
                course.setUpdatedDate(rs.getDate("updated_date"));
                course.setPrice(rs.getInt("price")); // Giá đã sửa
                course.setSalePrice(rs.getInt("sale_price")); // Giá khuyến mãi đã sửa

                // Xử lý danh sách URL thumbnail
                String thumbnailUrls = rs.getString("thumbnail_urls");
                if (thumbnailUrls != null) {
                    List<String> thumbnailUrlList = Arrays.asList(thumbnailUrls.split(","));
                    course.setThumbnailUrls(thumbnailUrlList);
                } else {
                    course.setThumbnailUrls(new ArrayList<>());
                }

                // Lấy và đặt taglines cho khóa học
                course.setTaglines(getTaglinesByCourseId(course.getId()));

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

// Phương thức lấy các khóa học liên quan dựa trên tagline
    public List<Course> getRelatedCoursesByTaglines(List<String> taglines, int courseId) {
        List<Course> relatedCourses = new ArrayList<>();
        if (taglines == null || taglines.isEmpty()) {
            return relatedCourses;
        }

        String sql = "SELECT DISTINCT c.*, GROUP_CONCAT(cth.thumbnail_url) AS thumbnail_urls, pp.price, pp.sale_price "
                + "FROM Course c "
                + "JOIN Course_Tagline ct ON c.id = ct.course_id "
                + "JOIN Tagline t ON ct.tagline_id = t.id "
                + "LEFT JOIN Course_Thumbnails cth ON c.id = cth.course_id "
                + "LEFT JOIN PricePackage pp ON c.id = pp.course_id "
                + "WHERE t.name IN (" + String.join(",", Collections.nCopies(taglines.size(), "?")) + ") "
                + "AND c.id != ? "
                + "GROUP BY c.id";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            int index = 1;
            for (String tagline : taglines) {
                ps.setString(index++, tagline);
            }
            ps.setInt(index, courseId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setTitle(rs.getString("title"));
                course.setSubtitle(rs.getString("subtitle"));
                course.setDescription(rs.getString("description"));

                // Set the list of thumbnail URLs
                String thumbnailUrls = rs.getString("thumbnail_urls");
                if (thumbnailUrls != null) {
                    List<String> thumbnailUrlList = Arrays.asList(thumbnailUrls.split(","));
                    course.setThumbnailUrls(thumbnailUrlList);
                } else {
                    course.setThumbnailUrls(new ArrayList<>()); // Set an empty list if no thumbnails
                }

                // Set the price and sale price
                course.setPrice(rs.getInt("price"));
                course.setSalePrice(rs.getInt("sale_price"));

                // Set the list of taglines for the course
                course.setTaglines(getTaglinesByCourseId(course.getId()));

                relatedCourses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return relatedCourses;
    }

    public List<Course> getRelatedCoursesByFilters(int categoryId, List<String> taglines, int limit) {
        List<Course> relatedCourses = new ArrayList<>();
        String sql = "SELECT c.id, c.title, c.subtitle, pp.price, pp.sale_price, GROUP_CONCAT(t.name) AS taglines "
                + "FROM Course c "
                + "JOIN PricePackage pp ON c.id = pp.course_id "
                + "JOIN Course_Tagline ct ON c.id = ct.course_id "
                + "JOIN Tagline t ON ct.tagline_id = t.id "
                + "WHERE c.category_id = ? "
                + "AND t.name IN (" + String.join(",", Collections.nCopies(taglines.size(), "?")) + ") "
                + "GROUP BY c.id "
                + "LIMIT ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            for (int i = 0; i < taglines.size(); i++) {
                ps.setString(2 + i, taglines.get(i));
            }
            ps.setInt(2 + taglines.size(), limit);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setTitle(rs.getString("title"));
                course.setSubtitle(rs.getString("subtitle"));
                course.setPrice((int) rs.getDouble("price"));
                course.setSalePrice((int) rs.getDouble("sale_price"));

                // Lấy danh sách taglines
                String taglinesStr = rs.getString("taglines");
                List<String> courseTaglines = Arrays.asList(taglinesStr.split(","));
                course.setTaglines(courseTaglines);

                relatedCourses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return relatedCourses;
    }

    // Lấy danh sách các khóa học từ cơ sở dữ liệu theo bộ lọc (tìm kiếm, danh mục, trạng thái)
    public List<Course> listAllCourses(String searchQuery, String categoryFilter, String statusFilter) throws SQLException {
        // Khởi tạo danh sách khóa học để lưu trữ kết quả
        List<Course> listCourses = new ArrayList<>();

        // Xây dựng câu truy vấn SQL động dựa trên các tham số đầu vào
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM Course WHERE 1=1");

        // Tạo danh sách các tham số cho PreparedStatement
        List<Object> parameters = new ArrayList<>();

        // Kiểm tra nếu có tìm kiếm theo tên khóa học
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            sqlBuilder.append(" AND title LIKE ?");
            parameters.add("%" + searchQuery + "%");
        }

        // Kiểm tra nếu có lọc theo danh mục
        if (categoryFilter != null && !categoryFilter.trim().isEmpty()) {
            sqlBuilder.append(" AND category_id LIKE ?");
            parameters.add("%" + categoryFilter + "%");
        }

        // Kiểm tra nếu có lọc theo trạng thái
        if (statusFilter != null && !statusFilter.trim().isEmpty()) {
            sqlBuilder.append(" AND status LIKE ?");
            parameters.add("%" + statusFilter + "%");
        }

        // Chuyển câu truy vấn thành chuỗi và chuẩn bị câu lệnh SQL
        String sql = sqlBuilder.toString();
        PreparedStatement statement = connection.prepareStatement(sql);

        // Gán các tham số vào câu lệnh truy vấn
        for (int i = 0; i < parameters.size(); i++) {
            statement.setObject(i + 1, parameters.get(i));
        }

        // Thực thi truy vấn và lấy kết quả trả về từ cơ sở dữ liệu
        ResultSet resultSet = statement.executeQuery();

        // Lặp qua từng bản ghi kết quả để tạo đối tượng Course và thêm vào danh sách
        while (resultSet.next()) {
            // Kiểm tra null cho cột description (nếu cần)
            String description = resultSet.getString("description");
            if (resultSet.wasNull()) {
                description = "";  // Hoặc có thể để null hoặc giá trị mặc định
            }

            Course course = new Course(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("subtitle"),
                    resultSet.getInt("category_id"),
                    resultSet.getInt("expert_id"),
                    description, // Sử dụng giá trị description đã kiểm tra null
                    resultSet.getInt("status")
            );
            listCourses.add(course); // Thêm khóa học vào danh sách
        }

        return listCourses; // Trả về danh sách khóa học
    }

// Hàm thêm mới khóa học và gắn taglines (chủ đề) cho khóa học
    public void insertCourse(Course course, String[] taglines) throws SQLException {
        // Câu truy vấn để thêm khóa học mới
        String sql = "INSERT INTO Course (title, subtitle, category_id, expert_id, description, status) VALUES (?, ?, ?, ?, ?, ?)";

        // Chuẩn bị câu lệnh SQL để thêm khóa học
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        // Đặt giá trị cho các trường trong bảng Course
        statement.setString(1, course.getTitle());        // Tiêu đề khóa học
        statement.setString(2, course.getSubtitle());     // Phụ đề khóa học
        statement.setInt(3, course.getCategoryId());      // ID danh mục
        statement.setInt(4, course.getExpertId());        // ID chuyên gia
        statement.setString(5, course.getDescription());  // Mô tả khóa học
        statement.setInt(6, course.getStatus());          // Trạng thái khóa học (active/inactive)

        // Thực thi lệnh thêm khóa học vào cơ sở dữ liệu
        statement.executeUpdate();

        // Lấy ID khóa học mới được tạo
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            int courseId = generatedKeys.getInt(1); // Lấy ID của khóa học vừa tạo

            // Gọi hàm để thêm taglines cho khóa học
            insertTaglines(courseId, taglines);
        }
    }

// Cập nhật thông tin khóa học và taglines của khóa học
    public void updateCourse(Course course, String[] taglines) throws SQLException {
        // Câu truy vấn để cập nhật khóa học theo ID
        String sql = "UPDATE Course SET title=?, subtitle=?, category_id=?, expert_id=?, description=?, status=? WHERE id=?";

        // Chuẩn bị câu lệnh SQL để cập nhật
        PreparedStatement statement = connection.prepareStatement(sql);

        // Đặt giá trị mới cho các trường của khóa học
        statement.setString(1, course.getTitle());        // Cập nhật tiêu đề khóa học
        statement.setString(2, course.getSubtitle());     // Cập nhật phụ đề khóa học
        statement.setInt(3, course.getCategoryId());      // Cập nhật danh mục khóa học
        statement.setInt(4, course.getExpertId());        // Cập nhật chuyên gia phụ trách khóa học
        statement.setString(5, course.getDescription());  // Cập nhật mô tả khóa học
        statement.setInt(6, course.getStatus());          // Cập nhật trạng thái khóa học
        statement.setInt(7, course.getId());              // Xác định ID của khóa học cần cập nhật

        // Thực thi lệnh cập nhật trong cơ sở dữ liệu
        statement.executeUpdate();

        // Xóa các tagline hiện tại của khóa học
        deleteCourseTaglines(course.getId());

        // Thêm mới các tagline cho khóa học
        insertTaglines(course.getId(), taglines);
    }

// Hàm thêm các tagline cho khóa học
    public void insertTaglines(int courseId, String[] taglines) throws SQLException {
        // Câu truy vấn để thêm tagline cho khóa học
        String sql = "INSERT INTO Course_Tagline (course_id, tagline_id) VALUES (?, ?)";

        // Lặp qua từng tagline và thêm vào cơ sở dữ liệu
        for (String taglineId : taglines) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, courseId);                // ID khóa học
            statement.setInt(2, Integer.parseInt(taglineId)); // ID của tagline
            statement.executeUpdate();                   // Thực thi câu lệnh thêm
        }
    }

// Hàm xóa tất cả các tagline của khóa học
    private void deleteCourseTaglines(int courseId) throws SQLException {
        // Câu truy vấn để xóa tất cả các tagline của khóa học
        String sql = "DELETE FROM Course_Tagline WHERE course_id=?";

        // Chuẩn bị câu lệnh SQL
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, courseId); // Đặt giá trị cho ID của khóa học cần xóa tagline

        // Thực thi câu lệnh xóa
        statement.executeUpdate();
    }

    // Lấy danh sách khóa học với phân trang
    public List<Course> listCoursesWithPagination(String searchQuery, String categoryFilter, String statusFilter, int startIndex, int subjectsPerPage) throws SQLException {
        List<Course> listCourses = new ArrayList<>();
        String sql = "SELECT c.*, s.value as categoryName, u.first_name as ownerFirstName, u.last_name as ownerLastName "
                + "FROM Course c "
                + "JOIN Setting s ON c.category_id = s.id "
                + "JOIN User u ON c.expert_id = u.id "
                + "WHERE c.title LIKE ? AND s.value LIKE ? AND c.status LIKE ? "
                + "LIMIT ?, ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + searchQuery + "%");
        statement.setString(2, "%" + categoryFilter + "%");
        statement.setString(3, "%" + statusFilter + "%");
        statement.setInt(4, startIndex);
        statement.setInt(5, subjectsPerPage);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Course course = new Course(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("subtitle"),
                    resultSet.getInt("category_id"),
                    resultSet.getInt("expert_id"),
                    resultSet.getString("description"),
                    resultSet.getInt("status")
            );
            // Set additional fields
            course.setCategoryName(resultSet.getString("categoryName"));
            course.setOwnerName(resultSet.getString("ownerFirstName") + " " + resultSet.getString("ownerLastName"));
            course.setTotalDuration(resultSet.getInt("total_duration"));
            course.setNumberOfLearner(resultSet.getInt("number_of_learner"));
            course.setCreatedDate(resultSet.getDate("created_date"));
            course.setUpdatedDate(resultSet.getDate("updated_date"));

            listCourses.add(course);
        }
        return listCourses;
    }

// Lấy tổng số lượng khóa học dựa trên các bộ lọc tìm kiếm, danh mục, trạng thái
    public int getTotalCourses(String searchQuery, String categoryFilter, String statusFilter) throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM Course WHERE title LIKE ? AND category_id LIKE ? AND status LIKE ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + searchQuery + "%");
        statement.setString(2, "%" + categoryFilter + "%");
        statement.setString(3, "%" + statusFilter + "%");

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("total");
        }
        return 0;
    }

    public int insertSubject(Course course) throws SQLException {
        // SQL query to insert a new course into the Course table
        String sql = "INSERT INTO Course (title, subtitle, category_id, expert_id, description, status, created_date, updated_date) "
                + "VALUES (?, ?, ?, ?, ?, ?, NOW(), NOW())";

        // Prepare the statement to insert the course and get the generated keys (course ID)
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Set the parameters for the prepared statement
            statement.setString(1, course.getTitle());       // Course name
            statement.setString(2, course.getSubtitle());    // Subtitle
            statement.setInt(3, course.getCategoryId());     // Category ID
            statement.setInt(4, course.getExpertId());       // Owner (expert ID)
            statement.setString(5, course.getDescription()); // Description
            statement.setInt(6, course.getStatus());         // Status (1 for active, 0 for inactive)

            // Execute the insertion and check if it was successful
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                // Retrieve the generated course ID
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int courseId = generatedKeys.getInt(1);

                        // Insert the thumbnail URLs for the course
                        insertCourseThumbnails(courseId, course.getThumbnailUrls(), course.getThumbnailDescriptions());

                        // Return the new course ID
                        return courseId;
                    }
                }
            }
        }
        // Return -1 if insertion failed
        return -1;
    }

// Method to insert thumbnail URLs into the Course_Thumbnails table
    public void insertCourseThumbnails(int courseId, List<String> thumbnailUrls, List<String> descriptions) throws SQLException {
        if (thumbnailUrls == null || thumbnailUrls.isEmpty() || descriptions == null || descriptions.size() != thumbnailUrls.size()) {
            return; // No thumbnails to insert, or mismatched URLs and descriptions
        }

        String sql = "INSERT INTO Course_Thumbnails (course_id, thumbnail_url, description) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < thumbnailUrls.size(); i++) {
                statement.setInt(1, courseId);
                statement.setString(2, thumbnailUrls.get(i));
                statement.setString(3, descriptions.get(i)); // Set description
                statement.addBatch(); // Add to batch for batch execution
            }
            statement.executeBatch(); // Execute batch insertion
        }
    }

    public List<Integer> getTaglineIdsByCourseId(int courseId) {
        List<Integer> taglineIds = new ArrayList<>();
        String sql = "SELECT tagline_id FROM Course_Tagline WHERE course_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                taglineIds.add(resultSet.getInt("tagline_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
        return taglineIds;
    }

    // Method to get thumbnail URLs for a specific course
    public List<String> getThumbnailUrlsByCourseId(int courseId) throws SQLException {
        List<String> thumbnailUrls = new ArrayList<>();
        String sql = "SELECT thumbnail_url FROM Course_Thumbnails WHERE course_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, courseId);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            thumbnailUrls.add(resultSet.getString("thumbnail_url"));
        }
        return thumbnailUrls;
    }

    public List<String> getAllNameCourseByCategory() throws SQLException {
        List<String> allNameSubject = new ArrayList<>();
        String sql = "SELECT title FROM Course WHERE category_id = 7";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            allNameSubject.add(resultSet.getString("title"));
        }
        return allNameSubject;
    }

    //VuLH
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
            pre.setInt(4, (int) obj.getTotalDuration());
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
