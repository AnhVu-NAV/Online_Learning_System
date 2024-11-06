/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
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

    public Vector<PricePackage> getPricePackages(String sql) {
        Vector<PricePackage> vector = new Vector<PricePackage>();
        try {
            Statement state = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                int courseId = rs.getInt(2);
                String title = rs.getString(3);
                int price = rs.getInt(4);
                int salePrice = rs.getInt(5);
                Date saleStartDate = rs.getDate(6);
                Date saleEndDate = rs.getDate(7);
                PricePackage obj = new PricePackage(id, courseId, title, price, salePrice, saleStartDate, saleEndDate);
                vector.add(obj);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;

    }
}
