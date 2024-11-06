/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Tagline;

/**
 *
 * @author AnhVuNAV
 */
public class TaglineDAO extends DBContext {

    //VuNA
    // Lấy tất cả các tagline
    public List<Tagline> getAllTaglines() throws SQLException {
        List<Tagline> taglinesList = new ArrayList<>();
        String sql = "SELECT * FROM Tagline";

        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Tagline tagline = new Tagline(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
            );
            taglinesList.add(tagline);
        }

        return taglinesList;
    }

    // Lấy Tagline theo ID
    public Tagline getTaglineById(int id) throws SQLException {
        String sql = "SELECT * FROM Tagline WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Tagline(resultSet.getInt("id"), resultSet.getString("name"));
        }
        return null;
    }

    // thêm tagline mới
    public int insertTagline(String taglineName) {
        String sql = "INSERT INTO Tagline (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, taglineName);
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                // Get the generated key
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Indicate failure
    }

}
