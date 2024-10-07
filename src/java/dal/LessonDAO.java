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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import dal.DBContext;
import java.sql.SQLException;
import model.Lesson;

/**
 *
 * @author AnhVuNAV
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
    public List<Lesson> getLessonsByChapterId(int chapterId){
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
}
