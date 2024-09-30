/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.Vector;
import model.Slider;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class SliderDAO extends DBContext {

    // <editor-fold defaultstate="collapsed" desc="Get Methods">
    public Vector<Slider> getAllSlider() {
        Vector<Slider> list = new Vector();
        String sql = "SELECT id ,image_url ,author_id ,backlink_url ,status\n"
                + "FROM Slider";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    Slider slider = new Slider();
                    slider.setId(rs.getInt("id"));
                    slider.setImageURL(rs.getString("image_url"));
                    slider.setAuthorId(rs.getInt("author_id"));
                    slider.setBacklinkURL(rs.getString("backlink_url"));
                    slider.setStatus(rs.getInt("status"));
                    list.add(slider);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    public Slider getSliderById(int id) {
        Slider slider = new Slider();
        String sql = "SELECT id ,image_url ,author_id ,backlink_url ,status\n"
                + "FROM Slider\n"
                + "WHERE id = ?";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, id);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    slider.setId(rs.getInt("id"));
                    slider.setImageURL(rs.getString("image_url"));
                    slider.setAuthorId(rs.getInt("author_id"));
                    slider.setBacklinkURL(rs.getString("backlink_url"));
                    slider.setStatus(rs.getInt("status"));
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return slider;
    }

    public Vector<Slider> getSliderBySearching(String request) {
        Vector<Slider> list = new Vector();
        String sql = "select * from Slider\n"
                + "where id = ? or image_url like ? or author_id like ? or backlink_url like ? or status like ?; ";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            String string = "%" + request + "%";
            pre.setString(1, request);
            pre.setString(2, string);
            pre.setString(3, request);
            pre.setString(4, string);
            pre.setString(5, request);
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    Slider slider = new Slider();
                    slider.setId(rs.getInt("id"));
                    slider.setAuthorId(rs.getInt("author_id"));
                    slider.setImageURL(rs.getString("image_url"));
                    slider.setBacklinkURL(rs.getString("backlink_url"));
                    slider.setStatus(rs.getInt("status"));
                    list.add(slider);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    public Vector<Slider> getSliderByOrder(String query) {
        Vector<Slider> vector = new Vector<Slider>();
        String sql = "select * from Slider " + query;
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            try (ResultSet rs = pre.executeQuery(sql)) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int authorId = rs.getInt("author_id");
                    String image_url = rs.getString("image_url");
                    String backlink_url = rs.getString("backlink_url");
                    int status = rs.getInt("status");
                    Slider obj = new Slider(authorId, image_url, backlink_url, status);
                    obj.setId(id);
                    vector.add(obj);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return vector;
    }
    // </editor-fold>

    public void updateSliderById(int id, Slider slider) {
        String sql = "UPDATE Slider\n"
                + "SET image_url = ?, author_id = ?, backlink_url = ?, status = ?\n"
                + "where id = ?";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, slider.getImageURL());
            pre.setInt(2, slider.getAuthorId());
            pre.setString(3, slider.getBacklinkURL());
            pre.setInt(4, slider.getStatus());
            pre.setInt(5, id);
            pre.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteSlider(int id) {
        String sql = "delete from Slider where id = ?;";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, id);
            pre.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void insertSlider(Slider slider) {
        String sql = "INSERT INTO Slider\n"
                + "(image_url\n"
                + ",author_id\n"
                + ",backlink_url\n"
                + ",status)\n"
                + "VALUES (? ,? ,? ,?)";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, slider.getImageURL());
            pre.setInt(2, slider.getAuthorId());
            pre.setString(3, slider.getBacklinkURL());
            pre.setInt(4, slider.getStatus());
            pre.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
