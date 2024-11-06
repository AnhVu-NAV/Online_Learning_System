
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Lesson;
import model.TextContent;
import model.VideoContent;
import java.sql.Connection;

/**
 *
 * @author AnhVuNAV
 */
public class LessonDAO extends DBContext {

    //TuNA
    private static final Logger LOGGER = Logger.getLogger(ChapterDAO.class.getName());

    public List<Lesson> getLessonsByChapterId(int chapterId) {
        List<Lesson> lessons = new ArrayList<>();

        String query = "SELECT * FROM Lesson WHERE chapter_id = ? ORDER BY `order`";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, chapterId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Lesson lesson = new Lesson();
                lesson.setId(resultSet.getInt("id"));
                lesson.setTitle(resultSet.getString("title"));
                lesson.setStatus(resultSet.getInt("status"));
                lesson.setLessonTypeId(resultSet.getInt("lesson_type_id"));
                lesson.setChapterId(resultSet.getInt("chapter_id"));
                lesson.setOrder(resultSet.getInt("order"));
                // Thêm bài học vào danh sách
                lessons.add(lesson);
            }
        } catch (SQLException e) {
            LOGGER.log(java.util.logging.Level.SEVERE, "Error retrieving lessons", e);
        }

        return lessons;
    }

    public Lesson getLessonById(int lessonId) {
        Lesson lesson = null;
        String query = "SELECT * FROM Lesson WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, lessonId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                lesson = new Lesson();
                lesson.setId(rs.getInt("id"));
                lesson.setTitle(rs.getString("title"));
                lesson.setStatus(rs.getInt("status"));
                lesson.setLessonTypeId(rs.getInt("lesson_type_id"));
                lesson.setChapterId(rs.getInt("chapter_id"));
                lesson.setOrder(rs.getInt("order"));
            }
        } catch (SQLException e) {
            LOGGER.log(java.util.logging.Level.SEVERE, "Error retrieving lesson", e);
        }

        return lesson;
    }

    public VideoContent getVideoContentById(int lessonId) {
        VideoContent content = null;
        String query = "SELECT * FROM VideoContent WHERE lesson_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, lessonId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                content = new VideoContent();
                content.setLessonId(rs.getInt("lesson_id"));
                content.setVideoId(rs.getString("video_id"));
                content.setListId(rs.getString("list_id"));
                content.setIndex_vid(rs.getInt("index_vid"));
                content.setVideoSummary(rs.getString("video_summary"));
                content.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            LOGGER.log(java.util.logging.Level.SEVERE, "Error retrieving content", e);
        }

        return content;
    }

    public TextContent getTextContentById(int lessonId) {
        TextContent textContent = null;
        String query = "SELECT * FROM TextContent WHERE lesson_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, lessonId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                textContent = new TextContent();
                textContent.setLessonId(resultSet.getInt("lesson_id"));
                textContent.setTextContent(resultSet.getString("text_content"));
            }
        } catch (SQLException e) {
            LOGGER.log(java.util.logging.Level.SEVERE, "Error retrieving content", e);
        }

        return textContent;
    }

    //VuNA
    public List<Lesson> getLessonsByCourseId(int courseId) {
        List<Lesson> lessons = new ArrayList<>();
        String sql = "SELECT * FROM Lesson WHERE course_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(rs.getInt("id"));
                lesson.setTitle(rs.getString("title"));
                lesson.setStatus(rs.getInt("status"));
                lesson.setLessonTypeId(rs.getInt("lesson_type_id"));
                lessons.add(lesson);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lessons;
    }

    // Lấy danh sách các bài học theo ID chương
    public List<Lesson> getLessonsByChapterID(int chapterId) {
        List<Lesson> lessons = new ArrayList<>();
        String sql = "SELECT * FROM Lesson WHERE chapter_id = ? AND status = 1 ORDER BY `order` ASC";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, chapterId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(rs.getInt("id"));
                lesson.setTitle(rs.getString("title"));
                lesson.setOrder(rs.getInt("order"));
                lesson.setLessonTypeId(rs.getInt("lesson_type_id"));
                lessons.add(lesson);
            }
        } catch (Exception e) {
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
