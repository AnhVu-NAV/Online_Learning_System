package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Question;

/**
 *
 * @author Admin
 */
public class QuestionDAO extends DBContext {

    private static final Logger logger = Logger.getLogger(QuestionDAO.class.getName());

    //Lấy câu hỏi dựa trên id và thứ tự 
    public Question getQuestionByQuizAndNumber(int quizId, int questionNumber) {
        String query = "SELECT * FROM Question WHERE quiz_id = ? LIMIT ?, 1";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, quizId);
            statement.setInt(2, questionNumber - 1); // SQL LIMIT offset, nên trừ 1 để có đúng vị trí
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
            logger.log(Level.SEVERE, "Error fetching question by quizId and question number", e);
        }
        return null;
    }

    public List<Question> getQuestionByQuizId(int quizId) {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM Question WHERE quiz_id = ? ";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, quizId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Question question = new Question();
                    question.setId(rs.getInt("id"));
                    question.setQuizId(rs.getInt("quiz_id"));
                    question.setLevelId(rs.getInt("level_id"));
                    question.setStatus(rs.getInt("status"));
                    question.setContent(rs.getString("content"));
                    question.setQuestionTypeId(rs.getInt("question_type_id"));
                    question.setHint(rs.getString("hint"));
                    questions.add(question);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching question by quizId ", e);
        }
        return questions;
    }

    // Lấy số lượng câu hỏi trong một quiz
    public int getQuestionCountForQuiz(int quizId) {
        String query = "SELECT COUNT(*) FROM Question WHERE quiz_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, quizId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching question count for quizId: " + quizId, e);
        }
        return 0;
    }

}
