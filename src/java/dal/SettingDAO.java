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
import java.util.Date;
import java.util.Vector;
import util.DataConvert;
/**
 *
 * @author 84941
 */
public class SettingDAO extends DBContext{
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
}
