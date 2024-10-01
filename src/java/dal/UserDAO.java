/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import static dal.DBContext.connection;
import model.User;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DateConvert;

/**
 *
 * @author ADMIN
 */
public class UserDAO extends DBContext {

    // <editor-fold defaultstate="collapsed" desc="Get methods">
    public Vector<String> getAllPrimaryEmail() {
        String sql = "select primary_email from User";
        Vector<String> list = new Vector<>();
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getString("primary_email"));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    //test done
    public User getUserByPrimaryEmail(String email) {
        String sql = "select * from User where primary_email = ?";
        User newUser = new User();
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, email);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    newUser.setId(rs.getInt("id"));
                    newUser.setPrimaryEmail(rs.getString("primary_email"));
                    newUser.setPassword(rs.getString("password"));
                    newUser.setCreatedDate(DateConvert.convertToUtilDate(rs.getDate("created_date")));
                    newUser.setUpdatedDate(DateConvert.convertToUtilDate(rs.getDate("updated_date")));
                    newUser.setStatus(rs.getInt("status"));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return newUser;
    }

    public User getUserById(int id) {
        String sql = "select * from User where id = ?";
        User newUser = new User();
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, id);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    newUser.setId(rs.getInt("id"));
                    newUser.setPrimaryEmail(rs.getString("primary_email"));
                    newUser.setPassword(rs.getString("password"));
                    newUser.setCreatedDate(DateConvert.convertToUtilDate(rs.getDate("created_date")));
                    newUser.setUpdatedDate(DateConvert.convertToUtilDate(rs.getDate("updated_date")));
                    newUser.setStatus(rs.getInt("status"));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return newUser;
    }

    // test done
    public int getIdByEmail(String email) {
        String sql = "select * from User where primary_email = ?";
        int result = 0;
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, email);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    result = rs.getInt("id");
                }
            }
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }
    // </editor-fold>

    public void updatePassword(User account, String password) {
        String sql = "UPDATE User\n"
                + "SET password = CASE \n"
                + "    WHEN password = ? THEN ?\n"
                + "    ELSE password\n"
                + "END\n"
                + "WHERE id = ?;";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, account.getPassword());
            pre.setString(2, password);
            pre.setInt(3, account.getId());
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void deleteAccount(User account) {
        String sql = "DELETE FROM User\n"
                + "WHERE id = ?;";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, account.getId());
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //test done
    public void insertAccount(User account) {
        String sql = "insert into User (primary_email, password, updated_date, created_date, status)\n"
                + "values (?, ?, ?, ?, ?);";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, account.getPrimaryEmail());
            pre.setString(2, account.getPassword());
            pre.setDate(3, DateConvert.convertToSQLDate(account.getCreatedDate()));
            pre.setDate(4, DateConvert.convertToSQLDate(account.getUpdatedDate()));
            pre.setInt(5, account.getStatus());
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public static void main(String[] args) {
        UserDAO udao = new UserDAO();
        User user = new User("tranxuanhoan04@gmail.com", "uHwARmxW2MS5Ub9mMA6RSO+mBwNQQ1xZPO+JdD8uY3U=", 0);
        udao.insertAccount(user); 
        System.out.println(udao.getUserByPrimaryEmail("tranxuanhoan04@gmail.com").toString()); 
    }
}
