package dal;

import java.sql.Connection;
import java.util.List;
import model.Chapter;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ChapterDAOTest {

    private ChapterDAO chapterDAO;

    @Before
    public void setUp() throws Exception {
        // Khởi tạo ChapterDAO và kết nối đến cơ sở dữ liệu thật
        chapterDAO = new ChapterDAO();
    }

    @Test
    public void testGetChaptersByCourseId_WithValidData() {
        System.out.println("Running testGetChaptersByCourseId_WithValidData");

        // Giả định rằng courseId = 1 có tồn tại trong cơ sở dữ liệu
        int courseId = 1;

        // Thực thi phương thức lấy danh sách các chương
        List<Chapter> chapters = chapterDAO.getChaptersByCourseId(courseId);

        // Kiểm tra rằng danh sách không rỗng và có đúng số chapter
        assertNotNull(chapters);
        assertFalse(chapters.isEmpty());

        // Kiểm tra dữ liệu chapter đầu tiên (giả sử database đã có sẵn dữ liệu từ file SQL)
        Chapter firstChapter = chapters.get(0);
        assertEquals("Java Basics - Part 1", firstChapter.getTitle()); // Tên chapter đầu tiên
        assertEquals(60, firstChapter.getDuration()); // Thời lượng
    }

    @Test
    public void testGetChaptersByCourseId_WithNoData() {
        System.out.println("getChaptersByCourseId with no data");

        // Giả sử courseId 999 không có dữ liệu
        int courseId = 999;
        
        // Thực thi phương thức
        List<Chapter> result = chapterDAO.getChaptersByCourseId(courseId);

        // Kiểm tra rằng danh sách trả về rỗng
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
