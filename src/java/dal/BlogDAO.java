/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import com.sun.jdi.connect.spi.Connection;
import java.util.ArrayList;
import java.util.List;
import model.Blogs;

/**
 *
 * @author mocun
 */
public class BlogDAO {
    private Connection connection;

    public BlogDAO() {
        // Initialize your database connection here
        connection = DatabaseConnection.getConnection();
    }

    public List<Blogs> getPaginatedBlogs(int offset, int noOfRecords) {
        List<Blogs> list = new ArrayList<>();
        String query = "SELECT * FROM Blogs ORDER BY updated_at DESC LIMIT ?, ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, offset);
            pstmt.setInt(2, noOfRecords);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Blogs blog = new Blogs();
                // Initialize your blog object from the result set here
                list.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getNoOfRecords() {
        int noOfRecords = 0;
        String query = "SELECT COUNT(*) FROM Blogs";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                noOfRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return noOfRecords;
    }
}
