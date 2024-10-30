/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Question;

/**
 *
 * @author Admin
 */
public class QuestionDAO extends DBContext {
    private static final Logger logger = Logger.getLogger(QuestionDAO.class.getName());
    public Question getQuestionById(int questionId) {
        String query = "SELECT * FROM Question WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, questionId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Question(
                    resultSet.getInt("id"),
                    resultSet.getInt("quiz_id"),
                    resultSet.getInt("level_id"),
                    resultSet.getInt("status"),
                    resultSet.getString("content"),
                    resultSet.getInt("question_type_id"),
                    resultSet.getString("hint")
                );
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching question by ID: " + questionId, e);
        }
        return null;
    }
}
