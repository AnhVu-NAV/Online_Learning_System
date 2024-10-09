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

/**
 *
 * @author Admin
 */
public class LessonDAO extends DBContext {

    private static final Logger LOGGER = Logger.getLogger(ChapterDAO.class.getName());

    public List<Lesson> getLessonsByChapterId(int chapterId) {
        List<Lesson> lessons = new ArrayList<>();

        
        String query = "SELECT * FROM Lesson WHERE chapter_id = ? ORDER BY `order`";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, chapterId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Lesson lesson = new Lesson();
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

    public static void main(String[] args) {
        LessonDAO lessonDAO = new LessonDAO();
        int chapterId = 57;  // Thay đổi chapterId này nếu cần

        // Lấy danh sách bài học của chương với chapterId đã chỉ định
        List<Lesson> lessons = lessonDAO.getLessonsByChapterId(chapterId);

        // Kiểm tra kết quả trả về
        if (lessons != null && !lessons.isEmpty()) {
            System.out.println("Số lượng bài học cho chapter ID " + chapterId + ": " + lessons.size());
            for (Lesson lesson : lessons) {
                System.out.println("Lesson ID: " + lesson.getId() + ", Title: " + lesson.getTitle());
            }
        } else {
            System.out.println("Không tìm thấy bài học nào cho chapter ID: " + chapterId);
        }
    }
}
