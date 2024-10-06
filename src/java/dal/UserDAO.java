/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import static dal.DBContext.connection;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
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

    public User getUserByPrimaryEmail(String email) {
        String sql = "select * from User where primary_email = ?";
        User newUser = new User();
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, email);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    newUser = new User(rs.getString("primary_email"), rs.getString("password"), rs.getInt("role_id"), rs.getString("first_phone"));
                    newUser.setCreatedDate(DateConvert.convertToUtilDate(rs.getDate("created_date")));
                    newUser.setFirstName(rs.getString("first_name"));
                    newUser.setLastName(rs.getString("last_name"));
                    newUser.setDob(DateConvert.convertToUtilDate(rs.getDate("dob")));
                    if (rs.getInt("gender") == 1) {
                        newUser.setGender(true); //male
                    } else {
                        newUser.setGender(false); //female
                    }
                    newUser.setSecondPhone(rs.getString("second_phone"));
                    newUser.setSecondaryEmail(rs.getString("secondary_email"));
                    newUser.setImageURL(rs.getString("image_url"));
                    newUser.setPreferContact(rs.getString("prefer_contact"));
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
                    newUser = new User(rs.getString("primary_email"), rs.getString("password"), rs.getInt("role_id"), rs.getString("first_phone"));
                    newUser.setCreatedDate(DateConvert.convertToUtilDate(rs.getDate("created_date")));
                    newUser.setFirstName(rs.getString("first_name"));
                    newUser.setLastName(rs.getString("last_name"));
                    newUser.setDob(DateConvert.convertToUtilDate(rs.getDate("dob")));
                    if (rs.getInt("gender") == 1) {
                        newUser.setGender(true); //male
                    } else {
                        newUser.setGender(false); //female
                    }
                    newUser.setSecondPhone(rs.getString("second_phone"));
                    newUser.setSecondaryEmail(rs.getString("secondary_email"));
                    newUser.setImageURL(rs.getString("image_url"));
                    newUser.setPreferContact(rs.getString("prefer_contact"));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return newUser;
    }

    public Vector<String> getAllSecondaryEmail() {
        String sql = "select secondary_email from User";
        Vector<String> list = new Vector<>();
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getString("secondary_email"));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Update methods">
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

    public void updateFirstName(User user, String firstName) {
        String sql = "update User\n"
                + "SET first_name = CASE \n"
                + "    WHEN first_name = ? THEN ?\n"
                + "    ELSE first_name\n"
                + "END\n"
                + "WHERE id = ?;";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, user.getFirstName());
            pre.setString(2, firstName);
            pre.setInt(3, user.getId());
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updateLastName(User user, String lastName) {
        String sql = "update User\n"
                + "SET last_name = CASE \n"
                + "    WHEN first_name = ? THEN ?\n"
                + "    ELSE first_name\n"
                + "END\n"
                + "WHERE id = ?;";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, user.getLastName());
            pre.setString(2, lastName);
            pre.setInt(3, user.getId());
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updateDateOfBirth(User user, java.util.Date dob) {
        String sql = "UPDATE User\n"
                + "SET dob = CASE \n"
                + "    WHEN dob = ? THEN ?\n"
                + "    ELSE dob\n"
                + "END\n"
                + "WHERE id = ?;";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setDate(1, DateConvert.convertToSQLDate(user.getDob()));
            pre.setDate(2, DateConvert.convertToSQLDate(dob));
            pre.setInt(3, user.getId());
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updateFirstPhoneNumber(User user, String phoneNumber) {
        String sql = "UPDATE User\n"
                + "SET first_phone = CASE \n"
                + "    WHEN first_phone = ? THEN ?\n"
                + "    ELSE first_phone\n"
                + "END\n"
                + "WHERE id = ?;";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, user.getFirstPhone());
            pre.setString(2, phoneNumber);
            pre.setInt(3, user.getId());
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updateSecondPhoneNumber(User user, String phoneNumber) {
        String sql = "UPDATE User\n"
                + "SET second_phone = CASE \n"
                + "    WHEN second_phone = ? THEN ?\n"
                + "    ELSE second_phone\n"
                + "END\n"
                + "WHERE id = ?;";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, user.getSecondPhone());
            pre.setString(2, phoneNumber);
            pre.setInt(3, user.getId());
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updateImageUrl(User user, String newImageURL) {
        String sql = "UPDATE User\n"
                + "SET image_url = CASE \n"
                + "    WHEN image_url = ? THEN ?\n"
                + "    ELSE image_url\n"
                + "END\n"
                + "WHERE id = ?;";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, user.getImageURL());
            pre.setString(2, newImageURL);
            pre.setInt(3, user.getId());
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updateSecondaryEmail(User user, String email) {
        String sql = "UPDATE User\n"
                + "SET secondary_email = CASE \n"
                + "    WHEN secondary_email = ? THEN ?\n"
                + "    ELSE secondary_email\n"
                + "END\n"
                + "WHERE id = ?;";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, user.getSecondaryEmail());
            pre.setString(2, email);
            pre.setInt(3, user.getId());
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }// </editor-fold>

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

    public void insertAccount(User account) {
        String sql = "insert into User (primary_email, password, created_date, status)\n"
                + "values (?, ?, ?, ?, ?);";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, account.getPrimaryEmail());
            pre.setString(2, account.getPassword());
            pre.setDate(3, DateConvert.convertToSQLDate(account.getCreatedDate()));
            pre.setInt(4, account.getStatus());
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void main(String[] args) {
        UserDAO udao = new UserDAO();
//        User user = new User("tranxuanhoan04@gmail.com", "uHwARmxW2MS5Ub9mMA6RSO+mBwNQQ1xZPO+JdD8uY3U=", 0);
//        udao.insertAccount(user); 
//        System.out.println(udao.getUserByPrimaryEmail("tranxuanhoan04@gmail.com").toString()); 
    }
}
