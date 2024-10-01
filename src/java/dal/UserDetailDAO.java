/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.UserDetail;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.*;

/**
 *
 * @author ADMIN
 */
public class UserDetailDAO extends DBContext {
    
    //test done
    public void insert(UserDetail userDetail) {
        String sql = "insert into UserDetail (user_id, first_name, last_name, dob, gender, tel_phone, image_url, secondary_email, prefer_contact)\n"
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, userDetail.getUserId());
            pre.setString(2, userDetail.getFirstName());
            pre.setString(3, userDetail.getLastName());
            pre.setDate(4, DateConvert.convertToSQLDate(userDetail.getDob()));
            if (userDetail.isGender()) {
                pre.setInt(5, 1);
            } else {
                pre.setInt(5, 0);
            }
            pre.setString(6, userDetail.getPhone());
            pre.setString(7, userDetail.getImageURL());
            pre.setString(8, userDetail.getSecondaryEmail());
            pre.setString(9, userDetail.getPreferContact());
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //test done
    public void delete(int id) {
        String sql = "delete from UserDetail where user_id = ?";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, id);
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Get methods. Click on the + sign on the left to edit the code.">
    //test done
    public Vector<UserDetail> getUserDetailByRequest(String request) {
        String sql = "select * from UserDetail " + request;
        Vector<UserDetail> list = new Vector();
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    UserDetail userDetail = new UserDetail();
                    userDetail.setUserId(rs.getInt("user_id"));
                    userDetail.setFirstName(rs.getString("first_name"));
                    userDetail.setLastName(rs.getString("last_name"));
                    userDetail.setDob(DateConvert.convertToUtilDate(rs.getDate("dob")));
                    if (rs.getInt("gender") == 0) {
                        userDetail.setGender(false);
                    } else {
                        userDetail.setGender(true);
                    }
                    userDetail.setPhone(rs.getString("tel_phone"));
                    userDetail.setImageURL(rs.getString("image_url"));
                    userDetail.setSecondaryEmail(rs.getString("secondary_email"));
                    userDetail.setPreferContact(rs.getString("prefer_contact"));
                    list.add(userDetail);
                }
            }
        } catch (Exception e) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
    
    //test done
    public UserDetail getUserDetailById(int id) {
        String sql = "select * from UserDetail where user_id = ?";
        UserDetail userDetail = new UserDetail();
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, id);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    userDetail.setUserId(rs.getInt("user_id"));
                    userDetail.setFirstName(rs.getString("first_name"));
                    userDetail.setLastName(rs.getString("last_name"));
                    userDetail.setDob(DateConvert.convertToUtilDate(rs.getDate("dob")));
                    if (rs.getInt("gender") == 0) {
                        userDetail.setGender(false);
                    } else {
                        userDetail.setGender(true);
                    }
                    userDetail.setPhone(rs.getString("tel_phone"));
                    userDetail.setImageURL(rs.getString("image_url"));
                    userDetail.setSecondaryEmail(rs.getString("secondary_email"));
                    userDetail.setPreferContact(rs.getString("prefer_contact"));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return userDetail;
    }

    public Vector<String> getAllSecondaryEmail() {
        String sql = "select secondary_email from UserDetail";
        Vector<String> list = new Vector<>();
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getString("secondary_email"));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Update methods. Click on the + sign on the left to edit the code.">
    //test done
    public void updateFirstNameUserDetail(UserDetail userDetail, String firstName) {
        String sql = "update UserDetail\n"
                + "SET first_name = CASE \n"
                + "    WHEN first_name = ? THEN ?\n"
                + "    ELSE first_name\n"
                + "END\n"
                + "WHERE user_id = ?;";

        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, userDetail.getFirstName());
            pre.setString(2, firstName);
            pre.setInt(3, userDetail.getUserId());
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updateLastNameUserDetail(UserDetail userDetail, String lastName) {
        String sql = "update UserDetail\n"
                + "SET last_name = CASE \n"
                + "    WHEN first_name = ? THEN ?\n"
                + "    ELSE first_name\n"
                + "END\n"
                + "WHERE user_id = ?;";

        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, userDetail.getLastName());
            pre.setString(2, lastName);
            pre.setInt(3, userDetail.getUserId());
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updateDateOfBirth(UserDetail userDetail, java.util.Date dob) {
        String sql = "UPDATE UserDetail\n"
                + "SET dob = CASE \n"
                + "    WHEN dob = ? THEN ?\n"
                + "    ELSE dob\n"
                + "END\n"
                + "WHERE user_id = ?;";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setDate(1, DateConvert.convertToSQLDate(userDetail.getDob()));
            pre.setDate(2, DateConvert.convertToSQLDate(dob));
            pre.setInt(3, userDetail.getUserId());
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updatePhoneNumber(UserDetail userDetail, String phoneNumber) {
        String sql = "UPDATE UserDetail\n"
                + "SET tel_phone = CASE \n"
                + "    WHEN tel_phone = ? THEN ?\n"
                + "    ELSE tel_phone\n"
                + "END\n"
                + "WHERE user_id = ?;";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, userDetail.getPhone());
            pre.setString(2, phoneNumber);
            pre.setInt(3, userDetail.getUserId());
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserDetail.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updateImageUrl(UserDetail userDetail, String newImageURL) {
        String sql = "UPDATE UserDetail\n"
                + "SET image_url = CASE \n"
                + "    WHEN image_url = ? THEN ?\n"
                + "    ELSE image_url\n"
                + "END\n"
                + "WHERE user_id = ?;";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, userDetail.getImageURL());
            pre.setString(2, newImageURL);
            pre.setInt(3, userDetail.getUserId());
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    // </editor-fold>

}
