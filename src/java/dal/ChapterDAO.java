/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import model.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class ChapterDAO extends DBContext {

    public int getIdByRequest(String request) {
        String sql = "select chapter.id from Chapter chapter " + request;
        int id = 0;
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = pre.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getInt("id");
                }
            }
        } catch (Exception e) {
            Logger.getLogger(ChapterDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return id;
    }
}
