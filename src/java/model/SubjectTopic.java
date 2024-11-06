///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package model;

import java.util.Vector;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ADMIN
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class SubjectTopic {
    private int id; //lesson_id, primary key
    private String title;
    private Vector<Lesson> lessons;

    
}
