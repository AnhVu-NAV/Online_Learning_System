/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import model.PricePackage;

/**
 *
 * @author 84941
 */
public class PricePackageDAO extends DBContext {

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
