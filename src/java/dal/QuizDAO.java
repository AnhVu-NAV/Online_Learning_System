/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class QuizDAO extends DBContext {

    public int getIdByRequest(String request) {
        int id = 0;
        String sql = "select quiz.id from Quiz quiz " + request;
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = pre.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getInt("id");
                }
            }
        } catch (Exception e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return id;
    }
}
