/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;
import java.util.Date;
/**
 *
 * @author 84941
 */
public class DataConvert {
    public java.sql.Date UtilDateToSqlDate(Date utilDate) {
        if (utilDate != null) {
            // Convert java.util.Date to java.sql.Date
            return new java.sql.Date(utilDate.getTime());
        } else {
            return null; // Handle null input
        }
    }
}
