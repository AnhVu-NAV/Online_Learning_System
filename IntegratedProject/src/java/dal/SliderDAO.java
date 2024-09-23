/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.Vector;
import model.Slider;
import java.sql.*;
import model.Account;

/**
 *
 * @author ADMIN
 */
public class SliderDAO extends DBContext {

    // <editor-fold defaultstate="collapsed" desc="Declare variable and constructor">
    private static AccountDAO adao;

    public SliderDAO() {
        this.adao = new AccountDAO();
        new DBContext();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Get Methods">
    public Vector<Slider> getAllSlider() throws Exception {
        Vector<Slider> list = new Vector();
        String sql = "SELECT id ,image_url ,author_id ,backlink_url ,status\n"
                + " FROM Slider";
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
        String sql = "SELECT id ,image_url ,author_id ,backlink_url ,status\n"
                + " FROM Slider\n"
                + " WHERE id = ?";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
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

    public Vector<Slider> getSlderByRequest(String request) throws Exception {
        Vector<Slider> list = new Vector();
        String sql = "select * from Slider\n"
                + "where id = ? or image_url like ? or author_id like ? or backlink_url like ? or status like ?; ";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, request);
        pre.setString(2, request);
        pre.setString(3, request);
        pre.setString(4, request);
        pre.setString(5, request);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            Slider slider = new Slider();
            slider.setId(rs.getInt("id"));
            slider.setAccount(adao.getAccountById(rs.getInt("author_id")));
            slider.setImage_url(rs.getString("image_url"));
            slider.setBacklink_url(rs.getString("backlink_url"));
            slider.setStatus(rs.getInt("status"));
            list.add(slider);
        }
        rs.close();
        pre.close();
        return list;
    }

    public Vector<Slider> getSliders(String sql) throws Exception {
        Vector<Slider> vector = new Vector<Slider>();
        Statement state = connection.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = state.executeQuery(sql);
        AccountDAO adao = new AccountDAO();
        while (rs.next()) {
            int id = rs.getInt("id");
            Account account = adao.getAccountById(rs.getInt("author_id"));
            String image_url = rs.getString("image_url");
            String backlink_url = rs.getString("backlink_url");
            int status = rs.getInt("status");
            Slider obj = new Slider(account, image_url, backlink_url, status);
            vector.add(obj);
        }
        return vector;
    }

    public Vector<Slider> getSliderByIdOrder() throws Exception {
        Vector<Slider> vector = new Vector<Slider>();
        String sql = "select * from SLider order by id;";
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet rs = pre.executeQuery(sql);
        AccountDAO adao = new AccountDAO();
        while (rs.next()) {
            int id = rs.getInt("id");
            Account account = adao.getAccountById(rs.getInt("author_id"));
            String image_url = rs.getString("image_url");
            String backlink_url = rs.getString("backlink_url");
            int status = rs.getInt("status");
            Slider obj = new Slider(account, image_url, backlink_url, status);
            vector.add(obj);
        }
        return vector;
    }

    public Vector<Slider> getSliderByAccountOrder() throws Exception {
        Vector<Slider> vector = new Vector<Slider>();
        String sql = "select * from SLider order by author_id;";
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet rs = pre.executeQuery(sql);
        AccountDAO adao = new AccountDAO();
        while (rs.next()) {
            int id = rs.getInt("id");
            Account account = adao.getAccountById(rs.getInt("author_id"));
            String image_url = rs.getString("image_url");
            String backlink_url = rs.getString("backlink_url");
            int status = rs.getInt("status");
            Slider obj = new Slider(account, image_url, backlink_url, status);
            vector.add(obj);
        }
        return vector;
    }

    public Vector<Slider> getSliderByStatusOrder() throws Exception {
        Vector<Slider> vector = new Vector<Slider>();
        String sql = "select * from SLider order by status;";
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet rs = pre.executeQuery(sql);
        AccountDAO adao = new AccountDAO();
        while (rs.next()) {
            int id = rs.getInt("id");
            Account account = adao.getAccountById(rs.getInt("author_id"));
            String image_url = rs.getString("image_url");
            String backlink_url = rs.getString("backlink_url");
            int status = rs.getInt("status");
            Slider obj = new Slider(account, image_url, backlink_url, status);
            vector.add(obj);
        }
        return vector;
    }

    // </editor-fold>
    public void updateSliderById(int id, Slider slider) throws Exception {
        String sql = "UPDATE Slider\n"
                + " SET image_url = ?, author_id = ?, backlink_url = ?, status = ?\n"
                + "where id = ?";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, slider.getImage_url());
        pre.setInt(2, slider.getAccount().getId());
        pre.setString(3, slider.getBacklink_url());
        pre.setInt(4, slider.getStatus());
        pre.setInt(5, id);
        pre.executeUpdate();
        pre.close();
    }

    public void deleteSlider(int id) throws Exception {
        String sql = "SELECT id\n"
                + " ,image_url\n"
                + " ,author_id\n"
                + " ,backlink_url\n"
                + " ,status\n"
                + " FROM Slider";
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
        String sql = "INSERT INTO Slider\n"
                + " ,image_url\n"
                + " ,author_id\n"
                + " ,backlink_url\n"
                + " ,status\n"
                + " VALUES (? ,? ,? ,?)";
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
//        Slider slider1 = new Slider();
//        Slider slider2 = new Slider();
        try {
////            slider.setAccount(adao.getAccountByEmail("b@gmail.com"));
////            slider.setBacklink_url("https://");
////            slider.setImage_url("");
////            slider.setStatus(1);
//
//            slider1.setAccount(adao.getAccountByEmail("b@gmail.com"));
//            slider1.setBacklink_url("https://");
//            slider1.setImage_url("https://cdn.pixabay.com/photo/2016/02/10/16/37/cat-1192026_1280.jpg");
//            slider1.setStatus(1);
//
//            slider2.setAccount(adao.getAccountByEmail("b@gmail.com"));
//            slider2.setBacklink_url("https://");
//            slider2.setImage_url("https://cdn2.fptshop.com.vn/unsafe/Uploads/images/tin-tuc/168364/Originals/meme-con-meo%20(1).jpg");
//            slider2.setStatus(1);
//            sdao.insertSlider(slider1);
//            sdao.insertSlider(slider2);
////            System.out.println(sdao.getSliderById(sdao.getMaxId()).toString());
////            System.out.println(sdao.getSliderById(sdao.getMaxId()).getImage_url());
////            sdao.deleteSlider(1);
////            sdao.deleteSlider(2); 
            for (Slider s : sdao.getSliderByAccountOrder()) {
                System.out.println(s.toString());
            }

//            slider.setAccount(adao.getAccountByEmail("b@gmail.com"));
//            slider.setBacklink_url("heheheh");
//            slider.setImage_url("hahaha");
//            slider.setStatus(1);
////            slider.setId(6); 
//            sdao.updateSliderById(6, slider);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
