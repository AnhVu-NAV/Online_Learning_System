/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Option;

/**
 *
 * @author ADMIN
 */
public class OptionDAO extends DBContext {

    public int getIdByQuestionId(int id) {
        int result = 0;
        String sql = "select option.id from Option option inner join Question question\n"
                + "on option.question_id = question.id\n"
                + "where question.id = ?";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, id);
            try (ResultSet resultSet = pre.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getInt("id");
                }
            }
        } catch (Exception e) {
            Logger.getLogger(OptionDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }
    
    public Option getOptionById(int id) {
        String sql = "select * from Option where id = ?";
        Option option = new Option();
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, id);
            try (ResultSet resultSet = pre.executeQuery()) {
                if (resultSet.next()) {
                    option.setId(resultSet.getInt("id"));
                    option.setQuestionId(resultSet.getInt("question_id"));
                    option.setExplanation(resultSet.getString("explaination"));
                    if (resultSet.getInt("is_true") == 0) {
                        option.setIsTrue(false);
                    } else {
                        option.setIsTrue(true); 
                    }
                }
            }
        } catch (Exception e) {
            Logger.getLogger(OptionDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return option;
    }
}
