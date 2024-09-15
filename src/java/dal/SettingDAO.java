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
import utility.DataConvert;
/**
 *
 * @author 84941
 */
public class SettingDAO extends DBContext{
    DataConvert dc = new DataConvert();
    //insert new setting into database. Attribute id is AUTO_INCREMENT 
    public int insertSetting(Setting obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Settings]\n"
                + "           ([setting_type_id]\n"
                + "           ,[value]\n"
                + "           ,[description]\n"
                + "           ,[status]\n"
                + "           ,[created_date]\n"
                + "           ,[updated_date]\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, obj.getSetting_type_id());
            pre.setString(2, obj.getValue());
            pre.setString(3, obj.getDescription());
            pre.setInt(4, obj.getStatus());
            pre.setDate(5, dc.UtilDateToSqlDate(obj.getCreated_date()));
            pre.setDate(6, dc.UtilDateToSqlDate(obj.getUpdated_date()));
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
                int setting_type_id = rs.getInt(2);
                String value = rs.getString(3);
                String description = rs.getString(4);
                int status = rs.getInt(5);
                Date created_date = rs.getDate(6);
                Date updated_date = rs.getDate(7);
                Setting obj = new Setting(id, setting_type_id, value, description, status, created_date, updated_date);
                vector.add(obj);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
        
    }
    // get all the settings in the database
    public Vector<Setting> getAll() {
        Vector<Setting> vector = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[setting_type_id]\n"
                + "      ,[value]\n"
                + "      ,[description]\n"
                + "      ,[status]\n"
                + "      ,[created_date]\n"
                + "      ,[updated_date]\n"
                + "  FROM [dbo].[Settings]";  

        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                int setting_type_id = rs.getInt(2);
                String value = rs.getString(3);
                String description = rs.getString(4);
                int status = rs.getInt(5);
                Date created_date = rs.getDate(6);
                Date updated_date = rs.getDate(7);
                Setting obj = new Setting(id, setting_type_id, value, description, status, created_date, updated_date);
                vector.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    // update Setting information based on id
    public int updateSetting(Setting obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[Settings]\n"
                + "   SET [setting_type_id] = ?\n"
                + "      ,[value] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[created_date] = ?\n"
                + "      ,[updated_date] = ?\n"             
                + " WHERE [id] = ?";
        try {

            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, obj.getSetting_type_id());
            pre.setString(2, obj.getValue());
            pre.setString(3, obj.getDescription());
            pre.setInt(4, obj.getStatus());
            pre.setDate(5, dc.UtilDateToSqlDate(obj.getCreated_date()));
            pre.setDate(6, dc.UtilDateToSqlDate(obj.getUpdated_date()));
            pre.setInt(7, obj.getId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

}
