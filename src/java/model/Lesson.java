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


/**
 *
 * @author AnhVuNAV
 */

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
    private String title;                     // Tên bài học
    private int status;
    private Setting lessonTypeId;       // Loại bài học (tham chiếu đến SubjectTopic)
    private String topic;                    // Chủ đề bài học
    private int order;                       // Thứ tự của bài học
    private Vector<LearningMaterial> learningMaterials; // Danh sách tài liệu học
    private Date createdAt;         // Thời gian tạo
    private Date updatedAt;         // Thời gian cập nhật

    // Constructor không đối số
    public Lesson() {}

    // Constructor đầy đủ
    public Lesson(int id, int status, int lessonTypeId, String title, int chapterId, int order) {
        this.id = id;
        this.status = status;
        this.lessonTypeId = lessonTypeId;
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

    // Getter và Setter cho các thuộc tính
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

        // Getter và Setter cho các thuộc tính
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLessonTypeId() {
        return lessonTypeId;
    }

    public void setLessonTypeId(int lessonTypeId) {
        this.lessonTypeId = lessonTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    
}


