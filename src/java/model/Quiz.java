/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Quiz {

    private int lessonId;
    private int duration;
    private float passRate;
    private Date updatedDate;
    private String description;
    private String title;
    public String subtitle;

    public Quiz() {
    }

    public Quiz(int lessonId, int duration, float passRate, Date updatedDate, String description, String title, String subtitle) {
        this.lessonId = lessonId;
        this.duration = duration;
        this.passRate = passRate;
        this.updatedDate = updatedDate;
        this.description = description;
        this.title = title;
        this.subtitle = subtitle;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
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

    @Override
    public String toString() {
        return "Quiz{" + "lessonId=" + lessonId + ", duration=" + duration + ", passRate=" + passRate + ", updatedDate=" + updatedDate + ", description=" + description + ", title=" + title + ", subtitle=" + subtitle + '}';
    }

}
