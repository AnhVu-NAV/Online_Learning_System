/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Quiz {
    private int lessonId;
    private String title;
    private int duration;
    private float passRate;
    private Date updatedDate;
    private String description;
    private String subtitle;
    private int type;

    public Quiz() {
    }
    
    public Quiz(int lessonId, String title, int duration, float passRate, Date updatedDate, String description, String subtitle, int type) {
        this.lessonId = lessonId;
        this.title = title;
        this.duration = duration;
        this.passRate = passRate;
        this.updatedDate = updatedDate;
        this.description = description;
        this.subtitle = subtitle;
        this.type = type;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getPassRate() {
        return passRate;
    }

    public void setPassRate(float passRate) {
        this.passRate = passRate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Quiz{" + "lessonId=" + lessonId + ", title=" + title + ", duration=" + duration + ", passRate=" + passRate + ", updatedDate=" + updatedDate + ", description=" + description + ", subtitle=" + subtitle + ", type=" + type + '}';
    }
    
}
