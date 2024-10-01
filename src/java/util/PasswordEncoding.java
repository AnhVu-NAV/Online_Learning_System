/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.security.MessageDigest;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author ADMIN
 */
public class PasswordEncoding {

    public static String getEncodingPassword(String rawPassword) {
        // Creating a salt code
        String salt = "ahsbdajnsbdj21ek;ádjuadawdwd231";
        String encryptedPassword = null;

        // Thêm salt code vào để tăng tính bảo mật 
        rawPassword = rawPassword + salt;
        try {
            // Transfer the combonation of raw password and salt code into byte array using UTF-8
            byte[] dataBytes = rawPassword.getBytes("UTF_8");
            // Compute a hash for the input data
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Pass the byte array and the method used for encryption
            encryptedPassword = Base64.encodeBase64String(md.digest(dataBytes));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return encryptedPassword;
    }
}
