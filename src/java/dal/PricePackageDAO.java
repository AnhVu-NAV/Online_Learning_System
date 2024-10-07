/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.PricePackage;

/**
 *
 * @author AnhVuNAV
 */
public class PricePackageDAO extends DBContext{
    // Lấy danh sách gói giá theo courseId
    public List<PricePackage> getPricePackagesByCourseId(int courseId) {
        List<PricePackage> pricePackages = new ArrayList<>();
        String sql = "SELECT * FROM PricePackage WHERE course_id = ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PricePackage pricePackage = new PricePackage();
                pricePackage.setId(rs.getInt("id"));
                pricePackage.setTitle(rs.getString("title"));
                pricePackage.setPrice((int) rs.getDouble("price"));
                pricePackage.setSalePrice((int) rs.getDouble("sale_price"));
                pricePackages.add(pricePackage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pricePackages;
    }
}
