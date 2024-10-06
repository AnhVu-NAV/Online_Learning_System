/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class LessonDAO extends DBContext {

    public int getIdByRequest(String request) {
        int id = 0;
        String sql = "select lesson.id from Lesson lesson " + request;
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = pre.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getInt("id");
                }
            }
        } catch (Exception e) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return id;
    }
}
