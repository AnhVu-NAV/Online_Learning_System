/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import model.User;
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

    DataConvert dc = new DataConvert();

    //insert new user into database. Attribute id is AUTO_INCREMENT 
    public int insertUser(User obj) {
        int n = 0;
        String sql = "INSERT INTO User\n"
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
            pre.setString(2, obj.getFirstName());
            pre.setString(3, obj.getLastName());
            pre.setString(4, obj.getPassword());
            pre.setDate(5, dc.UtilDateToSqlDate(obj.getDob()));
            pre.setInt(6, obj.getRoleId());
            pre.setDate(7, dc.UtilDateToSqlDate(obj.getCreatedDate()));
            pre.setInt(8, obj.getStatus());
            pre.setString(9, obj.getPhone());
            pre.setBoolean(10, obj.isGender());
            pre.setString(11, obj.getAddress());
            pre.setString(12, obj.getImageURL());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;

    }

    // get an user list based on specific conditions
    public Vector<User> getUsers(String sql) {
        Vector<User> vector = new Vector<User>();
        try {
            Statement state = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String email = rs.getString(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String password = rs.getString(5);
                Date dob = rs.getDate(6);
                int roleId = rs.getInt(7);
                Date createdDate = rs.getDate(8);
                int status = rs.getInt(9);
                String phone = rs.getString(10);
                Boolean gender = rs.getBoolean(11);
                String address = rs.getString(12);
                String imageURL = rs.getString(13);
                User obj = new User(id, email, firstName, lastName, password, dob, roleId, createdDate, status, phone, gender, address, imageURL);
                vector.add(obj);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;

    }

    // get all the user in the database 
    public Vector<User> getAll() {
        Vector<User> vector = new Vector<>();
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
                + "  FROM User";
        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String email = rs.getString(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String password = rs.getString(5);
                Date dob = rs.getDate(6);
                int roleId = rs.getInt(7);
                Date createdDate = rs.getDate(8);
                int status = rs.getInt(9);
                String phone = rs.getString(10);
                Boolean gender = rs.getBoolean(11);
                String address = rs.getString(12);
                String imageURL = rs.getString(13);
                User obj = new User(id, email, firstName, lastName, password, dob, roleId, createdDate, status, phone, gender, address, imageURL);
                vector.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    // update user information based on id
    public int updateUser(User obj) {
        int n = 0;
        String sql = "UPDATE User\n"
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
            pre.setString(2, obj.getFirstName());
            pre.setString(3, obj.getLastName());
            pre.setString(4, obj.getPassword());
            pre.setDate(5, dc.UtilDateToSqlDate(obj.getDob()));
            pre.setInt(6, obj.getRoleId());
            pre.setDate(7, dc.UtilDateToSqlDate(obj.getCreatedDate()));
            pre.setInt(8, obj.getStatus());
            pre.setString(9, obj.getPhone());
            pre.setBoolean(10, obj.isGender());
            pre.setString(11, obj.getAddress());
            pre.setString(12, obj.getImageURL());
            pre.setInt(13, obj.getId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public User getUserById(int search_id) {
        Vector<User> vector = new Vector<>();
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
                + "  FROM User"
                + "  WHERE User.id = " + search_id;

        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String email = rs.getString(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String password = rs.getString(5);
                Date dob = rs.getDate(6);
                int roleId = rs.getInt(7);
                Date createdDate = rs.getDate(8);
                int status = rs.getInt(9);
                String phone = rs.getString(10);
                Boolean gender = rs.getBoolean(11);
                String address = rs.getString(12);
                String imageURL = rs.getString(13);
                User obj = new User(id, email, firstName, lastName, password, dob, roleId, createdDate, status, phone, gender, address, imageURL);
                return obj;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;

    }
}
