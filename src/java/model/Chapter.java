/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
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

    public Chapter() {
    }

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

    @Override
    public String toString() {
        return "Chapter{" + "id=" + id + ", order=" + order + ", title=" + title + ", subtitle=" + subtitle + ", briefInformation=" + briefInformation + ", description=" + description + ", courseId=" + courseId + ", status=" + status + ", duration=" + duration + '}';
    }

}
