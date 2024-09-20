/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.*;

/**
 *
 * @author ADMIN
 */
public class LearningMaterial {

    private Lesson lesson_id;
    private Date updated_date;
    private int duration;
    private String file_url;
    private String videoContentUrl;
    private String htmlContent;
    private String title;

    public LearningMaterial() {
    }

    public LearningMaterial(Lesson lesson_id, Date updated_date, int duration, String file_url, String videoContentUrl, String htmlContent, String title) {
        this.lesson_id = lesson_id;
        this.updated_date = updated_date;
        this.duration = duration;
        this.file_url = file_url;
        this.videoContentUrl = videoContentUrl;
        this.htmlContent = htmlContent;
        this.title = title;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter and Setter methods. Click on the + sign on the left to edit the code.">
    public Lesson getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(Lesson lesson_id) {
        this.lesson_id = lesson_id;
    }

    public Date getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Date updated_date) {
        this.updated_date = updated_date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getVideoContentUrl() {
        return videoContentUrl;
    }

    public void setVideoContentUrl(String videoContentUrl) {
        this.videoContentUrl = videoContentUrl;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    // </editor-fold>

    @Override
    public String toString() {
        return "LearningMaterial{" + "lesson_id=" + lesson_id + ", updated_date=" + updated_date + ", duration=" + duration + ", file_url=" + file_url + ", videoContentUrl=" + videoContentUrl + ", htmlContent=" + htmlContent + ", title=" + title + '}';
    }

}
