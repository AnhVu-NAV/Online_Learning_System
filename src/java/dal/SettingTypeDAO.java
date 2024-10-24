///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package dal;
//<<<<<<< HEAD
//
//import model.SettingType;
//import java.sql.*;
//
///**
// *
// * @author ADMIN
// */
//public class SettingTypeDAO extends DBContext {
//
//    public SettingType getSettingTypeById(int id) throws Exception {
//        SettingType st = new SettingType();
//        String sql = "select * from SettingType\n"
//                + "where id = ?";
//        PreparedStatement pre = connection.prepareStatement(sql);
//        pre.setInt(1, id);
//        ResultSet rs = pre.executeQuery();
//        if (rs.next()) {
//            st.setId(rs.getInt("id"));
//            st.setName(rs.getString("name"));
//            return st;
//        }
//        rs.close();
//        pre.close();
//        return null;
//    }
//
//    public SettingType getSettingTypeByName(String string) throws Exception {
//        SettingType st = new SettingType();
//        String sql = "select * from SettingType\n"
//                + "where name = ?";
//        PreparedStatement pre = connection.prepareStatement(sql);
//        pre.setString(1, string);
//        ResultSet rs = pre.executeQuery();
//        if (rs.next()) {
//            st.setId(rs.getInt("id"));
//            st.setName(rs.getString("name"));
//            return st;
//        }
//        rs.close();
//        pre.close();
//        return null;
//    }
//
//    public void insert(String name) throws Exception {
//        String sql = "insert into SettingType (name)\n"
//                + "values (?);";
//        PreparedStatement pre = connection.prepareStatement(sql);
//        pre.setString(1, name);
//        pre.executeUpdate();
//        pre.close();
//    }
//
//    public static void main(String[] args) {
//        SettingTypeDAO stdao = new SettingTypeDAO();
//        try {
//            stdao.insert("haha");
//            System.out.println(stdao.getSettingTypeByName("haha").toString());
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
//=======
//import model.SettingType;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Date;
//import java.util.Vector;
///**
// *
// * @author 84941
// */
//public class SettingTypeDAO extends DBContext{
//    // get an setting type list based on specific conditions
//    public Vector<SettingType> getSettingTypes(String sql) {
//        Vector<SettingType> vector = new Vector<SettingType>();
//        try {
//            Statement state = connection.createStatement(
//                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            ResultSet rs = state.executeQuery(sql);
//            while (rs.next()) {
//                int id = rs.getInt(1);
//                String name = rs.getString(2);
//                SettingType obj = new SettingType(id, name);
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
//    public SettingType getSettingTypeByName(String search_name) {
//        Vector<SettingType> vector = new Vector<>();
//        String sql = "SELECT id"
//                + "      ,name"
//                + "  FROM SettingType"
//                + "  WHERE SettingType.name like '" + search_name+"'";
//
//        try {
//            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            ResultSet rs = state.executeQuery(sql);
//            while (rs.next()) {
//                int id = rs.getInt(1);
//                String name = rs.getString(2);
//                SettingType obj = new SettingType(id, name);
//                return obj;
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return null;
//
//>>>>>>> fbf38e88c7423732c03f084d6c211a2ea03af5a2
//    }
//}
