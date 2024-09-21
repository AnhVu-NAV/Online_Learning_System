/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
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
    public Date StringToSqlDate(String string_date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
             Date date = formatter.parse(string_date);
             return date;
        } catch(ParseException e){
            e.printStackTrace();
        }
        return null;
        
    }
    
}
