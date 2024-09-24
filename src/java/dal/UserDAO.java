/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

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
 * @author 84941
 */
public class UserDAO extends DBContext {

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
