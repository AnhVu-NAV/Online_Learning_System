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
public class PricePackageDAO extends DBContext {

    //VuNA
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

    //VuLH
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

    public int getPriceById(int pricePackageId) throws SQLException {
        String sql = "SELECT sale_price FROM PricePackage WHERE id = ?";
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pricePackageId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("sale_price");
                }
            }
        }
        throw new SQLException("PricePackage not found for ID: " + pricePackageId);
    }
    
    /**
     * Lấy thông tin gói giá theo ID.
     *
     * @param id ID của gói giá
     * @return Đối tượng PricePackage hoặc null nếu không tìm thấy
     * @throws Exception Nếu có lỗi trong quá trình truy vấn
     */
    public PricePackage getPricePackageById(int id) throws Exception {
        String query = "SELECT * FROM PricePackage WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    PricePackage pricePackage = new PricePackage();
                    pricePackage.setId(rs.getInt("id"));
                    pricePackage.setCourseId(rs.getInt("course_id"));
                    pricePackage.setTitle(rs.getString("title"));
                    pricePackage.setPrice(rs.getInt("price"));
                    pricePackage.setSalePrice(rs.getInt("sale_price"));
                    return pricePackage;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error while fetching price package by ID", e);
        }
        return null;
    }

}
