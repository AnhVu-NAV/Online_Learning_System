/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.SaleNote;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import util.DataConvert;

/**
 *
 * @author 84941
 */
public class SaleNoteDAO extends DBContext{

    DataConvert dc = new DataConvert();

    // Insert new sale note into database. Attribute id is AUTO_INCREMENT 
    public int insertSaleNote(SaleNote obj) {
        int n = 0;
        String sql = "INSERT INTO SaleNote (text_content, created_date, update_date) VALUES (?, ?, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, obj.getTextContent());
            pre.setDate(2, dc.UtilDateToSqlDate(obj.getCreatedDate()));
            pre.setDate(3, dc.UtilDateToSqlDate(obj.getUpdatedDate()));
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    // Get a list of sale notes based on specific conditions
    public Vector<SaleNote> getSaleNotes(String sql) {
        Vector<SaleNote> vector = new Vector<SaleNote>();
        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String textContent = rs.getString(2);
                Date createdDate = rs.getDate(3);
                Date updateDate = rs.getDate(4);
                SaleNote obj = new SaleNote(id, textContent, createdDate, updateDate);
                vector.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    // Get all the sale notes in the database 
    public Vector<SaleNote> getAll() {
        Vector<SaleNote> vector = new Vector<>();
        String sql = "SELECT id, text_content, created_date, update_date FROM SaleNote";
        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String textContent = rs.getString(2);
                Date createdDate = rs.getDate(3);
                Date updateDate = rs.getDate(4);
                SaleNote obj = new SaleNote(id, textContent, createdDate, updateDate);
                vector.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    // Update sale note information based on id
    public int updateSaleNote(SaleNote obj) {
        int n = 0;
        String sql = "UPDATE SaleNote SET text_content = ?, created_date = ?, update_date = ? WHERE id = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, obj.getTextContent());
            pre.setDate(2, dc.UtilDateToSqlDate(obj.getCreatedDate()));
            pre.setDate(3, dc.UtilDateToSqlDate(obj.getUpdatedDate()));
            pre.setInt(4, obj.getId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    // Get a sale note by id
    public SaleNote getSaleNoteById(int search_id) {
        String sql = "SELECT id, text_content, created_date, update_date FROM SaleNote WHERE id = " + search_id;

        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            if (rs.next()) {
                int id = rs.getInt(1);
                String textContent = rs.getString(2);
                Date createdDate = rs.getDate(3);
                Date updateDate = rs.getDate(4);
                return new SaleNote(id, textContent, createdDate, updateDate);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
