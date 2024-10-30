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
    private int status;
    private int lessonTypeId;
    private String title;
    private int chapterId;
    private int order;

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

    // Getter và Setter cho các thuộc tính
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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