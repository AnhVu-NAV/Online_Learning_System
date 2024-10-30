///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
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
}

