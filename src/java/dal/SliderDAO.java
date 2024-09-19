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

    // <editor-fold defaultstate="collapsed" desc="Declare variable and constructor">
    private static AccountDAO adao;

    public SliderDAO() {
        this.adao = new AccountDAO();
    }
    // </editor-fold>

    public Vector<Slider> getAllSlider() throws Exception {
        Vector<Slider> list = new Vector();
        String sql = """
                     SELECT id ,image_url ,author_id ,backlink_url ,status
                       FROM Slider
                     """;
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            Slider slider = new Slider();
            slider.setId(rs.getInt("id"));
            slider.setImage_url(rs.getString("image_url"));
            slider.setAccount(adao.getAccountById(rs.getInt("author_id")));
            slider.setBacklink_url(rs.getString("backlink_url"));
            slider.setStatus(rs.getInt("status"));
            list.add(slider);
        }
        rs.close();
        pre.close();
        return list;
    }

    public Slider getSliderById(int id) throws Exception {
        Slider slider = new Slider();
        String sql = """
                     SELECT id ,image_url ,author_id ,backlink_url ,status
                     FROM Slider
                     WHERE id = ?
                     """;
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        pre.setInt(1, id);
        if (rs.next()) {
            slider.setId(rs.getInt("id"));
            slider.setImage_url(rs.getString("image_url"));
            slider.setAccount(adao.getAccountById(rs.getInt("author_id")));
            slider.setBacklink_url(rs.getString("backlink_url"));
            slider.setStatus(rs.getInt("status"));
            return slider;
        }
        return null;
    }

    public void updateSlider(Slider slider) throws Exception {
        String sql = """
                     SELECT [id]
                           ,[image_url]
                           ,[author_id]
                           ,[backlink_url]
                           ,[status]
                       FROM [dbo].[Slider]
                     """;
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            Slider newSlider = new Slider();
            newSlider.setId(rs.getInt("id"));
            newSlider.setImage_url(rs.getString("image_url"));
            newSlider.setAccount(adao.getAccountById(rs.getInt("author_id")));
            newSlider.setBacklink_url(rs.getString("backlink_url"));
            newSlider.setStatus(rs.getInt("status"));
        }
        rs.close();
        pre.close();
    }

    public void deleteSlider(int id) throws Exception {
        String sql = """
                     SELECT [id]
                           ,[image_url]
                           ,[author_id]
                           ,[backlink_url]
                           ,[status]
                       FROM [dbo].[Slider]
                     """;
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            Slider slider = new Slider();
            slider.setId(rs.getInt("id"));
            slider.setImage_url(rs.getString("image_url"));
            slider.setAccount(adao.getAccountById(rs.getInt("author_id")));
            slider.setBacklink_url(rs.getString("backlink_url"));
            slider.setStatus(rs.getInt("status"));
        }
        rs.close();
        pre.close();
    }

    public void insertSlider(Slider slider) throws Exception {
        String sql = """
                     INSERT INTO Slider
                                (image_url
                                ,author_id
                                ,backlink_url
                                ,status)
                     VALUES (? ,? ,? ,?)
                     """;
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, slider.getImage_url());
        pre.setInt(2, slider.getAccount().getId());
        pre.setString(3, slider.getBacklink_url());
        pre.setInt(4, slider.getStatus());
        pre.executeUpdate();
        pre.close();
    }

    public static void main(String[] args) {
        SliderDAO sdao = new SliderDAO();
        Slider slider = new Slider();
        try {
            slider.setId(1); 
            slider.setAccount(adao.getAccountByEmail("b@gmail.com"));
            slider.setBacklink_url("https://");
            slider.setImage_url("https://");
            slider.setStatus(0);
            sdao.insertSlider(slider); 
            System.out.println(sdao.getSliderById(1).toString());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
