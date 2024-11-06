package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 * UserDAO class for accessing and managing user data in the database.
 * 
 * @author AnhVuNAV
 */
public class UserDAO extends DBContext {

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
