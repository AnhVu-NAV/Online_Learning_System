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
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
>>>>>>> main
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
            String pass = "123456"; // change this to your MySQL password
            String url = "jdbc:mysql://localhost:3306/shopcard"; // change mydb to your database name
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
        new DBContext(); // Just to test the connection
<<<<<<< HEAD
//        if (connection == null) {
//            System.out.println("null"); 
//        }
=======
>>>>>>> main
    }
}
