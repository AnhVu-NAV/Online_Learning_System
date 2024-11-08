/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.util.Vector;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {

    private int id;
    private int status;
    private String title;
    private int chapterId;
    private int lessonId;                    // ID của bài học
    private Course courseId;                 // Tham chiếu đến Course (khoá học)
    private Setting lessonType;       // Loại bài học (tham chiếu đến Setting)
    private String topic;                    // Chủ đề bài học
    private int order;                       // Thứ tự của bài học
    private Vector<LearningMaterial> learningMaterials; // Danh sách tài liệu học
    private Date createdAt;         // Thời gian tạo
    private Date updatedAt;         // Thời gian cập nhật
    private int lessonTypeId;

    // Constructor đầy đủ
    public Lesson(int id, int status,  Setting lessonType, String title, int chapterId, int order) {
        this.id = id;
        this.status = status;
        this.lessonType = lessonType;
        this.title = title;
        this.chapterId = chapterId;
        this.order = order;
    }

    public Lesson(int id, String title, int status, int lessonTypeId) {
        this.id = id;
        this.status = status;
        this.lessonTypeId = lessonTypeId;
        this.title = title;
        this.chapterId = chapterId;
        this.order = order;
    }

}
