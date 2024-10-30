/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author AnhVuNAV
 */
public class UserDAO extends DBContext {

    // Phương thức để lấy thông tin người dùng dựa trên ID
    // Phương thức lấy thông tin người dùng dựa vào ID
    public User getUserById(int id) {
        String query = "SELECT * FROM User WHERE id = ?";
        try {
            // Chuẩn bị câu truy vấn và thiết lập tham số
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            // Thực thi câu truy vấn
            ResultSet rs = ps.executeQuery();

            // Kiểm tra và lấy kết quả
            if (rs.next()) {
                User account = new User();
                account.setId(rs.getInt("id"));
                account.setEmail(rs.getString("primary_email"));
                account.setFirstName(rs.getString("first_name"));
                account.setLastName(rs.getString("last_name"));
                account.setPassword(rs.getString("password"));
                account.setDob(rs.getDate("dob"));
                account.setRoleId(rs.getInt("role_id"));
                account.setCreatedDate(rs.getDate("created_date"));
                account.setStatus(rs.getInt("status"));
                account.setPhone(rs.getString("first_phone"));
                account.setGender(rs.getBoolean("gender"));
//                account.setAddress(rs.getString("address"));
                account.setImageURL(rs.getString("image_url"));
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
    public void updateUser(User user) {
        String query = "UPDATE User SET first_name = ?, last_name = ?, phone = ?, gender = ?, address = ?, image_url = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPhone());
            ps.setBoolean(4, user.isGender());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getImageURL());
            ps.setInt(7, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            closeConnection();
        }
    }

    public List<User> getExperts() throws SQLException {
        // Khởi tạo danh sách để lưu trữ các chuyên gia
        List<User> expertsList = new ArrayList<>();

        // Câu truy vấn SQL để lấy danh sách người dùng có vai trò là 'Expert'
        String sql = "SELECT * FROM User WHERE role_id = (SELECT id FROM Setting WHERE value = 'Expert' AND setting_type_id = 1)";

        // Chuẩn bị câu lệnh truy vấn SQL
        PreparedStatement statement = connection.prepareStatement(sql);

        // Thực thi truy vấn
        ResultSet resultSet = statement.executeQuery();

        // Lặp qua từng bản ghi kết quả để tạo đối tượng User và thêm vào danh sách
        while (resultSet.next()) {
            User expert = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("primary_email"),
                    resultSet.getString("image_url")
            );
            expertsList.add(expert); // Thêm chuyên gia vào danh sách
        }

        return expertsList; // Trả về danh sách chuyên gia
    }

}
