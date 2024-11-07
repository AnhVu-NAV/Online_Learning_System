/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dal;

import java.util.List;
import model.Course;
import model.PricePackage;
import model.Setting;
import model.Tagline;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author AnhVuNAV
 */
public class CourseDAOTest {
    
    public CourseDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCourses method, of class CourseDAO.
     */
    @Test
    public void testGetCourses() {
        System.out.println("getCourses");
        int page = 0;
        int pageSize = 0;
        CourseDAO instance = new CourseDAO();
        List<Course> expResult = null;
//        List<Course> result = instance.getCourses(page, pageSize);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalCourses method, of class CourseDAO.
     */
    @Test
    public void testGetTotalCourses() {
        System.out.println("getTotalCourses");
        CourseDAO instance = new CourseDAO();
        int expResult = 0;
        int result = instance.getTotalCourses();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCourseCategories method, of class CourseDAO.
     */
    @Test
    public void testGetCourseCategories() {
        System.out.println("getCourseCategories");
        CourseDAO instance = new CourseDAO();
        List<Setting> expResult = null;
//        List<Setting> result = instance.getCourseCategories();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTaglinesByCourseId method, of class CourseDAO.
     */
    @Test
    public void testGetTaglinesByCourseId() {
        System.out.println("getTaglinesByCourseId");
        int courseId = 0;
        CourseDAO instance = new CourseDAO();
        List<String> expResult = null;
//        List<String> result = instance.getTaglinesByCourseId(courseId);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoursesByCategoriesAndKeyword method, of class CourseDAO.
     */
    @Test
    public void testGetCoursesByCategoriesAndKeyword() {
        System.out.println("getCoursesByCategoriesAndKeyword");
        List<String> categories = null;
        String keyword = "";
        String sortOption = "";
        int page = 0;
        int pageSize = 0;
        CourseDAO instance = new CourseDAO();
        List<Course> expResult = null;
//        List<Course> result = instance.getCoursesByCategoriesAndKeyword(categories, keyword, sortOption, page, pageSize);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalCoursesByCategoriesAndKeyword method, of class CourseDAO.
     */
    @Test
    public void testGetTotalCoursesByCategoriesAndKeyword() {
        System.out.println("getTotalCoursesByCategoriesAndKeyword");
        List<String> categories = null;
        String keyword = "";
        CourseDAO instance = new CourseDAO();
        int expResult = 0;
        int result = instance.getTotalCoursesByCategoriesAndKeyword(categories, keyword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCourseById method, of class CourseDAO.
     */
    @Test
    public void testGetCourseById() {
        System.out.println("getCourseById");
        int courseId = 0;
        CourseDAO instance = new CourseDAO();
        Course expResult = null;
        Course result = instance.getCourseById(courseId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPricePackageByCourseId method, of class CourseDAO.
     */
    @Test
    public void testGetPricePackageByCourseId() {
        System.out.println("getPricePackageByCourseId");
        int courseId = 0;
        CourseDAO instance = new CourseDAO();
        PricePackage expResult = null;
        PricePackage result = instance.getPricePackageByCourseId(courseId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRelatedCourses method, of class CourseDAO.
     */
    @Test
    public void testGetRelatedCourses_int() {
        System.out.println("getRelatedCourses");
        int categoryId = 0;
        CourseDAO instance = new CourseDAO();
        List<Course> expResult = null;
//        List<Course> result = instance.getRelatedCourses(categoryId);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRelatedCourses method, of class CourseDAO.
     */
    @Test
    public void testGetRelatedCourses_3args() {
        System.out.println("getRelatedCourses");
        int categoryId = 0;
        int expertId = 0;
        int courseId = 0;
        CourseDAO instance = new CourseDAO();
        List<Course> expResult = null;
//        List<Course> result = instance.getRelatedCourses(categoryId, expertId, courseId);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllTaglines method, of class CourseDAO.
     */
    @Test
    public void testGetAllTaglines() {
        System.out.println("getAllTaglines");
        CourseDAO instance = new CourseDAO();
        List<Tagline> expResult = null;
//        List<Tagline> result = instance.getAllTaglines();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRelatedCoursesByTaglines method, of class CourseDAO.
     */
    @Test
    public void testGetRelatedCoursesByTaglines() {
        System.out.println("getRelatedCoursesByTaglines");
        List<String> taglines = null;
        int courseId = 0;
        CourseDAO instance = new CourseDAO();
//        List<Course> expResult = null;
//        List<Course> result = instance.getRelatedCoursesByTaglines(taglines, courseId);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRelatedCoursesByFilters method, of class CourseDAO.
     */
    @Test
    public void testGetRelatedCoursesByFilters() {
        System.out.println("getRelatedCoursesByFilters");
        int categoryId = 0;
        List<String> taglines = null;
        int limit = 0;
        CourseDAO instance = new CourseDAO();
        List<Course> expResult = null;
//        List<Course> result = instance.getRelatedCoursesByFilters(categoryId, taglines, limit);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
