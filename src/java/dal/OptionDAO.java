/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Option;

/**
 *
 * @author Admin
 */
public class OptionDAO extends DBContext {

    private static final Logger logger = Logger.getLogger(QuestionDAO.class.getName());

    //Lấy câu trả lời dựa trên Id
    public List<Option> getOptionsByQuestionId(int questionId) {
        List<Option> options = new ArrayList<>();
        String query = "SELECT * FROM `Option` WHERE question_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, questionId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                options.add(new Option(
                        resultSet.getInt("id"),
                        resultSet.getInt("question_id"),
                        resultSet.getInt("status"),
                        resultSet.getInt("isTrue"),
                        resultSet.getString("explaination"),
                        resultSet.getString("content")
                ));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching options for question ID: " + questionId, e);
        }
        return options;
    }

    // Lấy một tùy chọn cụ thể theo ID
    public Option getOptionById(int optionId) {
        String query = "SELECT * FROM `Option` WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, optionId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Option(
                        resultSet.getInt("id"),
                        resultSet.getInt("question_id"),
                        resultSet.getInt("status"),
                        resultSet.getInt("isTrue"),
                        resultSet.getString("explaination"),
                        resultSet.getString("content")
                );
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching option with ID: " + optionId, e);
        }
        return null;
    }

}
