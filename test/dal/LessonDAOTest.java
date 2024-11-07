/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dal;

import java.util.List;
import model.Lesson;
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
public class LessonDAOTest {
    
    public LessonDAOTest() {
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
     * Test of getLessonsByCourseId method, of class LessonDAO.
     */
    @Test
    public void testGetLessonsByCourseId() {
        System.out.println("getLessonsByCourseId");
        int courseId = 0;
        LessonDAO instance = new LessonDAO();
        List<Lesson> expResult = null;
        List<Lesson> result = instance.getLessonsByCourseId(courseId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLessonsByChapterId method, of class LessonDAO.
     */
    @Test
    public void testGetLessonsByChapterId() {
        System.out.println("getLessonsByChapterId");
        int chapterId = 0;
        LessonDAO instance = new LessonDAO();
        List<Lesson> expResult = null;
        List<Lesson> result = instance.getLessonsByChapterId(chapterId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
