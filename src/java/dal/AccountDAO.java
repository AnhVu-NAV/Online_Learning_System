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
import util.DateConvert;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import util.DataConvert;

/**
 *
 * @author AnhVuNAV
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
            account.setDob(DateConvert.convertToUtilDate(rs.getDate("dob")));
            account.setRoleId(setdao.getSettingById(rs.getInt("role_id")));
            account.setCreatedDate(DateConvert.convertToUtilDate(rs.getDate("created_date")));
            account.setStatus(rs.getInt("status"));
            account.setPhoneNumber(rs.getString("phone"));
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
            account.setDob(DateConvert.convertToUtilDate(rs.getDate("dob")));
            account.setRoleId(setdao.getSettingById(rs.getInt("role_id")));
            account.setCreatedDate(DateConvert.convertToUtilDate(rs.getDate("created_date")));
            account.setStatus(rs.getInt("status"));
            account.setPhoneNumber(rs.getString("phone"));
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
        String sql = "UPDATE Account\n"
                + "SET password = CASE \n"
                + "    WHEN password = ? THEN ?\n"
                + "    ELSE password\n"
                + "END\n"
                + "WHERE id = ?;";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, account.getPassword());
        pre.setString(2, password);
        pre.setInt(3, account.getId());
        pre.executeUpdate();
        pre.close();
    }

    public void updateDateOfBirth(Account account, java.util.Date dob) throws SQLException {
        String sql = "UPDATE Account\n"
                + "SET dob = CASE \n"
                + "    WHEN dob = ? THEN ?\n"
                + "    ELSE dob\n"
                + "END\n"
                + "WHERE id = ?;";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setDate(1, DateConvert.convertToSQLDate(account.getDob()));
        pre.setDate(2, DateConvert.convertToSQLDate(dob));
        pre.setInt(3, account.getId());
        pre.executeUpdate();
        pre.close();
    }

    public void updatePhoneNumber(Account account, String phoneNumber) throws SQLException {
        String sql = "UPDATE Account\n"
                + "SET phone = CASE \n"
                + "    WHEN phone = ? THEN ?\n"
                + "    ELSE phone\n"
                + "END\n"
                + "WHERE id = ?;";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, account.getPhoneNumber());
        pre.setString(2, phoneNumber);
        pre.setInt(3, account.getId());
        pre.executeUpdate();
        pre.close();
    }

    public void updateImageUrl(Account account, String new_image_url) throws Exception {
        String sql = "UPDATE Account\n"
                + "SET image_url = CASE \n"
                + "    WHEN image_url = ? THEN ?\n"
                + "    ELSE image_url\n"
                + "END\n"
                + "WHERE id = ?;";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, account.getImage_url());
        pre.setString(2, new_image_url);
        pre.setInt(3, account.getId());
        pre.executeUpdate();
        pre.close();
    }

    // </editor-fold>
    public void deleteAccount(Account account) throws Exception {
        String sql = "DELETE FROM Account\n"
                + "WHERE id = ?;";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setInt(1, account.getId());
        pre.executeUpdate();
        pre.close();
    }

    public void insertAccount(Account account) throws Exception {
        String sql = "insert into Account (email, phone, password, role_id, created_date, status, image_url)\n"
                + "values (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, account.getEmail());
        pre.setString(2, account.getPhoneNumber());
        pre.setString(3, account.getPassword());
        pre.setInt(4, account.getRoleId().getId());
        pre.setDate(5, DateConvert.convertToSQLDate(account.getCreatedDate()));
        pre.setInt(6, account.getStatus());
        pre.setString(7, account.getImage_url());
        pre.executeUpdate();
        pre.close();
    }

    DataConvert dc = new DataConvert();

    //insert new account into database. Attribute id is AUTO_INCREMENT 
    public int insertAccount(Account obj) {
        int n = 0;
        String sql = "INSERT INTO Account\n"
                + "           (email\n"
                + "           ,first_name\n"
                + "           ,last_name\n"
                + "           ,password\n"
                + "           ,dob\n"
                + "           ,role_id\n"
                + "           ,created_date\n"
                + "           ,status\n"
                + "           ,phone\n"
                + "           ,gender\n"
                + "           ,address\n"
                + "           ,image_url)\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, obj.getEmail());
            pre.setString(2, obj.getFirst_name());
            pre.setString(3, obj.getLast_name());
            pre.setString(4, obj.getPassword());
            pre.setDate(5, dc.UtilDateToSqlDate(obj.getDob()));
            pre.setInt(6, obj.getRole_id());
            pre.setDate(7, dc.UtilDateToSqlDate(obj.getCreated_date()));
            pre.setInt(8, obj.getStatus());
            pre.setString(9, obj.getPhone());
            pre.setBoolean(10, obj.isGender());
            pre.setString(11, obj.getAddress());
            pre.setString(12, obj.getImage_url());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;

    }

    // get an account list based on specific conditions
    public Vector<Account> getAccounts(String sql) {
        Vector<Account> vector = new Vector<Account>();
        try {
            Statement state = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String email = rs.getString(2);
                String first_name = rs.getString(3);
                String last_name = rs.getString(4);
                String password = rs.getString(5);
                Date dob = rs.getDate(6);
                int role_id = rs.getInt(7);
                Date created_date = rs.getDate(8);
                int status = rs.getInt(9);
                String phone = rs.getString(10);
                Boolean gender = rs.getBoolean(11);
                String address = rs.getString(12);
                String image_url = rs.getString(13);
                Account obj = new Account(id, email, first_name, last_name, password, dob, role_id, created_date, status, phone, gender, address, image_url);
                vector.add(obj);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;

    }

    // get all the accounts in the database 
    public Vector<Account> getAll() {
        Vector<Account> vector = new Vector<>();
        String sql = "SELECT id\n"
                + "      ,email\n"
                + "      ,first_name\n"
                + "      ,last_name\n"
                + "      ,password\n"
                + "      ,dob\n"
                + "      ,role_id\n"
                + "      ,created_date\n"
                + "      ,status\n"
                + "      ,phone\n"
                + "      ,gender\n"
                + "      ,address\n"
                + "      ,image_url\n"
                + "  FROM Account";
        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String email = rs.getString(2);
                String first_name = rs.getString(3);
                String last_name = rs.getString(4);
                String password = rs.getString(5);
                Date dob = rs.getDate(6);
                int role_id = rs.getInt(7);
                Date created_date = rs.getDate(8);
                int status = rs.getInt(9);
                String phone = rs.getString(10);
                Boolean gender = rs.getBoolean(11);
                String address = rs.getString(12);
                String image_url = rs.getString(13);
                Account obj = new Account(id, email, first_name, last_name, password, dob, role_id, created_date, status, phone, gender, address, image_url);
                vector.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    // update Account information based on id
    public int updateAccount(Account obj) {
        int n = 0;
        String sql = "UPDATE Account\n"
                + "   SET email = ?\n"
                + "      ,first_name = ?\n"
                + "      ,last_name = ?\n"
                + "      ,password = ?\n"
                + "      ,dob = ?\n"
                + "      ,role_id = ?\n"
                + "      ,created_date = ?\n"
                + "      ,status = ?\n"
                + "      ,phone = ?\n"
                + "      ,gender = ?\n"
                + "      ,address = ?\n"
                + "      ,image_url = ?\n"
                + " WHERE id = ?";
        try {

            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, obj.getEmail());
            pre.setString(2, obj.getFirst_name());
            pre.setString(3, obj.getLast_name());
            pre.setString(4, obj.getPassword());
            pre.setDate(5, dc.UtilDateToSqlDate(obj.getDob()));
            pre.setInt(6, obj.getRole_id());
            pre.setDate(7, dc.UtilDateToSqlDate(obj.getCreated_date()));
            pre.setInt(8, obj.getStatus());
            pre.setString(9, obj.getPhone());
            pre.setBoolean(10, obj.isGender());
            pre.setString(11, obj.getAddress());
            pre.setString(12, obj.getImage_url());
            pre.setInt(13, obj.getId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Account getAccountById(int search_id) {
        Vector<Account> vector = new Vector<>();
        String sql = "SELECT id"
                + "      ,email"
                + "      ,first_name"
                + "      ,last_name"
                + "      ,password"
                + "      ,dob"
                + "      ,role_id"
                + "      ,created_date"
                + "      ,status"
                + "      ,phone"
                + "      ,gender"
                + "      ,address"
                + "      ,image_url"
                + "  FROM Account"
                + "  WHERE Account.id = " + search_id;

        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String email = rs.getString(2);
                String first_name = rs.getString(3);
                String last_name = rs.getString(4);
                String password = rs.getString(5);
                Date dob = rs.getDate(6);
                int role_id = rs.getInt(7);
                Date created_date = rs.getDate(8);
                int status = rs.getInt(9);
                String phone = rs.getString(10);
                Boolean gender = rs.getBoolean(11);
                String address = rs.getString(12);
                String image_url = rs.getString(13);
                Account obj = new Account(id, email, first_name, last_name, password, dob, role_id, created_date, status, phone, gender, address, image_url);
                return obj;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public Account getUserById(int id) {
        String query = "SELECT * FROM Account WHERE id = ?";
        try {
            // Chuẩn bị câu truy vấn và thiết lập tham số
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            
            // Thực thi câu truy vấn
            ResultSet rs = ps.executeQuery();
            
            // Kiểm tra và lấy kết quả
            if (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setEmail(rs.getString("email"));
                account.setFirst_name(rs.getString("first_name"));
                account.setLast_name(rs.getString("last_name"));
                account.setPassword(rs.getString("password"));
                account.setDob(rs.getDate("dob"));
                account.setRole_id(rs.getInt("role_id"));
                account.setCreated_date(rs.getDate("created_date"));
                account.setStatus(rs.getInt("status"));
                account.setPhone(rs.getString("phone"));
                account.setGender(rs.getBoolean("gender"));
                account.setAddress(rs.getString("address"));
                account.setImage_url(rs.getString("image_url"));
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            closeConnection();
        }
        return null; // Trả về null nếu không tìm thấy người dùng
    }
    
    
    // Phương thức cập nhật thông tin người dùng
    public void updateUser(Account user) {
        String query = "UPDATE Account SET first_name = ?, last_name = ?, phone = ?, gender = ?, address = ?, image_url = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirst_name());
            ps.setString(2, user.getLast_name());
            ps.setString(3, user.getPhone());
            ps.setBoolean(4, user.isGender());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getImage_url());
            ps.setInt(7, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            closeConnection();

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
