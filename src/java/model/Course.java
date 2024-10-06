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
public class Course {

    private int id;
    private String title;
    private String subtitle;
    private int expertId;
    private float totalDuration;
    private int status;
    private String description;
    private int categoryId;
    private Date createdDate;
    private Date updatedDate;
    private String thumbnailUrl;
    private int numberOfLearner;

    public Course() {
    }

    public Course(int id, String title, int expertId, float totalDuration, int status, String description, int categoryId, Date createdDate, Date updatedDate, String thumbnailUrl, int numberOfLearner) {
        this.id = id;
        this.title = title;
        this.expertId = expertId;
        this.totalDuration = totalDuration;
        this.status = status;
        this.description = description;
        this.categoryId = categoryId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.thumbnailUrl = thumbnailUrl;
        this.numberOfLearner = numberOfLearner;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter and Setter methods. Click on the + sign on the left to edit the code.">
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

    public int getExpertId() {
        return expertId;
    }

    public void setExpertId(int expertId) {
        this.expertId = expertId;
    }

    public float getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(float totalDuration) {
        this.totalDuration = totalDuration;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getNumberOfLearner() {
        return numberOfLearner;
    }

    public void setNumberOfLearner(int numberOfLearner) {
        this.numberOfLearner = numberOfLearner;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    // </editor-fold>

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", title=" + title + ", expertId=" + expertId + ", totalDuration=" + totalDuration + ", status=" + status + ", description=" + description + ", categoryId=" + categoryId + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", thumbnailUrl=" + thumbnailUrl + ", numberOfLesson=" + numberOfLearner + '}';
    }

}
