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
import model.Chapter;

/**
 *
 * @author Admin
 */
public class ChapterDAO extends DBContext {
    private static final Logger LOGGER = Logger.getLogger(ChapterDAO.class.getName());
    
    public List<Chapter> getChaptersByCourseId(int courseId) {
        List<Chapter> chapters = new ArrayList<>();
        
        // Chỉ lấy các cột cần thiết và sắp xếp theo `order`
        String query = "SELECT * FROM Chapter WHERE course_id = ? ORDER BY `order`";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, courseId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Chỉ lấy các giá trị cần thiết
                Chapter chapter = new Chapter();
                chapter.setId(resultSet.getInt("id"));
                chapter.setOrder(resultSet.getInt("order"));
                chapter.setTitle(resultSet.getString("title"));
                chapter.setSubtitle(resultSet.getString("subtitle"));
                chapter.setBriefInformation(resultSet.getString("brief_information"));
                chapter.setDescription(resultSet.getString("description"));
                chapter.setCourseId(resultSet.getInt("course_id"));
                chapter.setStatus(resultSet.getInt("status"));
                chapter.setDuration(resultSet.getInt("duration"));
                
                // Thêm chương vào danh sách
                chapters.add(chapter);
                
            }
        } catch (SQLException e) {
            LOGGER.log(java.util.logging.Level.SEVERE, "Error retrieving chapters", e);
        }

        return chapters;
    }
    
    public static void main(String[] args) {
    }
}

