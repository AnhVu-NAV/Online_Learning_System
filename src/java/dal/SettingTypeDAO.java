/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import model.SettingType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
/**
 *
 * @author 84941
 */
public class SettingTypeDAO extends DBContext{
    // get an setting type list based on specific conditions
    public Vector<SettingType> getSettingTypes(String sql) {
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
    public SettingType getSettingTypeByName(String search_name) {
        Vector<SettingType> vector = new Vector<>();
        String sql = "SELECT id"
                + "      ,name"
                + "  FROM SettingType"
                + "  WHERE SettingType.name like '" + search_name+"'";

        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                SettingType obj = new SettingType(id, name);
                return obj;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;

    }
}
