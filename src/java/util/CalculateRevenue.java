/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import dal.CourseDAO;
import dal.PersonalCourseDAO;
import dal.PricePackageDAO;
import dal.SettingDAO;
import dal.SettingTypeDAO;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import model.Course;
import model.PersonalCourse;
import model.PricePackage;
import model.Setting;
import model.SettingType;

/**
 *
 * @author 84941
 */
public class CalculateRevenue {

    public static Map<Integer, Integer> calculateRevenueByCourseCategoryId(Vector<PersonalCourse> personalCourseCategory) {
        Map<Integer, Integer> revenueByCourseCategory = new HashMap<>();
        //declare dao
        PersonalCourseDAO personalCourseDAO = new PersonalCourseDAO();
        PricePackageDAO pricePackageDAO = new PricePackageDAO();
        SettingDAO settingDAO = new SettingDAO();
        CourseDAO courseDAO = new CourseDAO();
        SettingTypeDAO settingTypeDAO = new SettingTypeDAO();
        //get all course category setting
        SettingType settingType = settingTypeDAO.getSettingTypeByName("Course Category");
        Vector<Setting> settingVector = settingDAO.getSettings("select*from setting where setting_type_id=" + settingType.getId());
        //get all course
        Vector<Course> courseVector = courseDAO.getCourses("select*from course");
        //get all price package
        Vector<PricePackage> pricePackageVector = pricePackageDAO.getPricePackages("select*from pricepackage");
        for (Setting setting : settingVector) {
            revenueByCourseCategory.put(setting.getId(), 0);
        }
        for (PersonalCourse personalCourse : personalCourseCategory) {
            for (Course course : courseVector) {
                for (PricePackage pricePackage : pricePackageVector) {
                    if((personalCourse.getCourseId()==course.getId())&&(personalCourse.getPricePackageId()==pricePackage.getId())){
                        revenueByCourseCategory.put(course.getCategoryId(), revenueByCourseCategory.get(course.getCategoryId())+pricePackage.getSalePrice());
                    }
                }
            }
        }
        return revenueByCourseCategory;

    }
}
