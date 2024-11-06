/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author 84941
 */
public class Course {

    private int id;
    private String title;
    private String subtitle;
    private int expertId;
    private int totalDuration;
    private int categoryId;
    private String description;
    private int status;
    private Date updatedDate;
    private Date createdDate;
    private int numberOfLearner;

    public Course() {
    }

    public Course(int id, String title, String subtitle, int expertId, int totalDuration, int categoryId, String description, int status, Date updatedDate, Date createdDate, int numberOfLearner) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.expertId = expertId;
        this.totalDuration = totalDuration;
        this.categoryId = categoryId;
        this.description = description;
        this.status = status;
        this.updatedDate = updatedDate;
        this.createdDate = createdDate;
        this.numberOfLearner = numberOfLearner;
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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getExpertId() {
        return expertId;
    }

    public void setExpertId(int expertId) {
        this.expertId = expertId;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getNumberOfLearner() {
        return numberOfLearner;
    }

    public void setNumberOfLearner(int numberOfLearner) {
        this.numberOfLearner = numberOfLearner;
    }

    @Override
    public String toString() {
        return "Course{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", subtitle='" + subtitle + '\''
                + ", expertId=" + expertId
                + ", totalDuration=" + totalDuration
                + ", categoryId=" + categoryId
                + ", description='" + description + '\''
                + ", status=" + status
                + ", updatedDate=" + updatedDate
                + ", createdDate=" + createdDate
                + ", numberOfLearner=" + numberOfLearner
                + '}';
    }
}
