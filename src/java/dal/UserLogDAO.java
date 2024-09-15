/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import model.UserLog;
import utility.DataConvert;
/**
 *
 * @author 84941
 */
public class UserLogDAO extends DBContext{
    DataConvert dc = new DataConvert();
    //insert new setting type into database. Attribute id is AUTO_INCREMENT 
    public int insertUserLog(UserLog obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[UserLogs]\n"
                + "           ([account_id]\n"
                + "           ,[created_date]\n"
                + "           ,[type_id]\n"
                + "     VALUES\n"
                + "           (?,?,?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, obj.getAccount_id());
            pre.setDate(2, dc.UtilDateToSqlDate(obj.getCreated_date()));
            pre.setInt(3, obj.getType_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;

    }
    // get an user log list based on specific conditions
    public Vector<UserLog> getUserLog(String sql) {
        Vector<UserLog> vector = new Vector<UserLog>();
        try {
            Statement state = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                int account_id = rs.getInt(2);
                Date created_date = rs.getDate(3);
                int type_id = rs.getInt(4);               
                UserLog obj = new UserLog(id, account_id, created_date, type_id);
                vector.add(obj);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
        
    }
    // get all the setting types in the database
    public Vector<UserLog> getAll() {
        Vector<UserLog> vector = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[account_id]\n"
                + "      ,[created_date]\n"
                + "      ,[type_id]\n"
                + "  FROM [dbo].[UserLogs]";  

        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                int account_id = rs.getInt(2);
                Date created_date = rs.getDate(3);
                int type_id = rs.getInt(4);
                UserLog obj = new UserLog(id, account_id, created_date, type_id);
                vector.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    
}
