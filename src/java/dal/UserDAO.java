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
    
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());

    public String updatePass(String email) {
        String sql = "UPDATE Account SET Password = ? WHERE Email LIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            String newPass = generateNewPassword();
            pstmt.setString(1, newPass);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            return newPass;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating password", e);
            return ""; // Return empty string if there's an error
        }
    }

    public void updatePassEncrypted(String email, String pass) {
        String encryptedPassword = EncryptBySHA256(pass);

        String sql = "UPDATE users SET password = ? WHERE email = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, encryptedPassword); 
            pstmt.setString(2, email);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePass(String email, String pass) {
        String sql = "UPDATE Account SET Password = ? WHERE Email LIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, pass);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating password", e);
        }
    }

    public String checkEmail(String email) {
        String sql = "SELECT CONCAT(first_name, ' ', last_name) as FullName FROM Account WHERE email = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("FullName"); // Return the FullName
                } else {
                    return "Nothing"; // No matching user found
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error checking email", e);
            return "Nothing"; // Return "Nothing" if there's an error
        }
    }

    public String generateNewPassword() {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_-+=<>?";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 7; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }

    public String EncryptBySHA256(String raw_password) {
        // Creating a salt code
        String salt = "ahsbdajnsbdj21ek;ádjuadawdwd231";
        String encrypted_password = null;

        // Adding the salt code into the raw password
        raw_password = raw_password + salt;
        try {
            // Transfer the combonation of raw password and salt code into byte array using UTF-8
            byte[] dataBytes = raw_password.getBytes("UTF-8");
            // Compute a hash for the input data
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Pass the byte array and the method used for encryption
            encrypted_password = Base64.encodeBase64String(md.digest(dataBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrypted_password;
    }

    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        System.out.println(dao.EncryptBySHA256("123"));

    }
}
