/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.*;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class SliderDAO extends DBContext {

    public ArrayList<byte[]> getImage() {
        ArrayList<byte[]> list = new ArrayList<>();
        String sql = """
                     select image_data from Slider 
                     """;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                list.add(rs.getBytes("image_data"));
            }
            rs.close();
            pre.close();
            return list;
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
        return null;
    }
    
    public static void main(String[] args) {
        SliderDAO sdao = new SliderDAO();
        for (byte[] b: sdao.getImage()) {
            for (int i = 0; i< b.length; i++) {
                System.out.println(b[i]);
            }
        }
    }
}
