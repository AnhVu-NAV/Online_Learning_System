/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.security.SecureRandom;

/**
 *
 * @author 84941
 */
public class CreateRandom {
<<<<<<< HEAD

=======
>>>>>>> fbf38e88c7423732c03f084d6c211a2ea03af5a2
    public static String generate6_DigitCode() {
        // Create a SecureRandom subject 
        SecureRandom random = new SecureRandom();

        // Create a 6-digit code
        int code = 100000 + random.nextInt(900000);

        // Tranfer int into string
        return String.valueOf(code);
    }
}
