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
                + "           ,prefer_contact\n"
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
            pre.setBoolean(9, obj.isGender());
            pre.setString(10, obj.getFirstPhone());
            pre.setString(11, obj.getSecondPhone());
            pre.setString(12, obj.getSecondaryEmail());
            pre.setString(13, obj.getImageURL());
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
                boolean gender = rs.getBoolean(10);
                String firstPhone = rs.getString(11);
                String secondPhone = rs.getString(12);
                String secondaryEmail = rs.getString(13);
                String imageURL = rs.getString(14);
                String preferContact = rs.getString(15);
                User obj = new User(id, primaryEmail, password, roleId, createdDate, status, firstName, lastName, dob, gender, firstPhone, secondPhone, secondaryEmail, imageURL, preferContact);
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
                boolean gender = rs.getBoolean(10);
                String firstPhone = rs.getString(11);
                String secondPhone = rs.getString(12);
                String secondaryEmail = rs.getString(13);
                String imageURL = rs.getString(14);
                String preferContact = rs.getString(15);
                User obj = new User(id, primaryEmail, password, roleId, createdDate, status, firstName, lastName, dob, gender, firstPhone, secondPhone, secondaryEmail, imageURL, preferContact);
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
            pre.setBoolean(9, obj.isGender());
            pre.setString(10, obj.getFirstPhone());
            pre.setString(11, obj.getSecondPhone());
            pre.setString(12, obj.getSecondaryEmail());
            pre.setString(13, obj.getImageURL());
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
                boolean gender = rs.getBoolean(10);
                String firstPhone = rs.getString(11);
                String secondPhone = rs.getString(12);
                String secondaryEmail = rs.getString(13);
                String imageURL = rs.getString(14);
                String preferContact = rs.getString(15);
                User obj = new User(id, primaryEmail, password, roleId, createdDate, status, firstName, lastName, dob, gender, firstPhone, secondPhone, secondaryEmail, imageURL, preferContact);
                return obj;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;

    }
}
