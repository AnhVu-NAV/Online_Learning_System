/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.*;
import java.sql.*;
import util.*;

/**
 *
 * @author ADMIN
 */
public class SettingDAO extends DBContext {

    private SettingTypeDAO stdao = new SettingTypeDAO();

    public Setting getSettingById(int id) throws Exception {
        Setting setting = new Setting();
        String sql = "select * from Setting\n"
                + " where id = ?";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            setting.setId(rs.getInt("id"));
            setting.setSettingTypeId(stdao.getSettingTypeById(rs.getInt("setting_type_id")));
            setting.setValue(rs.getString("value"));
            setting.setStatus(rs.getInt("status"));
            setting.setDescription(rs.getString("description"));
            setting.setCreatedDate(DateConvert.convertToUtilDate(rs.getDate("created_date")));
            setting.setUpdatedDate(DateConvert.convertToUtilDate(rs.getDate("updated_date")));
            return setting;
        }
        rs.close();
        pre.close();
        return null;
    }

    public int getMaxId() throws Exception {
        Setting setting = new Setting();
        String sql = "select max(id) from Setting";
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        rs.close();
        pre.close();
        return 0;
    }

    public void insert(Setting setting) throws Exception {
        String sql = "insert into Setting (setting_type_id, value, status, description, created_date, updated_date)\n"
                + "values (?, ?, ?, ?, ?, ?);";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setInt(1, setting.getSettingTypeId().getId());
        pre.setString(2, setting.getValue());
        pre.setInt(3, setting.getStatus());
        pre.setString(4, setting.getDescription());
        pre.setDate(5, DateConvert.convertToSQLDate(setting.getCreatedDate()));
        pre.setDate(6, DateConvert.convertToSQLDate(setting.getUpdatedDate()));
        pre.executeUpdate();
        pre.close();
    }

    public static void main(String[] args) {
        SettingDAO sdao = new SettingDAO();
        try {
            System.out.println(sdao.getMaxId());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
