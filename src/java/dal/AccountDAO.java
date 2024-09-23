/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Account;

/**
 *
 * @author AnhVuNAV
 */
public class AccountDAO extends DBContext{
    // Phương thức để lấy thông tin người dùng dựa trên ID
    // Phương thức lấy thông tin người dùng dựa vào ID
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