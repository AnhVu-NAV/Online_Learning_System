/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Setting;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import util.DataConvert;

/**
 *
 * @author 84941
 */
public class SettingDAO extends DBContext {

    //VuNA
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
    public Setting getSettingByID(int id) throws SQLException {
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

    //
    DataConvert dc = new DataConvert();

    //insert new setting into database. Attribute id is AUTO_INCREMENT 
    public int insertSetting(Setting obj) {
        int n = 0;
        String sql = "INSERT INTO Setting\n"
                + "           (setting_type_id\n"
                + "           ,value\n"
                + "           ,status\n"
                + "           ,description\n"
                + "           ,created_date\n"
                + "           ,updated_date)\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, obj.getSettingTypeId());
            pre.setString(2, obj.getValue());
            pre.setInt(3, obj.getStatus());
            pre.setString(4, obj.getDescription());
            pre.setDate(5, dc.UtilDateToSqlDate(obj.getCreatedDate()));
            pre.setDate(6, dc.UtilDateToSqlDate(obj.getUpdatedDate()));
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;

    }

    // get an setting list based on specific conditions
    public Vector<Setting> getSettings(String sql) {
        Vector<Setting> vector = new Vector<Setting>();
        try {
            Statement state = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                int settingTypeId = rs.getInt(2);
                String value = rs.getString(3);
                int status = rs.getInt(4);
                String description = rs.getString(5);
                Date createdDate = rs.getDate(6);
                Date updatedDate = rs.getDate(7);
                Setting obj = new Setting(id, settingTypeId, value, status, description, createdDate, updatedDate);
                vector.add(obj);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;

    }

    // update setting information based on id
    public int updateSetting(Setting obj) {
        int n = 0;
        String sql = "UPDATE Setting\n"
                + "   SET setting_type_id = ?\n"
                + "      ,value = ?\n"
                + "      ,status = ?\n"
                + "      ,description = ?\n"
                + "      ,created_date = ?\n"
                + "      ,updated_date = ?\n"
                + " WHERE id = ?";
        try {

            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, obj.getSettingTypeId());
            pre.setString(2, obj.getValue());
            pre.setInt(3, obj.getStatus());
            pre.setString(4, obj.getDescription());
            pre.setDate(5, dc.UtilDateToSqlDate(obj.getCreatedDate()));
            pre.setDate(6, dc.UtilDateToSqlDate(obj.getUpdatedDate()));
            pre.setInt(7, obj.getId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Setting getSettingById(int searchId) {
        Vector<Setting> vector = new Vector<>();
        String sql = "SELECT id"
                + "      ,setting_type_id"
                + "      ,value"
                + "      ,status"
                + "      ,description"
                + "      ,created_date"
                + "      ,updated_date"
                + "  FROM Setting"
                + "  WHERE Setting.id = " + searchId;

        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                int settingTypeId = rs.getInt(2);
                String value = rs.getString(3);
                int status = rs.getInt(4);
                String description = rs.getString(5);
                Date createdDate = rs.getDate(6);
                Date updatedDate = rs.getDate(7);
                Setting obj = new Setting(id, settingTypeId, value, status, description, createdDate, updatedDate);
                return obj;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public Setting getSettingBySettingTypeIdAndValue(int searchSettingTypeId, String searchValue) {
        Vector<Setting> vector = new Vector<>();
        String sql = "SELECT id"
                + "      ,setting_type_id"
                + "      ,value"
                + "      ,status"
                + "      ,description"
                + "      ,created_date"
                + "      ,updated_date"
                + "  FROM Setting"
                + "  WHERE Setting.setting_type_id = " + searchSettingTypeId + " and Setting.value like '" + searchValue + "'";

        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                int settingTypeId = rs.getInt(2);
                String value = rs.getString(3);
                int status = rs.getInt(4);
                String description = rs.getString(5);
                Date createdDate = rs.getDate(6);
                Date updatedDate = rs.getDate(7);
                Setting obj = new Setting(id, settingTypeId, value, status, description, createdDate, updatedDate);
                return obj;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;

    }
}
