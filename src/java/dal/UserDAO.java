package dal;

import model.User;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import util.DataConvert;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import java.util.Date;
import java.util.Vector;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDAO class for accessing and managing user data in the database.
 *
 * @author AnhVuNAV
 */
public class UserDAO extends DBContext {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());

    public String updatePass(String email) {
        String sql = "UPDATE User SET Password = ? WHERE Primary_Email LIKE ?";
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
        String sql = "UPDATE User SET Password = ? WHERE Primary_Email LIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, pass);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating password", e);
        }
    }

    public String checkEmail(String email) {
        String sql = "SELECT CONCAT(first_name, ' ', last_name) as FullName FROM User WHERE Primary_Email = ?";
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

    //VuNA
    // Method to get user details by ID
    public User getUserById(int id) {
        String query = "SELECT * FROM User WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setPrimaryEmail(rs.getString("primary_email"));
                user.setPassword(rs.getString("password"));
                user.setRoleId(rs.getInt("role_id"));
                user.setCreatedDate(rs.getDate("created_date"));
                user.setStatus(rs.getInt("status"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setDob(rs.getDate("dob"));
                user.setGender(rs.getInt("gender"));
                user.setFirstPhone(rs.getString("first_phone"));
                user.setSecondPhone(rs.getString("second_phone"));
                user.setSecondaryEmail(rs.getString("secondary_email"));
                user.setImageUrl(rs.getString("image_url"));
                user.setPreferContact(rs.getString("prefer_contact"));
                user.setAddress(rs.getString("address"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if the user is not found
    }

    // Method to update user details
    public void updateUser(User user) {
        String query = "UPDATE User SET primary_email = ?, created_date = ?, first_name = ?, last_name = ?, dob = ?, gender = ?, first_phone = ?, second_phone = ?, secondary_email = ?, image_url = ?, prefer_contact = ?, address = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getPrimaryEmail());
            ps.setDate(2, new java.sql.Date(user.getCreatedDate().getTime()));
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setDate(5, new java.sql.Date(user.getDob().getTime()));
            ps.setInt(6, user.getGender());
            ps.setString(7, user.getFirstPhone());
            ps.setString(8, user.getSecondPhone());
            ps.setString(9, user.getSecondaryEmail());
            ps.setString(10, user.getImageUrl());
            ps.setString(11, user.getPreferContact());
            ps.setString(12, user.getAddress());
            ps.setInt(13, user.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get a list of experts
    public List<User> getExperts() {
        List<User> expertsList = new ArrayList<>();
        String query = "SELECT * FROM User WHERE role_id = (SELECT id FROM Setting WHERE value = 'Expert' AND setting_type_id = 1)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User expert = new User();
                expert.setId(rs.getInt("id"));
                expert.setPrimaryEmail(rs.getString("primary_email"));
                expert.setFirstName(rs.getString("first_name"));
                expert.setLastName(rs.getString("last_name"));
                expert.setImageUrl(rs.getString("image_url"));
                expertsList.add(expert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expertsList;
    }
    
    
}
