/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Lesson {

    private int chapterId;
    private int id;
    private int status;
    private int lessonTypeId;
    private int order;
    private String title;

    public Lesson() {
    }

    public Lesson(int chapterId, int id, int status, int lessonTypeId) {
        this.chapterId = chapterId;
        this.id = id;
        this.status = status;
        this.lessonTypeId = lessonTypeId;
    }

    public int getCourseId() {
        return chapterId;
    }

    public void setCourseId(int chapterId) {
        this.chapterId = chapterId;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Lesson{" + "courseId=" + chapterId + ", id=" + id + ", status=" + status + ", lessonTypeId=" + lessonTypeId + '}';
    }

}
