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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author AnhVuNAV
 */
public class UserDAO extends DBContext {

    public Vector<User> getAll() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Vector<User> users = new Vector<>();
        String sql = "SELECT * FROM [user]";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String password = rs.getString("password");
                Date dob = rs.getDate("dob");
                int role_id = rs.getInt("role_id");
                Date created_date = rs.getDate("created_date");
                int status = rs.getInt("status");
                String phone = rs.getString("phone");
                int gender = rs.getInt("gender");
                String address = rs.getString("address");
                String image_url = rs.getString("image_url");
                User u = new User(id, email, first_name, last_name, password, dob, role_id, created_date, status, phone, gender, address, image_url);
                users.add(u);
            }
            return users;

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                rs.close();
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public User getOne(String email, String password, Integer status) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM user AS u "
                + "INNER JOIN setting AS r ON r.id = u.role_id "
                + "WHERE u.email = ? AND u.password = ? AND u.status = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            stm.setInt(3, status);

            rs = stm.executeQuery();

            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setEmail(email);
                u.setPassword(password);
                u.setStatus(status);
                u.setFirst_name(rs.getString("first_name"));
                u.setLast_name(rs.getString("last_name"));
                u.setDob(rs.getDate("dob"));
                u.setRole_id(rs.getInt("role_id"));
                u.setCreated_date(rs.getDate("created_date"));
                u.setPhone(rs.getString("phone"));
                u.setGender(rs.getInt("gender"));
                u.setAddress(rs.getString("address"));
                u.setImage_url(rs.getString("image_url"));

                System.out.println(u);
                return u;

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    //get all customer (role_id = 1)
    public Vector<User> getAllCustomer() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Vector<User> users = new Vector<>();
        String sql = "SELECT * FROM [user] WHERE role_id = 1";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String password = rs.getString("password");
                Date dob = rs.getDate("dob");
                int role_id = rs.getInt("role_id");
                Date created_date = rs.getDate("created_date");
                int status = rs.getInt("status");
                String phone = rs.getString("phone");
                int gender = rs.getInt("gender");
                String address = rs.getString("address");
                String image_url = rs.getString("image_url");

                User u = new User(id, email, first_name, last_name, password, dob, role_id, created_date, status, phone, gender, address, image_url);
                users.add(u);
            }
            return users;

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                rs.close();
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public User getUserById(int userId) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM [user]\n"
                + "WHERE [id] = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            rs = stm.executeQuery();

            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setStatus(rs.getInt("status"));
                u.setFirst_name(rs.getString("first_name"));
                u.setLast_name(rs.getString("last_name"));
                u.setDob(rs.getDate("dob"));
                u.setRole_id(rs.getInt("role_id"));
                u.setCreated_date(rs.getDate("created_date"));
                u.setPhone(rs.getString("phone"));
                u.setGender(rs.getInt("gender"));
                u.setAddress(rs.getString("address"));
                u.setImage_url(rs.getString("image_url"));
                System.out.println(u);
                return u;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public Vector<User> getCustomerByName(String name) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Vector<User> customers = new Vector<>();
        String sql = "select * from [user]\n"
                + "where role_id = 1 and [first_name] OR [last_name] LIKE ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            rs = stm.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setStatus(rs.getInt("status"));
                u.setFirst_name(rs.getString("first_name"));
                u.setLast_name(rs.getString("last_name"));
                u.setDob(rs.getDate("dob"));
                u.setRole_id(rs.getInt("role_id"));
                u.setCreated_date(rs.getDate("created_date"));
                u.setPhone(rs.getString("phone"));
                u.setGender(rs.getInt("gender"));
                u.setAddress(rs.getString("address"));
                u.setImage_url(rs.getString("image_url"));
                System.out.println(u);

                customers.add(u);
            }
            return customers;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

//    public void insert(User user) {
//        PreparedStatement stm = null;
//
//        String sql = "INSERT INTO [dbo].[user]\n"
//                + "           ([username]\n"
//                + "           ,[password]\n"
//                + "           ,[fullname]\n"
//                + "           ,[email]\n"
//                + "           ,[phone]\n"
//                + "           ,[address]\n"
//                + "           ,[role_id])\n"
//                + "     VALUES\n"
//                + "           (?,?,?,?,?,?,?)";
//        try {
//            stm = connection.prepareStatement(sql);
//            stm.setString(1, user.getUsername());
//            stm.setString(2, user.getPassword());
//            stm.setString(3, user.getFullname());
//            stm.setString(4, user.getEmail());
//            stm.setString(5, user.getPhone());
//            stm.setString(6, user.getAddress());
//            stm.setInt(7, user.getRole_id());
//            stm.executeUpdate();
//
//            System.out.println("Insert OK");
//
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDAO.class
//                    .getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                stm.close();
//                connection.close();
//
//            } catch (SQLException ex) {
//                Logger.getLogger(UserDAO.class
//                        .getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }

    public void banAnUser(int userId) {
        PreparedStatement stm = null;

        String sql = "UPDATE [dbo].[user] SET [status] = 1 WHERE id = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.executeUpdate();

            System.out.println("Banned userId = " + userId);

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void unbanUser(int userId) {
        String sql = "UPDATE [user] SET status = 0 WHERE id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, userId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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

}
