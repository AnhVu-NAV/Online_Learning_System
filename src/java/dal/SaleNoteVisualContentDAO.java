/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.SaleNoteVisualContent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import util.DataConvert;

/**
 *
 * @author 84941
 */
public class SaleNoteVisualContentDAO extends DBContext {

    //VuLH
    DataConvert dc = new DataConvert();

    // Insert new visual content into the database. Attribute id is AUTO_INCREMENT 
    public int insertVisualContent(SaleNoteVisualContent obj) {
        int n = 0;
        String sql = "INSERT INTO SaleNoteVisualContent (content, type, description) VALUES (?, ?, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, obj.getContent());
            pre.setInt(2, obj.isType() ? 1 : 0); // Convert boolean to int
            pre.setString(3, obj.getDescription());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    // Get a list of visual contents based on specific conditions
    public Vector<SaleNoteVisualContent> getVisualContents(String sql) {
        Vector<SaleNoteVisualContent> vector = new Vector<SaleNoteVisualContent>();
        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String content = rs.getString(2);
                boolean type = rs.getInt(3) == 1; // Convert int back to boolean
                String description = rs.getString(4);
                SaleNoteVisualContent obj = new SaleNoteVisualContent(id, content, type, description);
                vector.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    // Get all visual contents in the database 
    public Vector<SaleNoteVisualContent> getAll() {
        Vector<SaleNoteVisualContent> vector = new Vector<>();
        String sql = "SELECT id, content, type, description FROM SaleNoteVisualContent";
        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String content = rs.getString(2);
                boolean type = rs.getInt(3) == 1; // Convert int back to boolean
                String description = rs.getString(4);
                SaleNoteVisualContent obj = new SaleNoteVisualContent(id, content, type, description);
                vector.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    // Update visual content information based on id
    public int updateVisualContent(SaleNoteVisualContent obj) {
        int n = 0;
        String sql = "UPDATE SaleNoteVisualContent SET content = ?, type = ?, description = ? WHERE id = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, obj.getContent());
            pre.setInt(2, obj.isType() ? 1 : 0); // Convert boolean to int
            pre.setString(3, obj.getDescription());
            pre.setInt(4, obj.getId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    // Get visual content by id
    public SaleNoteVisualContent getVisualContentById(int search_id) {
        String sql = "SELECT id, content, type, description FROM SaleNoteVisualContent WHERE id = " + search_id;

        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            if (rs.next()) {
                int id = rs.getInt(1);
                String content = rs.getString(2);
                boolean type = rs.getInt(3) == 1; // Convert int back to boolean
                String description = rs.getString(4);
                return new SaleNoteVisualContent(id, content, type, description);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
