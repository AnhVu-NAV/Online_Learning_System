/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import model.SettingType;
import utility.DataConvert;

/**
 *
 * @author 84941
 */
public class SettingTypeDAO extends DBContext{
    DataConvert dc = new DataConvert();
    //insert new setting type into database. Attribute id is AUTO_INCREMENT 
    public int insertSetting(SettingType obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[SettingTypes]\n"
                + "           ([name]\n"
                + "     VALUES\n"
                + "           (?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, obj.getName());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;

    }
    // get an setting type list based on specific conditions
    public Vector<SettingType> getSettingsType(String sql) {
        Vector<SettingType> vector = new Vector<SettingType>();
        try {
            Statement state = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                
                SettingType obj = new SettingType(id, name);
                vector.add(obj);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
        
    }
    // get all the setting types in the database
    public Vector<SettingType> getAll() {
        Vector<SettingType> vector = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "  FROM [dbo].[SettingsTypes]";  

        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                SettingType obj = new SettingType(id, name);
                vector.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    // update Setting information based on id
    public int updateSetting(SettingType obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[SettingsTypes]\n"
                + "   SET [name] = ?\n"           
                + " WHERE [id] = ?";
        try {

            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, obj.getName());
            pre.setInt(2, obj.getId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
}
