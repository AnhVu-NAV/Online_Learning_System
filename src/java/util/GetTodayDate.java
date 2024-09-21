/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author 84941
 */
public class GetTodayDate {

    public static Date getTodayDate() {
        LocalDate localDate = LocalDate.now();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date date = formatter.parse(localDate.toString());
            return date;
        }catch(ParseException e){
            e.printStackTrace();
        }
        return null;  
    }

//    public static void main(String[] args) {
//        GetTodayDate getTodayDate = new GetTodayDate();
//        Date today = getTodayDate.getTodayDate();
//        System.out.println("Today's date : " + today);
//        
//    }
}
