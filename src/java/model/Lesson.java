/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author AnhVuNAV
 */
public class Lesson {
    private int id;
    private String title;
    private int status;
    private int lessonTypeId;

    public Lesson() {
    }

    public Lesson(int id, String title, int status, int lessonTypeId) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.lessonTypeId = lessonTypeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    
}
