/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.Chapter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author AnhVuNAV
 */
public class ChapterDAO extends DBContext {

    public List<Chapter> getChaptersByCourseId(int courseId) {
        List<Chapter> chapters = new ArrayList<>();
        String sql = "SELECT * FROM Chapter WHERE course_id = ? AND status = 1 ORDER BY `order` ASC";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Chapter chapter = new Chapter();
                chapter.setId(rs.getInt("id"));
                chapter.setOrder(rs.getInt("order"));
                chapter.setTitle(rs.getString("title"));
                chapter.setSubtitle(rs.getString("subtitle"));
                chapter.setDuration(rs.getInt("duration"));
                chapters.add(chapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chapters;
    }

}
