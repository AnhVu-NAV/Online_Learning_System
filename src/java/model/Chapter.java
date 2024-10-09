/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author AnhVuNAV
 */
public class Chapter {
    private int id;
    private int order;
    private String title;
    private String subtitle;
    private String briefInformation;
    private String description;
    private int courseId;
    private int status;
    private int duration;
    

    // Danh sách các bài học thuộc chương này
    private List<Lesson> lessons;

    // Constructor không đối số
    public Chapter() {}

    // Constructor đầy đủ
    public Chapter(int id, int order, String title, String subtitle, String briefInformation, String description, int courseId, int status, int duration) {
        this.id = id;
        this.order = order;
        this.title = title;
        this.subtitle = subtitle;
        this.briefInformation = briefInformation;
        this.description = description;
        this.courseId = courseId;
        this.status = status;
        this.duration = duration;
    }

    // Getter và Setter cho các thuộc tính
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getBriefInformation() {
        return briefInformation;
    }

    public void setBriefInformation(String briefInformation) {
        this.briefInformation = briefInformation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
