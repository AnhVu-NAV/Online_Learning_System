
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
import model.Course;
import model.Lesson;
import model.LearningMaterial;
import model.Setting;

public class LessonDAO extends DBContext {

    public LessonDAO() {
        super(); // Khởi tạo kết nối từ DBContext
    }

     // Lấy danh sách bài học có phân trang
    public List<Lesson> getPaginatedLessons(int offset, int noOfRecords) {
        List<Lesson> lessons = new ArrayList<>();
        String query = "SELECT l.id AS lesson_id, l.title AS lesson_title, l.chapter_id, l.order, "
                     + "st.value AS lesson_type_value "
                     + "FROM learnik.lesson l "
                     + "JOIN learnik.setting st ON l.lesson_type_id = st.id "
                     + "ORDER BY l.id DESC "
                     + "LIMIT ?, ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, offset);
            pstmt.setInt(2, noOfRecords);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                // Tạo đối tượng Setting cho loại bài học
                Setting lessonType = Setting.builder()
                                            .value(rs.getString("lesson_type_value"))
                                            .build();

                // Tạo đối tượng Lesson
                Lesson lesson = Lesson.builder()
                        .lessonId(rs.getInt("lesson_id"))
                        .name(rs.getString("lesson_title"))
                        .order(rs.getInt("order"))
                        .lessonTypeId(lessonType) // Gán lessonType từ Setting
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
        String query = "SELECT * FROM learnik.LearningMaterial WHERE lesson_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, lessonId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                // Tạo đối tượng LearningMaterial và gán `lessonId`
                LearningMaterial material = LearningMaterial.builder()
                        .lessonId(Lesson.builder().lessonId(lessonId).build())  // Gán đối tượng `Lesson` thay vì `int`
                        .title(rs.getString("title"))
                        .updatedDate(rs.getDate("upload_date"))
                        .build();

                materials.add(material);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materials;
    }

    // Đếm số lượng bài học
    public int getNoOfRecords() {
        int noOfRecords = 0;
        String query = "SELECT COUNT(*) FROM learnik.lesson";

        try (PreparedStatement pstmt = connection.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
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
        String query = "SELECT l.id AS lesson_id, l.title AS lesson_title, l.chapter_id, l.order, "
                     + "st.value AS lesson_type_value "
                     + "FROM learnik.lesson l "
                     + "JOIN learnik.setting st ON l.lesson_type_id = st.id "
                     + "WHERE l.id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, lessonId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Tạo đối tượng Setting cho loại bài học
                Setting lessonType = Setting.builder()
                                            .value(rs.getString("lesson_type_value"))
                                            .build();

                // Tạo đối tượng Lesson
                lesson = Lesson.builder()
                        .lessonId(rs.getInt("lesson_id"))
                        .name(rs.getString("lesson_title"))
                        .order(rs.getInt("order"))
                        .lessonTypeId(lessonType) // Gán lessonType từ Setting
                        .learningMaterials(getLearningMaterialsByLessonId(rs.getInt("lesson_id")))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lesson;
    }
}


