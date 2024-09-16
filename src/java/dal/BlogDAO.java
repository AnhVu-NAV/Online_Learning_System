/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Blogs;

public class BlogDAO extends DBContext {

    public BlogDAO() {
        super(); // Call the constructor of DBContext to initialize the connection
    }

    public List<Blogs> getPaginatedBlogs(int offset, int noOfRecords) {
        List<Blogs> list = new ArrayList<>();
        // Join với bảng User để lấy thêm authorName
        String query = "SELECT b.*, CONCAT(u.first_name, ' ', u.last_name) AS authorName "
                + "FROM Blog b "
                + "JOIN User u ON b.author_id = u.id "
                + "ORDER BY b.updated_date DESC "
                + "LIMIT ?, ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, offset);
            pstmt.setInt(2, noOfRecords);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Blogs blog = new Blogs();
                // Set the blog properties here according to your model
                blog.setBlogId(rs.getInt("id"));
                blog.setAuthorId(rs.getInt("author_id"));
                blog.setTitle(rs.getString("title"));
                blog.setContent(rs.getString("content"));
                blog.setBriefInfo(rs.getString("brief_info"));
                blog.setThumbnailUrl(rs.getString("thumbnail_url"));
                blog.setCreatedDate(rs.getTimestamp("created_date"));
                blog.setUpdatedDate(rs.getTimestamp("updated_date"));

                // Assuming you have a method to set the authorName
                blog.setAuthorName(rs.getString("authorName")); // Uncomment if you have this in the query

                list.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getNoOfRecords() {
        int noOfRecords = 0;
        String query = "SELECT COUNT(*) FROM blog";

        try (PreparedStatement pstmt = connection.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                noOfRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return noOfRecords;
    }
}
