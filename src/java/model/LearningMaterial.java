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
public class LearningMaterial {
    private int lessonId;
    private String title;
    private Date uploadDate;
    private int duration;
    private int type;

    public LearningMaterial(int lessonId, String title, Date uploadDate, int duration, int type) {
        this.lessonId = lessonId;
        this.title = title;
        this.uploadDate = uploadDate;
        this.duration = duration;
        this.type = type;
    }

    // Constructors
    public LearningMaterial() {}

    public int getType() {
        return type;
    }

    public void setType(int type) {
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

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

   
}

