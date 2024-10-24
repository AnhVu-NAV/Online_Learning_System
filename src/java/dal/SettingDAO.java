///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package dal;
//
//import model.*;
//import java.sql.*;
//import util.*;
//import model.Setting;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Date;
//import java.util.Vector;
//import util.DataConvert;
//
///**
// *
// * @author AnhVuNAV
// */
//public class SettingDAO extends DBContext {
//
//    private SettingTypeDAO stdao = new SettingTypeDAO();
//
//    public Setting getSettingById(int id) throws Exception {
//        Setting setting = new Setting();
//        String sql = "select * from Setting\n"
//                + " where id = ?";
//        PreparedStatement pre = connection.prepareStatement(sql);
//        pre.setInt(1, id);
//        ResultSet rs = pre.executeQuery();
//        if (rs.next()) {
//            setting.setId(rs.getInt("id"));
//            setting.setSettingTypeId(stdao.getSettingTypeById(rs.getInt("setting_type_id")));
//            setting.setValue(rs.getString("value"));
//            setting.setStatus(rs.getInt("status"));
//            setting.setDescription(rs.getString("description"));
//            setting.setCreatedDate(DateConvert.convertToUtilDate(rs.getDate("created_date")));
//            setting.setUpdatedDate(DateConvert.convertToUtilDate(rs.getDate("updated_date")));
//            return setting;
//        }
//        rs.close();
//        pre.close();
//        return null;
//    }
//
//    public int getMaxId() throws Exception {
//        Setting setting = new Setting();
//        String sql = "select max(id) from Setting";
//        PreparedStatement pre = connection.prepareStatement(sql);
//        ResultSet rs = pre.executeQuery();
//        if (rs.next()) {
//            return rs.getInt(1);
//        }
//        rs.close();
//        pre.close();
//        return 0;
//    }
//
//    public void insert(Setting setting) throws Exception {
//        String sql = "insert into Setting (setting_type_id, value, status, description, created_date, updated_date)\n"
//                + "values (?, ?, ?, ?, ?, ?);";
//        PreparedStatement pre = connection.prepareStatement(sql);
//        pre.setInt(1, setting.getSettingTypeId().getId());
//        pre.setString(2, setting.getValue());
//        pre.setInt(3, setting.getStatus());
//        pre.setString(4, setting.getDescription());
//        pre.setDate(5, DateConvert.convertToSQLDate(setting.getCreatedDate()));
//        pre.setDate(6, DateConvert.convertToSQLDate(setting.getUpdatedDate()));
//        pre.executeUpdate();
//        pre.close();
//    }
//
//    public static void main(String[] args) {
//        SettingDAO sdao = new SettingDAO();
//        try {
//            System.out.println(sdao.getMaxId());
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
//    }
//
//    DataConvert dc = new DataConvert();
//    //insert new setting into database. Attribute id is AUTO_INCREMENT 
//    public int insertSetting(Setting obj) {
//        int n = 0;
//        String sql = "INSERT INTO Setting\n"
//                + "           (setting_type_id\n"
//                + "           ,value\n"
//                + "           ,status\n"
//                + "           ,description\n"
//                + "           ,created_date\n"
//                + "           ,updated_date)\n"
//                + "     VALUES\n"
//                + "           (?,?,?,?,?,?)";
//        try {
//            PreparedStatement pre = connection.prepareStatement(sql);
//            pre.setInt(1, obj.getSetting_type_id());
//            pre.setString(2, obj.getValue());
//            pre.setInt(3, obj.getStatus());
//            pre.setString(4, obj.getDescription());
//            pre.setDate(5, dc.UtilDateToSqlDate(obj.getCreated_date()));
//            pre.setDate(6, dc.UtilDateToSqlDate(obj.getUpdated_date()));
//            n = pre.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return n;
//
//    }
//    // get an setting list based on specific conditions
//    public Vector<Setting> getSettings(String sql) {
//        Vector<Setting> vector = new Vector<Setting>();
//        try {
//            Statement state = connection.createStatement(
//                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            ResultSet rs = state.executeQuery(sql);
//            while (rs.next()) {
//                int id = rs.getInt(1);
//                int setting_type_id = rs.getInt(2);
//                String value = rs.getString(3);
//                int status = rs.getInt(4);
//                String description = rs.getString(5);
//                Date created_date = rs.getDate(6);
//                Date updated_date = rs.getDate(7);
//                Setting obj = new Setting(id, setting_type_id, value, description, status, created_date, updated_date);
//                vector.add(obj);
//
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return vector;
//
//    }
//    // update setting information based on id
//    public int updateSetting(Setting obj) {
//        int n = 0;
//        String sql = "UPDATE Setting\n"
//                + "   SET setting_type_id = ?\n"
//                + "      ,value = ?\n"
//                + "      ,status = ?\n"
//                + "      ,description = ?\n"
//                + "      ,created_date = ?\n"
//                + "      ,updated_date = ?\n"
//                + " WHERE id = ?";
//        try {
//
//            PreparedStatement pre = connection.prepareStatement(sql);
//            pre.setInt(1, obj.getSetting_type_id());
//            pre.setString(2, obj.getValue());
//            pre.setInt(3, obj.getStatus());
//            pre.setString(4, obj.getDescription());
//            pre.setDate(5, dc.UtilDateToSqlDate(obj.getCreated_date()));
//            pre.setDate(6, dc.UtilDateToSqlDate(obj.getUpdated_date()));
//            pre.setInt(7, obj.getId());
//            n = pre.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return n;
//    }
//    public Setting getSettingById(int search_id) {
//        Vector<Setting> vector = new Vector<>();
//        String sql = "SELECT id"
//                + "      ,setting_type_id"
//                + "      ,value"
//                + "      ,status"
//                + "      ,description"
//                + "      ,created_date"
//                + "      ,updated_date"
//                + "  FROM Setting"
//                + "  WHERE Setting.id = " + search_id;
//
//        try {
//            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            ResultSet rs = state.executeQuery(sql);
//            while (rs.next()) {
//                int id = rs.getInt(1);
//                int setting_type_id = rs.getInt(2);
//                String value = rs.getString(3);
//                int status = rs.getInt(4);
//                String description = rs.getString(5);
//                Date created_date = rs.getDate(6);
//                Date updated_date = rs.getDate(7);
//                Setting obj = new Setting(id, setting_type_id, value, description, status, created_date, updated_date);
//                return obj;
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return null;
//
//    }
//}
