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
public class DateConvert {

    public static java.sql.Date convertToSQLDate(Date date) {
        if (date != null) {
            // Convert java.util.Date to java.sql.Date
            return new java.sql.Date(date.getTime());
        }
        return null; // Handle null input
    }

    public static java.util.Date convertToUtilDate(Date date) {
        if (date != null) {
            return new java.util.Date(date.getTime());
        }
        return null;
    }
}
