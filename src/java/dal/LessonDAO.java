
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
import java.sql.Statement;
import model.TextContent;
import model.VideoContent;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import model.LearningMaterial;
import model.Setting;

/**
 *
 * @author AnhVuNAV
 */
public class LessonDAO extends DBContext {

    //TuanNA
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
        String query = "SELECT l.*, st.value AS lesson_type_value FROM Lesson l "
                + "JOIN Setting st ON l.lesson_type_id = st.id "
                + "WHERE l.id = ?";

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

                // Lấy giá trị lesson_type_value nếu cần cho hiển thị thêm thông tin
                String lessonTypeValue = rs.getString("lesson_type_value");
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

    //HuyLVN
    // Lấy danh sách bài học có phân trang
    public Map<Lesson, String> getPaginatedLessonsWithTypeValue(int offset, int noOfRecords, String searchName, String searchType, String searchStatus) {
        Map<Lesson, String> lessonsWithTypeValue = new HashMap<>();
        StringBuilder query = new StringBuilder(
                "SELECT l.id AS id, l.title AS lesson_title, l.chapter_id, l.order, l.status, "
                + "st.id AS lesson_type_id, st.value AS lesson_type_value "
                + "FROM learnik.lesson l "
                + "JOIN learnik.setting st ON l.lesson_type_id = st.id "
                + "WHERE 1=1 ");

        if (searchName != null && !searchName.isEmpty()) {
            query.append("AND l.title LIKE ? ");
        }
        if (searchType != null && !searchType.isEmpty()) {
            query.append("AND st.value = ? ");
        }
        if (searchStatus != null && !searchStatus.isEmpty()) {
            query.append("AND l.status = ? ");
        }

        // Sắp xếp theo order, chapter_id và id như yêu cầu
        query.append("ORDER BY l.order ASC, l.chapter_id ASC, l.id ASC LIMIT ?, ?");

        try (PreparedStatement pstmt = connection.prepareStatement(query.toString())) {
            int paramIndex = 1;
            if (searchName != null && !searchName.isEmpty()) {
                pstmt.setString(paramIndex++, "%" + searchName + "%");
            }
            if (searchType != null && !searchType.isEmpty()) {
                pstmt.setString(paramIndex++, searchType);
            }
            if (searchStatus != null && !searchStatus.isEmpty()) {
                pstmt.setInt(paramIndex++, Integer.parseInt(searchStatus));
            }
            pstmt.setInt(paramIndex++, offset);
            pstmt.setInt(paramIndex, noOfRecords);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int lessonTypeId = rs.getInt("lesson_type_id");
                String lessonTypeValue = rs.getString("lesson_type_value");

                Lesson lesson = Lesson.builder()
                        .id(rs.getInt("id"))
                        .title(rs.getString("lesson_title"))
                        .status(rs.getInt("status"))
                        .order(rs.getInt("order"))
                        .lessonTypeId(lessonTypeId)
                        .learningMaterials(getLearningMaterialsByLessonId(rs.getInt("id")))
                        .build();

                lessonsWithTypeValue.put(lesson, lessonTypeValue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lessonsWithTypeValue;
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
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM lesson l "
                + "JOIN setting st ON l.lesson_type_id = st.id WHERE 1=1 ");

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
                pstmt.setInt(paramIndex, Integer.parseInt(searchStatus));
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
    public Lesson getLessonByID(int lessonId) {
        Lesson lesson = null;
        String query = "SELECT l.id AS lesson_id, l.title, l.order, l.status, "
                + "st.id AS lesson_type_id, " // Lấy ID của loại bài học thay vì value
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
                // Tạo LearningMaterial với video_url và html_content nếu tồn tại
                LearningMaterial material = new LearningMaterial();
                material.setVideoUrl(rs.getString("video_url"));
                material.setHtmlContent(rs.getString("text_content"));

                Vector<LearningMaterial> materials = new Vector<>();
                materials.add(material);

                // Tạo đối tượng Lesson và thiết lập các thuộc tính
                lesson = new Lesson();
                lesson.setLessonId(rs.getInt("lesson_id"));
                lesson.setTitle(rs.getString("title"));
                lesson.setOrder(rs.getInt("order"));
                lesson.setStatus(rs.getInt("status"));
                lesson.setLessonTypeId(rs.getInt("lesson_type_id")); // Gán ID loại bài học
                lesson.setLearningMaterials(materials); // Gán các tài liệu học
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
        if (videoLink == null) {
            videoLink = ""; // Đặt giá trị mặc định nếu videoLink là null
        }
        if (htmlContent == null) {
            htmlContent = ""; // Đặt giá trị mặc định nếu htmlContent là null
        }
        try {
            connection.setAutoCommit(false);
            System.out.println("Inserting new lesson...");

            String lessonQuery = "INSERT INTO lesson (title, chapter_id, lesson_type_id, `order`, status) VALUES (?, ?, 1, ?, 1)";
            PreparedStatement lessonStmt = connection.prepareStatement(lessonQuery, Statement.RETURN_GENERATED_KEYS);
            lessonStmt.setString(1, title);
            lessonStmt.setInt(2, chapterId);
            lessonStmt.setInt(3, order);

            int rowsAffected = lessonStmt.executeUpdate();
            System.out.println("Rows affected by lesson insert: " + rowsAffected);

            if (rowsAffected == 0) {
                System.out.println("Lesson insertion failed, no rows affected.");
                connection.rollback();
                return;
            }

            ResultSet rs = lessonStmt.getGeneratedKeys();
            if (rs.next()) {
                int lessonId = rs.getInt(1);
                System.out.println("Generated lesson ID: " + lessonId);

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
            } else {
                System.out.println("Failed to retrieve generated lesson ID.");
            }

            connection.commit();
            System.out.println("Lesson committed successfully.");
        } catch (SQLException e) {
            System.out.println("Error during lesson creation: " + e.getMessage());
            connection.rollback();
            throw e;
        }
    }

// Tạo bài học với Quiz
    public void createQuizLesson(String title, int chapterId, int order) throws SQLException {
        try {
            connection.setAutoCommit(false);

            // Thêm bài học vào bảng `lesson`
            String lessonQuery = "INSERT INTO lesson (title, chapter_id, lesson_type_id, `order`, status) VALUES (?, ?, 2, ?, 1)";
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
                + "st.value AS lesson_type_value, vc.video_url AS video_url "
                + "FROM lesson l "
                + "JOIN setting st ON l.lesson_type_id = st.id "
                + "JOIN learningmaterial lm ON l.id = lm.lesson_id "
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

                // Lấy `lesson_type_value` nếu cần
                String lessonTypeValue = rs.getString("lesson_type_value");

                // Gán giá trị video_url vào trong learningMaterials nếu cần
                LearningMaterial lm = new LearningMaterial();
                lm.setVideoUrl(rs.getString("video_url"));
                lesson.setLearningMaterials(new Vector<>(List.of(lm)));

                lessons.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

    public void UpdateLesson(int lessonId, String title, int lessonTypeId, int chapterId, int order, String videoLink, String htmlContent) throws SQLException {
        String updateLessonQuery = "UPDATE lesson SET title = ?, lesson_type_id = ?, chapter_id = ?, `order` = ? WHERE id = ?";
        System.out.println("Updating lesson with ID: " + lessonId);

        try (PreparedStatement pstmt = connection.prepareStatement(updateLessonQuery)) {
            // Cập nhật thông tin cơ bản của lesson
            pstmt.setString(1, title);
            pstmt.setInt(2, lessonTypeId); // Đảm bảo lessonTypeId được truyền dưới dạng int
            pstmt.setInt(3, chapterId);
            pstmt.setInt(4, order);
            pstmt.setInt(5, lessonId); // Thêm lessonId vào câu truy vấn
            pstmt.executeUpdate();

            // Cập nhật thông tin liên quan đến LearningMaterial nếu type là "LearningMaterial"
            if (lessonTypeId == 1) { // 1 tương ứng với LearningMaterial
                UpdateLearningMaterial(lessonId, videoLink, htmlContent);
            }
            System.out.println("Lesson updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating lesson: " + e.getMessage());
            throw e;
        }
    }

    private int getLessonByTypeId(String type) throws SQLException {
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

    private void UpdateLearningMaterial(int lessonId, String videoLink, String htmlContent) throws SQLException {
        // Cập nhật video content nếu `videoLink` không rỗng
        if (videoLink != null && !videoLink.isEmpty()) {
            String updateVideoQuery = "UPDATE VideoContent SET video_url = ? WHERE lesson_id = ?";
            try (PreparedStatement videoStmt = connection.prepareStatement(updateVideoQuery)) {
                videoStmt.setString(1, videoLink);
                videoStmt.setInt(2, lessonId);
                videoStmt.executeUpdate();
            }
        }

        // Cập nhật HTML content nếu `htmlContent` không rỗng
        if (htmlContent != null && !htmlContent.isEmpty()) {
            String updateTextContentQuery = "UPDATE TextContent SET text_content = ? WHERE lesson_id = ?";
            try (PreparedStatement textStmt = connection.prepareStatement(updateTextContentQuery)) {
                textStmt.setString(1, htmlContent);
                textStmt.setInt(2, lessonId);
                textStmt.executeUpdate();
            }
        }
    }
}
