/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class LearningMaterial {

    private int lessonId;
    private String title;
    private Date uploadDate;
    private Date updatedDate;
    private int duration;
    private int type;
    private String videoUrl;
    private String htmlContent;

    public LearningMaterial() {
    }

    public LearningMaterial(int lessonId, String title, Date uploadDate, int duration, int type) {
        this.lessonId = lessonId;
        this.title = title;
        this.uploadDate = uploadDate;
        this.duration = duration;
        this.type = type;
    }

}
