///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LearningMaterial {

    private Lesson lessonId;
    private String title;
    private Date updatedDate;
    private int duration;
    private String videoContentUrl;
    private String htmlContent;
}
