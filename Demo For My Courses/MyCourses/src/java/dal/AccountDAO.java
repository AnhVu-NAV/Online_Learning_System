/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Account;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import model.Setting;
import util.DataConvert;

/**
 *
 * @author ADMIN
 */
public class AccountDAO extends DBContext {

    // <editor-fold defaultstate="collapsed" desc="Declare variable and constructor">
    private SettingDAO setdao;

    public AccountDAO() {
        this.setdao = new SettingDAO();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Get methods">
    public Account getAccountByEmail(String email) throws Exception {
        String sql = "select * from Account where email = ?";
        Account account = new Account();
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, email);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            account.setId(rs.getInt("id"));
            account.setEmail(rs.getString("email"));
            account.setPassword(rs.getString("password"));
            account.setDob(DataConvert.convertToUtilDate(rs.getDate("dob")));
            account.setRoleId(setdao.getSettingById(rs.getInt("role_id")));
            account.setCreatedDate(DataConvert.convertToUtilDate(rs.getDate("created_date")));
            account.setStatus(rs.getInt("status"));
            account.setPhoneNumber(rs.getString("phone_number"));
            account.setGender(rs.getBoolean("gender"));
            account.setFirstName(rs.getString("first_name"));
            account.setLastName(rs.getString("last_name"));
            account.setImage_url(rs.getString("image_url"));
            return account;
        }
        rs.close();
        pre.close();
        return null;
    }

    public Account getAccountById(int id) throws Exception {
        String sql = "select * from Account where id = ?";
        Account account = new Account();
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            account.setId(rs.getInt("id"));
            account.setEmail(rs.getString("email"));
            account.setPassword(rs.getString("password"));
            account.setDob(DataConvert.convertToUtilDate(rs.getDate("dob")));
            account.setRoleId(setdao.getSettingById(rs.getInt("role_id")));
            account.setCreatedDate(DataConvert.convertToUtilDate(rs.getDate("created_date")));
            account.setStatus(rs.getInt("status"));
            account.setPhoneNumber(rs.getString("phone_number"));
            account.setGender(rs.getBoolean("gender"));
            account.setFirstName(rs.getString("first_name"));
            account.setLastName(rs.getString("last_name"));
            account.setImage_url(rs.getString("image_url"));
            return account;
        }
        rs.close();
        pre.close();
        return null;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Update methods">
    public Vector<String> getAllEmail() throws SQLException {
        String sql = "select email from Account";
        Vector<String> list = new Vector<>();
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            list.add(rs.getString("email"));
        }
        rs.close();
        pre.close();
        return list;
    }

    public void updatePassword(Account account, String password) throws SQLException {
        String sql = """
                     UPDATE Account
                     SET password = CASE 
                         WHEN password = ? THEN ?
                         ELSE password
                     END
                     WHERE id = ?;
                     """;
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, account.getPassword());
        pre.setString(2, password);
        pre.setInt(3, account.getId());
        pre.executeUpdate();
        pre.close();
    }

    public void updateDateOfBirth(Account account, java.util.Date dob) throws SQLException {
        String sql = """
                     UPDATE Account
                     SET dob = CASE 
                         WHEN dob = ? THEN ?
                         ELSE dob
                     END
                     WHERE id = ?;
                     """;
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setDate(1, DataConvert.convertToSQLDate(account.getDob()));
        pre.setDate(2, DataConvert.convertToSQLDate(dob));
        pre.setInt(3, account.getId());
        pre.executeUpdate();
        pre.close();
    }

    public void updatePhoneNumber(Account account, String phoneNumber) throws SQLException {
        String sql = """
                     UPDATE Account
                     SET phone_number = CASE 
                         WHEN phone_number = ? THEN ?
                         ELSE phone_number
                     END
                     WHERE id = ?;
                     """;
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, account.getPhoneNumber());
        pre.setString(2, phoneNumber);
        pre.setInt(3, account.getId());
        pre.executeUpdate();
        pre.close();
    }

    public void updateImageUrl(Account account, String new_image_url) throws Exception {
        String sql = """
                     UPDATE Account
                     SET image_url = CASE 
                         WHEN image_url = ? THEN ?
                         ELSE image_url
                     END
                     WHERE id = ?;
                     """;
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, account.getImage_url());
        pre.setString(2, new_image_url);
        pre.setInt(3, account.getId());
        pre.executeUpdate();
        pre.close();
    }

    // </editor-fold>
    public void deleteAccount(Account account) throws Exception {
        String sql = """
                     DELETE FROM Account
                     WHERE id = ?;
                     """;
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setInt(1, account.getId());
        pre.executeUpdate();
        pre.close();
    }

    public void insertAccount(Account account) throws Exception {
        String sql = """
                     insert into Account (email, phone_number, password, role_id, created_date, status, image_url)
                     values (?, ?, ?, ?, ?, ?, ?);
                     """;
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, account.getEmail());
        pre.setString(2, account.getPhoneNumber());
        pre.setString(3, account.getPassword());
        pre.setInt(4, account.getRoleId().getId());
        pre.setDate(5, DataConvert.convertToSQLDate(account.getCreatedDate()));
        pre.setInt(6, account.getStatus());
        pre.setString(7, account.getImage_url());
        pre.executeUpdate();
        pre.close();
    }

    public static void main(String[] args) {
        AccountDAO adao = new AccountDAO();
        try {
//        Account account = adao.getAccountByEmail("b@gmail.com");
//
//        update
//        adao.updatePassword(account, "kekekeke");
//        adao.updateImageUrl(account, "https://haha"); 
//        
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate dob = LocalDate.parse("1996-10-02", formatter);
//        adao.updateDateOfBirth(account, dob); 
//       
//        adao.updatePhoneNumber(account, "0912797815"); 
//       
//        delete
//            Account account = adao.getAccountByEmail("hoana5k44nknd@gmail.com");
//            adao.deleteAccount(account);
//
//        insert        
//        LocalDate dob1 = LocalDate.parse("2024-10-02", formatter);
//            SettingDAO sdao = new SettingDAO();
//            Account account = new Account(
//                    "hoana5k44nknd@gmail.com",
//                    "0949279204",
//                    "12345678",
//                    sdao.getSettingById(1)); 
//
//            adao.insertAccount(account);
//
//        for (String string : adao.getAllEmail()) {
//            System.out.println(string);
//        }
//            System.out.println(sdao.getSettingById(1).toString());
//            System.out.println(adao.getAccountByEmail("hoana5k44nknd@gmail.com").toString());
            SettingDAO setdao = new SettingDAO();
            SettingTypeDAO setttingTypeDAO = new SettingTypeDAO();
            setttingTypeDAO.insert("a");
            Setting setting = new Setting(setttingTypeDAO.getSettingTypeByName("a"), "b", 0);
            setdao.insert(setting); 
            Account account = new Account("a@gmail.com", "0949279204", "12345678", setdao.getSettingById(3)); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
