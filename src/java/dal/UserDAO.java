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

    //TuanNA
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
    public User getUserByID(int id) {
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

    public User getUser(int id) {
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
//                user.setGender(rs.getInt("gender"));
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
    public void updateUserProfile(User user) {
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

    //VuLH
    DataConvert dc = new DataConvert();

    //insert new user into database. Attribute id is AUTO_INCREMENT 
    public int insertUser(User obj) {
        int n = 0;
        String sql = "INSERT INTO User\n"
                + "           (primary_email\n"
                + "           ,password\n"
                + "           ,role_id\n"
                + "           ,created_date\n"
                + "           ,status\n"
                + "           ,first_name\n"
                + "           ,last_name\n"
                + "           ,dob\n"
                + "           ,gender\n"
                + "           ,first_phone\n"
                + "           ,second_phone\n"
                + "           ,secondary_email\n"
                + "           ,image_url\n"
                + "           ,prefer_contact)\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, obj.getPrimaryEmail());
            pre.setString(2, obj.getPassword());
            pre.setInt(3, obj.getRoleId());
            pre.setDate(4, dc.UtilDateToSqlDate(obj.getCreatedDate()));
            pre.setInt(5, obj.getStatus());
            pre.setString(6, obj.getFirstName());
            pre.setString(7, obj.getLastName());
            pre.setDate(8, dc.UtilDateToSqlDate(obj.getDob()));
            pre.setInt(9, obj.getGender());
            pre.setString(10, obj.getFirstPhone());
            pre.setString(11, obj.getSecondPhone());
            pre.setString(12, obj.getSecondaryEmail());
            pre.setString(13, obj.getImageUrl());
            pre.setString(14, obj.getPreferContact());
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
                String primaryEmail = rs.getString(2);
                String password = rs.getString(3);
                int roleId = rs.getInt(4);
                Date createdDate = rs.getDate(5);
                int status = rs.getInt(6);
                String firstName = rs.getString(7);
                String lastName = rs.getString(8);
                Date dob = rs.getDate(9);
                int gender = rs.getInt(10);
                String firstPhone = rs.getString(11);
                String secondPhone = rs.getString(12);
                String secondaryEmail = rs.getString(13);
                String imageUrl = rs.getString(14);
                String preferContact = rs.getString(15);
                User obj = new User(id, primaryEmail, password, roleId, createdDate, status, firstName, lastName, dob, gender, firstPhone, secondPhone, secondaryEmail, imageUrl, preferContact);
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
                + "      ,primary_email\n"
                + "      ,password\n"
                + "      ,role_id\n"
                + "      ,created_date\n"
                + "      ,status\n"
                + "      ,first_name\n"
                + "      ,last_name\n"
                + "      ,dob\n"
                + "      ,gender\n"
                + "      ,first_phone\n"
                + "      ,second_phone\n"
                + "      ,secondary_email\n"
                + "      ,image_url\n"
                + "      ,prefer_contact\n"
                + "  FROM User";
        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String primaryEmail = rs.getString(2);
                String password = rs.getString(3);
                int roleId = rs.getInt(4);
                Date createdDate = rs.getDate(5);
                int status = rs.getInt(6);
                String firstName = rs.getString(7);
                String lastName = rs.getString(8);
                Date dob = rs.getDate(9);
                int gender = rs.getInt(10);
                String firstPhone = rs.getString(11);
                String secondPhone = rs.getString(12);
                String secondaryEmail = rs.getString(13);
                String imageUrl = rs.getString(14);
                String preferContact = rs.getString(15);
                User obj = new User(id, primaryEmail, password, roleId, createdDate, status, firstName, lastName, dob, gender, firstPhone, secondPhone, secondaryEmail, imageUrl, preferContact);
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
                + "   SET primary_email = ?\n"
                + "      ,password = ?\n"
                + "      ,role_id = ?\n"
                + "      ,created_date = ?\n"
                + "      ,status = ?\n"
                + "      ,first_name = ?\n"
                + "      ,last_name = ?\n"
                + "      ,dob = ?\n"
                + "      ,gender = ?\n"
                + "      ,first_phone = ?\n"
                + "      ,second_phone = ?\n"
                + "      ,secondary_email = ?\n"
                + "      ,image_url = ?\n"
                + "      ,prefer_contact = ?\n"
                + " WHERE id = ?";
        try {

            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, obj.getPrimaryEmail());
            pre.setString(2, obj.getPassword());
            pre.setInt(3, obj.getRoleId());
            pre.setDate(4, dc.UtilDateToSqlDate(obj.getCreatedDate()));
            pre.setInt(5, obj.getStatus());
            pre.setString(6, obj.getFirstName());
            pre.setString(7, obj.getLastName());
            pre.setDate(8, dc.UtilDateToSqlDate(obj.getDob()));
            pre.setInt(9, obj.getGender());
            pre.setString(10, obj.getFirstPhone());
            pre.setString(11, obj.getSecondPhone());
            pre.setString(12, obj.getSecondaryEmail());
            pre.setString(13, obj.getImageUrl());
            pre.setString(14, obj.getPreferContact());
            pre.setInt(15, obj.getId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public User getUserById(int search_id) {
        String sql = "SELECT id\n"
                + "      ,primary_email\n"
                + "      ,password\n"
                + "      ,role_id\n"
                + "      ,created_date\n"
                + "      ,status\n"
                + "      ,first_name\n"
                + "      ,last_name\n"
                + "      ,dob\n"
                + "      ,gender\n"
                + "      ,first_phone\n"
                + "      ,second_phone\n"
                + "      ,secondary_email\n"
                + "      ,image_url\n"
                + "      ,prefer_contact\n"
                + "  FROM User"
                + "  WHERE User.id = " + search_id;

        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String primaryEmail = rs.getString(2);
                String password = rs.getString(3);
                int roleId = rs.getInt(4);
                Date createdDate = rs.getDate(5);
                int status = rs.getInt(6);
                String firstName = rs.getString(7);
                String lastName = rs.getString(8);
                Date dob = rs.getDate(9);
                int gender = rs.getInt(10);
                String firstPhone = rs.getString(11);
                String secondPhone = rs.getString(12);
                String secondaryEmail = rs.getString(13);
                String imageUrL = rs.getString(14);
                String preferContact = rs.getString(15);
                User obj = new User(id, primaryEmail, password, roleId, createdDate, status, firstName, lastName, dob, gender, firstPhone, secondPhone, secondaryEmail, imageUrL, preferContact);
                return obj;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    //HuyLVN
    public User getOne(String primaryEmail, String password, Integer status) {
        Logger logger = Logger.getLogger(UserDAO.class.getName());
        String sql = "SELECT * FROM user WHERE primary_email = ? AND password = ? AND status = ?";

        logger.info("Bắt đầu phương thức getOne với các tham số: primaryEmail=" + primaryEmail + ", status=" + status);

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, primaryEmail);
            stm.setString(2, password);
            stm.setInt(3, status);

            logger.info("Đã chuẩn bị câu truy vấn SQL: " + sql);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    logger.info("Người dùng được tìm thấy với primaryEmail: " + primaryEmail);
                    return User.builder()
                            .id(rs.getInt("id"))
                            .primaryEmail(rs.getString("primary_email"))
                            .password(rs.getString("password"))
                            .roleId(rs.getInt("role_id"))
                            .createdDate(rs.getDate("created_date"))
                            .status(rs.getInt("status"))
                            .firstName(rs.getString("first_name"))
                            .lastName(rs.getString("last_name"))
                            .dob(rs.getDate("dob"))
                            .gender(rs.getInt("gender"))
                            .firstPhone(rs.getString("first_phone"))
                            .secondPhone(rs.getString("second_phone"))
                            .secondaryEmail(rs.getString("secondary_email"))
                            .imageUrl(rs.getString("image_url"))
                            .preferContact(rs.getString("prefer_contact"))
                            .address(rs.getString("address"))
                            .build();
                } else {
                    logger.info("Không tìm thấy người dùng với primaryEmail: " + primaryEmail);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Lỗi SQL khi tìm người dùng với email: " + primaryEmail, ex);
        }
        return null;
    }

       // Check if email already exists
    public boolean isEmailExists(String primaryEmail) {
        String sql = "SELECT COUNT(*) FROM user WHERE primary_email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, primaryEmail);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    Logger.getLogger(UserDAO.class.getName()).log(Level.INFO, "Email check found {0} matches for email {1}", new Object[]{count, primaryEmail});
                    return count > 0; // Return true if count is greater than 0, meaning email exists
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, "SQL error while checking email: " + primaryEmail, ex);
        }
        return false; // Return false if an exception occurs or no rows are found
    }

    // Check if phone number already exists
    public boolean isPhoneExists(String firstPhone) {
        String sql = "SELECT COUNT(*) FROM user WHERE first_phone = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, firstPhone);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    Logger.getLogger(UserDAO.class.getName()).log(Level.INFO, "Phone check found {0} matches for phone {1}", new Object[]{count, firstPhone});
                    return count > 0; // Return true if count is greater than 0, meaning phone number exists
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, "SQL error while checking phone number: " + firstPhone, ex);
        }
        return false; // Return false if an exception occurs or no rows are found
    }

    // Method to create a new user
    public int createNewUser(User user) {
        String sql = "INSERT INTO user (primary_email, password, role_id, created_date, status, first_name, last_name, dob, gender, first_phone, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getPrimaryEmail());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getRoleId());
            stmt.setDate(4, new java.sql.Date(user.getCreatedDate().getTime()));
            stmt.setInt(5, user.getStatus());
            stmt.setString(6, user.getFirstName());
            stmt.setString(7, user.getLastName());
            stmt.setDate(8, new java.sql.Date(user.getDob().getTime()));
            stmt.setInt(9, user.getGender());
            stmt.setString(10, user.getFirstPhone());
            stmt.setString(11, user.getAddress());

            int rowsAffected = stmt.executeUpdate();
            Logger.getLogger(UserDAO.class.getName()).log(Level.INFO, "User created successfully with email {0}, rows affected: {1}", new Object[]{user.getPrimaryEmail(), rowsAffected});
            return rowsAffected;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, "SQL error while creating new user with primaryEmail=" + user.getPrimaryEmail(), ex);
            return 0;
        }
    }
    
}
