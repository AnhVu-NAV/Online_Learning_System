/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import utility.DataConvert;

/**
 *
 * @author 84941
 */
public class AccountDAO extends DBContext {

    DataConvert dc = new DataConvert();

    //insert new account into database. Attribute id is AUTO_INCREMENT 
    public int insertAccount(Account obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Accounts]\n"
                + "           ([email]\n"
                + "           ,[first_name]\n"
                + "           ,[last_name]\n"
                + "           ,[password]\n"
                + "           ,[dob]\n"
                + "           ,[gender]\n"
                + "           ,[role_id]\n"
                + "           ,[created_date])\n"
                + "           ,[status])\n"
                + "           ,[phone_number])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, obj.getEmail());
            pre.setString(2, obj.getFirst_name());
            pre.setString(3, obj.getLast_name());
            pre.setString(4, obj.getPassword());
            pre.setDate(5, dc.UtilDateToSqlDate(obj.getDob()));
            pre.setBoolean(6, obj.isGender());
            pre.setInt(7, obj.getRole_id());
            pre.setDate(8, dc.UtilDateToSqlDate(obj.getCreated_date()));
            pre.setInt(9, obj.getStatus());
            pre.setString(10, obj.getPhone_number());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;

    }

    // get an account list based on specific conditions
    public Vector<Account> getAccounts(String sql) {
        Vector<Account> vector = new Vector<Account>();
        try {
            Statement state = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String email = rs.getString(2);
                String first_name = rs.getString(3);
                String last_name = rs.getString(4);
                String password = rs.getString(5);
                Date dob = rs.getDate(6);
                Boolean gender = rs.getBoolean(7);
                int role_id = rs.getInt(8);
                Date created_date = rs.getDate(9);
                int status = rs.getInt(10);
                String phone_number = rs.getString(11);
                Account obj = new Account(id, email, first_name, last_name, password, dob, gender, role_id, created_date, status, phone_number);
                vector.add(obj);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;

    }

    // get all the accounts in the database except the admin account
    public Vector<Account> getAll() {
        Vector<Account> vector = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[email]\n"
                + "      ,[first_name]\n"
                + "      ,[last_name]\n"
                + "      ,[password]\n"
                + "      ,[dob]\n"
                + "      ,[gender]\n"
                + "      ,[role_id]\n"
                + "      ,[created_date]\n"
                + "      ,[status]\n"
                + "      ,[phone_number]\n"
                + "  FROM [dbo].[Accounts]"
                + "  WHERE [dbo].[Accounts].[role_id] <> 0";

        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String email = rs.getString(2);
                String first_name = rs.getString(3);
                String last_name = rs.getString(4);
                String password = rs.getString(5);
                Date dob = rs.getDate(6);
                Boolean gender = rs.getBoolean(7);
                int role_id = rs.getInt(8);
                Date created_date = rs.getDate(9);
                int status = rs.getInt(10);
                String phone_number = rs.getString(11);
                Account obj = new Account(id, email, first_name, last_name, password, dob, gender, role_id, created_date, status, phone_number);
                vector.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    

    // update Account information based on id
    public int updateAccount(Account obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[Accounts]\n"
                + "   SET [email] = ?\n"
                + "      ,[first_name] = ?\n"
                + "      ,[last_name] = ?\n"
                + "      ,[password] = ?\n"
                + "      ,[dob] = ?\n"
                + "      ,[gender] = ?\n"
                + "      ,[role_id] = ?\n"
                + "      ,[created_date] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[phone_number] = ?\n"
                + " WHERE [id] = ?";
        try {

            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, obj.getEmail());
            pre.setString(2, obj.getFirst_name());
            pre.setString(3, obj.getLast_name());
            pre.setString(4, obj.getPassword());
            pre.setDate(5, dc.UtilDateToSqlDate(obj.getDob()));
            pre.setBoolean(6, obj.isGender());
            pre.setInt(7, obj.getRole_id());
            pre.setDate(8, dc.UtilDateToSqlDate(obj.getCreated_date()));
            pre.setInt(9, obj.getStatus());
            pre.setString(10, obj.getPhone_number());
            pre.setInt(11, obj.getId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Account getAccountById(int search_id) {
        Vector<Account> vector = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[email]\n"
                + "      ,[first_name]\n"
                + "      ,[last_name]\n"
                + "      ,[password]\n"
                + "      ,[dob]\n"
                + "      ,[gender]\n"
                + "      ,[role_id]\n"
                + "      ,[created_date]\n"
                + "      ,[status]\n"
                + "      ,[phone_number]\n"
                + "  FROM [dbo].[Accounts]"
                + "  WHERE [dbo].[Accounts].[id] = "+search_id;

        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String email = rs.getString(2);
                String first_name = rs.getString(3);
                String last_name = rs.getString(4);
                String password = rs.getString(5);
                Date dob = rs.getDate(6);
                Boolean gender = rs.getBoolean(7);
                int role_id = rs.getInt(8);
                Date created_date = rs.getDate(9);
                int status = rs.getInt(10);
                String phone_number = rs.getString(11);
                Account obj = new Account(id, email, first_name, last_name, password, dob, gender, role_id, created_date, status, phone_number);
                return obj;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
        
    }
}
