package dal;  

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
}