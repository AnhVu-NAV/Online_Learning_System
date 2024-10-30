/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Setting;

/**
 *
 * @author AnhVuNAV
 */
public class SettingDAO extends DBContext{
    // Lấy tất cả các setting theo loại setting (ví dụ: course category, user role)
    public List<Setting> getSettingsByType(int settingTypeId) throws SQLException {
        List<Setting> settingsList = new ArrayList<>();
        String sql = "SELECT * FROM Setting WHERE setting_type_id = ? AND status = 1"; // Chỉ lấy các setting đang hoạt động

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, settingTypeId);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Setting setting = new Setting(
                resultSet.getInt("id"),
                resultSet.getInt("setting_type_id"),
                resultSet.getString("value"),
                resultSet.getInt("status"),
                resultSet.getString("description")
            );
            settingsList.add(setting);
        }

        return settingsList;
    }

    // Lấy setting theo ID
    public Setting getSettingById(int id) throws SQLException {
        String sql = "SELECT * FROM Setting WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Setting(
                resultSet.getInt("id"),
                resultSet.getInt("setting_type_id"),
                resultSet.getString("value"),
                resultSet.getInt("status"),
                resultSet.getString("description")
            );
        }
        return null;
    }
    
    // Phương thức lấy tất cả các danh mục khóa học
    public List<Setting> getAllCategories() throws SQLException {
        List<Setting> categoriesList = new ArrayList<>();
        String sql = "SELECT * FROM Setting WHERE setting_type_id = 2 AND status = 1"; // 2 là Course Category và chỉ lấy những cái đang hoạt động

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Setting category = new Setting(
                resultSet.getInt("id"),
                resultSet.getInt("setting_type_id"),
                resultSet.getString("value"),
                resultSet.getInt("status"),
                resultSet.getString("description")
            );
            categoriesList.add(category);
        }

        return categoriesList; // Trả về danh sách các danh mục
    }
}
