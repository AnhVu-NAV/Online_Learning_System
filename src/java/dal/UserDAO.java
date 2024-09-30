/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

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
    public Vector<String> getAllEmail() {
        String sql = "select email from Account";
        Vector<String> list = new Vector<>();
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getString("email"));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    public User getAccountByEmail(String email) throws Exception {
        String sql = "select * from Account where email = ?";
        User account = new User();
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, email);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    account.setId(rs.getInt("id"));
                    account.setEmail(rs.getString("email"));
                    account.setPassword(rs.getString("password"));
                    account.setDob(DateConvert.convertToUtilDate(rs.getDate("dob")));
                    account.setRoleId(rs.getInt("role_id"));
                    account.setCreatedDate(DateConvert.convertToUtilDate(rs.getDate("created_date")));
                    account.setStatus(rs.getInt("status"));
                    account.setPhoneNumber(rs.getString("phone"));
                    account.setGender(rs.getBoolean("gender"));
                    account.setFirstName(rs.getString("first_name"));
                    account.setLastName(rs.getString("last_name"));
                    account.setImageURL(rs.getString("image_url"));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return account;
    }

    public User getAccountById(int id) {
        String sql = "select * from Account where id = ?";
        User account = new User();
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, id);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    account.setId(rs.getInt("id"));
                    account.setEmail(rs.getString("email"));
                    account.setPassword(rs.getString("password"));
                    account.setDob(DateConvert.convertToUtilDate(rs.getDate("dob")));
                    account.setRoleId(rs.getInt("role_id"));
                    account.setCreatedDate(DateConvert.convertToUtilDate(rs.getDate("created_date")));
                    account.setStatus(rs.getInt("status"));
                    account.setPhoneNumber(rs.getString("phone"));
                    account.setGender(rs.getBoolean("gender"));
                    account.setFirstName(rs.getString("first_name"));
                    account.setLastName(rs.getString("last_name"));
                    account.setImageURL(rs.getString("image_url"));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return account;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Update methods">
    public void updatePassword(User account, String password) {
        String sql = "UPDATE Account\n"
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

    public void updateDateOfBirth(User account, java.util.Date dob) {
        String sql = "UPDATE Account\n"
                + "SET dob = CASE \n"
                + "    WHEN dob = ? THEN ?\n"
                + "    ELSE dob\n"
                + "END\n"
                + "WHERE id = ?;";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setDate(1, DateConvert.convertToSQLDate(account.getDob()));
            pre.setDate(2, DateConvert.convertToSQLDate(dob));
            pre.setInt(3, account.getId());
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updatePhoneNumber(User account, String phoneNumber) {
        String sql = "UPDATE Account\n"
                + "SET phone = CASE \n"
                + "    WHEN phone = ? THEN ?\n"
                + "    ELSE phone\n"
                + "END\n"
                + "WHERE id = ?;";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, account.getPhoneNumber());
            pre.setString(2, phoneNumber);
            pre.setInt(3, account.getId());
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updateImageUrl(User account, String new_image_url) {
        String sql = "UPDATE Account\n"
                + "SET image_url = CASE \n"
                + "    WHEN image_url = ? THEN ?\n"
                + "    ELSE image_url\n"
                + "END\n"
                + "WHERE id = ?;";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, account.getImageURL());
            pre.setString(2, new_image_url);
            pre.setInt(3, account.getId());
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // </editor-fold>
    public void deleteAccount(User account) {
        String sql = "DELETE FROM Account\n"
                + "WHERE id = ?;";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, account.getId());
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void insertAccount(User account) {
        String sql = "insert into Account (email, phone, password, role_id, created_date, status, image_url)\n"
                + "values (?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, account.getEmail());
            pre.setString(2, account.getPhoneNumber());
            pre.setString(3, account.getPassword());
            pre.setInt(4, account.getRoleId());
            pre.setDate(5, DateConvert.convertToSQLDate(account.getCreatedDate()));
            pre.setInt(6, account.getStatus());
            pre.setString(7, account.getImageURL());
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
