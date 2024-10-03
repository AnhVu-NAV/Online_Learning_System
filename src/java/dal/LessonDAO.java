/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dal.DBContext;
import model.Lesson;

/**
 *
 * @author AnhVuNAV
 */
public class LessonDAO extends DBContext{
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
}
