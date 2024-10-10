/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.PersonalCourse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import util.DataConvert;

/**
 *
 * @author 84941
 */
public class PersonalCourseDAO extends DBContext {

    DataConvert dc = new DataConvert();

    public Vector<PersonalCourse> getPersonalCourses(String sql) {
        Vector<PersonalCourse> vector = new Vector<PersonalCourse>();
        try {
            Statement state = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                int customerId = rs.getInt(2);
                int courseId = rs.getInt(3);
                Date enrollDate = rs.getDate(4);
                Date expireDate = rs.getDate(5);
                int progress = rs.getInt(6);
                int status = rs.getInt(7);
                
                int pricePackageId = rs.getInt(8);
                PersonalCourse obj = new PersonalCourse(id, customerId, courseId, enrollDate, expireDate, progress, status, pricePackageId);
                vector.add(obj);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;

    }
}
