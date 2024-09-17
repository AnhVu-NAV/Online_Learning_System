/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Account;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import util.DataConvert;

/**
 *
 * @author ADMIN
 */
public class AccountDAO extends DBContext {

    public Account getAccountByEmail(String email) {
        String sql = "select * from Account where email = ?";
        Account account = new Account();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, email);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                account.setId(rs.getInt("id"));
                account.setEmail(rs.getString("email"));
                account.setPassword(rs.getString("password"));
                account.setDob(rs.getDate("dob"));
                account.setRoleId(rs.getInt("role_id"));
                account.setCreatedDate(rs.getDate("created_date").toLocalDate());
                account.setStatus(rs.getInt("status"));
                account.setPhoneNumber(rs.getString("phone_number"));
                account.setGender(rs.getBoolean("gender"));
                account.setFirstName(rs.getString("first_name"));
                account.setLastName(rs.getString("last_name"));
                return account;
            }
            rs.close();
            pre.close();
        } catch (SQLException e) {
            System.err.println("Error " + e.getMessage());
        }
        return null;
    }

    public Vector<String> getAllEmail() {
        String sql = "select email from Account";
        Vector<String> list = new Vector<>();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("email"));
            }
            rs.close();
            pre.close();
            return list;
        } catch (SQLException e) {
            System.err.println("Error " + e.getMessage());
        }
        return null;
    }

    public void updatePassword(Account account, String password) {
        String sql = """
                     UPDATE Account
                     SET password = CASE 
                         WHEN password = ? THEN ?
                         ELSE password
                     END
                     WHERE id = ?;
                     """;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, account.getPassword());
            pre.setString(2, password);
            pre.setInt(3, account.getId());
            pre.executeUpdate();
            pre.close();
        } catch (SQLException e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    public void updateDateOfBirth(Account account, LocalDate dob) {
        String sql = """
                     UPDATE Account
                     SET dob = CASE 
                         WHEN dob = ? THEN ?
                         ELSE dob
                     END
                     WHERE id = ?;
                     """;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setDate(1, DataConvert.convertDate(account.getDob())); 
            pre.setDate(2, java.sql.Date.valueOf(dob));
            pre.setInt(3, account.getId());
            pre.executeUpdate();
            pre.close();
        } catch (SQLException e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    public void updatePhoneNumber(Account account, String phoneNumber) {
        String sql = """
                     UPDATE Account
                     SET phone_number = CASE 
                         WHEN phone_number = ? THEN ?
                         ELSE phone_number
                     END
                     WHERE id = ?;
                     """;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, account.getPhoneNumber());
            pre.setString(2, phoneNumber);
            pre.setInt(3, account.getId());
            pre.executeUpdate();
            pre.close();
        } catch (SQLException e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    public void deleteAccount(Account account) {
        String sql = """
                     DELETE FROM Account
                     WHERE id = ?;
                     """;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, account.getId());
            pre.executeUpdate();
            pre.close();
        } catch (SQLException e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    public void insertAccount(Account account) {
        String sql = """
                     insert into Account (id, email, dob, first_name, last_name, phone_number, password, role_id, gender, created_date, status)
                     values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                     """;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, account.getId());
            pre.setString(2, account.getEmail());
            pre.setDate(3, DataConvert.convertDate(account.getDob()));
            pre.setString(4, account.getFirstName());
            pre.setString(5, account.getLastName());
            pre.setString(6, account.getPhoneNumber());
            pre.setString(7, account.getPassword());
            pre.setInt(8, account.getRoleId());
            pre.setBoolean(9, account.getGender());
            pre.setDate(10, java.sql.Date.valueOf(account.getCreatedDate()));
            pre.setInt(11, account.getStatus());
            pre.executeUpdate();
            pre.close();
        } catch (SQLException e) {
            System.err.println("Error " + e.getMessage());
        }
    }

//    public static void main(String[] args) {
//        AccountDAO adao = new AccountDAO();
//        Account account = adao.getAccountByEmail("b@gmail.com");

//        update
//        adao.updatePassword(account, "kekekeke");
//        
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate dob = LocalDate.parse("1996-10-02", formatter);
//        adao.updateDateOfBirth(account, dob); 
//       
//        adao.updatePhoneNumber(account, "0912797815"); 
//       
//        delete
//        Account account = adao.getAccountByEmail("b@gmail.com");
//        adao.deleteAccount(account); 
//
//        insert        
//        LocalDate dob1 = LocalDate.parse("2024-10-02", formatter);
//        Account account = new Account(
//                1,
//                "a@gmail.com",
//                dob,
//                "Hung",
//                "Nguyen",
//                "0949279204",
//                "hahakeke",
//                0,
//                true,
//                dob1,
//                0);
//        adao.insertAccount(account); 
//
//        for (String string : adao.getAllEmail()) {
//            System.out.println(string);
//        }
//        System.out.println(adao.getAccountByEmail("a@gmail.com").toString());
//    }
}
