/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dal;

import java.util.List;
import model.PricePackage;
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
public class PricePackageDAOTest {
    
    public PricePackageDAOTest() {
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
     * Test of getPricePackagesByCourseId method, of class PricePackageDAO.
     */
    @Test
    public void testGetPricePackagesByCourseId() {
        System.out.println("getPricePackagesByCourseId");
        int courseId = 0;
        PricePackageDAO instance = new PricePackageDAO();
        List<PricePackage> expResult = null;
        List<PricePackage> result = instance.getPricePackagesByCourseId(courseId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
