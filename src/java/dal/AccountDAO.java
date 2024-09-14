/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Account;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
                account.setDob(rs.getDate("dob").toLocalDate());
                account.setFirstName(rs.getString("first_name"));
                account.setLastName(rs.getString("last_name"));
                account.setPhoneNumber(rs.getString("phone_number"));
                account.setPassword(rs.getString("password"));
                account.setRole(rs.getInt("role"));
                account.setGender(rs.getInt("gender"));
                account.setCreatedDate(rs.getDate("created_date").toLocalDate());
                account.setIsActivated(rs.getInt("isActivated"));
                return account;
            }
            rs.close();
            pre.close();
        } catch (SQLException e) {
            System.err.println("Error " + e.getMessage());
        }
        return null;
    }

    public ArrayList<String> getAllEmail() {
        String sql = "select email from Account";
        ArrayList<String> list = new ArrayList<>();
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

//    public ArrayList<Account> getAccountByRole(int role) {
//        switch (role) {
//            case 0 -> {
//                return getAdmin();
//            }
//            case 1 -> {
//                return getCustomer();
//            }
//            case 2 -> {
//                return getExpert();
//            }
//            case 3 -> {
//                return getSaler();
//            }
//            case 4 -> {
//                return getMarketer();
//            }
//        }
//        return null;
//    }
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
            pre.setDate(1, java.sql.Date.valueOf(account.getDob()));
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
                     insert into Account (id, email, dob, first_name, last_name, phone_number, password, role, gender, created_date, isActivated)
                     values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                     """;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, account.getId());
            pre.setString(2, account.getEmail());
            pre.setDate(3, java.sql.Date.valueOf(account.getDob()));
            pre.setString(4, account.getFirstName());
            pre.setString(5, account.getLastName());
            pre.setString(6, account.getPhoneNumber());
            pre.setString(7, account.getPassword());
            pre.setInt(8, account.getRole());
            pre.setInt(9, account.getGender());
            pre.setDate(10, java.sql.Date.valueOf(account.getCreatedDate()));
            pre.setInt(11, account.getIsActivated());
            pre.executeUpdate();
            pre.close();
        } catch (SQLException e) {
            System.err.println("Error " + e.getMessage());
        }
    }

//    private ArrayList<Account> getAdmin() {
//        String sql = "select * from Account where role = 0";
//        ArrayList<Account> list = new ArrayList<>();
//        try {
//            PreparedStatement pre = connection.prepareStatement(sql);
//            ResultSet rs = pre.executeQuery();
//            while (rs.next()) {
//                Account account = new Account();
//                account.setId(rs.getInt("id"));
//                account.setEmail(rs.getString("email"));
//                account.setDob(rs.getDate("dob").toLocalDate());
//                account.setFirstName(rs.getString("first_name"));
//                account.setLastName(rs.getString("last_name"));
//                account.setPhoneNumber(rs.getString("phone_number"));
//                account.setPassword(rs.getString("password"));
//                account.setRole(rs.getInt("role"));
//                account.setGender(rs.getInt("gender"));
//                account.setCreatedDate(rs.getDate("created_date").toLocalDate());
//                account.setIsActivated(rs.getInt("isActivated"));
//                list.add(account);
//            }
//            rs.close();
//            pre.close();
//        } catch (SQLException e) {
//            System.err.println("Error " + e.getMessage());
//        }
//        return list;
//    }
//
//    private ArrayList<Account> getCustomer() {
//        String sql = "select * from Account where role = 1";
//        ArrayList<Account> list = new ArrayList<>();
//        try {
//            PreparedStatement pre = connection.prepareStatement(sql);
//            ResultSet rs = pre.executeQuery();
//            while (rs.next()) {
//                Account account = new Account();
//                account.setId(rs.getInt("id"));
//                account.setEmail(rs.getString("email"));
//                account.setDob(rs.getDate("dob").toLocalDate());
//                account.setFirstName(rs.getString("first_name"));
//                account.setLastName(rs.getString("last_name"));
//                account.setPhoneNumber(rs.getString("phone_number"));
//                account.setPassword(rs.getString("password"));
//                account.setRole(rs.getInt("role"));
//                account.setGender(rs.getInt("gender"));
//                account.setCreatedDate(rs.getDate("created_date").toLocalDate());
//                account.setIsActivated(rs.getInt("isActivated"));
//                list.add(account);
//            }
//            rs.close();
//            pre.close();
//        } catch (SQLException e) {
//            System.err.println("Error " + e.getMessage());
//        }
//        return list;
//    }
//
//    private ArrayList<Account> getExpert() {
//        String sql = "select * from Account where role = 2";
//        ArrayList<Account> list = new ArrayList<>();
//        try {
//            PreparedStatement pre = connection.prepareStatement(sql);
//            ResultSet rs = pre.executeQuery();
//            while (rs.next()) {
//                Account account = new Account();
//                account.setId(rs.getInt("id"));
//                account.setEmail(rs.getString("email"));
//                account.setDob(rs.getDate("dob").toLocalDate());
//                account.setFirstName(rs.getString("first_name"));
//                account.setLastName(rs.getString("last_name"));
//                account.setPhoneNumber(rs.getString("phone_number"));
//                account.setPassword(rs.getString("password"));
//                account.setRole(rs.getInt("role"));
//                account.setGender(rs.getInt("gender"));
//                account.setCreatedDate(rs.getDate("created_date").toLocalDate());
//                account.setIsActivated(rs.getInt("isActivated"));
//                list.add(account);
//            }
//            rs.close();
//            pre.close();
//        } catch (SQLException e) {
//            System.err.println("Error " + e.getMessage());
//        }
//        return list;
//    }
//
//    private ArrayList<Account> getSaler() {
//        String sql = "select * from Account where role = 3";
//        ArrayList<Account> list = new ArrayList<>();
//        try {
//            PreparedStatement pre = connection.prepareStatement(sql);
//            ResultSet rs = pre.executeQuery();
//            while (rs.next()) {
//                Account account = new Account();
//                account.setId(rs.getInt("id"));
//                account.setEmail(rs.getString("email"));
//                account.setDob(rs.getDate("dob").toLocalDate());
//                account.setFirstName(rs.getString("first_name"));
//                account.setLastName(rs.getString("last_name"));
//                account.setPhoneNumber(rs.getString("phone_number"));
//                account.setPassword(rs.getString("password"));
//                account.setRole(rs.getInt("role"));
//                account.setGender(rs.getInt("gender"));
//                account.setCreatedDate(rs.getDate("created_date").toLocalDate());
//                account.setIsActivated(rs.getInt("isActivated"));
//                list.add(account);
//            }
//            rs.close();
//            pre.close();
//        } catch (SQLException e) {
//            System.err.println("Error " + e.getMessage());
//        }
//        return list;
//    }
//    private ArrayList<Account> getMarketer() {
//        String sql = "select * from Account where role = 4";
//        ArrayList<Account> list = new ArrayList<>();
//        try {
//            PreparedStatement pre = connection.prepareStatement(sql);
//            ResultSet rs = pre.executeQuery();
//            while (rs.next()) {
//                Account account = new Account();
//                account.setId(rs.getInt("id"));
//                account.setEmail(rs.getString("email"));
//                account.setDob(rs.getDate("dob").toLocalDate());
//                account.setFirstName(rs.getString("first_name"));
//                account.setLastName(rs.getString("last_name"));
//                account.setPhoneNumber(rs.getString("phone_number"));
//                account.setPassword(rs.getString("password"));
//                account.setRole(rs.getInt("role"));
//                account.setGender(rs.getInt("gender"));
//                account.setCreatedDate(rs.getDate("created_date").toLocalDate());
//                account.setIsActivated(rs.getInt("isActivated"));
//                list.add(account);
//            }
//            rs.close();
//            pre.close();
//        } catch (SQLException e) {
//            System.err.println("Error " + e.getMessage());
//        }
//        return list;
//    }
//    public static void main(String[] args) {
//        AccountDAO adao = new AccountDAO();
////        Account account = adao.getAccountByEmail("b@gmail.com");
//
////        update
////        adao.updatePassword(account, "keke");
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
////        LocalDate dob = LocalDate.parse("1996-10-02", formatter);
////        adao.updateDateOfBirth(account, dob); 
////        adao.updatePhoneNumber(account, "0912797815"); 
////        delete
////        Account account = adao.getAccountByEmail("b@gmail.com");
////        adao.deleteAccount(account); 
////        insert        
////        LocalDate dob1 = LocalDate.parse("2024-10-02", formatter);
////        Account account = new Account(
////                1,
////                "a@gmail.com",
////                dob,
////                "Hung",
////                "Nguyen",
////                "0949279204",
////                "hahakeke",
////                0,
////                0,
////                dob1,
////                0);
////        adao.insertAccount(account); 
////        for (String string : adao.getAllEmail()) {
////            System.out.println(string);
////        }
//        System.out.println(adao.getAccountByEmail("a@gmail.com").toString());
//    }
}
