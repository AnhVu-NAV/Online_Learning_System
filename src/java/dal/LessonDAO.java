
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import model.Lesson;
import model.LearningMaterial;
import model.Setting;
import java.sql.Statement;

public class LessonDAO extends DBContext {

    public LessonDAO() {
        super(); // Khởi tạo kết nối từ DBContext
    }

    // Lấy danh sách bài học có phân trang
    public List<Lesson> getPaginatedLessons(int offset, int noOfRecords, String searchName, String searchType, String searchStatus) {
        List<Lesson> lessons = new ArrayList<>();
        StringBuilder query = new StringBuilder(
                "SELECT l.id AS lesson_id, l.title AS lesson_title, l.chapter_id, l.order, l.status, "
                + "st.value AS lesson_type_value "
                + "FROM learnik.lesson l "
                + "JOIN learnik.setting st ON l.lesson_type_id = st.id "
                + "WHERE 1=1 ");

        // Thêm điều kiện tìm kiếm vào câu truy vấn nếu có
        if (searchName != null && !searchName.isEmpty()) {
            query.append("AND l.title LIKE ? ");
        }
        if (searchType != null && !searchType.isEmpty()) {
            query.append("AND st.value = ? ");
        }
        if (searchStatus != null && !searchStatus.isEmpty()) {
            query.append("AND l.status = ? ");
        }

        query.append("ORDER BY l.id DESC LIMIT ?, ?");

        try (PreparedStatement pstmt = connection.prepareStatement(query.toString())) {
            int paramIndex = 1;
            if (searchName != null && !searchName.isEmpty()) {
                pstmt.setString(paramIndex++, "%" + searchName + "%");
            }
            if (searchType != null && !searchType.isEmpty()) {
                pstmt.setString(paramIndex++, searchType);
            }
            if (searchStatus != null && !searchStatus.isEmpty()) {
                pstmt.setInt(paramIndex++, Integer.parseInt(searchStatus)); // Chuyển đổi searchStatus sang kiểu số nguyên
            }
            pstmt.setInt(paramIndex++, offset);
            pstmt.setInt(paramIndex, noOfRecords);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Setting lessonType = Setting.builder()
                        .value(rs.getString("lesson_type_value"))
                        .build();

                Lesson lesson = Lesson.builder()
                        .lessonId(rs.getInt("lesson_id"))
                        .title(rs.getString("lesson_title"))
                        .status(rs.getInt("status"))
                        .order(rs.getInt("order"))
                        .lessonTypeId(lessonType)
                        .learningMaterials(getLearningMaterialsByLessonId(rs.getInt("lesson_id")))
                        .build();

                lessons.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lessons;
    }

    // Lấy danh sách LearningMaterial cho một bài học
    private Vector<LearningMaterial> getLearningMaterialsByLessonId(int lessonId) {
        Vector<LearningMaterial> materials = new Vector<>();
        String query = "SELECT lm.title, vc.video_url, lm.updated_date FROM learningmaterial lm "
                + "LEFT JOIN videocontent vc ON lm.lesson_id = vc.lesson_id WHERE lm.lesson_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, lessonId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                LearningMaterial material = LearningMaterial.builder()
                        .lessonId(lessonId)
                        .title(rs.getString("title"))
                        .updatedDate(rs.getDate("updated_date"))
                        .videoUrl(rs.getString("video_url")) // Gán video URL
                        .build();

                materials.add(material);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materials;
    }

    // Phương thức đếm số lượng bản ghi dựa trên tìm kiếm và trạng thái
    public int getNoOfRecords(String searchName, String searchType, String searchStatus) {
        int noOfRecords = 0;
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM learnik.lesson l "
                + "JOIN learnik.setting st ON l.lesson_type_id = st.id WHERE 1=1 ");

        // Thêm điều kiện tìm kiếm nếu có
        if (searchName != null && !searchName.isEmpty()) {
            query.append("AND l.title LIKE ? ");
        }
        if (searchType != null && !searchType.isEmpty()) {
            query.append("AND st.value = ? ");
        }
        if (searchStatus != null && !searchStatus.isEmpty()) {
            query.append("AND l.status = ? ");
        }

        try (PreparedStatement pstmt = connection.prepareStatement(query.toString())) {
            int paramIndex = 1;
            if (searchName != null && !searchName.isEmpty()) {
                pstmt.setString(paramIndex++, "%" + searchName + "%");
            }
            if (searchType != null && !searchType.isEmpty()) {
                pstmt.setString(paramIndex++, searchType);
            }
            if (searchStatus != null && !searchStatus.isEmpty()) {
                pstmt.setInt(paramIndex, Integer.parseInt(searchStatus)); // Chuyển đổi searchStatus thành số nguyên
            }

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                noOfRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return noOfRecords;
    }

    // Lấy thông tin bài học theo ID
    public Lesson getLessonById(int lessonId) {
        Lesson lesson = null;
        String query = "SELECT l.id AS lesson_id, l.title, l.order, l.status, "
                + "st.value AS lesson_type_value, "
                + "vc.video_url, tc.text_content "
                + "FROM lesson l "
                + "JOIN setting st ON l.lesson_type_id = st.id "
                + "LEFT JOIN LearningMaterial lm ON l.id = lm.lesson_id "
                + "LEFT JOIN VideoContent vc ON lm.lesson_id = vc.lesson_id "
                + "LEFT JOIN TextContent tc ON lm.lesson_id = tc.lesson_id "
                + "WHERE l.id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, lessonId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Setting lessonType = new Setting();
                lessonType.setValue(rs.getString("lesson_type_value"));

                LearningMaterial material = new LearningMaterial();
                material.setVideoUrl(rs.getString("video_url"));
                material.setHtmlContent(rs.getString("text_content"));

                Vector<LearningMaterial> materials = new Vector<>();
                materials.add(material);

                lesson = new Lesson();
                lesson.setLessonId(rs.getInt("lesson_id"));
                lesson.setTitle(rs.getString("title"));
                lesson.setOrder(rs.getInt("order"));
                lesson.setStatus(rs.getInt("status"));
                lesson.setLessonTypeId(lessonType);
                lesson.setLearningMaterials(materials);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lesson;
    }

    public List<String> getAllLessonTypes() {
        List<String> lessonTypes = new ArrayList<>();
        String query = "SELECT DISTINCT st.value "
                + "FROM setting st "
                + "JOIN lesson l ON l.lesson_type_id = st.id";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                lessonTypes.add(rs.getString("value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessonTypes;
    }

    // Phương thức cập nhật trạng thái của bài học
    public void updateLessonStatus(int lessonId, int newStatus) {
        String query = "UPDATE learnik.lesson SET status = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, newStatus);
            pstmt.setInt(2, lessonId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Tạo bài học với LearningMaterial
    public void createLearningMaterialLesson(String title, int chapterId, int order, String videoLink, String htmlContent) throws SQLException {
        try {
            connection.setAutoCommit(false);

            // Sử dụng backticks để bao quanh `order`
            String lessonQuery = "INSERT INTO lesson (title, chapter_id, lesson_type_id, `order`, status) VALUES (?, ?, 4, ?, 1)";
            PreparedStatement lessonStmt = connection.prepareStatement(lessonQuery, Statement.RETURN_GENERATED_KEYS);
            lessonStmt.setString(1, title);
            lessonStmt.setInt(2, chapterId);
            lessonStmt.setInt(3, order);
            lessonStmt.executeUpdate();

            ResultSet rs = lessonStmt.getGeneratedKeys();
            if (rs.next()) {
                int lessonId = rs.getInt(1);

                // Thêm vào LearningMaterial
                String learningMaterialQuery = "INSERT INTO LearningMaterial (lesson_id) VALUES (?)";
                PreparedStatement lmStmt = connection.prepareStatement(learningMaterialQuery);
                lmStmt.setInt(1, lessonId);
                lmStmt.executeUpdate();

                // Thêm video link
                if (videoLink != null && !videoLink.isEmpty()) {
                    String videoQuery = "INSERT INTO VideoContent (lesson_id, video_url) VALUES (?, ?)";
                    PreparedStatement videoStmt = connection.prepareStatement(videoQuery);
                    videoStmt.setInt(1, lessonId);
                    videoStmt.setString(2, videoLink);
                    videoStmt.executeUpdate();
                }

                // Thêm HTML content
                if (htmlContent != null && !htmlContent.isEmpty()) {
                    String textQuery = "INSERT INTO TextContent (lesson_id, text_content) VALUES (?, ?)";
                    PreparedStatement textStmt = connection.prepareStatement(textQuery);
                    textStmt.setInt(1, lessonId);
                    textStmt.setString(2, htmlContent);
                    textStmt.executeUpdate();
                }
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

// Tạo bài học với Quiz
    public void createQuizLesson(String title, int chapterId, int order) throws SQLException {
        try {
            connection.setAutoCommit(false);

            // Sử dụng backticks để bao quanh `order`
            String lessonQuery = "INSERT INTO lesson (title, chapter_id, lesson_type_id, `order`, status) VALUES (?, ?, 5, ?, 1)";
            PreparedStatement lessonStmt = connection.prepareStatement(lessonQuery, Statement.RETURN_GENERATED_KEYS);
            lessonStmt.setString(1, title);
            lessonStmt.setInt(2, chapterId);
            lessonStmt.setInt(3, order);
            lessonStmt.executeUpdate();

            ResultSet rs = lessonStmt.getGeneratedKeys();
            if (rs.next()) {
                int lessonId = rs.getInt(1);

                // Thêm vào Quiz
                String quizQuery = "INSERT INTO quiz (lesson_id) VALUES (?)";
                PreparedStatement quizStmt = connection.prepareStatement(quizQuery);
                quizStmt.setInt(1, lessonId);
                quizStmt.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    public List<Lesson> getLessonsWithVideoUrl() {
        List<Lesson> lessons = new ArrayList<>();
        String query = "SELECT l.id AS lesson_id, l.title AS lesson_title, l.status, l.order, "
                + "vc.video_url AS video_url "
                + "FROM lesson l "
                + "JOIN learningmaterial lm ON l.lesson_type_id = lm.lesson_id "
                + "JOIN videocontent vc ON lm.lesson_id = vc.lesson_id "
                + "WHERE l.lesson_type_id = 4";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Lesson lesson = new Lesson();
                lesson.setLessonId(rs.getInt("lesson_id"));
                lesson.setTitle(rs.getString("lesson_title"));
                lesson.setStatus(rs.getInt("status"));
                lesson.setOrder(rs.getInt("order"));

                // Gán giá trị video_url vào trong learningMaterials nếu cần
                LearningMaterial lm = new LearningMaterial();
                lm.setVideoUrl(rs.getString("video_url"));
                lesson.setLearningMaterials(new Vector<>(List.of(lm))); // Thêm LearningMaterial vào Lesson

                lessons.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

    public void updateLesson(int lessonId, String title, String type, int chapterId, int order, String videoLink, String htmlContent) throws SQLException {
        String updateLessonQuery = "UPDATE lesson SET title = ?, lesson_type_id = ?, chapter_id = ?, `order` = ? WHERE id = ?";
        System.out.println("Updating lesson with ID: " + lessonId); // kiểm tra id

        try (PreparedStatement pstmt = connection.prepareStatement(updateLessonQuery)) {
            // Cập nhật thông tin cơ bản của lesson
            pstmt.setString(1, title);
            pstmt.setInt(2, getLessonTypeId(type)); // Lấy ID của type từ bảng setting
            pstmt.setInt(3, chapterId);
            pstmt.setInt(4, order);
            pstmt.setInt(5, lessonId); // Thêm lessonId vào câu truy vấn
            pstmt.executeUpdate();

            // Cập nhật thông tin liên quan đến LearningMaterial nếu type là "LearningMaterial"
            if ("LearningMaterial".equals(type)) {
                updateLearningMaterial(lessonId, videoLink, htmlContent);
            }
            System.out.println("Lesson updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating lesson: " + e.getMessage());
            throw e; // Ném lỗi để servlet bắt được và hiển thị
        }
    }

    private int getLessonTypeId(String type) throws SQLException {
        String query = "SELECT id FROM setting WHERE value = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, type);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        throw new SQLException("Type not found in setting table: " + type); // Thông báo chi tiết hơn
    }

    private void updateLearningMaterial(int lessonId, String videoLink, String htmlContent) throws SQLException {
        String updateVideoQuery = "UPDATE VideoContent SET video_url = ? WHERE lesson_id = ?";
        String updateTextContentQuery = "UPDATE TextContent SET text_content = ? WHERE lesson_id = ?";

        // Cập nhật video content nếu có video link
        try (PreparedStatement videoStmt = connection.prepareStatement(updateVideoQuery)) {
            videoStmt.setString(1, videoLink);
            videoStmt.setInt(2, lessonId);
            videoStmt.executeUpdate();
        }

        // Cập nhật HTML content nếu có htmlContent
        try (PreparedStatement textStmt = connection.prepareStatement(updateTextContentQuery)) {
            textStmt.setString(1, htmlContent);
            textStmt.setInt(2, lessonId);
            textStmt.executeUpdate();
        }
    }

}
