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
import model.VideoContent;

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

    public static void main(String[] args) {
        LessonDAO ldao = new LessonDAO();
        List<Lesson> lessons = new ArrayList<>();
        lessons = ldao.getLessonsByChapterId(57);
        System.out.println(lessons);
 
    }
}
