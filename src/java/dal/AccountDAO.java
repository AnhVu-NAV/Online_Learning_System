/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Account;
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
        }
    }
}
