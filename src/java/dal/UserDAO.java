/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

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
}
