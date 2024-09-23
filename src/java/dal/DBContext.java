<<<<<<< HEAD
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
<<<<<<< HEAD

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

=======
=======
package dal;  

>>>>>>> fbf38e88c7423732c03f084d6c211a2ea03af5a2
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
<<<<<<< HEAD
>>>>>>> main
=======
<<<<<<< HEAD
>>>>>>> fbf38e88c7423732c03f084d6c211a2ea03af5a2
/**
 *
 * @author AnhVuNAV
 */
public class DBContext {
<<<<<<< HEAD

    protected Connection connection;

    public DBContext() {
        try {
            String user = "root"; // change this to your MySQL username
            String pass = "hoan2709"; // change this to your MySQL password
            String url = "jdbc:mysql://localhost:3306/Learnik"; // change mydb to your database name 
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println(connection);
        } catch (ClassNotFoundException | SQLException ex) {
=======
    protected Connection connection;
    public DBContext() {
        try {
            String user = "root"; // change this to your MySQL username
            String pass = "12345"; // change this to your MySQL password
            String url = "jdbc:mysql://localhost:3306/Learnik"; // change mydb to your database name
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println(connection);
        } catch (Exception ex) {
>>>>>>> main
            ex.printStackTrace();
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
<<<<<<< HEAD
        new DBContext(); // Just to test the connection
<<<<<<< HEAD
//        if (connection == null) {
//            System.out.println("null"); 
//        }
=======
>>>>>>> main
=======
        DBContext dbContext = new DBContext();
        if (dbContext.connection != null) {
            System.out.println("Kết nối thành công với cơ sở dữ liệu!");
        } else {
            System.out.println("Kết nối thất bại!");
        }
=======
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
import java.util.logging.Level;  
import java.util.logging.Logger;  

public class DBContext {  
    protected Connection connection;  

    public DBContext() {  
        try {  
            String user = "root"; 
            String pass = "123456"; 
            String url = "jdbc:mysql://localhost:3306/Learnik"; 

            Class.forName("com.mysql.cj.jdbc.Driver");  
            connection = DriverManager.getConnection(url, user, pass);  
            System.out.println("Connect successfully: " + connection);  
        } catch (ClassNotFoundException e) {  
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "Not found Driver", e);  
        } catch (SQLException e) {  
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "Error to connect database", e);  
        }  
    }  

    public Connection getConnection() {  
        return connection;  
    }  

    public void closeConnection() {  
        if (connection != null) {  
            try {  
                connection.close();  
            } catch (SQLException e) {  
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "Lỗi khi đóng kết nối", e);  
            }  
        }  
    }  

    public static void main(String[] args) {  
        DBContext dbContext = new DBContext();  // Chỉ để kiểm tra kết nối  
        dbContext.closeConnection(); // Đóng kết nối sau kiểm tra  
    }  
    
    public ResultSet getData(String sql) {
        ResultSet rs = null;
        try {
            Statement state = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
>>>>>>> 4d0982c745e6175e3e5a1a5b1ff350c362a92147
>>>>>>> fbf38e88c7423732c03f084d6c211a2ea03af5a2
    }
}
