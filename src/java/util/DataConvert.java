/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class DataConvert {

    public static java.sql.Date convertDate(Date date) {
        if (date != null) {
            // Convert java.util.Date to java.sql.Date
            return new java.sql.Date(date.getTime());
        } else {
            return null; // Handle null input
        }
    }
}
