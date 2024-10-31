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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Quiz;
/**
 *
 * @author Admin
 */
public class QuizDAO extends DBContext {
    private static final Logger logger = Logger.getLogger(QuizDAO.class.getName());
    public List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();
        String query = "SELECT * FROM Quiz";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                quizzes.add(new Quiz(
                    resultSet.getInt("lesson_id"),
                    resultSet.getString("title"),
                    resultSet.getInt("duration"),
                    resultSet.getFloat("pass_rate"),
                    resultSet.getDate("updated_date"),
                    resultSet.getString("description"),
                    resultSet.getString("subtitle"),
                    resultSet.getInt("type")
                ));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching quizzes from database", e);
        }
        return quizzes;
    }

    // Lấy một quiz cụ thể theo ID
    public Quiz getQuizById(int quizId) {
        String query = "SELECT * FROM Quiz WHERE lesson_id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, quizId);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                return new Quiz(
                    resultSet.getInt("lesson_id"),
                    resultSet.getString("title"),
                    resultSet.getInt("duration"),
                    resultSet.getFloat("pass_rate"),
                    resultSet.getDate("updated_date"),
                    resultSet.getString("description"),
                    resultSet.getString("subtitle"),
                    resultSet.getInt("type")
                );
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching quiz with ID: " + quizId, e);
        }
        return null;
    }
    
     public int getQuizDurationById(int quizId) {
        String query = "SELECT duration FROM Quiz WHERE lesson_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, quizId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Lấy duration từ kết quả truy vấn và trả về
                return resultSet.getInt("duration"); // Trả về phút
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Trả về 0 nếu không tìm thấy hoặc có lỗi
    }
   
}
