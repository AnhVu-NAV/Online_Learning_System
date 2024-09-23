/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.SettingType;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class SettingTypeDAO extends DBContext {

    public SettingType getSettingTypeById(int id) throws Exception {
        SettingType st = new SettingType();
        String sql = "select * from SettingType\n"
                + "where id = ?";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            st.setId(rs.getInt("id"));
            st.setName(rs.getString("name"));
            return st;
        }
        rs.close();
        pre.close();
        return null;
    }

    public SettingType getSettingTypeByName(String string) throws Exception {
        SettingType st = new SettingType();
        String sql = "select * from SettingType\n"
                + "where name = ?";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, string);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            st.setId(rs.getInt("id"));
            st.setName(rs.getString("name"));
            return st;
        }
        rs.close();
        pre.close();
        return null;
    }

    public void insert(String name) throws Exception {
        String sql = "insert into SettingType (name)\n"
                + "values (?);";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, name);
        pre.executeUpdate();
        pre.close();
    }

    public static void main(String[] args) {
        SettingTypeDAO stdao = new SettingTypeDAO();
        try {
            stdao.insert("haha");
            System.out.println(stdao.getSettingTypeByName("haha").toString());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
