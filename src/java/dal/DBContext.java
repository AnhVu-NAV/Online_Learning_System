/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AnhVuNAV
 */
public class DBContext {

    protected static Connection connection;

    public DBContext() {
        try {
            String user = "root"; // change this to your MySQL username
            String pass = "hoan2709"; // change this to your MySQL password
            String url = "jdbc:mysql://localhost:3306/Learnik"; // change mydb to your database name 
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);

            // Thêm shutdown hook để đóng kết nối
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    if (connection != null && !connection.isClosed()) {
                        connection.close();
                        System.out.println(connection);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }));

            // Giả lập một số hoạt động
            Thread.sleep(1000); // Chờ 10 giây để xem hoạt động
        } catch (ClassNotFoundException | InterruptedException | SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public static void main(String[] args) {
        new DBContext(); // Just to test the connection
    }
}
